package model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTOkens {
	
	/**
	  비밀키를 .properties파일에서 읽어와서 반환하는 메소드
	  @param keyPath  비밀키가 기록된 .properties파일의 경로(단,확장자 생략)
	  @param key      .properties파일의 주어진 키값. 예: 키 = 비밀키 일때의 키값
	  @return         비밀키 반환(tokens.properties의 !@#$%^&*) -> 비밀키를 확인해야 알맞은 토큰인지 확인하는 것
	 */
	
	private static String getSecretKey(String keyPath,String key) {
		ResourceBundle resource=ResourceBundle.getBundle(keyPath);
		String secretkey = resource.getString(key);
		return secretkey;
	}
	
	/**
	 * JWT토큰을 생성해서 반환하는 메소드
	 * @param username 사용자 아이디
	 * @param keyPath 비밀키가 기록된 .properties파일의 경로(단,확장자 생략)
	 * @param key .properties파일의 주어진 키값. 예: 키 = 비밀키 일때의 키값
	 * @param payloads 추가로 사용자 정보를 저장하기 위한 Claims(아래 Map으로 한 이유는 Claims가 사실 Map이기 때문, Map 대신 Claims해도 됨)
	 * @param expirationTime 토큰 만료 시간(15분에서 몇 시간이 적당).단위는 천분의 1초
	 * @return
	 */
	public static String createToken(String username,String keyPath,String key,Map<String, Object> payloads,long expirationTime) {
		//비밀키 가져오기
		String secretKey = getSecretKey(keyPath, key);		
		//JWT 토큰의 만료 시간 설정
		long currentTimeMillis = System.currentTimeMillis();//토큰의 생성시간, 현재 시간을 밀리세컨단위로 가져옴
		expirationTime = currentTimeMillis + expirationTime; //토큰의 만료시간		
		
		//Header 부분 설정
		Map<String, Object> headers = new HashMap<>();
		headers.put("typ", "JWT");
		headers.put("alg", "HS256");	
		
		JwtBuilder builder = Jwts.builder()//Jwts가 빌더패턴임, 헤더/페이로드 등 설정함
				.setHeader(headers)// Headers 설정
				.setClaims(payloads)// Claims 설정(기타 페이로드, 여기부터 Expiration까지 페이로드 설정하는 것)
				.setSubject(username)//사용자 ID 설정
				.setIssuedAt(new Date(currentTimeMillis))//생성 시간 설정(토큰 발행시간, 그냥 new Date()만 써도 됨)
				.setExpiration(new Date(expirationTime))//만료 시간 설정(필수로 설정하자.왜냐하면 토큰(문자열이라)은 세션처럼 제어가 안된다)
				.signWith(SignatureAlgorithm.HS256,secretKey.getBytes());//비밀 키로 JWT를 서명, 시크릿키와 암호화 알고리즘으로 헤더, 페이로드를 암호화
		
		//JWT 생성
		String jwt = builder.compact();//이걸해야 토큰이 만들어지는 것, String반환	
		return jwt;
	}
	/**
	 * 발급한 토큰의 payloads부분을 반환하는 메소드
	 * @param token  발급토큰
	 * @param keyPath 비밀키가 기록된 .properties파일의 경로(단,확장자 생략)
	 * @param key .properties파일의 주어진 키값. 예: 키 = 비밀키 일때의 키값
	 * @return 토큰의 payloads부분 반환
	 */
	public static Map<String, Object> getTokenPayloads(String token,String keyPath,String key) {
		
		//비밀키 가져오기
		String secretKey = getSecretKey(keyPath, key);
		
		Map<String, Object> claims = new HashMap<>();
		try {
			//JWT토큰 파싱 및 검증
			claims = Jwts.parser()
					.setSigningKey(secretKey.getBytes("UTF-8"))//서명한 비밀키 설정, .properties의 비밀키
					.parseClaimsJws(token)//토큰 분석, 주어진 JWT토큰과 비밀 키를 사용하여 JWT토큰을 검증하는 메소드로 실패시 예외를 발생시킨다
					.getBody();				
			return claims;
		} 
		catch (ExpiredJwtException e) { // 토큰이 만료되었을 경우
			claims.put("invalid","Validity period has expired");			
		}
		catch (Exception e) {
			//검증에 실패하면 즉 유효하지 않은 토큰 false 반환
			claims.put("invalid","Invalid Token");		
		}
		return claims;
	}/////////////////////////////////

	
	/**
	 * 유효한 토큰인지 검증하는 메소드
	 * @param token  발급토큰
	 * @param keyPath 비밀키가 기록된 .properties파일의 경로(단,확장자 생략)
	 * @param key .properties파일의 주어진 키값. 예: 키 = 비밀키 일때의 키값
	 * @return 유효한 토큰이면 true,만료가됬거나 변조된 토큰인 경우 false반환
	 */
	public static boolean verifyToken(String token,String keyPath,String key) {
		
		//비밀키 가져오기
		String secretKey = getSecretKey(keyPath, key);		
		
		try {
			//JWT토큰 파싱 및 검증
			Jws<Claims> claims = Jwts.parser()
					.setSigningKey(secretKey.getBytes("UTF-8"))//서명한 비밀키 설정
					.parseClaimsJws(token);//주어진 JWT토큰과 비밀 키를 사용하여 JWT토큰을 검증하는 메소드로 실패시 예외를 발생시킨다
			//토큰의 유효성과 만료일자 확인
			return !claims.getBody().getExpiration().before(new Date());//현재시간과 만료시간 비교, 만료됐으면 false
		} 	
		catch (Exception e) {
			//e.printStackTrace(); 
			//위 코드 주석처리시 java.lang.IllegalArgumentException: JWT String argument cannot be null or empty. 에러 안뜸
			return false;
		}
	}/////////////////////////////////
	/**
	 * 문자열인 발급된 토큰을 요청헤더의 쿠키에서 읽어오는 메소드
	 * @param request 요청헤더에서 쿠키를 읽어오기 위한 HttpServletRequest객체
	 * @param cookieName 토큰 발급시 설정한 쿠키명
	 * @return 발급된 토큰
	 */
	public static String getToken(HttpServletRequest request,String cookieName) {
		//발급한 토큰 가져오기
		Cookie[] cookies=request.getCookies();
		String token="";
		if(cookies !=null){
			for(Cookie cookie:cookies){
				if(cookie.getName().equals(cookieName)){
					token= cookie.getValue();
				}
			}
		}
		return token;
	}///////////
	/**
	 * 토큰을 삭제하는 메소드
	 * @param request HttpServletRequest객체
	 * @param response HttpServletRequest객체
	 */
	public static void removeToken(HttpServletRequest request,HttpServletResponse response) {
		Cookie cookie = new Cookie(request.getServletContext().getInitParameter("COOKIE-NAME"),"");
		cookie.setPath(request.getContextPath());
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		//로그아웃 처리후 로그인 페이지로 이동]
		//response.sendRedirect("LoginBasedToken.jsp");
	}////////////////////////////	
	//토큰 만료시간 연장메소드
	//expiration:현재시간+연장시간
	public static String setTokenExpiration(String jwt,String keyPath,String key, Date expiration) {
		
		//비밀키 가져오기
		String secretKey = getSecretKey(keyPath, key);
		
		String newToken=null;
		try{
			Claims claims = Jwts.parser()
	                .setSigningKey(secretKey.getBytes("UTF-8"))
	                .parseClaimsJws(jwt).getBody();	
			
	        claims.setExpiration(expiration);
	
	        newToken = Jwts.builder()
	                .setClaims(claims)
	                .signWith(SignatureAlgorithm.HS256,secretKey.getBytes("UTF-8"))
	                .compact();
		}
		catch(Exception e){
			e.printStackTrace();
		}
        return newToken;
    }



}
