package abstraction06;

/*
 클래스명.java 파일에 여러개의 class를 정의할때는 main메소드가 있는 클래스에 public을 붙여라
 그 외 클래스는 public 생략(main이 있는 class에 퍼블릭 붙여야함)
 원칙은 클래스명.java 파일당 내가 정의한 클래스 하나 선언
 */

// 학생을 추상화하자, 아래에 학생 클래스 정의함, 이제 찍어내면 됨
class Student{
	//[멤버변수] 아래 Person자료형타입의 person 아 왜 이름 똑같이 만들어서
	//heap 영역에 생기는 Person 자료형의 메모리가 객체(=현실 자동차, 설계도로 찍어낸것, 이게 실체, new로 만든 것), 
	Person person; // 자료형은 Person(클래스) -> 참조형  -> 멤버변수는 초기화됨, 클래스는 참조형, null로 초기화된것, 주소가 없음, 그래서 아래 .으로 가리키지못함, 아래 another은 new 있으니까 주소 들어있음, 가리킬 수 있음
	Person another = new Person(); // 선언과 동시에 메모리 할당.
	String stNumber; //학번 속성
	//[멤버 메소드]
	void study() {
		System.out.println(person.name+"가(이) 공부하다"); //위에 person변수가 뜸
	}
	//프로그램 효율성을 위한 멤버 메소드]
	void printStudent() {
		person.printPerson();                       // 주소가 있어야 .으로 가리킬 수 있음
		System.out.println("학번:"+stNumber);
	}
}


public class StudentApp { /// App 붙이면 무조건 main 체크

	public static void main(String[] args) {
		
		//Student타입의 메모리생성 = Student타입 객체화 = Student타입 인스턴스화 //같은말3개 무조건 암기
		Student student1 = new Student();
		System.out.println("student1:"+student1); // 주소 나옴, Student 타입의 자료형으로
		//student1.printStudent(); [x] 실행 시 에러(런타임에러)/ 빨간줄은 컴파일에러
		System.out.println("student1.person:"+student1.person);
		/*
		 Exception in thread "main" java.lang.NullPointerException: Cannot invoke "abstraction06.Person.printPerson()" because "this.person" is null
	at abstraction06.Student.printStudent(StudentApp.java:21)
	at abstraction06.StudentApp.main(StudentApp.java:36)
	
	빨간줄은 안갔는데 런타임오류, nullpointerexception -> 참조한다 = point한다 라고 함, 
	스택의 메모리 내에 아무 주소도 없으면 가리키는 메모리가 없는 것, 
void printStudent() {
		person.printPerson();
		System.out.println("학번:"+stNumber);
	}
	-> 여기 person이 메모리가 없는 것
		 */
		
		/*
		 위 코드 내용
		 stack에 student1 생김, heap에 Student@0x1234(큰방=객체) 메모리 생김(Student자료형 타입)
		 (작은방들)person메모리, another메모리(얘는 선언과 동시에 Person타입메모리 할당), stNumber메모리, // 메소드는 study(), printStudent() 만들어짐 ->student1에 넣음
		 heap영역에 Person타입@x5678(큰방=객체) 메모리가 생김(another때문에) -> name, age, weight, 메소드 4개까지 작은방들 생김
		 이 Person타입 큰방의 주소가 heap의 Student@0x1234(큰방) 내에 another(작은방) 내에 주소가 저장됨, -> Person@x5678 참조
		 Student student1 = new Student(); -> student1에 0x1234 넣음
		 student1.printStudent(); 해서 따라가보니 ->person.printPerson();  null, 값이 없는데 어떻게 찾아가,
		 person.printPerson(); -> 이게 person 말고 another면 0x5678로 Person0x5678로 가서 printPerson찾을 수 있음, 가리키는 메모리가 있으니까
		
		 */
		
		/*
		 에러이유 다시
		 student1.person이 null임으로 즉 Person타입의 메모리를 가리키고 있지않음(참조하지않음)으로 에러발생 
		 */
		
		//해결방법1] Person타입의 메모리 생성해서 주소 대입
		student1.person = new Person(); //person은 Person타입/ new로 Person 타입의 메모리가 또 생김, 새로 만들었으니까 주소 당연히 다름 Person@0x5678이랑 똑같은 구성이
		// Person@0x7777 주소로 생김 -> Student@0x1234 내 person 내에는 주소 Person@0x7777이 들어가게 됨
		System.out.println("student1.person(Person타입 주소 대입후):"+student1.person); 
		student1.printStudent();// [o] 이제 가리키는 메모리가 있으니까 에러 안남, 학번은 String이니까 null 나옴
		//초기화 
		student1.stNumber="2023학번"; // 그냥 . 점 찍으면서 하나하나 주소 찾아가면됨
		student1.person.age=20; //이건 . . 2번 찍어 찾아감
		student1.person.name="가길동";
		student1.person.weight=99;
		System.out.println("[초기화 후]");
		student1.printStudent();
		
		/*
		 Student@0x1234의 stNumber에는 2023학번, Person@0x7777의 이름, 나이, 무게는 위 설정으로 바뀜
		 */
		
		//해결방법2]another의 주소값을 person에 대입 0x5678 들어가도록함
		student1.person=student1.another; // 주소를 같게 만들어줌
		System.out.println("student1.person:"+student1.person);
		System.out.println("student1.another:"+student1.another);
		//초기화
		student1.person.name="나길동";
		student1.another.age=100;
		student1.person.weight=150;
		student1.printStudent();     //나길동의 정보 출력
		
		/*
		 * 자바에서는 메모리를 Heap과 Stack 2가지 영역으로 나누어 사용, Heap영역은 동적으로 할당되는 객체들이 저장되는 곳
		 * heap에 저장된 객체들은 GC로 관리, 객체의 크기와 수명은 프로그램 실행 중 동적으로 결정, Heap의 참조가 없는 객체는 GC가 가져감, 
		   stack영역은 메소드 호출 시에 생성되는 지역변수와 매개변수가 저장되는 곳, main 내 매개변수도 지역변수임
		   Last in first Out(LIFO구조, 자료구조의 하나) -> 차곡차곡 쌓으면 맨마지막에 쌓인것부터 꺼내옴
		   메소드 (호출이)는 종료되면 stack에 쌓인건 하나씩 자동으로 제거됨
		   heap과 stack 모두 jvm이 처리해주는것
		 */
		
		
		
		
		
	}///////////////main

}////////////////////class
