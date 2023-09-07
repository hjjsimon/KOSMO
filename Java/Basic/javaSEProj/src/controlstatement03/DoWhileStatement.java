package controlstatement03;

import java.io.IOException;
import java.util.Scanner;

public class DoWhileStatement {

	public static void main(String[] args) throws IOException {
		
		// 선생님은 dowhile 안씀 while로 대체
		/*
		do ~ while() 문: for, while 같은 반복문, 반드시 한번은 실행하고 나서 반복하고자 하는 경우 사용
		for, while 초기식에 바로 반복조건이 안맞으면 내부 명령문 실행x
		
		
		형식:
		[초기식]
		do{	
			 	[초기식] // [x] 여기에 넣으면 반복조건 참이면 초기식 계속 실행, 초기식은 한번만 실행돼야하므로 맨 위에 둬야함
			 	명령문들;  //do 블락 내 명령문이 실행, do블락내 명령문 끝나고난 후 반복조건을 따짐, 반복조건 참이면 do블락 처음으로 와서 실행
			 	[증감식] // 명령문 끝나면 증감
		
		}while(반복조건);  //끝에 반드시 ; 붙여라, 여기가 do while문의 끝이라고 지정하는 것  
			 
		무한루프]	
		do{
		
		}while(true); //true 넣으면 무한루프
		 
		 */
		
		//1부터 10까지 누적합: do~while문 사용
		int sum=0;
		int i=1; //초기식
		do {
			sum+=i;
			i++;
		}while(i<=10); // i가 10이하일동안 반복
		System.out.println("1부터 10까지 누적합:"+sum);
		
		// 무조건 1명의 점수를 입력받아 성적처리, 더 입력받고 싶으면 아무키나 누르고, 종료하고 싶으면 'X'나 'x' 클릭, 그러면 프로그램 종료
		
		Scanner sc = new Scanner(System.in);
		int kor, eng, math, exitCode;
		/*
		  do {
		
			//점수입력받는부분
			System.out.println("국어점수 입력?");
			kor=sc.nextInt();
			System.out.println("영어점수 입력?");
			eng=sc.nextInt();
			System.out.println("수학점수 입력?");
			math=sc.nextInt();
			
			//학점 즉 결과출력
			int avg=(kor+eng+math)/3/10;
			switch(avg) {
				case 10:
				case 9: System.out.println("A학점"); break;
				case 8: System.out.println("B학점"); break;
				case 7: System.out.println("C학점"); break;
				case 6: System.out.println("D학점"); break;
				default: System.out.println("F학점");

			}
			//추가 입력여부 확인하기
			System.out.println("계속 입력하시려면 아무키나\r\n종료하시려면 'x'나 'X'를 누르세요");
			exitCode=System.in.read(); //사용자가 입력한건 exitCode에 들어감
			
						
		}//while(true); //do블락 끝나면 반복조건으로옴, 근데 반복조건이 true면 무한루프, 안끝남 다시 맨위로 do에 가서 "국어점수입력?" 나옴
		while(!(exitCode=='x'||exitCode=='X')); // x입력시 false 되니까 밑에칸으로 나옴, 아무것도 안나옴
		
		*/
		
		// 위의 do~while문을 while문으로 변경
		
		while(true) { //true니까 무조건 안으로 들어옴
			//점수입력받는부분
			System.out.println("국어점수 입력?");
			kor=sc.nextInt();
			System.out.println("영어점수 입력?");
			eng=sc.nextInt();
			System.out.println("수학점수 입력?");
			math=sc.nextInt();
			
			//학점 즉 결과출력
			int avg=(kor+eng+math)/3/10;
			switch(avg) {
				case 10:
				case 9: System.out.println("A학점"); break; // break는 switch나 반복문에서 빠져나올때, continue는 반복을 계속할때
				case 8: System.out.println("B학점"); break;
				case 7: System.out.println("C학점"); break;
				case 6: System.out.println("D학점"); break;
				default: System.out.println("F학점");
				// 여기까지 true니까 무조건 실행
			}
			//추가 입력여부 확인하기
			System.out.println("계속 입력하시려면 아무키나\r\n종료하시려면 'x'나 'X'를 누르세요");
			exitCode=System.in.read(); //사용자가 입력한건 exitCode에 들어감
			if(exitCode=='x'||exitCode=='X') break; // x나 X치면 break로 빠져나가 밖으로 나가게함
		}
		System.out.println("성적처리하느라 고생하셨습니다");
		
		
		
		
		
		
		
	}///////////////////////// main

}//////////////////////// class
