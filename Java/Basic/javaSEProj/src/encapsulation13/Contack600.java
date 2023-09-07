package encapsulation13;

public class Contack600 {

	//멤버변수]
	SneezeCap sneezeCap=new SneezeCap();
	SniffleCap sniffleCap=new SniffleCap();
	SnivelCap snivelCap=new SnivelCap();
		
	//멤버메소드]
	void take() {
		//복용순서가 중요하다고 가정] 
		//재채기 멈추고-> 콧물 멈추고-> 기침 멈추게해야 감기 낫는다고 하자
		sneezeCap.take();
		snivelCap.take();
		sniffleCap.take();
	
	
	
}
}