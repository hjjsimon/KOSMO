package modifier09;

/*
- 멤버변수(멤법메소드 동일, 정적/인스턴스형)는 크게 정적멤버 변수와 인스턴스형 멤버변수로 나눈다
- 멤버메소드도 정적메소드 와 인스턴스형 메소드로 나눈다.
- 멤버변수나 멤버 메소드 앞에 static이라는 modifier가 붙으면 정적 멤버 즉 정적 멤버변수이거나 정적 메소드이다.
- 단,class앞에는 static을 붙일 수 없다 

 예]   int age;//인스턴스형 멤버변수(static없는 변수)
     static int age;//정적 멤버변수
     public void method(){};//인스턴스형 메소드(static없는 메소드)
     public static void method(){};//정적 메소드 -> static public도 됨, 순서 바꿔도 되지만, 보통 앞에 접근지정자씀(public등)
    
※ 정적멤버(상수 ,변수 혹은 메소드/스태틱붙은 애들)의 메모리는 JVM에서 클래스를 로드할 시점에 생기고
   인스턴스형 멤버는 JVM에서 인터프리터(main찾아 new했을때) 할때 메모리가 생성된다.(즉 new연산자로 인스턴스화 할때 생긴다)
※ 클래스의 멤버에 접근시 접근 방법
	
	 1]인스턴스형 멤버 : 인스턴스변수.멤버 (인스턴스변수가 . 앞에 붙었던거)
	
	   클래스명 인스턴스변수 = new 생성자();       ->new하던과정
	   인스턴스변수.멤버;
	
	 2]정적 멤버 :클래스명.멤버
*/
//static class StaticClass{} //[x] 클래스 앞 static 불가

//class StaticClass{} //[o]

//static지정자는 클래스의 멤버에 붙인다
class MyStatic{
	
	//[멤버변수]
	
	int instanceVar;//인스턴스형 멤버변수] 반드시 클래스를 인스턴스화(객체화,힙영역에 메모리생성)하여 인스턴스 변수로 접근, new를 인터프리터가 읽을 때 돼서야 생김
	static int staticVar; //정적 멤버변수] 인스턴스화 필요 없이 클래스명으로 접근, JVM에서 클래스를 로드할 때 (클래스로더) 메모리가 이미 생성
	
	//[멤버메소드]
	void instanceMethod() {//인스턴스형 메소드]
		//인스턴스형 메소드 안에서는 모든멤버(인스턴스형,정적멤버)를 사용할 수 있다 -> 아래 설명! 생기는 시기 생각!
		
		System.out.println(instanceVar);//위에서 만든 instanceVar 변수 쓸 수 있음 -> void instanceMethod 생기는 시기랑 같으니까!
		System.out.println(staticVar); //이건 메모리에 이미 만들어져 있으니까 쓸 수 있음
	}
	static void staticMethod() {//정적 메소드
		//정적 메소드 안에서는 인스턴스형 멤버(변수,메소드)를 사용할 수 없다-> 아래 설명
		
		//System.out.println(instanceVar); [x]-> 못씀 이미 static void staticMethod는 생겼는데 instanceVar는 늦게 생겼으니까
		System.out.println(staticVar); //[o] ->쓸 수 있음, 메모리 생성시점 동일하니까
	} 
}////////MyStatic





public class StaticModifier {
	
	//인스턴스형 멤버] -> 스태틱없음
	int instanceVar;
	void instanceMethod() {}
	
	//정적 멤버
	static int staticVar;
	static void staticMethod() {}
	
	
	
	
	public static void main(String[] args) {
		//MyStatic클래스의 정적멤버 접근] 클래스명.정적멤버
		System.out.println(MyStatic.staticVar); //클래스명이 MyStatic / new로 인스턴스화 안했는데 클래스명으로 접근가능
		MyStatic.staticMethod(); //메소드 동일
		
		//MyStatic클래스의 인스턴스형멤버 접근] 인스턴스변수.인스턴스형 멤버
		MyStatic ms1=new MyStatic(); //클래스 인스턴스화
		System.out.println(ms1.instanceVar); //인스턴스변수로 접근
		ms1.instanceMethod(); // 2개 출력시킴
		
		/*
		 설계도 설명
		 
		 Static이라는 메모리영역이 Stack,Heap이랑 따로 있음, 클래스로더할 때, 정적멤버들이 생성, staticVar(변수), staticMethod() (메소드), main 메소드도 같이 생김,
		 그래서 인터프리터할 때 메인에서 한 줄씩 읽으면 System.out.println(MyStatic.staticVar); MyStatic.staticMethod(); 2개 코드 실행가능
		 
		 MyStatic ms1=new MyStatic(); 실행시
		 Stack영역 ms1, Heap영역에 MyStatic타입의 메모리 생김(MyStatic@0x1234), instanceVar(변수), instanceMethod() (메소드) 작은방 2개가 생김 
		 ms1.instance로 찾아가면 Var int니까 0 저장
		 
		 new MyStatic 또 하면 하나 더 Heap에 찍어냄, 계속 붕어빵처럼
		 
		 Static은 이미 생긴거라 한번만 생김 -> 그리고 모든 객체(MyStatic@어쩌구들)가 이를 공유할 수 있음
		 */
		
		System.out.println(ms1.staticVar); // 경고는 뜨는데 실행은 잘 됨
		//The static field MyStatic.staticVar should be accessed in a static way 
		//정적멤버인 staticVar를 static으로 실행하라는뜻, 클래스로 찾으라는 경고임
		//이렇게하면 안됨, 정적멤버도 인스턴스변수로 접근가능하나, 다른 사람이 정적멤버인데 인스턴스변수로 착각할 수 있음
		MyStatic ms2 = new MyStatic(); //설계도로 또 MyStatic@0x5678 만드는 것
		
		/*
		  ※정적 멤버는 인스턴스화된 객체(MyStatic@0x5678방들)마다 갖고 있는 멤버가 아니라 하나만 생성되서 모든 객체가 공유한다.
		    그래서 공통으로 사용하는 변수나 메소드에 주로 static을 붙인다.
이줄암기!     메소드는 멤버변수에 있는 데이타가 아닌 매개변수에 전달된 데이타로만 일을 처리하는 메소드에는 static을 붙이자(아래설명) -> 이래야 아주 유리, 굳이 인스턴스화할 필요 없음****
		    static하면 엉뚱하게 공유하므로 문제가 생긴다! 이럴 경우 정적메소드!
		 
		 instanceMethod() -> 주로 instanceVar 같은 MyStatic 방에 있는 것들로 작업함
		 staticMethod() -> 정적메소드는 instanceVar 쓰는게 아니라 num1,num2등 매개변수(인자)로 전달받는 데이터로 작업을 함
		 굳이 Mystatic 인스턴스화할 필요 없이 Mystatic클래스명 으로 staticMethod()찾아가면됨 
		  */
		
		
		//public class StaticModifier { -> StaticModifier 아직 new 인스턴스화 안함
		//정적멤버: (클래스명). 멤버로 접근하나 자기클래스안에서 접근하기 때문에 클래스명 보통 생략
		
		System.out.println(StaticModifier.staticVar); // staticVar 자기꺼임, 근데 굳이 자기 이름(클래스명) 또 붙일 필요없음, ex. 재준이는
		System.out.println(staticVar);
		staticMethod(); // 지금 메인 내에 있는데 static 되어있으니까 같은 시기 생겨서 되는 것, 그냥 바로 호출하면 된다~
		
		//인스턴스형 멤버: 인스턴스화 후 접근
		StaticModifier sm = new StaticModifier(); // new해야 메모리에 생김~
		System.out.println(sm.instanceVar);
		sm.instanceMethod();
		
		
		
		
		
		
		
		
		
		
		
		
	}//////////////main

}//////////////////class
