package method05;

		/*
		메소드 형식 3: 매개변수는 있고 반환값은 없는 경우 (위만 뚫림)
		
		접근지정자 [modifier] void 메소드명(매개변수들){                      // 반환 없으니 void 씀
			처리할 일;
		}
		
		메소드에서 필요한 값을 매개변수를 통해서 받고 그 값으로 일을 처리한 후 결과값을 바로 출력하고자 할때 주로 사용 (-> 결과값을 내보내지는 않음)
		
		*/

public class MethodShape03 {

		//1] 메소드 정의: start부터 end까지 누적합을 구하는 메소드 ex. 1~10 / 1~100 / 1~1000 누적합을 여러번 구하려함, 근데 같은 로직, 중복코딩 발생, 이럴 때 쓰면 좋음
	
	static void printTotal(int start, int end) {              // 시작값, 끝값을 구해야함, 여러개(지금 2개) 나열하면 됨
		int sum=0; // 누적합을 저장할 변수 선언
		for(int i=start ; i<=end ; i++) sum+=i;       // i=1 -> start, i<=10 -> end
		
		System.out.printf("%d부터 %d까지 누적합:%d%n",start,end,sum); // i=1 -> start, i<=10 -> end
				
	}////////////printTotal
	
	//문제] 국/영/수 세 과목의 점수를 매개변수로 전달받아 평균을 구하고 학점을 출력하는 메소드를 정의하라 그리고 main 메소드에서 호출하여 결과를 확인하라
	
	/* ㅅㅂ 왜 void -> String으로 바꼈지
	static String grade(int kor, int eng, int math) {
		
		int avg = (kor+eng+math)/3;
		System.out.printf("평균:%d",avg);
		
		switch(avg/10) {
			case 10:
			case 9: return "A학점"; 
			case 8: return "B학점"; 
			case 7: return "C학점"; 
			case 6: return "D학점"; 
			default: return "F학점";
	    
		}//////switch
		
	}
	*/
	
	static void printGrade(int kor, int eng, int math) {
		
		int avg = (kor+eng+math)/3/10;   //switch에서 찾아옴
		switch (avg) {
			case 10: System.out.println("A학점");     
			case 9: System.out.println("A학점");	break;
			case 8: System.out.println("B학점");	break;
			case 7: System.out.println("C학점");	break;
			case 6: System.out.println("D학점");	break;
			default: System.out.println("F학점");
			}
		
	}///////////////printGrade
	
		// 문제] 숫자 2개를 매개변수로 입력받아서 즉 시작단과 끝단을 매개변수에 입력받아
		// 해당 구구단을 출력하는 메소드를 정의하고 main 메소드에서 호출하여라 (2x16부터 16x16까지) 
	
	static void printGugudan(int start, int end) {        //////////////소스 배끼기
		
		for(int k=1 ; k<=end ; k++) {
			for(int j=start ; j<=end ; j++ ) {
				System.out.printf("%d * %d =%-6d",j,k,j*k);
			}
			System.out.println();
		}
	}
	
	
	public static void main(String[] args) {
	
		//2] 메소드 호출
		//매개변수에 값 전달: 변수에 저장해서 전달
		int start=1, end=10;
		printTotal(start, end);
		// 메소드도 메모리가 있음, main 메소드의 stack 메모리 중 start에 1, end에 10 저장
		// printTotal의 메소드 메모리에도 stack 메모리 중 start 메모리, end 메모리, sum 메모리 존재
		// printTotal(int start, int end) -> 여기 start -> 1, end-> 10으로 바뀜, 그리고 시작
		
		//매개변수에 값 전달: 상수값 직접 전달 -> 값을 start, end 2개 받으니 아래 (1,100) 이런 식으로 반드시 2개 받아야 에러 안남
		printTotal(1,100);
		printTotal(100,1000);        // 이런식으로 호출만해서 중복코딩 방지~

		printGrade(90, 90, 90);
		printGrade(98, 77, 59);
		
		int[][] score= {
				{97,99,67},
				{88,99,78},
				{100,67,90},
				{77,56,100},
				{50,60,90}};
		
		//문제] 위에 정의한 printGrade()메소드를 호출하여 2차원배열 score에 저장된 5명의 학생의 학점을 출력하라
		
		System.out.println("[5명의 성적]");
		for(int i=0 ; i<score.length ; i++) 
			printGrade(score[i][0],score[i][1],score[i][2]);
		
		System.out.println("[2단에서 9단까지]");
		printGugudan(2, 9);
		
		System.out.println("[3단에서 16단까지]");
		printGugudan(3, 16);
		
		System.out.println("[5단에서 12단까지]");
		printGugudan(5, 12);
		
			
		
		/*
		int avg = (kor+eng+math)/3/10;   //switch에서 찾아옴
		switch (avg) {
			case 10: System.out.println("A학점");     
			case 9: System.out.println("A학점");	break;
			case 8: System.out.println("B학점");	break;
			case 7: System.out.println("C학점");	break;
			case 6: System.out.println("D학점");	break;
			default: System.out.println("F학점");
			}
		
		
		//문3] 2차원 배열에 저장된 값을 위 모양대로 출력하라
				for(int i=0 ; i<nine.length ; i++) { // i -> 행 인덱스
					for(int k=0 ; k<nine[i].length ; k++) { //k-> 열 인덱스
						System.out.printf("%-3d",nine[i][k]);			
					}
					System.out.println();
				}
		
		*/
		
		
		
		
		
		
		
		

	}//////////////////////main
}//////////////////////////class
