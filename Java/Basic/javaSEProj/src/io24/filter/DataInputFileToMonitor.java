package io24.filter;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class DataInputFileToMonitor {

	public static void main(String[] args) throws IOException {
		//파일(javatype.dat)에 저장된 자바의 각 자료형값을 디코딩(필터사용)해서 자바의 각 자료형(변수)에 저장하자, 그리고 확인차 모니터에 출력하자
		//데이터소스:파일, 노드스트림:FileInputStream
		//데이터목적지:자바의 각 변수-메모리, 모니터-노드스트림:System.out
		//DataOutputStream으로 필터링한(암호화된) 데이터는 DataInputStream클래스를 사용해서 읽어야 제대로 읽어온다. 단, 읽어올때는 저장된 자료형 순서대로 읽어와야한다
		//1]입력스트림 생성
		DataInputStream dis=new DataInputStream(new FileInputStream("src/io24/filter/javatype.dat"));
		//2]dis로 읽어서 자바의 각 자료형에 저장, 단 반드시 DataOutputStream으로 출력할 때의 순서를 꼭 지켜야한다-> 외계어에서 읽어오는방법~~~
		byte b=dis.readByte();//저장된 외계어 파일에서 d 읽어온것 100 출력됨
		System.out.println(b);
		byte[] bArr=new byte[4];
		dis.read(bArr);
		System.out.println(Arrays.toString(bArr));
		char ch=dis.readChar();
		System.out.println(ch);//외계어에서 가 가져옴
		int num=dis.readInt();
		System.out.println(num);
		boolean bool=dis.readBoolean();
		System.out.println(bool);
		String str=dis.readUTF();
		System.out.println(str);
		//dis.readByte(); -> 더 읽을게 없음, 에러남 EOFException End Of File, 파일이 끝남
		dis.close();
	}

}
