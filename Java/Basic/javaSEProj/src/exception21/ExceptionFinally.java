package exception21;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
finally절:예외가 발생하든 안하든 반드시 실행 하고자하는 명령문들을 기술.
1) try ~catch절
  -예외 직접 처리
2) try ~catch~finally절 -> 에러나면 캐치->파이널리/ 에러안나면 트라이->파이널리
	-예외 직접 처리후 반드시 실행할 문장도 처리(DB연결 끊어주는것 등 finally에 넣어줌)
3) try ~finally절
	-예외는 던지고(throws) 예외가 발생하든 안하든 반드시 실행할 문장 처리
※  finally절안에 있는 명령문은 return문을 만나더라도 실행됨(return으로 메소드빠져나가도), 단,System.exit(0)를 만나면 프로그램 종료되므로 당연히 실행안됨.

   [1.외부 자원 사용시 발생하는 예외 클래스들(컴파일 예외) 처리 방법]
   1-1. 직접 처리하지 말고 던지자, 해당 메소드명 옆에 throws 예외클래스명
   1-2. 예외를 개발자가 직접 처리 try~catch절 이용
    try{
            예외가 발생할 만한 코드
    }
    catch(예외클래스 인스턴스변수){
          예외발생시 catch절에서 처리
    }
    	※try는 단독으로 못쓰고 try~catch 혹은 try~finally절 혹은 try~catch~finally의 쌍으로 사용한다.
   	※런타임예외는 던져봤자 의미 없다. 반드시 try~catch절로 직접 처리해야 한다
  	※main메소드에서는 런타임 예외는 반드시 try~catch 컴파일예외는 던지거나 try~catch해도 됨.
  */
public class ExceptionFinally {
	//컴파일 예외 발생하는 메소드]
	static void compile() throws IOException {
		System.out.println("한 문자를 입력하세요?");
		//방법1]예외를 던진다->throws IOException
		int ascii=System.in.read(); //코드를 호출한 위 메소드 compile()에 IOException으로 throw됨 
		System.out.println("입력한 문자:"+(char)ascii);
		//방법2]직접 try~catch로 처리(다른 메소드에서 예외처리 불필요)
/*		try {
			int ascii=System.in.read(); 
			System.out.println("입력한 문자:"+(char)ascii);	
		}
		catch(IOException e) {
			e.printStackTrace(); -> try/catch 누르면 자동 생성
*/		}

	static void tryFinally() throws IOException {//위에서 IOExceptiopn 던짐, 지금 compile()도 던지던가 트라이/캐치 해야함
		try {
			compile();//컴파일함
		}
		finally {
			System.out.println("반드시 실행할 명령문");
		} 	
	}
	//런타임 예외 발생하는 메소드]
	static void runTime() {
		Integer.parseInt("백억원");
	}
	static void tryCatchFinally() {
		Scanner sc=new Scanner(System.in);
		int age=-10;
		try {
			System.out.println("나이를 입력하세요?");
			//int age=sc.nextInt();
			age=sc.nextInt();
			//System.out.println("당신의 10년후 나이:"+(age+10));
			//return;//정상적으로 숫자 입력시 finally절 실행됨, nextInt에러 안나면 아래 return 실행, 원래 이거하면 메소드 빠져나감, 근데도 finally는 실행됨/에러나면 return실행x, 캐치로감
			System.exit(0); //정상적으로 숫자 입력시 finally절 실행 안되고 프로그램 종료
		}
		catch(InputMismatchException e) {//숫자 아닌거 입력시 nextInt에러, 대입연산코드 +10 안되고 바로 캐치절로 들어옴, 근데 반드시 age+10 출력하고 싶으면 finally씀
			System.out.println("나이는 숫자만...");
		}
		finally {//예외무관 무조건 출력
			System.out.println("당신의 10년후 나이:"+(age+10)); //try블락 안에서 선언된 age 지역변수라 에러남, 에러나면 10년후 나이 0 되도록 초기값을 -10 세팅함  
		}
	}
	
	public static void main(String[] args) /*throws IOException*/ {//compile 메인메소드에서 쓰면 또 던져야함
		//컴파일 예외는 던지거나 try~catch하거나]
		//1]던지거나
		//compile();
		//2]try~catch하거나
		try {
			compile();
		} catch (IOException e) { //IOException, Exception 뭘로 잡아도 됨 -> 방법2로 compile()에서 예외를 던지지 않도록 try~catch를 해버리면 여기바로위 코드에서 예외가 안나오니 지금 코드 캐치빨간줄, 캐치할게 업승ㅁ
			e.printStackTrace();
		}
		
		//tryFinally(); //던져도 되고 트라이캐치도 되고
		
		//런타임 예외는 반드시 try~catch
		try {
			runTime();
		}
		catch(Exception e) {
			System.out.println("숫자형식이 아닌 예외:"+e.getMessage());
		}
		tryCatchFinally();
	}

}
