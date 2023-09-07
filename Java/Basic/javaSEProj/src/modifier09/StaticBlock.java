package modifier09;

/*
[static 블락]
-동일 클래스 안의 main보다 먼저 실행됨 즉 main에 실행코드가 없어도 static블락안에 있는 내용이 실행됨 (main도 static 붙어있는데, 지금하는 static{}블락 이거는 필살기임)
 단, 다른 클래스에 main이 있는 경우는 다른 클래스의 main이 순차적으로 실행되다 static블락이 있는 클래스를 인스턴스화 할때 그때 생성자보다 먼저 static블락이 실행된다.(다음 클래스)
-static 블락안에서는 정적 멤버만 사용가능(클래스로더 시점에 static블락이 실행!!!, main블락은 인터프리터 때 실행!!!)	

-형식
     static{
		
		
     }
 */

// class명 () -> 이 형식 무조건 메소드
// MyStatic() -> new 뒤에 이게 생성자, 가장 먼저 호출(실행), 직접 코드 써서 호출하니까



public class StaticBlock {

		//인스턴스형 멤버] -> 스태틱없음
		int instanceVar;
		void instanceMethod() {}
		
		//정적 멤버]
		static int staticVar;
		static void staticMethod() {}
		 
	static { //메인블락에 아무것도 없는데 static이라 메인블락보다 먼저 실행됨
		System.out.println("[static블락 시작]");
		
		
		//인스턴스멤버x] static 블락 안에서 정적멤버만 가능, 인스턴스멤버 아래 둘 다 안됨
		//System.out.println(instanceVar);
		//instanceMethod(); 
		
		//정적멤버o]
		System.out.println(staticVar);
		staticMethod();
		
		int localVar=10;//static 블락안에서 선언한 지역변수, 멤버아니고!!
		System.out.println("static블락안에서 선언한 지역변수:"+localVar);
		
		System.out.println("[static블락 끝]"); 
	}
		
		
	public StaticBlock() { 
	//메소드라 a()라면 에러나야함(반환타입없으니까), 메소드 앞에 반환타입 있어야하는데 없음, 에러나야함, 
	//클래스명하고 메소드명이 같으면 생성자라고함, 이때는 반환타입 없어도 됨
		System.out.println("StaticBlock의 생성자");
	}
	
	
	// static이 메인보다 먼저 읽혀도, 막상 메인 없으면 run as가 안됨
	/*
	public static void main(String[] args) {

		System.out.println("main 메소드");
	}
	*/
}
