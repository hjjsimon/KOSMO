package collection20;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
[배열이나 컬렉션(List계열만)에 저장된 객체(데이타) 정렬하기] 
※단,배열의 타입 혹은 List계열 컬렉션에 저장된 객체의 타입은 반드시 1.Comparable인터페이스를 상속받고 2.compareTo()메소드 오버라이딩(sort메소드 인자가 하나일때)
1]배열 정렬
  1)오름차순(디폴트:1,2,3,~순서)
  		Arrays.sort(배열) - 이때 배열은 기본 자료형 타입의 배열이어도 상관없다(Comparable 상속필수)
  		Arrays.sort(T[],Comparator<? super T>) - T는 참조형(클래스) 타입의 배열이어야 한다. T는 Comparable이 구현 불필요.	 
  2)내림차순:참조형 타입의 배열이어야 한다(int 같은 기본자료형x)	 
  		Arrays.sort(T[],Collections.reverseOrder()) - 쉬운방법, T는 Comparable 구현 필수
  		Arrays.sort(T[],Comparator<? super T>) T는 Comparable이 구현 불필요.
2]컬렉션 정렬
	1)오름 차순
		Collections.sort(List계열 컬렉션) List계열 컬렉션에 저장할 객체는 Comparable 구현 필수
		Collections.sort(List계열 컬렉션,[Comparator<? super T>]) List계열 컬렉션에 저장할 객체는 은 Comparable이 구현 불필요
	2)내림 차순	
		Collections.sort(List계열 컬렉션,Collections.reverseOrder()) List계열 컬렉션에 저장할 객체는 Comparable 구현 필수
		Collections.sort(List계열 컬렉션,[Comparator<? super T>]) List계열 컬렉션에 저장할 객체는 은 Comparable이 구현 불필요

*/
public class CollectionNArraySort {

	public static void main(String[] args) {

		System.out.println("[Arrays클래스로 배열 정렬하기]");
		String[] names= {"홍길동","가길동","도길동","나길동","마길동"}; //대부분 자료형과 관련 클래스는 Comparable 돼있음, String도, 이런건 신경안써도 됨
		System.out.println("1. 오름차순으로 정렬");
		//Arrays.sort(names); //원본배열이 재배치 된다(in-place방식), 괄호 안의 Object가 Arrays같은 배열 넣을 수 있는 것
		Arrays.sort(names,new Comparator<String>() {//원본배열이 재배치 된다(in-place방식) 동일
			@Override
			public int compare(String src, String target) {
				//숫자는 -, 문자열은 compareTo
				return src.compareTo(target); //src가 앞이면 오름차순
			}//Comparable 모르겠다 싶으면 그냥 항상 구현하면 됨 T[]에는 배열 넣어주면 됨
		});
		for(String name:names) System.out.println(name); //오름차순 정렬출력, 원본방이 바뀜, 홍길동이 0번-> 맨뒤번 됨
		System.out.println("2. 내림차순으로 정렬");
		//Arrays.sort(names,Collections.reverseOrder());
		Arrays.sort(names,new Comparator<String>() {//원본배열이 재배치 된다(in-place방식) 동일
			@Override
			public int compare(String src, String target) {
				//숫자는 -, 문자열은 compareTo
				return target.compareTo(src); //src가 앞이면 오름차순
			}//Comparable 모르겠다 싶으면 그냥 항상 구현하면 됨 T[]에는 배열 넣어주면 됨
		});	
		for(String name:names) System.out.println(name); //내림차순 정렬출력
		
		System.out.println("Arrays클래스의 기타 유용한 메소드]");
		System.out.println("1. 배열의 요소들을 하나의 문자열로 변환:Arrays.toString(배열)");
		//System.out.println(names); //주소나옴
		System.out.println(Arrays.toString(names)); //배열의 데이터를 일렬 문자열로 대괄호에 묶어서 줌********굿 왕좋음
		System.out.println("2. 배열을 List콜렉션으로 변환:Arrays.asList(T...a)");//T는 무조건 클래스 의미
		//List<String> list=Arrays.asList(names);//마우스 대보면 배열 넣으면 List<String>반환한다고 나옴, 그대로 담아줌, 앞의 <String>은 무시
		List<String> list=Arrays.asList("홍길동","가길동","도길동","나길동","마길동");
		for(String s:list) System.out.println(s);
		System.out.println("3. List콜렉션을 배열로 변환:리스트콜렉션객체.toArray()");
		Object[] objArray=list.toArray();//Object형 배열 반환한다고 나옴, Object에 담음
		System.out.println(Arrays.toString(objArray));
		
		System.out.println("[Collections클래스로 컬렉션에 저장된 객체 정렬하기]");//컬렉션은 객체 저장하는 그릇, List컬렉션만 됨
		List<Integer> numbers=Arrays.asList(90,45,89,100,25,68,1004,50); //List<> 안에 int불가 Integer 필수, asList써서 인자로 전달된 대로 List 만듦
		System.out.println("1. 오름차순으로 정렬");
		//Collections.sort(numbers);//인자 1개짜리라 Integer comparable 되어있음
/*		Collections.sort(numbers, new Comparator<Integer>() {
	 	@Override
			public int compare(Integer src, Integer target) {
				return src-target;
			}//컬렉션에 저장된 타입이 Integer니까 보라색 Integer넣음
		});*/
/*		numbers.sort(new Comparator<Integer>() {//collections 안해도됨, Comparator 넣으라함, 넣어줌
			@Override
			public int compare(Integer src, Integer target) {
				return src-target;
			}
		});*/
		numbers.sort(Comparator.naturalOrder());//JDK8에서 추가 -> 아까 콘솔아카데미 하던거 Comparable없이, 이게 간편함 근데 Comparable 구편필요, Collections 임포트 안해도돼서 좋음 
		System.out.println(numbers);//numbers List컬렉션인데 toString이 문자열로 바뀌도록 오버라이딩 되어있음
		System.out.println("2. 내림차순으로 정렬");
		//Collections.sort(numbers,Collections.reverseOrder()); //
/*		Collections.sort(numbers, new Comparator<Integer>() {
		 	@Override
					public int compare(Integer src, Integer target) {
					return target-src;
				}//컬렉션에 저장된 타입이 Integer니까 보라색 Integer넣음
		});*/
/*		numbers.sort(new Comparator<Integer>() {
				@Override
				public int compare(Integer src, Integer target) {
					return target-src;
				}
		});*/
		numbers.sort(Comparator.reverseOrder());
		System.out.println(numbers);//내림차순 출력
		//예외발생시 catch로감, 예외없으면 catch말고 그냥 아래로 내려감
		//e에는 절대 null아님, 에러정보가 들어감, jvm이 실행해서 넣어줌, 에러 발생시 해당 클래스의 객체를 만들어서 e에 넣는것
		//에러나면 아래코드실행안되고 catch로 넘어감, 아래코드 실행하고싶은데 안됨, 근데 해야되는 DB끊기 이런건 finally에 넣는것
		//finally는 예외무관 무조건 실행할 절, try에서 데이터베이스에서 코드를 받으면 에러시 catch갔다가 finally 와서 DB끝내는거 해야함
		//try는 단독불가, catch나 finally 혹은 둘다 써야함
	}

}
