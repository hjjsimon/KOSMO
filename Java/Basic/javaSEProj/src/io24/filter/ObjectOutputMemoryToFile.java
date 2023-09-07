package io24.filter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectOutputMemoryToFile {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		//1]데이터 소스(메모리) 준비-입력 스트림 불필요
		Person pe1=new Person("가길동",20,"천호동");//설계도 가지고 객체로 만듦
		Person pe2=new Person("나길동",30,"잠실동");
		Person pe3=new Person("다길동",40,"석촌동");
		
		//2]필터를 끼운 출력스트림 생성
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("src/io24/filter/Persons.txt"));
		
		//3]인스턴스화된 객체를 oos로 출력
		//사전준비사항:객체를 파일로 저장하려면 먼저 해당 객체는 직렬화가 돼있어야한다, 그렇지 않으면 notSerializable예외 발생 -> Person클래스 가서 implement 해주고 와야함
		oos.writeObject(pe1);//DataOutput상속받아서 write 그대로 쓸 수 있음, 객체 쓸거니까 writeObject
		oos.writeObject(pe2);
		oos.writeObject(pe3);
		//메모리에 저장된걸 ios이미지로 스냅샷 뜸 -> �
		
		
	}////////main

}////////////class
