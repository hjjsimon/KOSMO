package abstract17;

public class Triangle extends Figure{
	@Override
	void area(String figureName) {
		System.out.println(figureName+"의 면적:"+(int)(width*height*0.5)); 
	}
	public Triangle(int width, int height) {
		super(width, height);
		System.out.println("Triangle의 인자생성자");
		
	}

	
}
