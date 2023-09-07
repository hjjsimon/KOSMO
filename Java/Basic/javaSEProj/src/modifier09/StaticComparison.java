package modifier09;

/*
 멤버변수(x,y)에 저장된 값으로 덧셈을 하는 계산기에서는 x와 y값을 저장하는 멤버변수를 static으로 하면 안된다
 왜냐하면 각 계산기 (인스턴스화된 객체)마다 공유 메모리(static)에 저장된 값을 사용하기 때문이다
 단,x,y값을 멤버 변수에 있는 값을 사용하지 않고 add(x,y)메소드의 인자로 받아서
 덧셈을 한다면 이때는 add(x,y)메소드를 static으로해서 공유하는게 유리하다
 */


// 계산기 설계도를 2개 만든것, 메소드는 동일, 멤버변수 값으로 계산 동일, 근데 멤버변수가 다름
// 계산기 하나에

//1. 멤버변수가 인스턴스형
class CalcHavingInstance{
	int x,y; //멤버변수가 인스턴스형
	void add() { //멤버메소드 괄호 안에 매개변수(인자)가 없네? 매개변수에 값을 안받고, 멤버변수에 저장된 값으로 더하기하는 메소드
		System.out.println("덧셈 결과:"+(x+y));
	}
}


//2. 멤버변수가 정적(파이썬은 클래스변수라고함)
class CalcHavingStatic{
	static int x,y; //멤버변수가 정적, 클래스로더할 때 이미 생김
	void add() { //괄호 안에 매개변수(인자)가 없네? 매개변수에 값을 안받고, 멤버변수에 저장된 값으로 더하기하는 메소드
		System.out.println("덧셈 결과:"+(x+y));
	}
}

class CalcAddStatic{
	//멤버변수에 저장된 데이터가 아닌 인자로 전달된 값만을 가지고 더하기 -> 이때는 그냥 클래스명으로 접근, 인스턴스화할 필요 없음
	static void add(int x, int y) {
		System.out.println("덧셈 결과:"+(x+y));
		
	}
}


public class StaticComparison {

	
	
	
	public static void main(String[] args) {

		System.out.println("[멤버변수 x,y 및 add()가 인스턴스형인경우"); //정상인경우
		CalcHavingInstance chi1= new CalcHavingInstance(); // heap에 CalcHaving큰방 안에 작은방들 x방, y방, add메소드 있음
		chi1.x=10;
		chi1.y=10;           
		chi1.add(); //멤버변수 지정, 덧셈결과 20
		
		CalcHavingInstance chi2= new CalcHavingInstance(); // heap에 CalcHaving큰방 안에 작은방들 x방, y방, add메소드 있음, 계산기 또 하나 붕어빵복제
		chi2.x=100;
		chi2.y=100;           
		chi2.add(); // 이번 붕어빵 계산기는 200을 계산함
		chi1.add(); // 여기는 여전히 데이터 그대로라 계산 20
		
		System.out.println("[멤버변수 x,y 및 add()가 정적인경우");//정적으로 x, y는 static 영역에 생겨있음, 인스턴스하면 void add() 생김
		CalcHavingStatic.x=10;
		CalcHavingStatic.y=10;
		CalcHavingStatic chs1=new CalcHavingStatic();
		chs1.add();//static 영역에 x, y에 각각 10 저장, 메모리 큰방 안에 작은방 add뿐 -> 계산 시 20
		
		CalcHavingStatic.x=100;
		CalcHavingStatic.y=100;
		CalcHavingStatic chs2=new CalcHavingStatic();
		chs2.add();//static 영역에 x, y에 각각 100으로 바꿈, 이번엔 200 나옴
		chs1.add(); // 계산결과를 공유하니까 아까 20답이던 계산기가, 20이 됨, 계산기 붕어빵끼리 메모리를 따로 가져야함, static 값으로 공유하면 안됨
		
		System.out.println("[add()를 정적메소드로 정의한 경우]"); //멤버변수에 저장된 static void add(int x, int y) { , static 돼있으니 인스턴스화 안함, 인스턴스변수에 접근할필요없음
		CalcAddStatic.add(10, 10); // 전달된 값으로만 계산함, 멤버변수로 계산 안함!!! -> 이럴 때는 무조건 메소드에 static 붙여줌
		CalcAddStatic.add(100, 100);
		CalcAddStatic.add(10, 10);
		
		
		
	}

}
