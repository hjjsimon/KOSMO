package wrapperclass10;

public class WrapperClass01 {

	/*
	 Wrapper 클래스: 기본자료형을 클래스로 만든 것, 
	 jdk1.1부터 있었음 래핑, 포장한다는 뜻, 기본자료형을 클래스로 래핑함, 참조형으로 포장한것 -> 클래스로 추상화, byte ->Byte 나머지는 클래스니까 
	 short->Short, long->Long, float->Float, 등등 앞 소문자만 대문자로,
	 예외2개 int ->Integer, char ->Character ----> 근데 얘네 제일 많이 씀, 얘네의 주요 메소드 정리하는게 다음 WrapperClass02
	 jdk5.0부터 auto-boxing(기본자료형이 클래스가 됨 ex.int ->Integer), auto-unboxing이 있음
	 원래 형변환, 대입연산 기본자료형과 참조형 사이에는 불가, 5.0부터 오토박싱,언박싱으로 기본자료형과 래퍼클래스 사이에 변환이 가능
	 */
	
	//[AutoBoxing <->AutoUnBoxing]
	/*
	- 오토박싱 ,오토 언박싱은 JDK5.0 이상에서만 적용 됨
	- Wrapper클래스와 기본 자료형과의 관계에서 나온 개념
	- 오토박싱 : 기본 자료형이 자동으로(Auto) 참조형(Wrapper클래스)으로 바뀌는거, 예] int -> Integer
	- 오토언박싱 :참조형(Wrapper클래스)이 기본 자료형으로 자동으로 바뀌는거, 예] Integer -> int
	 클래스에는 멤버변수, 멤버메소드가 있음,			 
	*/
	
	public static void main(String[] args) {

		//jdk1.4 이전 버전에서의 코딩형태
		//1.기본자료형으로 + 연산] -> 다 기본자료형이니까, 멤버 없음, 주소저장 안되어있으므로, num1.으로 멤버 가리킬 수 없음
		int num1=200;
		int num2=20;
		int result=num1+num2;
		System.out.println("result="+result);
		
		//2.Wrapper클래스로 + 연산]
		// 자바.랭에 inte 컨트롤하면 Integer
		Integer num1Obj=new Integer(200); 
		//Obj -> 오브젝트, 클래스로 주소 저장, new로 생성자=constructor, Integer() -> 인자 200 전달
		//버전 올라가면 jdk가 삭제한대, 30에서 Integer(200); 빗금, 사라짐 deprecated는 쓰면 안됨, 지금은 공부니까 갠찬
		Integer num2Obj=new Integer(20);
		// 스택에 num1Obj(주소들어감) 있고, 힙에 Integer타입의 메모리(멤버변수, 멤버메소드 존재)가 생성,
		// 생성자(200)하면 -> 어딘가 데이터를 저장하는 메모리 어딘가에 200이 저장
		System.out.println(num1Obj+num2Obj); //[x] jdk1.4이전 버전은 컴파일에러, 지금 1.5넘어서 ㄱㅊ, 오토 언박싱 적용됨
		//원래 자바에서 주소연산이 안됨. 빨간줄 가야하는데.. 여튼 해보면 220나옴
		result=num1Obj.intValue()+num2Obj.intValue();  //int 컨트롤 찾으면, intValue -> int+int=int -> result 인트형에 담을 수 있음, int꺼내오려고 intValue
		System.out.println("result="+result); //1.5이전 버전방식, 이젠 안함
		//num1. 기본자료형은 . 찍어도 접근할 수 있는 메모리 없음, 멤버 없음
	
		Integer num1Object=Integer.valueOf(num1); //int타입을 넣으래, 근데 Integer로 반환, num1을 Integer에 담음
		System.out.println("num1(int타입)을 byte형값으로 변환:"+num1Obj.byteValue()); 
		//int 200을 byte로 바꾸면 -56됨, 
		
		/*
		 200이면 양수니까 부호비트+
		 0 0 0 0 0 0 0 0
		 0 0 0 0 0 0 0 0
		 0 0 0 0 0 0 0 0 
		 1 1 0 0 1 0 0 0  -> 128, 64, 8
		 
		 찌부시키면
		 1 1 0 0 1 0 0 0 만 남음 -1x64+8 = -56
		 */
		
		System.out.println("Integer클래스의 메소드 미사용:"+(byte)num1); //-56
		System.out.println("num1(int)을 이진수로 변경:"+Integer.toBinaryString(num1)); //계산기씀, 2진수로 200 = 1100 1000
		//컨트롤 했을 때 그림에 s 있으면 스태틱, 헷갈릴 수 있으니 클래스명으로 접근
		System.out.println("num1Object:"+num1Object); 
		System.out.println("num1Object.toString()"+num1Object.toString());
		//num1Obj, num1Object 모두 주소 저장되어있음, 그러니까 . 으로 접근, heap영역엔 Integer타입 메모리 저장, toString, binary 등등 멤버 존재, 근데 저장된 데이터 그대로 출력됨
		//wrapper클래스는 toString 메소드가 리모델링 돼서, 그냥 저장된 데이터 200으로 출력해준대
		
		
		
		
		//jdk5.0부터 위에 int ->Integer 위해 인트밸류 이런거 안함
		//1)오토박싱: int->Integer:오토박싱이 일어남
		num1Obj=num1;//new Integer(num1) <-원래 이거 해야했는데, 이제 오토박싱 때문에 됨, 기본자료형과 참조형이 대입연산 가능해짐
		
		//2)오토언박싱: 위에 밸류오브해야 언박싱됨
		int num3=num1Obj; //오른쪽부터연산 Integer가 int 왼쪽이 된 것
		Integer num3Obj=300; //오토박싱, int형 상수300이 Integer가 됨, new integer원래 했어야함
		/*
		 Wrapper클래스를 사용하는 이점: Wrapper클래스 안의 많은 메소드 사용가능
		 고로 쉽게 정수를 이진수로 혹은 8진수로 쉽게 변환가능 등등
		 */
		System.out.println("int형의 최대값:"+Integer.MAX_VALUE); //int최대값 2^31-1, s는 static, f는 상수니까 final
		System.out.println("int형의 최소값:"+Integer.MIN_VALUE); //int최소값
		System.out.println("num1을 8진수로 변경:"+Integer.toOctalString(num1)); //wrapper로 인티저클래스만들어놓음, int넣고 string반환해준대, 200을 8진수 문자열로 변경
		System.out.println(0310); //8진수는 0붙임
		System.out.println("num1을 16진수로 변경:"+Integer.toHexString(num1)); //16진수 문자열로 변경
		System.out.println(0xC8); //16진수는 0x붙임
		
		/*
		 자바의 모든 클래스들은 Object라는 최상위 클래스로 부터 상속을 받는다.                                왕중요
		 Wrapper클래스들은 Object부터 상속받은 toString()메소드를 오버라이딩하여 인스턴스 변수 출력시
		 주소가 아니라 실제 저장된 값이 반환되도록 오버라이딩 되었다.	 
		 */
		
		//num1Obj. //컨트롤해보면 회색글씨 Object 있는건 Object로부터 상속받았다는 것, toString은 Integer로 돼있음, 리모델링된것 (Object로 돼있으면 리모델링된거x)
		//toString() -> 검은글씨 Integer라고 돼있음
		//toString -> 주소가 아니라 저장된 대표값을 반환하는 메소드, 리모델링(오버라이딩) 되어있음
		Integer remodel=new Integer(1004); //remodel에는 주소 저장
		System.out.println("remodel:"+remodel); //근데 주소출력이 안된다~~, 1004값이 나옴, toString메소드가 리모델링 돼서!
		System.out.println("remodel.toString:"+remodel.toString());
		System.out.println("remodel의 클래스명:"+remodel.getClass().getName());//remodel이라는 주소가 가리키는 클래스명을 출력해보자
		System.out.println(remodel.hashCode());
		//Integer클래스로 나옴, 앞에 패키지명도 바자.랭 나옴
		//toString은 Object에서 상속받은것
		//원래 Object클래스의 toString()메소드는 객체의 메모리 주소를 아래와 같은 문자열로 반환하도록 정의됨.      왕중요
		//패키지명1.패키지명2....클래스명@16진수체계의 메모리 주소
		//래퍼클래스는 실제 저장된 값이 반환되도록 오버라이딩
		WrapperClass01 wc=new WrapperClass01();// 이건 오버라이딩 안해서 주소 나옴
		System.out.println("wc:"+wc);
		System.out.println("wc.toString():"+wc.toString());	//wc.toString 보면 object꺼로 되어있음, 오버라이딩(리모델링) 안돼있는 것
		System.out.println("wc.hashCode()(10진수):"+wc.hashCode());
		//메모리코드 16진수를 10진수형태로 만든게 해시코드
		//래퍼클래스 toString이 오버라이딩 돼 있으면 해시코드도 리모델링 돼 있음
		System.out.println(Integer.toHexString(wc.hashCode()));//메소드로 16진수 바꿔보자
		
		
		
	}

	
	
	
	
	
}
