package controlstatement03;

import java.io.IOException;

public class IFStatement02 {

	public static void main(String[] args) throws IOException {

		/*
		 * if문 기본형식2] -> 아래 if랑 else문이 하나의 명령문을 이룸, 하나로 통 if else문(if단독은 가능, else는 if 필요)
		 * 
		 * if(조건식){ // 해석: 만약 조건식이 참이라면
		 *  조건식이 참일때 실행할 명령문;
		 * }
		 * 
		 * else{ // else: "그 밖에" 라는 뜻, 참의 그 밖은 거짓뿐, 조건식이 거짓이면 엘스블락 실행, 경우의 수가 2가지일 때 사용 
		 * ex. 짝수거나 홀수거나, if문형식 첫번째로 쓰려면 2번연산, 이건 1번
		 *  조건식이 거짓일때 실행할 명령문;
		 * }
		 * 역시 실행할 명령문이 하나일때는 {} 생략가능
		 * 
		 */
		
		int num1=100; // 99로 바꾸면 둘 다 홀수로 나옴
		System.out.println("[if문 형식 첫번째로 짝/홀수 판단]"); // 조건식 2번 실행 구림
		if(num1 % 2 ==0) System.out.println("짝수");   //나머지 0 이면 짝수라고 실행
		if(num1 % 2 !=0) System.out.println("홀수");   //나머지 0 아니면 홀수라고 실행
		
		System.out.println("[if문 형식 두번째로 짝/홀수 판단]"); // 조건식 1번 실행 좋음, 경우의 수 2가지 일때 위보다 좋음
		if(num1 % 2 ==0) System.out.println("짝수");  
		else System.out.println("홀수");
		
		// main 메소드는 인터프리터로 int num1 부터 한줄한줄 읽으며 연산함. 세번째 연산시 num1이 100이니까 짝수라서 실행, 그 다음에는 !=0 아니니까 실행안됨
		// 1번 연산은 true로 짝수 실행되고나면 else는 짝수가 맞으니까 실행조차 안함. 위에는 짝 한번 홀 한번 2회 실행
		
		/*
		 * 삼항연산자
		 * if ~else문과 같다.
		 * 코드를 짧게 표현할 때 주로 사용 (if ~else문 대신에) 
		 * 
		 * -> 삼항연산자 쓰는 곳 2개 
		 * 1) 코드를 짧게 표현시 삼항연산자 사용 2) 문법적으로 if ~else를 쓸 수 없는 곳에서 삼항연산자를 쓰는 것 그냥 대체
		 * 
		 * 구문]
		 * 변수 = 조건식? 참일때 값 : 거짓일때 값 ;
		 *        항1     항2        항3
		 * 
		 * 조건식이 t/f로 나옴, 참일때 값을 : 전에 넣고, 거짓일때 값을 ; 전에 넣음
		 * 조건식이 참이면 : 이전 값만 남고, 변수로 삽입, 항상 = 오른쪽부터 계산!
		 * 
		 */
		
		System.out.println("[삼항연산자로 짝/홀수 판단]");
		
		// 1) if~else 코드(4줄)를 짧게 삼항연산자로 표현
		String result = num1 % 2 ==0  ?  "짝수" :  "홀수" ;  // "" 안의 값을 넣으니까 String 씀, 짝/홀수 판단 조건식이 참이니까 result에는 "짝수" 값이 들어감
		System.out.println(result); 
		
		// 2) 문법적으로 if 대신 삼항연산자 result가 된다는 것 위의 내용
		System.out.println(num1 % 2 ==0  ?  "짝수" :  "홀수");
		// 메소드의 인자로 if는 못 넣음, println 옆 괄호 안에 if 못쓴다는뜻, 3+4, 3>4 같은 연산은 쓸 수 있음, result도 삼항연산, 같은 연산이라 들어갈 수 있다는 뜻
			
		// if~else도 하나의 명령어이므로 이전에 if안에 if 들어가듯 가능
		System.out.println("[짝/홀수 판단후 짝수인 경우 100이상인지 판단 - if~else문]");
		if(num1 % 2 ==0) { // 짝수는 여기서 확정, {}는 지금 생략가능 if~else문은 하나의 명령문임
			if(num1 >=100) System.out.println("짝수이면서 100이상");
			else System.out.println("짝수이면서 100미만");
		}
		
		System.out.println("[짝/홀수 판단후 짝수인 경우 100이상인지 판단 - 삼항연산자]"); // 위에는 통으로보면 if문형식 첫번째, if~else문은 아님, 그래도 바꿀 수 있음
		System.out.println(num1 % 2 ==0 ? num1 >=100 ? "짝수이면서 100이상": "짝수이면서 100미만": "" ); // 맨끝 홀수이면 출력X, 그 아이디어로 명령문 씀 
		// 삼항연산자를 2번 쓸 수 있음, 자주 씀
		
		if(num1 % 2 !=0)// { // 100은 짝수니까 아래내용 출력안됨
			System.out.println("num1은 "+num1);
			System.out.println(num1 +"은 홀수");
		//} // if 내 조건문이 거짓이니까 밑에는 실행됨
		// else	System.out.println(num1 +"은 짝수");
		// 위에 {}중괄호 앞 //로 생략 시 if랑 연결되는건 둘 중 윗 문장뿐이라 else 위에 if랑 연결이 안돼서 에러나는 것, if는 else랑 짝을 이뤄야한다
		
		
		// 문] 한 문자를 입력받아 숫자인지 아닌지 if~else문과 삼항연산자를 이용하여 판단
			
		System.out.println("하나의 문자를 입력하세요?");
		int ascii = System.in.read();	// 입력한 문자의 아스키코드 나옴, system.in.read();만 쓰고 빨간줄, 수정 시 맨 위에 IOE 어쩌구랑 import랑 2개 쓰임
		
		System.out.println("[if~else문 버전]");
		boolean isNumber = ascii >= '0' && ascii <='9'; // 숫자입력시 && 왼쪽, 오른쪽 둘 다 트루
		if(isNumber) System.out.println("숫자입니다"); // t면 이거출력
		else System.out.println("숫자가 아닙니다"); // f면 이거출력
				
		
		System.out.println("[삼항연산자 버전]");
		String isNumber1 = ascii >= '0' && ascii <= '9'  ?  "숫자입니다" :  "숫자가 아닙니다" ;	
		System.out.println(isNumber1);
				 
		System.out.println(ascii >= '0' && ascii <='9' ? "숫자입니다": "숫자가 아닙니다");	// 선생님버전, 조건문 대신 boolean으로 된 isNumber 넣어도 됨
	
		
		System.in.skip(2); // 엔터 스킵
		System.out.println("다시 한 문자를 입력하세요?");
		char word = (char)System.in.read(); // 이번에는 사용자가 입력하는 값이 문자도 될 수 있으니 char에 담음, 이름은 word
		
		// 문] 숫자인지 판단 후 2의 배수를 판단, 2의 배수면 "2의 배수" 출력, 아니면 "2의 배수가 아님" 출력
		// 또한 숫자가 아니고 알파벳이라면 대소문자 판단 후, 대문자면 "다문자" 아니면 "소문자" 출력
		// if~else문 사용, 가정: 숫자나 알파벳만 입력 -> 경우의 수 2가지 숫자 or 알파벳, 능력되면 if~else 말고 삼항연산자도 해보기
		
		System.out.println("[if~else문 버전]");
		
		isNumber = word >= '0' && word <='9'; // 아까 불린에 저장한걸 word로만 바꿈
		if(isNumber) {//숫자라면
			if((word-'0') % 2 ==0) System.out.println("2의 배수");
			else System.out.println("2의 배수가 아님");
		}
		else {//알파벳이라면
			if(word >= 'A' && word <= 'Z') System.out.println("대문자");
			else System.out.println("소문자");
		}
		
		System.out.println("[삼항연산자]");
		
		System.out.println(isNumber?  // 바깥 if문
				(word-'0') % 2 ==0 ?"2의 배수" :"2의 배수가 아님": // 안쪽 if, else문
					word >= 'A' && word <= 'Z'? "대문자": "소문자" ); // 바깥 else문
		// 참일때도 if~else, 거짓일때도 if~else, 가독성은 안좋으나 한줄로 떨어짐, 위처럼 쓰는걸 추천, 그러나 메소드의 인자로 전달할때는 이걸로 해야함
		
	
	}////main

}////class
