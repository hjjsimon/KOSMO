package constructor15;

public class Constructor02 {

	public static void main(String[] args) {

		//기본생성자로 객체 생성]
		Person person1=new Person(); 
		person1.print();//null,0,null로 기본초기화상태로 호출됨
		person1.initialize("가길동", 20, "가산동"); 
		person1.print();//위대로 초기화 후 출력               -> .print는 불러줘야 호출됨, 생성자는 무조건 1빠 호출(new ~ 하는게 호출임)
		
		//기본생성자        -> 먼저 출력되는거?? 확인
		//[미상님의 정보]
		//나이:1
		//사는 곳:부모님 주소
	
		
		Person person2=new Person("나길동"); //이렇게 쓰면 이름만 초기화하는 Person이 출력됨
		person2.print();
		
		Person person3=new Person("다길동",30);
		person3.print();
		
		Person person4=new Person("라길동",40,"청담동"	);
		person4.print();
		
	}

}
