package datatype01;

public class StringType {

	public static void main(String[] args) {
		
	// String형: 참조형-기본자료형이 아님, 문자형을 저장할 수 있는 데이터타입, 자바에서 문자열 나타낼 때 "" 씀
	// +는 숫자연산에서는 더하기, 지금 문자열에 사용될 때는 문자열 연결을 의미
	// 문자열 + 숫자는 문자열 -> 자바, 파이썬은 에러남
	// 참조형과 기본자료형 사이에 형변환(Type Casting) 불가
		
	int number; //변수 선언
	number = 99; //변수 초기화
	//String strNumber = (String)number; // number가 int라서 String으로 담을 수 없다고함 형변환(cast) 필요 그래도 안됨, 참조형 기본자료형이라 형변환 불가
	//int initNum = (int)"100"; // ""안에 있으면 스트링형 상수, int 씌워도 캐스트 안됨 형변환 불가
	String strNumber="100";
	System.out.println(strNumber+number); //strNumber는 String 타입, number는 int, 지금 문자열 100+ 숫자99 라서 문자열100에 +연결99 됨
		
	//1]new 연산자를 사용해서 문자열 저장
	String newString = new String("new 연산자 사용"); // 참조형은 데이터 저장하려면 new 연산자 써야하는데 문자열 많이 쓰니까 기본자료형처럼 쓰게 "100"처럼 함
	System.out.println(newString);
	
	//2]기본자료형처럼 문자열 저장(new 연산자 미사용)
	String stringLikeBasic="기본 자료형처럼 문자열 저장"; //기본자료형처럼 상수값 대입함
	System.out.println(stringLikeBasic);
	
	//3]+가 문자열에 사용 시 연결 의미
	String plusString;
	plusString=newString+","+stringLikeBasic; // +","+ 가 센스
	System.out.println(plusString); // 위의 2개 연산자를 +로 연결해서 출력
	
	int kor=100,eng=100,math=100;
	System.out.println("총점:"+kor+eng+math); // 총점 300 아니고 100 100 100 으로 나옴
	// kor까지 총점:100에 +100하면 총점100100 이런식으로 됨, 문자열인 총점부터 시작해서 그럼
	// 괄호로 묶으면 괄호가 우선순위가 높으니까 괄호부터 계산해서 300이 나옴
	System.out.println("총점:"+(kor+eng+math));
	
	
	
	
	
	
		
	
	}//main

}//class
