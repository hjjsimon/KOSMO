package encapsulation13;

public class ColdPatient { //감기환자 SneezeCap <-캡슐 들 먹을 것
	
/*	//==========캡슐화 전(묶기전)============
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
*/		
	//감기환자는 순서대로 약먹을 필요 없음, 콘택600만 먹으면 됨	
	
	//==========캡슐화 전(묶은후)============
	
	//멤버 변수]
	Contack600 contack600=new Contack600();
	//멤버 메소드]
	void take() {
		contack600.take(); 
	}
	
	

}/////////////////class
