package inheritance16;

public class Teacher extends Person {//클래스 만들 때 superclass에서 인헤리턴스16의 person 찾아넣으면 자동 extends붙여서 나옴
	//Person이 Student, Teacher에 하나씩 상속됨, Student랑 Teacher은 상관관계 없음
	//[멤버 변수]
	//이름과 나이와 주소는 재사용]
	String subject;//과목, 티쳐클래스에서 새로 만든 멤버변수
	//인자생성자]
	public Teacher(String name, int age, String addr, String subject) { 
		//super();
		this.name=name;
		this.age=age;
		this.addr=addr;
		this.subject=subject;
		System.out.println("Teacher의 인자 생성자"); 
	}
	//[멤버 메소드]
	//Person에서 정의한 메소드는 재사용
	void teach() {//Teacher클래스에서 새롭게 추가한 메소드
		System.out.println(String.format("%s샘이 %s과목을 가르치다",name,subject)); //name, subject 여기 클래스에서 정의안했는데도 다 받아쓸 수 있음~ 상속~ 
	}
	String getTeacher() {
		return String.format("%s,과목:%s",getPerson(),subject);
	}
	void printTeacher() {
		System.out.println(getTeacher());
	}
	
	
	
	
	
	
	
	
	
}
