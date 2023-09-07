package polymorphism14;

import java.util.Date;

public class OverridingApp {

	public static void main(String[] args) {

		//인스턴스변수child는 앞에 Child타입, 메모리는 new 옆에 대로 Child 타입 
		Child child=new Child("가길동",20,"Child Member Var1"); //지금껏 변수명이랑, 앞에 타입 똑같이 맞춰줌, Child 타입 변수명 child,
		//new Child <- Child타입으로 변수 만든 것 이게 Homogeneous
		//근데 상속을 배운 후로 앞에 Child-> Parent로 바꿔도 됨, 근데 child라는 변수로 접근하는 멤버가 틀려짐
		//Parent child-> 이것도 다형성에 속함 Heterogeneous
		
		//그림설명
		//스택에 child 생김, 메모리는 new 옆에 있는대로 Child 타입메모리가 힙에 생김, Child@0x1234, 주소는 child에 들어감
		//Child@0x1234에는 Parent로부터 상속받은 작은방 안에 더 작은방 name, age/ eat(), sleap(int), walk(Date), exercise(), getParent(), printParent() [static은 static영역]
		//Parent급 작은방에는 변수 newExtendVar, 메소드 newExtendMethod()[새로정의], eat()[새로정의], walk(int)[새로정의], sleap(int)[오버라이딩], exercise()[오버라이딩], getParent()[오버라이딩], printParent()[오버라이딩]
		//child 변수가 Child 타입이면 Child타입 메모리주소 Child@0x1234를 가리킴 -> 부모+자식 멤버 사용가능
		//child 변수가 Parent 타입이면 Child타입 메모리주소 Child@0x1234(주소는 똑같이 지정) 안의 Parent 작은방을 가리킴 ->부모 멤버만 사용가능
		
		System.out.println("[자식타입의 인스턴스 변수-오버라이딩한 메소드 호출가능]"); //이제 sleep(int) 호출 시 전체방을 가리키므로 오버라이딩 된게 나타남, 부모꺼는 사라짐, .해도 안뜸
		child.sleep(10);
		child.exercise();
		child.getParent();
		child.printParent();
		
		System.out.println("[자식타입의 인스턴스 변수-자식에서 새롭게 확장한 메소드 호출가능]"); //프라이빗인 eat()는 지금 OverridingApp으로 클래스 달라서 안뜸
		child.newExtendMethod();
		child.walk(10); //자식에서 새로 만든 walk
		child.walk(new Date()); //상속받은 메소드, 부모로부터 상속받은 walk, Date타입이니까 new Date() 넣음 ->부모님이 산책하신다

		//인스턴스 변수 타입(Parent)과 메모리 타입(Child)이 다르다 -> Heterogeneous! 이것도 다형성! 프로그램 확장성 때문에 많이 쓴다~~~~~ 
		Parent parent=new Child("나길동",30,"Child Member Var2");
		//메모리는 Child타입, 변수는 Parent타입, 변수명 parent
		//주소는 0x1234 동일, 근데 상속받은 Parent 작은방으로 감*******, 접근은 당연히 parent.으로 주소 찾아 접근, eat() 프라이빗은 당연히 안뜨고
		// Parent를 찾아가서 walk하면 *******
		//parent.walk(null) -> walk는 오버라이딩 안함, 그냥 부모상태의 walk(Date) 보임*****************
		//sleep(int)는 오버라이딩 돼서 리모델링 돼서 이전 건물 없음
		//exercise(), getParent(), printParent() 도 오버라이딩 된게 보임
		
		/*
		  인스턴스 변수:Parent타입(부모)
		  메모리 : Child타입(자식)		 
		
		  ※ 인스턴스 변수가 부모타입든 자식타입든 무조건 오버라이딩한 메소드가 호출된다. -> 리모델링 했으면 이전 건물 없어짐
		    단, 오버라이딩을 하지 않았다면 당연히 상속받은 부모의 메소드가 호출된다. -> 리모델링 안했으니까 상속받은 그대로 씀
		   
		  ※만약 오버라이딩한 메소드호출시 부모의 메소드를 사용하고자 한다면 super키워드로 접근해서 재정의 하면된다.
		  
		   @Override
			public void exercise() {
				//super.exercise(); 이대로 두면 재구현 아님, 부모 메소드 그대로 씀       -> Child 클래스안에 있는거 super.쓰면 부모님이 운동하신다 출력! 이게 위의 당구장말
				System.out.println("자식이 운동한다");
	}
			   
		 */
		System.out.println("[부모타입의 인스턴스 변수-오버라이딩한 메소드 호출가능]");
		parent.sleep(10);
		parent.exercise();
		parent.getParent();
		parent.printParent();
		
		System.out.println("[부모타입의 인스턴스 변수-자식에서 새롭게 확장한 메소드 호출가능]"); //프라이빗인 eat()는 지금 OverridingApp으로 클래스 달라서 안뜸
		//parent.newExtendMethod(); //자식에서 새로 정의한 부모에는 없는거라 undefined 나옴
		//parent.walk(10); //자식에서 새로 만든 walk는 int타입, parent로 찾아가는 walk는 Date타입, 숫자 넣을 수 없음
		parent.walk(new Date()); //이건 당연히 뜸
		//Child@0x1234의 eat()는 private이니까 안보임
		
		//형변환으로 Parent작은방 지칭하다가 Child@0x1234로 지점을 바꾸면 보임(포인트 직선이 이동한다~~)
		//부모타입의 인스턴스 변수로 자식에서 새롭게 확장한 멤버에 접근하려면 형변환을 해야한다! 이제 Parent안 말고 바깥까지포함 전체가 보인다~		
		Child ch=(Child)parent; //Child로 형변환해서 ch에 담은것
		ch.newExtendMethod();//이제 이것도 뜸
		ch.walk(10);//walk(int)자식꺼 뜸
		ch.walk(new Date()); //walk(Date)부모꺼도 뜸
		
//		boolean
//		equals(Object obj)	->타입이 달라도 주소비교가 가능한 Object의 메소드
//		Indicates whether some other object is "equal to" this one.
		//이해용 그림이 자식이 넓은거지, 부모가 더 넓음
		//넓은거에 작은걸 넣을 수 있는거니까
		//Parent parent=new Child(); -> 이게 가능함
		//Child ch1=new Parent(); -> 이건 불가!!!
		Child ch1=(Child)new Parent();//큰 그릇에 작은 그릇 담는거랑 동일하게 형변환하면 됨, 당연히 상속관계 있어야 가능
		
		// A의 a 있고 B의 b 있을 때, 둘 다 Object 상속은 무조건 받으니까
		//a.equals(Object~~
		//모든 타입의 부모니까 모든 타입을 담을 수 있음 -> 오버로딩 안해도 됨 -> 모든타입을 담을 수 있게 만든게 (Object 변수)***** 뭐 짱이다 이런 뜻
		//equals(int a) -> 이런거에 더블 넣고싶으면 (int)로 형변환 해야함
	}

}
