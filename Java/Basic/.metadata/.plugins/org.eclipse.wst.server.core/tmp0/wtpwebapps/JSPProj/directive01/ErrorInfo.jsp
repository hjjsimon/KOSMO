<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isErrorPage="true"%>
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
    <script src="https://kit.fontawesome.com/16e1907a6b.js" crossorigin="anonymous"></script>
    <title>ErrorInfo.jsp</title>
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
        	<legend class="w-auto px-3">에러발생</legend>
        	<h2>관리자에게 문의하세요: <i class="fa-solid fa-phone"></i>010-1234-5678</h2>
        	<h3 class="text-danger">에러내용</h3>
        	<!-- JSP의 exception내장객체는 page지시어의 isErrorPage의 속성이 true인 jsp페이지에서만 사용가능 -->
        	<%--=exception.getMessage() --%><!-- 빨간줄은 서블릿 변환불가, isErrorPage="true" 추가해줘야 에러해결-->
        	<h4>숫자만 입력하세요</h4>
        </fieldset>
        
    </div><!--container-->
</body>
</html>