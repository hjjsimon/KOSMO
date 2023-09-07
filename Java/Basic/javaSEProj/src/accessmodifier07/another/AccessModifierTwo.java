package accessmodifier07.another;


import accessmodifier07.AccessModifierOne; //임포트할 필요 없음, 안쓰이니까

//AccessModifierTwo 클래스는 AccessModifierOne과 다른 패키지 안에 있다.
//AccessModifierOne의 멤버(변수,메소드)에 접근해보자

public class AccessModifierTwo {

	//클래스와 클래스 사이에 접근할 것, AccessModifierOne의 퍼블릭, 패키지를 가져올 것
	
	//[멤버 변수]
	//두 클래스 모두 같은 패키지안에 있기 때문에 import할 필요 없다
	AccessModifierOne one; // 문제없음, 클래스(자료형)과 변수     //다른 디렉토리임, 패키지 다르니까 임포트해야함
	
	//[멤버 메소드]
	public void access() {
		
		/*
		 AccessModifierOne 멤버중 private이 붙은 멤버는 접근불가]-클래스가 서로 다르므로
		 private이 붙은 멤버는 주로 퍼블릭이 붙은 멤버 메소드를 통해서 간접으로 호출할 수 있다, 직접호출은 불가, 접
		 */
		
		one=new AccessModifierOne();
		
		//멤버변수에 접근하기
		//one. // one. 찍으면 private 계열 멤버 빼고 퍼블릭, 패키지는 뜸
		//one.privateVar=10;//[x] not visible
		//one.packageVar=10;                                //package니까 다른 패키지(디렉토리,폴더) -> not visible
		one.publicVar=10; //패키지 다르면 public은 임포트 필요
		
		//멤버메소드에 접근하기
		//one.privateMethod();//[x] not visible
		//one.packageMethod(); 								//package니까 다른 패키지(디렉토리,폴더) -> not visible 위와 동일
		one.publicMethod();
	
		//PackageClass클래스는 접근지정자 생략형이지만 AccessModifierTwo와 같은 패키지 안에 있으므로 접근가능, 소스 확인~!~~~~!!~
		
		//PackageClass pack = new PackageClass();  //패키지 클래스 접근지정자 생략형, 다른 패키지에서 보이지 않으므로 import불가
				
	
	}
	
	
	
	
	
}
