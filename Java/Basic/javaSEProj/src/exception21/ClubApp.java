package exception21;

public class ClubApp {//나만의 예외클래스로 해결하는방법~

	public static void main(String[] args) {
		Club club=new Club();
		try {
			club.entrance("남루", 20);
		} catch (NotGoodAppearanceException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace(); -> 자동으로 생긴거 뭐하는 놈이지
		} //메소드를 던지게 해놓음, 메인이니까 try,catch로 해결!
		try {
			club.entrance("정장", 15);
		} catch (NotGoodAppearanceException e) {
			System.out.println(e.getMessage());
		} 
		try {
			club.entrance("정장", 45);
		} catch (NotGoodAppearanceException e) {
			System.out.println(e.getMessage());
		} 
		try {
			club.entrance("정장", 20);
		} catch (NotGoodAppearanceException e) {
			System.out.println(e.getMessage());
		} 
	}////main

}////class
