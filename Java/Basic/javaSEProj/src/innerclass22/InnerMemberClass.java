package innerclass22;
//클래스의 멤버가 변수 상수 메소드, 근데 클래스 안에 클래스(내부클래스), 인터페이스(내부인터페이스) 정의도 가능함, 내부클래스만 많이 씀
//내부클래스에서는 외부클래스의 멤버를 new 안하고 그대로 쓸 수 있음, 단 내부클래스의 멤버는 외부클래스에서 못가져다씀
//내부클래스의 멤버를 쓰려면 외부클래스에서 new 인스턴스화 해야함
//CLI(CUI), 커맨드 라인(유저) 인터페이스 ex.자판기에 돈넣는 곳, 음료수 나오는 곳을 인터페이스라고함, 명령어 기반 ex. 리눅스, 명령어를 쳐야 해당 공간으로 이동 ex. D드라이브 이동 
//GUI, 그래픽 사용자 인터페이스 ex.윈도우 운영체제, 모바일, 웹브라우저, 명령어 안쓰고 클릭, 드래그, 그래픽으로 구성됨, 윈도우프로그램은 윈도우 Driven 프로그램이라고함, 클릭, 드래그 등 이벤트로 시행됨
//GUI에서 이벤트 처리할때 내부멤버클래스, 내부무명클래스 많이 씀
//빌더패턴으로 클래스 설계시 내부정적클래스 많이 씀
/*
 [내부클래스(Inner Class)]

	1]내부 클래스란
	-클래스안의 클래스를 내부 클래스 혹은 Inner클래스 혹은 중첩 클래스,
	Nested클래스라 한다.
	-내부 클래스는 외부 클래스의 변수이나 
	 메서드를 자신의 것처럼 자유롭게 사용가능
	 단,내부 정적 클래스만이 외부의 non-static멤버를 사용 못함
	-내부 정적 클래스만 제외하고 모든 내부클래스가 
	 정적 멤버를 가질 수 없다.
	-GUI에서 이벤트 처리 할때 주로 사용한다
	(내부 클래스 중에서도 주로 내부 무명클래스를 사용)

    ※외부클래스를 인스턴스화(객체화 즉 new)하면
       외부클래스가 heap영역에 메모리가 생기고
       내부클래스는 new해줘야 메모리에 생긴다.
       고로 내부 클래스에서는 외부 클래스가 먼저 메모리에 생김으로
       외부클래스의 모든 멤버를 사용할 수 있다.
       단, 외부클래스에서는 내부클래스의 멤버를 사용하려면
       new(내부 클래스도 설계도임으로)해서 사용해야 한다.(원칙)
       단,내부정적 클래스는 외부 클래스보다 먼저 메모리에 생긴다.

	2]내부 클래스의 종류(넓게 보면 모두 클래스 안의 클래스)

		클래스가 정의되는 위치에 따른 분류]
		
		-클래스안에 정의된 클래스

		  1.내부 멤버 클래스(static이 안붙음) -> 그냥 클래스에서는 인스턴스형 이라고 했음
		  2.내부 정적 클래스(static이 붙음) -> 클래스에는 원래 static 안붙음, 내부클래스만 가능

		-메소드 안에 정의 된 클래스
		
		  3.내부 로컬 클래스(이름이 있는 클래스) -> 메소드 안(지역,로컬)에 정의됨, 메소드 안에서만 사용가능한 클래스라서 거의 안씀
		  4.내부 무명 클래스(이름이 없는 클래스,무명 클래스) -> 어나니머스 클래스
 
 */
/*
[내부 멤버 클래스]:클래스안의 클래스로 static이 안붙음
- 외부클래스의 모든 멤버(정적이든 인스턴스형이든)를 사용할 수있다.
- 정적멤버, 인스턴스멤버 모두 가질 수 있음(jdk16부터 정적멤버도 가질 수 있게됨, 이전까지는 내부클래스가 외부클래스의 인스턴스에 의존하므로 정적멤버 접근불가)
  그래서 외부클래스와 별개인 클래스에서도 외부클래스 인스턴스화 없이 내부의 정적멤버에 접근할 수 있게 되었다
- 외부클래스명$내부클래스명.class로 파일이 생긴다.(=컴파일이 됨)
- 이벤트 기반 프로그래밍(GUI프로그래밍):윈도우 프로그래밍(자바SE),웹프로그래밍(EE),모바일 프로그래밍(안드로이드) 등 시 주로 사용
 ※외부클래스에서 내부클래스의 멤버 접근에 관한 이론
  -외부 클래스에서는 내부 클래스의 멤버 접근 불가	
  -외부 클래스에서 내부 클래스의 멤버에 접근하려면 내부 클래스를 인스턴스화 한 후에 접근할 수 있다****************이것만 기억
   단,외부의 정적 메소드에서는 내부의 정적멤버만 인스턴스화 없이 내부클래스명.정적멤버 로 접근가능(상수도 static붙으니까 정적)
*/
/*
class A{
	Date date;
	public A() {//주로 생성자에서 초기화
		date=new Date();
	}
	
	Date date=new Date();//Date타입의 변수를 선언하면서 동시 초기화(위의 4줄이랑 동일한 코드)
	
	date. 쓰기불가-> 클래스 안에서는 정의문만 써야함, 실행문은 쓰는게 아님
}
*/

class OuterClass{
	//멤버변수
	int outerInstanceVar;
	int sameVar;
	static int outerStaticVar;//정적
	
	//내부클래스 인스턴스화
	InnerClass inner=new InnerClass();
	
	//멤버메소드
	void outerInstanceMethod() {
		//외부에서 내부에 접근하기]->외부에서는 내부의 멤버에 접근불가(접근시 반드시 내부클래스를 인스턴스화해야함)
		//System.out.println(innerInstanceVar);//인스턴스니까 같은 인스턴스는 원래 가능, 근데 내부꺼라 접근불가(변수는 변수선언x표시)
		System.out.println(inner.innerInstanceVar);//인스턴스화해서 이렇게하면 됨
		//innerInstanceMethod();//접근불가(메소드는 정의x표시)
	}
	static void outerStaticMethod() {
		//외부에서 내부에 접근하기]->내부의 정적멤버는 무조건 접근가능, 메모리 생성시점 동일(인스턴스변수는 인스턴스화해도불가)
		//innerStaticMethod();//바로 접근은 불가
		System.out.println(InnerClass.innerStaticVar);//그냥 호출하면 자기꺼에서 찾음 없어서 에러, 내부꺼 찾으려면 자기가 갖고있는 클래스.찍어줘야함
		InnerClass.innerStaticMethod();
		//System.out.println(inner.innerInstanceVar);//인스턴스화해도 static메소드라 내부에 인스턴스 불가
	}
	//내부멤버클래스
	class InnerClass{//옛날에는 클래스 앞에 static 붙여야 아래 static 붙이는거 가능했음 -> 여기까지 클래스 3개 생김, 메인하나, Outer하나, Outer$Inner하나
		//멤버변수
		int innerInstanceVar;
		int sameVar=1;
		static int innerStaticVar; //jdk16전까지는 static 못썼음(외부클래스 인스턴스화 후 그 변수로 내부클래스를 인스턴스화하면 inner. 됐음
/*		void instanceMethod() {
			System.out.println(outerInstanceVar); //자기꺼처럼 가져다쓸 수 있음, InnerClass에 static붙이면 지금 인스턴스는 불가, 
			System.out.println(outerStaticVar); //이미 생성된 static도 당연
		}
		static void staticMethod() {//이것도 못썼음
			System.out.println(innerStaticVar);//인스턴스는 스태틱 시기에 없으니 불가
			System.out.println(outerStaticVar);
		}
*/		//생성자->클래스니까 당연히 있어야함, 없으면 기본생성자 제공
		public InnerClass() {
			System.out.println("내부 클래스의 생성자");
		}
		//멤버메소드
		void innerInstanceMethod() {
			//내부에서 외부에 접근하기] ->내부클래스에서는 외부의 모든멤버 사용가능(그냥 내꺼처럼)
			System.out.println(outerInstanceVar);
			System.out.println(outerStaticVar);//정적멤버 메모리에 이미 있으니 당근 가능
			outerInstanceMethod();
			outerStaticMethod();
			//외부의 멤버와 내부 멤버 충돌 시
			//내부 클래스안에서 this는 내부클래스 지칭
			//내부 클래스안에서 외부클래스명.this는 외부클래스를 지칭
			//내부 멤버변수=내부 멤버변수
			//sameVar=sameVar; //멤버변수, 지역변수 충돌시 가까운 지역변수가 우선, 오른쪽 왼쪽 둘 다 1로 초기화된 내 세임바
			//this.sameVar=sameVar;//this도 내꺼 inner 이것도 위랑 같은 코드
			//외부 멤버변수=내부 멤버변수
			OuterClass.this.sameVar=sameVar;//이렇게 해야 왼쪽의 sameVar가 외부꺼임-> 외부의 멤버변수에 내부의 멤버변수를 넣는방법
		}
		static void innerStaticMethod() {//원래 static 붙이는거 안됐는데 가능해짐!->static이니까 당연히 정적멤버만 가져다쓸 수 있음
			//내부에서 외부에 접근하기]
			//System.out.println(outerInstanceVar);
			System.out.println(outerStaticVar);//정적멤버 메모리에 이미 있으니 당근 가능
			//outerInstanceMethod();
			outerStaticMethod();//정적멤버 메모리에 이미 있으니 당근 가능
		}
	}////InnerClass
}////OuterClass

public class InnerMemberClass {

	public static void main(String[] args) {
		//jdk16이후->아래처럼 인스턴스화 없이 바로 사용가능
//		System.out.println(OuterClass.InnerClass.innerStaticVar); 
		//jdk16이전
//		OuterClass oc=new OuterClass();
//		OuterClass.InnerClass inner= oc.new InnerClass();;
//		System.out.println(inner.innerInstanceVar);
		
		//별도의 클래스에서 내부클래스를 인스턴스화 불가, 가져올일도 없음. 가져와도 바로하면 아래처럼 숨겨져서 안보임(주로 외부에서 내부, 내부에서 외부 불러오는게 끝)
		//InnerClass inClass=new InnerClass(); ->직접 인스턴스화 불가
		//필수! 외부클래스를 먼저 인스턴스화 한다
		OuterClass outerClass=new OuterClass();
		//방법1:외부클래스에 내부클래스를 인스턴스화 한 경우-> outerClass의 인스턴스변수로 inner로 접근(inner도 OuterClass의 멤버임)
		System.out.println(outerClass.inner.innerInstanceVar);
		//방법2:외부클래스에서 내부클래스를 인스턴스화 하지 않은 경우-> 형식: 외부클래스명.내부클래스명 변수=외부클래스인스턴스변수.new 내부클래스명 -> 으로 내부클래스 인스턴스화
		OuterClass.InnerClass inner=outerClass.new InnerClass();
		System.out.println(inner.innerInstanceVar);
		//OuterClass.InnerClass.innerStaticMethod();//이렇게 찾아야함, InnerClass찾을라면 내부클래스라 여기서 안보임		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
