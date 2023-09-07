package com.kosmo.springapp.basic.fileupdown;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.util.UriUtils;

/*
	CommonsMultipartResolver 		
	최대 업로드 용량 사이즈 설정:setMaxUploadSize(바이트) -1:무제한
	용량 초과시 org.springframework.web.multipart.MaxUploadSizeExceededException 예외 발생
*/
@ControllerAdvice//여기서 에러처리, 없으면 다른 곳으로 (전역처리하는 곳) 이동
@Controller
public class FileUpDownController {
	
	/*
	 * 파일 업로드시 MultipartFile API사용
	 * 주요 메소드:
	 * getOriginalFilename()-사용자가 올린 파일명
	 * getSize()-파일 크기(바이트)
	 * getContentType()-파일 컨텐트 타입
	 * getName()-input type="file"의 name속성에 지정한 값
	 * ※MultipartFile객체의 transferTo(File f)메소드 호출만으로 업로드 처리됨.
		
	   ※enctype="multipart/form-data" 일때
	     Map은 input type="file"을 받지 못한다.-> @RequestParam Map map 하던거 불가함, @RequestParam MultipartFile 해야함, 아니면 DTO만들어야함
	     (단, file 말고는 Map으로 당연히 받을 수 있음)
	     또한 checkbox(여러개 선택해도) 는 하나만 받는다.
	     
	   ※최대 업로드 용량 초과시 WARNresolved 에러남  
	     
	     
	     
	 */
	
	@Value("${file.upload}")//스프링 프레임워크꺼 임포트
	private String saveDirectory;//여기에 .properties의 file.upload = /upload 주입
	
	@PostMapping(value = "/FileUpDown/FileUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	//1.Map 및 List<MultipartFile>로 파일포함 모든 파라미터 받기
	/*
	public String upload(
			@RequestParam Map map,//기타 파라미터 받기용
			@RequestParam List<MultipartFile> files,//type="file" 받기용(List<MultipartFile> 이렇게 하는게 필수!!! 3개 파일이면 이거 객체 3개 생김)
			HttpServletRequest req,//서버의 물리적 경로 얻기용
			Model model) throws IllegalStateException, IOException {
		
		//기타 파라미터 출력
		System.out.println("작성자:"+map.get("writer"));//현재 여기 서버에서 받아 출력해 확인해봄 잘 받았는지
		System.out.println("제목:"+map.get("title"));
		System.out.println("파일들:"+map.get("files"));//이거 안돼~~
		
		 	//작성자:가길동
			//제목:123
			//파일들:null -> map으로 못받는다~~~ 
		 
		
		//파일정보 저장용
		List<File> uploadFiles = new Vector<>();
		//1] 서버의 물리적 경로 얻기(webapp에 upload폴더 생성함, .properties도 확인)
//		System.out.println(saveDirectory);// /upload
		String phisicalPath = req.getServletContext().getRealPath(saveDirectory);
//		System.out.println(phisicalPath);// D:\HJJ\Workspace\Java\Spring\springapp\src\main\webapp\ upload 
		for(MultipartFile mFile: files) {
			//2]File객체 생성
			//파일 중복시 이름 변경
			String newFilename = FileUtils.getNewFileName(phisicalPath, mFile.getOriginalFilename());
			File file = new File(phisicalPath+File.separator+newFilename);
			//업로드한 파일객체 저장
			uploadFiles.add(file);
			//3]파일 업로드
			mFile.transferTo(file);//예외처리 던져
		}
		//데이터 저장
		model.addAttribute("uploadFiles",uploadFiles);
		//기타 파라미터
		model.addAllAttributes(map);
		
		return "fileupdown12/UploadComplete";
	}
*/
	//2.자바빈으로 모든 파라미터 받기
	public String upload(
			UploadCommand cmd,//기타 파라미터 및 파일들 받기용
			HttpServletRequest req,//서버의 물리적 경로 얻기용
			Model model) throws IllegalStateException, IOException {
		
		
		//파일정보 저장용
		List<File> uploadFiles = new Vector<>();
		//1] 서버의 물리적 경로 얻기(webapp에 upload폴더 생성함, .properties도 확인)
		String phisicalPath = req.getServletContext().getRealPath(saveDirectory);
		for(MultipartFile mFile: cmd.getFiles()) {
			//2]File객체 생성
			//파일 중복시 이름 변경
			String newFilename = FileUtils.getNewFileName(phisicalPath, mFile.getOriginalFilename());
			File file = new File(phisicalPath+File.separator+newFilename);
			//업로드한 파일객체 저장
			uploadFiles.add(file);
			//3]파일 업로드
			mFile.transferTo(file);//예외처리 던져
		}
		//데이터 저장
		model.addAttribute("uploadFiles",uploadFiles);
		//기타 파라미터
		model.addAttribute("writer", cmd.getWriter());
		model.addAttribute("title", cmd.getTitle());
		
		return "fileupdown12/UploadComplete";
	}
	
	
	
	
	
	
	
	//최대 업로드 용량 초과시 예외 처리 - 업로드를 처리하는 스프링 컨텍스트의 빈이 파라미터를 가로챔
	//그래서 핸들러(=컨트롤러) 메소드로 파라미터 전달이 안된다(param.으로 출력불가, ex.value="${param.writer }" -> 올린이에 쓴 값 안뜸)
	//따라서 예외처리는 전역으로 처리해야한다
	
	//방법1: 페이지 서비스 - 이때는 파라미터를 받지 못한다
	/* ExceptionControllerAdvice.java로 옮김
	@ExceptionHandler({FileSizeLimitExceededException.class, MaxUploadSizeExceededException.class})//tomcat꺼임
	public String maxUploadError(Model model) {
		model.addAttribute("maxUploadSizeError", "파일 최대 업로드 용량을 초과했어요");
		return "fileupdown12/FileUpDown";
	}
	*/
	
	@GetMapping("/FileUpDown/FileList")
	public String list(HttpServletRequest req, Model model) {
		//1]서버의 물리적 경로 얻기
	    String phisicalPath = req.getServletContext().getRealPath(saveDirectory);
	    //2]File객체 생성
	    File f = new File(phisicalPath);
	    File[] files = f.listFiles();
	    req.setAttribute("files", files);//굳이 모델에 안담고 req에 담음
	    
	    /*
	    List<File> fileList = new ArrayList<>();
	    
	    if (files != null) {
	        for (File file : files) {
	            if (file.isFile()) {
	                fileList.add(file);
	            }
	        }
	    }
	    model.addAttribute("fileList", fileList);
	    */
	    return "fileupdown12/FileList";
	}
	
	//파일 다운로드
	@GetMapping("/FileUpDown/FileDownload")//
	public ResponseEntity<Resource> download(@RequestParam String filename, HttpServletRequest req) throws MalformedURLException{//core에 있는 Resource, 파라미터 하나 넘어옴, 한개니까 그냥 String으로 받음
		//1]서버의 물리적 경로 얻기
	    String phisicalPath = req.getServletContext().getRealPath(saveDirectory);
	    //2]File객체 생성
	    File f = new File(phisicalPath+File.separator+filename);
	    //3]응답바디에 쓸 UrlResource객체 생성
	    UrlResource resource = new UrlResource("file:"+f.getAbsolutePath());//파일의 패스를 String으로 줌, 에러 던져
	    //4]한글 파일명 인코딩 처리
	    filename = UriUtils.encode(filename, "UTF-8");
	    //5]브라우저 자체 파일 다운로드 상자 뜨도록 응답헤더 설정
	    //헤더명: content-Disposition, 헤더값: attachment; filename="파일명"
	    return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+filename).body(resource);
	    //ok하면 200번
	    
	    
		
	}
	
	
	
	
	
	
	
}
