package io24.filter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjectInputFileToMemory {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

		//1]필터를 끼운 입력스트림 생성
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream("src/io24/filter/Persons.txt"));//객체 읽어오는거라 오브젝트써야함
		//2]ois로 읽어서 Person타입에 저장
		Object obj=ois.readObject();//모든타입 꺼낼 수 있음 Object반환
		if(obj instanceof Person) {
			Person person1=(Person)obj;
			System.out.println(person1);//Person의 toString 오버라이딩 해놓음, 주소출력x, 외계어를 잘 읽어옴
		}
		Person person2=(Person)ois.readObject();
		System.out.println(person2);
		Person person3=(Person)ois.readObject();
		System.out.println(person3);
		//ois.readObject();-> EOFException 데이터아웃풋 상속받아 에러 동일
		ois.close();
	}
}
