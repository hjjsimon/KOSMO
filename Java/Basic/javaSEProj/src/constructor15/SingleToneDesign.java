package constructor15;
//클래스를 만드는걸 디자인패턴이라고함, 싱글톤디자인 많이 씀 ex. Calendar 클래스가 이걸로 만들어짐(new안쓴이유)
/*
싱글톤 디자인: 클래스를 설계하는 디자인 패턴의 하나(방법의 하나)로 하나의 인스턴스 즉 하나의 메모리를 생성해
            이를 공유하고자 할때 사용하는 패턴 즉 하나의 메모리를 서로 공유해서 사용하므로
            값 변경시 문제가 발생할 수 있는 경우는 읽기 전용으로 하자. -> getter만 만들라는 뜻~~ setter는 쓰기~
 			예]java.util패키지의 Calendar클래스
 방법]
 1.생성자의 접근 지정자를 private으로 한다. -> 다른 곳에서 new 못함, 인스턴스화 못함, is not visible, 캘린더로 해봤음
 2.정적 메소드로 해당 클래스의 객체를 반환하도록 정의한다. -> new를 반환하는 것처럼, 우리가 지금껏 new로 계속 찍었던건 싱글톤 아님 이게 일반적이긴함
*/

//ex. A클래스 만들어서 A a1=new A(); 함, a2, a3 계속, new할때마다 새롭게 메모리 만들어짐, A타입 객체 3개 만들어짐, 주소 다름, 각각 a1,2,3에 넣음
//싱글톤디자인은 new를 못하게하고, 정적메소드로 객체를 생성하게함, 그리고 1개만, 그래서 싱글톤 디자인이라고함, 위에 객체 3개 만들어지는거랑 1개 만들어지는거랑 차이
//ex. 달력같은경우 부산1월이랑 서울1월이랑 당연히 같음, 달력 1개만 만들어서 돌려쓰겠다는 뜻, 그래서 캘린더 클래스를 싱글톤으로 만듦

//1.싱글톤으로 미설계시]-> 지금껏 함
class NoSingleTone{
	//[멤버변수]
	int noShareVar;//공유x 객체마다 이 메모리가 만들어짐
	//[멤버메소드]
	void print() {
		System.out.println("noShareVar:"+noShareVar);
	}
}////NoSingleTone

//2.싱글톤으로 설계시]
class SingleTone{
	//[멤버변수]
	int shareVar;
	private static SingleTone single=new SingleTone(); //인스턴스, 메모리 늦게 생김, new해야 생김, 그래서 static 붙여줌(아래 return 갔다가 옴)
//	public SingleTone() { single=new SingleTone(); }//클래스안에서 이거랑 바로 윗줄 같은 코드임 근데 이건 귀찮
	
	//[생성자]
	//1.접근지정자를 private으로 지정
	private SingleTone() {}
	//[멤버메소드]
	//2.정적메소드로 해당클래스의 객체를 반환하도록 정의한다.
	public static SingleTone getInstance() { //캘린더 getInstance 반환타입도 캘린더, 이것도 싱글톤을 싱글톤으로 반환, 위 멤버에 싱글톤타입 single 만들어놓음, 클래스로더할때 new SingleTone 메모리생성
		return single;//딱한번만 생김 주소, 그걸 반환
	}
	void print() {
		System.out.println("shareVar:"+shareVar);
	}
	
}////SingleTone
 
public class SingleToneDesign {

	public static void main(String[] args) {

		//1.싱글톤으로 미설계시]-> new할때마다 메모리가 생긴다
		NoSingleTone no1=new NoSingleTone(); //힙에 메모리 생김, noSharVar작은방에 100저장, 메소드는 print 존재
		no1.noShareVar=100;
		no1.print();
		
		NoSingleTone no2=new NoSingleTone(); //똑같은 형식 메모리 또 생김, 작은방은 200
		no2.noShareVar=200;
		no2.print();
		
		System.out.println(String.format("no1:%s,no2:%s,no1의 noSharVar:%s,no2의 noSharVar:%s", no1, no2, no1.noShareVar, no2.noShareVar)); //println이라 마지막에는 줄바꿈%n필요 없음
		//주소2개, 값2개 나옴
		
		//2.싱글톤으로 설계시]
		//SingleTone st1=new SingleTone(); //생성자 접근지정자를 프라이빗해서 안보임, 인스턴스화 불가
		SingleTone st1=SingleTone.getInstance(); //싱글톤타입 single 반환, single에는 주소 저장
		st1.shareVar=100;
		st1.print();
		
		SingleTone st2=SingleTone.getInstance(); 
		st2.shareVar=200;
		st2.print();
		
		System.out.println(String.format("st1:%s,st2:%s,st1의 noSharVar:%s,st2의 noSharVar:%s", st1, st2, st1.shareVar, st2.shareVar));
		//위에 싱글톤에는 no1,2 주소 다름, 지금은 주소 st1,2 같음
		//메모리 객체가 1개 생겨서 주소를 공유하는 것
		//값도 200 200으로 같음

		//그림설명
		//main실행시 .class파일이 jvm에 들어감, 클래스로더부터 SingleToneDesign ~~ 쭉 메모리 로드
		//클래스로더할때, 아직 메인실행x, 스태틱 영역에 정적멤버 single 하나 생김, 주소 저장, getInstance메소드도 스태틱에 생김
		//힙영역에 SingleTone타입 메모리 생김, SingleTone@0x1234, 설계도 보면, 안에는 shareVar 작은방(new했으니까), 0초기화, 
		//생성자 얘도 있다고 치고, 메소드(작은방)은 print() 존재
		//메인찾고 SingleTone st1=SingleTone.getInstance();호출, return single하면 주소, 이걸 st1에 담음, st1은 스택에 있는 메모리, 
		//st1이라는 이름으로 같은 메모리를 가리킴, st1.shareVar=100;, st1이라는 이름으로 찾은 shareVar에 100 넣음, 프린트하면 100나옴
		//SingleTone st2=SingleTone.getInstance(); 이것도 리턴싱글인데 st2에 st1이랑 같은 single 주소를 넣음, 얘가 st2라는 이름으로 찾아가 200넣음
		//결국 shareVar는 100->200이 됨,
		//최종 	System.out.println(String.format("st1:%s,st2:%s,st1의 noSharVar:%s,st2의 noSharVar:%s", st1, st2, st1.shareVar, st2.shareVar)); 
		//출력시 주소 st1,2 동일, 값은 200으로 바뀜. 같은 작은방에 200이 있음, 메모리 1개 생성해서 객체를 공유하는 것, 이게 싱글톤 디자인 패턴
		
		// int shareVar; 를 읽기전용으로 하고싶으면?
		// private int shareVar; 하면 접근불가, 그리고 밑에
		// public int ~getter 하면됨, 근데 st1.2 처럼 세팅은 못함, 이거 하려면 세터도 해야함
		
		
	}////////main

}////////////class
