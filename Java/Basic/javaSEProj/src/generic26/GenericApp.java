package generic26;

import java.util.Arrays;

import console.academy.Student;

public class GenericApp {

	public static void main(String[] args) {

		System.out.println("[타입파라미터 미지정]");
		MyGeneric generic1=new MyGeneric<>();//Raw Type
		generic1.setField("NOT TYPE-PARAMETER");//setField(Object), 넣을거 세팅안함 Object뜸, 내맘대로 String넣음, setter로 field 세팅함
		Object obj=generic1.getField();//반환타입 Object뜸, Object타입이라 만든 메소드 안뜸
		System.out.println("저장된 문자열의 길이:"+((String)obj).length());//String으로 형변환해야 String클래스 메소드뜸
		System.out.println("[타입파라미터 지정]");
		MyGeneric<Student> generic2=new MyGeneric<>();//콘솔아카데미의 Student 임포트
		//generic2.setField("TYPE-PARAMETER");//[x]컴파일시 타입체크,아까 Object넣으라고 했는데 지금은 지정해서 Student 넣으라고함
		generic2.setField(new Student("가길동", 20, "2023학번"));//setField(Student) 
		generic2.getField().print();//이제 형변환 필요없음, 바로 print가능
		System.out.println("[제너릭 메소드 호출:정적메소드-타입파라미터 지정]");//제네릭 메소드 3개 만들어놓음
		//정적->클래스명접근,호출시 타입결정,근데 Number상속받은 타입만 가능하게 제한걸어놓음, 메소드의 T가 Integer됨,List<Integer>넣어야함
		//asList도 스태틱! 클래스명 Arrays로 접근!, 1~5의 총합해줌
		MyGeneric.<Integer>genericMethod(Arrays.asList(1,2,3,4,5));//반환타입 없음, 메소드안에서 sysout으로 호출
		//MyGeneric.<Integer>genericMethod(Arrays.asList(1.0,2,3,4,5));//[x]제너릭메소드 호출시 타입 Integer로 결정했으므로 1.0은 불가, 타입체크됨
		System.out.println("마지막 요소:"+MyGeneric.<Double>getEndValue2(1.0,2.0,3.0,4.0,5.0));//Var args임, Double넣어야함
		//System.out.println("마지막 요소:"+MyGeneric.<Double>getEndValue2(1,2.0,3.0,4.0,5.0));//[x]무조건 Double만, 1 불가
		System.out.println("[제너릭 메소드 호출:정적메소드-타입파라미터 미지정]");
		MyGeneric.genericMethod(Arrays.asList(1,2,3,4,5));//T에 1,2,3,4,5 들어간거보고 알아서 Integer로 추론함
		MyGeneric.genericMethod(Arrays.asList(1.0,2,3,4,5));//[o]T지정시 안됐는데 이제 됨, 어느타입이든 되는것
		System.out.println("마지막 요소:"+MyGeneric.getEndValue2(1,2.0,3.0,4.0,5.0));//[o]이것도 이제 됨
		System.out.println("[제너릭 메소드 호출:인스턴스형메소드-타입파라미터 지정]");
		System.out.println("마지막 요소:"+generic2.<Integer>getEndValue1(1,2,3,4,5));//위에 인스턴스화 해놓음,인스턴스변수명 접근
		//System.out.println("마지막 요소:"+generic2.<Integer>getEndValue1(1.0,2,3,4,5));//[x] 1.0불가
		System.out.println("[제너릭 메소드 호출:인스턴스형메소드-타입파라미터 미지정]");
		System.out.println("마지막 요소:"+generic2.getEndValue1(1,2,3,4,5));
		System.out.println("마지막 요소:"+generic2.getEndValue1(1.0,2,3,4,5));//[o] 숫자면 다 됨
		System.out.println("[정적 일반 메소드의 타입 파라미터 제한(?)");//설명못들음
		MyGeneric.genericMethod1(Arrays.asList(1,2,3,4,5));
		MyGeneric.genericMethod1(Arrays.asList(1.0,2.0,3.0,4.0,5));
	}

}
