package lambda27;

//함수형 인터페이스는 추상메소드를 하나만 갖는다
@FunctionalInterface //이거 붙이면 컴파일이 안됨?? 몰라 그렇대
//인터페이스에 @FunctionalInterface 어노테이션 붙이면, 그 인터페이스는 함수형 인터페이스가 돼서 무조건 추상메소드 하나만 가져야한다 
public interface MyInterface {
	
	int calc(int a,int b); //인자 2개 받고 int를 반환하는 추상메소드를 만든 것
	//void a(); -> 추상메소드 2개 되는 인터페이스는 당연 원래 괜찮음, 근데 람다식 쓰려면 추상메소드 2개 불가
}
