package console;

import java.util.Scanner;

import common.utility.CommonUtil;

public class BaseBall { // 처음부터 반복으로 만들면 어려움, 처음은 그냥 한번만 진행시키게 만들어야함

	// 컴퓨터든 사용자든 3개 숫자 입력 시, 배열 3칸에 각각 넣고 비교
	// c[0] -> u[0],u[1],u[2] 모두 비교해봐야함, 인덱스(자리)랑 데이터랑 다 다르면 아무것도 아님, 인덱스만 다르고 데이터 같으면 볼, 둘 다 같으면 스트라이크
	// 첫번째 발생은 첫번째방, 두번째 발생은 다른 곳 -> 첫번째랑 중복되면 두번째방에 넣으면 안됨 다르면 넣음, 세번째 발생도 동일방식 -> 로또에 쓸 수 있음
	// 사용자는 3자리 입력, 517이면 100단위의 5를 추출해서 첫번째 방에 넣고~ 이런식
	
	//1. 랜덤하게 숫자 발생 후 중복되지 않게 인자로 전달된 배열에 저장하는 메소드] -> 배열이 전달되니까 콜바이레퍼런스
	public static void setRandomNumber(int[] random, int start, int end) {   // 과제 2번 랜덤번호
	// private 말고 public 붙이면 다른 곳에서도 쓸 수 있음, computer, i, j로 된건 바꿔도 됨 -> random, start, end
	// computer 주소가 random에 저장
	// 메인스택에 computer 메모리가 있고, 힙에 computer[0]~[2]에 0 0 0 저장
	// random 메모리에도 주소 0x1234 -> 이 메소드에서는 힙에 random[0]~[2]가 됨, 그리고 메인에서도 바뀜	
		
		for(int i=0 ; i<random.length ; i++) { //length는 3
			while(true) { //true니까 무조건 들어옴
				//랜덤하게 숫자 발생 시키자 -> random 배열에 저장할 것, 변수 i는 random배열의 인덱스, 0~2까지 됨, 3이면 빠져나옴
				int randomNumber = (int)(Math.random()*(end-start+1))+start; // 차이값+1을 곱하고 시작값 더하고, 1~9 사이 숫자 발생
				//랜덤하게 발생시킨 숫자의 중복여부를 체크하는 변수선언
				boolean isDuplicated = false; //false로 초기화
				//랜덤하게 발생시킨 숫자(randomNumber)와 배열(random)에 저장된 값의 중복여부 체크
				for(int k=0 ; k<=i-1 ; k++){// k<i-1 반복조건한 이유? 최초 i=0,k=0 이 때는 중복체크할필요없음, 첫번째자리 그냥 넣으면 됨
					if(randomNumber==random[k]) {// k방의 값과 랜덤값이 같으면 중복된 것 -> 중복시 false세팅을 true로 바꿔줌
						isDuplicated=true;
						break; //안for문 밖으로 나옴
					}/////안for의 if
					
				}/////////안for
				
				if(!isDuplicated) {//중복되지 않는경우, random 첫번째자리에 5 넣어줌 
					random[i]=randomNumber;
					break; //가장 가까운 반복문인 while문을 빠져나감
				}///////while안의 if
				
				//for문에서 처음으로가면 i++됨, i=1됨, 1<3이니까 내려오고, 숫자 랜덤발생하고, false로 초기화
				//두번째도 5면 0<=0 이니까 if로 들어감, 5중복이니까 빠져나감, break로 빠져나감, true에 not 붙였으니 if(false)되어 안감
				//while문 처음으로 다시 이동, true로 내려와서 다시 발생, 3발생시, randomnumber 3, random[0]=5, k=1 <= (i=1) -1 거짓이므로 if문 안 isDuplicated로 안들어감
				//!isduplicated로 들어감 
			}///////while
		}////////////밖for
	}////////////////setRandomNumber
	
	//2. 사용자 입력용 메소드
	private static void setUserNumber(int[] user) {
		Scanner sc = new Scanner(System.in);
		System.out.println("세 자리 숫자를 입력하세요?");		
//		int userNumber=sc.nextInt(); //userNumber에 입력값 세자리 저장
		
		//문제] 먼저 입력한 문자열이 숫자인이 아닌지 판단하고
		// 숫자형식이 아니라면 "숫자만 입력하세요" 출력 후 다시 입력받기
		// 숫자형식이면 3자리만 입력받고, 3자리가 아니면 "숫자는 3자리만 입력하세요" 출력 후 다시 입력
		// 3자리라면 3자리가 중복이 안되도록 하여라
		// 만약 중복 시 "중복된 숫자가 있어요" 출력 후 다시 입력
		// 정상 입력시에만 int userNumber로 넘어감
		
		String userStr;
		while(true) {
		userStr=sc.nextLine(); //입력하는게 숫자일 때만 parseInt됨
	
		if(!CommonUtil.isNumber(userStr)){//숫자가 아니면 true 반환
			System.out.println("숫자만 입력하세요");
			continue; //다시 컨티뉴 
		}////if
		else if(userStr.length()!=3) { //3자리가 아니면 빠꾸
			System.out.println("숫자는 3자리만 입력하세요");
			continue;
		}
		else {//숫자이고 3자리면 여기로 옴
			char[] chArr=userStr.toCharArray(); // 0칸을 1과 2, 1칸을 2와 비교해야함
			boolean isDuplicated=false; //중복시 false
			for(int i=0;i<chArr.length-1;i++) {
				for(int k=i+1;k<chArr.length;k++) {
					if(chArr[i]==chArr[k]) {
						isDuplicated=true;
						break;
					}
				}////안쪽for
				if(isDuplicated) break; //true면 바깥 반복문도 빠져나가게함 
			}////바깥for
			if(isDuplicated) {
				System.out.println("중복된 숫자가 있어요");
				continue;
			}
		}////else
		break; //정상입력시 바로 나감
		}////while
		
		int userNumber=Integer.parseInt(userStr); //숫자가 아닌걸 parseInt시 NFE에러
		user[0]=userNumber/100; // 100의 자리수만 딱 들어감, 315면 3.15인데 userNumber/100 둘 다 인트형이라 내림으로 3만 남고 그게 user[0]에 들어감
		user[1]=userNumber%100/10; // 100으로 나누면 15만 남음, 그리고 십의자리수만 뺌
		user[2]=userNumber%10; //10으로 나누면 5남음
  
	}////////////////setUserNumber	
	
	
	//3. 판단 후 스트라이크/볼 저장용 메소드
	private static void setStrikeOrBall(int[] strikeOrBall, int[] computer, int[] user) {
		// ex. computer 각 방에 5, 1, 7 저장/ user 각 방에 7, 1, 5 저장 -> 각 방끼리 9번 비교 필요
		for(int i=0 ; i<computer.length ; i++) {
			for(int k=0 ; k<user.length ; k++) {
				// 자리가 같고, 숫자 같으면 스트라이크, 0번방에 ++해줌
				if(i==k&&computer[i]==user[k]) strikeOrBall[0]++;
				// 자리수는 다르나, 값이 같으면 볼
				else if(i!=k&&computer[i]==user[k]) strikeOrBall[1]++;
			}///안for
		}/////밖for
		}///////////////////////////setStrikeOrBall	
		
	//4. 계속 여부 판단용  메소드
	private static void isContinue() {
		Scanner sc = new Scanner(System.in);
		System.out.println("종료하려면 'X나 'x'\r\n계속하려면 아무키나 누르세요");
		String exitCode = sc.nextLine(); //문자 받으니까 nextLine, nextInt 아님
		if(exitCode.equalsIgnoreCase("X")){//대소문자 무시 ignore, 입력한 문자열이 X면 참
			System.out.println("즐거우셨죠. 다음에 또..."); 
			System.exit(0); //메소드에서 값도 반환안하는데 어떻게 끝냄? -> 지금이게 프로그램 종료 코드, (0)은 프로그램적으로 정상종료의미
		}//if	
	}////////isContinue
	//X나x가 아니면 여기로 나와서ㅏ isContinue(); 호출한 곳 이후로 내려감, 아무것도 없으니 다시 while문 처음부터 반복
	
	
	public static void main(String[] args) {
		
		while(true) { //3스트라이크 맞추면 끝내기2-> 컴퓨터 숫자발생부터 다시 시작, 밖while
		
		//1] 랜덤하게 세자리 숫자를 발생시키자(컴퓨터)
		int computer[] = new int[3]; //6으로 바꾸고 아래 9는 45로 바꾸면 로또
		setRandomNumber(computer,1,9); // 배열을 전달할거임 각 자리마다 1~9만 쓸 수 있음(설정한다고해서 set씀)
		//컴퓨터 숫자 확인
		for(int i=0 ; i<computer.length ; i++) System.out.printf("%-3d",computer[i]); // 0으로 최초지정
		System.out.println(); //위 printf의 줄바꿈용
		
		int tryCount=1; //몇번째에 맞췄는지 추가하기
		
		while(true) { //3스트라이크 맞추면 끝내기1 -> 맞출때까지 실시, 안while
		
			//2] 사용자로부터 3자리 숫자를 입력받자
			int user[] = new int[3]; //사용자입력 숫자를 저장할 1차원 배열 선언, 항상 데이터는 메인에 존재
			setUserNumber(user); //콜바이레퍼런스, user 통으로 쓰면 됨, 이거 호출 안했으면 아래값 000 나옴
			//for(int i=0 ; i<user.length ; i++) System.out.printf("%-3d",user[i]);
			
			//3] 판단하기, 즉 자리(인덱스)가 같고 값이 같으면 스트라이크, 자리는 다르고 값만 같음녀 볼
			// 0번째 방에는 스트라이크, 1번째 방에는 볼 저장, 내맘
			int [] strikeOrBall = new int[2];
			setStrikeOrBall(strikeOrBall,computer,user);
			// 스트라이크(0번방), 볼(1번방) 출력
			System.out.printf("%d 스트라이크, %d 볼%n",strikeOrBall[0],strikeOrBall[1]);
			// 근데 3스트라이크 되면 끝나야함, 그 전에는 계속 입력해야함 2] int user부터 시작 여기부터 while로 감싸면됨
			if(strikeOrBall[0]==3) {
				System.out.printf("빙고 짝! 짝! 짝! %d번째에 맞췄어요, 축하합니다!%n", tryCount);
				break;
			}
			
			tryCount++; //몇번째에 맞췄는지 추가하기
			
		}/////////안while   
		
		//4]게임 계속여부 판단용 메소드 호출
		isContinue();//뭔가 반환해야 그 값을 보고 빠져나옴, 근데 반환안시킬 것
		
		}/////////밖while
	
	}///////////////main

}///////////////////class
