package accessmodifier07.sub;

import accessmodifier07.Super; //다른 패키지에 있기 때문에 Super는 import 해줘야 쓸 수 있음*** 

public class Normal extends Super{//슈퍼에서 상속
	//두 클래스는 상속관계로 같은 패키지 안에 있으므로
	//package, protected, public은 접근 가능하므로 모두 상속
	//private는 접근 불가
	
	void access() {//내꺼라 this 써줌?
		//this.privateMethod(); //is not visible
		//this.packageMethod();//package 달라 보이지도 않음!!! is not visible
		this.protectedMethod(); //다른 패키지라도 super랑은 상속관계라 쓸 수 있음
		this.publicMethod();
		
	}
	
	
	
	
	
}
