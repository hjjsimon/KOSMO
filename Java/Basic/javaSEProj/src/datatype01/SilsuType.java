package datatype01;

import java.math.BigDecimal;

public class SilsuType {

	public static void main(String[] args) {
		//실수형에서 대표 자료형은 double
		//규칙1] 정수형과 실수형 연산시 결과는 실수형, 실수형이 더 큰 그릇
		//long(정수형):8byte, float(실수형):4byte -> 더하면 플롯나옴 4byte, 이건 소수점 때문
		//부동소수점으로 실수 표현->자바 그래서 큰 수까지 표현가능, 고정소수점 -> 큰 수는 안됨
		long ln = 100;//100이 인트형이니까 4바이트 데이터니까, 큰 그릇 8바이트 롱에 충분
		float f1 = 200;//float은 실수인데 정수형도 됨? [o] 200은 int형 정수인데도 플롯형 됨
		//long ln1 = ln + f1; //[x]8바이트, 4바이트 이지만 실수형 4바이트로 넣어야 함, 연산결과가 플롯형이라는 뜻, 롱은 불가
		//방법1]맨앞 long을 float으로 바꾸던가
		float f2 = ln + f1;//[o] 연산결과 플롯이니까
		System.out.println("f2="+f2);//실수형이라서 300.0 으로 소수까지 표기됨
		//방법2]연산결과를 long형으로 형변환
		long ln1 = (long)(ln + f1);
		System.out.println("ln1="+ln1);//long은 정수형이니까 소수점 없이 300으로 나옴
		//방법3]f1만 long으로 바꿈
		//long ln1 = ln + (long)f1;
		System.out.println("ln1="+ln1);
		
		//tip)주로 소수점 이하를 제거하고자 할 때 int형(정수형)으로 형변환하면 된다.
		//float kor=99, eng=80, math=96;
		//float avg=(kor+eng+math)/3; -> /대쉬는 나누기
		//System.out.println("평균:"+avg); //플롯형은 소수점 6자리까지 나옴
		double kor=99, eng=80, math=96;
		double avg=(kor+eng+math)/3;
		System.out.println("평균:"+avg); //double은 소수점 16자리까지 나옴
		System.out.println("평균(소수점제거):"+(int)avg); //소수점 날리는법, 그냥 내림

		/*
		 규칙2]실수형에서 ex. 200.0은 더블형 8바이트 기본으로 저장, 플롯은 4바이트라 8바이트 200.0 담을 수 없음
		 소수점이 붙으면 무조건 더블형으로 인식 단, 소수점이 붙지 않은 값은 플롯에 담을 수 있다
		 */
		
		//방법1]float f3=3.14;//3.14는 더블형 상수, 그렇다고 못담는건 아님 밑에 -> 이거 안씀 그냥 밑에 F씀
		float f3=100;//[o] 인트형 상수100을 플롯형 변수 f3에 저장, 실수형이 더 크니까 정수보다 
		System.out.println("f3="+f3);//플롯이니까 소수점 붙어나옴
		
		f3=(float)3.14;//3.14를 플롯에 형변환 담으면 소수점 정확히 플롯으로도 가능
		System.out.println("f3="+f3);
		
		//방법2]실수 숫자 뒤에 f나 F(권장)
		f3=3.14F;//대문자L 붙이던거랑 동일 플로트형 상수라고 말하는 것
		System.out.println("f3="+f3);
		
		//규칙3]실수형도 같은 자료형끼리의 연산결과는 같은 자료형
		// 큰 자료형과 작은 자료형의 연산결과는 큰 자료형 ex. 플롯+플롯=플롯, 플롯+더블=더블, 더블+더블=더블
		float ff1=100,ff2=3.14F,fresult;
		double d1=100,d2=3.14,dresult;
		fresult=ff1+ff2; //플롯끼리합은 플롯
		System.out.println("fresult="+fresult);
		dresult=d1+d2;
		System.out.println("dresult="+dresult);
		dresult=d1+ff2;
		System.out.println("dresult="+dresult);//3.1400001049 어쩌구 왜 3.14로 안끝나나? 부동소수점 때문임
		
		/* 반지름이 10인 원의 면적
		   단, 면적을 저장하는 변수의 타입을 3가지 형태(int/float/double)의 자료형에 저장하여 출력하라 그리고 소수점 제거하라(셋다 314나와야함)
		   단, 아래의 변수 radius와 pi를 사용해서 구하라, 곱하기는 별표
		 */
		
		int radius = 10;
		double pi = 3.14;
		//면적을 저장하는 변수
		
		int iarea;
		float farea;
		double darea;
		
		System.out.println(radius*radius*pi);
		
		iarea=(int)(radius*radius*pi);  //위에서 iarea를 인트라고 했는데 파이가 더블이니까 r*r*p 는 결과값이 전체 더블이 됨 
		farea=radius*radius*(float)pi;
		darea=radius*radius*pi; // 이건 그냥 되는데 314.0 전체 int 변환  필요
		
		System.out.println("iarea="+iarea);
		System.out.println("farea="+(int)farea);
		System.out.println("darea="+(int)darea);
		
		//길게도 해보기
		
		iarea=radius*radius*(int)pi;
		System.out.println("iarea="+iarea); // 이러면 300이 나옴, 위처럼 전체를 int로 해줘야 314가 나옴, int 해주니까 314로 소수점 날아감
		farea=radius*radius*(float)pi; // 이건 더블이라 플롯에 안담김, 하나만 플롯 담으면 인트인트플롯 = 플롯
		System.out.println("farea="+farea); // 이건 .0 나오니까 인트 필요
				
		
		d1 = 0.1;
		d2 = 0.2;
		System.out.println(d1+d2); // 0.3 안나옴, 플로팅포인트에러= 부동소수점 에러 의미, 큰 숫자 위해 씀
		System.out.println(d1*d2); // 0.02 안나옴, floating point error
		System.out.println(d1+d2 == 0.3); // == 는 같다는 의미, 답이 true 나와야함, 근데 false 나옴
		
		//위 문제 해결법 Bigde 에서 컨트롤 시프트 엔터 빅데시멀이라는 클래스를 쓰면 자바에서 소수점 문제해결가능
		BigDecimal big1 = new BigDecimal("0.1");
		BigDecimal big2 = new BigDecimal("0.2");
		BigDecimal big3 = big1.add(big2);
		System.out.println(big3);
		System.out.println(big3.compareTo(new BigDecimal("0.3"))==0);  // 이게 위에 false랑 같은 코드, 컴페어투는 나중에 배움
		
		/*
		 * BigDecimal타입1.compareTo(BigDecimal타입2)
		 * 값이 같으면 0
		 * BigDecimal타입1이 가리키는 값이 더 크면 1
		 * BigDecimal타입1이 가리키는 값이 더 작으면 -1
		 */
		
	} ///////////////////main
	
}///////class
