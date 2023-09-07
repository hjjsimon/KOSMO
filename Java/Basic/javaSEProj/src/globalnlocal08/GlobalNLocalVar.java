package globalnlocal08;

/*
1) 멤버변수(클래스안에서 선언된 변수): -> 클래스의 구성원
         멤버변수는 해당 클래스안의 모든 메서드에서 사용 가능(static붙이면) 하고 전역변수(Global variable)라고 한다.
         멤버변수는 초기화를 하지 않고 사용해도 해당 자료형의 기본값으로 초기화 된다.
         예] int:0,double:0.0 참조형:null, boolean:false로 등
		
2) 지역변수(Local Variable):
		특정 지역(Scope) 안에서 선언되어 그 지역에서만 사용되는 변수, 그 지역을 벗어나면 그 변수는 메모리에서 사라진다.
		
		메소드 안에서 선언된 변수 혹은 Block(if문,while문,for문,try문등) {} 안에서 선언된 변수를 지역변수라 한다. 
		또한 매개변수도 지역변수이다
		ex. 아래 main 메소드 안에서 선언된 int num은 지역변수, 지역변수는 선언된 곳을 벗어나면 메모리가 해제된다.
		지역변수는 사용시 반드시 초기화를 하고 사용해야 한다.
		즉 지역변수는 자동으로 초기화가 안된다. -> 0으로 멤버변수처럼 자동초기화x
		
		※지역변수앞에는 modifier중(final/abstract/static) final만 붙일 수 있다. -> 지역안에는 상수 잘 안씀
		  abstract는 클래스와 메소드앞에만 붙일 수 있다. static는 못붙임.(당연히 메소드나 {}가 제일 먼저 실행되는게 아니니까)
		  또한 지역변수 앞에는 접근지정자를 붙일수 없다.
		
*/

public class GlobalNLocalVar { //글로벌 앤 로컬 배리어블
	
	//스태틱 붙이면, 인스턴스화(new붙이는거) 안해도 GlobalNLocalVar. 치면 됨, 자동 인스턴스화? 
	//스태틱 붙이면, 메모리 생성시점이 다름 !!!!!!!!!!!!!
	//스태틱 붙이면 CLASS LOADER 할 때 메모리 생김, 원래는 interpreter하면서 메인 한줄씩 읽고 메모리 생김
	// static 없는 private int mNoInit; 는 GlobalNLocalVar gnv = new GlobalNLocalVar(); 으로 new할 때 메모리가 생김
	// 저 밑에 System.out.println(GlobalNLocalVar.mystatic); 하면 main은 같은 public static void main(String[] args) 
	// 메인도 static으로 class loader할 때 같이 생긴거라 출력이 가능함
	// GlobalNLocalVar로 인스턴스화할 필요 없음 
	// 클래스의 구성인 멤버에만 static 붙일 수 있음
	// static 붙으면 정적멤버라고함(클래스명으로 접근), 안붙으면 인스턴스형 멤버 -> 인스턴스화 해야 메모리에 생기니까(이건 . 찍어서 주소로접근)
	// 클래스에 붙일 수 있는 modifier는 abstract, final, static 3가지, 근데 abstract만 소스에서 만드는 클래스에 가능
	 
	
	
	//static int num=10; // static 붙이면 밑에서 가져갈 수 있음, 없으면 불가 -> 클래스안은 0으로 초기화 되어있음
	
	//[멤버 변수] -> 인스턴스가 필요함 static안에 없으니까 아래 메소드도 인스턴스형 멤버
	private int mNoInit; // m멤버,번호
	int mInit=100; //선언 동시 초기화
	int [] mArray = new int[3]; //선언과 동시에 메모리할당 및 주소로 초기화, 주소는 mArray, 3
	
	//[멤버 메소드]
	int getTotal(int start, int end) { //매개변수가 start end
		
		int sum;
		sum=0; //지역변수 초기화 필수 그래야 아래 2줄 가능
		System.out.println(sum); //지역변수(local) sum은 초기화x
		
		for(int i=start; i<=end; i++) {
			sum+=i; //start부터 end까지 누적합이 sum에 들어감
		}
		//System.out.println("for문이 끝난 후 i:"+i); //[x]i cannot be resolved to a variable-> 변수로 풀 수 없다 -> i도 for블락 안에서만 사용가능한 지역변수
		//지역변수 i는 for문 안에서만 사용가능
		//지역변수 sum, start, end는 getTotal메소드 안에서는 어디든 사용가능
		
		
		if(sum%2==0) {
			String strResult;
			//System.out.println(strResult); //초기화 안해서 빨간줄, initialized안됨
			strResult="총합은 짝수";
			System.out.println(strResult);
		}
		else {
			//strResult="총합은 홀수"; //여기서는 strResult 처음봄, 블락이 다르니까, if블락에서만 쓸 수 있음
			String strResult="총합은 홀수";
			System.out.println(strResult);//[o]
		}
			
		//무조건 실행되는 블락, 이렇게는 안씀
		{
			int block=100;
			System.out.println(block);
		}
			//System.out.println(block); // 블락내 지역변수 여기서 풀이불가

		System.out.println("멤버변수(mNoInit):"+mNoInit);//자동으로 0 초기화
		return sum; //초기화 안해서 리턴 불가
		
	}////////getTotal
	
	
	
	
	
	public static void main(String[] args) { // 매개변수 args도 지역변수
		
		//int num; -> 지역변수는 0으로 초기화가 안되어있음, 아무 값도 없어서 초기화 하고 써야함 
		//System.out.println(num);
		
		
	/*	int num; // 이걸 쓰면 하위블락의 num은 중복이됨
		{
			int num=10;
			System.out.println(num);
		}
		num+=10; // num cannot be resolved to a variable -> 위의 {} 블락 끝나니 메모리에서 사라진 것
		//int num=10; //새로 선언해야함(선언:자료형붙여서)
	
	*/
		//GlobalNLocalVar클래스 인스턴스화, 객체화, 메모리생성
		//멤버변수 출력
		GlobalNLocalVar gnv = new GlobalNLocalVar();
		System.out.println("멤버변수(mNoInit):"+gnv.mNoInit); //초기화 안되어있음
		System.out.println("멤버변수(mInit):"+gnv.mInit);
		System.out.println("멤버변수(mArray):"+gnv.mArray);
		
		//멤버메소드 출력
		int total=gnv.getTotal(1, 10);
		
		//public int start; //지역변수라 앞에 접근지정자 못붙임 public당연
		
		//배열은 지역에서 선언된 배열이든, 클래스의 멤버로 선언된 배열이든, 해당 자료형으로 초기화 되어있다. ->>>>> 지금까지 변수였음!!!!!!!!!!!
		//변수는 멤버변수만 초기화o, 지역변수 초기화x
		
		System.out.println("[클래스의 멤버인 배열]");
		for(int i=0; i<gnv.mArray.length; i++) 
			System.out.printf("%d번방:%d%n",i,gnv.mArray[i]);  // 걍 배열은 어디든 초기화, mArray int형이라 0초기화
			
		System.out.println("[지역에서 선언한 배열]");
		int[] lArray=new int[3]; //로컬배열
		for(int i=0; i<lArray.length; i++)                  // 여기서 직접 선언한거라 gnv. 빼고 그냥 lArray l은 local뜻
			System.out.printf("%d번방:%d%n",i,lArray[i]);	// 걍 배열 초기화 되어있음
		
		
		
		
		
		
		
		
	}/////////////////main

}/////////////////////class
