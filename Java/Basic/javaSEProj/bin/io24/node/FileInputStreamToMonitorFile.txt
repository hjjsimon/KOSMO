package io24.node;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileInputStreamToMonitorFile {

	public static void main(String[] args) throws IOException {
		//파일로부터(Keyboard.txt)데이터를 읽어 모니터와 파일(KeyboardCopy.txt)에 동시출력, 파일에 직접 연결이 가능한 바이트기반의 노드스트림 이용
		//데이터소스, 목적지에 따라 입출력 노드스트림 결정
		//데이터소스: 파일, 바이트기반의 입력스트림:FileInputStream
		//데이터목적지: 모니터, 바이트기반의 출력스트림:System.out-PrintStream타입
		//           파일, 바이트기반의 출력스트림:FileOutputStream
		//1]입력스트림 생성
		FileInputStream fis=new FileInputStream("src/io24/node/Keyboard.txt");//키보드.txt 우클릭 카피 퀄리파이드 네임으로 주소복사 후 src부터 시작
		//2]출력스트림 생성
		//모니터용]
		OutputStream out=System.out;//부모에 담음
		//파일용]
		FileOutputStream fos=new FileOutputStream("src/io24/node/KeyboardCopy.txt");//파일명을 카피로 바꿔줌, 불린값 넣는 오버로딩도 있는데 그건 true면 추가해주는 것
		//3]fis로 읽고 fos와 out로 출력(아까는 키보드)
		int data;
		while((data=fis.read())!=-1) { //the next byte of data, or -1 if the end of thefile is reached. -> 파일끝에 도달시 읽을 바이트 없으면 -1 반환
			//모니터로 출력]
			out.write(data);
			out.flush();
			//파일로 출력]
			fos.write(data);
			fos.flush();
		}
		//4]스트림 닫기
		fis.close();
		fos.close();
	}
}
