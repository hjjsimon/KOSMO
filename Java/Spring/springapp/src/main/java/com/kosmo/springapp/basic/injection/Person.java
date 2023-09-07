package com.kosmo.springapp.basic.injection;

import org.springframework.stereotype.Component;

@Component//어노테이션 안붙으면 컨테이너가 얘를 빈으로 관리 안함, 생성이 안됨, 컴포넌트 어노테이션 붙이면 서버 생성시 생김
public class Person {//어노테이션 써서 빈에 등록한것

	//[속성(필드)-멤버변수들]
	private String name;
	private String addr;
	private String age;
	//[기본 생성자]
	public Person() {
		System.out.println("Person의 기본 생성자");
	}
	//[인자 생성자]
	public Person(String name, String addr, String age) {
		this.name = name;
		this.addr = addr;
		this.age = age;
		System.out.println("Person의 인자 생성자");
	}
	//[게터/세터]
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	@Override//주소 보려고 주석해놨었음
	public String toString() {
		return String.format("[이름:%s,주소:%s,나이:%s]", name, addr, age);
	}
	
}
