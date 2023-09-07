package exception21;

//예외클래스 만드는 방법1] Exception클래스를 상속받아 예외클래스로 만든다. 이후 생성자 정의만 내입맛 맞게 오버로딩하면 됨
public class NotGoodAppearanceException extends Exception {//지금 만든건 그냥 자바클래스, 예외클래스의 구조가 되도록 상속받게하면됨, Exception 눌러보면 finally 안붙음, 그러면 상속가능
	//2]생성자 정의
	//기본생성자
	public NotGoodAppearanceException() {
		//Exception의 인자생성자인 Exception(String message) 호출]
		//인자인 message는 getMessage()로 호출할때 반환되는 예외메시지임
		super("복장불량은 입장불가");
	}
	//인자생성자
	public NotGoodAppearanceException(String message) {
		super(message);//스트링 받은걸 그대로 부모한테 전달, 예외메시지로 만들어줌
	}
}
