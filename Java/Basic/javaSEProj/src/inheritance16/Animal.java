package inheritance16;
/*
super키워드:
-인스턴스화된 부모 클래스를 지칭한다.
-즉 부모 클래스의 멤버(인스턴스형 멤버)에 접근할때 사용   -> super. 찍으면 다 보임, 정적인건 물론 정적으로 접근하라고 나옴
-※자식클래스와 부모클래스의 멤버
(메소드,변수)
명이 동일할때 구분해주기 위한 키워드
-정적 메소드안에서 사용불가(this와 같다) -> 인스턴스화는 new할때
super():부모의 생성자 호출, 자식의 생성자 안에서만 호출 가능
        this()와 super()동시호출 불가, 둘 다 생성자라 첫줄에 있어야하는데 위배됨
        자식의 생성자(기본,인자)에서는 super()로 명시하지 않으면 무조건 먼저 부모의 기본 생성자를 호출한다.
        super()역시 항상 첫문장으로 기술
 자식의 생성자에서 super()를 명시하지 않아도 자동으로 부모의 기본 생성자를 먼저 호출한다
 만약 부모의 기본 생성자가 아닌 인자 생성자를 호출하려면 직접 명시한 super(인자들)를 이용해서 호출해주면 된다.
*/
/*
* =====================================================
*    super()               |    this()
* =========================+===========================
* 부모의 생성자 호출           | 자신의 생성자 호출
* =========================+===========================
* 자식의 생성자 안에서 호출     | 자신의 생성자 안에서 호출
* =========================+===========================
*                항상 첫문장에 기술(공통)
* =====================================================
* this()와 super()는 한 생성자 안에서 동시에 호출 불가
* ===================================================== 
* 
//super는 부모, this는 자기 자신*******
//super()는 부모의 기본생성자, this()는 자신의 기본생성자 -> 둘 다 첫줄에 있어야하므로 공존은 불가 
//super()도 this()과 규칙이 동일!
//this는 멤버변수와 지역변수 충돌할 때 차이구분용
//super는 부모의 멤버와 자식의 멤버가 충돌할 때 차이구분용(부모는 super()를 붙여야함) 
	
*/
public class Animal {
	
	//[멤버변수]
	String species;
	int year;
	String gender;
	
	//[기본 생성자]
	public Animal() {}
	
	//[인자 생성자] -> 부모가 인자생성자를 만들어서 기본생성자가 없어진것 자동으로 추가x
	public Animal(String species, int year, String gender) {
		//super();
		this.species = species;
		this.year = year;
		this.gender = gender;
	}
	
	//[멤버 메소드]
	void printAnimal() {
		System.out.print(String.format("종:%s,나이:%s,암수:%s",species,year,gender)); //뒤에 쭉 연결하려고 ln 지움
	}
	
	
	
}