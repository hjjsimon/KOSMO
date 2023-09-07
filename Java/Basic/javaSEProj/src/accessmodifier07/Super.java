package accessmodifier07;

public class Super {
	
	//[멤버 메소드] -> 변수 귀찮아서 이것만 함, 접근지정자 다 써봄
	private void privateMethod() {}
	void packageMethod() {} //패키지 생략
	protected void protectedMethod() {}
	public void publicMethod() {}

	//자기 클래스 안에서 당연히 모든 멤버 접근가능
	void call() {
		privateMethod();
		packageMethod();
		protectedMethod();
		publicMethod();
	}
}
