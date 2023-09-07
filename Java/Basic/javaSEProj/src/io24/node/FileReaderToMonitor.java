package io24.node;

import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

public class FileReaderToMonitor {//reader니까 문자단위 읽음, monitor 1바이트단위라 문제생김, 바이트->문자 필요, OutputStreamWriter

	public static void main(String[] args) throws IOException {
		
		//파일로부터 한문자씩 읽어 모니터에 출력
		//데이터소스:파일, 노드스트림:FileReader(문자기반)
		//데이터목적지:모니터, 노드스트림:System.out(바이트기반), 브릿지스트림:OutputStreamWriter
		//1]입력스트림 생성
		FileReader fr=new FileReader("src/io24/node/KeyboardWriter.txt");//새로 파일 만듦, 여기에 입력할 것
		//2]출력스트림 생성
//		PrintStream out=System.out;
		//3]브릿지스트림으로 1바이트씩 출력 스트림으로 내보내는 데이터를 문자단위로 변환
		OutputStreamWriter osw=new OutputStreamWriter(System.out);
		
		int data;
		//fr로 읽고 out으로 출력-한글깨짐->안녕하세요->HUX8�
/*		while((data=fr.read())!=-1){//파일 끝에 도달시 -1반환
			out.write(data);
			out.flush();
		}*/
		//4]fr로 읽고 osw로 출력
		while((data=fr.read())!=-1){
			osw.write(data);
			osw.flush();
		}
		//5]스트림닫기
		fr.close();
	}/////////

}/////////
