package interface18;
/*
//객체(나)의 설계도가 Class(아빠), Class의 설계도는 Interface(할아버지) -> Interface는 상수, 추상메소드만 가질 수 있음, Interface 상속 시 추상메소드를 반드시 구현해야함
//Interface를 통해 여러개를 상속받을 수 있음, 이 때는 extends가 아니라 implements 씀, 그리고 extends는 나열불가, implements는 나열가능
		
-클래스가 객체의 설계도라면 인터페이스는 클래스의 설계도라 할 수 있다
 ※클래스는 인터페이스를 상속받을 수 있지만 인터페이스는 클래스를 상속 받을 수 없다.
-자바는 단일 상속이 원칙이나 인터페이스를 이용해서 다중 상속을 구현할 수 있다.
 ,(콤마)로 구분해서 여러개의 인터페이스를 상속 받을 수 있다.
-추상 클래스처럼 상속이 목적으로 상속받은 클래스는 추상 메소드를 오버라이딩해야 하기때문에 동일한 API(메소드)를 사용할 수 있다.
-멤버로는 추상메소드와 상수(final변수)로만 구성된다.
-접근지정자는 public과 default접근지정자만 가질 수 있다. modifier(static,final)는 가질 수 없다.
-인터페이스에 있는 추상메소드는 public과 abstract란 키워드를 생략한다. ->생략해도 자동으로 붙음
-자식의 오버라딩 메서드에서는 반드시 public을 붙여야 한다. ->오버라이딩 규칙 
-인터페이스의 상수 또한 public static final을 생략해도, 컴파일러가 자동으로 붙여준다.	
-클래스가 인터페이스를 상속받을때는 implements키워드(구현), 인터페이스가 인터페이스를 상속받을때는 extends키워드(확장) -> 다르면 implements, 같으면 extends
-인터페이스는 생성자가 없다.
*/

//interface MyInter{} //[o]default(package/생략형)접근지정자 사용, class가 interface로 바뀐 것뿐임
//static interface MyInter{} //[x]클래스와 동일, static은 미리 만드는것, 추상메소드를 가질 수 있는데, 구현부가 없는 메소드를 어떻게 미리 static으로 만들어, class와 완전동일
//final interface MyInter{}//[x]클래스와 다르다, 설계도가 계속 상속받아야지, final 당연히 불가, 클래스는 가능
//abstract interface MyInter{}//[o]붙여도 되나 의미x, 어차피 interface

//[인터페이스들]->클래스처럼 대문자시작
interface MyInter1{
	//public Myinter1() {}//[x]인터페이스는 생성자를 가질 수 없음
	//[멤버상수]
	public static final int MAX_INT=Integer.MAX_VALUE;
	double MAX_DOUBLE=Double.MAX_VALUE; //public static final 안붙여도 알아서 붙음, interface가 변수를 가지면 자동으로 public static final 붙여서 상수 만듦
	//[추상메소드들]
	public abstract void noOmit(); //omit 생략하다, 생략안한게 noomit -> 오버라이딩하면 지정자 abstract는 사라짐, public void 이런 겉껍질은 동일하게 해야함
	void omit(); //public abstract -> 이것도 자동으로 붙는다~
}

interface MyInter2{
	void omit(); //추상메소드 하나 만들자
}

abstract class AbstractClass{
	//void abstractMethod(); //[x]추상클래스 안에서는 추상메소드 만들려면 반드시 abstract 필수, 인터페이스 안에서는 abstract 생략가능, 자동 추상메소드 됨
	abstract void abstractMethod(); //[o] 
}

class MyClass implements MyInter1, MyInter2{
	@Override
	public void noOmit() {}
	@Override
	public void omit() {}//tip: 인터페이스에 동일한 이름의 추상메소드가 있는 경우, 먼저 기술한 인터페이스의 추상메소드만 오버라이딩 된다 ex. omit 동일 시, 1만 오버라이딩 
}
//인터페이스가 인터페이스 상속받을 때: extends사용, 클래스와 다르게 콤마로 여러개 상속가능
interface MyInter3 extends MyInter1, MyInter2{//인터페이스 1개도 여러개 인터페이스 상속가능, 이 때는 같은급이라 확장(extends)
	//새롭게 추상메소드 추가도 가능
	void abstractMethod();
}

class lasyClass extends AbstractClass implements MyInter3{
	@Override//빨간줄 add함
	public void abstractMethod() {//위에 public static 생략임, 위에 보면 추상클래스에 같은 이름 메소드가 있음, package생략된걸 public으로 바꾸면 MyInter3에 있는게 오버라이딩 됨, 넓은 범위의 메소드가 오버라이딩 우선순위가 높음 
	}//MyInter3의 추상메소드를 오버라이딩 해야한다, 접근지정자가 더 넓으니까!
	@Override//여기 2개는 MyInter 1,2꺼
	public void noOmit() {
	}
	@Override
	public void omit() {
	}
}

public class InterfaceBasic {

	public static void main(String[] args) {
		//1]인터페이스 역시 추상클래스처럼 인스턴스화 불가
		//MyInter1 mi1=new MyInter1();//[x]인스턴스화 불가
		//2]이질화: 인터페이스명 인터페이스타입의인스턴스변수= new 자식클래스명();
		MyInter2 mi2=new MyClass(); //MyInter1 상속받은거 LasyClass, MyClass 등등, 앞에가 더 큰 부모 뒤는 작은거면 이질화 OK
		//오버라이딩 된 NoOmit, Omit 메소드 호출됨
		mi2.omit(); //noOmit은 안뜸, 뜨고싶으면 아래!
		((MyClass)mi2).noOmit();//자식으로 형변환하면 뜸
		((MyInter1)mi2).noOmit();//MyInter1도 당연히 됨 -> 인터페이스는 상속관계가 없어도 형변환이 가능하다(클래스는 상속관계시에만 형변환 가능)******
		//그림설명, mi2에 마이클래스 주소 저장, 마이클래스 안에 마이인터1,2 작은방 있음, mi2는 마이인터2타입이라 마이인터2작은방의 Omit만 보임*****
		//마이인터1으로 형변해서 마이인터1 작은방 가리키거나, 더 큰 방 마이클래스를 가리키면 noOmit 두 경우 모두 보임******
		//인터페이스 상수 접근]: 인터페이스명으로 접근하면 됨
		System.out.println(MyInter1.MAX_INT);
		System.out.println(MyInter3.MAX_INT); //얘도 마이인터1 상속받은거라 3로도 찾아갈 수 있음
		System.out.println(lasyClass.MAX_INT); //LasyClass도 마찬가지
		
	}

}
