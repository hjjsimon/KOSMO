<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    errorPage="ErrorInfo.jsp"
    %><!-- 에러 발생시 위 페이지로 이동 -->
<% 
	//사용자가 입력한 값 받기]
	String stringAge=request.getParameter("age");//파라미터=name 담음
	//out.println("stringAge:"+stringAge);//stringAge:null, get방식이므로 확인눌러야 서버에 전달되어 null이 아니게됨, 최초에 값전달 안돼서 null임
	int after10Years = -1;
	String errorMessage = "";
	if(stringAge != null){//사용자가 버튼을 눌렀으면
		//방법1] try~ catch로 직접 예외처리(HTTP 상태 500 – 내부 서버 오류 해결하기)
		/*
		try{
			after10Years = Integer.parseInt(stringAge) + 10;
		}
		catch(NumberFormatException e){//숫자형식이 아닐 때 에러메세지 표시
			errorMessage="나이는 숫자만 입력하세요";
		}*/
		//방법2] page지시어의 errorPage속성사용(try~ catch불필요)
		after10Years = Integer.parseInt(stringAge) + 10;
	}
	
%>
    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <title>ErrorIndex.jsp</title>
    <style>
        /*점보트론 세로폭 및 마진바텀 줄이기*/
        .jumbotron{
            padding-top: 1rem;
            padding-bottom: 1rem;
            margin-bottom: .5rem;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="jumbotron bg-info">
            <h1>errorPage 및 isErrorPage속성</h1>
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">에러를 발생시킬 페이지</legend>
        	<form><!-- action지정 안하면 서버에 현재페이지 재요청, 메소드 안쓰면 get방식(현재페이지 요청) -->
				<div class="form-group">
					<label for="age">나이를 입력하세요?</label><!-- 텍스트 눌러도 커서이동하도록 for 준것 -->
					<input class="form-control" type="text" name="age" id="age"/>
				</div>
				<input class="btn btn-success" type="submit" value="확인"/>
			</form>
			<% if(after10Years != -1){ %><!-- 중괄호 필수 -->
			당신의 10년 후 나이는 <%=after10Years %>살 이군요
			<%} %>
			<% if(errorMessage.length() != 0) { %><!-- 에러메시지가 빈문자열이 아니면 출력 -->
			<div class="alert alert-warning mt-2 ">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
	 			<strong>Not Number!</strong><%=errorMessage %>
	 		</div>
	 		<%} %>
        </fieldset>
        
    </div><!--container-->
</body>
</html>