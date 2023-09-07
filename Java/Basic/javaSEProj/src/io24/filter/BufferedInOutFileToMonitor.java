package io24.filter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

//파일로부터 1바이트씩 읽어서 모니터에 출력하자(필터)
//데이터소스:파일-노드스트림:FileInputStream
//데이터목적지:모니터-노드스트림:System.out
//필터스트림:BufferedInputStream/BufferedOutputStream
public class BufferedInOutFileToMonitor {

	public static void main(String[] args) throws IOException {
		//1]필터를 끼운 입력스트림 생성
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream("src/io24/filter/KeyboardBuffered.txt"));
		//2]필터를 낑누 출력스트림 생성
		BufferedOutputStream bos=new BufferedOutputStream(System.out);
		//3]bis로 읽고 bos로 출력
		int data;
		while((data=bis.read())!=-1){
			bos.write(data);
			bos.flush();
		}
		//4]스트림 닫기
		bis.close();
	}

}
