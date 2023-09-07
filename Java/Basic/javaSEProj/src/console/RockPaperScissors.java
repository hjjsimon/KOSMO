package console;

import java.util.Scanner;

public class RockPaperScissors {

	// 상수정의
	public static final int SCISSORS=1;
	public static final int ROCK=2;
	public static final int PAPER=3;
	public static final int EXIT=9;

	
	public static void main(String[] args) {
		
		// https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Math.html
		/* static double
		random()
		Returns a double value with a positive sign, greater than or equal to 0.0 and less than 1.0.
		*/
		// math. random 어쩌구 홈페이지 들어간걸 가져다쓰면 0.0<= x <1.0 사이의 소수 더블값을 랜덤으로 나한테 줌, 이게 컴퓨터
		// 소수 * 10 하면 더블*인트=더블로 0.xxxx, 1.xxxx~ 9.xxx 나옴, int 처리 시 내림으로 0~9 나옴, +1 처리 시 1~10 나옴
		System.out.println((int)(Math.random()*10)+1); // Math. random() 을 실행시키면 위 설명
		
		/*
		 * Math클래스의 random()메소드:
		 * 0.0사이에서 1.0사이의 double형값을 무작위로 발생시켜주는 메소드(단,1.0은 미 포함)
		 *
		 * 1) 특정 범위의 숫자를 랜덤하게 발생 시키려면
		 * (int)(Math.random()*차이값)+시작값              -> 아까 10 곱함, 아까 1~10이면 10-1이 차이값이라 9인데 왜 10? 
		 * 단,끝값은 포함 안됨							// 차이값에 9 시작값에 1 넣으면 끝값(지금10)이 안나옴, 끝값 포함시키려면 차이값 9에 +1 해야함
		 * 
		 * 2) 끝값을 포함 시키려면
		 * (int)(Math.random()*(차이값+1))+시작값
		 *
		 * 예] 5부터 15사이의 숫자를 랜덤하게 발생시키려면
		 * 차이값: 15-5 =10;
		 * 시작값: 5
		 * 끝값:15
		 *
		 * (int)(Math.random()*10)+5 : 단,15는 발생 안됨
		 *
		 * 끝값도 발생시키려면
		 * (int)(Math.random()*11)+5
		 */
		
		// 4~8사이의 숫자 랜덤하게 발생시키자, 시작값:4, 끝값:8, 차이값:4
		System.out.println((int)(Math.random()*5)+4);    
		
		// 가위바위보
		/*
		System.out.println((int)(Math.random()*3)); // 0, 1, 2 세개만 나옴
		
		if((int)(Math.random()*3)==0); System.out.println("가위");
		if((int)(Math.random()*3)==1); System.out.println("바위");
		if((int)(Math.random()*3)==2); System.out.println("보");
		*/
		
		
		Scanner sc= new Scanner(System.in); // 처음부터 반복하려고하지말고, 한번 만들고 반복시키기
		
		while(true) {      // 전체 반복세팅 계속 타자만 치게함
		
		
		//1] 메뉴 출력
		System.out.println("===============[메뉴 번호]===============");
		System.out.println("        1.가위 2.바위 3.보 9.종료");
		System.out.println("=======================================");

		//2] 컴퓨터는 숫자를 랜덤하게 발생시킨다
		int computer = (int)(Math.random()*3)+1;
		//System.out.println("컴퓨터:"+computer); -> 1, 2, 3 지정했으니까 생략
		
		//3] 사용자 숫자 입력 받기
		System.out.println("메뉴 번호를 입력하세요?");
		int user = sc.nextInt();
		//System.out.println("사용자:"+user); -> 안씀 구림
		
		
		if(user==EXIT) {
			System.out.println("다음에 또 즐~"); // while문 다음에 넣어도 됨
			break;
		} // user가 9 상수치면 브레이크
		
		
		//4] 게임 승리여부 판단 -> main 위에 상수를 정의함, final 붙으면 상수 / 사용자는 이기거나 지거나 비기거나 경우의 수 3가지 -> else if 써야함
		
		// 1, 2, 3 지정
		System.out.printf("[당신:%s, 컴퓨터:%s]%n",
				user==SCISSORS?"가위":user==ROCK?"바위":"보", // 사용자 삼항연산자 사용
				computer==SCISSORS?"가위":computer==ROCK?"바위":"보"); // 삼항연산자 안에 삼항연산자 쓴 것
		
		if(user==SCISSORS && computer==PAPER ||
		   user==ROCK && computer==SCISSORS	||
		   user==PAPER && computer==ROCK)
		   System.out.println("당신이 이겼어요");
		else if (user==computer) System.out.println("비겼어요");
		else System.out.println("당신이 졌어요");
		
		// 한번 세팅끝내면 반복은 쉬움, 메뉴부터 여기까지 통으로 while 반복
		
		} /////////while
		
		/*
		 * 배열은 참조형(Reference type), 기본자료형이 아님, 메모리구조와 데이터저장 장소 다 틀림
		 */
		
		
	}///////////////main

}///////////////class
