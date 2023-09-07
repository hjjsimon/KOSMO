package io24.node;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class InOutExample {

	public static void main(String[] args) throws IOException {

		//문제]STDNodeInOut.java파일의 내용을 읽어서 모니터와 파일로 출력, 파일로 출력시 파일명은 STDNodeInOut.txt로 하자->확장자만 바꿈
		FileInputStream fis=new FileInputStream("src/io24/node/STDNodeInOut.java");
		//OutputStream out=System.out;//모니터로 출력->한방에 출력가능
		FileOutputStream fos=new FileOutputStream("src/io24/node/STDNodeInOut.txt");//파일로 출력
		int data;
		while((data=fis.read())!=-1) {//1바이트씩 읽어서 담음
			//모니터로 출력]
			//out.write(data);//한방에 출력가능
			//out.flush();
			//한방에 출력하는법->변수선언도x
			System.out.write(data);
			System.out.flush();
			//파일로 출력]
			fos.write(data);//읽은거 출력
			fos.flush();
		}
		fis.close();
		fos.close();
	}

}//바이트기반끝!!!!!!! 이후 문자기반! 
