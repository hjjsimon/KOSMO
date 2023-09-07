package console.addressbook;

public class Student extends Person{//상속
	//[멤버변수]
	public String stNumber; //부모에 없고, 자식에서 새롭게 확장한 멤버
	//[인자생성자]-> 기본생성자는 상속해주는 부모에서만 쓰면 됨
	public Student(String name, int age, String stNumber) {//학생 이름, 나이 얘네 둘은 Person에서 상속받은거, 학번 stNumber는 여기서 새로 추가한 것
		super(name, age);
		this.stNumber=stNumber;//2개 인자생성자 상속받은거에서 stNumber 괄호위에 추가해줌
	}
	//[멤버메소드]->다 오버라이딩 //우클릭->소스 이후 클래스에 맞게 변경함
	@Override
	public String get() {
		return String.format("%s,학번:%s",super.get(),stNumber);//super.get(); <- 자식에 맞게 새로 구현, 부모의 get()을 호출하는대로 앞의 %s에 들어감, 뒤의 %s는 학번 새로 추가
	}////get()
	@Override
	public void print() {
		System.out.println(get());
	}////print()
	
}
