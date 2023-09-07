package interface18;

public class Triangle extends FigureData implements Figure,Drawble{//클래스1개, 인터페이스2개 상속

	public Triangle(int width, int height) { //가로폭, 세로폭 초기화
		super(width, height);
	}
	@Override
	public void draw(String figureName) {
		System.out.println(figureName+"을 3개의 선으로 그리다");
	}
	@Override
	public void area(String figureName) {
		System.out.println(figureName+"의 면적:"+(int)(width*height*0.5));
	}

}
