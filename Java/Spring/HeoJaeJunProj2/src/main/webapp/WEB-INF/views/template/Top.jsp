<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

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
    <script src="https://kit.fontawesome.com/0b4621b427.js" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
	<script src="https://fonts.googleapis.com/css?family=Open+Sans:600"></script>
    <style>
       body{
		  margin:0;
		  color:#6a6f8c;
		  background:rgba(40,57,101,.9);
		  font:600 16px/18px 'Open Sans',sans-serif;
		}
		*,:after,:before{box-sizing:border-box}
		.clearfix:after,.clearfix:before{content:'';display:table}
		.clearfix:after{clear:both;display:block}
		a{color:inherit;text-decoration:none}
		
		.login-wrap{
		  width:100%;
		  margin:auto;
		  max-width:700px;
		  min-height:875px;
		  position:relative;
		 
		}
		.login-html{
		  width:100%;
		  height:100%;
		  position:absolute;
		  padding:90px 70px 50px 70px;
		  
		}
		.login-html .sign-in-htm,
		.login-html .sign-up-htm{
		  top:0;
		  left:0;
		  right:0;
		  bottom:0;
		  position:absolute;
		  transform:rotateY(180deg);
		  backface-visibility:hidden;
		  transition:all .4s linear;
		}
		.login-html .sign-in,
		.login-html .sign-up,
		.login-form .group .check{
		  display:none;
		}
		.login-html .tab,
		.login-form .group .label,
		.login-form .group .button{
		  text-transform:uppercase;
		}
		.login-html .tab{
		  font-size:22px;
		  margin-right:15px;
		  padding-bottom:5px;
		  margin:0 15px 10px 0;
		  display:inline-block;
		  border-bottom:2px solid transparent;
		}
		.login-html .sign-in + .tab,
		.login-html .sign-up + .tab{
		  cursor: pointer;
		}
		.login-html .sign-in:checked + .tab,
		.login-html .sign-up:checked + .tab{
		  color:#fff;
		  border-color:#1161ee;
		}
		.login-form{
		  min-height:345px;
		  position:relative;
		  perspective:1000px;
		  transform-style:preserve-3d;
		}
		.login-form .group{
		  margin-bottom:15px;
		}
		.login-form .group .label,
		.login-form .group .input,
		.login-form .group .button{
		  width:100%;
		  color:#fff;
		  display:block;
		}
		.login-form .group .input,
		.login-form .group .button{
		  border:none;
		  padding:15px 20px;
		  border-radius:25px;
		  background:rgba(255,255,255,.1);
		}
		.login-form .group input[data-type="password"]{
		  text-security:circle;
		  -webkit-text-security:circle;
		}
		.login-form .group .label{
		  color:#aaa;
		  font-size:12px;
		}
		.login-form .group .button{
		  background:#1161ee;
		}
		.login-form .group label .icon{
		  width:15px;
		  height:15px;
		  border-radius:2px;
		  position:relative;
		  display:inline-block;
		  background:rgba(255,255,255,.1);
		}
		.login-form .group label .icon:before,
		.login-form .group label .icon:after{
		  content:'';
		  width:10px;
		  height:2px;
		  background:#fff;
		  position:relative;
		  transition:all .2s ease-in-out 0s;
		}
		.login-form .group label .icon:before{
		  left:3px;
		  width:5px;
		  bottom:6px;
		  transform:scale(0) rotate(0);
		}
		.login-form .group label .icon:after{
		  top:6px;
		  right:0;
		  transform:scale(0) rotate(0);
		}
		.login-form .group .check:checked + label{
		  color:#fff;
		}
		.login-form .group .check:checked + label .icon{
		  background:#1161ee;
		}
		.login-form .group .check:checked + label .icon:before{
		  transform:scale(1) rotate(45deg);
		}
		.login-form .group .check:checked + label .icon:after{
		  transform:scale(1) rotate(-45deg);
		}
		.login-html .sign-in:checked + .tab + .sign-up + .tab + .login-form .sign-in-htm{
		  transform:rotate(0);
		}
		.login-html .sign-up:checked + .tab + .login-form .sign-up-htm{
		  transform:rotate(0);
		}
		.hr{
		  height:2px;
		  margin:60px 0 50px 0;
		  background:rgba(255,255,255,.2);
		}
		.foot-lnk{
		  text-align:center;
		}
		.no-hover{
			color: white !important;
		    text-decoration: none !important;
		}
		

    </style>
</head>
<body>
	