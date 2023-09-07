package exception21;

import java.io.IOException;
import java.sql.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionBasic {

	public static void main(String[] args) /*throws IOException*/ {
		/*
		1]컴파일 에러(Checked Exception): ->빨간줄 체크가는 에러
			- 컴파일시 발생하는 에러
			- Syntax에러(문법오류) ,IOException(예외 클래스),SQLException(예외클래스)등 
			ex. 자바에서는 외부자원 IO,SQL사용시 무조건 에러냄 
			//한문자 입력시 System.in.Read(); 빨간줄감, 이게 컴파일에러 IO예외, Read는 키보드에서 읽겠다는것, jvm실행환경 안에 있는게 아니라 밖의 키보드, 
			 * //외부자원에서 가져오려고하면 자바는 무조건 컴파일에러 발생시킴, 오라클이 SQL 이거 쓸때도 에러냄
			- 컴파일이 안되면 실행이 안됨.(실행은 .class파일, 저장해야 컴파일해서 .class생김)
			- 컴파일 에러(외부 자원 사용시 발생하는 컴파일에러:IOException,SQLException)는
			  던지거나(throws), 직접 처리(try~catch절) 할 수 있다, 단,Syntax오류(문법오류)는 직접 수정해야 함-> 런타임오류는 try~catch만 가능		  	
		*/
		//1-1]Syntax에러
		//Int num; //[x]해결책 I를 i로	
		int num;//[o]
		if(true); {}
		//else {}//[x]else는 항상 if와 짝을 이뤄야함
		//1-2]IOException -> 던지면 jvm이 처리해줌 신경x
		//방법1]예외를 throw한다-> System.in.read(); 컴파일에러남, 방법1]빨간줄 눌러서 throws 해야함 -> main안에서 호출하니까 main에 throws함, 키보드로 입력될 수 있는 에러를 처리해야함 /*throws IOException*/ 
		//호출한 메소드() throws 예외클래스 -> 일반형식
		//System.in.read();
		//방법2]try~catch절로 감싸 직접 예외처리
/*		try {
			System.in.read();
		} catch (IOException e) {
			
			e.printStackTrace();
		} //
*/		
		/*
		2]런타임 에러(UnChecked Exception)
		- 컴파일시에는 체크가 안됨.실행시에만 발생되는 에러
		- RuntimeException계열(NullPointerException,ArithmeticException, ArrayIndexOutOfBoundsException등)
		- main메소드에서는 throws해도 오류발생. 즉 직접 처리(try~catch절)해야만 한다
		 ※예외 발생시 JVM은 해당 예외클래스를 인스턴스화 해서 예외 객체를 프로그램쪽에 전달한다. ->IOException을 인스턴스화한 e가 객체, 오류정보 들어있음
		*/
		//[ArrayIndexOutOfBoundsException]:배열의 크기를 벗어난 인덱스 사용시
		try {
			int[] array=new int[2];
			array[0]=100;
			System.out.println("array[0]:"+array[0]);
			array[1]=200;
			System.out.println("array[1]:"+array[1]);
			array[2]=300;// 위에 [0],[1]번방뿐 [2]번방 없으니 에러, 프로그램 멈춰서 아래 코드 실행이 불가
			System.out.println("array[2]:"+array[2]); 
		}
		catch(ArrayIndexOutOfBoundsException e) {//에러명으로 받고, 객체명은 e 에러유래로 많이씀
			System.out.println("예외가 발생했어요");//array[2]출력코드 실행안되고 바로 catch 여기로 들어옴
			System.out.println("관리자에게 문의하세요");
		}
		//[NumberFormatException]:숫자형식의 문자열을 int형으로 변환시 해당 문자열이 숫자형식이 아닐때
		Scanner sc=new Scanner(System.in);
		System.out.println("나이를 입력하세요?");
		try {
			//1.nextInt()로 나이받기
			//int age=sc.nextInt();
			//2.nextLine()로 나이받기 -> 얘는 문자도 에러안남
			String StringAge=sc.nextLine();
			int age=Integer.parseInt(StringAge);
			System.out.println("당신의 10년후 나이:"+(age+10));
		}
		catch(InputMismatchException|NumberFormatException e) {//지금은 nextInt가 int에 못담아 넘버포맷익셉션이라고는 안나옴//nextLine은 넘버포멧익셉션으로 나옴, or 처리 해줘야함, 뭐가 발생하든 에러처리가능
			System.out.println("나이는 숫자만...");
		}
		//[NullPointerException]:인스턴스 변수에 해당 객체의 메모리주소가 저장이 안된경우에 .으로 객체의 멤버에 접근할 때 발생
		System.out.println(today);//주소없음 null나옴
		try {
			today.getTime();//가리키는 메모리 없으니 .으로 겟타임메소드 접근불가 NullPointerException 나옴
		}
		catch(NullPointerException e){
			System.out.println("today는 null입니다. 포인터할 수 없어요");
		}
		// "": 빈 문자열, null아님, 크기0나옴
		// null: null값. null이다
		String empty=""; //빈 문자열
		System.out.println("빈 문자열의 길이:"+empty.length()); //null이 있는거면 NullPointerException 에러남
		String nullStr=null;
		try {
			System.out.println("null문자열의 길이:"+nullStr.length()); //NullPointerException -> nullStr이 null임
		}
		catch(NullPointerException e) {
			System.out.println("nullStr은 null입니다");
		}
		//[ArithmeticException]: 0으로 나눌때 발생
		int result=100;
		try {
			result/=0; //result=result/0 인것
			System.out.println("0으로 나눈 결과:"+result); //ArithmeticException: / by zero <- 오른쪽이 예외메세지, zero로 나누지말란뜻
		}
		catch(ArithmeticException e) {
			//예외메시지 출력방법
			//1.사용자 임의 예외 메세지
			System.out.println("0으로 나눌 수 없어요");//보안적으로 이게 좋음
			//2.예외클래스의 인스턴스변수 e를 이용: 모든 예외계열은 toString이 오버라이딩 되어있음(
			//e.toString() 또는 e만-> "예외클래스 : 예외메시지" 형태로 문자열로 반환
			System.out.println(e);
			//3.e.getMessage():예외메시지만 출력->/ by zero 출력
			System.out.println(e.getMessage()); 
			//4.e.printStackTrace(); ->개발시 주로 사용, 쭉 쌓아놓고 개발중 확인, 이건 보안 문제 왕큼, 문제정보가 다 나옴, 나중에는 주석처리 해줘야함
			e.printStackTrace();
		}
		
	}////main
	static Date today; //클래스니까 null로 초기화
}////class
