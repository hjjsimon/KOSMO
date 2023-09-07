package lambda27;

public class MyCalculator {

	//인자(매개변수)에 람다식(익명함수)을 받는 메소드]
	static void paramMethod(MyInterface lambda, int a, int b){//lambda에는 함수가 전달, 그 뒤는 숫자 2개 전달
		System.out.println(lambda.calc(a, b));
	}
	//람다식(익명함수)를 반환하는 메소드] -> 반환 반드시 함수형 인터페이스로 해야함
	static MyInterface returnMethod(int z) {
		return (a,b)->a*b+z; //함수도 반환시킬 수 있음
	}
	
	public static void main(String[] args) {
		
		System.out.println("[인터페이스를 구현한 클래스:더하기만 가능]");
		//방법1]인터페이스를 구현한 클래스 사용시 더하기만 가능(클래스 재사용가능, MyInterfaceImpl타입 객체 또 생성하면 됨/ 람다식은 1회용만 가능)
		MyInterface my1=new MyInterfaceImpl();//자식을 부모에~
		System.out.println(my1.calc(10, 10));
		System.out.println("[익명클래스 사용:모든 연산 가능]");
		//방법2]익명클래스(메소드안에 정의하는 클래스)로 구현, 즉 별도의 클래스 불필요(방법1보다 코드간결, 단 재사용불가)
		MyInterface my2=new MyInterface() {//여기부터
			@Override
			public int calc(int a, int b) {
				return a+b;
			}
		};//여기까지가 클래스임, 재사용은 불가, 물론 어거지로 아래처럼 또 재사용은 가능
		System.out.println(my2.calc(10, 10));
		my2=new MyInterface() {
			
			@Override
			public int calc(int a, int b) {
				
				return a-b;//코드 불편 근데 빼기 가능해짐
			}
		};
		System.out.println(my2.calc(10, 10));
		//방법3] 람다식 사용, 클래스 불필요, 방법2보다 코드 더 간결, 단 클래스 없어 재사용 불가, calc추상메소드 하나로 다양한 기능구현 가능
		//*람다식은 MyInterface의 추상메소드 형태여야한다-> 즉, 인터페이스의 추상메소드를 람다식으로 구현한다(반환값 int, 받는 인자 2개)
		//단, 추상메소드가 1개라는 전제하에 a,b로 판단한거임, 인터페이스에 추상메소드 여러개 만들면 절대불가
		System.out.println("[람다식 사용:모든 연산 가능]");
		MyInterface lambda=(a,b)->a+b; 
		System.out.println(lambda.calc(10, 10));
		lambda=(a,b)->a-b;
		System.out.println(lambda.calc(10, 10));
		lambda=(a,b)->a*b;
		System.out.println(lambda.calc(10, 10));
		lambda=(a,b)->a/b;
		System.out.println(lambda.calc(10, 10));
		//람다식(익명함수)를 메소드의 인자로 전달
		paramMethod((a,b)->a%b, 10, 3);// (a,b)->a%b 이 함수를 lambda에 전달한 것, lambda=(a,b)->a%b; 이거랑 같은 식임, 람다식도 인자로 전달가능!
		//람다식(익명함수)를 반환하는 메소드
		System.out.println(returnMethod(100).calc(10, 5));//MyInterface타입 반환, 10*5+100
		
	}

}
