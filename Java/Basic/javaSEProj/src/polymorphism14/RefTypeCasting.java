package polymorphism14;
/*
참조형 사이의 형변환(Type Casting) -------> 인스턴스 변수: 메모리를 가리키는것********

 - 두 클래간의 상속관계가 존재할때만 형변환이 가능하다.********(완전 구조 다르면 어떻게 상속을 해줘)
 - 형변환시 타입캐스팅 연산자 : (자료형) 사용 -> 기본자료형 형변환 동일
 - 부모클래스 -> 자식클래스의 인스턴스변수에 저장(Down Casting) -> 부모타입이 자식타입으로 바뀜, 부모가 큰데 down되는것
 - 자식클래스 -> 부모클래스의 인스턴스변수에 저장(Up Casting) -> 자식타입이 부모타입으로 바뀜
 	
[1.업 캐스팅] 	
	부모의 인스턴스 변수 = 자식의 인스턴스 변수;//[O] ->자식이 더 작으니까 당연, 기본자료형에서 큰그릇에 작은그릇을 넣는거랑 동일 ex. Person p2=new Student();
	묵시적 형변환, 즉 캐스팅 연산자를 사용할 필요가 없다. -> 따로 형변환 없이 그냥 담김
	
[2.다운 캐스팅]

	자식타입의 인스턴스 변수= (자식타입)부모의 인스턴스변수
	강제적 형변환 즉 캐스팅 연산자 사용 -> 아까 에러나던거 Normal n=(Normal)s;
	※
 	만약에 부모의 인스턴스변수에 자식의 인스턴스 변수가 저장되어 있지 않다면 다운캐스팅 하더라도 실행시 에러 -> 아까 에러나던거 Normal n=(Normal)s;
 	
 	다운캐스팅 조건: 반드시 부모의 인스턴스 변수에 자식의
 	                        인스턴스 변수가 저장되어 있어야 한다
*/

class Base{
	void base() {
		System.out.println("Base의 메소드:base()");
	}
}////Base

class Derived extends Base{
	void derived() {
		System.out.println("Derived의 메소드:derived()");
	}
	@Override 
	void base() {//오버라이딩
		System.out.println("Derived에서 오버라이딩한 메소드:base()");
	}
}////Derived




public class RefTypeCasting { //리퍼런스, 참조형간의 형변환

	public static void main(String[] args) {

		Base base=new Base();
		base.base();
		
		Derived derived=new Derived();
		derived.base(); //오버라이딩한게 호출됨
		//두 클래스간 상속관계가 존재시만 주소 대입연산 및 형변환 가능
		System.out.println("[base인스턴스변수에 derived인스턴스변수 대입 전]:");
		System.out.println("base인스턴스변수가 참조하는 타입:"+base.getClass().getName()); 
		//위가 클래스명을 반환해주는 메소드, Base base=new Base(); <- base는 Base클래스 인스턴스변수니까 당연!
		System.out.println(base instanceof Derived); 
		System.out.println((Derived)base);//[x]실행시 오류 base ->Derived타입으로 바꾸면 캐스트에러
		base=derived;//업캐스팅, derived의 주소를 base에 넣는것, base의 Base연결은 끊김, 부모를 자식에 넣은거라 업캐스팅
		System.out.println("base인스턴스변수가 참조하는 타입:"+base.getClass().getName());
		System.out.println(base instanceof Derived);
		//단, 자식에서 새롭게 정의한 멤버는 부모타입의 인스턴스변수로는 접근불가(해결방안: 형변환-다운캐스팅)
		//base.derived();//[x]
		((Derived)base).derived(); //[o]다운캐스팅, 형변환, 변수에 안담고 바로 .찍음, 귀찮,그냥 instanceofOP 복습
		
	}////main
}////class
