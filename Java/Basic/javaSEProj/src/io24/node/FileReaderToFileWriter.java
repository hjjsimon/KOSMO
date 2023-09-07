package io24.node;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*//브릿지스트림 안써도됨, 입출력 단위 맞음
문자 스트림: 문자(한글:2바이트,영문자,숫자:1바이트)단위로 데이타를 읽고 쓴다.
문자 스트림중 파일에 직접 연결이 가능한
노드 스트림:FileReader/FileWriter
주로 이진(binary) 파일보다 텍스트 파일을 다룰때 주로 사용		 
데이타 소스:파일 ,노드 스트림(문자 단위):FileReader
데이타 목적지:파일,노드 스트림(문자 단위):FileWriter
*/
/*
* File관련 각 노드 스트림의 예외 발생 종류
------+-----------------+--------------+-----------------+--------------
구분   | FileInputStream |  FileReader  |FileOutputStream | FileWriter
------+-----------------+--------------+-----------------+-------------
생성자  |              FileNotFoundException	             |IOException
------+--------------------------------+-----------------+--------------
read()|			IOException            |
------+--------------------------------+----------------------------------
write()                                |           IOException
---------------------------------------+---------------------------------
close()							IOException
------------------------------------------------------------------------	 
※ IOException는 FileNotFoundException의 부모
*/
public class FileReaderToFileWriter {

	public static void main(String[] args) throws IOException {

		//문]FileInputStreamToMonitorFileOutput.java를 문자단위로 읽어서 .txt파일로 문자단위로 저장하라
		//1]입력스트림 생성
		FileReader fr=new FileReader("src/io24/node/FileInputStreamToMonitorFile.java");//FileReader는 Reader로 끝났으니 Reader타입, Reader상속!
		//2]출력스트림 생성(문자단위니까 파일라이터)
		FileWriter fw=new FileWriter("src/io24/node/FileInputStreamToMonitorFile.txt");
		//3]fr로 읽고, fw로 출력
		int data;
		while((data=fr.read())!=-1) {//문자단위로 읽고, 문자단위로 출력
			fw.write(data);
			fw.flush();
		}
		//4]스트림 닫기
		fw.close();
		fr.close();//둘다 파일이니까 둘다 닫아줘야함
	}

}
