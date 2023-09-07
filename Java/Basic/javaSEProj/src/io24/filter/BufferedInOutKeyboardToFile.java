package io24.filter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//키보드로부터 데이터를 읽어 파일(KeyboardBuffered.txt로 출력하라(1바이트기반의 노드스트림사용, 입출력은 필터사용->속도증가)
//데이터소스:키보드-노드스트림:System.in
//데이터목적지:파일-노드스트림:FileOutputStream
//필터스트림:BufferedInputStream/BufferedOutputStream
public class BufferedInOutKeyboardToFile {

	public static void main(String[] args) throws IOException {

		//1]필터를 끼운 입력스트림 생성
		BufferedInputStream bis=new BufferedInputStream(System.in);//키보드로 읽어서 
		//2]필터를 끼운 출력스트림 생성
		BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream("src/io24/filter/KeyboardBuffered.txt"));//파일로 출력
		//3]bis로 읽고 bos로 출력
		int data;
		while((data=bis.read())!=-1) {
			bos.write(data);
			bos.flush();
		}
		//4]스트림 닫기(파일은 닫아야함)
		bos.close();
	}

}
