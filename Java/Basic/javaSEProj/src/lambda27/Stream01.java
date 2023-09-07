package lambda27;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

//연속된 데이터의 흐름을 Stream이라고함, ex.컬렉션 중 map 제외 .stream()됨, map은 key, value로 저장하는거라 연속된 데이터 흐름이 아님	
public class Stream01 {

	public static void main(String[] args) {
		System.out.println("[스트림 소스:배열]");
		String[] mountains= {"한라산","지리산","덕유산","치악산","비슬산"};
		Stream<String> stream1=Arrays.stream(mountains);//배열을 스트림으로 생성, String넣은걸 보고 알아서 String으로 제너릭메소드 만듦
		stream1.forEach(x->System.out.println(x));//x를 하나씩 뽑아서 출력함,반환타입 하나라 바로 sysout함, 명령문 하나면 {} 필요없음, 확장for문, 일반for문 이런것보다 훨씬 간단함
		//stream1.forEach(x->System.out.println(x));//[x] forEach 최종연산 후 한번 사용된 스트림은 닫힘, 또 Arrays.stream해서 만들어야함
		//System.out.println("[Arrays.sort()로 정렬:원본이 변경됨]");
		//Arrays.sort()로 배열정렬: 원본배열이 변경됨
		//Arrays.sort(mountains);//오름차순됨, 원본이 변경됨, ㄱ.ㄴ.ㄷ.. 덕유산이 0번방, mountains는 stream이 아님, 확장for문 써야함
		//for(String mountain:mountains) System.out.println(mountain);
		System.out.println("[스트림객체의 중간연산 sorted()로 배열정렬:원본이 변경안됨]");
		stream1=Arrays.stream(mountains);//스트림 닫혀서 새로 만들어야함
		//stream1.sorted().forEach(x->System.out.println(x));//sorted는 기본 오름차순, Comparator넣는 sorted는 넣는거 따라 오름차순/내림차순
		stream1.sorted((x,y)->y.compareTo(x)).forEach(x->System.out.println(x));//내림차순, Comparator의 compare 메소드는 인자 2개고 int 반환함
		System.out.println("[원본배열 출력]");
		for(String mountain:mountains) System.out.println(mountain);//원본변경x
		System.out.println("[스트림 소스:컬렉션]");
		List<String> collections=Arrays.asList(mountains);//배열 넣으면 리스트가 만들어짐
		Stream<String> stream2=collections.stream();//리스트 컬렉션 데이터로 스트림으로 만들자
		//System.out.println("[Collections.sort()로 정렬:원본이 변경됨]");
		//Collections.sort(collections);//오름차순 기본 변경
		//for(String mountain:collections) System.out.println(mountain);
		System.out.println("[스트림객체의 중간연산 sorted()로 컬렉션정렬:원본이 변경안됨]");
		//stream2.sorted().forEach(x->System.out.println(x));//원본은 그대로, 스트림만 오름차순정렬
		stream2.sorted((x,y)->y.compareTo(x)).forEach(x->System.out.println(x));//내림차순
		System.out.println("[원본컬렉션 출력]");
		for(String mountain:collections) System.out.println(mountain);
	}

}
