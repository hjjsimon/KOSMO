package abstract17;
/*
 * [추상클래스 (Abstract Class)] -> 목적1. 상속, 목적2. 동일한 API사용
   
   - 한 개 이상의 추상 메소드를 가진다면 
     추상 클래스로 선언 해야 함
   - 추상 메소드를 갖고 있지 않더라도 인스턴스 생성을 
      못하게 할 목적이면 추상 클래스로 선언 할 수도 있음
       (추상 메소드가 없어도) 
   - 추상메소드(abstract method)란?
      메소드 몸체(body)없이 선언만 하는 것 
      이 때 메소드 앞에 abstract 란 modifier를 붙여 준다.
		ex) abstract public void abstractMethod();
   - 추상메소드를 한개라도 가진 클래스는 class 앞에    
     abstract를 붙여주어 추상 클래스로 만들어야 한다.

   - 추상 클래스를 상속받은 클래스에서는  
     추상 메소드를 강제적으로 오버라이딩해야한다. 
     그렇지 않을 경우 그 자식 클래스도 추상 클래스가 되어야 함.

   - 추상 클래스는 타입선언은 할 수 있으나  
      new 해서 객체 생성은 할 수 없다.
     즉 상속관계시 부모타입으로 선언하고 
      자식타입의 객체를 생성 할 수는 있다.

//지금까지 만든 클래스들을 Concrete Class(완전함,=일반클래스) 이라고함, 추상클래스는 new를 할 수 없음(설계도 역할을 못함)
//메모리에 만들 수 없음, 메소드에 abstract가 붙음, 중괄호 없음(=구현부가 없음) -> 구현부가 없는 이런 추상메소드(온전하지 않다는 뜻, 그래서 메모리에 못 만듦)를 가질 수 있는게 추상클래스 
//클래스 앞에 abstract 지정자 붙이면 추상 클래스가 됨, 추상메소드 없어도 abstract 클래스 옆에 붙이면 추상클래스
//목적이 상속, 다른 클래스의 부모가 될 수 있음
//추상클래스를 상속받은 자식이 있으면 추상메소드를 쓸 때 구현부가 없으므로 반드시 오버라이딩 해야함, 자식도 추상클래스면 추상메소드를 가질 수 있으니까 오버라이딩 안해도 됨

	[클래스별 멤버제한]
	----------------------------------------------------------------
	일반 클래스	     |추상 클래스의     | 인터페이스
	(concrete class)     |(abstract class)  | (interface)
	---------------------+------------------+------------------------
	1. 멤버변수	     |  1 +2 +3 +4와    |    1.final변수와
	2. 멤버메소드	     |  추상메소드      |       2.추상메소드만을 멤버로 가짐
	3. 생성자	     |                  |
	4. final변수(상수)등 |                  |
	---------------------+------------------+------------------------

	[final 지정자(modifier)]의 의미

	*final지정자가 변수에 지정되면 상수가 됨

	------------------------------------------------------------------------
	      |   abstract	     |  final (ex. String 클래스, 얘는 상속받을 수 없음, extends 불가)
	------+------------------+----------------------------------------------
	클래스 | 상속 목적(추상)     | 상속을 못받게함(마지막 클래스, 더 이상 다른 클래스의 부모가 될 수 없음)
	------+------------------+----------------------------------------------
	메소드  |강제로 오버라이딩     | 오버라이딩을 못하게 하기 위함(메소드에 final 붙으면 오버라이딩으로
	      |시키는 목적(추상)     | 재정의가 불가능해짐)
	------+------------------+----------------------------------------------
	변수   |    X (불가)       | 값을 재 할당 못하게 즉 상수로 지정됨(public static을 함께 사용)
	------+------------------+------------------------------------------------
	*abstract 와 final 지정자는 동시에 사용불가(둘 중의 하나만 붙여한다)

 */

//ex. 내가 팀장임, 가, 나, 다에게 삼각형,사각형,원 면적 구하는 메소드 만들어오라고함
//추상 클래스 안에 추상 메소드를 만들면, abstract void area(); -> 이걸 쓰면 구현부는 달라도 호출 메소드 이름은 동일함, =동일한 API를 사용한다고 함

/*
[추상클래스-불완전한 설계도] -> new 불가
1]class 앞에 abstract(modifier)를 붙이면 그 클래스는 추상 클래스가 된다
2]어떤 클래스가 추상 메소드를 가지면 그 클래스를 반드시 추상 클래스로 만들어 줘야 한다
  단, 추상 메소드가 없어도 abstract만 붙이면 추상 클래스가 될 수 있다.
  ※추상 메소드란?
    - 메소드 앞에 abstract키워드가 붙은 것
    - 메소드 원형만 있고 구현부가 없는것, 예] 접근지정자 abstract 반환타입 메소드명([매개변수]);
    - 추상메소드는 오버라이딩이 목적이다*****
3]추상 클래스는 인스턴스화 할 수 없다.즉 메모리에 객체를 생성할 수 없다 즉 new연산자로 메모리를 할당 할 수 없다.
4]추상클래스는 상속이 목적이다*****
5]추상 클래스를 상속받은 자식클래스(sub class)에서는 만약 추상 클래스가(super class) 하나라도 추상 메소드를 가지고 있다면 반드시 오버라이딩 해야 한다
   즉 동일한 API를 사용할 수 있다.
6]만약 자식클래스에서 부모클래스의 추상메소드를 오버라이딩 하지 않으려면 자식도 추상 클래스로 만들어야 한다
7]추상클래스는 new해서 인스턴스화 할 수 없지만 추상클래스 타입의 인스턴스 변수에 자식클래스의 메모리를 할당 할 수 있다(Heterogeneous)
*/

//추상클래스1]-추상메소드가 없는 추상클래스
abstract class NoHavingAbstractMethod{
	//[멤버상수]
	public static final int MAX_INT=Integer.MAX_VALUE;
	//[멤버변수]
	int istanceVar;
	static int staticVar;
	//[멤버메소드]
	void instanceMethod() {}
	static void staticMethod() {}
}
//추상클래스2]-추상메소드를 가진 추상클래스, 추상메소드 있으면 반드시 클래스도 추상클래스가 돼야한다
abstract class HavingAbstractMethod{
	abstract void abstractMethod(); //[o], 구현부가 없는게 추상메소드
	//void abstractMethod(); [x], 추상메소드가 아님, 구현부가 없어서 에러남 <- 메소드 규칙자체에 어긋남
}

class NoHavingChild extends NoHavingAbstractMethod{
	int newVar; //새롭게 추가한 자식멤버
	//추상메소드를 갖지않은 추상클래스를 상속받은 경우, 강제적으로 오버라이딩 할 의무는 없다, 정적메소드는 오버라이딩 불가
	//강제사항 아님, 임의로 오버라이딩 한 것
	@Override
	void instanceMethod() {}
}

class HavingChild extends HavingAbstractMethod{//구현되지 않은 메소드 추가 HavingChild 빨간줄 찍으면 나옴
	//자식을 일반클래스로 만들면, 의무적으로 오버라이딩 해야한다 
	@Override
	void abstractMethod() {}
	//자식에서 새롭게 추가한 메소드
	void newMethod() {} 
}

//추상클래스3] 추상메소드를 가진 추상클래스를 상속받은 자식클래스에서 의무적인 오버라이딩을 안하려면, 자식클래스도 추상클래스로 만들면 된다
abstract class HavingChildNoOverriding extends HavingAbstractMethod {//부모가 추상메소드를 갖고있어서 빨간줄감
	abstract void abstractNewMethod(); //이거 포함 상속받은거까지 총 2개 추상메소드 존재
}

class HavingNewChild extends HavingChildNoOverriding{//빨간줄 눌러서 위의 2개 추상메소드 오버라이딩함
	@Override
	void abstractNewMethod() {
	}
	@Override
	void abstractMethod() {
	}
}

public class AbstractBasic {

	public static void main(String[] args) {
		
		//1. 추상클래스는 인스턴스화 불가]-자식타입으로는 객체생성 가능(헤테로지너스)
		//NoHavingAbstractMethod name=new NoHavingAbstractMethod(); //[x]추상클래스라고 abstract 붙이면 인스턴스화 불가 
		NoHavingAbstractMethod name=new NoHavingChild();//[o]헤테로지너스, 변수는 부모타입, 메모리는 자식타입
		
		//2. 추상클래스 타입의 인스턴스변수에 자식클래스 타입의 메모리할당-이질화]
		HavingAbstractMethod ham=new HavingChild(); 
		ham.abstractMethod();
		((HavingChild)ham).newMethod();//자식으로 형변환하면 자식에서 새로 만든 newMethod() 뜸
		
		//추상클래스의 정적멤버:(자식 혹은 부모)클래스명.정적멤버
		NoHavingAbstractMethod.staticMethod(); //클래스명으로 접근
		NoHavingChild.staticMethod();//상속받았으니까 애도 됨			
	}
}
