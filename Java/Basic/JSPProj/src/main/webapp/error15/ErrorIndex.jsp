<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>웹 어플리케이션 예외처리</h1><!-- 프로젝트 완료 후 사용자에게 보여주기 전에 빨간줄 가면 안되니까 처리 -->    
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">web.xml설정을 통한 예외처리</legend><!-- try~catch말고 -->
        	<%
        		//방법1]try~catch사용
				/*
				try{
	        		out.println("HTTP메소드:"+request.getMethod()); //GET
	        		out.println("파라미터의 문자열 길이:"+request.getParameter("name").length());
	        		//최초 파라미터 없으니 name이 null임, NullPointException, 쿼리스트링 전달시 해결가능
				}
        		catch(NullPointerException e){
        			out.println("<h1 class='display-4'>관리자에게 문의하세요</h1>");
        			return;
        		}*/
        		//방법2]page지시어 속성 사용(위의 <%@ 안에 errorpage 설정)

	        	//방법3]web.xml의 설정으로 예외 처리- 전역설정으로 페이지마다 설정 불필요
				//     즉 web.xml에 설정만 하면 모든 .jsp혹은 서블릿에서 적용된다.
				/*
		       		-개발완료후 범용적인 에러 처리시
		        	 방법1:에러코드로(ex.서버측에러 500)
		         	 방법2:예외 클래스 직접 지정(ex.nullpoint 등 다양한 에러가 500에 해당, 이게 더 구체적이므로 2개 동시 설정시 이게 우선함)
		         	 예외 클래스 직접 지정이 우선한다.
		        
		       		location:컨텍스트 루트를 제외한 /로 시작하는 웹상의 경로
		       		try~catch불필요
		       		포워드로 에러 처리 페이지로 이동시킨다
		       		
		       		ex) web.xml에 아래 설정
		       		<error-page>
		       	  	<error-code>500</error-code>
		       	  	<location>/error15/ErrorCode.jsp</location><!-- 모든 JSPProj에서 500에러시 여기로 포워드됨 -->
		       	  	</error-page>
		       		
	   			*/
				request.setAttribute("reqVar", "리퀘스트 영역입니다");
        		out.println("HTTP메소드:"+request.getMethod()); //GET
        		out.println("파라미터의 문자열 길이:"+request.getParameter("name").length());
        	
        	%>
        </fieldset>        
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>