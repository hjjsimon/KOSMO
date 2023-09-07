package com.kosmo.springapp.basic.ajax;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosmo.springapp.onememo.service.ListPagingData;
import com.kosmo.springapp.onememo.service.OneMemoDTO;
import com.kosmo.springapp.onememo.service.OneMemoService;

@Controller
public class AjaxController {

	//회원여부 판단해야함 서비스 주입
	@Autowired
	private OneMemoService<OneMemoDTO> service;//impl 보니까 OneMemoDTO임
	
	//[AJAX 미사용] - 페이지 다시 받아 새로고침 됨
	//반환타입 void인 경우: 직접 웹브라우저에 출력 스트림으로 결과값 출력
	//반환타입 String인 경우: 디스패처서블릿에게 뷰 반환, 결과값은 모델에 저장(JSP로 포워드, 지금 아래)
	
	@RequestMapping("/Ajax/NoAjax.do")
	public String noajax(@RequestParam Map map, Model model){//id, pwd 받음, model에 데이터 저장
		//1]서비스 호출
		boolean isLogin = service.isLogin(map);
		//2]데이터 저장
		model.addAttribute("isLogin",isLogin? map.get("id")+"님 반갑습니다": "회원정보 불일치");
		//3]뷰정보 반환(페이지 반환)
		return "ajax10/Ajax";
	}
	
	//[AJAX 사용]-페이지를 전송하지 않고 데이터만 전송
	//[TEXT로 응답할때]
	//반환타입은 String 그리고 @ResponseBody()어노테이션 사용
	//페이지 반환 아님, 즉 데이터를 바로 반환(모델계열에 저장x)
	@RequestMapping("/Ajax/AjaxText.do")
	public @ResponseBody String ajaxText(@RequestParam Map map) {//이런식으로 중간에 써도 됨
		
		//1]서비스 호출
		boolean isLogin = service.isLogin(map);
		
		//Ajax.jsp 강제 에러 발생 유도, function확인
		//Integer.parseInt("aaa");
		
		//2]데이터 응답(여기서 return하는게 data가 됨, Ajax.jsp에서 확인) 
		//방법1]
		//return isLogin? "Y": "N";//true면 Y 리턴
		//방법2] Map으로 즉각 메세지 만들어 출력
		return isLogin? map.get("id")+"님 즐감!!!": "회원가입해요";
	}
	/*
	   	스프링 부트는 아래 내용 해당 없음, 기본으로 포함돼있음
	   	사전준비:pom.xml에 jackson-databind라이브러리 의존성 추가
	  
		[Jackson] -> 자바스크립트와 자바(서버,객체)를 소통하는데 통역사역할
		
		Java 객체(Map혹은 DTO)를 JSON으로 변환하거나
		JSON을 Java 객체(Map혹은 DTO)로 변환하는 라이브러리
		
		List계열 컬렉션 즉 List<Map> 혹은 List<DTO>는
		자바스크립트로 변환시 JSON배열로 변환된다
		
		예] [ {key1:value2,key2:value2,},{},{},{}...]
		
		Spring 3.0 이후로부터, Jacskon과 관련된 API를 제공
		Jackson라이브러리를 사용하면 자동화 처리 가능
		
		Jackson라이브러리 pom.xml에 설정시
		
		MessageConverter API인 MappingJacksonHttpMessageConverter API가
		작동하여 컨트롤러가 리턴하는 객체를 후킹하여 ObjectMapper API로 JSON
		형태의 문자열로 자동으로 변환하여 반환한다.	
		
		---------------------------------------------------아래 메소드 있으나 쓸일 없지
		1] 자바객체를 JSON으로 변환시
		1) writeValue(File객체,DTO혹은 MAP) :객체를 JSON파일(.json)로 변환	-> DTO나 MAP이 {키:밸류,,,} 로 작성된 파일로 변환됨
		2) writeValueAsString(DTO혹은 MAP):객체를 JSON형식의 문자열로 변환
		3) writerWithDefaultPrettyPrinter().writeValueAsString(DTO혹은 MAP) -> 이쁘게 바뀜~ 
		
		2] JSON형태의 문자열을 자바객체로 변환시
		1) readValue(File객체,DTO혹은 MAP):JSON파일(.json)에 있는 내용을 자바객체로 읽어들일때
		2) readValue(JSON형식 문자열,DTO혹은 MAP):JSON형식의 문자열을 자바객체로 읽어 들일때
	 */
	 //[JSON으로 응답할때]
	 @PostMapping("/Ajax/AjaxJson.do")
	 @ResponseBody
	 /*
 	 //방법1,2-String타입으로 반환
	 public String ajaxJson(@RequestParam Map map) throws JsonProcessingException {
		 //1]서비스 호출
		 boolean isLogin = service.isLogin(map);
		 
		 //방법1 [ObjectMapper 미사용] 직접 json형식의 문자열 생성해서 반환 - 미친짓
		 //반환 : 회원인 경우- {"isLogin":"방가방가"} 비회원인 경우- {"isLogin":"다음기회에"}
		 //return String.format("{\"isLogin\":\"%s\", \"id\":\"%s\", \"pwd\":\"%s\"}", 
		 //		 isLogin?"방가방가":"다음기회에", map.get("id"), map.get("pwd"));
		 
		 //방법2 [ObjectMapper 사용] - 위보단 나음 반쯤 미친짓
		 //Map 혹은 DTO를 JSON형식의 문자열로 변경 - writeValueAsString()
		 ObjectMapper mapper = new ObjectMapper();
		 map.put("isLogin", isLogin?"방가방가":"다음기회에");
		 return mapper.writeValueAsString(map);//에러는 던져
	 }
	 */
	 //방법3 - Map 혹은 DTO 즉 자바객체로 반환 (권장)
	 public Map ajaxJson(@RequestParam Map map) throws JsonProcessingException {
		 //1]서비스 호출
		 boolean isLogin = service.isLogin(map);
		 map.put("isLogin", isLogin?"방가방가":"다음기회에");
		 //2]데이터 응답
		 return map;
		 
	 }
	
	 @GetMapping("/Ajax/AjaxJsonArray.do")
	 public @ResponseBody List<OneMemoDTO> ajaxJsonArray(@RequestParam Map map, HttpServletRequest req){
		 //Ajax.jsp 보면 data받는게 없으니까 4]AJAX사용 - JSON 배열로 응답받기 보면 인자 없음, 근데 아래 뭐 받아야해서 빈맵 받음
		 System.out.println("요청이 들어왔어요");
		 //1]서비스 호출
		 ListPagingData<OneMemoDTO> records = service.selectList(map, req, 1);
		 return records.getRecords();
		 
	 }
	
	 @GetMapping("/Ajax/AjaxCourse.do")
	 @ResponseBody
	 /*
	 public Map getCurriculum(@RequestParam("course") String course) {
	     Map map = new HashMap();
	     if (course.equals("dotnet")) {
	         map.put("d01", "C#");
	         map.put("d02", "ASP.NET");
	         map.put("d03", "WPF4");
	     } 	
	     else if (course.equals("iot")) {
	    	 map.put("i01", "라즈베리 파이");
	    	 map.put("i02", "파이썬");
	     } 
	     else if (course.equals("java")) {
	    	 map.put("j01", "자바");
	    	 map.put("j02", "JSP");
	    	 map.put("j02", "스프링");
 	     }
 	     return map;
	 }
	 */
	 public Map ajaxCourse(@RequestParam String course) {
		 System.out.println("과정:"+course);//현재 서버(컨트롤러) 쪽에서 받았는지 확인
		 Map map = new TreeMap();
		 switch(course.toUpperCase()) {//DB안만들어서 아래 하드코딩, 원래 안됨, 반복 돌면 두세줄, 서비스 만들면 한줄에 끝남
			 case "JAVA": 
				 map.put("j01", "자바");//value가 키, 텍스트가 밸류
				 map.put("j02", "JSP");
				 map.put("j03", "스프링");
				 break;
			 case "DOTNET": 
				 map.put("d01", "C#");
				 map.put("d02", "ASP.NET");
				 map.put("d03", "WPF4");
				 break;	 
			 default: 
				 map.put("i01", "라즈베리 파이");
				 map.put("i02", "파이썬");
		 }
		 return map;
	 }
	 
	 //Jackson라이브러리의 API 사용하기 - 확인은 STS콘솔에서 확인
	 @Autowired
	 private ObjectMapper mapper;//AjaxConfig에서 new함 
	 
	 //src/main/resources에 있는 자원을 읽거나 쓰는 API: ResourceLoader(중요)
	 @Autowired
	 private ResourceLoader resourceLoader;
	 
	 //1.자바객체(아래 map)를 JSON으로 변환
	 //가. KEY=VALUE쌍으로 데이터 받기(폼태그로 받으면 KEY=VALUE쌍으로 전송)
	 //		map, dto를 반환시 바로 json으로 바뀜, 근데 지금은 변환따로하고 페이지 반환해보기 연습 
	 @PostMapping("/Ajax/form.do")
	 //@ResponseBody 이거 써야 데이터 받는것, 이거 안쓰면 페이지를 받음
	 public String form(@RequestParam Map map, Model model) throws StreamWriteException, DatabindException, IOException {//requestparam 말고 DTO로 받아도 됨


		//[자바객체(Map)를 JSON으로 변환] 
			//1.JSON파일로 서버에 저장
			//mapper.writeValue(new File("onememo.json"), map);//프로젝트 폴더 루트에 생성된다
			
			//단계1:config패키지의 WebConfiguration클래스에 아래 코드 추가
			//     registry.addResourceHandler("/json/**").addResourceLocations("classpath:/json/");
			//     (브라우저에서 추가한 URL패턴으로 json파일을 직접 확인하고자)
			//단계2:ClassPathResource 를 사용해서 파일 생성하고
			//     생성한 리소스는 ClassPathResource 와 ResourceLoader API를 통해 읽을 수 있다
			//단계3:브라우저에서는 http://localhost:포트/컨텍스트루트/json/저장한파일명.json으로 접근
			
	        
			try {
	            // JSON 파일이 위치할 디렉토리 경로를 지정.
	            String filePath = "json";
	            // ClassPathResource객체 생성.
	            ClassPathResource resource = new ClassPathResource(filePath);
	            System.out.println("getPath():"+resource.getPath());
	            System.out.println("getURI():"+resource.getURI());
	            System.out.println("getURL():"+resource.getURL());
	            //물리적 경로 얻기
	            File file = resource.getFile();
	            String directoryPath = file.getAbsolutePath();
	            //생성할 파일의 경로를 지정.
	            String newFilePath = directoryPath + File.separator + "onememo.json";
	            //아래 3줄은 리소스에서 읽은 내용을 새로운 파일로 저장하는 코드
	            //새로 생성할 파일을 OutputStream으로 연다(반드시 바이트 스트림으로 그래야 배포시에도 동작한다).
	            //OutputStream outputStream = new FileOutputStream(newFilePath);
	            //리소스에 있는 파일 읽은 코드
	            // ClassPathResource에서 읽어온 파일의 내용을 바이트 배열로 가져온다.
	            //byte[] jsonData = FileCopyUtils.copyToByteArray(resource.getInputStream());
	            // 새로 생성한 파일에 내용을 쓴다.
	            //outputStream.write(jsonData);
	            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(newFilePath), map);
	         
	            //outputStream.close();

	            System.out.println("새로운 파일이 생성되었습니다: " + newFilePath);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			//뷰정보 반환]
			return "ajax10/Ajax";
		 
	 }
	 
	 //1.자바빈(DTO)을 반환하는 경우 - 자바빈으로 파라미터 받을떄는 @RequestParam어노테이션을 붙이면 400에러 발생(대신 아래 @ModelAttribute(생략가능)는 가능)
	 /*
	 @PostMapping("/Ajax/AjaxDataKeyValue.do")
	 public @ResponseBody OneMemoDTO ajaxDataKeyValue(
			 //@RequestParam OneMemoDTO dto //400에러
			 @ModelAttribute OneMemoDTO dto
			 ){//이번엔 map말고 DTO로 반환, 키밸류는 DTO로 받음, DTO로 받을시 
		 return dto;//JSON형식의 문자열로 변환되서 반환됨
		 //dto 앞에는 attribute 원래 붙여야함
	 }
	 */
	 
 	 //2.Map으로 반환하는 경우 - 값을 받지 않은 속성인 경우 null인 속성 제거효과(@RequestParam 필수)
	 @PostMapping("/Ajax/AjaxDataKeyValue.do")
	 public @ResponseBody Map ajaxDataKeyValue(@RequestParam Map map){
		 return map;//똑같이 JSON형식의 문자열로 변환되서 반환됨, 전달된 파라미터가 키로 저장
		 //{title: '123321', name: '321321', content: '23312'}
	 }
	
	 @PostMapping("/Ajax/AjaxDataJson.do")
	 @ResponseBody
	 public Map ajaxDataJson(@RequestBody Map map) {//이번에 JSON형식 문자열 받는거라 @RequestBody!!!!!!!!!!!!!!!!
		 return map;
	 }
	 
	 @PostMapping("/Ajax/AjaxDataArray.do")
	 @ResponseBody
	 public List<Map> ajaxDataArray() {//Ajax.jsp에 data 설정 없음, 서버에서 받는게 없다는 뜻
		 List<Map> users = new Vector<>();
		 Map map = new HashMap();
		 map.put("id", "KIM");
		 map.put("name", "김길동");
		 users.add(map);
		 
		 map = new HashMap();
		 map.put("id", "LEE");
		 map.put("name", "이길동");
		 users.add(map);
		 
		 return users;//다 JSON으로 넘어간다~
	 }
	
	 //2. JSON을 자바객체로 변환 - 서버에 저장된 .json 파일을 읽어서 브라우정로 보내기
	 //json구조를 자바빈으로 만들어주는 사이트:
	 //https://www.jsonschema2pojo.org/
	 
	 @PostMapping("/Ajax/AjaxJsonToJava.do")
	 @ResponseBody
	 //방법1) JSON을 맵으로 반환
	 //public Map ajaxJsonToJava() throws IOException {
	 //방법2) JSON을 
	 public OneMemoDTO ajaxJsonToJava() throws IOException {
		 Resource resource= resourceLoader.getResource("classpath:static/onememo.json");//"classpath:"는 src/main/resources를 의미한다
		 File file=resource.getFile();	//파일객체 생성
		 //방법1) JSON을 맵으로 반환
		 //Map map = mapper.readValue(file, Map.class);//Map.class로 반환한다는 뜻, file을 자바객체인 map으로 바꾼 것
		 //return map;
		 
		 //방법2) JSON을 자바빈으로 반환
		 return mapper.readValue(file, OneMemoDTO.class);
	 }
	 
	
 
}
/*

ClassPathResource 와 ResourceLoader 
스프링 프레임워크에서 리소스를 로드하는 데 사용하는 API다
	차이점
		ClassPathResource는 클래스이며 ResourceLoader는 인터페이스이다.
		ClassPathResource는 주로 클래스 패스 상의 리소스를 로드하기 위해 사용. 
		ResourceLoader는 클래스 패스외의 다른 리소스 위치에서도 리소스를 로드할 수 있다.

		ClassPathResource는 직접 생성할 수 있다. 
		즉, new ClassPathResource("json")와 같이 객체를 생성하여 사용
		반면에 ResourceLoader는 스프링 컨텍스트에 의해 주입받고 직접 생성하지 않는다.

	공통점
		리소스 로딩: 둘 모두 리소스를 로드하는 기능을 제공.
		파일 경로, 클래스 패스상의 경로, URL 등 다양한 리소스를 로드할 수 있다.
		
		리소스 경로 지정: 둘 모두 리소스 경로를 지정하여 해당 경로에 위치한 
					  리소스를 로드할 수 있다.

		InputStream 반환: 둘 모두 로드한 리소스를 InputStream 형태로 반환.
		 				 이를 통해 리소스의 내용을 읽고 처리할 수 있다.
		둘 모두 스프링 프레임워크에서 제공하는 기능이며 스프링 애플리케이션에서 
		자원을 로드하는 데 사용된다.

요약하면 ClassPathResource는 클래스 패스 상의 리소스에 특화되어 있고 직접 생성할 수 있으며
ResourceLoader는 일반적인 리소스 로딩 인터페이스로 스프링 컨텍스트에 의해 주입받아 사용한다.

*/
  
  
  
  
  
  
  
  
  
  
 



