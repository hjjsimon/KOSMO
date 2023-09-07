package collection20;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import console.academy.Person;
import console.academy.Student;

//컬렉션도 인터페이스, 컬렉션도 하나의 자료형, 뭘 수집? -> 인스턴스화된 객체
//new하면 인스턴스화, 메모리 생김, 힙영역에 생긴 메모리들을 수집하는게 컬렉션, 이런 객체를 저장하는게 컬렉션
/*	 
[컬렉션 클래스]

- Collection인터페이스로 부터 구현된 클래스 -> 컬렉션 인터페이스를 implements한 클래스를 컬렉션 클래스라고함
- 데이타(객체)를 효율적으로 사용하기 위한 하나의 자료구조 -> ex. 배열은 크기가 고정, 늘리거나 줄이기 불가, 컬렉션은 크기가 정해지지않음, 변경가능, 객체1개 저장시 공간1개, 또하나 저장시 공간2개됨, 3개도 됨, 중간 객체 삭제 시 공간1,3 붙음
- 객체(클래스)만 저장 할 수 있다. ->기본자료형 저장 불가, 인스턴스화된 객체만 저장, jdk5.0 이후로는 오토박싱(int->Integer) 때문에 기본자료형도 되는셈, Integer클래스로 저장
- get()메서드 혹은 Iterator,Enumeration인터페이스의 메서드를 이용해서 객체를 꺼내 올 수 있다. 
- 메모리 기반의 작은 데이타 베이스(=데이터 저장소) 역할을 한다(입력.삭제.검색 등 일을 하는게 데이터베이스라는 소프트웨어)
- Collection인터페이스의 주요 메서드

1) int size():컬렉션에 저장된 객체의 개수 리턴
2) boolean isEmpty();객체가 비었으면 true,아니면 false반환
3) boolean add(Object obj): 객체 추가.성공시 true
4) boolean remove(Object obj):객체 삭제.성공시 true
5) boolean removeAll(Collection col)저장된 객체 전체 삭제
6) boolean contains(Object obj):해당 객체가 있으면 true.
7) Iterator iterator():Iterator인터페이스 반환

컬렉션의 계층 구조](상속상태, 면접잘나옴)

1.인터페이스	        	Collection
		    	     		|
		    	     		|
		   		+----------------------------+              
			    |                            |                         
2.인터페이스들    Set                          List                      Map                       
				|                            |                         |
3.대표클래스들  HashSet                   ArrayList,Vector         HashMap,HashTable         

컬렉션 3계열]
-Set계열은 데이터 저장시 중복을 허용하지 않고 순서없이 입력된다.(저장소가 복주머니라고 생각, 메모리에 객체 만들어지면, 복주머니에 주소저장, 같은 주소객체 저장은 불가, 여튼 주소넣으면 서로 섞임
-List계열은 데이터 중복을 허용하고 순서있게 저장된다. ->배열형태로 되어있음, 크기제한은 없음, 객체 중복저장도 가능, 인덱스가 0,1,2,,, 다르니까 구분 가능함
-Map계열은 키와 값의 쌍으로 저장되고 인덱스가 아닌 키값으로 검색해서 검색 속도가 빠르다 -> 컬렉션 상속안받은 유일, 키값으로 바로 찾음, List나 Set계열보다 검색속도 빠름
*/

//HashSet: Set(인터페이스)계열의 컬렉션 클래스, 앞에 뭐가 더 붙으면 뒷단어의 자식임, implements 했거나 extends
//1.중복저장불가 2.순서없이저장 3.데이터 꺼내올때: iterator() -> 순서 없어서 무슨 객체를 꺼내올지 모름

public class HashSetApp {

	public static void main(String[] args) {
		/*
		 컬렉션 클래스들 : Generic Type에 속한다. 즉 인스턴스화(new, 메모리만듦) 할때 타입 파라미터(<T>또는 <E>)를 지정한다 (type의 t, element의 e)
		                예] HashSet<E>형태다 -> E가 타입 파라미터, 클래스도 타입이니까 제너릭 타입이라고함, 일반적임
		                <E>를 지정하지 않은(<E>가 없는) 타입을 Raw Type이라 한다. -> 모든 타입을 지정 가능, 아무거나 저장 가능
		                Type safety: 타입의 안정성을 보장함 ex. <E>에 String 지정시 String 타입 아닌걸 넣으면 컴파일에서 에러남, String만 넣어야함 
		 */
		//1]<T>타입 파라미터에 타입을 지정안한 경우] 어느 타입의 객체든 저장가능. (Raw Type)
		//1-1]컬렉션 생성
		HashSet set=new HashSet();//<E>없는 지금 HashSet은 raw type		
		
		//1-2]컬렉션에 저장할 객체 생성->총5개
		String strObj1="JAVA";
		String strObj2=new String("WORLD");
		java.sql.Date dateObj=new java.sql.Date(new Date().getTime()); //변수찍고 getTime하든, 그냥 getTime하든 똑같다~
		Integer intObj=100;
		Student stuObj=new Student("가길동",20,"2023학번");
		
		//1-3]컬렉션에 객체저장-boolean add(obj) -> add이런거 외워야함, 모든걸 받을 수 있는건 obj! 헤테로지너스, 부모로 담는다~
		System.out.println(set.add(strObj1)?"저장성공":"저장실패"); //저장성공시 true
		System.out.println(set.add(strObj2));
		System.out.println(set.add(dateObj));
		System.out.println(set.add(intObj));
		System.out.println(set.add(stuObj));
		
		//1-4]컬렉션에 저장된 객체수 얻기:int size()
		System.out.println("중복 저장 전 저장된 객체 수:"+set.size()); //5개 나와야함
		
		//1-5]중복저장[x]-에러는 안나지만 저장은 안됨
		System.out.println(set.add(strObj1)?"저장성공":"저장실패");//이미 저장되었음, set은 중복저장 안되므로 false, 저장실패나옴
		System.out.println("중복 저장 후 저장된 객체 수:"+set.size()); //추가저장 안되므로 5개 나옴
		//그림설명
		//스택에 set변수, 힙에 복주머니0x5678
		//strObj1, 힙에 String@0x1234  
		//add메소드로 저장시, 복주머니 0x5678에 0x1234 이런 주소 저장
		
		//1-6]저장된 객체 꺼내오기
				//    -순서없이 저장됨(반복기를 통해서 꺼내오기)
				/*
				   Iterator의 주요 추상 메소드]
				  ※데이타가 순서 있게 저장된 자료구조(List 계열) 또는 나 순서 없이 저장된 자료구조(Set계열)에서 데이타를 꺼내올때 사용하는 타입(iterator 인터페이스)
				   단,확장 for문 써도 무방 ->자바에서 Iterator 잘 안씀, 확장 for문으로 List, set에서 다 꺼내올 수 있음
				 
				  1) hasNext():꺼내올 객체가 있으면 true반환 없으면 false반환. -> 꺼내올게 있는지?
				  2) hasNext()메소드로 판단후 next()메소드로 꺼내온다 -> 꺼내올게 있으면 next로 꺼내오면됨
				*/
		System.out.println("[반복기 사용]");
		//1-6-1]iterator()메소드로 Iterator타입의 인스턴스 얻기
		Iterator it=set.iterator(); //iterator 반환, iterator가 인터페이스, 이를 이용해서 꺼내오면 됨, iterator반환하니까 iterator로 받음, util패키지
		//1-6-2]hasNext()로 꺼내올 객체의 유무여부 판단, 몇개 저장됐는지 모르니까 while씀, true면 꺼내올게 있음, next로 꺼냄
		while(it.hasNext()) {
			Object obj=it.next();//반환타입이 object, 반환타입이 다양하니 다 커버할 수 있는건 object뿐
			if(obj instanceof String) System.out.println("String타입:"+obj); //String타입은 toString 오버라이딩 돼있으니까 그대로 가져옴(Student만 오버라이딩 안돼있음)
			else if(obj instanceof java.sql.Date) System.out.println("Date타입:"+obj); //오버라이딩 돼있음, 주소안나옴, 저장된 데이터 나옴
			else if(obj instanceof Integer) System.out.println("Integer타입:"+obj);
			else if(obj instanceof Student) System.out.println("Student타입:"+((Student) obj).get()); 
			//왜 get이 안뜨나? get()메소드 앞에 생략형, public으로 해야함, 엔터치면 형변환됨, obj는 Object로 부모타입, Object에 get()메소드 없음, Student클래스에서 get() 새로 만든거라 형변환해서 접근해야함
			//get하면 값으로 뜸, get없으면 Student는 주소뜸
		}//저장한 순서대로 출력안됨
		//모든객체 꺼내온후 next()호출시 실행오류발생]
		//next()메소드 호출시마다 하나씩 꺼내온다, 만약 컬렉션에 꺼내올 객체가 없는데 next()를 호출하면 NoSuchElementException 실행에러
		//it.next(); //[x] 실행오류, 또하나 꺼냄, 위에서 순환하면서 다 꺼내온것, 꺼내올 객체 없으니 while(false) 반환하고 다음 next()로 진행한것, 
		
		/*
	   	확장 for문: 배열이나 컬렉션에 저장된 값(객체)을 꺼내올때 사용하는  for문의 확장형
	   	형식]
	   	for(자료형 객체를담을변수 : 배열명이나 혹은 컬렉션명){ <-중간에 콜론하나, 그 뒤에는 배열변수나 컬렉션변수 여기서 하나씩 꺼내와서 변수에 담겠다는 뜻, 꺼낼게 없으면 for문 밖으로 나감, 배열이나 컬렉션에 저장된게 Integer면 꺼내온 변수의 자료형도 Integer로 하면 됨
	  
	   	}	   	
	    */
		String[] mountains= {"비슬산","덕유산","태백산","속리산"};
		for(String mountain:mountains) System.out.println(mountain);//꺼내온걸 mountain에 담음, 비슬산, 덕유산, 태백산, 속리산 하나씩 출력
		System.out.println("[확장 for문 사용]");
		for(Object obj:set) {//이번에는 타입 다 다르니까 object타입으로 담음
			if(obj instanceof String) System.out.println("String타입:"+obj); 
			else if(obj instanceof java.sql.Date) System.out.println("Date타입:"+obj); 
			else if(obj instanceof Integer) System.out.println("Integer타입:"+obj);
			else if(obj instanceof Student) System.out.println("Student타입:"+((Student) obj).get());
		}
		//1-7]검색: boolean contains(Object e) -> 저장되어있으면 true 반환
		System.out.println(set.contains(strObj2)?"strObj2가 있다":"strObj2가 없다");
		System.out.println(set.contains(intObj)); //있으니까 true나옴
		System.out.println(set.contains("JAVA")); //JAVA가진 스트링객체도 있으니 true
		System.out.println(set.contains("KOSMO")); //이건 없으니 false
		
		//1-8]삭제: boolean remove(Object e) -> 주소 전달해서 확인, 있으면 삭제
		System.out.println(set.remove(strObj1)?"삭제성공":"삭제실패"); //실제로는 주소저장되어있음, 오버라이딩 때문에 값이 나오는것
		System.out.println(set.remove(strObj1)?"삭제성공":"삭제실패"); //위에서 삭제했으니 삭제실패나옴, 지울거 없음
		System.out.println("삭제 후 저장된 객체 수:"+set.size());
		
		//1-9]전체 삭제: boolean removeAll(Collection c) -> 컬렉션은 부모, 자식들이니까 이걸로 다 받아도 됨
		//set.remove(set);
		set.clear(); //위랑 같은 식, 이게 낫대
		System.out.println("전체 삭제 후 저장된 객체 수:"+set.size());
		//2]<?>타입 파라미터 사용-해당 타입(?)만 저장가능.
		//즉 컴파일시 타입체크 가능(타입 안정성:Type Safety)
		//HashSet<Student> set2=new HashSet<Student>(); <- <Student> 했으면 set2에는 Student만 넣어야함
		HashSet<Student> set2=new HashSet<>(); //옛날에는 생성자 HashSet 옆에 다이아몬드에 위에처럼 Student넣어야했는데, jdk1.7부터 생성자의 타입파라미터 생략가능
		//HashSet<Student> <- raw type 말고, 파라미터 붙인걸 제너릭타입(제너릭클래스)라고함
		//2-1]컬렉션에 저장할 객체 생성
		Student stu1=new Student("가길동",30,"2019학번");
		Student stu2=new Student("나길동",20,"2023학번");
		Student stu3=new Student("다길동",25,"2020학번");
		Student stu4=new Student("라길동",40,"2018학번");
		//2-2]객체 저장, add메소드
		set2.add(stu1); //HashSet<Student> set2=new HashSet<>(); -> 타입파라미터를 Student로 지정해서 이전에는 obj였는데 이번엔 Student타입 들어감
		set2.add(stu2);
		set2.add(stu3);
		set2.add(stu4);
		//set2.add(strObj2); //[x] 타입파라미터에 지정한 타입만 저장가능, 컴파일시 타입체크, String이라 안됨
		//2-3]
		System.out.println("[반복기 사용]");
		Iterator<Student> it2=set2.iterator();
//		while(it2.hasNext()) {//꺼내올게 4개나 있으니까 true, 반복1회에 next3회 실시, 첫번째 name꺼낼때는 라길동, 두번째는 가길동, 세번째는 나길동 가져온것, 여기서는 에러가 안남 출력은 이상함, 이제 3개 꺼냈으니 1개 남음, true임, name 첫번쨰 꺼낼때는 에러안남, age는 에러남
//			System.out.println(String.format("이름:%s,나이:%s,학번:%s",it2.next().name,it2.next().age,it2.next().stNumber)); //it2.next() 위에는 Object반환이었는데 지금 Student로 지정
//		}
		//next()메소드는 호출할때마다 객체를 무조건 하나씩 꺼내온다, 고로 아래는 반복 한번에 3개의 Student객체를 꺼내오는것
		//만약 더 이상 꺼내올 객체가 없는데 next()를 호출하면 nosuchelement 실행에러
		while(it2.hasNext()) {
			Student st=it2.next(); //반복 1번에 next1번으로 바꿔줌
			System.out.println(String.format("이름:%s,나이:%s,학번:%s",st.name,st.age,st.stNumber));
		}
		System.out.println("[확장 for문 사용]");
		for(Student st:set2) st.print();
		//2-4]검색
		System.out.println(set2.contains(stu1));
		System.out.println(set2.contains(new Student(null,0,null))); //false 나옴
		//2-5]삭제
		//set2.remove(stu1); //학생개체 4개 중 1개 지우면 아래 출력 3개 나옴
		//set2.clear(); //다 지우면 아래 출력 0 나옴
		//System.out.println("삭제 후 저장된 객체수:"+set2.size()); 
		Scanner sc=new Scanner(System.in);
		System.out.println("검색할 이름을 입력하세요?");
		String searchName=sc.nextLine().trim();
		
		//문제] 컬렉션2에 저장된 객체를 이름으로 검색하자, 있는 이름으로 검색해보고, 없는 이름으로도 검색해보자
		//있으면 해당 학생의 정보를 출력하고, 없으면 "해당 학생이 없어요" 메세지 출력
/*나		Iterator<Student> it3=set2.iterator();
		while(it3.hasNext()) {
			Student st=it3.next();
			if(st.name.equals(searchName)) System.out.println(String.format("이름:%s,나이:%s,학번:%s",st.name,st.age,st.stNumber)); 
			else if(!st.name.equals(searchName)) String.format(st.name+"로(으로) 검색된 정보가 없어요"); 
		}
*/			
		//스택에 stu1 0x1234저장, Student@0x1234가리킴, 복주머니에 0x1234들어가면, 얘도 Studnet@0x1234가리킴, 그 안의 name 가리키면 됨	
		
		boolean isExist=false; //불린값 지정, 컬렉션에서 되게 많이 쓰는 패턴, 암기***********
		for(Student s:set2) //s찍으면 주소나옴, s가 가리키는 name이 같은 애를 찾는것
			if(searchName.equals(s.name)) {
				s.print();
				isExist=true;
				break;
			}
		if(!isExist) System.out.println("해당 학생이 없어요");
	}

}
