package io24.node;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class KeyboardToMoniterFile {

	public static void main(String[] args) throws IOException {

		//키보드는 데이터소스, system.in -> 모니터 또는 파일은 데이터목적지, 모니터는 system.out, 파일은 FileOutputStream, 바이트단위로 읽어오니까 바이트단위로 내보내야함, 그래야 한글이 안깨짐(문자단위면 FileWriter) 
		//키보드로 데이터를 입력받아 입력받은 내용을 파일과 모니터로 출력
		//데이터소스:키보드, 바이트(키보드라)노드스트림:System.in
		//데이터목적지 파일, 바이트노드스트림:FileOutputStream
		//          모니터, 바이트노드스트림:System.out
		//1]데이터 입력용 바이트 기반의 노드스트림 생성
		InputStream is=System.in;
		//2-1]파일 출력용
		//Window식 디렉토리 표기법
		//FileOutputStream fos=new FileOutputStream("D:\\HJJ\\Workspace\\Java\\Basic\\javaSEProj\\src\\io24\\node\\Keyboard.txt"); //경로 풀로 넣어줌(안좋음 사람마다 폴더다름), \\Keyboard.txt추가해주면 이 파일을 생성하겠다는 뜻 -> 왜 난 안생기지 파일 아 생김 지우래 그냥 선생님이
		//유니스/리눅스식 디렉토리 표기법
		//FileOutputStream fos=new FileOutputStream("D:/HJJ/Workspace/Java/Basic/javaSEProj/src/io24/node/Keyboard.txt"); //리눅스는 \\대신/ 쓴대, 리프레쉬하면 키보드.txt에 있는거 다 날아감
		//파일경로:src부터 시작]
		FileOutputStream fos=new FileOutputStream("src/io24/node/Keyboard.txt");//src부터 접근, 이게 더 좋은 방법
		//2-2]모니터 출력용
		PrintStream out=System.out;
		//3]is로 읽고 fos와 ps로 출력
		int ascii;
		while((ascii=is.read())!=-1) {//컨트롤z로 빠져나옴
			//파일로 출력]
			fos.write(ascii);
			fos.flush();//write니까 flush해줘야함
			//모니터로 출력
			out.write(ascii);
			out.flush();
		}
		//4]스트림 닫기 -> fos는 표준입출력이 아니라 꺼줘야함
		fos.close();//FileNotFoundException 였는데 왼쪽 예외 던지면 IOException으로 바뀜, 이게 부모라 포함가능 ㄱㅊ
		//바이트로 입출력하면 ㄱㅊ 한글도 출력 잘 됨, 컨트롤제트 누르면 먹음
		//HELLO
		//안녕
		//1234567890
		//사용자 입력한걸 Keyboard.txt가면 파일로 나타남
	}
}
