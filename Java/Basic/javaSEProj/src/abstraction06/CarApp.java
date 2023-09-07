package abstraction06;

// 자동차 추상화를 만들자
// = 자동차 설계도를 만들자
// = 자동차 클래스를 만들자

// 1. 클래스는 객체의 설계도다!!!! 
// 2. 클래스는 내가 만든 자료형이다!!!!

class Car{ // 원래 .java에 클래스 1개, 이렇게 여러개 만들면 안됨
	
	//[멤버상수](클래스의 구성원이다), 자동, 수동 모델로 자동차 만듦
	public static final String AUTO="자동"; //final만 붙이면 상수, public(접근지정자) static(지정자)은 기본으로 붙임, 나중에 알게됨
	public static final String MANUAL="수동";
	
	//[멤버변수]-> 자동차의 성질
	String carModel; //차량모델(제너시스,싼타페 등등)
	String carGear = AUTO; //차량기어
	int carYear; //차 연식몇년식?
	Person owner; //차량소유주 -> 내가만들었던 이름, 나이, 몸무게 -> Person에서 가져오면됨, 차량소유주 등등 사람정보
	
	//[멤버 메소드] . 쓰면 Person타입의 멤버 가져옴
	void drive() {// 지금 carModel은 null, carGear는 AUTO(위에 지정함), owner은 null 등등 
		System.out.println(owner.name+"가(이) "+carModel+"을(를) 운전한다");
	}
	
	//[프로그램 효율을 위한 멤버 메소드들] 자동차에는 없고 프로그램용
	//멤버변수 초기화용 메소드
	void initialize() { 
		carModel="제너시스 G80";
		carYear=2023;
		owner = new Person();
		owner.name="가길동";
		owner.age=20;
		owner.weight=70;
	}
	
	//정보 출력용 메소드
	void printCar() {
		System.out.println("[차량정보]");
		System.out.println("모델명:"+carModel);
		System.out.println("연식:"+carYear);
		System.out.println("기어:"+carGear);
		System.out.println("소유주");
		owner.printPerson(); //이전에 만든 메소드
	}
	//설계도 다 만듦, 이걸로 객체를 만들 것
	
	
}///////



public class CarApp {

	public static void main(String[] args) { //이건 main 컨트롤 스페이스 엔터
		
		// Car클래스(설계도)로 Car타입의 메모리 생성(객체화, 인스턴스화)
		Car car1 = new Car(); // car1은 스택에 쌓임
		
		/*
		 stack에 car1 메모리 생성, heap에 Car@0x1234 메모리(큰방) 생김
		 내부에 carModel, carGear("자동"으로 초기화), carYear(0초기화), owner(null), /메소드 drive(), initialize(), printCar()  등 작은방 생김 
		 위에까지가 new 끝남, car1에는 0x1234 주소 저장, car1이라는 이름으로 참조함, 메소드 가려면 car1.carModel 이런 식으로 멤버 다 뜨면 찾아가면됨
		 */

		System.out.println("[멤버변수 초기화용 메소드(initailize) 호출전]");
		//car1.printCar(); // 정보출력용메소드에 쓴대로 뜸, 근데 owner.printPerson();이 가리키는 곳이 없으니 NullPointerException 에러뜸
		
		System.out.println("[멤버변수 초기화용 메소드(initailize) 호출후]");
		car1.initialize(); // carModel에 제네시스G80들어가고, carGear는 안건드렸고, carYear 2023바뀌고, new Person 때문에 Person타입 메모리가 생김
		// Person타입(heap영역,@0x5678,큰방)에는 name, age, weight,/메소드들 eat(), sleep(), printPerson(), getPerson()(작은방)만들어짐
		// car 클래스 owner에는 이제 주소가 들어감 0x5678 생김
		// car1은 근데 메인에서 선언한 변수, car 클래스에는 owner(??)
		// 여튼 초기화 종료 그리고 아래로 출력
		car1.printCar(); 
		// 출력 끝나면 main 아래줄 다시 시작
		Car car2= new Car();
		car2.carModel="포르쉐";
		//클래스의멤버(변수든 메소드든 상수든 등등)에  static이 붙으면 클래스명.멤버로 바로 접근가능함
		//위에 Car car2= new Car(); 처럼 변수로 접근안해도됨, 다른 클래스는 car1하던식으로 접근해야함 static 아니면
		car2.carGear=Car.MANUAL; //이건 미리 알아두기, Car은 클래스명. 점 찍으면 오토/ 메뉴얼 뜸, ->얘네는 static 붙어있었음
		car2.carYear=2023;
		car2.owner=new Person(); //메모리 만들어짐, 주소 저장됐을 것, 찾아가면 되겠네
		car2.owner.name="나길동";
		car2.owner.age=20;
		car2.owner.weight=60;
		car2.printCar(); //출력
		car2.drive(); //나길동가(이) 포르쉐을(를) 운전한다 출력
		
		/*
		 Car car2= new Car(); -> car2메모리가 stack에 만들어짐, Car타입으로 new! Car@0x1111 메모리 똑같은 형식으로 heap에 메모리 생성
		 car2 스택에는 0x1111 저장 -> Car@0x1111 참조(포인트,가리킴)함
		 heap의 Car@0x1111의 carModel에는 포르쉐, carGear는 수동, carYear에는 2023 저장, 
		 그리고 car2.owner=new Person(); -> owner는 Person타입으로 메모리 생성, heap영역에 Person타입 메모리 또 생김
		 Person@0x2222 생기면 car@0x1111의 owner에 0x2222 주소가 저장됨 -> Person@0x2222찾아감
		 근데 클래스는 달라짐, car2.owner.name 이렇게 따라가면 Person@0x2222(큰방)의 name(작은방)에 나길동 age, weight도 저장, 그리고 출력하면됨
		  
		 */
		
		
		
		
		
		
		
		
		
		
	}/////////main
	
}////////////class
