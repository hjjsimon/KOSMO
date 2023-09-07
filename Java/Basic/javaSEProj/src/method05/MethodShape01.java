package method05;

		//변수, 자료형(배열포함), 연산자, 제어문, 메소드(다른 언어는 함수 function, 객체지향프로그램에서는 메소드 method) -> 메소드가 마지막~
		// y=f(x) -> x=입력데이터 를 받아 연산을 하면 y로 값이 나옴, 함수 f(x)는 우리가 세팅하는 것
		// 만드는 메소드는 크게 4가지 형태  ex. println() <- 괄호 안의 값을 콘솔에 출력해주는 println 메소드
		// 1) [ ] 가장 많이 씀 
		// 2) [] 위 막히면 입력값이 없고, 상자 안에서는 어떤 일을 함, 근데 아래로 결과값이 안나옴
		// 3) 위만 뚫림: 입력값을 받음, 안에서 어떤 일을 하고 결과값이 나옴, 근데 아래로 결과값이 안나옴
		// 4) 아래만 뚫림: 결과값만 내보냄(=리턴=반환 이라고 표현함) -> 메소드를 사용하는(호출하는)쪽에 반환해줌
		
		/*
		메소드(method):객체에서 행동을 의미, 프로그래밍 관점에서 보면 (데이타를 가지고)어떤 일을	처리하는 하나의 부품 -> 어떤 행동, 연산 의미
		ex. public static void main(String[] args) -> 이것도 메소드임, 여기 내부에 void a() {} 메소드는 불가, 그 위 클래스 안 영역은 가능
		
		※메소드는 class안에서 정의해야하고, 메소드안에서는 메소드를 정의 할 수 없다 (파이썬 같은 함수형은 되는데 자바는 안됨)
		※메소드 정의시 반드시 반환타입(자료형)이 있어야 한다. // 값을 반환하지 않으면 void 라는 키워드를 사용함 -> 아래 닫힌 2개 형태 메소드 의미
		※메소드명은 변수 명명규칙처럼 만들면 된다. 소문자로 시작한다(동사형). 의미있는 이름으로 지어라 -> 동사형 빼고는 변수명명규칙 동일함 ex. println print니까 출력의미지 당연히
		
		[메소드 정의]	
				
		접근지정자(Access Modifier) [Modifier] 반환타입 메소드명([매개변수들]){         
					
			처리할 일;
			[return 결과값;]	 
		}
		
		접근지정자 지금 안배움, 키워드로 정의됨, 아무거나쓰면안됨, modifier는 지정자라고함, 얘도 키워드 아무거나쓰면안됨
		반환x 시 반환타입 void, int를 반환 시 반환타입 int, 그냥 반환하는 결과값을 반환타입에 쓰면 됨
		메소드명은 내맘, 변수명명규칙처럼
		매개변수들 대괄호 -> 없어도 된다는 뜻, 여기가 데이터를 받는 변수, 위에 막히면 없는거고, 위에 뚤리면 있고(2개 변수받으면 2개고~)
		처리할 일: 내가 세팅
		결과값을 내보내는 밑에 뚫린 형태면 [return 결과값;] 씀, 밑에 막혔으면 [return 결과값;] 안씀 
		반환값이 없으면 void -> return 10(변수: 쓰면 변수에 저장된걸 반환하는 것); 쓰면 에러남 -> 근데 return; 쓰면 에러안나고 바로 메소드 빠져나감
		(브레이크는 스위치나 반복문 빠져나갈때 이건 메소드 빠져나갈때) 
		*/
		
		/*
	 	[메소드 형식1]: 위아래막힘, 매개변수(위)도 없고, 반환값(아래)도 없는 경우
		결과값을 반환하지 않을 때 반환타입은 void
		주로 [출력하는 기능]을 담당한다.
			
		접근지정자 [modifier] void 메소드명() {         //매개변수 없으니 괄호 바로 닫음
			처리할 일                                 // 반환x, 리턴키워드 없음
		}
		
		*/


public class MethodShape01 {

	
	//1] 메소드 정의 -> 첫번째 형태, 위아래 없고, 출력만하는 기능
	static void noParamNoReturn() {//파라미터, 리턴 다 없음, 매개변수도 없음
		System.out.println("=============메인메뉴==============");	
		System.out.println("1.New 2. Continue 3. Exit");
		System.out.println("=================================");
	}//////////noParamNoReturn
	
	// 아무일도 안하지만 메소드형식 맞으니 메소드, 위,아래 막힌 이런건 출력을 담당
	// 아래의 메인 메소드 안에 있는 코드가 아니라 출력안됨, 아래 void main 부터 출력하는 것
	// 정의는 했지만 사용(호출)은 안한 것 
	
	// static void noParamNoReturn() {}  
	// [x] 동일 클래스 안에서 동일 메소드 당연히 중복 불가, 밑에서 뭘 부를지 모르니까, 당연히 상식
	// 근데 객체지향언어(자바)는 int a 괄호 안에 이런거 쓰면 또 됨, 오버로딩이라고 함
	
	static void noParamNoReturn2() {} // [o]일만 안하지, 이름다른건 메소드는 당연히 정의된대로 맞음
	
	static void noParamNoReturn3() {  // 여기 왕중요 
		
		int returnValue=100;
		
		//반환타입을 void로 정의했기 때문에 컴파일에러(=빨간줄), 즉 값을 반환할 수 없다.
		//return returnValue; // [x] -> 반환한다는 뜻, 빨간줄 감, 반환타입이 void인 메소드는 값을 반환할 수 없다. 라고 나옴, 반환값이 없으니까 당연히 반환시키면 안되지
		// static void noParamNoReturn3() 반환값을 void -> int로 하면 반환을 하는 것, int로 그러면 빨간줄 사라짐
		
		System.out.println("return문 이전");
		//return; // 이건 값을 반환 안하는 것, 에러 안남, 그냥 탈출
		// [o] 값을 반환한다는 의미가 아니라 메소드를 바로 빠져 나가겠다는 의미(뒤에 아무것도 없이; 니까!) -> 특정 조건일 때 return으로 많이 씀
		// 결론: 반환타입이 void인 메소드에서 return;문은 주로 어떤 특정 조건하에서 메소드를 빠져나갈 때 주로 사용한다
		//System.out.println("return문 이후"); // 빨간줄감, 반복문할 때 봤던 unreachable code, 메소드 빠져나가니까 절대 도달불가한 코드
	
		if(returnValue%2==0) {// 100이라고 했지만 모른다고 치고, 짝수인지? 
			System.out.println(returnValue+"는 짝수"); // 100이니까 출력되고 return 하면 저 아래 main의 아래줄로감
			return; // return; 대신 else 써도 당연히 됨
		}
		System.out.println(returnValue+"는 홀수");  // 지금은 위의 return;이 실행 안될 수도 있기 때문에 지금 코드가 빨간줄 안가는 것, unreachable code가 아닌 것
		// 위 코드는 절대 실행안되는게 아니라, if조건이 거짓일 때는 실행가능함
		
		
		
		
	}//////////////////noParamNoReturn3()
	
	
	
	
	public static void main(String[] args) { //args 를 파라미터 혹은 아규먼트라고 함
		
		//2] 메소드 호출: 메소드 원형 그대로 호출 -> 위에 썼던걸 가져옴, 정의된 대로 호출됨
		noParamNoReturn(); //noPara~~~~~~ 니까 no 컨트롤 스페이스 엔터 치면 메소드 원형 호출하게됨 -
		// 컨트롤 누른채로 마우스 위에 가져다 클릭하면 위에 원형으로 이동함, 이게 출력된 것
		System.out.println(10); // 10 그대로 출력
		//System.out.println(noParamNoReturn()); 
		// 빨간줄? 그대로 출력불가, 메소드의 반환값이 있어야 출력을 해줌, 값을 반환하는 메소드가 아니라 출력x 에러, void(값반환x)로는 정의가 안된다고 나옴

		noParamNoReturn3(); // return 하면 System.out.println(returnValue+"는 홀수");  빠져나와 이 밑줄로 진행, 메소드 빠져나온 것, return하면 호출한 곳으로 바로 나옴, 실행의 return이 끝났으니까 
		
		
		
		
		
		
		
		
		
		
		
	}//////////////////main
}//////////////////////class
