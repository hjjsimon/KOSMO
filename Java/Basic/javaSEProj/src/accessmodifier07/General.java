package accessmodifier07;

public class General {//일반 클래스, 오브젝트만 상속받을 것
	//accessmodifier07->polymorphism14
	//protected		    오버라이딩(다형성)
	//										이질화(다형성)
	//           	    instanceOf연산자
	//										타입캐스팅(참조형사이의 형변환)
	
	//protected, public 뒤 2등, 패키지랑 비교해서 생각
	//패키지 같으면 클래스 내 멤버 공유 가능(package 지정자는 생략가능)
	//A와 B가 서로 부모, 자식으로 상속관계면 패키지가 달라도 멤버 앞에 protected 붙이면 볼 수 있음(같은 패키지면 당연히 보임, 패키지가 달라도 상속관계면 보임, 패키지보다 넓은 범위)
	
	
	 void access() {//Super랑 상속관계 없음, Object상속
		 //private 멤버만 접근불가, 같은 패키지에 있으므로 생략형, protected, public 모두 접근 가능
		 //또한 같은 패키지 안에 있으므로 import할 필요 없다
		 
		 Super sup=new Super(); //상속 안받았으니 Super 인스턴스화해서 접근해야함/ Normal은 상속받았으니 인스턴스화 없이 this.로 받는 것****
		 sup.packageMethod();
		 sup.protectedMethod();
		 sup.publicMethod();
		 
		 
		 
	 }

}
