package abstract17;

public class FigureApp {

	public static void main(String[] args) {
		//Figure f=new Figure; //[x]추상클래스라 인스턴스화 불가
		Figure f=new Rectangle(100, 100);
		f.area("사각형"); //무조건 f.area로 찾으면 됨 
		//Figure(추상 클래스)의 인자생성자-width,height -> 부모 생성자 먼자
		//Rectangle의 인자생성자 -> 호출한 자식 생성자가 다음 출력
		//사각형의 면적:10000
		f=new Triangle(100,100);
		f.area("삼각형");
		f=new Circle(100);
		f.area("원");
	}////main

}////class
