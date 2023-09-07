package encapsulation13;

		//accountNo 멤버변수라 멤버변수가 있는 클래스 내에서 사용 가능
		//public void setAccountNo(String accountNo) {
		//this.accountNo = accountNo;
		//}
		//Setter에서 this 썼음. 
		//멤버변수와 지역변수가 충돌할 때 구분하기 위해서 this를 씀
		//그 위에는 지역변수 username, 멤버변수 name -> 이럴 때는 써도 됨
		//지금 위에는 둘 다 accountNo로 지역변수 멤버변수 같아서 구분 필요
		//자신의 인스턴스형 멤버에 this 씀, this는 클래스 EncapsulationDTO 자신을 의미
		
		/*
			this: 인스턴스화된 자기자신의 클래스를 지칭한다
	      - 인스턴스형 멤버에 접근(this. 찍으면 됨)할때 사용하는 키워드(반드시 static이 안붙은 "인스턴스형 메소드" 안에서만 사용가능, 다른 모든 곳에서 사용불가)
	      - this 키워드는 정적 메서드안에서 사용할 수 없다.
	      - ※지역변수와 멤버변수 구분할때(충돌할때) 주로 사용
		*/

public class ThisKeyword {
	
	//[인스턴스형 멤버]
	int instanceVar, anotherInstanceVar;
	void instanceMethod() {
		//this는 인스턴스형 메소드 안에서만 사용가능
		//this. 하면 자기자신의 모든 멤버(인스턴스,정적 모두)가 뜬다.
		System.out.println(this.instanceVar); //[o]자기 멤버 다 뜸, 밑에 staticVar도 클래스 안의 멤버라 뜸 -> 이미 static으로 메모리 안에 있어서 ㄱㅊ
		//정적멤버도 this키워드로 접근가능하지만
		//in a static way으로 접근하라고 경고가 나온다.
		System.out.println(this.staticVar); //[o] 근데 에러, 정적으로(클래스명으로) 접근하라고 노란줄 훈수, this. 안해도 자기 클래스인걸 알고있음, 재준이가~~ 느낌
		System.out.println(staticVar); //[o] 자신 클래스이므로 굳이 클래스명을 붙일 필요가 없다
	}
	
	//this키워드는 지역변수와 멤버변수를 구분할 때 주로 사용*******중요
	void initialize(int instanceVar, int another, int staticVar) {
		//지역변수(매개변수)와 멤버변수가 같은 지역안에서 사용될 때 이름이 같은 경우 지역변수가 우선한다
		//매개변수=매개변수; 멤버변수 초기화 안됨.
		//가까운애를 가져다씀, 매개변수에 매개변수 넣는거라 초기화x
		//instanceVar=instanceVar; //자기 자신에게 자신을 넣는거니까 효과가 없다고 no effect 경고 뜸
		this.instanceVar=instanceVar; //위에 instanceVar이 10으로 초기화
		//지역변수와 멤버변수 구분이 가능 시 this 생략가능, 밑에줄 this 안써도됨
		anotherInstanceVar=another; //위에 anotherInstanceVar이 100으로 초기화
		//this.staticVar=staticVar; //둘다 위의 int staticVar를 의미, 아래 정적멤버 초기화 안됨, this붙이면 경고는 뜨는데 상관없음, 실행에 문제없다, 그러면 어케함?
		//이때는 그냥 클래스명으로 접근, 정적으로 접근~
		//정적멤버와 지역변수가 동일할 때는 this가 아닌 클래스명으로 접근해서 구분해라
		ThisKeyword.staticVar=staticVar;
	
	}
	
	//[정적 멤버]->클래스로더할때 메모리 생김, static은 이미 생성돼있어서 this 못씀, 아직 지칭 클래스 안생김
	static int staticVar;
	static void staticMethod() {
		//this.[x]정적 메소드 안에서는 사용불가
	}
	
	
	public static void main(String[] args) {

		ThisKeyword thisKeyword=new ThisKeyword();
		thisKeyword.initialize(10, 100, 1000);      //10이 위의 instanceVar에 넣은 것, 근데 자기자신에게 넣는거라 10안됨 0으로 최초 초기화상태 그대로
		System.out.println("thisKeyword.instanceVar:"+thisKeyword.instanceVar);
		System.out.println("thisKeyword.anotherInstanceVar"+thisKeyword.anotherInstanceVar);
		System.out.println("staticVar:"+staticVar);
		
		
		
		
		
		
		
	}//////////main

}//////////////class
