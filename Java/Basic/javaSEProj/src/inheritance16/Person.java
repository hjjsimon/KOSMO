package inheritance16;

/*	
클래스 상속:
 -단일 상속만 지원(클래스 하나만 상속받을 수 있다)
 -IS A 관계 성립해야 한다.
  자식 IS A 부모
 -extends 키워드 사용
 -private접근 지정자가 붙은 부모의 멤버는 상속은 받으나 접근 불가. //부모 멤버중에 private붙은건 상속은 받는데, 접근x 쓰지는 못함
 -형식] 접근지정자 [지정자] class 자식클래스명  extends 부모클래스명{ }
---------------------------------------------------------------------------------------------------------
[상속성(Inheritance)] -> 객체지향언어의 가장 큰 장점이 코드의 재사용, 이게 상속성 덕분임

	- 추상화한 클래스(부모)에 새로운 기능(메소드)이나 속성(필드,멤버변수)을 추가하여 새로운 클래스(자식)로 만드는 것을 의미. 
-> 자식은 멤버를 또 정의할필요x, 재사용하면됨
->부모의 멤버변수, 멤버메소드 그대로 받음, 거기에 지맘대로 더 추가하는 것
->부모클래스가 통으로 자식클래스 안에 작은 상자로 들어가는것

	- 상속 개념을 적용함으로써 개발시간 단축, 재사용성 등으로 OOP 장점을 살릴 수 있다.
	- 상속 관계는 부모와 자식간에 "자식 is a 부모"라는 is a 관계가 성립할때 맺을 수 있다
->Student is a Person, 학생은 사람이다, 자식 부모 말이 됨
->Person is a Student, 사람은 학생이다, 자식 부모 말이 안됨
->A is a B, 뒤에 있는 B가 더 큰 범위여야 맞음, A가 작은 범위여야함

	- 자바에서 상속을 받을 때는 extends 란 키워드를 사용.
->extends Object, 라고하면 상속해준 부모클래스 의미, String은 Object의 상속을 받음
->Object에 toString() 있는데, String에 또 toString() 인터넷 보면 나옴
->상속받은거 아는데 왜? 리모델링이라 다시 정의 써준것!  
	- 자바는 단일 상속 개념이므로 extends 로 상속 받을 수 있는 클래스는 단 하나뿐이다.
->자바는 클래스 하나만 상속받을 수 있음, 단일 상속
*/

public class Person extends Object{//원래 extends Object있는데 생략한것, 따로 지정하지않으면 모든 클래스는 Object 자동상속, 클래스 만들 때 보임

	//[멤버 변수]
	String name;
	int age;
	String addr;
		
	//[기본 생성자]: 인자생성자는 using field, 이건 우클릭 소스 제너레이트 컨스트럭터 프럼 superclass
	public Person() {
		super(); //Object(부모)의 기본생성자: 부모가 super니까!, 우클릭해보면 public Object() {} -> 하는 일이 없대, 뭔일없으면 //로 지워도 됨
		System.out.println("Person의 기본 생성자");
	}
	
	//[멤버 메소드]
	String getPerson() {
		return String.format("이름:%s,나이:%s,주소:%s",name,age,addr);//문자열 반환
	}
	void printPerson() {
		System.out.println(getPerson()); //위에 문자열에 값이 채워져서 그대로 출력
	}
	
	
	
	
	
}//////////////class
