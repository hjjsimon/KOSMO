package io24.filter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//파일(FileReaderToMonitor.java)로부터 데이터를 1바이트씩 읽어 .txt 파일에 1바이트씩 출력하자
//입력을 위한 노드스트림:FileInputStream
//출력:FileOutputStream
public class BufferedInOutFileToFile {

	public static void main(String[] args) throws IOException {
		//1]필터를 끼운 입력스트림 생성
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream("src/io24/node/FileReaderToMonitor.java"));//node에 있는 파일을 filter로 복붙한것
		//2]필터를 낑누 출력스트림 생성
		BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream("src/io24/filter/FileReaderToMonitor.txt"));
		//3]bis로 읽고 bos로 출력
		int data;
		while((data=bis.read())!=-1){
			bos.write(data);
			bos.flush();
		}
		//4]스트림 닫기
		bis.close();
		bos.close();
	}

}
