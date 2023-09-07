package com.kosmo.springapp.basic.annotation;

public class AuthenticationCommand {

	//SessionAttributeController.java에서 3.커맨드객체 사용시 에서 넘어옴, 커맨드 객체 만든것
	
	//※반드시 로그인 폼의 파라미터명과 일치시키자
	//멤버변수]
	private String id;
	private String pwd;
	
	//게터,세터]
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
