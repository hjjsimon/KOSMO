package polymorphism14;
/*
-자바의 모든 클래스의 최상위 부모는 Object클래스이다
1)Object클래스의 toString()메소드는 객체의 주소를 String으로 반환해주는 메소드다****** 	-> 이렇게  패키지명.클래스명@주소(16진수)
 ※toString()메소드 오버라이딩시 반드시 hashCode()메소드(인트값을 반환함)도 오버라이딩하자 -> 해시코드는 메모리 주소를 십진수 인트값으로 반환함
 
 Object클래스의 String toString()메소드]
		 :객체의 주소를 문자열로 반환
		  패키지명.클래스명@주소(16진수)
 	

2)Object클래스의 equals()메소드는 두 객체간의 인스턴스비교, 즉 주소값 비교,주소가 같으면 true,다르면 false반환
	 
 Object클래스의 boolean equals()메소드]
        : 비교 클래스의
          인스턴스변수.equals(대상클래스의 인스턴스변수)
          두 객체의 주소비교
*/

class MyClass{
	int data;//데이터 저장용, 아래 생성자로 생성하면 세팅이 될 것(괄호 오른쪽을 왼쪽에 넣음)
	//인자 생성자]
	public MyClass(int data) {
		this.data = data;
	}////MyClass
	
	//실제 저장된 데이터 10(위에 int data)가 반환되도록 Object로부터 상속받은
	//toString()메소드 오버라이딩(재정의)
	@Override//소스->오버라이딩으로 만듦
	public String toString() {
		return String.valueOf(data); //지금 super 왼쪽은 Object임, 이거 두고 실행하면 결과 동일, 지금 data에 저장된 10을 가져오고싶음, 오버라이딩 규칙상 String반환은 고정해야함
		//int를 String으로 바꿔야함!
		//해시코드도 오버라이딩 해야한다~
	}
//	@Override//소스->오버라이딩으로 만듦
//	public int hashCode() {
//		return data; //이건 toString하면 기본으로해라, 여튼 이번엔 data 반환하도록 오버라이딩함
//	}
	//실제 저장된 데이터를 비교하도록 equals(Object)메소드
	//오버라이딩]
	@Override
	public boolean equals(Object obj){//obj Object타입이라 위의 data 안뜸, 확인! 
		if(obj instanceof MyClass) {
			MyClass mc= (MyClass)obj; //obj가 Myclass타입일때 들어감
			if(this.data==mc.data) return true; //this 확인
			else return false;//값이 다른 경우
		}
		return false;//타입이 MyClass가 아닌경우, 
	}	
	//toString()오버라이딩 후 메모리 주소를 문자열로 반환하는 메소드
	//단,hashCode()를 오버라이딩 하면 안된다 ->메모리코드를 10진수로 바꾸는 메소드 얘가 toString의 재료라 얘마저 바뀌면 안됨
	String getAddress() {
		return super.toString(); //주소를 문자열로 반환해주는게 toString!!
	}////getAddress	

}////MyClass

class Point{//Point에서는 toString, equals 오버라이딩 안함
	int x,y;

	public Point(int x, int y) { 
		this.x = x;
		this.y = y;
	}
	//문1]"x=10,y=20"처럼 반환되도록 toString을 오버라이딩하라 
	
	 	
	@Override
	public String toString() {
//		return String.format("x=%d,y=%d",x,y);	//샘
		return "x="+x+",y="+y;					//이것도 됨, 간지가 안남
//나		String str1= String.valueOf(x);
//		String str2= String.valueOf(y);
//		return String.format("x=%s, y=%s",str1,str2);
	}
	//문2]저장된 x좌표와 y좌표가 같은지 다른지 비교하도록 equals 메소드를 오버라이딩하라 -> point1이 가리키는 객체의 x좌표 10, point2도 x좌표 10, y좌표도 20,20으로 같음 같으면 true
	public boolean equals(Object obj) {
		if(obj instanceof Point) {
			Point point=(Point)obj;
			if(x==point.x&&y==point.y) return true;
			else return false;//값이 다른 경우, point2의 y=21로하면 다르니까 false나옴
		}
		return false;//타입이 Point가 아닌 경우
	}
	
	
}////Point

public class ObjectClass {

	public static void main(String[] args) {

		MyClass mc1=new MyClass(10);
		MyClass mc2=new MyClass(10);
		System.out.println("[두 객체의 toString() 호출]");//Object에게 상속받은 toString 출력(오버라이딩x)
		System.out.println(mc1);//주소로 나옴, toStirng 찍어보면 Object로 나옴 --> 위에 오버라이딩하면 10나옴
		System.out.println(mc1.toString()); //--> 오버라이딩하면 10나옴
		System.out.println(mc1.getClass().getName()+'@'+Integer.toHexString(mc1.hashCode())); //mc1을 10진수로 바꾼 주소를 Hex 16진수로?! -> 10이 16진수가 a? 그게 나옴
		//getClass().getName() + '@' + Integer.toHexString(hashCode()) -> 이거랑 toString 같은거라고 홈피에 나옴
		//위에 해시코드 만들었을 때는 a 생략시 16진수로 나옴
		System.out.println(mc2);
		System.out.println(mc2.toString());
		System.out.println(mc2.getClass().getName()+'@'+Integer.toHexString(mc2.hashCode())); 
		System.out.println("[두 객체를 equals()메소드로 비교]");
		System.out.println(mc1==mc2?"Same":"Different"); //주소 달라~
		System.out.println("mc1의 주소:"+mc1.getAddress()); //mc1주소
		System.out.println("mc2의 주소:"+mc2.getAddress()); //mc2주소
		//당연히 주소 다르지, 타입은 MyClass로 같지만
		//당연히 쓸데없이 메소드 만들고 쓸 필요없음, 이미 만들어진 toString 메소드 쓰면 되지 바보도 아니고
		System.out.println(mc1.equals(mc2)?"같다":"다르다"); //equals도 오버라이딩 할 생각, 값 10 10이 같은지 다른지 나오도록
		//getAddress -> 이전의 toString을 오버라이딩해서 이전상태로 못쓰니까 새로 내가 만든 메소드! 주소를 알고싶어서~
		
		Point point1=new Point(10,20);
		Point point2=new Point(10,20);
		System.out.println("[toString()호출]"); 
		System.out.println(point1);//toString생략해도 동일함, 주소 나옴
		System.out.println(point2);
		System.out.println("[equals()호출]");//주소 다르니 false나옴
		System.out.println(point1.equals(point2));
		
		
		
	}////main

}////class
