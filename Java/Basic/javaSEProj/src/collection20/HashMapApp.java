package collection20;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import console.academy.Student;

/*
HashMap: Map(인터페이스)계열의 컬렉션 클래스(Map이 부모)
	- key 와 value(객체)의 쌍으로 객체 저장
	- 키값은 중복이 안된다 -> Set컬렉션과 같음, 중복값으로 저장시 마지막 키값으로 저장됨 ex. name이라는 키값으로 가길동, 나길동, 다길동 저장시 다길동 하나만 저장됨
	- 키값으로 검색하기 때문에 다른 컬렉션보다 검색 속도가 빠르다
	
	데이타 꺼내올때: ->Set, List는 확장for문 바로 적용가능, 여기는 바로 확장for문 적용불가
	keySet():Set<?>타입 반환 ->키값 중복 안되므로 복주머니에 Set컬렉션 형태로 저장
	get(키값):키값에 해당하는 value(객체)값 반환 ->그리고 get키 (암기: keySet() 후 get(키))
	keys()  :Enumeration<?>타입 반환(HashTable의 메소드) -> 키값만 반환, 근데 열거형(Enumeration)으로 반환, HashMap에는 없고 HashTable에만 있음
	values():Collection<?>타입 반환,value값만 반환 ->반환이 컬렉션타입, 얘 상속받은게 List, Set -> 여기 확장for문 적용가능, value로 꺼내와서 바로 확장for문 쓸 수 있음
	*/

public class HashMapApp {

	public static void main(String[] args) {
		//1]컬렉션 객체 생성-Raw Type
		//Map<K, V> //K는 key(주로 스트링), V는 value,  
		Map map=new HashMap();//key, value 둘 다 어느타입이든 가능
		//2]객체 저장:Object put(키값, Value(객체)값);
		//반환값(Value의 타입과 동일): 키값으로 저장된 이전 value(객체)가 반환됨, 이전에 저장된 value(객체)가 없을시 null 반환
		//ex. 가길동 저장시 null 반환, 나길동 저장시 가길동 반환 그리고 가길동은 나길동으로 덮어씌워짐
		System.out.println("name이라는 키값으로 이전에 저장된 객체:"+map.put("name", "가길동")); //null반환함, 타입 파라미터 지정 안해서 인자 둘 다 Object 타입
		map.put("age", 20); //int넣음?->Integer로 오토박싱된것
		map.put("gender", "남자");
		map.put("address", new String("가산동"));
		//3]저장된 객체수:int size()
		System.out.println("저장된 객체수:"+map.size());//가길동, 20, 남자, 가산동 4개 저장, 사이즈4
		//4]키값을 중복해서 저장: 저장은 되나 기존 키값에 해당하는 value의 새로운 객체로 대체됨.
		System.out.println("name이라는 키값으로 이전에 저장된 객체:"+map.put("name", "KaKilDong")); //이전에 name으로 저장된 가길동 대체함
		System.out.println("키값 중복 저장 후 저장된 객체수:"+map.size());//객체수 대체한거라 그대로 4
		//5]출력
		//5-1]키값을 알때(대부분):Object get(키값)
		System.out.println("키값을 알 때:"+map.get("name"));
		//for(Object obj:map) {}//반복가능한 객체는 next로 하나하나 꺼내올 수 있음, for(i=1~10000)까지 반복한다고치면 10000개의 메모리를 미리 준비해야함, 근데 iterable한 객체는 필요할때마다 next로 하나씩 꺼내오면됨 그럴필요 없음
		//[x]확장 for문은 배열이나 반복가능한(Iterable) 객체(Set 혹은 List계열 컬렉션)에만 적용가능 -> 3개! 배열, set, list
 		//5-2]키값을 모를때->키셋 후 겟키
		//Step1]Set<?> keySet()메소드 호출: 키값들을 Set계열의 컬렉션형태로 반환
		//Step2]Step1에서 얻은 Set계열 컬렉션에 확장for문 적용해서 키값 얻기
		//Step3]get(키값)으로 Value(객체)값 얻는다.
		//Step1]키값들이 저장된 Set컬렉션 얻기-------------
		Set keys=map.keySet(); //반환타입 Set이니까 Set에 담음 
		//Step2]확장 for문 적용해서 키값 가져오기-------------
		for(Object key:keys) {
			//Step3]get(키값)으로 Value값 얻기-------------
			Object value=map.get(key);
			System.out.println(String.format("%s : %s",key,value));//->위에 Set으로 저장해서 순서 무작위로 나옴
		}
		//5-3]Value만 얻어올때:values() -> key는 안가져옴
		Collection values=map.values();
		System.out.println("[value(객체들)만 얻기]");
		for(Object value:values) System.out.println(value); 
		//6]검색: boolean containsKey(key)- 해당 키가 존재하면 true, 아니면 false
		//       boolean containsValue(value)- 해당 값이 존재하면 true, 아니면 false
		System.out.println(map.containsKey("name")); //t
		System.out.println(map.containsKey("tall")); //f
		System.out.println(map.containsValue("남자")); //t
		System.out.println(map.containsValue("여자")); //f
		//7]삭제: Object remove(키값)-삭제된 키값에 해당하는 value(객체) 반환
		System.out.println("삭제된 객체(value):"+map.remove("gender")); //gender 키값으로 남자 밸류 갖고있었음
		System.out.println("[삭제후 출력]"); 
		for(Object key:keys) {//복붙 
			Object value=map.get(key);
			System.out.println(String.format("%s : %s",key,value)); //남자만 사라짐
		}
		//7-1]전체삭제:removeAll()은 없음, clear()로 삭제
		map.clear();
		System.out.println("삭제후 저장된 객체수:"+map.size()); //다 지움, 0남음
		//HashTable클래스(Map계열)에서는 keySet()/values()/get("키값")으로 모두 꺼내올 수 있다
		//또한 keys()메소드를 통해서도 가능 (HashMap클래스에는 keys()메소드가 없음)
		map=new Hashtable<>();
		//객체저장]
		map.put("기관","한국 소프트웨어 인재개발원"); //헤테로지너스로 쓰면 편한게 자식이라 해시맵, 해시테이블 둘 다 돼서 가능
		map.put("위치", "가산동");
		map.put("대표", "정영철");
		Enumeration em=((Hashtable)map).keys();//map.keys() 없음, 형변환 필요 ->Enumeration 반환, 같은 타입으로 받아줌
		while(em.hasMoreElements()) {
			//키값 얻기
			Object key=em.nextElement();
			//밸류값 얻기
			Object value=map.get(key);
			System.out.println(String.format("%s : %s",key,value));
		}
		//타입 파라미터 지정-Generic Type(타입안정성(컴파일 시점에 타입체크), 형변환 불필요)
		Map<String, Student> genericMap=new HashMap<>();
		//컬렉션에 저장할 객체 생성
		Student stu1=new Student("가길동",30,"2019학번");
		Student stu2=new Student("나길동",20,"2023학번");
		Student stu3=new Student("라길동",25,"2020학번");
		Student stu4=new Student("다길동",40,"2018학번");
		//객체 저장]
		genericMap.put("학생1", stu1);
		genericMap.put("학생2", stu2);
		genericMap.put("학생3", stu3);
		genericMap.put("학생4", stu4);
		System.out.println("[키값을 알때]");
		genericMap.get("학생1").print(); //학생1의 정보 출력
		System.out.println("[키값을 모를때]");
		Set<String> kies=genericMap.keySet(); //kies에서 key값들(String타입)을 꺼내옴
		for(String kie:kies) {
			Student value=genericMap.get(kie); //밸류값 반환이니까 갖다대면 Student
			System.out.println(String.format("%s : %s",kie,value.get())); //Student의 toString 오버라이딩 안해서 주소나옴 -> .get() 하면 됨 이건 오버라이딩함
		}
		//[맵계열 컬렉션에 저장된 객체 정렬하기]
		Map<String,Integer> sortMap=new HashMap<>(); //모든 타입이 지금 하는대로 정렬되는건 아님
		sortMap.put("D", 10); //int->Integer 오토박싱
		sortMap.put("C", 5);
		sortMap.put("A", 9);
		sortMap.put("B", 7);
		//1.Entry를 요소로 갖는 List컬렉션 객체 생성 -> 이번 참가자는~ 이런 느낌
		//-Entry는 Map인터페이스의 내부 정적 인터페이스이다 -> 원래 static안되는데 내부는 됨
		//-Entry는 해당 맵의 key=value쌍을 요소로 갖는 객체다
		//  즉 위의 sortMap에 저장된 키=값의 엔트리들을 저장한 리스트 컬렉션 생성-------------------------소스
		List<Entry<String, Integer>> entries=new Vector<>(sortMap.entrySet());//key-value pair(키,밸류 한쌍)를 map entry라고함, 수정할 수 없대, 뒤에 new Vector에는 컬렉션 어쩌구 넣는거 씀
		//지금 위에 List 컬렉션을 만듦, D-10의 키-밸류 한쌍을 엔트리라고 하는데, 이걸 요소로 한 방에 하나씩 4방에 각각 갖는 List컬렉션을 만든 것
		System.out.println("[Entry객체가 저장된 리스트 컬렉션 출력]");
		for(Entry<String,Integer> entry:entries) {//꺼내오면 Entry임, toString이 오버라이딩 되어있음(딱 보니까 그렇대 왜지)
			System.out.println(entry); //key=value형태의 Entry객체
		}
		//2. 1번에서 생성한 List컬렉션에 Collections.sort()적용 ->sort는 정렬을 못함, 지금 이건 리스트만 가능
		Collections.sort(entries,new Comparator<Entry<String,Integer>>() {//타입파라미터를 엔트리타입으로 바꿔줌
		
			@Override
			public int compare(Entry<String,Integer> o1, Entry<String,Integer> o2) {//앞에서 뒤에 빼면 오름차순, 뒤에서 앞에 빼면 내림차순
				//숫자일때는 -, 문자열일때는 compareTo()
				//[value로 정렬하기]
				//return o1.getValue()-o2.getValue();//오름차순
				//return o2.getValue()-o1.getValue();//내림차순
				//[key로 정렬하기]
				//return o1.getKey().compareTo(o2.getKey());//오름차순
				return o1.getKey().compareTo(o2.getKey()); 
			}
		});
		System.out.println("[정렬후 출력]");
		for(Entry<String,Integer> entry:entries) {
			System.out.println(entry); //key=value형태의 Entry객체
		}
		
		
	}

}
