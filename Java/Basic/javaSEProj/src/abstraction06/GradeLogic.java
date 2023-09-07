package abstraction06;

//import java.util.Arrays;
import java.util.Scanner;

public class GradeLogic { 
	
	//여기에는 로직만 넣음, 다른 곳에서 출력
	/*
	 저장된 성적을 처리하는 로직을 추상화하자
	 멤버변수]: 학생수, GradeRecord타입의 배열(크기는 학생수만큼, 배열쓰는게 아니면 1명밖에 저장못함)
	 멤버메소드]: 
	 1]학생수를 설정하는 메소드
	 2]점수를 입력받고 총점 및 평균을 구하는 메소드
	 3]등수를 구하는 메소드
	 4]결과를 출력하는 메소드
	 */

	//[멤버변수]
	int numberOfStudents; //학생수 저장용, 0초기화
	GradeRecord [] records; //변수명은 records로 함, GradeRecord자료형의 배열은 참조형(배열은 참조형)이니까 null로 초기화 -> new하면 주소를 저장하게됨
	//new GradeRecord[3]하면 GradeRecord 타입으로 방3개 만들어짐 -> GradeRecord도 클래스로 참조형이니까 null로 초기화 -> 클래스 그냥 자료형이라고!
	
	String[] titles= {"국어","영어","수학"};
	
	Scanner sc = new Scanner(System.in); // 메인에서 학생수 유지하고 아래에 int
	
	//[멤버메소드] 
	//1]학생수를 설정하는 메소드 -> 매개변수:없음, 반환타입:void <= 멤버변수로 하면돼서 인자가 없대
	void setNumberOfStudents() { //학생수
		System.out.println("학생수를 입력하세요?"); //nextInt는 숫자만 읽음, 엔터 스트림에 남아서 밑에 읽게되니 엔터 버려줘야함
		numberOfStudents=sc.nextInt(); //메인에서 메소드만 입력받으면 끝남
		sc.nextLine();//엔터값 읽어서 버리기
		records=new GradeRecord[numberOfStudents]; //학생수만큼 그래이드레코드타입 배열 생성, 이제 new 했으니 records에 주소가 저장
		for(int i=0 ; i<records.length ; i++) {
			records[i]= new GradeRecord();//그레이드레코드의 주소 넣으면 됨, 이제 new로 주소 넣었으니 각 방은 주소 들어감
			
			/*
			 1.GradeLogic logic = new GradeLogic();  
			 
			 스택의 logic이 GradeLogic@0x1234 만듦, 힙영역 GradeLogic 자료형의 메모리(=클래스)
		
			public class GradeLogic {
			int numberOfStudents; 
			GradeRecord [] records; 
			String[] titles= {"국어","영어","수학"};
			Scanner sc = new Scanner(System.in);	
			void setNumberOfStudents() {
				System.out.println("학생수를 입력하세요?");
				numberOfStudents=sc.nextInt();
				records=new GradeRecord[numberOfStudents];
				for(int i=0 ; i<records.length ; i++) {
				records[i]= new GradeRecord();
				}
			
			
			2. records=new GradeRecord[numberOfStudents];
			 
			GradeLogic@0x1234 안의 records에 0x5678 주소 저장 -> GradeRecord0x5678 방 3개 만듦
			
			
			3. GradeRecord0x5678의 방 3개는 각각 records[0],[1],[2] 그리고 new 했으니 다시 [0],[1],[2] 안에 주소 생성
			4. 3개 각 방의 주소는 다시 GradeRecord 3개와 연결
			5. 3개 각 방내의 각 1개 jumsu는 3개짜리 인트 배열을 만들고, jumsu에 인트배열 주소 저장
			
			*/
		}
		
	}/////////////setNumberOfStudents	
	
	//2]점수를 입력받고 총점 및 평균을 구하는 메소드		
	//매개변수:NO
	//반환타입:void
	void setNameNJumsu() {
		//학생수만큼 이름 및 국영수 점수 입력받기
		for(int i=0 ; i<numberOfStudents ; i++) { //records.length로 해도 numberOfStudents 동일
			System.out.printf("[%d번째 학생]%n",i+1);
			//i번째 학생 이름 입력받기 -> GradeRecord 각 3개방 생긴걸 records[0].name 이런 식으로 참조해서 찾아감
			System.out.println("이름 입력?");
			records[i].name=sc.nextLine(); //이름 입력 받아서 logic -> GR -> GR -> GR의 name에 저장됨
			//i번째 학생의 국영수 점수를 입력받자
			for(int k=0 ; k<GradeRecord.SUBJECTS ; k++) {//static 붙어서 서브젝트를 찾아갈 수 있음, 과목3개
				System.out.println(titles[k]+"점수 입력?"); //titles 국,영,수 각방 만들어줬었음 스트링으로
				records[i].jumsu[k]=sc.nextInt(); //records로 찾아가서 jumsu 3개방 하나씩 찾아가서 입력값 넣음
				
				//i번째 학생의 점수 누적
				records[i].total+=records[i].jumsu[k];
			}///////////for
			//i번째 학생의 국영수 점수 평균저장
			records[i].avg=(double)records[i].total/GradeRecord.SUBJECTS;
			sc.nextLine();//위로 가면 또 nextLine이 엔터 읽음
		}
		
	}///////////////setNameNJumsu
		
	//3] 등수를 구하는 메소드, 매개변수:NO, 반환타입:void
	//등수는 토탈이나 평균 아무거나로 비교가능 내맘, 토탈은 지금 logic->GL->GR->GR->안의 total이 각각 1총 3개 존재
	//total1과 2비교, 2가 더 작으면 rank 원래 1초기화를 ++해줌, total1과 3비교, 3이 더 작으면 3의 rank도 ++, total 2랑3모두 2등됨, 2랑3등 비교해서 작은놈rank 하나더++하면 끝
	void setRank() {
		for(int i=0; i<numberOfStudents-1; i++) {
			for(int k=i+1; k<numberOfStudents; k++) { //i 0이면 k 1과 비교, i 1이면 k 2와 비교, 위 설명 말하는것
				if(records[i].total<records[k].total) records[i].rank++;
				else if(records[i].total>records[k].total) records[k].rank++;
			}
		}
	}/////////////setRank
	
	//4] 결과를 출력하는 메소드, 매개변수:NO, 반환타입:void
	
	private void sort() { //records[0],[1],[2]에 들어있는 주소를 성적순으로 바꾸면 됨
		
		//Arrays.sort(records); //에러남
		
		for(int i=0 ; i<records.length-1 ; i++) {
			
			for(int k=i+1; k<records.length ; k++) {
				
			if(records[i].total < records[k].total) { 
				GradeRecord temp = records[k];  // 주소가 저장되어있으니 temp를 GradeRecord 타입으로 해준것, 이전에는 int로 했음
				records[k] = records[i];
				records[i] = temp; 
			}///if	
			}/// 안쪽 for									
		}/// 바깥쪽 for		
	}/// sort
		
	
	void print(){
		
		//출력 전 1등부터 정렬]
		sort(); //이걸 위의 메소드에서 가져와준것
		
		System.out.println("================================================");
		System.out.printf("%-10s%-5s%-5s%-5s%-5s%-8s%s%n","NAME","KOR","ENG","MATH","SUM","AVG","RANK");
		System.out.println("================================================");
		for(int i=0 ; i<records.length; i++) {
			//이름 출력
			System.out.printf("%-10s", records[i].name);//i번째학생의 이름
		/*	System.out.printf("국어:%d,영어:%d,수학:%d,총점:%d,평균%.2f%n"
					,logic.records[i].jumsu[0]
					,logic.records[i].jumsu[1]
					,logic.records[i].jumsu[2]
					,logic.records[i].total
					,logic.records[i].avg);
		}// 여기로 복붙하면 logic이란 변수 없음, 바로 records로 접근하면 됨*/
			
			//국영수 출력
			for(int k=0; k<GradeRecord.SUBJECTS; k++) {
				System.out.printf("%-5s",records[i].jumsu[k]);
			}
			//총점.평균.등수 출력]
			System.out.printf("%-5s%-8.2f%s%n",records[i].total,records[i].avg,records[i].rank);
		
		
	}
		System.out.println("================================================");
	
	
	
	
	}

		
	
}
