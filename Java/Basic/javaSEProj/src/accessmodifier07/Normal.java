package accessmodifier07;

public class Normal extends Super{//슈퍼에서 상속
	//두 클래스는 상속관계로 같은 패키지 안에 있으므로
	//package, protected, public은 접근 가능하므로 모두 상속
	//private는 접근 불가
	
	void access() {//내꺼라 this 써줌?
		//this.privateMethod(); //is not visible
		this.packageMethod();
		this.protectedMethod();
		this.publicMethod();
		
	}
	
	
	
	
	
}
