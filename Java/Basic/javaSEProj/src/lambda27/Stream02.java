package lambda27;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Stream02 {
	
	public static void main(String[] args) {
		System.out.println("[스트림 객체의 주요 메소드]");
		//map():스트림의 각 요소를 람다식으로 적용해 변경하는 함수(map만 쓰는건, 중간연산이라 스트림 안닫힘, forEach는 최종연산이라 스트림 닫힘)
		System.out.println("1.map()함수");//중간연산
		IntStream stream=IntStream.rangeClosed(1, 5);//시작~끝 모두 포함해서 1~5 int숫자로 스트림을 만듦
		stream.map(e->e*e).forEach(x->System.out.println(x));;//인자 하나 받고 하나 반환, 2면 2*2, 4면 4*4~
		//reduce():스트림의 요소를 줄이는 함수
		System.out.println("2.reduce()함수");//최종연산
		System.out.println(IntStream.range(1, 6).reduce((e1,e2)->e1+e2));//OptionalInt, range는 끝값포함x, 1~5까지만 씀, e1 e2 대신 x y 해도 됨(reduce최종이라 닫힘)
		System.out.println(IntStream.range(1, 6).reduce((e1,e2)->e1+e2).getAsInt());//OptionalInt를 getAsInt로 int로 바꿨더니 15
		//스트림 요소 1,2,3,4,5 있었음, 처음 1,2 들어오면 1+2=3됨, 그 후 e1에 3이 들어가고 e2에는 3 들어와 3+3=6, e1에 6, e2에 4 6+4=10, 이런식으로 15됨, 이게 reduce임
		System.out.println("3.reduce()함수");//중간연산
		//IntStream.rangeClosed(5, 10).limit(3).forEach(x->System.out.println(x));//5,6,7까지 3개만 가지고 스트림만들어 반환, 5,6,7출력
		IntStream.rangeClosed(5, 10).limit(3).forEach(System.out::println);//이것도 위랑 동일, 근데 잘 모르겠으니까 그냥 위에꺼 써
		System.out.println("4.skip()함수");
		//skip():스트림의 최초요소부터 설정한 요소 수까지 제외하고 나머지로 새로운 Stream반환
		IntStream.rangeClosed(5, 10).skip(3).forEach(x->System.out.println(x));//5,6,7 제외하고 8,9,10으로 스트림 생성
		//distinct():중복값 제거 후 새로운 스트림 반환(요소들 중 같은 값을 가지는 요소가 있으면 그 중 하나만반환
		System.out.println("5.distinct()함수");
		Arrays.asList(1,2,5,4,2,3,2,5,1,1).stream().distinct().sorted().forEach(x->System.out.println(x));;//List컬렉션 만든것.stream으로 stream객체 반환, 스트림 만들고 디스팅트로 중복 다 제거함,오름차순 정렬까지
		//filter():조건을 만족하는 요소만 스트림으로 반환
		System.out.println("6.filter()함수");//true만 가지고 스트림 만듦
		IntStream.rangeClosed(1, 10).filter(x->x%2==0).forEach(x->System.out.println(x)); //짝수인것만 스트림에 남겨둠
		System.out.println("7.count()함수");
		System.out.println(IntStream.rangeClosed(9, 999).filter(x->x%2!=0).count());//9~999숫자로 스트림 만듦, 그리고 그 중 홀수만 걸러 개수를 셈, count도 집계니까 최종연산
		System.out.println("8.sum()함수");
		System.out.println(IntStream.rangeClosed(1, 10).filter(x->x%2==0).sum());//sum도 집계니까 최종연산
		System.out.println("9.max()함수");
		System.out.println(IntStream.rangeClosed(1, 10).max().getAsInt()); //OptionalInt반환하니까 getAsInt로 반환함, 최대값 가져옴
		System.out.println("10.min()함수");
		System.out.println(IntStream.rangeClosed(1, 10).min().getAsInt());
		System.out.println("11.findAny()함수");//Any는 불안정함수
		System.out.println(IntStream.rangeClosed(1, 10).findAny().getAsInt());//첫번째 숫자 1
		System.out.println("12.findFirst()함수");
		System.out.println(IntStream.rangeClosed(1, 10).findFirst().getAsInt());//첫번째 숫자 1
		
		//문]1~100까지 숫자 중 50이상이고 홀수이면서 5의 배수인 수의 합을 구하라 ex. 55+65+75+85+95, 단 람다식과 스트림만 이용
		System.out.println("[스트림 미사용]");
		int sum=0;
		for(int i=0;i<=100;i++) {//이건 좋은데 100번반복, 데이터 더 많으면 for문 못씀, 무조건 스트림 써야함
			if(i>=50 && i%5==0 && i%2!=0) sum+=i;
		}
		System.out.println(sum);
		
		//스트림사용
		//방법1)
		System.out.println(IntStream.rangeClosed(1, 100).filter(i->i>=50 && i%5==0 && i%2!=0).sum());
		//방법2)
		System.out.println(IntStream.rangeClosed(1, 100).skip(49).filter(i->i%5==0 && i%2!=0).sum());
		//System.out.println(IntStream.rangeClosed(1, 100).skip(50).filter(x->x%2!=0).filter(x->x%5==0).sum());->50을 제외하면 안됨
		//방법3)
		System.out.println(IntStream.rangeClosed(1, 100).filter(x->x>=50).filter(x->x%2!=0).filter(x->x%5==0).sum());
		
	}
}
