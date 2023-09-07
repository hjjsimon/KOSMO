package abstraction06;

import java.util.Scanner;

public class GradeApp {

	public static void main(String[] args) {
		
		//여기 main에서 실행해야 console에 뜸
		//메소드는 GradeLogic에 있음
		
		//1]GradeLogic 객체화(인스턴스화, heap영역에 GradeLogic타입의 메모리 생성)
		GradeLogic logic = new GradeLogic();
		//System.out.println(logic.records);		<- 이런식으로 logic 참조해서 찾아가보면 null나옴
	
		/*
		 스택에 logic 메모리 생성, 힙에 GradeLogic타입 GradeLogic@0x1234 메모리 생성, new했으니 logic에 0x1234 주소 저장
		 컨트롤 그래이드로직 클릭, 거기로 가서 세팅 확인
		 numberOfStudents(int 0초기화), records메모리 배열이라 null로 저장		
		 메소드는 setNumberOfStudents() 생성 		
		 	 		
		 */
				
		//2]학생수를 설정하는 메소드 호출		
		logic.setNumberOfStudents();
		/*for(int i=0 ; i<logic.records.length ; i++)
			System.out.printf("records[%d]:%s%n",i,logic.records[i]); //records가 배열, 
		다 이해하면 출력할 필요x*/ 
		
		//3명 입력시 메소드의 numberOfStudents(int 0초기화) -> 3됨
		//3명 입력시 records=new GradeRecord[numberOfStudents];로 힙영역에 3개짜리 배열 생성
		//하나하나 GradeRecord타입 -> 방 3개 모두 GradeRecord로 class, 즉 참조형이니까 주소 저장
		//Person p; -> p=new Person으로 Person타입 메모리 저장하던 것과 동일
		//GradeLogic@0x1234안의 records에 주소 0x5678저장, 새로 만들어진 배열 GR0x5678 메모리, -> 여기 각 방은 records[0], [1], [2]로 부름
		
		//i가 0일때 new로 힙에 GradeRecord@0x1111에 name, jumsu, total, avg, rank(스트링도 참조형인데 지금 따로 안뺌) 
		//[I@0x1111-1 메모리 생기고 -> jumsu에 0x1111-1 저장, 지금은 int니까 0초기화
		//0x1111 -> records[0]에 생김, records[0].name 이런식으로 방생성
		//records[1]은 그래이드레코드0x2222로 지정
		//logic -> GradeLogic -> 그 안의 records->GR0x5678 -> 레코드[0]~[2] 각 방은 또 GR각각 만듦 -> 그 GR안의 jumsu는 int배열 3개 담당(각 jumsu[0]~[2]번방)
		//
		
		//3]이름 및 점수를 입력받고 총점 및 평균을 구하는 메소드 호출
		logic.setNameNJumsu();
	/*	for(int i=0 ; i<logic.records.length; i++) {
			System.out.println(logic.records[i].name+"의 점수");//i번째학생의 이름
			System.out.printf("국어:%d,영어:%d,수학:%d,총점:%d,평균%.2f%n"
					,logic.records[i].jumsu[0]
					,logic.records[i].jumsu[1]
					,logic.records[i].jumsu[2]
					,logic.records[i].total
					,logic.records[i].avg);
		}
	확인차 출력	
	*/	
		//4]등수 설정용 메소드 호출
		logic.setRank();
		
		//5]결과 출력용 메소드 호출
		logic.print();
		
		
		
		
		
	}

}
