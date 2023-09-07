package collection20;

import java.util.Set;
import java.util.TreeMap;

public class TreeMapApp {

	public static void main(String[] args) {

		//TreeMap:Map계열 컬렉션, 키값이 정렬된 상태로 저장됨(디폴트는 오름차순 정렬)
		//HashMap보다 성능이 떨어지나, 정렬된 키값으로 출력하고자할때 유리
		
		TreeMap<Character, String> map=new TreeMap<>();
		//객체 저장]-> 막 저장했는데 오름차순으로 알아서 저장됨, ㄱ은 곽길동, ㅎ은 홍길동으로 대체됐을 것
		map.put('ㄷ', "도길동");
		map.put('ㅎ', "하길동");
		map.put('ㅁ', "마길동");
		map.put('ㄱ', "고길동");
		map.put('ㅎ', "홍길동");
		map.put('ㄱ', "곽길동");
		System.out.println("[키로 오름차순해서 정렬-디폴트]");//ascending=오름차순
		Set<Character> keys=map.keySet(); //
		for(Character key:keys) {
			String value=map.get(key);
			System.out.println(String.format("%s:%s",key,value)); //ㄱ,ㄷ,ㅁ,ㅎ 자동 오름차순 정렬저장
		}
		System.out.println("[키로 내림차순해서 정렬]");//descending=내림차순
		keys=map.descendingKeySet(); //NavigableSet을 반환한다고함, 위에 keys는 Set, 부모타입에 자식 담을 수 있으니 그대로 복붙해서 써도 됨
		for(Character key:keys) {
			String value=map.get(key);
			System.out.println(String.format("%s:%s",key,value)); //ㄱ,ㄷ,ㅁ,ㅎ 자동 오름차순 정렬저장
		}
		
		///////////////////////소스
		
	}

}
