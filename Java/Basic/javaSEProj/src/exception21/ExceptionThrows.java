package exception21;

import java.io.IOException;

/*
예외객체 생성후  throw키워드를 이용해서 직접 던지기 ->throw랑 throws랑 다름, 내가 throws한건 반드시 try/catch해야함
- 반환타입 메소드명 throws 예외클래스와 쌍이다.
- throws를 이용해서 던진 예외는 언젠가는 반드시 try~catch를 해야 한다. 즉 만약 계속 던졌다면 최종 main에서는 다시 던질 수는 있지만 실행시 에러 고로 try~catch해야 한다.	
형식]
접근지정자 [modifier] 반환타입 메소드명 throws 예외클래스{
	특정조건일때
	throw new Exception(); -> 이것도 Exception객체 생성, 지금 Exception 던지고 있으니 위에 예외클래스 자리에는 적어도 Exception(생기는 객체보다 큰 범위면 다됨)
	
	//throw 이후의 명령문은 실행이 안된다. -> return키워드와 동일, 빠져나감
}
*/
public class ExceptionThrows {
	//기존 자바에서 제공해주는 예외를 던지는 메소드(이건 내가 임의로 던진게 아니라 계속 던져도 됨, 얘가 던진걸 내가 처리하는 것) ex. read() 호출하는 경우]
	static void throwsMethodByJava() throws IOException {
		System.out.println("문자입력?");
		System.in.read(); //이건 내가 만든 메소드가 아니고 자바에서 제공하는 메소드, 예외를 던지는걸로 정의가 돼있음, 이걸 우리가 받아서 또 던질 수 있음 throws IOException, 메인에서 throwsMethodByJava()메소드 호출 시에는 또 throws IOException 해야함
	}
	static void callByJava() throws IOException {
		throwsMethodByJava(); //위 메소드 호출하면 또 던져야함
	}
	//이제 직접 내가 던짐, 보통 특정 조건일 때 던짐
	static void throwsMethodByUser(int value) throws Exception {
		if(value%2==0) {//짝수인 조건
			//1.예외객체 생성
			Exception e=new Exception("짝수는 안돼요");//e.getMassage랑 동일? 어쩌구함
			throw e; //throws Exception 던지는 것만 해결 가능(최대 Exception 부모)
			//System.out.println("throw 키워드 이후");//바로 빠져나가니까 여기 도달 불가 unreachable code 에러
		}
		System.out.println(value+"는 홀수");//throw로 빠져나가 여기 실행
	}
	static void callByUser(int value) throws Exception {
		throwsMethodByUser(value);
	}
	public static void main(String[] args) throws Exception {
		//callByJava(); //또 던져야함 -> 예외를 던져도 되고 try~catch를 해도 된다
		
		//allByUser(10); //: 짝수는 안돼요 -> 예외객체 만들 때 쓴 에러로 실행에러 나옴, 트라이캐치만 가능한 해결방법임
		
		try {
			callByUser(10);
		}
		catch(Exception e) {
			System.out.println(e.getMessage()); //짝수는 안돼요 출력
		} 
		
		
	}

}
