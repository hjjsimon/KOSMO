package io24.filter;

import java.io.Serializable;

public class Person implements Serializable{

	/*
	자바 클래스의 객체(new한거)는 직렬화가 되야 파일로 저장하거나 네트웍을 통해 전송할 수 있다.
	어떤 클래스를 직렬화 하려면 Serializable(인터페이스임, 추상메소드 없음)혹은 Externalizable(인터페이스임, 자바에서 안씀, 추상메소드 2개 있어서 써야함) 인터페이스를 구현(상속)해야 한다.
	*/
	//멤버변수
	private String name;
	private int age;
	private String addr;
	public Person(String name, int age, String addr) {
		this.name = name;
		this.age = age;
		this.addr = addr;
	}
	@Override
	public String toString() {
		return "[이름="+name+",나이="+age+",주소="+addr+"]";
	}
	

}
