package constructor15;

/*
this() -> 자신의 클래스, .찍으면 클래스 자신의 모든 메소드 뜸, () 있어서 얘도 메소드, 인자는 없어서 기본생성자 ---> 그냥 호출하는 인자 개수에 맞는 생성자 메소드 
-자기 자신의 생성자를 의미
-항상 생성자안에서만 사용 가능 ->일반메소드에서 호출시 에러남, 생성자안에서만!
-생성자 안에서도 맨 첫번째 문장에 와야한다.
-멤버변수만큼 인자를 가진 인자 생성자를 호출하기 위해서 주로 사용 -> this()는 잘 안씀, 
 (멤버변수보다 인자가 적은 생성자 안에서)
*/

//this();하면 기본생성자 호출, this("~~") 하면 인자 1개인 인자생성자 호출, 이 때 인자 2개인 생성자메소드 안에서 1개인 인자생성자 호출해도 됨,
//근데 this(name);, this.name=name; 이랑 같은 코드임

class Point{
	//[멤버 변수]
	private int x,y;
	//[기본 생성자]
	public Point() {
		//this(); [x] 재귀에러
		this(1,2); //이제 밑에 두줄 필요 없음
//		this.x=1; //this 생략해도됨, 자기자신이라?
//		this.y=2;
		System.out.println("기본 생성자");
		//this(1,2); [x] 둘 다 초기화? 안됨, 첫번째문장 필수  
	}////Point
	//[인자 생성자]-> x만 초기화
	public Point(int x) {
		this(x,2);
//		this.x = x;
		System.out.println("인자생성자:x");
	}
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		System.out.println("인자생성자:x,y	");
	}
	//[멤버 메소드]
	void print() {
		//this(1,2); [x]생성자안에서만 호출가능
		System.out.printf("X:%s,Y:%s%n",x,y);
	}
}

public class Constructor03 {

	public static void main(String[] args) {
		
		//[기본 생성자로 객체 생성]
		Point point1=new Point(); //왼쪽 실행시 public Point() {로 이동, this(1,2); x랑 y를 1,2로 초기화, 그리고 System.out.println("인자생성자:x,y	"); 호출, 그리고 System.out.println("기본 생성자"); 호출, 그리고 밑에 프린트로System.out.printf("X:%s,Y:%s%n",x,y); 출력 
		point1.print();
		
		//[인자 생성자로 객체 생성]
		Point point2=new Point(10);
		point2.print();
		Point point3=new Point(10,20);
		point3.print();
	
		
	}////////main
}////////////class
