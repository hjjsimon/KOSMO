package collection20;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import console.academy.Student;

public class ArrayListApp {

	public static void main(String[] args) {

		//ArrayList: List(인터페이스)계열의 컬렉션 클래스
		//1)중복저장가능 2)순서있게저장 3)데이터 꺼내올때: iterator()/get(index)
	
		//1]List 계열 컬렉션 객체 생성(Raw Type)
		List list=new ArrayList(); //헤테로지너스, List, ArrayList 임포트
		
		//1-1]객체저장:add()계열
		//boolean add(Object element):계속 추가되면서 인덱스는 0부터 자동부여
		//boolean add(int index,Object element); :계속 추가되면서 저장시 인덱스위치 부여할 수 잇다. 인덱스번호 재배치됨.
		
		//ex. 배열 3개에 0x1111, 0x2222, 0x9999저장, int 1에 0x8888추가로 저장시, 배열 4개되고 1번, 2번은 뒤로 밀려 2번, 3번됨  
		System.out.println(list.add("가산")?"저장성공":"저장실패"); //raw type이라 아무거나 저장가능, String객체 저장, index지정 안했으니 0번방에 저장
		list.add("나산");//인덱스 1번방에 저장
		list.add("다산");//인덱스 2번방에 저장
		//list.add(5, "라산");//인덱스 5번방에 저장? 에러남, 사이즈 3이라 인덱스 0,1,2뿐 인덱스 5 없음, 중간에 빈건 안됨 런타임오류, 3번방에 추가로 저장하는건 가능함
		//인덱스 지정시에는 순차적으로 다음 인덱스를 지정하거나, 기존에 존재하는 인덱스로 지정
		list.add(3,"라산");
		list.add(list.size(),"마산"); //list.size() <-마지막 방번호 다음
		list.add(list.size(),"바산");
		System.out.println("중복 저장 전 객체 수:"+list.size()); //6개 만듦
		//1-2]중복저장:허용 -> 순서로 구분이 가능하니까
		System.out.println(list.add("가산")?"저장성공":"저장실패"); //마지막 방에 저장
		System.out.println("중복 저장 후 객체 수:"+list.size());
		//1-3]출력
		System.out.println("[일반 for문:get(index)]");
		for(int i=0;i<list.size();i++) {
			Object obj=list.get(i); //list계열 get(), 타입파라미터 지정x, 반환타입 지정 안하면 object임
			System.out.println(obj); //오버라이딩, 주소 대신 값 출력
		}
		System.out.println("[확장 for문]");
		for(Object obj:list) System.out.println(obj); //for(Object obj:list) <- list에서 꺼내옴
		System.out.println("[반복기]");
		Iterator it=list.iterator();
		while(it.hasNext()) System.out.println(it.next()); //it.next()에서 꺼내옴, for문, 확장for문, 반복기 셋 다 동일 결과
		//1-4]특정 위치의 인덱스에 저장된 객체를 대체: set(int index, Object obj)-> 밀어내는게 add, 덮어쓰는게 set
		//특정 인덱스 위치에 새로운 객체가 저장되고 기존 객체는 삭제됨, 객체가 주소면 연결이 끊긴거지 연결되어있던 객체가 사라지는건 아님
		list.set(4, "MASAN(SET)"); //4번방 덮어쓰기	
		System.out.println("[MASAN(SET)으로 대체 후]");
		for(Object obj:list) System.out.println(obj); //4번방 SET됨
		list.add(4,"MASAN(ADD)");
		System.out.println("[MASAN(ADD)으로 추가 후]");
		for(Object obj:list) System.out.println(obj); //4번방 ADD됨, 4번방 SET은 5번방으로 밀림
		//1-5]검색
		System.out.println(list.contains("다산")); //다산 있으니 true
		System.out.println(list.contains("아산")); //아산 없으니 false
		//1-6]삭제 -> 주소삭제에 index삭제가 추가됨
		//삭제후에는 자동으로 인덱스가 다시 부여된다
		//인스턴스(주소)로 삭제:boolean remove(Object obj)
		//인덱스로 삭제:Object remove(int index):삭제된 객체(주소)를 반환
		Object obj=list.remove(1); //나산 제거, object반환하므로 obj에 담음
		System.out.println("삭제된 객체:"+obj); //삭제된 객체는 obj에 저장
		System.out.println(list.remove("MASAN(SET)"));
		System.out.println(list.remove("MASAN(ADD)")); //MASAN 둘 다 삭제시킴
		System.out.println("[삭제 후]");
		for(Object o:list) System.out.println(o); //obj중복이라 o로 그냥 바꿈, o에 담고 출력
		//중복된 객체 삭제하기-인덱스가 빠른 것부터 삭제(앞부터)
		list.remove("가산"); //가산, 다산, 라산, 바산, 가산 -> 다산, 라산, 바산, 가산 됨, 앞 가산 삭제
		//전체 삭제: boolean removeAll(Collection<?> col) 혹은 void clear()
		//list.clear(); //다 지움 removeAll대신 이거 쓰래
		System.out.println("[삭제 후]");
		for(Object o:list) System.out.println(o);
		//1-7]객체의 인덱스 찾기:int indexOf(Object)
		// 주소로 인덱스 찾을 때 해당 주소가 없는 경우 -1 반환
		System.out.println("다산의 인덱스:"+list.indexOf("다산"));
		
		//2]타입 파라미터 지정:<Student>
		List<Student> list2=new ArrayList<>();//Student타입 아닌거 넣으면 에러, 타입 안정성
		//2-1]컬렉션에 저장할 객체 생성
		Student stu1=new Student("가길동",30,"2019학번");
		Student stu2=new Student("나길동",20,"2023학번");
		Student stu3=new Student("다길동",25,"2020학번");
		Student stu4=new Student("라길동",40,"2018학번");
		//2-2]객체 저장
		list2.add(stu1); 
		list2.add(stu2);
		list2.add(stu3);
		list2.add(stu4);
		list2.add(stu2); //중복저장
		System.out.println("[중복 저장 후]");
		for(Student s:list2) s.print(); //Student로 지정해서 Student로 꺼냄, 가나다라나
		list2.remove(stu2);//인스턴스(주소)로 중복저장된 객체 삭제-> 나길동 둘 다 주소 동일, 빠른순서의 1번 인덱스의 나길동 삭제
		System.out.println("[중복 저장된 객체 삭제 후]");
		for(Student s:list2) s.print(); //가다라나
		//2-3]출력 -> index 필요시 일반 for문, 아니면 거의 확장for문
		System.out.println("[일반 for문:get(index)]");
		for(int i=0;i<list.size();i++) list2.get(i).print();
		System.out.println("[확장 for문]");
		for(Student s:list2) s.print();
		System.out.println("[반복기]");
		Iterator<Student> it2=list2.iterator(); //iterator의 Student를 반환
		while(it2.hasNext()) it2.next().print();
		//2-4]삭제 -> 인덱스, 주소 둘 다 삭제가능
		//인덱스 삭제] -> 주소반환해줌
		System.out.println("삭제된 객체(학생)의 이름:"+list2.remove(2).name); //라길동 삭제, name 라길동 반환, .name 없으면 주소반환 
	
		//문제] 사용자로부터 이름을 입력받아 그 이름으로 검색해서 인덱스 위치(indexOf사용)을 알아내서
		//해당 인덱스로 그 객체를 삭제하고 삭제된 객체의 정보(이름,나이,학번)을 출력하여라
		
		Scanner sc=new Scanner(System.in);
		System.out.println("삭제할 학생의 이름 입력?");
		String name=sc.nextLine().trim();
		
/*		if(list2.contains(name)) {
			list2.remove(list2.indexOf(name));
			System.out.printf("삭제된 객체(학생)의 이름:%s,나이:%s,학번:%s",
					list2.remove(list2.indexOf(name)).name,
					list2.remove(list2.indexOf(name)).age,
					list2.remove(list2.indexOf(name)).stNumber);
		}
*/			
		for(Student s:list2)
			if(name.equals(s.name)) {//주소비교
				int index=list2.indexOf(s);
				list2.remove(index).print();
				break;
			}
	}////main
}////class
