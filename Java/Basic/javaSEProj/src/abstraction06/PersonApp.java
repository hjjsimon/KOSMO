package abstraction06;

// 클래스를 가지고 객체를 만든다!, 클래스에 name, age, weight(멤버변수) / sleep, eat, getperson, printperson(멤버 메소드) 있음, 가져다 쓸 예정
// [클래스의 객체 생성]
// int num=10;
// 클래스명 인스턴스 변수 = new 클래스명(); // 클래스명()는 생성자

// [클래스의 멤버 접근]
// 인스턴스변수.멤버  -> .을 레퍼런스 연산자라 한다

public class PersonApp {      ///////////App을 쓰고 만들면 class가 있는것

	public static void main(String[] args) {
		
		//1]Person타입의 인스턴스 변수 선언
		Person person1; 
		//person1=10; // 에러남, 데이터 저장 불가, 배열하고 동일, class도 참조형(클래스, 스트링, 인터페이스, 배열 4가지가 참조형)
		person1 = new Person();//new쓰면 새로운 메모리가 만들어짐, Person(생성자라고함)타입으로 heap영역에 메모리만듦!
		
		/*
		 stack에 person1이라는 메모리가 생김(Person을 int라고 생각하면됨)
		 heap 영역에 Person타입의 메모리가 만들어짐 주소가 Person@0x1234 -> 이전에는 Person대신 [] 배열이라 대괄호
		 Person@0x1234(큰사각형)에 메모리(작은사각형)가 3개 만들어짐-> 각각 name(문자열저장), age(숫자저장), weight(실수저장)
		 메소드 4개도 메모리가 만들어짐 이름 각각 메소드니까 괄호해줌 sleep(), eat(), getPerson(), printPerson()
		 총 방이 7개임
		 person1 = new Person();의 대입연산자 오른쪽이 끝남  
		 person1에는 주소가 저장 -> 참조형 -> Heap영역의 메모리를 주소로 참조함
		 접근하려면 person1.name 이런식으로 도트(점.) 붙여서 변수, 메소드로!
		 메모리에 Person@0x1234 만들어진걸 객체화, 인스턴스화 됐다고 말함
		 */
		
		//2] new 연산자로 Person타입의 메모리를 heap영역에 생성(할당)
		// 인스턴스변수 person1에는 heap영역에 생성된 Person타입의 메모리 주소가 저장됨
		// new연산자의 의미]
		// Person 클래스를 인스턴스화 한다 -> 여기부터 아래로 지금 3가지는 같은 말
		// Person 클래스를 객체화한다
		// Person 타입의 메모리를 heap영역에 생성한다
		// 위 세가지는 모두 같은 의미
		//
		//
		System.out.println(person1);
		// person이 abstraction06 패키지에 있는 것까지 나옴
		// name 스트링 -> 참조형이니까 null로 초기화 age는 1이라고 동시초기화함(태어나면서 1살), weight는 0.0 
		person1.printPerson(); // <- 컨트롤 하고 누르면 메소드니까 넘어감, 근데 또 메소드라 그 위 getPerson으로 또 넘어감, null의 정보 나이 1살 몸무게 0.0 이라고 문자열 반환
		// person1. 찍으면 나오는게 object(모든 클래스의 최상위 클래스)한테 상속받았다는 의미, 정의 우리가 따로 안함, 기본적으로 이것저것 죄다 상속
		
		//멤버 변수(필드) 초기화]
		person1.age=20; // int니까 정수 넣음
		person1.name="홍길동"; //string 문자열 넣어줌
		person1.weight=80; // 더블에 인트넣기가능 -> 저장은 80.0으로 됨
		System.out.println("[멤버변수에 값 할당 후]");
		System.out.println(person1.getPerson()); // getPerson()이 반환한 값으로 출력함
		person1.sleep(); // 홍길동이 자다
		person1.eat(); // 홍길동이 먹다, 그래서 몸무게가~이다
		
		//3]인스턴스 변수선언과 동시에 메모리 할당
		Person person2 = new Person(); // new 했으니까 메모리 생성, Person타입은 동일, 아까 heap 안에 Person@0x5678 <- 주소 다른 메모리가 똑같은 구성으로 하나 더 생김
		// 주소는 스택의 person2에 들어감
		System.out.println(person2);
		System.out.println(person2.getPerson()); // heap의 이전 Person에는 홍길동, 20, 80.00 저장되어있고, 지금 Person에는 null,1,0 초기화상태
		
		// 멤버변수 초기화]
		person2.name="가길동";
		person2.weight=3.41;
		System.out.println("[멤버변수에 값 할당 후]");
		System.out.println(person2.getPerson());
		
		person2 = new Person(); // <- person2는 그냥 위에서 쓴 변수임, 변수니까 값이 바뀔 수 있음, new 하면 또 새로운 메모리가 똑같이 생김 방 7개짜리, 당연히 주소는 달라짐
		// 새로운 주소 Person@0x5678 ->Person@0x7777 주소로 person2에 저장된 주소가 바뀜, 이전에 연결된 참조는 끊어지고 새로 연결됨, 끊어진 메모리는 더 이상 찾아갈 수 없음.
		// 언젠가 자바의 garbage collector가 이전 메모리를 걷어감, 그러면 사라짐
		// c언어는 개발자가 메모리 만들면 반드시 걷어야함, 자바는 신경쓸 필요x
		System.out.println(person2.getPerson()); //새로 세팅됨
		
		// 자바에서는 new로 새롭게 할당한 메모리를 해제할 필요가 없다
		// GC(가비지 컬렉터)가 돌아다니다가 더 이상 사용되지 않는 메모리(Unreachable Object:스택에서 참조된 끊긴 객체)는 해제한다.
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}///////////////////////main

}/////////////////////////////class
