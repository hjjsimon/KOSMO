package abstract17;

public abstract class Figure {//Figure->도형이란 뜻
	//[멤버변수]
	int width,height,radius; 
	//[기본생성자]-> new 못하는데 생성자 있어봤자 의미? 자식의 생성자를 통해 만들 수 있음, 의미 있음! -> HavingAbstractMethod ham=new HavingChild(); 
	public Figure() {
		System.out.println("Figure(추상 클래스)의 기본생성자");
	}////Figure
	//[인자생성자]->소스,유징필드
	public Figure(int radius) {
		this.radius = radius;
		System.out.println("Figure(추상 클래스)의 인자생성자-radius");
	}
	public Figure(int width, int height) {  
		this.width = width;
		this.height = height;
		System.out.println("Figure(추상 클래스)의 인자생성자-width,height");
	}
	//[추상메소드]
	//도형의 면적을 구하는 추상메소드
	abstract void area(String figureName);
	
	
	
	
	
	
	
	
	
	
}
