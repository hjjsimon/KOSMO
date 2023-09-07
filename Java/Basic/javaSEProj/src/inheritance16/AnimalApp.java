package inheritance16;

public class AnimalApp {

	public static void main(String[] args) {

		Dog dog=new Dog("포유류",2,"수컷","치와와");
		dog.printDog();
		dog.bark();
		
		//종:포유류,나이:0,암수:수컷,개 종류:치와와
		//2살인 치와와가 짖다
		//왜 0살로 출력??
		//스택에 dog생기고 주소0x1234 들어감, 힙에 Dog@0x1234 타입의 메모리가 생김, 멤버변수는 dogKind, year, 생성자 빼고, 메소드는 bark(), printDog()
		//상속받은 Animal작은방 안에는 더작은방으로 멤버들 species, year, gender, printAnimal() 존재
		
		//위의 new Dog 쫓아가면
		//public Dog(String species, int year, String gender, String dogKind) {
			//this.species=species;
			//this.gender=gender;
			//this.year=year;
			//this.dogKind=dogKind;
		//}
		// 지금 상속받은 Animal 작은방 안의 species에 포유류, gender는 수컷,
		//근데 Dog 클래스에서 this.year는 Dog 자신의 year라서 Animal급의 Dog@0x1234 방 안의 year에 2가 저장, 치와와도 동급 방 species에 생김
		//System.out.print(String.format("종:%s,나이:%s,암수:%s",species,year,gender)); -> 여기의 year는 Animal안의 0인 상태의 year가 초기화 되는 것
		//System.out.println(year+"살인 "+dogKind+"가 짖다"); -> 이건 Dog 클래스의 year라 2살 맞음
		//this.year=year; 를 바꿔줘야함
		
		//Dog의 year속성 접근: 인스턴스변수(dog)로 접근
		System.out.println(dog.year); //0
		//dog의 주소로 찾아가는 year는 0살~~, animal에서 상속받은게 안뜸, 완전히 다른 클래스(AinmalApp)에서는 부모꺼까지는 안뜸, 그러면 한도끝도 없으니까
		
		//그러면 부모 Animal의 year는 어떻게 접근?
		//동일한 필드(멤버변수)가 존재할 때 부모의 필드에 접근하기(자식의 인스턴스변수 dog로)
		
		//방법1] Dog클래스 가서 get메소드 만들어 super.year로 반환
		System.out.println("방법1:부모의 year를 반환하는 메소드 호출:"+dog.getAnimalYear()); //2
		
		//방법2] 형변환
		System.out.println("방법2:자식에서 메소드 정의하지 않고 형변환:"+((Animal)dog).year); //2, Dog, Animal은 상속관계라 형변환 가능!
		//Animal로 형변환 후 전체 한번 더 감싸서 . year로 찾아감
		
	}

}
