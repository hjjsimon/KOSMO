<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- CookieExamCartEmpty.jsp -->
<%
	//[장바구니 비우기-쿠키삭제]
	//요청헤더에서 쿠키값 읽기
	Cookie[] cookies = request.getCookies();
		
	if(cookies != null){//쿠키는 꼭 null체크
		for(Cookie cookie : cookies){
			//쿠키명 얻기(상품명)
			String name = cookie.getName();
			//쿠키값 얻기(상품코드)
			String value = cookie.getValue();
			if(value.toUpperCase().startsWith("PC_FR")){
				//쿠키 삭제처리]
				//1]동일한 쿠키명으로 쿠키생성, 쿠키값은 빈 문자열로
				Cookie cook = new Cookie(name, "");
				cook.setPath(request.getContextPath());//새로운 쿠키를 만들어서 응답할 때 씀, 이거 필수임, 요청헤더명에 사라짐
				//2]유효기간은 0이나 -값으로 설정
				cook.setMaxAge(0);
				//3]다시 응답헤더에 추가
				response.addCookie(cook);
			}
		}
	}
	//상품 목록페이지로 이동
	response.sendRedirect("CookieExamIndex.jsp");

%>


