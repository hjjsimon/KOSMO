package io24.node;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class KeyboardToFileWriter {

	public static void main(String[] args) throws IOException {
		//입출력 읽고 쓰는 바이트가 같아야 한글 안깨짐, 이걸 해결해주는게 브릿지, 다리역할을 한다
		//InputStreamReader는 바이트기반, 바이트단위로 입력받음 이걸 문자(Reader니까)단위로 변환하는 브릿지스트림(키보드로 읽으면 무조건 바이트 이를 문자단위(Writer)로 출력해줘야 한글이 안깨짐)
		//Output ~~은 문자단위로 받아 바이트로 출력, 모니터도 바이트단위! 
		//ex. 읽기를 FileReader로 읽었으면 파일에서 문자단위(Reader)로 읽은것, 모니터는 바이트단위라 출력시 한글깨짐, 문자로 바꿀때 Out~써야함
		//인풋:바이트->문자/ 아웃풋:문자->바이트
		/*
		키보드로 읽어서(바이트 단위로 읽음) 문자 단위로 파일에 출력
		브릿지 스트림을 이용해서 1바이트씩 읽은 데이타를 문자단위로 변환한다.
		
		데이타 소스: 키보드 - System.in(1바이트씩 읽음)
		브릿지 스트림: InputStreamReader(1바이트를 문자단위로 변환)
		데이타 목적지: 파일 - FileWriter(한 문자씩 출력)
		
		[자바IO에서 한글이 깨지는 경우]->입출력바이트단위 틀릴때
		-1바이트씩 읽어서 한 문자씩 출력시 :InputStreamReader브릿지 스트림 이용
		-한 문자씩 읽어서 1바이트로 출력시 :OutputStreamWriter브릿지 스트림 이용
		※1바이트씩 읽어서 1바이트씩 출력하거나 한문자 씩 읽어서 한문자 씩 출력 할때는 한글이 깨지지 않음.(1바이트 입력,출력 기본은 키보드,모니터)
	*/
		//1]바이트기반 입력스트림 생성
		//키보드에서 1바이트씩 입력하면 .java에 read()로 1바이트씩 들어옴, 그대로 File에 보내면 숫자나 영문은 1바이트씩은 상관없음, 한글은 2바이트라 문제
		//1바이트로 읽는 .java에서 문자기반 writer를 쓰면 문자로 읽을 수 있음, 바이트를 문자기반으로 바꿔줘야함 o(바이트)->ㅁ(문자) 이런식으로 중간에 .java에서 브릿지로 바꿔줘야함
		//InputStream is=System.in;
		//2]브릿지 스트림을 이용해서 바이트 기반 입력을 문자기반으로 변환
		InputStreamReader isr=new InputStreamReader(System.in);//InputStream 타입을 넣어줘야함, 당연히 is대신 System.in 써도 됨 그러면 위에 //하면됨
		//Scanner sc=new Scanner(System.in)	이게 키보드로 입력받는다는 뜻! 위 System.in쓰는게 동일
		//3]문자기반의 출력 스트림 생성
		FileWriter fw=new FileWriter("src/io24/node/KeyboardWriter.txt");//.txt파일 만듦
		//is로 읽고 fw로 출력-한글이 깨진다]
		int data;
/*		while((data=isr.read())!=-1) {//ctrl+z눌러야 끝남, 이전까지는 바이트단위로 읽었는데, 지금 메소드는 리더기때문에 싱글캐릭터 (문자)단위로 읽음, 한글도 영문숫자도 한글자씩!
			fw.write(data); //fw가 파일라이터니까 한문자씩, 캐릭터형 배열 넣어줘야함
			fw.flush();
		}*/
		//while((data=is.read())!=-1) { -> isr 대신 is쓰면 바이트단위로 읽어서 한글 다 깨짐 ->ìëíì¸ì 됨
		//4]isr로 읽어서 fw로 출력
		//read()는 바이트단위가 아니라 문자단위로 읽는다, 그래서 바이트수를 카운트로 알아내기 힘들다.(가능은함 정규표현식 쓰거나 한글 카운트 2개씩? 뭔 옛날에 했던 방식으로)
		int totalChar=0;//읽은 문자수
		int repeatCount=0;
		//필터효과 적용전]
/*		while((data=isr.read())!=-1) {
			totalChar++;
			repeatCount++;
			fw.write(data); 
			fw.flush();
		}*/
		//필터효과 적용] ->한문자니까 char에 저장함
		char[] cbuf=new char[10];
		//read(char[] cbuf)는 읽은 문자수 반환, 실제 데이터는 cbuf에 저장된
		//read(byte[])와 (char[])의 차이: byte[]는 배열이 다 안채워져도 엔터를 만나면 읽은 문자수 반환된다
		//ex. byte[]는 가나엔터치면 가,나,\r,\n 문자 4개 읽은것 
		//ex. char[]는 문자 4개니까 4 반환함? 뭔말???
		while((data=isr.read(cbuf))!=-1) {//읽은 문자수=data
			totalChar+=data;
			repeatCount++;
			//write(char[],0,읽은 문자수)
			fw.write(cbuf,0,data); //0번부터 채우니까 0써야지->안녕하세요 HELLO 1234567890은 26글자였음, 지금 안녕하세요하면 5자에 엔터까지 0~6번방까지 채워지고 숫자7반환, 또 HELLO도 7반환, 0~9면 문자 12개, 12누적, 총 7+7+12, 26누적에 반복은 3회   
			fw.flush();//[10]이면 다 채워져서 읽은 문자수 10반환, 그래서 반복횟수 4나옴
		}
		System.out.println("총 입력 문자수:"+totalChar+",반복횟수:"+repeatCount);//안녕하세요 5글자, 근데 총입력문자7개 나옴 엔터때문에 \r\n추가됨
		
		//스트림 닫기->System.in이니까 키보드랑 연결된것, 끊어줘야함
		fw.close();
	}

}
