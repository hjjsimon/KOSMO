package array04;

import java.util.Scanner;

public class ArrayExample {

	public static void main(String[] args) {
		
		String[] subjects = {"국어","영어","수학"}; // 초기화자로 세팅, 사용자가 데이터 입력 시 메세지를 뿌려주기 위한 배열데이터
		
		/*
		  *1] 학생수를 사용자로부터 입력받자 -Scanner클래스 사용
		  *2] 입력받은 학생 수만큼 국영수 점수 및 총점를 저장할수 있는 int형 배열을 선언하고  메모리를 할당해라
		  *3] 학생 수만큼 각 학생의 국영수 점수를 입력받아 2]에서 생성한 배열에 저장해라. -Scanner클래스 사용
		  *4] 각 학생의 국영수 점수 및 총점 그리고 평균을 출력하여라. 		
		*/
		
		//1] 학생수를 사용자로부터 입력받자
		Scanner sc = new Scanner(System.in);
		System.out.println("학생수를 입력하세요?"); // 사용자경험, ux 라고함, 이걸 좋게 해줘야 사용자가 뭘 써야할지 앎
		int numberOfStudents = sc.nextInt(); //숫자니까 nextInt로 입력, 입력 시 numberOfStudents에 숫자가 들어감
		//System.out.println(numberOfStudents); //입력전까지 이 코드진행x
		
		//2] 입력받은 학생 수만큼 국영수 점수 및 총점를 저장할수 있는 int형 배열을 선언하고  메모리를 할당해라 -> 국영수총점, 학생1명당 4개 메모리 필요 4열! n행!
		int [][] students = new int [numberOfStudents][4]; // 행은 학생수n명, 열은 4열
		
		//3] 학생 수만큼 각 학생의 국영수 점수를 입력받아 2]에서 생성한 배열에 저장해라
		for(int i=0 ; i<numberOfStudents ; i++) {//학생수만큼 반복하면 됨, 
			//i번째 학생의 국영수 점수 입력받기
			System.out.println(i+1+"번째 학생의 점수를 입력하세요?"); //위에 string 배열 만들고옴
			for(int k=0 ; k<subjects.length ; k++) {
				System.out.println(subjects[k]+"점수를 입력하세요?");
				//각 과목점수 입력받기
				students[i][k]=sc.nextInt(); // k-> 국영수만 입력하면 되니까 3열, 0, 1, 2 면됨
				//각 과목점수를 마지막방에 누적
				students[i][students[i].length-1]+=students[i][k]; // 스튜던트 길이 4고 3번방이니까 -1 에 누적, 행번호[i] 해줘야함
			}
		}
		
		//4] 각 학생의 국영수 점수 및 총점 그리고 평균을 출력하여라. 	-> 등수순으로 출력하는 연습도 해보기
		
		for(int i=0 ; i<numberOfStudents ; i++) {
			System.out.println("==========================================");
			System.out.printf("             %d 번째 학생 성적%n",i+1);
			System.out.println();
			// 국영수점수 / 총점 / 그리고 / 평균까지 출력
			System.out.printf("    국어:%d,영어:%d,수학:%d,총점:%d,평균:%.2f%n",
					students[i][0],
					students[i][1],
					students[i][2],
					students[i][3],
					(double)students[i][3]/subjects.length); //맨마지막 과목수로 나눠줌
		}
		System.out.println("==========================================");

		
		
	}//////main
}/////////////class
