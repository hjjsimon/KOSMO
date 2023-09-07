package datatype01;

public class VariableDeclaration {

	public static void main(String[] args) {
		System.out.println("[변수선언 방법 첫번째]");
		//자료형(data type) 변수명;  <-------기본 형태
		int num;//int형(숫자-정수/3.14는 실수)을 저장할 수 있는 num이라는 이름의 메모리를 할당해주세요. 실행 시 의미
		//*지역변수는 초기화하지 않고 사용시 컴파일오류
		//System.out.println(num);//[x] 
		num = 100; // =라는 할당 연산자, 100이라는 숫자를 num이라는 메모리에 넣은 것
		System.out.println(num);//[o] 데이터 100이라는 값을 왼쪽의 num에 할당해서 초기화(시작,이니셜라이즈)가 된 것.
		System.out.println("[변수선언 방법 두번째-선언과 동시에 초기화]");
		//1] 200을 저장할 메모리 생성이 먼저: int initNum
		//2] initNum 메모리 만들고 200이 변수로 들어감: initNum = 200;
		int initNum =200; //한 라인에 선언과 동시에 initnum 메모리를 만들고 200을 initnum 안에 복사하는 것 8, 11 두 줄을 한줄에
		System.out.println(initNum); //이거 안치면 위의 initnum이 빨간줄 됨
		System.out.println("[변수선언 방법 세번째-동시에 동일한 타입의 변수 여러개 선언]");
		//, 콤마로 구분해서 같은 자료형의 변수 여러개 선언 또한 초기화도 같이 할 수 있다.
		int fnum,snum=1000,tnum; //인트타입 3개 넣음. 중간에 int 같은거 넣으면 에러남
		//System.out.println(fnum); //[x]fnum만 넣으면 초기화가 안돼서 피바다
		fnum = snum; //snum에 저장된 데이터 1000을 fnum에도 복사하겠다는 뜻, fnum 초기화
		System.out.println("fnum="+fnum);//[o]더블쿼테이션에 저장된 값이 그대로 뜸
		//fnum = 1; //변수니까 변하는 수 기존 1000값을 지우고 1로 바꿈
		//fnum = snum + tnum; //[x]tnum 초기화 안돼서 빨간줄
		tnum = 500;
		fnum = snum + tnum; //[o]tnum 초기화 안돼서 빨간줄, 이 코드 실행 시 1500됨
		System.out.println("fnum="+fnum);//[o] fnum이 1000에서 1500으로 바뀜
		
		//100 = fnum; //[x]100 먼저 쓴건 상수, 피바다. initnum안에 200을 복사, 처음 200을 넣은 메모리는 상수값. 결론적으로 왼쪽엔 무조건 변수가 와야함
		// 위에 // 없애면 할당연산자(대입연산자,=) 왼쪽에는 변수만 와야한다고함, 오른쪽에는 값이나 값이 저장된 변수가 온다.
		//int fnum; //[x]중복돼서 이름 바꾸라고함, 위에 이미 fnum 선언됨 변수 앞 자료형 int는 무조건 선언의미! 같은 지역({}블락 내)에는 같은 이름 불가능
		int fNum; //[o] fNum이 대문자라서 다른 fnum과 다른 메모리인 것, 자바는 엄격하게 대소문자를 구분한다.
		
		//int 4you;//[x] 2-1]숫자시작불가
		int you4,y4ou;//[o] 숫자시작아니면 갠찬
		//int special#var;//[x] 2-2]특수문자 불가, _ 랑 $만 됨
		int _underbar,under_bar,underbar_;
		int $dollar,doll$ar,dollar$;
		
		//int public; //[x] 2-3] 예약어는 변수로 사용 불가
		int puBlic; //[o] B 들어간건 예약어 아니라 괜찮음.
		
		
		
		
		
	}////main

}///class
