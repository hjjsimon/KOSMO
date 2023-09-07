package method05;

import java.util.Scanner;

//메소드 형식 4: 위아래 다 뚫림, 매개변수도 있고, 반환값도 있는 경우
/*
접근지정자 [modifier] 반환타입 메소드명(매개변수들) { // 매개변수 수는 받는 값의 개수와 동일

	처리할 일;
	return 결과값;
	
}
가장활용빈도가 높은 메소드타입	
 */
public class MethodShape04 {

	// 인원수를 매개변수로 전달받아 인원수만큼 나이를 사용자로부터 입력받고 그 나이의 평균을 반환하는 메소드 정의
	
	//1] 메소드 정의
	//반환값이 평균이니까 double로 받아줌
	static double getAverageOfAge(int personCount) { //personCount=인원수
		
		Scanner sc = new Scanner(System.in);
		int totalOfAge=0;
		
		//인원수만큼 반복하면서 나이 입력받기 그리고 totalOfAge에 누적
		for(int i=0 ; i<personCount; i++) {
			System.out.println(i+1+"번째 나이 입력?");
			totalOfAge+=sc.nextInt();
		}
		
		//평균 구해서 반환
		return (double)totalOfAge/personCount;
		//return 0; // 더블형이 0 인트형보다 크니까 충분히 담음
		
	}/////////////////////////////////////////////////////////getAverageOfAge
	
	// 문제] 매개변수로 두 숫자와 연산자(+,-,*,/)를 전달받아서 그 결과값을 반환하는 메소드를 정의해라
	// 그리고 main 메소드에서 호출하여 그 결과값을 확인하여라. 반환타입은 int형
	
	static int getCalc(int num1, int num2, char op) {     //연산자 char에 받는데 int도 된다        // void로 해도 리턴효과. 리턴가능
	
		switch(op) {
		case '+': return num1+num2;               // 값 1개만 반환? callbyreference -> 굳이 리턴
		case '-': return num1-num2;
		case '*': return num1*num2;
		case '/': return num1/num2;
		default: return -2147483648;              // 이 값이 나올 수 없음(int형의 최소값)이 나올 확률은 지극히 낮다 -> 이게 나오면 잘못된 연산자라고 해줌   
		}
	}	
	
	// 문제] 여러개의 숫자를 입력받아서 그 중 최대값을 구하는 메소드를 정의하자
	// 단, 숫자의 개수는 매개변수(아까 인원수 매개변수로 받음)로 전달받고 숫자의 개수만큼 사용자로부터 숫자를 입력(Scanner)받아
	// 최대값을 구해 그 최대값을 반환하는 메소드이다. 그리고 main에서 호출하여 최대값을 출력하여라
	// 사용자가 몇개의 숫자를 입력할지 모름
	
	/*static int max(int numOfNum) { //numOfNum = 숫자의 개수, 반환할 목적-> 최대값 -> 메소드명
		Scanner sc = new Scanner(System.in); // sc.nextInt에 저장
		for(int i=0 ; i<=numOfNum ; i++) {
			System.out.println(i+1+"번째 숫자 입력?");
			int temp = sc.nextInt();
			if()	
		}
	}*/
	
	static int getMaxValue(int numberCount) {
		
		Scanner sc = new Scanner(System.in);
	
		/*	 학생버전]
		int [] numbers = new int[numberCount]; // 입력하는 개수대로 메모리 만들어짐
		
		for(int i=0 ; i<numberCount ; i++) {
			System.out.println(i+1+"번째 숫자 입력?");
			numbers[i]=sc.nextInt(); // 받은걸 각 방에 저장
		}	
		int max = numbers[0];
		for(int i=0 ; i<numberCount ; i++) {
			if(max<numbers[i]) max=numbers[i];
		}
		return max;
	}////////////////////getMaxValue
	*/
	
/*		// 선생님버전] 배열사용
		int max=0;
		int [] numbers = new int[numberCount];
		for(int i=0 ; i<numberCount ; i++) {
			System.out.println(i+1+"번째 숫자 입력?");
			numbers[i]=sc.nextInt(); 
			if(i==0) max = numbers[0]; // 첫번째 입력 값을 max에 저장-> 위랑 같은 로직, 위에는 for문 2번써서 numberCount3이면 for문 3번 또 3번 실행, 이건 딱 3회 실행
			else {//첫번째 입력이 아니라면
				if(max<numbers[i]) max=numbers[i];
			}
		}	
*/
		
		// 선생님버전] 배열미사용  -> 반복도 횟수만큼, 배열메모리도 안씀 best
		int max=0;
		for(int i=0 ; i<numberCount ; i++) {
			System.out.println(i+1+"번째 숫자 입력?");
			if(i==0) max=sc.nextInt(); 		 // 첫번째 입력값을 max에 저장
			else {
				int temp = sc.nextInt();
				if(max<temp) max=temp;
			}
		}
		return max;
		
	}
	
	public static void main(String[] args) {
		
		//2]메소드 호출
		//System.out.printf("나이 평균:%.1f%n",getAverageOfAge(3));   // <-getAverageOfAge(0)의 괄호 안에 숫자 입력
		
		int returnValue = getCalc(10, 10, '%');  // *이면 100을 반환함, %이면 case에 없으니 -21억 나오고 잘못된 연산자입니다~
		if(returnValue == -2147483648) System.out.println("잘못된 연산자입니다");
		else System.out.println("연산 결과는 "+returnValue);
		
	
		System.out.println("최대값: "+getMaxValue(3)); // 3을 nuberCount에 넣음 이게 콜바이밸류
		
		
		
		
		
		
		
	}//////////main

}//////////////class
