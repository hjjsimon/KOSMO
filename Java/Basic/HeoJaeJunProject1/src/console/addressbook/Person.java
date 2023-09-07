package console.addressbook;

public class Person implements Comparable<Person>{ //스튜던트, 티쳐는 아빠 퍼슨, 할아버지 컴페어러블 펄슨, 상속받은거라 아래 오버라이드 에러안남->숙제
	//[멤버변수]
	public String name;
	public int age;
	//[기본생성자]-> 상속받을거라 미리 만듦(generate from superclass)
	public Person() {}
	//[인자생성자]->(generate using field)
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}////Person
	//[멤버메소드]
	String get() {
		return String.format("이름:%s,나이:%s",name,age);
	}
	void print() {//여기에 위에 "이름:%s,나이:%s",name,age 그대로 만들어도 됨, GUI는 창이 뜸, 콘솔용은 print만 해도 됨, GUI용은 get 출력, 그래서 두개로 만드는 것
		System.out.println(get());
	}
	@Override
	public int compareTo(Person target) {
		return name.compareTo(target.name);
	}
}