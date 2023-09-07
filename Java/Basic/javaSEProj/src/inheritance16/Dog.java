package inheritance16;

//[Dog is a Animal 성립] -> Dog가 작은범위니까 자식 가능
public class Dog extends Animal{
	
	//Animal을 상속받은 Dog클래스 생성하자(Animal의 기본생성자 정의 안하고 인자 생성자만 정의한경우)마자 Dog클래스명에 빨간줄 해결법
	//방법1. 부모 클래스에 기본생성자 정의
	//방법2. 자식 클래스에서 부모클래스의 인자생성자 호출
	
//[기본 생성자]
//public Animal() {}	
//저장하면 자동 컴파일, 이 때 기본생성자 아래가 자동으로 생기는 것, 그리고 안에서 부모의 기본생성자 호출함!
	
//	public Dog() { //이거 자동으로 되는 것, 근데 부모의 인자생성자 만들어서 기본생성자가 없어짐, 
//		super();
//	}
	
	//해결1] 부모의 기본생성자를 만듦
	
	//해결2] 부모의 인자생성자를 호출함
//	public Dog() {
//		super("",0,""); 
//	}

	//[멤버변수]
	String dogKind; //자식에서 확장한 멤버, 부모에는 없음
	int year; //Dog에서 새롭게 추가한 멤버변수(지역 틀리니 ㄱㅊ), 부모에 year있는데 자식에도 year?? -> 안쓰면 자식꺼 기본임, ㄱㅊ
	//그림으로 치면 힙의 Dog 내에 지금 위 year 작은방, 부모Animal 통으로 작은방, 그 안에도 부모의 year 더 작은방 
	
	//[기본 생성자]
	public Dog() {
		//super();;
	}
	
	//[인자 생성자]
	public Dog(String species, int year, String gender, String dogKind) {
//		this.species=species;
//		this.gender=gender;
		//this.year=year;
//		super.year=year; //Animal 안의 year에 2 넣음******
		super(species,year,gender); //3줄을 한줄로 줄이고, 인자 3개 한번에 호출, 심지어 super 안써도 됨!!
		this.dogKind=dogKind;
		//super(species,year,gender); [x] 무조건 슈퍼, 디스는 첫줄!
	}
	
	//[멤버 메소드]
	void bark() {
		//super(); //[x] 생성자 안에서만 호출가능
		//System.out.println(year+"살인 "+dogKind+"가 짖다");
		System.out.println(super.year+"살인 "+dogKind+"가 짖다"); // 이것도 super로 바꿔줘야함******
	}
	void printDog() {
		printAnimal();
		System.out.println(",개 종류:"+dogKind);
	}
	static void staticMethod() {//정적메소드 연습하려고 넣는것
		//super. //[x] 디스처럼 인스턴스 메소드에서만 사용가능
	}
	
	//방법1: super키워드로 부모의 year 접근해서 반환
	int getAnimalYear() {
		return super.year;
	}
	
	
	
	
	
	
}
