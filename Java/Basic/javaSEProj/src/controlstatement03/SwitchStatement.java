package controlstatement03;

import java.io.IOException;
import java.util.Scanner;

public class SwitchStatement {

		// switch문: if문처럼 조건에 따라서 분기하는 제어문
		// if문은 범위(이산x, 99.11111 이런식으로 값이 연속적분포가능시)로 분기할 때 주로 씀
		// 범위가 아니고 특정 값(이산o ex. 3으로 나눠서 나머지가 0, 1, 2으로 딱 3가지값만 가능)이면 switch문
		// 형식:
		/*  정수식(산술식 -> t/f가 나오면 안됨): 연산의 결과값이 byte/short/char/int/String/Enum타입으로 
		       switch 괄호에 나오는 식 long형은 해당 안됨. -> 숫자가 너무 큼, 케이스를 따지기가 어려워서 그런듯
		       정수식은 산술식이거나 직접 변수를 정수식으로
		       사용하거나
		       예] num % 5 혹은 num 	      
		              
		  switch(정수식){ //스위치블락 필수, 정수식의 결과가 이산적
		   	case 값1 :  // 정수식의 가능한 값을 값1에 적어줌, 결과값이 값1이면 명령문이 실행, ex. 나머지 0이면 값1 =0 
		     	명령문1;
		     	....
		        break;  // 브레이크 필수, 안해줘도 에러는 안나지만 논리적으로 안맞는 결과가 나옴 ex. 6%3이면 값1=0이 답, 명령문1실행, 근데 브레이크 없으면 코드는 순차진행이라 명령문2도 실행됨
		    case 값2 :
		     	명령문2;
		        break;
		    ......
		    case 값n :  // 5%3이면 2가 맞으니까 명령문 3도 실행됨
		     	명령문n;
		        break;
		  
		    [default :          // 대괄호 생략가능, if문의 else랑 같은 것 값1~n이 아니면 default의 명령문이 실행됨
		     	명령문m;
		     	[break;]                           // 여기 브레이크는 굳이 필요x, 어차피 이거 실행 시 밖으로 나옴
		    ]                   // 대괄호 생략가능
		  }	스위치블락	 // 브레이크 만나면 스위치블락 밖으로 빠짐
		   
		  break문을 만나면 switch문 밖으로 빠져 나옴.
		    범위를 가지고 분기할때는 주로 if문을 사용하고
		    수식의 결과값이 이산적일때 switch문 사용		  
		 */
		

	public static void main(String[] args) throws IOException {
		
		// JDK 5.0부터 추가된 클래스: Scanner -> JDK 1.1, 1.2, 1.3 .. 1.5는 너무 급변해서 5.0이라고 부름
		Scanner sc = new Scanner(System.in);
		System.out.println("숫자를 입력하세요?");
		int inputNumber = sc.nextInt();         // read랑 동일, Int라 사용자가 숫자 입력하는 것, 입력전에는 진행x, 빨간색 블라킹상태, 숫자 아무거나 입력하면 밑에 출력 진행
		System.out.println("입력한 숫자:"+inputNumber);   
		
		System.out.println("[if문으로 나머지값 판단]");
		int remain = inputNumber % 3; // 위에 입력한 숫자는 inputNumber에 저장, remain = 나머지라는 뜻		
		
		if(remain ==0) System.out.println("나머지가 0");
		else if(remain ==1) System.out.println("나머지가 1");
		else System.out.println("나머지가 2");
		
		
		System.out.println("[switch문으로 나머지값 판단]");
		switch(remain) { //int remain = inputNumber % 3; 에서 inputNumber % 3; 쓰기 귀찮, remain int형이라 switch 내 사용 가능
			case 0:
				System.out.println("나머지가 0");
				break;                                 //   -> 얘랑 밑에 브레이크 // 처리하면 99 했을 때 나머지가 0, 나머지가 1, 나머지가 2 모두 출력됨
			case 1:
				System.out.println("나머지가 1");
				break;
			default:
				System.out.println("나머지가 2");
		}
		
		// switch문에는 정수식 즉 결과값이 byte/short/char/int/String/Enum타입 와야함, boolean값은 불가, 즉 비교식 논리식 불가, char short 계산 시 int
		
		//switch(inputNumber % 3 == 0) {} // [x] 비교식 안됨
		
		//switch(inputNumber % 3 == 0 || inputNumber >=0 ) {} // [x] 논리식 안됨
		
		byte b=1;
		switch(b) { // 결과값 위의 remain처럼 주로 변수로 담음, 옆에 b처럼 여튼 변수도 byte/short/char/int/String/Enum타입만 가능
			case 1:
				System.out.println("b에 저장된 값이 1");
			/* case 분기시 값 중복불가, 당연히 에러, 두개니까 b가 찾아갈 수 x
			case 2:
				System.out.println("b에 저장된 값이 2");	
			case 2:                                                  
				System.out.println("b에 저장된 값이 2");
			*/	
			
			// break가 없는 경우: 먼저 해당 case를 찾고, 순차적으로 다른 case의 명령문들도 모두 차례로 실행, 반드시 break 필요
				
			case 2:                                                  
				System.out.println("b에 저장된 값이 2");	
			case 5:                                                  
				System.out.println("b에 저장된 값이 5");	
			case 10:                                                  
				System.out.println("b에 저장된 값이 10");	       // break 안걸면 1, 2, 5, 10 모두 출력
		}
		
		long ln=5;
		//switch(ln) {} //[x] 롱형은 에러
		//switch(ln % 3) {} //[x] 롱%인트=롱 연산결과이므로 에러
		
		
		// 계산기 만들기
		System.out.println("첫번째 숫자 입력?");
		int fnum = sc.nextInt();
		System.out.println("연산자 기호(+,-,*,/) 입력?");
		char op = (char)System.in.read();                              
		System.out.println("두번째 숫자 입력?");
		int snum = sc.nextInt();
		System.out.printf("첫번째 숫자:%d,연산자:%c,두번째숫자:%d%n",fnum,op,snum);
		switch(op)	{	// switch 안에 char도 가능
			case '+': // char니까 싱글쿼트로 감쌈
				System.out.printf("%d + %d = %d%n", fnum,snum,fnum+snum);
			break;	
			case '-': // char니까 싱글쿼트로 감쌈
				System.out.printf("%d - %d = %d%n", fnum,snum,fnum-snum);
			break;	
			case '*': // char니까 싱글쿼트로 감쌈
				System.out.printf("%d * %d = %d%n", fnum,snum,fnum*snum);
			break;
			case '/': // char니까 싱글쿼트로 감쌈
				System.out.printf("%d / %d = %d%n", fnum,snum,fnum/snum);
			break;
			default:System.out.println("잘못된 연산자 기호입니다");
		}//////switch
		
		// string형도 JDK5부터 가능해짐
		System.out.println("수강하려는 과목은?");
		sc.nextLine();                            // nextLine 문자열을 반환함, 가져다대면 String 어쩌구 나옴
		String subject = sc.nextLine();
		switch(subject) { // switch 안에 subject -> 지금 string도 된다는게 중요
			case "국어": System.out.println("과목은 국어"); break;
			case "수학": System.out.println("과목은 수학"); break;
			default: System.out.println("개설되지 않은 과목입니다");
		}
		
		/*
		 * 여러가지 경우를 한번에 처리 가능함
		 *  case 값1:
		 *  case 값2:
		 *  case 값n: 실행문n; break;
		 *  값1, 값2, 값n  어떤 경우에도 위의 실행문n이 실행됨
		 */
		
		switch(inputNumber) { // 저기 위에 inputNumber 가져옴
			case 1:
			case 2:
			case 100:
			case 200:
				System.out.println("1이거나 2거나 100이거나 200중 하나");  // 사용자 1, 2, 100, 200 뭘 입력해도 이걸 출력, 1일 때도
				break;
			case 300:
				System.out.println("300입니다"); // 근데 300 입력 시 이걸 출력
			
		}
		
		// 90.00000001도 A, Switch 내에 double은 못 옴
		
		System.out.println("국어점수 입력?");
		int kor = sc.nextInt();
		
		System.out.println("영어점수 입력?");
		int eng = sc.nextInt();
		
		System.out.println("수학점수 입력?");
		int math = sc.nextInt();
		
		// 문] 평균을 구해서 90점 이상 "A학점" 출력, 80점 이상 "B학점" 출력, 70점 이상 "C학점" 출력, 60점 이상 "D학점" 출력, 60점 미만 "F학점" 출력하여라 
		// switch에 넣으려면 범위의 값을 이산적으로 딱딱 떨어진 값으로 만들면 됨
		
		int avg = (kor+eng+math)/3/10;     // int 시 9.9 등은 소수점 이하 잘려서 9가 됨
		switch (avg) {
			case 10: System.out.println("A학점");   // 올100을 생각 못함, 올100이 F될뻔
			//break;								   // 여기 브레이크 안해도 됨, 10이나 9나 A임
			case 9: System.out.println("A학점");
			break;
			case 8: System.out.println("B학점");
			break;
			case 7: System.out.println("C학점");
			break;
			case 6: System.out.println("D학점");
			break;
			default: System.out.println("F학점");
		}
		
		/* 선생님 코드
		switch (avg) {
		case 10:                                        // 올100을 생각 못함, 올100이 F될뻔 // 여기 브레이크 안해도 됨, 10이나 9나 A임
		case 9: System.out.println("A학점");break;
		case 8: System.out.println("B학점");break;
		case 7: System.out.println("C학점");break;
		case 6: System.out.println("D학점");break;
		default: System.out.println("F학점");
		*/	

		

		//}
		
	

	}/////////main

}///////////class
