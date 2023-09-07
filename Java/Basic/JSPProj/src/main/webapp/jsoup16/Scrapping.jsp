<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Vector"%>
<%@page import="org.jsoup.nodes.Element"%>
<%@page import="org.jsoup.select.Elements"%>
<%@page import="org.jsoup.Jsoup"%>
<%@page import="org.jsoup.nodes.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/template/Top.jsp"/>
    <div class="container" style="margin-top:50px">
        <div class="jumbotron bg-info">
            <h1>자바언어로 웹 스크래핑 하기(=크롤링)</h1>            
        </div><!--jumbotron-->
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">https://jsoup.org(주소복붙)</legend>
        	<%
	        	Document doc = Jsoup.connect("https://en.wikipedia.org/").get();//JSOUP의 Document 임포트
	        	out.println("<h3 class='display-4'>"+doc.title()+"</h3>");//브라우저 출력
	        
	        	Elements newsHeadlines = doc.select("#mp-itn b a");
	        	//System.out.println(newsHeadlines);//mp-itn 하위 b 하위 a의 헤드라인들 가져옴
	        	out.println("<ul class='list-unstyled'>");
	        	for (Element headline : newsHeadlines) {
	        	  out.println(String.format("<li><a href='%s'>%s</a></li>", headline.absUrl("href"), headline.attr("title")));
	        	}//앞에 https://en.wikipedia.org 도메인 붙여줌
	        	
        		out.println("</ul>");
        	%>
        </fieldset>     
        <fieldset class="form-group border p-3">
        	<legend class="w-auto px-3">네이버 시리즈온 스크래핑하기(조회순)</legend>
        	<%
        		doc = Jsoup.connect("https://serieson.naver.com/v3/movie/ranking/realtime").get();
        		System.out.println(doc.html());//html소스 쫙 가져옴
	        	//#container > div.RankingPage_ranking_wrap__GB855 > ol > li:nth-child(1) > a > div.Poster_info_area__HGRo_.Poster_margin_left__sLY0L > span
				//#container > div.RankingPage_ranking_wrap__GB855 > ol > li:nth-child(2) > a > div.Poster_info_area__HGRo_.Poster_margin_left__sLY0L > span
        		//규칙 찾기: 구조적으로 보면 ol > li:~~ > a 니까 몇번째 차일드만 지우면 됨, 그러면 영화순위 100개 제목만 다 가져옴
        		
        		Elements lies= doc.select("#container > div.RankingPage_ranking_wrap__GB855 > ol > li");
        		out.println("<ul class=\"list-unstyled\">");
        		int rank=1;
        		for(Element li:lies){
        			String href=li.firstChild().absUrl("href");        			
        			//out.println(li.firstElementChild().firstElementChild().firstElementChild().attr("style")+"<br/>");
        			//out.println(li.firstElementChild().firstElementChild().firstElementChild().html()+"<br/>");//svg로 이미지 소스를 만든다.소스보기시 확인가능
        			String title=li.firstElementChild().firstElementChild().firstElementChild().child(1).child(0).attr("alt");
        			out.println("<li>"+String.format("<a href='%s'><span class='badge badge-danger'>%s</span> %s</a>",href,rank++,title)+"</li>");        		
        		}
        		out.println("</ul>");
        	%>
        	
        
         </fieldset>     
    </div><!--container-->
<jsp:include page="/template/Footer.jsp"/>