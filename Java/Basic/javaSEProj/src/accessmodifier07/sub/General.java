package accessmodifier07.sub;

import accessmodifier07.Super; //이것도 패키지가 달라 import 해줘야함*************

public class General {//일반 클래스, 오브젝트만 상속받을 것

	 void access() {//Super랑 상속관계 없음, Object상속
		 //private 멤버만 접근불가, 같은 패키지에 있으므로 생략형, protected, public 모두 접근 가능
		 //또한 같은 패키지 안에 있으므로 import할 필요 없다
		 
		 Super sup=new Super(); //상속 안받았으니 Super 인스턴스화해서 접근해야함/ Normal은 상속받았으니 인스턴스화 없이 this.로 받는 것****
		 //sup.packageMethod(); //패키지 다르니까 당연히 안보임
		 //sup.protectedMethod(); //제너럴하고 슈퍼하고 상속이 없어서 안보임
		 sup.publicMethod();
	 }

}
