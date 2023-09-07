package io24.filter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BufferedRWFileToFile {

	public static void main(String[] args) throws IOException {

		//문제]파일(BufferedInOutFileToFile.java)로부터 입력받아 .txt로 출력(노드스트림은 바이트기반)
		//문자기반의 필터스트림(BufferedReader/Writer)를 통해 필터링하라
		//1]입력스트림 생성
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("src/io24/filter/BufferedInOutFileToFile.java")));//인풋스트림을 리더로 바꿔야함
		//2]출력스트림 생성
		PrintWriter pw=new PrintWriter(new FileOutputStream("src/io24/filter/BufferedInOutFileToFile.txt"),true);//t면 오토플러쉬
		//3]br로 읽어서 pw로 출력
		String data;//라인단위로 읽으면 무조건 스트링
		while((data=br.readLine())!=null) {
			pw.println(data);
		}
		//4]스트림닫기
		br.close();
		pw.close();
	}////////////main

}///////////////class
