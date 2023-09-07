package constructor15;

public class Person {
	//[멤버변수]
	String name;
	int age;
	String addr;
	
	//[생성자]
		/*
		기본(default) 생성자:매개변수(인자)가 없는 생성자
		 형식]
		 public 클래스명(){
		 	super()		
		 }	
		  반환타입이 없다.
		  멤버변수 초기화에 주로 사용.
		  ※사용자가 기본 생성자를 정의하지 않으면 컴파일러가 자동으로 제공해줌	
		*/
	//private //주로 퍼블릭인데 프라이빗으로 생성자 정의하면 못찾아서 에러남, 다른 클래스에서 인스턴스화 불가하도록 막는 방법.
	//1.기본생성자]
	public Person() {//기본생성자 내에서 초기화 안했으므로 아래 초기화 없이 출력시 null,0,null나옴
		//this(); //이건 자기자신을 호출, 그리고 다시 자신 this()부름 그리고 다시 자기 자신 Person()호출, 무한반복, 밑에 sysout"기본생성자" 못나옴, Reculsive에러 (재귀)
		this("미상",1,"부모님 주소"); // this 눌러보면 인자 3개짜리 메소드로 이동하고 미상, 1, 부모님주소 출력함,(this가 인자3개짜리 생성자메소드를 호출하는 것, 그리고 sysout "기본생성자"출력함, 밑에 3줄을 안써도 됨
//		name="미상";
//		age=1;
//		addr="부모님 주소";
		System.out.println("기본생성자");
	}
	
	//2.인자생성자] 매개변수가 있는 생성자
	//인자생성자를 정의하면 기본생성자를 더이상 제공하지 않는다
	//인자생성자 계속 만들면 생성자 오버로딩에 해당
	
	public Person(String name) {//이거 저장시 Constructor02의 new Person(); 에러남, 기본생성자 더 이상 제공x, 그래서 위 Person() 또 만들어야
		this(name,1,"부모님 주소");
//		this.name=name;
//		age=1;
//		addr="부모님 주소"; //그냥 태어난 곳
		System.out.println("인자생성자:name"); 
	}
	
	public Person(String name, int age) {
		this(name,age,"부모님 주소");
//		this.name=name;
//		this.age=age;
//		addr="부모님 주소"; 
		System.out.println("인자생성자:name,age");
	}
	public Person(String name, int age, String addr) {
		this.name=name;
		this.age=age;
		this.addr=addr; 
		System.out.println("인자생성자:name,age,addr");
	}
	// 생성자를 4가지 방법으로 만듦, 기본, 인자(3가지 방식으로 초기화가능하도록)
	
	//[멤버메소드] 
	//1.멤버변수 초기화용 메소드] ->이거 호출안하면 초기화 안됨, 그냥 호출시 null, 0, null로 위의 멤버변수 불려짐
	void initialize(String name, int age, String addr) {
		this.name=name;
		this.age=age;
		this.addr=addr;
	}////initialize
	
	//2.정보출력용 메소드]
	void print() {
		System.out.println(String.format("[%s님의 정보]%n나이:%s%n사는 곳:%s", name, age, addr));
	}	
}
