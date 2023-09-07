<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!-- top -->
<tiles:insertAttribute name="TOP"/><!-- layouts.xml에 설정한 이름 TOP그대로 세팅해야함 Top.jsp가 여기에 들어오는 것 -->
<!-- body -->
<tiles:insertAttribute name="body"/>
<!-- footer -->
<tiles:insertAttribute name="footer"/>

