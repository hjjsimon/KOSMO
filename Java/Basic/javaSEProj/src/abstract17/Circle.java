package abstract17;

public class Circle extends Figure{
	@Override
	void area(String figureName) {
		System.out.println(figureName+"의 면적:"+(int)(radius*radius*Math.PI)); 
	}
	public Circle(int radius) {
		super(radius);
		System.out.println("Circle의 인자생성자");
	}
	
}
