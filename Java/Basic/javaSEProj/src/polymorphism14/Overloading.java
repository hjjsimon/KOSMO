package polymorphism14;

import java.util.Date;

import abstraction06.Person;

public class Overloading {

	//OOP의 특징 1. 추상화, 2. 캡슐화, 3.다형성, 4.상속성
			//다형성: 형태가 다양하다(그 중 하나가 오버로딩) -> 한 클래스 안에 동일한 이름의 여러 메소드를 정의할 수 있게 됨
			//메소드 이름 동일 ex. abs(double, float, int, long) <- 인자를 받는 매개변수 타입이 다 다름, 그러면 이름은 같지만 다른 메소드로 처리함
			
	/*		[다형성(Polymorphism)] -> 3개, 오버로딩(얘만 상속무관), 오버라이딩, 헤테로지너스

			*메소드와 관련된 개념

			1] 오버로딩(Overloading)-중복정의
			    하나의 클래스 안에서 적용되는 개념으로 같은 
			            이름의 메서드를 여러개 정의 할 수 있다 -> 아래 규칙에 해당해야 오버로딩에 해당함
			    - 메소드명이 동일해야 한다.
			    - 매개변수의 데이터 타입이 다르거나
			    - 매개변수의 갯수가 다르거나 
			       매개변수의 타입이 다른 경우 순서가 다른 경우도 오버로딩이다	    
			    - 메소드의 반환타입은 상관이 없다.

			2] 오버라이딩(Overriding)-재정의
			    상속시에 적용되는개념 즉 부모로부터 상속 받은 메소드를 
			    재정의해서 사용하는 것
			    - 메소드명이 동일해야 한다.
			    - 메소드의 매개변수 갯수, 데이터타입이  같아야 한다.
			    - 메소드의 반환타입도 같아야 한다.
			    - 부모 메서드의 접근 지정자가 
			       public이거나 protected인 경우에만 오버라이딩이 된다.

			    - 부모 메서드가 default 지정자나 private지정자를 
			       가진 메서드를 오버라이딩 한경우
			       자식 고유의 메서드로 처리된다(오버라이딩 한 것이 아님)	
			       //단,default접근 지정자는 다른 패키지에서는
			//접근이 안됨으로 부모와 자식이 다른 패키지일 경우에만
			       //오버라이딩 한것이 아님.
			      //같은 패키지일 경우는 오버라이딩에 해당

			    - Exception의 경우 부모 클래스의 메소드와 동일하거나 
			      더 구체적인 Exception을
			      발생시켜야 한다.
	*/
			/*
			Overloading(중복정의): 하나의 클래스안에서 같은 이름의 메소드를 여러개 정의 할 수 있다. -> 아래 조건을 만족해야함
			
			조건1)매개변수 갯수가 다르거나 
			-> ex. abs(int), abs(int, int) 처럼 매개변수 개수가 다를 때 이 abs는 다른 메소드임 / 메소드가 음식, 인자가 양념, 설탕 1번친거랑 2번친 김치찌개 다름
			-> ex. abs(int a), abs(int b) 동일 메소드 -> 변수명 내맘 이건 상관없음
			
			조건2)갯수가 같다면 매개변수 타입이 다르거나 
			-> ex. abs(int), abs(double) 처럼 매개변수가 double, int 등등 다를 때/ 설탕 1번친거랑 소금 1번친거랑 다른 김치찌개, 다른 음식(메소드)이 됨
			
			조건3)타입과 갯수가 같다면 매개변수 순서가 달라야한다
			-> ex. abs(int, float), abs(float, int) 이것도 다른 메소드/ 양념 순서가 중요, 변수 개수랑 타입 같아도 순서 다르면 다른 음식(메소드)
			
			그러면 다른 메소드로 본다. -> 위 조건이 맞으면 중복해서 메소드를 사용할 수 있다
			※단, 반환타입과는 전혀 관계가 없다.
			-> ex. double abs(int, float), float abs(int, float) / 동일 메소드, 음식 동일, 반환은 담는 그릇임, double 큰그릇, float 작은그릇, 그릇 상관x, 음식은 동일함
			
			원래는 하나의 클래스안에서 메소드를 중복 정의해서 사용할 수 없다.
			단,오버로딩을 적용해 같은 이름의 메소드를 중복정의 할 수는 있다.
		*/
	
	
	//아래 첫줄, 셋째줄 두 메소드는 같은 메소드(오버로딩x)
	//메소드명 동일, 매개변수 타입 및 개수 및 순서가 동일하므로 같은 메소드
	//반환타입은 무관하다
	
	//int noOverloading(String str, Date date) {return 0;} //변수명 str이든 뭐든 이런건 상관없음
	//int noOverloading(String str, Date date) {return 0;} //순서, 개수, 타입 3박자 모두 같으니 같은 메소드 *************3개 암기
	//String noOverloading(String str, Date date) {return 0;} //위윗줄이랑 같은 메소드임

	//메소드 오버로딩 방법]
	//1]매개변수(parameter)타입이 다른 경우: 양념의 종류가 다른 경우
	void paramTypeDiff(int param) {}
	//void paramTypeDiff(int param) {} //같은 메소드 중복은 안됨, 빨간줄
	void paramTypeDiff(float param) {} //이건 다른 메소드라 ㄱㅊ 이름 중복돼도 됨
	void paramTypeDiff(double param) {} //이건 다른 메소드라 ㄱㅊ 이름 중복돼도 됨
	
	//2]매개변수 개수가 다른 경우: 양념을 넣는 횟수가 다른 경우
	void paramCountDiff() {}//매개변수 없는 형태
	void paramCountDiff(int param) {} //양념 한번 치면 다른 음식(다른 메소드)
	void paramCountDiff(int param1, int param2) {}
	
	//3] 매개변수 타입, 개수 같지만 순서가 다른 경우: 양념은 같지만 넣는 순서가 다름
	void paramOrderDiff(int args1, float args2, double args3) {} 
	void paramOrderDiff(float args2, int args1, double args3) {} //위와 양념순서 달라 다른 음식(다른 메소드)
	void paramOrderDiff(double args3, float args2, int args1) {}
	
	/*
	JDK5.0이상 부터 메소드의 기능은 동일하고
	매개변수의 타입이 동일한 하나의 자료형일 경우      -> ex. void paramCountDiff(int param) {} / void paramCountDiff(int param1, int param2) {}
	매개변수 갯수에 따라서 매번 오버로딩 하지않고      -> 위처럼 매번 오버로딩 할 필요 없음, 3개짜리 언제 또 만들고, 4개 언제 또 만들고 그래
	VarArgs라는 기능을 이용해서                   -> 이걸 쓰면 하나의 메소드로 정의 가능, 많이 쓰는 스킬이래
	하나의 메소드로 처리할 수 있다.
	[매개변수 형식]
	메서드명(자료형 ... 매개변수명)           -> ... 이런거 넣어본적 없음, -> 배열이 되어버림
	이때 매개변수명는 배열명이 된다.
	즉 하나의 메소드로 여러 호출 형식의 메소드를
	호출할 수 있다.
	*/
	
	//VarArgs기능 미사용시 여러개 메소드 오버로딩해야함
	//매개변수로 받은 모든 값의 합을 구하는 메소드
	//VarArgs기능 안쓰는 경우]
/*	static int getTotal(int args) {
		int sum=0;
		sum+=args; //args를 누적합
		return sum;
	}
	static int getTotal(int args1, int args2) {
		int sum=0;
		sum+=args1+args2; //args를 누적합
		return sum;
	}
	static int getTotal(int args1, int args2, int args3) {
		int sum=0;
		sum+=args1+args2+args3; //args를 누적합
		return sum;
	}
*/	
	//VarArgs기능 사용하는 경우]: ... 3개 찍음
	static int getTotal(int...args) {//변수명 args, args라는 메모리에는 주소 저장, 10 1개 인자 전달시 방[0] 1개가 만들어짐, 2개 인자 전달시 방[0],[1] 2개가 만들어짐
		System.out.printf("args:%s, 배열크기:%s%n",args,args.length);
		int sum=0;
		for(int i=0; i<args.length; i++) sum+=args[i];
		return sum;
	}////getTotal
	
	public static void main(String[] args) {

		System.out.println("총합:"+getTotal(10)); //10을 sum에 더함
		System.out.println("총합:"+getTotal(10,20)); //인자 2개 받는 메소드 없음.. 위에 가서 또 정의해야함
		System.out.println("총합:"+getTotal(10,20,30));
		
		
		
	}

}
