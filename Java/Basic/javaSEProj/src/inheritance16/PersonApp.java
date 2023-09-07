package inheritance16;

public class PersonApp {

	public static void main(String[] args) {
		
		System.out.println("[기본생성자로 객체생성:Student]");
		Student student1=new Student(); 
		//public Student() {super();} 스튜던트 클래스의 기본생성자로 이동 -> Person의 기본생성자로 이동 -> Object갔는데 아무것도 없음 ->
		//되돌아와서 Person에서 System.out.println("Person의 기본 생성자"); 출력 후 끝남 ->
		//호출한쪽으로 다시 와서 System.out.println("Student의 기본생성자"); 출력 -> 그리고 여기로 옴
		//Person의 기본 생성자 출력 후 Student의 기본생성자 출력됨, 부모먼저 당연히!
		
		//스택에 student1, 주소0x1234생김, 힙에는 Student@0x1234 생김, 그 안에는 stNumber작은방 있음, (생성자도 그려야하는데 생략)
		//메소드 작은방은 study(), getStudent(), printStudent() 3개 존재
		//그리고 부모의 기본생성자도 생각하면 아예 통으로 Person타입(주소는의미x) 방이 작은방급으로 생겨서 그 안에
		//멤버로 name, age, addr, 멤버메소드로 getPerson(), printPerson() 있음  --> 이게 메모리 생겨있는 형태!
		//접근할때는 student1.stNumber, student1.name(Person껀데 상속받은거라 그대로 접근가능)
		//student1.name //갖다대면 오른쪽에 Person에게 상속받았다고 나옴
		student1.printStudent(); //이름:null,나이:0,주소:null,학번:null -> 나이0, 나머지null초기화상태
		
		student1.name="가길동"; //컨트롤해서 세모 보이는건 속성
		student1.age=20;
		student1.addr="가산동";
		student1.stNumber="2023학번";
		System.out.println(student1.getStudent()); //이름:가길동,나이:20,주소:가산동,학번:2023학번
		
		System.out.println("[인자 생성자로 객체 생성:Student]");
		Student student2=new Student("나길동",20,"청담동","2023학번");
		student2.printStudent(); //Person의 기본 생성자 -> 아무리 주석처리해도 나오는거 못막음
		student2.study();
		
		//Teacher teacher=new Teacher(); //[x]에러,Teacher에서는 인자생성자만 만들어서 기본생성자 없어짐
		//인자생성자를 정의해서 더 이상 기본생성자를 제공해주지 않으므로 기본생성자로 객체생성하려면 기본생성자를 직접 정의해줘야한다
		//그래서 Teacher 만들려면 인자생성자로 만들어야한다!
		Teacher teacher=new Teacher("나교사", 40, "인천", "자바");
		teacher.printTeacher();
		teacher.teach();
		
		//super는 부모, this는 자기 자신*******
		//super()는 부모의 기본생성자, this()는 자신의 기본생성자 -> 둘 다 첫줄에 있어야하므로 공존은 불가 
		//super()도 this()과 규칙이 동일!
		//this는 멤버변수와 지역변수 충돌할 때 차이구분용
		//super는 부모의 멤버와 자식의 멤버가 충돌할 때 차이구분용(부모는 super()를 붙여야함) 
		
	}////////main
}/////////class
