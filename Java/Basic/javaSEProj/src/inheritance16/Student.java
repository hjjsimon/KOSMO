package inheritance16;

//[Student is a Person 성립]
public class Student extends Person{ //extends Person-> inheritance16의 person을 상속받게함, 멤버변수 이름, 나이, 주소, 메소드 2개 그대로 상속받음
	//학생만의 특성을 상속받은거에 추가하면 됨
	
	//[멤버 변수]
	//이름, 나이, 주소는 Person꺼 재사용
	String stNumber; //학번, Student클래스에서 학생에 맞게 새롭게 확장한 변수

	//[기본생성자] //모든 생성자에는 super(); 기본으로 추가된다고~~~
	public Student() {
		//super(); //Person의 기본생성자를 호출하는 코드, 생략해도 자동으로 호출된다, 클릭하면 Person으로 넘어감, Person은 또 Object에서 상속
		//System.out.println("Person의 기본 생성자"); 이번에는 하는 일 있음! 근데 지금 super() 지워도 됨, 부모의 기본생성자 Object 상속이 또 알아서 자동으로 이뤄지기 때문!
		//Person에 () 지우고, Person(int a) 하면 인자생성자가 만들어진거라 기본생성자가 사라짐, 다시 만들어줘야함
		//Person(int a) 하면 -> super("aaa")로 인자 1개짜리로 받아서 호출해주면 됨, 
		//****부모가 먼저 호출돼야 자식이 만들어짐, 자식의 생성자 안에서는 그래서 무조건 부모() 자동으로 만들어지는것
		//따로 만들지 않으면 기본생성자 () 괄호안에 아무것도 없는게 기본으로 세팅!
		//모든 클래스에는 상속 기본생성자가 추가됨, 위에 인자생성자 Person(int a)내가 만들어서 {} 해도 super(); 알아서 생김 
		//부모의 인자생성자를 정의하려면 기본생성자를 아무일도 안해도 같이 정의해줘라! 기본으로 가져오는데 안되면 오류 생기니까!!
		System.out.println("Student의 기본 생성자");
	}
	
	//[인자생성자] //모든 생성자에는 super(); 기본으로 추가된다고~~~
	public Student(String name, int age, String addr, String stNumber) { //이번에는 소스->제너레이트 필드에서 가져옴
		//super(); -> 기본생성자 생략된거~ 이거 생략하고 호출해도 System.out.println("Person의 기본 생성자"); 실행된다!
		this.name=name; //정의 안했는데 부모꺼 그대로 가져와서 재사용
		this.age=age;
		this.addr=addr;
		this.stNumber = stNumber;
		System.out.println("Student의 인자 생성자"); //모든 자식 생성자에는, 부모의 기본생성자가 추가된다!****
	}
	
	//[멤버 메소드]
	void study() {
		System.out.println(String.format("나이가 %s인 %s가 공부하다",age,name)); //this 없이 그냥 가도 됨
	}
	String getStudent() {
		return String.format("%s,학번:%s",getPerson(),stNumber); //이름,나이,주소에 학번 추가로 출력해야함, 메소드 getPerson(), printPerson()도 상속받음
	}
	//앞의 %s에는 getPerson() 통으로 넣고, 뒤에 %s에는 학번
	void printStudent() {
		System.out.println(getStudent()); //위의 "%s,학번:%s" -> 이대로 출력해줌
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
