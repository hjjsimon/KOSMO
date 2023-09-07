package io24.filter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class BufferedRWFileToMonitor {

	public static void main(String[] args) throws IOException {
		//파일로부터 데이터를 읽어서(노드스트림:FileReader)모니터로 출력하자, 필터스트림을 통해 필터링하자(BufferedReader/PrintWriter)
		//1]필터끼운 입력스트림 생성
		BufferedReader br=new BufferedReader(new FileReader("src/io24/filter/BufferedInOutFileToFile.java"));//br장점2개 라인단위라빠름, 반환 String이라 메소드다양
		//2]필터끼운 출력스트림 생성
		PrintWriter pw=new PrintWriter(System.out,true);
		//3]br로 읽고 pw로 출력
		String data;
		
		int line=1;
		while((data=br.readLine())!=null) {
			//문제]라인번호를 붙여서 출력하고 "java"를 한글 "자바"로 바꿔서 출력해라
			for(int i=1;(data=br.readLine())!=null;i++) {
				data=data.replace("java", "자바");//이게 내맘대로 바꾼게 아님, 그냥 String을 반환했는데 담지도 않은것, 담아놓고 그걸 출력해야함->data=으로 data를 바꿔줘야함
				pw.println(i+" "+data);
			}
			/*pw.println(String.format("%-4s%s",line++,data.replace("java", "자바")));*/
		}
		//4]스트림닫기
		br.close();
	}

}
