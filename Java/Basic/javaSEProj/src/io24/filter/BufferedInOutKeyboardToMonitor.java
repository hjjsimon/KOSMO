package io24.filter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

//캐시:제일 빠르다, 버퍼:데이터 임시저장 메모리, 램:프로그램이 올라온다(실행).휘발성, ROM:컴퓨터에 내장된 읽기전용메모리(비휘발). 하드디스크:영구데이터저장. 플래시:내용보존, 하드디스크보다 빠르고 내구성높음(USB) 
//BufferedReader쓰면 BufferedWriter는 잘 안씀, PrintWriter를 잘 씀 이게 장점이 많음
//BufferedInputStream, BufferedOutputStream 512자의 메모리를 저장할 수 있는 계란판이 존재, 이게 다 버퍼드 계열, 문자든 바이트단위든 계란판에 담아서 한번에 계란판 단위로 필터를 거쳐 내보냄, 그래서 웬만하면 필터를 끼움
//필터를 쓰면 필터의 유용한 메소드도 쓸 수 있음

//키보드로부터 데이터를 입력받아 모니터로 출력하자(단, 필터스트림 이용)
//데이터소스:키보드,노드스트림:System.in
//데이터목적지:모니터,노드스트림:System.out
//필터스트림:BufferedInputStream/BufferedOutputStream
public class BufferedInOutKeyboardToMonitor {

	public static void main(String[] args) throws IOException {

		//노드스트림과 필터스트림 연결방법]
		//필터스트림 생성자의 인자로 노드스트림(인풋이나 아웃풋)을 전달하면 된다
		
		//1]필터를 끼운 입력스트림 생성
		BufferedInputStream bis=new BufferedInputStream(System.in);//인풋스트림이 부모
		//2]필터를 끼운 출력스트림 생성
		BufferedOutputStream bos=new BufferedOutputStream(System.out);//아웃풋스트림이 부모->시스템.아웃이니까 모니터에 출력
		//이렇게 입,출력스트림에 각각 512개짜리 계란판 필터 끼움
		//3]bis로 읽고 bos로 출력
		int data;
		while((data=bis.read())!=-1) {
			bos.write(data);
			bos.flush();
		}
	}
}
