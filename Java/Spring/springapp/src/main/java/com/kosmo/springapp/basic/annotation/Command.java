package com.kosmo.springapp.basic.annotation;

//스프링에서는 DTO계열 클래스를 커맨드 객체라 한다
public class Command {

	public Command() {
		System.out.println("Command의 생성자");
		//확인 누를 때마다 매번 Command의 생성자 호출됨
		//요청 들어올 때마다 계속 new하는 것, 싱글톤 디자인이 아님, 싱글톤이면 한번 호출 후 계속 덮어쓰기
	}
	
	/*
	 폼의 파라미터명과 속성(멤버변수)을 반드시 일치시켜야한다
	 체크박스 같은 경우는 타입을 String[]해도 무방
	 String으로 받는 경우 ,(콤마)구분해서 선택된 모든 값들이 저장된다
	 
	 사용자가 name, id 등 입력시 setter가 작동해서 멤버변수 값들이 초기화됨
	*/
	
	//속성들]
	private String name;
	private String id;
	private String pwd;
	private String inter;//private String[] inter; 가능
	private String grade;
	private String file;
	private String self;
	private String gender;
	
	//자바코드(@Configuration 및 @Bean 사용)로 현재 빈을 컨테이너에 등록하기 위한 테스트용 생성자
	public Command(String name, String id, String pwd) {
		this.name = name;
		this.id = id;
		this.pwd = pwd;
		System.out.println("Command의 인자 생성자");
	}
	
	//게터,세터]
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public String getInter() {
		return inter;
	}
	public void setInter(String inter) {
		this.inter = inter;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
	
	
	
	
	
	
}
