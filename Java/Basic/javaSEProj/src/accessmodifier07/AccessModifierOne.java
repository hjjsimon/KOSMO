package accessmodifier07;
//public, package, private, protected -> 4개가 접근지정자(엑세스 모디파이어) 
//클래스 앞에는 퍼블릭, 패키지 2개밖에 못붙임(만들때 체크보임), protected는 상속을 배워야함 나중에 함 

/*
접근지정자:클래스와 클래스 사이 혹은 클래스의 멤버간의 접근을 제어하는 기능을 가진 한정자       
         private < default(생략형,package) < protected < public               
private:자기 클래스안에서만 접근가능.												 
                다른 클래스에서는 접근 불가능
public: 모든 클래스에서 접근 가능
생략형(패키지접근지정자,디폴트접근지정자):
같은 패키지내에 있는 클래스들끼리는 접근가능	
[접근지정자를 붙일 수 있는곳]:
1]접근지정자 [modifier] class 클래스명
2]접근지정자 [modifier] final 자료형 멤버상수=초기값:접근지정자와 modifier는
  순서는 상관없다
  final 접근지정자 자료형 멤버상수=초기값
3]접근지정자 [modifier] 자료형 멤버변수;
4]접근지정자 [modifier] 반환타입 메소드명;
  단, class 앞에 붙일 수 있는 접근지정자는 public과
  생략형만 붙일 수 있다.
  결론: 인터페이스 와 클래스와 클래스의 모든 멤버에는 접근지정자를
           붙일 수 있다.
		
 A클래스, B클래스 간의 멤버(변수든 메소드든)에 접근하고싶음
 ex.A클래스에서 B클래스의 멤버를 쓰고싶음, 접근을 하고싶음
 제한을 하면 접근지정자, 제한을 할지말지 결정
 접근범위가있음: public > protected > package > private
 ex.A클래스안에 메소드든 변수든 private 붙이면 B에서 안보임, 접근불가, A클래스에서만 쓰고싶은것
 ex.B클래스안에 메소드든 변수든 package 붙으면 A클래스에서는 접근할수도, 못할수도 -> 두 클래스가 같은 패키지(디렉토리) 안에 있으면 접근가능
 패키지접근지정자=생략형=default접근지정자
 ex.int 앞에 아무것도 없음, 생략형 접근지정자 -> package 붙은건데 안쓴것
 퍼블릭은 어디에서든 보임, 패키지 달라도 보임, 보이긴 보이는데 패키지 다르면 import B를 해야 쓸 수 있음
 
 -> 클래스, 클래스멤버 2가지에 접근지정자 붙일 수 있음
 -> ex. 메인메소드 안에 int num=10;(지역변수) 이런건 클래스 안(클래스~메인 사이에 있으면 클래스 아레 있으니 멤버변수됨)에 있는게 아니라 클래스멤버아니고 접근지정자x
 -> main은 class안에 있으니 이것도 클래스멤버임 ex. class 안에 int num=10; 이건 패키지 생략, 같은 패키지만 쓸 수있음, class 안 private이면 해당 클래스만 쓸 수 있음
 */

class packageClass{} //[0] 생략형(패키지,디폴트) 접근지정자 클래스 잘 만들어짐

//private class PrivateClass{} //[x] 클래스 앞이니까 퍼블릭, 패키지만 붙일 수 있음



//class A{} <- package 생략된것, 생략형 접근지정자 
//ex. abstraction06의 Accountapp에 옮기면 밑에서 못 씀, import안됨, 다른 패키지니까 접x 
//ex. abstraction06의 Accountapp에 옮기고, public으로 바꾸면 보임, 근데 쓰려면 import 해야함
public class AccessModifierOne {//여기서 A 쓸 수 있음
	
	//A a = new A(); //위에 생략안하면 여기서 A쓸 수 있음 
	
	//[멤버변수] -> 멤버변수에 접근지정자 붙여보기
	public int publicVar;
	int packageVar;//패키지 붙은것
	private int privateVar;
	
	//[멤버메소드] -> 멤버메소드에 접근지정자 붙여보기
	public void publicMethod() {
		privateMethod(); //다른 클래스에서 직접 호출이 불가능하니까(접근불가) 접근이 가능한 메소드에서 호출
	}
	
	void packageMethod() {}
	
	private void privateMethod() { //private이 붙은 멤버는 자기 클래스 안에서는 어디서든 접근가능
		
		System.out.println("privateVar:"+privateVar);   
	}
	
	
	
	
	
	
	
}
