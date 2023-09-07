package interface18;

public class FigureDrawbleApp {

	public static void main(String[] args) {
		Figure f=new Rectangle(100, 100);//Figure에는 area는 있는데 draw가 없음, 오버라이딩한 area는 잘뜸
		f.area("사각형");
		((Rectangle)f).draw("사각형"); //메모리 그림 찾아가는거 생각하기!, 클래스를 찾아가거나 드로어블 인터페이스 찾아가면 됨, 아까 한 것과 동일
		((Drawble)f).draw("사각형");
		
		Drawble d=new Triangle(100, 100);
		d.draw("삼각형");
		((Triangle)d).area("삼각형"); //드로우는 있는데 에어리어는 없음, 에어리어 찾는 2개 방법
		((Figure)d).area("삼각형");
		
		d=new Circle(100);
		d.draw("원");
		((Circle)d).area("원");
		((Figure)d).area("원");
		
		
	}

}
