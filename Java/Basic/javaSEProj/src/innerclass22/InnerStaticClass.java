package innerclass22;
/*
내부 정적 클래스:클래스안의 클래스로 class앞에 static이 붙음
- 외부 클래스의 인스턴스형 멤버는 사용할 수 없다
- 외부클래스명$내부클래스명.class로 파일이 생긴다.
※ 원래 클래스 앞에는 static을 붙일 수 없으나 내부 클래스는 가능하다
*/
class OuterStatic{//같은 패키지(디렉토리)에 OuterClass 있어서 .class이름 같으면 안되니까 바꿔줌, 내부클래스는 외부$내부.class라 외부만 이름 다르면 됨
	//멤버변수
	int outerInstanceVar;
	static int sameVar;
	static int outerStaticVar;//정적
	
	//내부클래스 인스턴스화
	InnerClass inner=new InnerClass();
	
	//멤버메소드
	void outerInstanceMethod() {
		//외부에서 내부에 접근하기]->외부에서는 내부의 멤버에 접근불가(접근시 반드시 내부클래스를 인스턴스화해야함)
		//외부에서는 내부의 멤버에 접근불가(접근시 내부클래스를 인스턴스화 해야한다) -> 정적멤버는 클래스명으로 접근하면됨
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
	//내부정적클래스->원래 클래스 앞 static 불가! 내부라 가능
	static class InnerClass{//옛날에는 클래스 앞에 static 붙여야 아래 static 붙이는거 가능했음 -> 여기까지 클래스 3개 생김, 메인하나, Outer하나, Outer$Inner하나
		//멤버변수
		int innerInstanceVar;
		static int sameVar=1;
		static int innerStaticVar; //jdk16전까지는 static 못썼음(외부클래스 인스턴스화 후 그 변수로 내부클래스를 인스턴스화하면 inner. 됐음 -> 이제 static 클래스라 당연히 가질 수 있음
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
			//내부에서 외부에 접근하기] ->내부클래스에서는 외부의 모든멤버 사용가능(그냥 내꺼처럼) ->외부의 인스턴스형 멤버는 사용불가
			//System.out.println(outerInstanceVar); ->내부 정적클래스에서는 외부의 인스턴스형멤버 사용불가***************이것만 이번에 달라진것(내부멤버클래스와 다른점)
			System.out.println(outerStaticVar);//정적멤버 메모리에 이미 있으니 당근 가능 ->외부의 정적 멤버만 사용가능
			//outerInstanceMethod();
			outerStaticMethod();
			//외부의 멤버와 내부 멤버 충돌 시
			//내부 클래스안에서 this는 내부클래스 지칭
			//내부 클래스안에서 외부클래스명.this는 외부클래스를 지칭
			//내부 멤버변수=내부 멤버변수
			//sameVar=sameVar; //멤버변수, 지역변수 충돌시 가까운 지역변수가 우선, 오른쪽 왼쪽 둘 다 1로 초기화된 내 세임바
			//this.sameVar=sameVar;//this도 내꺼 inner 이것도 위랑 같은 코드
			//외부의 정적멤버와 내부 정적멤버 충돌할 때
			//외부의 정적멤버변수=내부 정적멤버변수
			OuterStatic.sameVar=sameVar;//이렇게 해야 왼쪽의 sameVar가 외부꺼임-> 외부의 멤버변수에 내부의 멤버변수를 넣는방법 
			//-> sameVar도 외부의 인스턴스멤버(static없으니까), this가 인스턴스멤버 접근에 쓰는것, 근데 접근불가 에러, 외부,내부 int sameVar에 static 붙이고 this 빼면 접근가능 
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

public class InnerStaticClass {

	public static void main(String[] args) {
		//jdk16이후->아래처럼 인스턴스화 없이 바로 사용가능
//		System.out.println(OuterClass.InnerClass.innerStaticVar); 
		//jdk16이전
//		OuterClass oc=new OuterClass();
//		OuterClass.InnerClass inner= oc.new InnerClass();;
//		System.out.println(inner.innerInstanceVar);
	
		
		//별도의 클래스에서 내부클래스를 인스턴스화 불가능
		//InnerClass inClass=new InnerClass(); 
		//외부클래스를 인스턴스화 할 필요 없다, 내부클래스만 인스턴스화하면 됨
		//내부의 인스턴스형 멤버 접근시-내부 정적 클래스 인스턴스화해야함]
		//외부클래스명.내부클래스명 인스턴스변수=new 외부클래스명.내부클래스명()
		OuterStatic.InnerClass inner=new OuterStatic.InnerClass();//빌더패턴일때 주로 사용(별도의 클래스에서 아주 많이 사용)
		System.out.println(inner.innerInstanceVar);
		inner.innerInstanceMethod();
		//내부의 정적멤버 접근시-외부클래스명.내부클래스명.정적멤버로 바로가능]->정적멤버라 인스턴스화 불필요
		System.out.println(OuterStatic.InnerClass.innerStaticVar);
		
		
		
	}

}
