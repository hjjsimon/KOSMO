package com.kosmo.restapi.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosmo.restapi.model.FileUtils;
import com.kosmo.restapi.model.ObjectDetectDTO;
import com.kosmo.restapi.model.UsersDto;
import com.kosmo.restapi.model.UsersMapper;

import io.swagger.annotations.ApiOperation;

@RestControllerAdvice//이거 추가해야 전역으로 넘어가서 업로드 최대용량 초과했어요 에러시 500 대신 문자열 출력됨
@RestController//컨트롤러 아니고 레스트 컨트롤러! 이게 RestAPI 차이!
public class RestApiController {

	//서비스단 생략, 바로 DB 접속해서 가져옴, 로직 복잡하면 서비스단 넣어야함
	@Autowired
	private UsersMapper mapper;
	
				//jar 배포시
				
				//war 배포시 필요
				@Value("${file.location}")
				private String saveDirectory;
	
	//1.데이타를 key=value쌍으로 받아서 입력
	//-FORM태그를 이용해서 요청하거나
	//-jQuery ajax로 요청시에는 data:"username=KIM&password=1234&name=김길동",
	// 혹은 data:{username:"KIM",password:"1234",name:"김길동"}
	//-POSTMAN으로 요청시에는 Body탭의 x-www-form-urlencoded 선택후 key와 value입력
	//요청 형식
	//POST http://localhost:8080/users	
	/*
	@PostMapping("/users")
	public UsersDto join(UsersDto dto) {		
		int affected= mapper.save(dto);		
		return dto;
	}*/
	
	//2.데이타를 JSON으로 받아서 입력
	//-jQuery ajax로 요청시에는 data:JSON.stringify({키:"값",키2:"값2"}),
	//                       contentType:"application/json"
	//-POSTMAN으로 요청시에는 Body탭의 raw 선택 및 json선택 후 json형태로 데이터 작성
	//요청 형식
	//POST http://localhost:8080/users	-> 스웨거로 회원가입후 여기 들어가면 JSON으로 회원 출력 
	@PostMapping("/users")
	@ApiOperation(value = "회원가입",notes = "JSON형식의 데이터를 받아 JSON형식의 데이터 반환")
	//http://localhost:8080/swagger-ui/#/rest-api-controller/joinUsingPOST 의 POST 설명이 바뀜
	public UsersDto join(@RequestBody UsersDto dto) {		
		int affected= mapper.save(dto);		
		return dto;
	}
	
	//3.회원의 모든 데이타 조회
	/*
	  -FORM태그 혹은 A태그
	  -jQuery ajax
	  -POSTMAN
	 */
	//요청 형식
	//GET http://localhost:8080/users
	@GetMapping("/users")
	@CrossOrigin //Ajax.jsp는 8080이라 9090접근불가, 이거 해줘야 받음
	public List<UsersDto> getAllUsers(){
		return mapper.find();
	}
	
	//4.URI 파라미터(패스 파라미터)로 회원 조회
	/*
	  -FORM태그 혹은 A태그
	  -jQuery ajax
	  -POSTMAN
	 */
	//요청 형식
	//GET http://localhost:8080/users/:username
	@GetMapping("/users/{username}")
	public UsersDto getUserByUsername(@PathVariable String username) {
		return mapper.findByUsername(username);
	}
	
	//5.데이타를 JSON으로 받아서 수정(반드시 JSON으로 받아야 한다)
	//AJAX 혹은 POSTMAN으로 요청
	//※PUT이나 DELETE도 데이타는 요청바디에 싣는다	
	//DTO에는 수정할 비번과 이름을 받자
	//예]{
	//		"password":"5678",
	//		"name":"이길동"
	//	}	
	//요청 형식
	//PUT http://localhost:9090/users/:username
	@PutMapping("/users/{username}")//같은 URL패턴이지만 위에는 GET, 이건 PUT이라 갠찬
	public UsersDto editUser(@PathVariable String username, @RequestBody UsersDto dto) {//두번째는 요청인자의 JSON데이터 받으려고 @RequestBody
		
		//사용자가 수정할 예정
		//어느 회원(레코드)을 수정할지 자바빈에 설정
		dto.setUsername(username);
		mapper.update(dto);//행의 수 반환
		return dto;
		
	}
	
	//6.키를 URL파라미터로 받아서 삭제
	//AJAX 혹은 POSTMAN으로 요청	
	//데이타는 필요없다	
	//요청 형식
	//DELETE http://localhost:9090/users/:username
	
	/*방법1]
	@DeleteMapping("/users/{username}")
	public UsersDto removeUser(@PathVariable String username) {
		UsersDto dto = mapper.findByUsername(username);//삭제 전에 꺼내와야함
		mapper.delete(username);//삭제 후 dto 반환
		return dto;
	}
	*/
	/*
	//방법2] 반환타입 MAP
	@DeleteMapping("/users/{usersname}")
	public Map removeUser(@PathVariable String username) {
		UsersDto dto = mapper.findByUsername(username);
		mapper.delete(username);
		//DTO를 Map으로 변경
		//방법1:DTO를 MAP으로 변경(또는 MAP을 DTO로 가능)
		Map map = new HashMap();
		map.put("username", dto.getUsername());
		map.put("password", dto.getPassword());
		map.put("name", dto.getName());
		map.put("joindate", dto.getJoindate());
		
		*/
		/*
		//방법2:apache common의 BeanUtils.describe(DTO객체); 혹은 Jackson 의 OBjectMapper사용
		ObjectMapper oMapper = new ObjectMapper();
		Map map = oMapper.convertValue(dto, Map.class);;//dto를 Map으로 바꾼 것, 위에 4줄 안해도 됨 편함
		return map;
	}*/

	/*
	//방법3] 반환타입 String으로 해보기
	@DeleteMapping("/users/{username}")
	public String removeUser(@PathVariable String username) throws JsonProcessingException {
		UsersDto dto = mapper.findByUsername(username);
		mapper.delete(username);
		//DTO(또는 MAP)를 문자열로 변환
		ObjectMapper oMapper = new ObjectMapper();
		return oMapper.writeValueAsString(dto);
	}
	*/
	
	//방법4] 반환타입 ResponseEntity
	@DeleteMapping("/users/{username}")
	public ResponseEntity<UsersDto> removeUser(@PathVariable String username) {
		UsersDto dto = mapper.findByUsername(username);
		mapper.delete(username);
		HttpHeaders headers = new HttpHeaders();//스프링프레임워크꺼 임포트
		headers.set("Content-Type", "application/json;charset=UTF-8");//뒤에는 한글처리 해준것
		return ResponseEntity.ok().headers(headers).body(dto);//ok는 성공, 200코드
	}
	
	//7.파일 업로드
	//key=value 형태로 전송
	//FORM태그(enctype="multipart/form-data") 혹은 AJAX 혹은 POSTMAN으로 요청	
	//POSTMAN으로 요청시에는 Body탭의 form-data 선택후
	//key와 value입력
	//파일인 경우 key입력시 옆에 file선택
	//요청 형식
	//POST http://localhost:8080/files
	//talend 잘 안됨 postman이나 스웨거 이용하기
	@CrossOrigin
	@PostMapping(value = "/files",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)//MediaType 스프링프레임워크꺼 임포트, url을 /files로 설정한것
	public ResponseEntity<Map> upload(HttpServletRequest req, @RequestPart List<MultipartFile> files) throws IllegalStateException, IOException{
		//@RequestPart로 받아야 스웨거에서 파일찾기 버튼이 생김, @RequestParam도 잘 받긴 하는데 버튼 안생김 
		
		//서버의 물리적 경로 얻기
		String path = req.getServletContext().getRealPath("/upload");
		//D:\HJJ\Workspace\Java\Spring\restapi\src\main\webapp\ upload 에 저장됨
		for(MultipartFile muFile : files) {//files에서 꺼내옴
			//2]File객체 생성
			//파일 중복시 이름 변경
			String newFilename = FileUtils.getNewFileName(path, muFile.getOriginalFilename());//kosmo꺼 임포트, FileUtils에 메소드 만든거임
			File file = new File(path+File.separator+newFilename);//파일객체 만듦
			//3]파일 업로드
			muFile.transferTo(file);
		}
		Map map = new HashMap<>();
		map.put("success", true);//map넣어줘야해서 씀
		return ResponseEntity.ok().header("Content-Type", "application/json;charset=UTF-8").body(map);
	}
	//개발할때는 주석처리, 테스트 에러 안보이니까
	/*
	@ExceptionHandler({Exception.class})
	public ResponseEntity<String> error(){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.header("Content-Type", "text/plain;charset=UTF-8").body("업로드 최대용량 초과했어요");
				//@RestControllerAdvice 필수!
	}
	*/
	
	/*
	 [RestTemplate]
	 -Spring 3.0부터 지원하는 내장 클래스로 스프링 서버에서 REST한 방식으로 HTTP 통신을 하기위한 API
	 	->별도로 설치할 필요 없는데 RestTemplate 커넥션 풀 사용하기 위해 pom.xml에 httpclient 등록해놨었음
	 -Rest방식으로 다른 서버와 HTTP 통신을 동기 방식으로 쉽게 할수 있는 템플릿
	  (AsyncRestTemplate는 비동기 통신)
	 -기본적으로 Connection pool을 사용하지 않아서
	  많은 요청을 하면 TIME_WAIT로 인해 자원이 점점 부족해져
      서비스에 어려움이 있다
	 -내부적으로 java.net.HttpURLConnection 사용
	 -요청을 보낼때는 HttpEntity<Map혹은 DTO,HttpHeaders>타입에 요청바디(데이타)와 요청헤더와 설정
	 ※클라이언트가 보내는 데이타가 Key=Value쌍(application/x-www-form-urlencoded)일때는 반드시 MultiValueMap 사용
	   데이타가 JSON일때는 (application/json)일때는 MultiValueMap 혹은 Map 사용
	 -응답을 받을때는 ResponseEntity<Map혹은 DTO>
	 */
	 //JSON을 자바객체로 변환하기
	 //https://www.jsonschema2pojo.org
	 //※단,서버에서 받은 JSON의 키에 _가 포함되어 있는 경우 자동으로 카멜 케이스로 바뀌니까 _로 다시 수정해주자
	 //구글에서 _를 껴서 보내줌, 자바빈 바뀔때 카멜케이스 되니 주의
	 //구글 비전 API 사용
	 //객체 감지
	 //POST https://vision.googleapis.com/v1/images:annotate
	/*
	
	ex)아래 requests는 키, 값은 []배열, {}는 클래스로 보면 됨, ObjectDetectDTO 확인
	
	JSON 요청 본문:
		{
		  "requests": [
		    {
		      "image": {
		        "source": {
		          "imageUri": "CLOUD_STORAGE_IMAGE_URI"
		        }
		      },
		      "features": [
		        {
		          "maxResults": RESULTS_INT,
		          "type": "OBJECT_LOCALIZATION"
		        },
		      ]
		    }
		  ]
		}
	요청 헤더	
	-H "Authorization: Bearer $(gcloud auth print-access-token)" \
    -H "x-goog-user-project: PROJECT_ID" \
    -H "Content-Type: application/json; charset=utf-8" \
	 */
	
	@Autowired
	private RestTemplate restTemplate;
	
	@CrossOrigin
	@RequestMapping("/vision/object-detect")//클라이언트는 get요청함//Post 바꾸면 그림에서 찾기 안됨, 그냥 requestMapping가자
	public Map objectDetect(@RequestBody Map paramMap) {//추가)@RequestBody Map paramMap 이전에 없었음 
		
		//401에러: 인증 오류시 아래 에러 핸들러 추가
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {//익명클래스 만듦
			
			@Override
			public boolean hasError(ClientHttpResponse response) throws IOException {
				HttpStatus status = response.getStatusCode();//스프링프레임워크꺼 임포트
				return status.series() == HttpStatus.Series.SERVER_ERROR;
			}
			
		});

		//1.요청헤더 설정용 객체 생성(구글로 요청 보내기 위한), 위에 요청헤더 값 복붙
		//Bearer 뒤에는 구글 클라우드 쉘에 gcloud auth print-access-token 하고 토큰 받아서 출력(토큰 1시간이면 만료)
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer ya29.a0AWY7CkkmZBlQId2U-RfK5RxOzDtWGw-W0t7ODP4-ygS50P2VrwcV_I5XpmbpXOxsYaTqjVGupfR_NWgaWSjnWOljhxiT7iTnTFsaI0Xc0yLE36RnivNvXXD3hbGxrBIrac4XgRAfRzKiiMNEqbz8vPvUmkFOLC-eZ5kSLgaCgYKAXkSARASFQG1tDrphKEsX5zIrQ4GL9ATqbDG-w0173");
		headers.add("x-goog-user-project", "braided-gravity-391102");
		headers.add("Content-Type", "application/json; charset=utf-8");
		
		//2.구글의 요청본문(JSON형태)과 동일한 구조의 DTO로 요청바디 설정
		//방법1: 자바빈에 요청바디 설정
		ObjectDetectDTO requestBody = new ObjectDetectDTO();
		ObjectDetectDTO.Source source = new ObjectDetectDTO.Source();
		//source.setImageUri("https://cloud.google.com/vision/docs/images/bicycle_example.png");//여기에 그림파일 uri 넣으면 됨
		source.setImageUri(paramMap.get("url").toString()); //추가) Ajax.jsp에 지정한 url에서 가져옴 
		
		ObjectDetectDTO.Image images = new ObjectDetectDTO.Image();
		images.setSource(source);

//		"LABEL_DETECTION": 이미지에 포함된 물체나 개념을 탐지하고 관련된 라벨을 제공.
//		"TEXT_DETECTION": 이미지에서 텍스트를 감지하고 추출.
//		"DOCUMENT_TEXT_DETECTION": 이미지에 포함된 문서 전체의 텍스트를 감지하고 추출다.
//		"FACE_DETECTION": 이미지에서 얼굴을 감지하고 얼굴에 대한 특성과 감정을 분석.
//		"LOGO_DETECTION": 이미지에서 로고를 감지하고 관련 정보를 제공.
//		"LANDMARK_DETECTION": 이미지에서 유명한 랜드마크를 감지하고 식별.
//		"IMAGE_PROPERTIES": 이미지의 색상 정보를 분석하고 주요 색상 및 이미지 특성을 제공.
//		"SAFE_SEARCH_DETECTION": 이미지에 대한 안전한 검색 결과를 제공하고 불순한 콘텐츠를 필터링.
		
		ObjectDetectDTO.Feature feature = new ObjectDetectDTO.Feature();
		feature.setMaxResults(20);
		//feature.setType("OBJECT_LOCALIZATION");//동물, 사물 등 일치정보 퍼센트로 확인
		//LABEL_DETECTION으로 바꾸면 20개 가득 채워서 나옴
		//TEXT_DETECTION으로 바꾸면 OCR이 됨
		feature.setType(paramMap.get("type").toString());//추가)Ajax.jsp에 지정한 type으로 읽어옴
		
		ObjectDetectDTO.Request request = new ObjectDetectDTO.Request();
		request.setImage(images);
		request.setFeatures(Arrays.asList(feature));
		
		//한글로 응답받기(근데 안됨)
		ObjectDetectDTO.ImageContext imageContext = new ObjectDetectDTO.ImageContext();
		imageContext.setLanguageHints(Arrays.asList("ko"));
		request.setImageContext(imageContext);
		
		requestBody.setRequests(Arrays.asList(request));

		/*
		//방법2:리소스의 클래스 패스상에 요청본문형식의 JSON파일 생성후 읽어서 맵으로 변환
		//resources 안에 json 폴더에 detect.json 파일 만들고 읽어서 가져오는 방법
		ClassPathResource resource = new ClassPathResource("json");        
        //물리적 경로 얻기
        File file = resource.getFile();
        String directoryPath = file.getAbsolutePath();
        //생성할 파일의 경로를 지정.
        String newFilePath = directoryPath + File.separator + "detect.json";
		ObjectMapper objectMapper= new ObjectMapper();
		Map bodyMap=objectMapper.readValue(new File(newFilePath), Map.class);
		System.out.println("json파일 읽기:"+bodyMap.get("requests"));
		*/
		
		//3.요청 헤더정보등을 담은 HttpEntity객체 생성		
		//DTO혹은 Map에는 요청시 서버에 보낼 데이타를 담는다.
		//※데이타가 Key=Value쌍(application/x-www-form-urlencoded)일때는 반드시 MultiValueMap 사용
		//  데이타가 JSON일때는 (application/json)일때는 MultiValueMap 혹은 Map 사용
		//HttpEntity<DTO혹은 Map> entity = new HttpEntity(DTO혹은 Map객체,headers);
		HttpEntity entity = new HttpEntity(requestBody,headers);
		
		//4.RestTemplate으로 요청 보내기
		//String url="한글이 포한된 요청URI";
		//요청 URL에 한글 포함시는 UriComponents로 객체 생성후 사용시는 uri.toString()해서 사용한다
		//UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();
		String url = "https://vision.googleapis.com/v1/images:annotate";
		
		//외부 OPEN API(구글)서버로부터 받은 데이터 타입이
		//{}인 경우 Map 혹은 DTO
		//[{},{},...]인 경우 List<Map 혹은 DTO>
		ResponseEntity<Map> responseEntity = restTemplate.exchange(
													url, //요청 URL 
													HttpMethod.POST, //요청 메소드
													entity, //HttpEntity(요청바디와 요청헤더 포함)
													Map.class //응답 데이터가 {}일 때
													//DTO계열.class// {}일 때
													//List.class//[{}...]일 때
													);
		
		System.out.println("응답코드:"+responseEntity.getStatusCodeValue());
		System.out.println("응답헤더:"+responseEntity.getHeaders());
		System.out.println("응답바디:"+responseEntity.getBody());
		
		return responseEntity.getBody();//body안에 json있으니까 body줌
		
		
	}
	
	@CrossOrigin
	@PostMapping("/vision/ocr")//사용자가 올린 이미지를 base64로 인코딩,  base64로 인코딩된건 위의 uri로도 못가져옴
	public Map ocr(@RequestParam String base64) {
		
		//401에러: 인증 오류시 아래 에러 핸들러 추가
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {//익명클래스 만듦
			
			@Override
			public boolean hasError(ClientHttpResponse response) throws IOException {
				HttpStatus status = response.getStatusCode();//스프링프레임워크꺼 임포트
				return status.series() == HttpStatus.Series.SERVER_ERROR;
			}
		});
		
		//1.요청헤더 설정용 객체 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer ya29.a0AWY7CkkmZBlQId2U-RfK5RxOzDtWGw-W0t7ODP4-ygS50P2VrwcV_I5XpmbpXOxsYaTqjVGupfR_NWgaWSjnWOljhxiT7iTnTFsaI0Xc0yLE36RnivNvXXD3hbGxrBIrac4XgRAfRzKiiMNEqbz8vPvUmkFOLC-eZ5kSLgaCgYKAXkSARASFQG1tDrphKEsX5zIrQ4GL9ATqbDG-w0173");
		headers.add("x-goog-user-project", "braided-gravity-391102");
		headers.add("Content-Type", "application/json; charset=utf-8");
		
		//2.구글의 요청본문(JSON형태)과 동일한 구조의 Map으로 요청바디 설정
		Map<String,List> requestBody = new HashMap<>();//requests는 키, 아래의 배열은 List로 받음
		List value = new Vector<>();//이제 features, image 넣으면 됨, 둘 다 키값으로 안에 [] 배열 저장함
		Map map = new HashMap<>();//이렇게하는거 귀찮으면 요청본문을 .json으로 저장 후 objectMapper로 읽어서 map에 저장하면 더 편함(권장)
		Map imageValue = new HashMap<>();
		imageValue.put("content", base64);//content키로 base64 인코딩된걸 넣어줌
		map.put("image", imageValue);	
		List featureValue = new Vector<>();
		Map featureMap = new HashMap<>();
		featureMap.put("type", "TEXT_DETECTION");
		featureValue.add(featureMap);
		map.put("features", featureValue);
		value.add(map);
		requestBody.put("requests", value);
		
		
		//3.요청 헤더정보등을 담은 HttpEntity객체 생성		
		HttpEntity entity = new HttpEntity(requestBody,headers);
		
		//4.RestTemplate으로 요청 보내기
		//String url="한글이 포한된 요청URI";
		//요청 URL에 한글 포함시는 UriComponents로 객체 생성후 사용시는 uri.toString()해서 사용한다
		//UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();
		String url = "https://vision.googleapis.com/v1/images:annotate";
		
		//외부 OPEN API(구글)서버로부터 받은 데이터 타입이
		//{}인 경우 Map 혹은 DTO
		//[{},{},...]인 경우 List<Map 혹은 DTO>
		ResponseEntity<Map> responseEntity = restTemplate.exchange(
													url, //요청 URL 
													HttpMethod.POST, //요청 메소드
													entity, //HttpEntity(요청바디와 요청헤더 포함)
													Map.class //응답 데이터가 {}일 때
													//DTO계열.class// {}일 때
													//List.class//[{}...]일 때
													);
		
		System.out.println("응답코드:"+responseEntity.getStatusCodeValue());
		System.out.println("응답헤더:"+responseEntity.getHeaders());
		System.out.println("응답바디:"+responseEntity.getBody());
		
		return responseEntity.getBody();
		
		

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
