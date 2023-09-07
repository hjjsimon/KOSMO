package console;

import java.util.Scanner;

import common.utility.CommonUtil;

public class RockPaperScissorsMethodVer {

		// 상수정의, 사용자 입력값 설정용
		public static final int SCISSORS=1;
		public static final int ROCK=2;
		public static final int PAPER=3;
		public static final int EXIT=9;
		public static final int REPLAY=4;
		
		//메뉴 체크에 따른 반환값용  -> checkMenu char로 반환하니까 char로 상수 만듦
		public static final char QUIT='Q';            //EXIT상수 대응
		public static final char CONTINUE='C';        //REPLAY상수 대응
		public static final char NORMAL='N';	      //메뉴번호 1, 2, 3 대응
		
		
	//메뉴 출력용 메소드      -> 아래 기능을 하나 메소드로 뺌
	static void showMenu() {
			
			System.out.println("======================[메뉴 번호]======================");
			System.out.println("        1.가위 2.바위 3.보 4.메뉴 다시보기 9.종료");
			System.out.println("=====================================================");
			
		}//////////showmenu
	
	// 숫자를 랜덤하게 발생시키는 메소드-컴퓨터용
	private static int getComputerNumber(int start, int end) {
		
		return (int)(Math.random()*((end-start)+1))+start; 
	}//////////getComputerNumber
	
	// 사용자 숫자입력받아서 반환하는 메소드
	private static int getUserNumber() {
		Scanner sc= new Scanner(System.in);
		
		//문] 정상적인 메뉴번호를 입력할 때까지 계속 입력받도록 하여라
		//즉, 숫자가 아닌 문자(특수문자 등) 입력시에는 "메뉴 번호만.."을 출력하고, 다시 입력받도록 하여라(isNumber()메소드 만든 후 문제)
		
		
		
		System.out.println("메뉴 번호를 입력하세요?");
		String menuNumber; //선언부터 함 그래야 아래 리턴에도 해당, while블락 안에만 있으면 스택에서 사라져 리턴에서 못찾음
		
		while (true) {
			menuNumber=sc.nextLine();
		//입력받는 부분, 문자열이 숫자형식이면 int로 반환하는 parseInt
		//숫자형식이 아니면 Throws:NumberFormatException - if the string does not contain aparsable integer. 에러, 다시 입력받아야함
		//숫자형식일때 parseInt하면 됨, 숫자형식 아닐 때는 에러남-> 다시 입력받으면 됨
			
			if(!CommonUtil.isNumber(menuNumber)) { //문자면 false -> 근데 반전, true로 내부로 들어감, 숫자면 브레이크
				System.out.println("메뉴번호만...");
				continue; //while의 true로 감
			}
			break;//숫자는 브레이크로 바로 return으로 감, parseInt
		}/////////while
		return Integer.parseInt(menuNumber);
				
		
		
		//return sc.nextInt(); //입력한걸 바로 반환, 3.20에 제거
	
	}/////////////getUserNumber
	
	// 사용자 입력 숫자(1,2,3)에 따라 "가위", "바위", "보" 반환하는 메소드 -> 스트링으로 반환 문자니까
	static String getSRPvalue(int value) {          //가위바위보 약자, 값 
		//삼항연산자버전
		return value==SCISSORS ? "가위" : value==ROCK ? "바위" : "보" ; 
		
	/*	//Swtich문
		switch(value) {
		case SCISSORS: return "가위";
		case ROCK: return "바위";
		default: return "보";
		}*/
	}
	
	
	// 가위바위보 승리판단 후 결과 출력용 메소드
	private static void showResult(int computer, int user) {
		System.out.printf("[당신:%s, 컴퓨터:%s]%n",getSRPvalue(user),getSRPvalue(computer)); // 이렇게하면 아래 안써도 됨
	
	/*		
	  			user==SCISSORS?"가위":user==ROCK?"바위":"보",             // 사용자 삼항연산자 사용
				computer==SCISSORS?"가위":computer==ROCK?"바위":"보");    // 삼항연산자 안에 삼항연산자 쓴 것
	*/	
		if(user==SCISSORS && computer==PAPER ||
		   user==ROCK && computer==SCISSORS	||
		   user==PAPER && computer==ROCK)
		   System.out.println("당신이 이겼어요");
		else if (user==computer) System.out.println("비겼어요");
		else System.out.println("당신이 졌어요"); 
	}//////////////showResult
	
	// 메뉴번호 체크용 메소드 
	private static char checkMenu(int menu) { // user였는데 menu로 바꿈, 사용자가 입력한 번호가 menu로 들어감 

		if(menu==EXIT) return QUIT; // EXIT 입력 시 QUIT, 빠져나감
		else if(menu==REPLAY) { //continue 못씀 반복문 아니니까
			showMenu();
			return CONTINUE;
		}
		else if(!(menu==1||menu==2||menu==3)) { // 전체 통으로 괄호하고 ! 해도 됨
			System.out.println("메뉴에 없는 번호입니다");
			showMenu(); //메뉴 다시 보여주고
			return CONTINUE; //컨티뉴로 처음부터 돌아가게해줌
		}
		return NORMAL;// 위에 if,else if, else if 뚫었으면 메뉴 1,2,3 입력한것 
	}/////////////////////checkMenu

		
	public static void main(String[] args) {
		
			//Scanner sc= new Scanner(System.in); // 처음부터 반복하려고하지말고, 한번 만들고 반복시키기, 사용자숫자입력 메소드로 빼버림
				
			showMenu(); //이렇게 while밖에 두면 한번만 보임
			
			while(true) {      // 전체 반복세팅 계속 타자만 치게함
				
				
				//1] 메뉴 출력
				//showMenu();

				//2] 컴퓨터는 숫자를 랜덤하게 발생시킨다             
				int computer = getComputerNumber(1,3); //1,2,3숫자 만들어짐, 1~3이니까 1,3으로함               //(int)(Math.random()*3)+1;   
				// 코드가 짧아서 빼기는 애매, 그래도 빼는게 좋음, 메소드는 +1 같은 하드코딩하면 안됨, 매개변수로 받은걸로 바꿔버려야함
				//System.out.println("컴퓨터:"+computer); -> 1, 2, 3 지정했으니까 생략
				
				//3] 사용자 숫자 입력 받기
				//System.out.println("메뉴 번호를 입력하세요?");        // 이것도 메소드로 뻄
				int user = getUserNumber();
				//System.out.println("사용자:"+user); -> 안씀 구림
				
				//4] 메뉴번호체크
				char checks=checkMenu(user); // 반복문어쩌구 얘기함, char로 받는대 그냥 선생님이 정함, int도 됨
				
				if(checks==QUIT) break;
				else if(checks==CONTINUE) continue;
				
				
		/*		if(user==EXIT) {
					System.out.println("다음에 또 즐~"); // while문 다음에 넣어도 됨
					break;
				} // user가 9 상수치면 브레이크
				
				else if(user==REPLAY) {      // EXIT 처럼 상수로 빼버리자
					showMenu();// 4번 누르면 메뉴 다시 보도록, 근데 당신이 졌어요 else로 진행됨
					continue; // 컨티뉴로 반복문 처음으로 가게 바꾸면 true로 이동, 컴퓨터 숫자 랜덤발생 진행됨
				}
				
				//다른 숫자 입력시 졌다고 나옴, 에러 해결 --------------->위에서 4, 9는 빼줬으니까 1,2,3만 빼줌
				else if(!(user==1||user==2||user==3)) { // 전체 통으로 괄호하고 ! 해도 됨
					System.out.println("메뉴에 없는 번호입니다");
					showMenu(); //메뉴 다시 보여주고
					continue; //컨티뉴로 처음부터 돌아가게해줌
				}
		*/		
				
				//5] 게임 승리여부 판단 -> main 위에 상수를 정의함, final 붙으면 상수 / 사용자는 이기거나 지거나 비기거나 경우의 수 3가지 -> else if 써야함
				
				showResult(computer,user); // 컴퓨터 랜덤, 사용자 입력한 숫자를 넘겨줘야함 

	/*			// 1, 2, 3 지정
				System.out.printf("[당신:%s, 컴퓨터:%s]%n",
						user==SCISSORS?"가위":user==ROCK?"바위":"보", // 사용자 삼항연산자 사용
						computer==SCISSORS?"가위":computer==ROCK?"바위":"보"); // 삼항연산자 안에 삼항연산자 쓴 것
				
				if(user==SCISSORS && computer==PAPER ||
				   user==ROCK && computer==SCISSORS	||
				   user==PAPER && computer==ROCK)
				   System.out.println("당신이 이겼어요");
				else if (user==computer) System.out.println("비겼어요");
				else System.out.println("당신이 졌어요");
	*/			
				// 한번 세팅끝내면 반복은 쉬움, 메뉴부터 여기까지 통으로 while 반복
				
				} /////////while
				
				/*
				 * 배열은 참조형(Reference type), 기본자료형이 아님, 메모리구조와 데이터저장 장소 다 틀림
				 */
				
	}

}
