package polymorphism14;

import java.util.Date;

public class Child extends Parent{ //상속

	//[멤버변수]
	String newExtendVar; //부모에 없던 새롭게 확장한 멤버변수, 부모를 확장해서 자식을 만듦~
	
	//[인자생성자] 소스->슈퍼클래스
	public Child(String name, int age, String newExtendVar) {
		super(name, age);
		this.newExtendVar=newExtendVar;
	}
	
	//[멤버메소드]
	void newExtendMethod() {//자식에서 새롭게 확장한 멤버메소드
		System.out.println("자식에서 새롭게 확장한 메소드");
	}
	private void eat() {//오버라이딩[x] 부모 private이라 자식에서 안보임, 보여야 리모델링하지, 자식이 상속받는지도 모름, 자식에서 새롭게 확장한 메소드!
		System.out.println("자식이 먹는다");//그냥 별개로 만든 메소드인셈, 부모 메소드에서 복붙함, 그리고 안에만 부모님이 드신다-> 자식이 먹는다로 바꿈
	}
	@Override //주석의 일종 @over 컨트롤하면 됨, 컴파일러한테 아래 메소드는 오버라이딩한 메소드라고 알려주는 메소드!! 어노테이션! 오버라이딩이라고 해놓고 밑에 sleep1으로 메소드명 바꾸면 빨간줄감
	public String sleep(int age) {//부모 복붙, -> 이건 오버라이딩에 해당! sleep 같은 패키지라 보임, int age 말고 int year해도 상관 없이 오버라이딩, 변수명은 무관
		System.out.println("자식이 잔다");//부모 패키지였는데 퍼블릭쓴건 ㄱㅊ 부모보다 같거나 넓으면 됨
		return null;
	}
	int walk(int date){//부모 복붙, 부모는 protected인데 자식 package로 만들면 에러, 이번엔 오버라이딩 없이 그냥 새로운 메소드로 만들 예정, Date->int로 바꿈, 이건 오버로딩!
		System.out.println("나이가 "+age+"살인 자식이 걷다");//상속받은 walk 하나 자기가 만든 walk 하나 -> 오버로딩!! Child. 하면 2개 메소드 뜰 것!
		return 0;
	}
	//소스->오버라이드 메소드하면 자동으로 오버라이드 만들어줌, 그리고 super.메소드 갖다버리고 새로 구현해서 오버라이딩
	@Override
	public void exercise() {
		//super.exercise(); 이대로 두면 재구현 아님, 부모 메소드 그대로 씀
		System.out.println("자식이 운동한다");
	}
	@Override
	String getParent() {
		return super.getParent()+",자식 멤버변수:"+newExtendVar;//부모꺼 그대로에 자식에서 새로 추가한 멤버변수 더함
	}
	@Override
	void printParent() {
		System.out.println(getParent());
	}
	//@Override -> 이거하면 오버라이딩 안된거니까 에러남
	static void staticMethod(){//오버라이딩 테스트용 복붙, 정적메소드는 오버라이딩 대상[x]
		System.out.println("자식의 정적 메소드");
	}
}
