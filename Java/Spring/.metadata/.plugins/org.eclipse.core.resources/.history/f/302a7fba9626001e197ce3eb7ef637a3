<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="m" uri="/WEB-INF/tlds/common.tld" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>    
    <script src="https://kit.fontawesome.com/dedb6fdace.js" crossorigin="anonymous"></script>

    <!-- fontaswem-->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>    
  	
    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="<c:url value="/resources/tsfolder/lib/animate/animate.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/tsfolder/lib/owlcarousel/assets/owl.carousel.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/tsfolder/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css"/>" rel="stylesheet" />

    <!-- Customized Bootstrap Stylesheet  누나꺼랑 겹침
    <link href="/resources/tsfolder/css/bootstrap.min.css" rel="stylesheet">
     -->

    <!-- Template Stylesheet -->
    <link href="<c:url value="/resources/tsfolder/css/style.css"/>" rel="stylesheet">
	
     <!-- Template Javascript -->
     <script src="<c:url value="/resources/tsfolder/js/main.js"/>" ></script>
     
     <!-- googlefont -->
	<link rel="preconnect" href="https://fonts.googleapis.com" crossorigin> 
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2? family=Hahmlet&family=Nanum+Myeongjo&family=Stylish&display=swap" rel="stylesheet">
	<title>TemplateApplyingModule.jsp</title>
	<style>
        .bs-canvas-overlay {
            opacity: 0.85;
            z-index: 1100;
        }
        .bs-canvas {
            top: 0;
            z-index: 1110;
            overflow-x: hidden;
            overflow-y: auto;
            width: 330px;
            transition: margin .4s ease-out;
            -webkit-transition: margin .4s ease-out;
            -moz-transition: margin .4s ease-out;
            -ms-transition: margin .4s ease-out;
        }
        .bs-canvas-left {
            left: 0;
            margin-left: -330px;
        }
        .bs-canvas-right {
            right: 0;
            margin-right: -330px;
        }
        .bg-gra{
            background:white;
            z-index: 500;
            box-shadow: rgba(101, 101, 101, 0.3) 2px 2px 4px 4px;
        }
        .bg-gra1{
		    background: #FDCDBC;
            z-index: 500;
        }
        .text1{
            font-size: 3rem;
        }
        .text2{
        	font-size: 1.25rem;
        }
        /* 버튼 애니메이션 */
        .custom-btn {
          display:flex;
          align-items:center;
          justify-content:center;
		  width: 130px;
		  height: 40px;
		  border-radius: 5px;
		  font-size: 20px;
		  font-weight: bold;
		  background: transparent;
		  cursor: pointer;
		  transition: all 0.3s ease;
		  position: relative;
		  outline: none;
		}
		.btn-14 {
		  background: rgb(255,151,0);
		  border: none;
		  z-index: 1;
		}
		.btn-14:after {
		  position: absolute;
		  content: "";
		  width: 100%;
		  height: 0;
		  top: 0;
		  left: 0;
		  z-index: -1;
		  border-radius: 5px;
		  background-color: #ff771c;
		}
		.btn-14:hover {
		  color: black;
		}
		.btn-14:hover:after {
		  top: auto;
		  bottom: 0;
		  height: 100%;
		}
		.sidemenu a{
			display: block;
		    height: 60px;
		    line-height: 60px;
		    width: 100%;
		    position: relative;
		    margin-left: 17px;
		    color:black;
		    /*border-bottom: 3px dashed rgb(101, 101, 101, 0.3);*/
		}
		.sidemenu a:hover {
			cursor: pointer;
			color:blue;
			font-weight: bold;
			background-color: transparent;

		}
		.firstdown a {
			display:block;
			text-decoration:none
		}
		.firstdown a:hover {
			cursor: pointer;
			text-decoration:none;
		}
		/* 사이드 버튼 아코디언 형태 */
.accordion {
    background: #eee;

    width: 100%;
}


.accordion .section {
    border-bottom: 2px dashed #ccc;
    padding: 10px 10px;
    background: #fff;
}

.section div a {
	font-size: 17px;
	color:gray;
	line-height:10px;
	margin: 0px 0px 10px 20px;
}

.section h3 a {
    display: flex;
    font-weight: normal;
	padding: 0.7em 0px;
    text-decoration: none;
    color:black;
    font-size:22px;
    font-weight: bold;
}

.accordion h3 a:hover {
    text-decoration: none;
}

.accordion h3 + div {
    height: 0;
    overflow: hidden;
    -webkit-transition: height 0.3s ease-in;
    -moz-transition: height 0.3s ease-in;
    -o-transition: height 0.3s ease-in;
    -ms-transition: height 0.3s ease-in;
    transition: height 0.3s ease-in;
}

.accordion :target h3 a {
    text-decoration: none;
    font-weight: bold;
}

.accordion :target h3 + div {
    height: 140px;
}

.accordion .section.large:target h3 + div {
    overflow: auto;
}
.arrow-down {
			width: 0;
	    	height: 0;
			border-left: 7px solid transparent;
			border-right: 7px solid transparent;
			border-top: 7px solid black;
		}
.ditem {
	display:flex;
	flex-direction: column;
	justify-content: space-between;
	line-height: 30px;
	margin: 0px;
}
.ditem a {
	align-items:center;
	margin: 10px;
	padding: 3.5px;
	text-decoration: none;
}
    </style>
</head>
<script>
	//비디오 전체화면
	
	/*
	//쿠키값 가져오는 함수
	function getCookieValue(cookieName) {
		//debugger
		var cookies = document.cookie.split("; ");
	  	for (var i = 0; i < cookies.length; i++) {
	    	var cookie = cookies[i].split("=");
	    	var name = cookie[0];
	    	var value = cookie[1];
	    	if (name === cookieName) {
	      		return value;
	    	}
	  	}
	  	return "";
	}
	
	document.addEventListener("DOMContentLoaded", function() {
		//쿠키값 가져와서 로그인 상태 확인 함수
		function checkLoginStatus() {
		var token = getCookieValue("User-Token");
		//console.log("token",token);
		if (token) {
            // 토큰이 존재하면 로그인 상태로 간주
            // 모든 .logout 요소에 스타일 적용
            var logoutElements = document.querySelectorAll(".logout");
            for (var i = 0; i < logoutElements.length; i++) {
                logoutElements[i].style.display = "";
            }
            // 모든 .login 요소에 스타일 적용
            var loginElements = document.querySelectorAll(".login");
            for (var j = 0; j < loginElements.length; j++) {
                loginElements[j].style.display = "none";
            }
        } else {
            // 토큰이 존재하지 않으면 로그아웃 상태로 간주
            // 모든 .logout 요소에 스타일 적용
            var logoutElements = document.querySelectorAll(".logout");
            for (var i = 0; i < logoutElements.length; i++) {
                logoutElements[i].style.display = "none";
            }
            // 모든 .login 요소에 스타일 적용
            var loginElements = document.querySelectorAll(".login");
            for (var j = 0; j < loginElements.length; j++) {
                loginElements[j].style.display = "";
            }
        }
    }
		// 페이지 로딩 시 로그인 상태 확인
		checkLoginStatus();
	});
	*/
	
	//햄버거 버튼 클릭시 좌측에 메뉴바 열기/닫기
	jQuery(document).ready(function ($) {
        $(document).on('click', '.pull-bs-canvas-left', function () {
            $('body').prepend('<div class="bs-canvas-overlay bg-dark position-fixed w-100 h-100"></div>');
            if ($(this).hasClass('pull-bs-canvas-left'))
                $('.bs-canvas-left').addClass('ml-0');
            return false;
        });

        $(document).on('click', '.bs-canvas-close, .bs-canvas-overlay', function () {
            var elm = $(this).hasClass('bs-canvas-close') ? $(this).closest('.bs-canvas') : $('.bs-canvas');
            elm.removeClass('mr-0 ml-0');
            $('.bs-canvas-overlay').remove();
            return false;
        });
        
        //페이지의 값이 스크롤을 내리지 않으면 메뉴바 색상부여
		//스크롤을 내리면 값에 변화에 따라 배경색 주기
        $(window).scroll(function () {
            var $this = $(window);

            if ($this.scrollTop() > 800) {
                $('#navbar').addClass('bg-gra');
            } else {
                $('#navbar').removeClass('bg-gra');
            }
        });
    });
	/*
	document.addEventListener("DOMContentLoaded", function() {
	    var dropdownItems = document.querySelectorAll(".dropdown-item2");
	    dropdownItems.forEach(function(item) {
	      var url = item.getAttribute("data-url2");
	      item.addEventListener("click", function(event) {
	        event.preventDefault();
	        window.location.href = url;
	      });
	    });
	  });
	
	document.addEventListener("DOMContentLoaded", function() {
	    var dropdownItems = document.querySelectorAll(".dropdown-item1");
	    dropdownItems.forEach(function(item) {
	      var url = item.getAttribute("data-url1");
	      item.addEventListener("click", function(event) {
	        event.preventDefault();
	        window.location.href = url;
	      });
	    });
	  });*/


</script>
<body>
<div class="container-fluid position-relative p-0" style="width:100%;">
	 <nav class="navbar navbar-expand-sm navbar-dark fixed-top justify-content-between" style="padding:0px 0px;" id="navbar">
        <div class="firstdown p-0 mx-4">
            <a class="pull-bs-canvas-left mr-5 d-flex">
                <span style="font-weight:bold; font-size:50px; color:#EF605D">&#9776;</span>
            </a>
        </div>
         
        <a class="navbar-brand" style="position: absolute; margin-left: 44%;" href="<c:url value='/' />">
        	<img src="<c:url value='/resources/images/mainicon.png' />" width="40" height="40">
        	<img src="<c:url value='/resources/images/maintitle.png' />" width="160" height="40">
        </a>

        <ul class="navbar-nav mx-5 " >
       		<div class="d-inline-flex align-items-center" style="height: 45px">
            	<a class="btn btn-sm btn-outline-dark btn-sm-square rounded-circle mx-2" href="<c:url value="/test1.do"/>">1</a>
                <a class="btn btn-sm btn-outline-dark btn-sm-square rounded-circle mx-2" href="#">2</a>
                <a class="btn btn-sm btn-outline-dark btn-sm-square rounded-circle mx-2" href="#">3</a>
                <a class="btn btn-sm btn-outline-dark btn-sm-square rounded-circle mx-2" href="#">4</a>
                <a class="btn btn-sm btn-outline-dark btn-sm-square rounded-circle mx-2" href="<c:url value="/AdminMain.do"/>">관</a>
            </div>
            
            <!-- 유효한 토큰인지 판단하는 함수 -->
            <c:set var="token" value="${cookie['User-Token'].value }"/>
            <%--
            <fmt:bundle basename="config.tokens">
				<fmt:message key="secret-key" var="key"/>
            </fmt:bundle>
            <c:set var="isValid" value='${m:isValid(token,key,pageContext.request,pageContext.response) }' />
 				--%>
            
            <c:if test="${not empty token }" var="isLogin">
	            <li class="nav-item text2 mx-2">
	                <a class="nav-link custom-btn btn-14 text-center text-light" href="<c:url value="/project/MyPage.do"/>">마이 페이지</a>
	            </li>
	            <li class="nav-item text2 mx-2">
	                <a class="nav-link custom-btn btn-14 text-center text-light" href="<c:url value="/project/Logout.do"/>">로그아웃</a>
	            </li>
            </c:if>
            
            <c:if test="${not isLogin }">
	            <li class="nav-item text2 mx-2" >
	                <a class="nav-link custom-btn btn-14 text-center text-light" href="<c:url value="/project/Login.do"/>">로그인</a>
	            </li>
	            <li class="nav-item text2 mx-2">
	                <a class="nav-link custom-btn btn-14 text-center text-light" href="<c:url value="/project/Join.do"/>">회원가입</a>
	            </li>
            </c:if>
        </ul>
    </nav>
    <!-- navbar left side -->
    <div class="bs-canvas bs-canvas-left position-fixed h-100 justify-content-start" style="z-index:99999; background: linear-gradient(to bottom, #ffffff, #FFFAFA);">
        <header class="bs-canvas-header p-4 bg-gra1 justify-content-center align-items-center">
        	<h4 class="d-inline-block mb-0 text1">
        		<img src="<c:url value='/resources/images/mainicon.png' />" width="30" height="35">
        		<img src="<c:url value='/resources/images/maintitle.png' />" width="140" height="35">
        	</h4>
            <button type="button" class="bs-canvas-close close mt-2" aria-label="Close">
                <span aria-hidden="true" class="text-dark text1">&times;</span>
            </button>
        </header>

        <div class="bs-canvas-content px-3 pb-5 ">
            <div class="accordion">
             <div class="section" >
		          <h3>
		            <a class="text-decoration-none font-weight-bold" href="<c:url value="/"/>">
		            <img src="<c:url value="/resources/images/tsimages/free-icon-home-3771140.png"/>" style="opacity: 0.7;" width="25" height="25">
		         
		             &nbsp; 홈으로 가기
		            
		            </a>
		          </h3>
	        </div>
	        
	        <div id="one" class="section" active:false>
	          <h3 class="d-flex">
	            <a href="#one" style="align-items: center;">
	                <img src="<c:url value="/resources/images/tsimages/free-icon-vitamins-525955.png"/>" width="25" height="25">
	                &nbsp; 영양성분 검색
	                <span class="arrow-down" style="margin-left: 70px;" ></span>
	            </a>
	          </h3>
	          <div class="ditem">
	            <a href="<c:url value="/NutrientSelect.do"/>">인기 영양소</a>
	 
	            <a href="<c:url value="/NutrientSelectVitamin.do"/>">비타민</a>
	   
	            <a href="<c:url value="/NutrientSelectMineral.do"/>">미네랄 및 기타영양소</a>
	         
	            <a href="<c:url value="/NutrientSelectFunctional.do"/>">기능성 원료</a>
	          </div>
	        </div>
	        <div class="section">
	          <h3>
	            <a class="text-decoration-none"  href="<c:url value="/ranking/selectfood.do"/>">
	                <img src="<c:url value="/resources/images/shape/캡슐.png"/>" style="opacity: 0.8;" width="25" height="25">
	                &nbsp; 영양제 검색
	            </a>
	          </h3>
	        </div>
	        <div id="three" class="section">
	          <h3>
	            <a class="text-decoration-none" href="<c:url value="/magazineindex.do"/>">
	                <img src="<c:url value="/resources/images/tsimages/free-icon-whats-new-5511348.png"/>" width="25" height="25">
	                &nbsp; 메디큐 칼럼
	            </a>
	        </div>
	        <div id="four" class="section large">
	          <h3>
	            <a class="text-decoration-none" href="#">
	                <img src="<c:url value="/resources/images/tsimages/free-icon-data-analysis-3449317.png"/>" width="25" height="25">
	                &nbsp; 분석 및 결과
	            </a>
	          </h3>
	        </div>
	        <div id="five" class="section">
	          <h3>
	            <a class="text-decoration-none" href="<c:url value="/board/List.do" />">
	                <img src="<c:url value="/resources/images/tsimages/free-icon-chat-724715.png"/>" width="25" height="25">
	                &nbsp; 커뮤니티
	            </a>
	          </h3>
	        </div>
	        <div id="six" class="section" active:false>
	          <h3 class="d-flex">
	            <a href="#six" style="align-items: center;">
	                <img src="<c:url value="/resources/images/tsimages/free-icon-lifestyle-4807765.png"/>" width="25" height="25">
	                &nbsp; 정신건강 테스트
	                <span class="arrow-down" style="margin-left: 120px;"></span>
	            </a>
	          </h3>
	          <div class="ditem" style="border: none;">
	              <a href="<c:url value="/project/MentalTest1.do"/>">심리적 스트레스</a>
	      
				  <a href="<c:url value="/project/MentalTest2.do"/>">흡연 유형 평가</a>
			
				  <a href="<c:url value="/project/MentalTest3.do"/>">알코올 의존(AUDIT-K)</a>
				
				  <a href="<c:url value="/project/MentalTest4.do"/>">우울증(CES-D)</a>
	          </div>
	        </div>
	      </div>
        </div>
    </div>
    <!-- navbar left side end-->
</div>

 