package io24.filter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class BufferedRWKeyboardToMonitor {

	public static void main(String[] args) throws IOException {

		//키보드로부터 입력받아서 모니터로 출력하자, 단 브릿지스트림을 통해 문자단위로 변환하고, 필터스트림을 통해 필터링하자(BufferedReader/BufferedWriter필터스트림사용)
		//new BufferedReader(new InputStreamReader(System.in));//리더는 문자인데 키보드는 바이트? 브릿지를 써주면 됨
		//1]필터를 끼운 입력스트림 생성
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));//키보드로 읽은 시스템.in을 브릿지(InputStreamReader) 써서 문자로!(Input스타일을 리더로 바꿔줌)
		//2]필터를 끼운 출력스트림 생성
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		//3]br로 읽고 bw로 출력
		//BufferedReader의 readLine()메소드: 스트림의 끝에 도달하면 null반환하거나 읽은 문자열이 없으면 null반환(엔터값은 읽지않음) ->이전엔 -1반환
		String data; //이제 int아님, 문자로 한줄씩 읽으니 String에 담음
//		while((data=br.readLine())!=null) {//readLine()은 한줄씩 읽어서 매우 빠름
			//방법1]읽어온 데이터 뒤에 엔터값 추가
			//bw.write(data+"\r\n");
			//bw.flush();
			/*
		     * 안녕하세요
			안녕하세요HELLO-> 엔터값을 안읽음 쭉 출력되므로 엔터\r\n 추가해주면 됨
			HELLO1234567890
			1234567890 
		   */
		    //방법2]줄바꿈 기능을 하는 메소드 newLine() 사용
			//bw.write(data);
			//bw.newLine();//이걸 추가해주면 라인세퍼레이터, 줄바꿈 해주는것, 엔터 안해도 됨
			//bw.flush();
//		}
		//PrintWriter-출력용 필터스트림]
		//PrintWriter객체 생성시 생성자의 두번째 인자로 true주면 autoflush지원, 즉 flush 호출할 필요없음
		//BufferedWriter는 줄바꿈을 하려면 \r\n을 추가하거나, newLine()메소드를 호출해야하지만
		//PW는 줄바꿈을 지원하는 println(String str)메소드를 제공한다
		//즉 문자기반으로 입출력시 BufferedReader읽고 PrintWriter출력하면 편하다
	
		PrintWriter pw=new PrintWriter(System.out,true); //BW대신 PW를 많이 씀, 이건 브릿지 안쓰고 바로 out해도됨, true 하고, 오토플러싱도 됨
		//br로 읽고 pw로 출력
		while((data=br.readLine())!=null) {
			//플러쉬 불필요, 브릿지 불필요, 줄바꿈 불필요 -> pw가 짱임
			pw.println(data);
		}
	}

}
