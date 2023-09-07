package io24.filter;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.InputMismatchException;

import common.utility.CommonUtil;

public class BufferedRWKeyboardToFile {

	public static void main(String[] args) throws IOException {

		//파일은 방법2개 FileOutputStream, 필터 RW니까 브릿지써야함/ 또는 fileWriter, 
		//버퍼드 라이터는 둘 다 가능 대신 브릿지, PW는 안씀 짱
		//키보드로 입력받아서 파일(KeyboardChar.txt)로 출력(FileWriter스트림 사용), 단 키보드로 입력받은 데이터(1바이트)는 브릿지 스트림으로 문자단위로 변환하라
		//그리고 필터스트림(2바이트 기반)을 통해 필터링하자(BufferedReader/PrintWriter필터스트림 사용)
		//1]필터끼운 입력스트림 생성
/*		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		//2]필터끼운 출력스트림 생성
		PrintWriter pw=new PrintWriter(new FileWriter("src/io24/filter/KeyboardChar.txt"),true);
		//3]br로 읽고 pw로 출력
		String data;
		while((data=br.readLine())!=null) {
			pw.println(data);
		}
		//4]스트림닫기
		pw.close();//파일만 처리
*/		
		//스캐너 클래스가 나오기 전에는 필터를 이용해 귀찮게 직접 썼다
		//Scanner클래스의 nextLine() 및 nextInt()메소드와 같은 내가 만든 메소드 테스트
		System.out.println("이름을 입력하세요?");
		System.out.println("당신의 이름:"+nextLine());
		System.out.println("나이를 입력하세요?");
		System.out.println("당신의 10년 후 나이:"+(nextInt()+10));
	
	}////main	
	
		private static String nextLine() {
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			String input=null;
			try {
				input=br.readLine();
			}
			catch(IOException e) {e.printStackTrace();}
			return input;
		}
		
		private static int nextInt() throws InputMismatchException{
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			String input=null;
			try {
				input=br.readLine();
			}
			catch(IOException e) {e.printStackTrace();}
			if(!CommonUtil.isNumber(input)) throw new InputMismatchException();
			return Integer.parseInt(input);
		}
}////class
