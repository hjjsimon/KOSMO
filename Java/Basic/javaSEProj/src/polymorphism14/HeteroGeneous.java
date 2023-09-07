package polymorphism14;

//상속관계가 존재해야 형변환, 대입연산이 가능하다***
/*
다형성:
1]오버로딩-  같은 이름의 메소드를 한 클래스안에서
           여러개 정의 가능(양념-매개변수)
2]오버라이딩 - 상속관계에서 부모의 메소드를
            리모델링(외관은 그대로 안에만 변경)
3]Heterogeneous(이질화)
	전제조건:두 클래스 사이에 상속관계가 존재해야 함
			 부모타입 부모타입의인스턴스변수
			 = new  자식타입의 메모리 할당
   예]
   부모타입의 인스턴스 변수에 자식타입의 메모리 할당해서
   그 주소를 저장
   Person person = new Student();
   혹은
   Person person = new Person();	->person에 주소, 힙에 Person타입 메모리 생성
   Student student = new Student();	->student에 주소, 힙에 Student타입 메모리 생성
   person= student;                 ->이 코드 실행 시 student의 주소가 person에 들어감, person은 Student메모리를 가리키게 됨(Person메모리 연결끊김), 이것도 이질화
   
   ->Person person = new Student(); 이 한줄이 아래 3줄 대체 가능, Person메모리도 덜 생기고 더 좋음
   
   
부모타입의 인스턴스 변수가 접근 할 수 있는 범위
1]부모로터 상속받는 멤버
2]자식에서 오버라이딩한 메소드가 우선 호출됨.
※자식에서 새롭게 정의한 멤버(변수,메소드등)는 접근 불가.(오버로딩한 메소드도 포함)	
※이점:메소드의 매개변수를 부모타입으로 정의시 모든 자식타입을 저장할 수 있음으로 자식 클래스 수만큼 메소드를 오버로딩 할 필요 없다*****
     예]Object클래스의 객체.equals(Object) -> 그래서 equals 메소드가 1개인것!
     
-Homogeneous(동질화)
같은 타입의 인스턴스 변수에 같은 타입의
메모리 주소를 저장
예]
Student st = new Student();
*/

class Person{
	String name;
	void personMethod() {
		System.out.println("부모의 메소드:personMethod()");
	}
}////Person

class Student extends Person{
	
	String stNumber; //자식에서 새로 만든 멤버변수
	
	void study() {//자식에서 새로 만든 멤버메소드
		System.out.println("자식에서 새롭게 확장한 메소드:study()");
	}
	@Override
	void personMethod() {//부모꺼 가져와서 리모델링
		System.out.println("자식에서 오버라이딩한 메소드:personMethod()");
	}
	void personMethod(int num) {//부모에 없음 int타입
		System.out.println("자식에서 새롭게 확장한(오버로딩) 메소드:personMethod()");
	}
	
	
	
}////Student

public class HeteroGeneous {

	public static void main(String[] args) {

		//1.[동질화]: 인스턴스변수로 모든 멤버에 접근가능, 오버라이딩한 메소드가 존재할 경우, 부모의 메소드가 아니라, 오버라이딩한 메소드가 무조건 호출됨
		Student st=new Student(); //지금 바깥을 가리키는 상태, 
		//멤버 변수류
		System.out.println(st.name); //부모꺼 접근
		System.out.println(st.stNumber);//자식에서 새로 정의
		//멤버 메소드류
		st.personMethod(); //오버라이딩, 부모가 준 personMethod()호출x, 리모델링한 상태로 호출됨
		st.personMethod(10); //오버로딩, 자식이 새로 만든거 호출됨
		st.study();
		
		//2.[이질화] -> Student 안에 작은 Person 상속방을 가리킨다*************
		/*이질화]-자식에서 새롭게 정의 한 멤버(변수,메소드)
		부모타입의 인스턴스변수로 접근 불가
		단,부모타입의 인스턴스 변수를
		자식타입으로 형변환(강제적 형변환-다운캐스팅) 하면
		자식에서 새롭게 정의한 멤버 접근가능
		※ 동질화든 이질화든 오버라이딩한 메소드가 우선 호출된다.*********
		 */
		Person p=st; //변수는 Person타입, 메모리는 Student타입, 당연히 꼭 new 안해도 되고, 그냥 변수대로 가도됨
		//멤버 변수류
		System.out.println(p.name); //상속받은거 그냥 보임
		//System.out.println(p.stNumber); //자식에서 새로 만든거라 안보임, is not a field에러
		//멤버 메소드류
		st.personMethod(); //오버라이딩
		//st.personMethod(10); //오버로딩(새롭게 추가)[x]
		//p.study(); //[x] 찾을 수 없음, 자식꺼임
		
		Person p2=new Student();
		System.out.println(p2.name);//null
		p2.personMethod();//오버라이딩한대로 나옴
		//p2.stNumber//자식꺼라 안뜸
		
		//방법1]형변환후 바로 접근
		System.out.println(((Student)p2).stNumber); //p2까지 한번 더 감싸야함
		((Student)p2).personMethod();
		((Student)p2).study();
		
		//방법2]자식타입의 인스턴스 변수에 담아서 그 변수로 접근 ->한번에 쉽게! 위처럼 반복코딩 없앰
		Student s=(Student)p2; //계속 접근해야하면 변수에 담는게 편하고, 몇개 없으면 위처럼 해도 되고
		System.out.println(s.stNumber);
		s.personMethod(0);
		s.study();
		
		
	}////main

}////class
