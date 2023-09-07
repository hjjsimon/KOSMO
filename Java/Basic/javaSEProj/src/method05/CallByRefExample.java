package method05;

import java.awt.print.Printable;

public class CallByRefExample {
	
	private static void setTotalNAverage(double[][] scores) {
		
		for(int i=0 ; i<scores.length ; i++) { //////////////////////////////// 소스확인
			// 총점구하기]
			for(int k=0 ; k<scores[i].length-2 ; k++) { // 0, 1, 2까지만 반복시킴
				scores[i][3]+=scores[i][k]; // i번째 행의 k번째 열을 누적해서 i,3에 넣음
			}
			// 평균 구하기]
			scores[i][4]=scores[i][3]/3; //scores 자체가 double이라 요소하나하나가 double, 그래서 또 더블처리 해줄 필요 없음	
		}
		
	}///////////////////setTotalNAverage
	
	private static void print(double[][] scores) { // 콜바이레퍼런스로 15개 저장을 1개로 퉁침
		
		String [] titles= {"국어","영어","수학","총점","평균"};
		for(int i=0 ; i<scores.length ; i++) {
			System.out.printf("[%d번째 학생 성적]%n",i+1);
			for(int k=0 ; k<scores[i].length ; k++) { 
				System.out.printf("%s:%-4.0f",titles[k],scores[i][k]); //0f로 소수점출력안함 
			}
			System.out.println();
		}
		
	}/////////////////print
	
	
	

	public static void main(String[] args) {
		
		double [][] scores = {               // 보통 데이터는 메인에 있음, 3명의 점수, 총점, 평균은 0 0 에 넣을 예정
				{100,100,100,0,0},
				{90,90,90,0,0},
				{95,95,95,0,0}
			};
		
		// (0,0에) 총점 및 평균을 설정하는 메소드 호출] scores 통으로 쓰면 됨, 기본자료형은 1개밖에 반환불가, 지금 15개 변수, 15개 반환 -> 콜바이레퍼런스로 가능
		setTotalNAverage(scores); // 빨간줄 클릭하면 void로 메소드 아래에 만들어짐, 위로 옮기자, 
		
		// 출력] 
		print(scores);
		
		
		
		
		
		
		
	}///////////////////main





	

	

}///////////////////////class
