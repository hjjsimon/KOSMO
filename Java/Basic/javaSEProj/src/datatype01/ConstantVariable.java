package datatype01;

public class ConstantVariable {
	////////////////////클래스안
	
	int num; // 클래스안 여기도 지역이 틀리니 괜찮. 아래 메소드 내의 num들과 생성되는 메모리 다름
	
	void add() {
		///////////////////////////여기는 클래스안아님
		
		int num; //이건 블락 안에 선언됐으니 지역변수, add메소드 안에 num이라는 메모리

	}
   
	void minus() {
		
		int num; // 지역변수, 클래스 안이 아님, 이건 에러 안남 다른 지역의 허재준, 같은 지역내에서 중복은 안됨, minus메소드 안에 num이라는 메모리
	}
	
	
	////////////////////클래스안 ----------------------------------------아래부터 final 시작
	
	//final double PI; // 클래스 안에서 상수 선언 시, 반드시 선언과 동시에 초기화하거나, 초기화하지 않을 시 생성자에서 초기화해야한다.(생성자 아직 안배움)
	//[x]위에는 초기화 안해서 에러남
	
	static final double PI=3.14;//[o]초기화,PI는 double형 상수,,, 이제 퍼블릭 아래로 고고
	
	static final int SCISSORS =1; //밑의 컴퓨터 -> 1 인게 int형 상수니까 int로 함
	static final int ROCK =2;
	static final int PAPER =3;
	
	public static void main(String[] args) {
		
		// 변수 Variable, 상수 Constant variable -> 상수도 베리어블이긴함. 일정한 변수. 그냥 각각 다른 메모리공간을 차지함	
		// 변수명은 소문자시작, 상수명은 모두 대문자 -> 구분목적	
		// 상수: 저장된 값이 절대 변하지 않는 메모리의 한 종류
		// 선언방법: final 자료형 변수명=초기값; 이전에 하던거 동일 앞에 final만 붙이면 됨, 동시에 초기화하나 final로 마지막, 밀봉해버림
		// 상수명 선언 후 중간에 값 변경 시 에러
		// 상수는 보통 대문자로 선언
		// 상수는 프로그램 가독성 높이고, 유지 보수에 유리, 또는 고정된 데이터를 상수로 선언한다
		
		// 클래스 안이 아닌 곳에서 선언된 변수 나 상수를 지역변수 혹은 지역상수라 한다.
		// 클래스 안은 메인과 클래스 사이공간
		// 특정 블락(지역)에서 선언된 변수 나 상수를 지역변수 혹은 지역상수라 한다
		
		
		System.out.println(PI); //위에 final 앞에 static 붙여야 에러안남
		//PI=3.141592653589793; // [x] PI는 위에서 final 해버려서 값이 변경될 수 없다고함. 3.14로 이미 픽스됨
		
		final String NICKNAME; //이건 지역상수, 초기화해도 에러안남, 그냥 있는데 잘 안씀
		//메소드(즉, 지역)안에서 상수를 선언할 때는 사용전에만 초기화하면 됨, 주로 클래스안에서 상수선언함

		//System.out.println(NICKNAME); //[x]초기화 안됐다고 에러남
		
		NICKNAME="자바"; //상수초기화 완료
		
		System.out.println(NICKNAME); // NICKNAME도 상수이므로 중간에 바꾸려면 에러남
		
		//NICKNAME="한소인"; //[x] 위에서 말한 에러
		
		int computer=1,user=2;
		System.out.println("컴퓨터 LOSE : 가독성이 낮다"); //가독성 높이기 위해 위의 클래스 안에 상수 선언함
		
		computer=SCISSORS;
		user=ROCK;
		System.out.println("컴퓨터 LOSE : 가독성이 높다"); //위에서 선언하고 옴
		
		
	} //main
	
	///////////////////////클래스안
	
}//class
