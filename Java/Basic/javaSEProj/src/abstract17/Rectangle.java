package abstract17;

public class Rectangle extends Figure{//추상메소드 오버라이딩하라고 빨간줄
	@Override
	void area(String figureName) {//width,height,radius는 그대로 상속받음, radius만 상관없음
		System.out.println(figureName+"의 면적:"+width*height); //초기화안됨, 초기화하려면 부모의 인자생성자 가져와야함
	}
	public Rectangle(int width, int height) {//항상 부모의 생성자 먼저, 다음 자식의 생성자 실행
		super(width, height); 
		System.out.println("Rectangle의 인자생성자");
	}

}
