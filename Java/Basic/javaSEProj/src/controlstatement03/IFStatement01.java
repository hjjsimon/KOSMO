package controlstatement03;

import java.io.IOException;

public class IFStatement01 {

				//분기문(조건문):프로그램 흐름상  분기하고자 할때 사용하는 제어문
				//if문/switch문 두 가지가 있다.
				//if문 기본 형식 1]
				/*
				 조건식은 비교식 또는 논리식이어야 한다. -> 무조건 t나 f가 나오던가 직접 넣어도 됨
				 즉 결과값이 true 아니면 false가 나오는 식이거나
				 직접 boolean값(true,false)을 줄 수 있다.
				 if(만약 ~조건식이 참이~ 라면) -> t이면 if 블락 내의 명령문이 실행 / f면 그 아래로 내려가서 다른 명령문이 실행
				 
				 
				 ------------------------------------------이 아래 틀은 무조건 암기,
			
								 
				 if(조건식)//만약(if) 조건식이 참이라면         // if(괄호) <- 괄호내에는 조건식! 반드시 t,f로 결과 나와야함, int num=1 이런 선언은 불가
				 {////if블락 시작
				 	 조건식이 참일때 실행할 명령문;	
					
				 }////if블락 끝		 	
				
				 
				 조건식이 참일때 실행할 명령문이 하나이면 if 블락을  ->  {}  생략가능하다
				 {}는 실행문들을 하나로 묶는 블락역할을 한다.		
				*/
		
		
		public static void main(String[] args) throws IOException { //main 내 코드가 실행되면 끝남
		
			int num1 = 10;
			
			//1] 결과값이 boolean값이 아닌 식은 조건식으로 불가
			
			//if(num1 % 2) {} // [x] if 내에 값으로 0 나옴, t나 f가 아님 안됨, 0으로 int값. boolean값으로 t나 f 필요. 에러뜸
			//if(num1) {} // [x]
			
			//2] 조건식은 비교식 아니면 논리식 혹은 boolean 값(t나f) 이어야한다 , 비교식 논리식은 결과가 t나 f
			if(num1 % 2 ==0) {  //비교식으로 넣어줌 -> 안에가 참이면 아래 명령어가 실행됨
			 System.out.printf("%d는 짝수%n", num1);
			}
		
			if(num1 % 2 !=0) {  // 비교식, 0은 0이 아니다? false. 거짓이니까 아래 내용 실행 안되고 그 밑에 프로그램 종료 명령어가 실행됨
				System.out.printf("%d는 홀수%n", num1);
			}
			
			if(num1 % 2 == 0 && num1 >= 10) { //논리식, 양쪽 t, t 니까 &&에 맞아서 출력이 됨
				System.out.printf("%d는 짝수고 10보다 크거나 같다%n", num1);
			}
			
			if(true)  // 굳이 쓸 필요 없음, 이렇게는 안씀, 그리고 명령문1개는 중괄호 생략가능, 선생님은 이럴 때 그냥 한 라인에 써버림
				 System.out.println("항상 실행되는 명령문");
				
			
			if(false) System.out.println("항상 실행 안되는 명령문");  // 절대 출력되지 않는 명령문
			
			if(num1 % 2 != 0)   ;  // <- 여기 세미콜론 하면 안됨, 아래 블락은 if와 무관한 명령문, ; 왼쪽 빈칸 명령문 필요함
			
			// 아래 블락{}는 위의 if문과 연결x, 전혀 관련없이 무조건 실행된다. 즉 위의 if문에 연결된 if블락이 아니다
			
			{
				System.out.printf("%d는 홀수%n", num1);
			}
			
			/*
			 * 3] 수행할 명령문이 여러개인 경우 {} 생략하면 의도하지 않은 결과를 초래할 수 있음
			 * 고로 조건식이 참일 때 수행 할 명령문이 여러개인 경우 반드시 {} 묶어라		
			 */
			
			
			if(num1 % 2 != 0)  //{ //num1은 10 저장돼있음, 0은 0이 아니다? 거짓, 밑으로 내려감, 2개 명령문 안됨
				System.out.printf("num1은 %d다%n", num1);
				System.out.printf("%d는 홀수다%n", num1);
			//}
			
			// -> 위에 괄호에 //{ //} 로 블락을 생략해버려서, 다음으로 첫번째로 오는 명령문만 if에 연결되는 것, 실행할 명령문이 여러개면 반드시 한 블락으로 묶어야함
			
			/*
			 * System.in.read()메소드: 사용자 입력을 받을때까지 블락상태가 된다. 다음코드 실행X. in이라 출력x. 사용자가 입력하는 것
			 * 입력한 문자의 아스키코드 반환
			 */
			
			//  System.out.println("95:"+(char)95);   95는 ^(꺽쇄) 라고 나옴 	
			//	System.out.println("94:"+(char)94);    이걸 쓰면 94가 무슨 문자인지 나옴 Z랑 a 사이
			//	System.out.println("93:"+(char)93);   93은 ] 라고 나옴 
				
				
			System.out.println("1. 하나의 문자를 입력하세요?"); // 질문을 하는 것 
			int asciiCode=System.in.read();	               // 사용자가 입력한 값을 int에 담음
			System.out.println("사용자가 입력한 값(아스키 코드값):"+asciiCode); // 입력한 값을 int 그대로 변수이름 아스키코드로 출력
			System.out.println("사용자가 입력한 값(문자):"+(char)asciiCode);  // 입력한 값을 아스키코드에 맞는 문자(char)로 출력, 치는대로 출력됨 ex. A면 A.
			// 밑에 초록색으로 A치고 엔터치면 결과값 나옴
					
			// main 옆에 throws IOException -> 가 추가됨 (첫번째 해결방법 클릭)
			// read는 사용자한테 입력을 받는 코드, 이게 입력 안되면 진행을 Block 시킴, 입력 받을 때까지 블락, 아래 빨간불 들어와서 컴터 안꺼짐
		
			// 사용자가 입력한 문자가 숫자인지 아닌지를 판단하자 -> 아직 사용자가 숫자쓸지 아닐지 모르는 상황
			
			/*
			1] 아스키 코드 값을 알 때 (0 아스키코드=48)
			boolean isNumber = asciiCode >= 48 && asciiCode <= 57; // 사용자가 2 입력시 아스키코드 50, 48보다 크고 t, 57보다 작으니 t, 최종 t 
			사용자가 A 입력시 65이므로 t, f 각각 나와서 최종 f
			if(isNumber) System.out.println("입력한 문자는 숫자다");
			if(!isNumber) System.out.println("입력한 문자는 숫자가 아니다");
			isNumber에 false 저장 시, 밑에껀 f가 아니니까 t가 됨, 즉 if(true)라서 실행됨 -> "입력한 문자는 숫자가 아니다" 출력
	
			2] 아스키 코드 값을 모를 때 (''로 문자를 입력)
			boolean isNumber = asciiCode >= '0' && asciiCode <= '9'; 
			if(isNumber) System.out.println("입력한 문자는 숫자다");
			if(!isNumber) System.out.println("입력한 문자는 숫자가 아니다");
			*/		
			
			/* 문제] 사용자가 입력한 문자가 알파벳이거나(or) 숫자이면
			 * "알파벳 혹은 숫자"라고 출력하고 아니면 "기타"라고 출력하여라
			 * (아스키코드값 모른다고 가정) else문 불가
			 * 참고로 영문 알파벳의 아스키 코드값은 대문자 A(65)~Z(90), 소문자 a(97)~z(122) -> 91~ 96은 알파벳이 아닌데 뭐지?
			 */
			
			boolean isAlphabetOrNumber = asciiCode >= '0' && asciiCode <='9' || // and && 연산 먼저 하니까 || 앞에 괄호할 필요는 없음
										 asciiCode >= 'A' && asciiCode <='Z' || // 여기서 끝내면 ^ 도 숫자로 나옴
										 asciiCode >= 'a' && asciiCode <='z' ;
			if(isAlphabetOrNumber) System.out.println("알파벳 혹은 숫자"); // 3개 논리식 중 하나라도 참이면 이걸로 나옴
			if(!isAlphabetOrNumber) System.out.println("기타"); // 3개 논리식 모두 거짓이면 이걸로 나옴
			
			System.out.println("2. 하나의 문자를 입력하세요?"); // 처음부터 char 변환 가능
			
			/*
			char word = (char)System.in.read();               //어 왜 여기서 블라킹 안되지? 이게 실행이 돼서 아래에 출력이 됐다는 뜻, 근데 줄바꿈만 됨
			System.out.println("입력한 문자:"+(int)word);             // 스트림에 A가 들어간 후 \r\n이 들어감
			// \r\nA -> 순서로 스트림 있었는데 무조건 한바이트씩 읽음, A 먼저 읽고, 그 다음에 \n을 읽어서 줄바꿈이 된것
			// 숫자니까 int로 word 앞에 해주면 됨
			word = (char)System.in.read();               // char는 선언했으니 지워줌, A하고 엔터하면 \r 읽음 10으로.
			System.out.println("입력한 문자:"+(int)word);              
			*/
			
			//엔터값 읽어서 미사용 하겠다는 뜻
			/*
			System.in.read();
			System.in.read();
			*/
			// 위 방법 또는
			System.in.skip(2);                        // 2칸 빼는 방법
			char word = (char)System.in.read();      // 두번째 입력한건 옆에 word에 담은 것 
			System.out.println("입력한 문자:"+word);         //여기는 숫자로 변환말고, A 한번 더 치면 프로그램 종료 나옴

			/*
			 * 문제] 사용자가 입력한 값이 숫자인지 먼저 판단하고, 숫자라면 2의 배수인지를 판단하여
			 * 2의 배수인 경우만 "2의 배수입니다"라고 출력하여라.
			 * 2의 배수가 아닌 경우 "2의 배수가 아니다"라고 출력
			 * 문자 '0'의 아스키 코드값:48
			 * 
			 * 숫자가 아니면 아무것도 출력되면 안됨
			 */
			
			// 캐릭터형이 연산에 참여시 아스키코드로 바꿔서 입력되므로 int로 바꿀 필요는 없음 ex '가' -> 44032
			
			boolean isNumber = word >= '0' && word <='9';  // t나 f로 저장될것
			//boolean isMultiple = word % 2==0; //사람이 0을 48로 해놔서 운이 좋게 된것, 49였으면 1은 50 -> 이렇게 홀짝이 바뀜
			boolean isMultiple = (word-'0') % 2==0;   //문자0 입력 시 word가 48 -> 여기서 48 빼서 순수한 0값을 만들면 더 좋음
			// word는 사용자가 쓰는 문자
			
			//방법1]if문 안에 if문으로 처리, if문도 하나의 명령문이라 가능
			if(isNumber) {// isNumber가 숫자면 true
				if(isMultiple) System.out.println("2의 배수입니다");
				if(!isMultiple) System.out.println("2의 배수가 아니다");
			}
			
			//방법2]하나의 조건식안에서 논리 연산으로 처리
			if(isNumber && isMultiple) System.out.println("2의 배수입니다");
			if(isNumber && !isMultiple) System.out.println("2의 배수가 아니다");

			System.out.println("프로그램 종료!!!");
			
			/*
			 *  복습
			 *  if(식) <- 괄호안에는 1] 변수(t/f나오는 불린변수필수), 2] t/f자체값(쓸 일 없음), 3] 비교나 논리식(t/f로 결과나옴) 들어가면 됨
			 *  
			 */
			
	
			
			
			
	}// main
}/////class
