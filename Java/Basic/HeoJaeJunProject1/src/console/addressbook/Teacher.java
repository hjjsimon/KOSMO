package console.addressbook;

public class Teacher extends Person{
	//[멤버변수]
	String subject;//새롭게 확장한 멤버변수
	//[인자생성자]
	public Teacher(String name, int age, String subject) {
		super(name, age);
		this.subject=subject; //상속받은 상태에서 subject 새로 추가해줌
	}
	//[멤버메소드]->다 오버라이딩 //우클릭->소스 이후 클래스에 맞게 변경
	@Override
	String get() {
		return String.format("%s,과목:%s",super.get(),subject); 
	}////get
	@Override
	void print() {
		System.out.println(get());
	}////print
	//학생+교사 합쳐서 3명 받을 예정, Student(클래스=자료형)타입의 배열 3개, Teacher타입의 배열 3개 만들것
	//Student@0x1234 메모리 생길때마다 Student배열[0],[1],[2]에 순서대로 주소 들어감, 최초에는 참조형이니까 티쳐방까지 총 6개 다 null로 초기화되어있음
	//null이 3개남을 때까지만 받으면됨
	//더 좋은 아이디어, 애초에 주소담을 때 Person타입 배열을 쓰면됨, 자식인 Student, Teacher은 Person에 넣을 때 자식이 더 작으니 바꿀 필요도 없음 
}
