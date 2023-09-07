<%@page import="java.util.Arrays"%>
<%@page import="java.io.IOException"%>
<%@page import="java.lang.reflect.Array"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--!
	//서버측에서도 유효성 체크(get방식은 정보노출, post방식으로 해야함)
	private boolean isValidate(JspWriter out, String param, String message){
		if(param==null || param.trim().length()==0){
			try{
				out.println("<script>");
				out.println("alert('"+message+"');");
				out.println("history.back();");
				out.println("</script>");
			}
			catch(IOException e){}
			return false;//에러난것
		}
		return true;//정상적으로 입력 시 true
	}
	private boolean isValidate(JspWriter out, String[] param, String message, int count){
		if(param == null || param.length < count){//체크박스 선택x 시 null 또는 선택한 값이 필수선택항목개수보다 작으면
			try{
				out.println("<script>");
				if(param != null) message = count +"개 이상 선택하세요";//선택은 함
				out.println("alert('"+message+"');");
				out.println("history.back();");
				out.println("</script>");
			}
			catch(IOException e){}
			return false;//에러난것
		}
		return true;
	}
--%>
<!-- 절대경로(/또는 http로 시작): 컨텍스트 루트(/JSPProj) 자동으로 붙으니 제외 -->
<%@ include file="/common/Validate.jsp" %>
<%	
	//POST방식일 때 한글처리 - 현재 톰캣버전(10)은 인코딩 불필요(원래 깨짐, 10버전 안깨짐)
	//request.setCharacterEncoding("UTF-8");//톰캣 9버전 이하는 필수(10이후 없어도 됨)
	if(!"POST".equals(request.getMethod())){//요청의 메소드를 가져옴, 포스트 방식이 아니면 아래 실행 안되도록함 
		out.println("<script>");
		out.println("alert('잘못된 접근입니다');");
		out.println("history.back();");//이전페이지로 되돌림, 이전 입력값은 안사라지는게 특징(비밀번호 입력값은 사라짐, 이건 어쩔 수 없음)
		out.println("</script>");
		return;//public void _jspService()메소드 빠져나가기
	}
	//파라미터 받기
	String id = request.getParameter("id");
	if(!isValidate(out,id,"아이디를 입력하세요")) return;//false반환시 바로 return, public void _jspService()메소드 빠져나가기
	String pwd = request.getParameter("pwd");
	if(!isValidate(out,pwd,"비밀번호를 입력하세요")) return;
	String gender = request.getParameter("gender");
	if(!isValidate(out,gender,"성별을 선택하세요")) return;
	String[] inter = request.getParameterValues("inter");
	if(!isValidate(out, inter, "관심사항을 선택하세요", 2));//2개 이상 선택하도록 함
	String grade = request.getParameter("grade");
	if(!isValidate(out,grade,"학력을 선택하세요")) return;
	String file = request.getParameter("file");
	if(!isValidate(out,file,"파일을 첨부하세요")) return;
	String self = request.getParameter("self");
	if(!isValidate(out,self,"자기소개를 입력하세요")) return;
	
%>    
    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/bootstrap/css/bootstrap.min.css">
    <script src="<%=request.getContextPath() %>/bootstrap/js/jquery@3.6.4/dist/jquery.slim.min.js"></script>
    <script src="<%=request.getContextPath() %>/bootstrap/js/popper.min.js"></script>
    <script src="<%=request.getContextPath() %>/bootstrap/js/bootstrap.bundle.min.js"></script> 
    
    <script src="https://kit.fontawesome.com/16e1907a6b.js" crossorigin="anonymous"></script>
    <title>RequestForExampleComplete.jsp</title>
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
	<!-- 네비게이션바 -->
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
        <a class="navbar-brand" href="#"><i class="fa-solid fa-house-chimney"></i></a>
		<span class="navbar-text">모두를 위한 플랫폼</span>        
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="collapsibleNavbar">
            <ul class="navbar-nav mr-3">
                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">Dropdown link</a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">Link 1</a>
                        <a class="dropdown-item" href="#">Link 2</a>
                        <a class="dropdown-item" href="#">Link 3</a>
                    </div>
                </li>
                <li class="nav-item">
                    <form class="form-inline" action="#">
                        <input class="form-control mr-sm-2" type="text" placeholder="검색어 입력">
                        <button class="btn btn-success" type="submit">검색</button>
                    </form>
                </li>
            </ul>
        </div>
    </nav>
    <div class="container" style="margin-top:50px;">
        <div class="jumbotron bg-info">
            <h1>파라미터 받기 결과</h1>
        </div><!--jumbotron-->
        <form>
			<div class="form-group">
				<label><kbd class="lead">아이디</kbd></label>
				<input type="text" class="form-control" name="id" placeholder="아이디를 입력하세요" value="<%=id%>">
			</div>
			<div class="form-group">
				<label><kbd class="lead">비밀번호</kbd></label>
				<input  type="text" class="form-control" name="pwd" placeholder="비밀번호를 입력하세요" value="<%=pwd%>">
			</div>			
			<div class="form-group">
				<label><kbd class="lead">성별</kbd></label>
				<div class="d-flex">
					<div class="custom-control custom-radio mr-2">
						<input type="radio" class="custom-control-input" name="gender" value="man" id="male" 
						<%--= ("man".equals(gender)) ? "checked" : "" --%>
						<%if("MAN".equals(gender.toUpperCase())){ %> checked <%} %>
						>
						<label for="male" class="custom-control-label">남자</label>
					</div>
					<div class="custom-control custom-radio">
						<input type="radio" class="custom-control-input" name="gender" value="woman" id="female" 
						<%--= ("woman".equals(gender)) ? "checked" : "" --%>
						<%if("WOMAN".equals(gender.toUpperCase())){ %> checked <%} %>
						>
						<label for="female"	class="custom-control-label">여자</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label><kbd class="lead">관심사항</kbd></label>
				<div class="d-flex">
					<div class="custom-control custom-checkbox">
						<input type="checkbox" class="custom-control-input" name="inter" value="POL" id="POL" 
						<%if(Arrays.toString(inter).indexOf("POL")!=-1){ %>checked<% } %>>
						<label class="custom-control-label" for="POL">정치</label>
					</div>
					<div class="custom-control custom-checkbox mx-2">
						<input type="checkbox" class="custom-control-input" name="inter" value="ECO" id="ECO" 
						<%if(Arrays.toString(inter).indexOf("ECO")!=-1){ %>checked<% } %>>
						<label class="custom-control-label" for="ECO">경제</label>
					</div>
					<div class="custom-control custom-checkbox">
						<input type="checkbox" class="custom-control-input" name="inter" value="ENT" id="ENT"
						<%if(Arrays.toString(inter).indexOf("ENT")!=-1){ %>checked<% } %>>
						<label class="custom-control-label" for="ENT">연예</label>
					</div>
					<div class="custom-control custom-checkbox ml-2">
						<input type="checkbox" class="custom-control-input" name="inter" value="SPO" id="SPO" 
						<%if(Arrays.toString(inter).indexOf("SPO")!=-1){ %>checked<% } %>>
						<label class="custom-control-label" for="SPO">스포츠</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label><kbd class="lead">학력사항</kbd></label>
				<select name="grade" class="custom-select mt-3 custom-select-lg">					
					<option value="ELE" <%--= ("ELE".equals(grade)) ? "selected='selected'" : "" --%>
					<%if(grade.startsWith("E")){ %>selected<%} %>>초등학교</option>
					<option value="MID" <%= ("MID".equals(grade)) ? "selected='selected'" : "" %>>중학교</option>
					<option value="HIG" <%= ("HIG".equals(grade)) ? "selected='selected'" : "" %>>고등학교</option>
					<option value="UNI" <%= ("UNI".equals(grade)) ? "selected='selected'" : "" %>>대학교</option>
				</select>
			</div>
			<div class="form-group">
				<label><kbd class="lead">첨부파일</kbd></label>
				<div class="custom-file">
					<input type="file" class="custom-file-input" name="file" id="file">
					<label	class="custom-file-label" for="file"><%=file %></label>
					<!-- label, textarea는 태그 사이에 넣기, value 안됨! -->
				</div>
			</div>
			<div class="form-group">
				<label><kbd class="lead">자기소개</kbd></label>
				<textarea class="form-control" rows="5" name="self"><%=self %></textarea>
			</div>
		</form>
    </div><!--container-->
</body>
</html>