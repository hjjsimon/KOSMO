package io24.filter;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataOutputMemoryToFile {

	public static void main(String[] args) throws IOException {
		//자바의 자료형에 저장된 데이터를 필터를 통과시킬때 DataOutputStream으로 출력은 DataInputStream으로 읽어야함(Data->자바의 자료형에 저장된 데이터 의미)
		//Object붙은건 힙에 생긴 메모리를 파일로 저장하거나 네트워크로 저장할때 쓰는 필터(단, 객체는 Serializable해야함, 직렬화)-> 근데 웬만한 클래스는 Serializable을 구현함(implement)
		//여튼 메모리의 데이터를 파일로 저장하겠다
		//자바의 각 자료형을 인코딩(필터사용)해서 파일로 저장하자
		//데이터소스: 자바의 각 자료형에 저장된 값(메모리-변수) -> 인풋스트림필요x
		//데이터목적지: 파일, 노드스트림:FileOutputStream(필터가 바이트기반이라 이거써야함)
		//DataOutputStream으로 필터링한(인코딩된) 데이터는 DataInputStream클래스를 사용해서 읽어야 제대로 읽어온다
		
		//1]데이터 소스준비-입력스트림 불필요
		byte b=100; //d로 나옴
		byte[] bArray= {97,98,99,100}; //abcd로 나옴
		char ch='가';
		int num=256;
		boolean bool=false;
		String object="안녕 자바! HELLO JAVA! 123456789";
		
		//2]출력스트림 생성->파일에 연결함
		DataOutputStream dos= new DataOutputStream(new FileOutputStream("src/io24/filter/javatype.dat"));
		//3]메모리에서 데이터 읽고(변수에 저장된 데이터를) dos로 출력, 자바의 각 자료형에 해당하는 출력용 메소드 사용
		dos.writeByte(b); //각 자료형에 맞는 write 계열이 있음, byte읽으니까 b 넣으면 됨
		dos.write(bArray);
		dos.writeChar(ch);
		dos.writeInt(num);
		dos.writeBoolean(bool);
		dos.writeUTF(object); //String은 좀 특이함 UTF임
		//4]스트림닫기
		dos.close();
		
		//dabcd�  ~~~~~~~~뭔거지같이 나옴
		//byte는 문자로 나옴, String은 그대로 인코딩, 나머지도 이상하게 나옴
		//출력스트림 필요x, 읽어서 변수에 넣으면 되니까
		
	}

}
