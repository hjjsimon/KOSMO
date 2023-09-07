package constructor15;

//생성자 -> 무조건 메모리 생성 후 가장먼저 호출 실행, new 클래스명(); <- () 괄호열고닫고 있으면 다 메소드임, 생성자도 메소드 근데 특수한 메소드, 예외를 적용받음
//ex. String클래스에 String() 메소드 있음, 이게 생성자인셈 ~~
//메소드 정의: 접근지정자 반환타입 메소드명(){};  , 반환타입 기능정의하고, 반환타입 있으면 리턴하고~
//메소드명이 클래스명이면 생성자! 그리고 반환타입 지정x, 
//생성자: 접근지정자 메소드명(){} -> 주로 public접근지정자씀, 다른 곳에서도 쓰도록, 인자가 없는 생성자를 디폴트생성자(=기본생성자, 자동제공됨)라고 함, 매개변수 있으면 인자생성자라고 함   
//new는 객체만들라는 뜻
//생성자는 멤버변수 초기화하는 일(목적, 중요)********
//ex. Person에는 생성자만든적 없음, 근데 PersonApp에서 Person person2 = new Person(); 써도 문제없음, 자동으로 컴파일러가 제공해줌
//ex. Person에 public Person(int age){ super();} -> 내가 만들면 너가 뭘 만드는구나~ 해서 기본생성자 안만들어줌
//내가 정의해야함, 인자 받는거 없이 public Person(){ super();} 새로 써야함, 인자 달라도 ㄱㅊ, 오버로딩으로 다른 메소드
//이제 PersonApp가서 person1=new P -> 컨트롤 찍으면 Person()랑 Person(int age) 둘 다 뜸
//인자생성자를 별도 생성시 인자 없는 기본생성자를 기본으로 제공해주지 않는다는 뜻~~~~******

/*
1]생성자란? : 객체가 생성될 때(인스턴스화, 메모리가 만들어질 때) 최초로 실행되는 메소드를 의미. -> new 다음 생성자 옴, 가장 먼저 호출되는 메소드!!!****
2]생성자 특징
	- 생성자 이름은 클래스명과 동일해야 한다
	- 반환타입을 가져선 안된다.
	- 생성자의 접근지정자로는 주로 public속성
3]생성자의 역할
	- 멤버 변수를 초기화 하는 일*****
	- 생성자를 정의하지 않았을 경우 컴파일러는 default(기본)생성자를 제공해줌
    - 인자 생성자를 하나라도 정의했다면, 그 때는 컴파일러가 default(기본)생성자를 제공 해주지 않는다. -> 기본생성자 그냥 항상 만드는게 낫다
	- 생성자를 다양하게 오버로딩 함으로써 다양한 초기값을 부여할 수 있다.
*/

class Saram{//멤버변수 초기화용 메소드 별도로 만들어 멤버변수 초기화]
	
	String name;
	String lastJumin;
	
	//멤버변수 초기화용 메소드] 
	void initialize(String name, String lastJumin) {
		this.name=name; //null초기화
		this.lastJumin=lastJumin; //null초기화
	}////initialize
	void print() {
		System.out.println(String.format("%s는 성별이 %s입니다", name, lastJumin.charAt(0)=='1'?"남성":"여성")); 
		//문자로 반환, lastJumin null초기화, 주소 없어서 lastJumin.charAt(0) 찾아올 수 없음
	}////print
}////saram

class Saram2{//이번엔 초기화용 메소드 안만듦
	String name;
	String lastJumin;
	//[기본 생성자]
	public Saram2() {//메소드명 클래스명과 똑같이, 인자 없으면 디폴트생성자
		name="미상";
		lastJumin="1234567";
		System.out.println("[기본(디폴트)생성자]");
	}////Saram2
	//[인자 생성자]
	public Saram2(String name) {
		this.name=name;
		lastJumin="1234567";
		System.out.println("[인자 생성자(이름)]");		
	}
	public Saram2(String name, String lastJumin) { //우클릭, 소스, 제너레이트 콘스트럭터 유징 필드로 자동생성
		this.name = name;
		this.lastJumin = lastJumin;
		System.out.println("[인자 생성자(이름,주민번호)]");
	}
	void print() {
		System.out.println(String.format("%s는 성별이 %s입니다", name, lastJumin.charAt(0)=='1'?"남성":"여성")); 
	}
}////Saram2

public class Constructor01 {
	
	public static void main(String[] args) {

		//Saram 인스턴스화/객체화/메모리 생성]
		Saram saram=new Saram();//Saram 생성자
		
		//초기화용 메소드 미호출시]-NullPointerException발생
		//saram.print();
		saram.initialize("가길동", "1234567"); //lastJumin null초기화, 주소 없어서 찾아올 수 없음 -> 이제 초기화해서 print 제대로 됨
		saram.print();
		
		Saram2 saram21=new Saram2(); 
		//위에 기본생성자는 디폴트로 제공, 따로 정의x, 이번에 Saram2()는 새로 정의함, Saram2호출해서 name, lastJumin에 미상, 1234567값 넣고 프린트함
		//그리고 주소는 saram21에 담음
		saram21.print(); //뭐가 먼저 됐다고 했는데,, 기본생성자 먼저 출력,, 놓침
		
		//Saram2 saram22=new Saram2() //여기까지 찍으면 메소드 2개 나옴, 기본생성자, 인자생성자
		Saram2 saram22=new Saram2("나길동"); //인자생성자로 나길동 초기화됨, 생성된 메모리주소는 saram22에 들어감, 밑에서 프린트해보자
		saram22.print();
		
		Saram2 saram23=new Saram2("다길동","2345678");//이제 메소드3개 나옴, 2시작이라 여자
		saram23.print();
	}
}
