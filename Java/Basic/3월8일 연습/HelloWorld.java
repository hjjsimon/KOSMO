import java.lang.System;
import java.lang.String;
import java.util.Date;

public class HelloWorld
{
	public static void main(String[] args)
	{
		System.out.println("Hello World!!!");
		System.out.println("안녕 자바");
		System.out.println("감사합니다.");
		//한줄 주석 : //


		//자바 교육기관명 출력 시작
		/*
		여러줄 주석 처리하기
		System.out.println("한국");
		System.out.println("소프트웨어");
		System.out.println("인재개발원");
		*/
		//자바 교육기관명 출력 끝

		Date today = new Date();
		System.out.println(today);
	}

}