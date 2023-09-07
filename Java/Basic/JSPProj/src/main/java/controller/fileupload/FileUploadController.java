package controller.fileupload;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.FileUtils;

//서블릿 3.0부터 @MultipartConfig 어노테이션과 jakarta.servlet.http.Part 인터페이스
//사용하야 쉽게 파일업로드 구현
//maxFileSize: 파일 하나의 업로드 최대 크기(바이트).기본 값은 -1(크기 제한 없음)
//maxRequestSize:하나의 요청의 데이타 최대 크기(바이트).여러 파일 및 기타 전송값등을 합한 최대 크기(기본값 -1)
//fileSizeThreshold : 디스크에 임시적으로 저장될 파일의 크기.(기본값 0)
//※자바에서는 파일 쓰기 시에 먼저 임시 디렉터리에 파일을 저장한다.
//기본 임시 디렉터리는 자바의 시스템 프로퍼티로 java.io.tmpdir로 지정되어 있고
//실제 저장 위치는 System.getProperty("java.io.tmpdir"); 로 확인 할수 있다.
//https://docs.oracle.com/javaee/6/tutorial/doc/gmhal.html

//@WebServlet 대신 web.xml에 서블릿 태그 등록
//최초 요청이 있을 때 생성된다. 단, web.xml에 <servlet>태그를 등록하거나 @WebServlet 어노테이션을 붙여야한다
//그래야 서블릿 컨테이너가 web.xml에서 FileUploadController를 보고 생성해줌
//요청 들어와야 생성, 요청처리 서블릿은 요청 들어왔을 때 만들어지는 LazyLoading(리스너, 필터는 PreLoading 미리 생김)
@MultipartConfig(maxFileSize = 1024*500, maxRequestSize = 1024*500*5)//최대 500kb(기본 바이트단위), 5개까지 
public class FileUploadController extends HttpServlet{
	//HttpServlet 상속 받아야지만 FileUploadController 이게 서블릿이 됨, 이제 web.xml에 등록해서(또는 @WebServlet) 생성이 되면 사용자 요청을 받을 수 있음
	
	public FileUploadController() {
		System.out.println("FileUploadController생성자");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
		//파일을 저장할 서버의 물리적 경로(metadata 어쩌구 나옴, 여기에 저장됨, 프로젝트 익스플로러의 upload에 저장x)
		String saveDirectory = req.getServletContext().getRealPath("/upload");//물리적경로반환
		System.out.println("saveDirectory:"+saveDirectory);
		//https://jakarta.ee/specifications/platform/9/apidocs/
		//Gets all the Part components of this request, provided that it is of type multipart/form-data.
		//Workspace\Java\Basic\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\ wtpwebapps\JSPProj\ upload 에 저장됨
		//멀티타입 폼형태로 제공되는 모든 파트 컴포넌트를 얻을 수 있다(파일뿐만 아니라 name속성도 part로 넘어옴, getSize()이런건 에러날 수 있으므로 if(part.get처리함)------------------
		Collection<Part> parts = req.getParts();//파일 여러개 가져옴(파일용량 초과시 에러), 맨위 설명 3.0부터 구현, 컬렉션 반환, 파일 여러개 가능하므로 -s복수
		//파일용량 초과시 여기서 에러
		System.out.println("올린이:"+req.getParameter("name"));
		System.out.println("임시 저장디렉토리:"+System.getProperty("java.io.tmpdir"));//cf)노출되면 안되는건 환경변수에 지정함, System.getenv로 출력가능
		//https://jakarta.ee/specifications/platform/9/apidocs/
		//https://jakarta.ee/specifications/platform/10/apidocs/
		//업로드한 파일정보를 담기위한 컬렉션 선언
		List<Map<String, String>> fileInfos = new Vector<>();
		
		for(Part part : parts) {
			if(part.getContentType() != null) {//파일인 경우 type="file"이 아닌경우 null
				//ex) 최초 a.txt -> a_1.txt로 변경, 후에 a.txt를 올렸을 때 덮어써지지 않기 위해 이름 바꾸는 것 -> FileUtils.java 확인
				//서버에 중복된 파일명 있으면 덮어쓰기 계속됨
				//중복파일이 저장된 경우 새로운 이름으로 변경
				String systemFileName = FileUtils.getNewFileName(saveDirectory, part.getSubmittedFileName());//기존파일 없으면 올리는 파일이름 반환
				//파일 하나의 정보를 저장할 맵
				Map<String, String> fileMap = new HashMap<>();
				fileMap.put("ContentType", part.getContentType());
				fileMap.put("FileSize", String.valueOf(part.getSize()));
				fileMap.put("SubmittedFileName", part.getSubmittedFileName());
				fileMap.put("systemFileName", systemFileName);
				fileInfos.add(fileMap);
				//파일 업로드
				part.write(saveDirectory+File.separator+systemFileName);//파일구분자: 리눅스는 /으로 파일구분, 윈도우는 / 씀
				System.out.println("컨텐츠 타입:"+part.getContentType());
				System.out.println("파라미터명:"+part.getName());
				System.out.println("파일크기:"+part.getSize()+"바이트");
				System.out.println("원본 파일명:"+part.getSubmittedFileName());
				System.out.println("실제 저장된 파일명:"+systemFileName);//.metadata에 들어갔다가, 아래서 몇번째 하고 들어가보면 업로드한 파일 들어가있음

			}
		}
		//필요정보를 request영역에 저장
		req.setAttribute("fileInfos", fileInfos);
		}
		catch (Exception e) {
			req.setAttribute("ERROR", "업로드 최대 파일용량(파일하나:500KB 파일전체:2.5MB)을 초과했어요");
			req.getRequestDispatcher("/fileupdown12/Upload.jsp").forward(req, resp);
		}
		
		//405에러 HTTP 메소드인 POST는 이 URL에 의해 지원되지 않습니다.
		//post방식으로 보냈으니 doPost를 오버라이딩, 그러면 405 에러 안뜸
		//super.doPost(req, resp);//프로젝트 실행시 게시판 잘 됨
		//서블릿 요청으로 생긴 요청, 응답은 req, resp에 저장됨
		req.getRequestDispatcher("/fileupdown12/UploadComplete.jsp").forward(req, resp);//.jsp 만들기 전에는 404에러뜸
	}	
	
	private List<String> getFileName(Part part) {
		System.out.println("====Content-Disposition start========");
		System.out.println(part.getHeader("Content-Disposition"));
		System.out.println("====Content-Disposition end========");
		/*
		 	form-data; name="attachfile"; filename="자바기초교안.txt"
			form-data; name="attachfile"; filename="자바정규표현식.txt"
		
		 */
		String[] contents=part.getHeader("Content-Disposition").split(";");
		for(int i=0;i < contents.length;i++) {
			System.out.println(String.format("contents[%s]:%s",i,contents[i]));
		}
		/*
		contents[0]:form-data
		contents[1]: name="attachfile"
		contents[2]: filename="자바기초교안.txt"
		
		contents[0]:form-data
		contents[1]: name="attachfile"
		contents[2]: filename="자바정규표현식.txt"
		 */
		List<String> filenames = new Vector<>();
		for(String content : contents) {
			
			if(content.trim().startsWith("filename")) {
				filenames.add(content.split("=")[1]);
			}
			
		}
		return filenames;
	}
	
}
