package controlstatement03;

import java.io.IOException;
import java.util.Scanner;

public class IFStatement03 {

	/*
	  if문 기본형식3]		                        // 경우의 수가 여러개면 실시하는 기본형식
	  if(조건식1){                               // if 뒤 조건식은 무조건 t,f 로 결과 나와야함
	  	조건식1이 참일때 실행할 명령문1
	  }
	  else if(조건식2){                          // else와 if 사이에 한칸 띄워야함, 사실 else문인데 if가 더 있는것, 지금 else와 연결된 if는 위의 if, if 참이면 그냥 그대로 끝
	  	조건식2이 참일때 실행할 명령문2
	  }
	  else if(조건식3){
	  	조건식3이 참일때 실행할 명령문3			    // 지금 else와 연결된 if는 조건식2 앞의 if , 조건식 1, 2 거짓이고 조건식 3이 참이면 실행
	  }
	  else if(조건식4){
	  	조건식4이 참일때 실행할 명령문4
	  }
	  ..........
	  ...............
	  else if(조건식 n){
	  	조건식n이 참일때 실행할 명령문n
	  }
	    [                                           // 대괄호는 생략가능, 문제에 따라 else는 추가될수도 안될수도
	      else{
	    	모든 조건식이 거짓일때 실행할 명령문else      // 위에 조건식 다 거짓이면 실행
	     }
	    ]		 
	 */
	
	public static void main(String[] args) throws IOException {
		
		int kor=99, eng=80, math=89;
		double avg= (kor+eng+math)/3.0; // A, B, C, D, F 학점 매기기
		
		if(avg >=90) System.out.println("A학점");
		else if(avg >= 80) System.out.println("B학점"); // else if (avg < 90 && avg >=80)  앞에 avg < 90 && 필요 X
		else if(avg >= 70) System.out.println("C학점");
		else if(avg >= 60) System.out.println("D학점");
		else System.out.println("F학점");
		
		
		System.out.println("하나의 문자를 입력하세요?");
		
		char word = (char)System.in.read();
		
		// 문] 숫자인지 판단 후 2의 배수를 판단, 2의 배수면 "2의 배수" 출력, 아니면 "2의 배수가 아님" 출력
		// 또한 숫자가 아니고 알파벳이라면 대소문자 판단 후, 대문자면 "대문자" 아니면 "소문자" 출력
		// if~else if()만 이용, 특수문자 입력 시 출력이 안돼야함
		
		/* 내 풀이 틀림
		if((word-'0') % 2 ==0 ) System.out.println("2의 배수");  
		else if(word-'0' % 2 !=0 ) System.out.println("2의 배수가 아님");
		else if(word >='A' && word <= 'Z') System.out.println("대문자");
		else if(word >='a' && word <= 'z') System.out.println("소문자");
		else System.out.println("");
		*/
		
		if(word >='0' && word <='9' && (word-'0') % 2 ==0) System.out.println("2의 배수");
		else if(word >='0' && word <='9' && (word-'0') % 2 !=0) System.out.println("2의 배수가 아님");
		else if(word >='A' && word <='Z') System.out.println("대문자");
		else if(word >='a' && word <='z') System.out.println("소문자");   // else if 써줘야함, else 처리하면 특수문자도 소문자라고 나옴
		
		// 문] 위에서 입력받은 문자가 숫자이면 "숫자", 알파벳이면 "알파벳", 숫자도 알파벳도 아니면 "기타"
		
		if(word >='0' && word <='9') System.out.println("숫자");
		else if(word >='A' && word <='Z' || word >='a' && word <='z') System.out.println("알파벳");
		else System.out.println("기타");
		
		// 문] (종합) 세 숫자 중 최대 값을 구하는 로직을 작성하자 (if문 형식 3가지 중 아무거나 사용 가능)
		// 첫번째, 두번째, 세번째 각각 제일 큰 값으로 3번 테스트해봐야함, 그리고 가장 큰 숫자가 나와야함
		
		Scanner sc = new Scanner(System.in);
		System.out.println("첫번째 숫자를 입력하세요?");
		int num1 = sc. nextInt();                     // 입력받은 숫자를 num1에 저장
		System.out.println("두번째 숫자를 입력하세요?");
		int num2 = sc. nextInt();
		System.out.println("세번째 숫자를 입력하세요?");
		int num3 = sc. nextInt();
		
	 	System.out.printf("num1:%d,num2:%d,num3:%d%n",num1,num2,num3);
		
		
		// 메모리가 num1, num2, num3 3개가 만들어짐, 각각 어떤 숫자가 저장되는지는 모름
		// 그래서 변수를 하나 만들 것, max라는 이름의 변수를 만들어서 num1의 값을 복사, 그리고 max랑 num2 대소비교, num2가 더 크면 max값을 num2로 바꿀 것, num3도 똑같이 추가시행
		
		int max=num1;
		if(max < num2) max = num2;  // 조건문 참으로 max가 더 작으면 max에 num2를 넣는것
		if(max < num3) max = num3;  // 조건문 참으로 max가 더 작으면 max에 num3를 넣는것
		System.out.println("최대값:"+max);
		
		/*
		if (num1 > num2 && num1 > num3); System.out.println(num1);
		else if (num2 > num1 && num2 > num3); System.out.println(num2);
		else System.out.println(num3);
		*/
	 	
	 	if (num1 > num2 && num1 > num3); System.out.println(num1);
	 	if (num2 > num1 && num2 > num3); System.out.println(num2);
	 	if (num3 > num1 && num3 > num2); System.out.println(num3);
		
		
		
		
		

	}////////////main

}/////////class
