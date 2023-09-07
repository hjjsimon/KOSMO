package modifier09;

public class StaticBlockApp {

	public static void main(String[] args) {
		System.out.println("[StaticBlockApp의 main 메소드]"); // 다른 클래스에 있는 메인이라 먼저 진행
		
		//이전 StaticBlock 인스턴스화
		// 메모리 만들고 최초로 가져오는애
		StaticBlock sb= new StaticBlock(); //얘를 인스턴스화할때 이미 static블락은 만들어져있고, 실행은 안되어있던 것, 생성자보다 먼저 static 블락이 실행된것
		//먼저 실행한 실행블락
	}
		/*	static { //메인블락에 아무것도 없는데 static이라 메인블락보다 먼저 실행됨
			System.out.println("[static블락 시작]");
			
			//인스턴스멤버x] static 블락 안에서 정적멤버만 가능, 인스턴스멤버 아래 둘 다 안됨
			//System.out.println(instanceVar);
			//instanceMethod(); 
			
			//정적멤버o]
			System.out.println(staticVar);
			staticMethod();
			
			int localVar=10;//static 블락안에서 선언한 지역변수, 멤버아니고!!
			System.out.println("static블락안에서 선언한 지역변수:"+localVar);
			
			System.out.println("[static블락 끝]"); 
		}*/
		
	

}
