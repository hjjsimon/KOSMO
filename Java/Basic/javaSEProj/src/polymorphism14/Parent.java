package polymorphism14;

import java.util.Date;

//메소드를 재정의할 수 있음, 재정의= 메소드의 구현부 재정의!!
//자식이 부모에게 상속받음, 부모의 메소드 있으면 그대로 상속 ex. public void add(int a,int b){return a+b;} -> 바깥은 그대로 작성, {} 내부가 구현부라고함, 구현부만 바꾸는게 오버라이딩 규칙
//퍼블릭, 보이드, 메소드명, 인트 2개, 이런 껍데기는 모두 똑같이 놓고, 내부 알맹이만 재정의함, 외관 건물은 냅두고 내부만 바꾸는거라 리모델링
//오버라이딩도 다형성, 메소드와 관련된 개념, 다양하게 쓸 수 있으니까 다형성~
//접근지정자는 부모와 같거나, 부모보다 넓어야함 ex. 부모 package면 자식에서는 package, protected(상속받은거니까), public 3가지 가능

/*2] 오버라이딩(Overriding)-재정의
	    상속시에 적용되는개념 즉 부모로부터 상속 받은 메소드를 
	    재정의해서 사용하는 것
	    - 메소드명이 동일해야 한다.
	    - 메소드의 매개변수 갯수, 데이터타입이  같아야 한다.
	    - 메소드의 반환타입도 같아야 한다.
	    - 부모 메서드의 접근 지정자가 
	       public이거나 protected인 경우에만 오버라이딩이 된다.

	    - 부모 메서드가 default 지정자나 private지정자를 
	       가진 메서드를 오버라이딩 한경우
	       자식 고유의 메서드로 처리된다(오버라이딩 한 것이 아님)	-> 부모의 private이 붙은 메소드는 오버라이딩 절대 불가! 자식에서 안보임!
	       //단,default접근 지정자는 다른 패키지에서는
	//접근이 안됨으로 부모와 자식이 다른 패키지일 경우에만
	       //오버라이딩 한것이 아님.
	      //같은 패키지일 경우는 오버라이딩에 해당

	    - Exception의 경우 부모 클래스의 메소드와 동일하거나 
	      더 구체적인 Exception을
	      발생시켜야 한다.*/
/*
오버라이딩(Overriding)-재정의
상속시에 적용되는 개념 즉 부모로부터 상속 받은 메소드를
재정의해서 사용하는 것
  - 메소드명이 동일해야 한다.
  - 메소드의 매개변수 갯수, 데이터타입 및 순서가 모두  같아야 한다.
  - 메소드의 반환타입도 같아야 한다.
  - 접근지정자는 부모와 같거나 부모보다 넓어야 한다
  - Exception의 경우 부모 클래스의 메소드와 동일하거나 더 구체적인 Exception을 발생시켜야 한다.
*/
public class Parent {
	//[멤버변수]
	String name;
	int age;
	
	//[생성자]
	//기본생성자] 소스->슈퍼클래스
	public Parent() {} //기본생성자 그냥 무조건 만들어놔
	//인자생성자] 소스->using field
	public Parent(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	//[멤버메소드]
	private void eat() {
		System.out.println("부모님이 드신다");
	}
	String sleep(int age) {//패키지~
		System.out.println("부모님이 주무신다");
		return null; //오버라이딩을 지금 하는거, 리턴 지금 대충~ null해놓기
	}
	protected int walk(Date date){//이번엔 반환타입 int, Date타입 받고, 근데 이것도 안쓸거~ 그냥 int 지겨우니까
		System.out.println("부모님이 산책하신다");
		return 0;
	}
	public void exercise() {
		System.out.println("부모님이 운동하신다");
	}	
	//위에는 메인에서 Parent를 new 했을 때 만들어짐, 아래는 클래스로더가 이미 만들어놓음
	//new 해야 만들어지니까, 아직 안만들어짐, 설계도를 변경할 수 있는 것
	//static은 이미 만들어진거라 오버라이딩 대상이 될 수 없음, 이미 설계가 완료됨
	static void staticMethod(){//오버라이딩 테스트용-> 정적메소드는 오버라이딩 대상이 아니다!
		System.out.println("부모님의 정적 메소드");
	}
	String getParent() {
		return String.format("성함:%s,연세:%s",name,age);
	}
	void printParent() {
		System.out.println(getParent());
	}
	//////부모 설계 끝!
	
	

}
