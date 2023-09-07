package interface18;

public class Circle extends FigureData implements Figure,Drawble{//클래스1개, 인터페이스2개 상속

	public Circle(int radius) { //가로폭, 세로폭 초기화
		super(radius);
	}
	@Override
	public void draw(String figureName) {
		System.out.println(figureName+"을 1개의 선으로 연결해서 그리다");
	}
	@Override
	public void area(String figureName) {
		System.out.println(figureName+"의 면적:"+(int)(radius*radius*Math.PI));
	}

}
