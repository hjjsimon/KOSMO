package wrapperclass10;

import java.util.Scanner;

//https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Integer.html
//패키지를 묶은게 모듈, Module java.base
//Package java.lang
//Class Integer
//Integer클래스의 필드 -> MAX_VALUE 이런 것들이 있음
//Integer num1Obj=new Integer(200); -> 이거 하지말고 그냥 Integer num1Obj=200; 하면됨, 나중에 에러날 수 있으니까 쓸데없이 하지말라고, 그냥 귀찮은거 줄여준 패치
//static 정적 메소드 있으면 클래스명으로 접근(인스턴스화필요x), static 없으면 인스턴스변수로 접근

public class WrapperClass02 {

	public static void main(String[] args) {

		//[Integer Wrapper 클래스의 주요 메소드]
		//1] 숫자형식의 문자열을 숫자로 변경 (ex. "1004" -> 1004로 바꾸고싶음)
		// static int parseInt(String s); -> 반환타입이 int, .찍어도 뭐가 없음
		// static Integer valueOf(String s); ->반환타입이 참조형 Integer, 래퍼클래스, .찍으면 뭐가 있음
		
		String strNumber="1000";//문자열 -> 숫자형 더하면 문자+숫자 -> 문자열 
		System.out.println("10+strNumber:"+(10+strNumber));//문자열 101000, 그대로 더해짐
		System.out.println("strNumber를 숫자로 변경(parseInt):"+(10+Integer.parseInt(strNumber))); //반환타입이 int, 1010나옴
		System.out.println("strNumber를 숫자로 변경(valueOf):"+(10+Integer.valueOf(strNumber))); //결과 1010동일, 근데 엄청난 차이
		// parseInt는 int반환, str괄호 끝나고 . 찍으면 아무것도 없음, 밑에는 .찍으면 Integer반환이라 멤버 뜸
		
		Scanner sc=new Scanner(System.in);
		System.out.println("나이를 입력하세요?");
		//첫번째] nextInt()받는 경우 -> 숫자입력시 nextInt
		//int age=sc.nextInt(); //사용자가 입력한 int 숫자로 그대로 반환 -> 숫자아닌 열살 입력 시 InputMismatch 런타임에러
		//System.out.println("당신의 10년후 나이:"+(10+age));
		//sc.nextLine(); -> string으로 받으려면 밑에처럼
		//String strAge=sc.nextLine();
		//System.out.println("당신의 10년후 나이:"+(10+strAge)); //스트링(문자열)으로 반환하니까, 10에 20 문자열 연결, 1020됨
		//System.out.println("당신의 10년후 나이:"+(10+Integer.parseInt(strAge))); //이제 20쓰면 문자열->숫자 돼서 제대로 계산 10+20=30
		
		Integer numObj=Integer.parseInt(strNumber); //문자열을 Int로 바꿨는데 Integer로 바뀜, 여기가 int->integer 이게 오토박싱, 메소드 쓸 수 있게됨
		System.out.println("문자열 1000을 byte형 값으로 변경(parseInt):"+numObj.byteValue());
		//System.out.println("문자열 1000을 byte형 값으로 변경(valueOf):"+numObj.byteValue());
		//static Integer valueOf(String s); 반환타입이 인티저면 오토박싱할 필요 없음, numObj 지우고
		System.out.println("문자열 1000을 byte형 값으로 변경(valueOf):"+Integer.valueOf(strNumber).byteValue());
		//인티저하고, 밸류오브, 바이트벨류, 인티저타입에서 계속~ 메소드체인 이라고함, 연달아 바꿈
		
		String money="일억원";
		//Integer.parseInt(money); //[x] 실행 시 numberformatexception, 숫자형식 아니니까 에러 -> Integer . parse int = Integer를 int 타입으로 분석하다
		//Integer.valueOf(money); //[x] 위와 동일, 숫자형식 아니라 에러
	
		String floatString="3.14"; //
		//Integer.parseInt(floatString); //[x] 실행 시 numberformatexception, 숫자형식 아니니까 에러
		System.out.println(Double.parseDouble(floatString));//parseDouble이니까 double로 반환 -> 실수 3.14로 바꿔줌/ 그냥 Integer->int 처럼 Double->double도 됨
		System.out.println(Float.parseFloat(floatString));//실수 3.14로 바꿔줌
		
		//2]숫자를 문자열로 변경:
		//static String toString(int i) -> int 숫자 인자를 받아서 String으로 반환, static은 인자를 받은 값으로 작업할 때 씀
		//String toString() -> String으로 반환
		int num2=1000;
		//num2에 저장된 숫자를 문자열로 변경
		//2-1]정적 메소드 사용 -> static String toString(int i)부터 써보기
		System.out.println(Integer.toString(num2)+10); 
		//2-2]인스턴스형 메소드 사용 -> String toString() 써보기
		Integer intObj=num2; //오토박싱
		System.out.println(intObj.toString()+10);
		
		//3]2진수나 8진수나 16진수형식의 문자열을 십진수로 바꿀 때 사용하는 메소드
		//static int parseInt(String s, int radix)
		//static Integer valueOf(String s, int raidx)
		//위의 strNumber "1000"을 2진수로, 8진수로, 16진수로 보면 2^3, 8^3, 16^3 으로 볼 수 있음
		//매개변수 2개짜리 써야함, radix가 진법 의미 -> 2진수의 형태를 parseInt(10진수)로 바꾸겠다는 뜻
		System.out.println("2진수형태의 문자열 1000을 십진수로:"+Integer.parseInt(strNumber, 2)); 
		System.out.println("8진수형태의 문자열 1000을 십진수로:"+Integer.parseInt(strNumber, 8)); 
		System.out.println("16진수형태의 문자열 1000을 십진수로:"+Integer.parseInt(strNumber, 16)); 

		//4]십진수를 각 진수로 변환
		//static String toBinaryString(int i) :2진수형태의 문자열
		//static String toOctalString(int i) :8진수형태의 문자열
		//static String toHexString(int i) :16진수형태의 문자열
		System.out.println(Integer.toHexString(15)); //15를 16진수로 바꾸면 f
		
		//결론]
		//1)숫자형식의 문자열을 숫자로: parse계열 메소드
		//static int ParseInt();
		
		//2)valueOf()계열
		//static Integer valueOf(int i)
		//int->Integer
		//위는 jdk5.0이후부터 의미 없음, 오토박싱쓰면 됨
		
		//3)숫자를 문자열로: toString()계열
		//static String toStirng(int);
		//String toString()
		//[1]번과 [3]은 기억
		
		
		
		
		
	}////////main

}////////////class
