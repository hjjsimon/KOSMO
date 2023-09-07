<%@page import="java.io.IOException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	//서버측에서도 유효성 체크(get방식은 정보노출, post방식으로 해야함)
	private boolean isValidate(JspWriter out, String param, String message){
		if(param == null || param.trim().length()==0){
			try{
				out.println("<script>");
				out.println("alert('"+message+"');");
				out.println("history.back();");//확인 누르면 뒤로 이동함
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

%>