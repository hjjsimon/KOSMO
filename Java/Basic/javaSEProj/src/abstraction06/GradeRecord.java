package abstraction06;

public class GradeRecord { //여러타입의 데이터를 저장할 수 있는 내가 만든 자료형 = 클래스 GradeRecord
	// 여기에는 데이터만 저장
	// 1명 학생의 이름/국영수점수(배열)/총점/평균/등수를 저장하는 자료형(클래스-> 자료형이다!)
	// 성적정보를 저장하는 자료구조를 추상화하자
	
	//[멤버상수]
	public static final int SUBJECTS=3;
	
	//[멤버변수]
	String name;//이름] 1명밖에 저장못함
	int[] jumsu=new int[SUBJECTS]; // 과목3개 대신 과목 상수로 가져와서 쓴것
	int total; //총점
	double avg; //평균
	int rank=1; //등수저장, 1로 초기화
	
	// GradeRecord 타입을 배열 3개로 만들어서 3명의 성적을 저장하려면 -> GradeRecord타입의 방이 [0], [1], [2]로 3개 만들어짐, 그냥 int나 GradeRecord나 같은 자료형
	
}
