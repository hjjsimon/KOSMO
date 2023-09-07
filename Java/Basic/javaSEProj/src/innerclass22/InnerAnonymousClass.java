package innerclass22;
/*
익명 클래스]
-이름이 없는 클래스
-GUI프로그래밍 시 주로 사용(이벤트 처리하기 위해서)
-부모 클래스의 메소드를 오버라이딩하는 것이 주된 용도*****아래 예시도 오버라이딩
-메소드 안에 정의된 클래스
-이름이 없어서 부모클래스의 생성자를 빌려서 인스턴스화 한다
-외부클래스명$1.class,외부클래스명$2.class 등..즉 클래스명이 없기때문에 만들어진 순서대로 인덱스가 부여되서 내부클래스명이 된다
 형식]
 부모클래스명 인스턴스변수 = new 부모클래스명(){ <-여기 중괄호 사이가 내부클래스, 이름이 없으니 부모 생성자를 빌림
 };//반드시 ;을 붙여라
*/
//Comparator 인터페이스인데 new?불가, 근데 이걸 이름을 빌리면 new가능, 클래스 이름이 없으면 함, 콜렉션엔어레이솔트해 있음 
//메인메소드 안에 정의했던 이름없는 익명클래스, {} 중괄호 안은 그냥 클래스임!, 부모인 Comparator의 이름을 빌린 클래스 ex. 허재준x 허진의 아들, 이런셈
/*		Arrays.sort(names,new Comparator<String>() {//원본배열이 재배치 된다(in-place방식) 동일
			int newVar=10; -> 이런건 원래 (클래스명)으로 접근해야하는데 클래스명 없음, 그래서 새롭게 정의한건 부모이름으로 찾을 수 없음, 자식에서 새로 정의한거니까!
			@Override
			public int compare(String src, String target) {
				//숫자는 -, 문자열은 compareTo
				return target.compareTo(src); //src가 앞이면 오름차순
			}//Comparable 모르겠다 싶으면 그냥 항상 구현하면 됨 T[]에는 배열 넣어주면 됨
		});	*/
class Person{//클래스 이름이 Person! 
	String name;

public Person(String name) {
	this.name = name;
}
	@Override
	public String toString() {
		return "이름:"+name;
	}	
}
class Student extends Person{
	String stNumber;//자식에서 새로확장한 변수
	public Student(String name, String stNumber) {
		super(name);
		this.stNumber = stNumber;
	}
	String get() {//자식에서 새로확장한 메소드
		return String.format("%s,학번:%s", super.toString(),stNumber);//super.name해도 됨, 부모에서 오버라이딩한걸 그대로 상속받았으니까
	}
	@Override
	public String toString() {//자식에서도 오버라이딩함 
		return get();
	}	
}
//추상클래스
abstract class AbstractClass{
	abstract void abstractMethod();
}
//인터페이스
interface Inter{
	//추상메소드->어차피 인터페이스는 추상뿐, abstract안붙여도됨
	void abstractMethod();
}

public class InnerAnonymousClass {

	public static void main(String[] args) {
		//[이름이 있는 자식클래스(Student)의 일반적인 이질화형태]
		Person person=new Student("홍길동","2023학번");
		System.out.println(person);//toString부모,자식 둘 다 오버라이딩, 지금 자식 toString 호출됨
		//[자식에서 새롭게 확장한 멤버 접근]-형변환(다운캐스팅)
		Student student=(Student)person;
		System.out.println(student.stNumber); //윗코드 해야 .찍힘
		System.out.println(student.get());
		
		Person anony=new Person("나길동") { //동질화, 근데 Person기본생성자 없음, 그래서 인자생성자써야함, 그래서 인자 넣어줌
			//멤버변수
			int newVar;//익명클래스에서 새롭게 확장한 멤버
			//멤버메소드
			void newMethod() {//익명클래스에서 새롭게 확장한 멤버
				System.out.println("익명클래스에서 새롭게 확장한 메소드");
			}
			@Override
			public String toString() {//또 오버라이딩
				return super.toString()+",newVar:"+this.newVar;
			}
		};//{}은 Person을 상속받은 익명클래스다!
		System.out.println(anony.getClass().getName());//innerclass22.InnerAnonymousClass$1 -> 처음만든 익명클래스라 $1나옴, anony에는 주소저장 
		//[자식에서 새롭게 정의한 멤버접근]
		//다운캐스팅:(자식클래스명)부모타입인스턴스변수
		//클래스명이 없으므로 다운캐스팅 불가, 그러므로 자식에서 새롭게 정의한 멤버는 접근불가, 즉 익명클래스는 오버라이딩이 목적
		//((InnerAnonymousClass$1)anony).//불가
		System.out.println(anony);
		//추상클래스를 상속받은 익명클래스]
		AbstractClass ac=new AbstractClass(){//추상클래스라 불가, 익명클래스로 해야함, 오버라이딩 빨간줄해결
			@Override
			void abstractMethod() {
				System.out.println("추상메소드 오버라이딩:추상클래스");			
			}
		};	
		ac.abstractMethod();
		//인터페이스를 상속받은 익명클래스
		Inter inter=new Inter() {
			@Override
			public void abstractMethod() {
				System.out.println("추상메소드 오버라이딩:인터페이스");	
			}
		};
		inter.abstractMethod();
		
	
		
	}

}
