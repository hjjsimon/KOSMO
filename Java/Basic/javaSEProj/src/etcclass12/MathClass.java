package etcclass12;

import java.util.Random;

public class MathClass { //Math는 있음, Class 붙여줘야함

/*	https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Math.html
 	
 	Class Math
	java.lang.Object
	java.lang.Math
	public final class Math
	extends Object            ->상속받으면 extends 나옴
	
	
	Method Summary
	All Methods/ Static Methods/ Concrete Methods ->매개변수로 받은 값으로 작업, 인스턴스 메소드 없음, 스태틱메소드뿐, 별도 값을 가질 필요 없으니까
*/	
	//문] Math클래스의 abs()메소드와 같은 기능을 하는 메소드를 정의해라
	private static int abs(int value) {
		
		return value>=0? value: -value; //선생님
		
/*		if(value<0) value = -value;
		return value;
*/		
	}//////////////////////abs
	
	//문] Math클래스의 round()메소드와 같은 기능을 하는 메소드를 정의해라
	private static long round(double value) { //double형은 8바이트니까 long형 8바이트 맞춰 반환
		
		return value>0? (long)(value+0.5): (long)(value-0.4); //return에도 삼항연산자로 조건식 가능함, 양수는 0.5더해서 단위 올림, 음수는 0.4빼서 단위 내림*****
	}

	//문]
	private static int pow(int num, int loop) {
			
		int sum=1; // 더하는건 0부터지만 곱하는건 1부터, 0에 곱하면 0됨
		for(int i=0; i<loop; i++) sum*=num;
		return sum;
		
		
		
/*나		for(int i=1; i<=loop; i++) {
				num*=num;             // 2^3이면, 1회 2*2=4, 2회 (2*2)*(2*2)=16, 3회(16)*16 이렇게 됨 틀림
		}
			return num;
*/		}

	
	
	public static void main(String[] args) {

		//Math클래스는 인스턴스화(객체화/Heap에 메모리생성) 불가]
		//Math math=new Math(); //[x]The constructor Math() is not visible, 접근지정자할 때 봄, private라는 뜻
		System.out.println(Math.PI); //클래스명 Math.으로 접근, 3.14~ 나옴
		//Math클래스의 겁나 많은 정적메소드 중 중요한 것들 다름
		//Math.//오버로딩: Math. 치면 abs () 안에 double, float등 다양하게 넣을 수 있음을 의미, 오버라이딩 아님
		
		//Math 클래스의 주요 메소드
		//절대값: static 반환타입 abs(매개변수): 반환타입은 매개변수의 타입에 따라 결정됨, int넣으면 int나오고~ 넣은대로 절대값 반환해줌 (절대값-> absolute라 abs)
		float f= -3.14F;
		double d= -100.9;
		int num=10;
		System.out.println("[abs()메소드]");
		System.out.println(Math.abs(f));
		System.out.println(Math.abs(d));
		System.out.println(Math.abs(num));
		System.out.println("[내가 만든 abs()메소드]");
		System.out.println(abs(-100));
		System.out.println(abs(100));
		System.out.println(abs(-99));
		
		//올림값: static double ceil(double a) //더블 넣으면 더블로 반환, 천장이 ceil
		//소수점에서 큰 수로 가장 가까운 정수를 찾는다
		//ex. 3.4->4/ 3.9->4/ -3.4->-3  ==> x축 좌표 그리면 됨, 무조건 오른쪽 정수값 찾으면 됨
		System.out.println("[ceil()메소드]");
		System.out.println(Math.ceil(3.4));
		System.out.println(Math.ceil(3.9));
		System.out.println(Math.ceil(-3.4));

		//내림값: static double floor(double a)
		//소수점에서 작은 수로 가장 가까운 정수를 찾는다.
		//ex. 3.4->3/ 3.9->3/ -3.4->-4 ==> x축 좌표, 왼쪽 정수값
		System.out.println("[floor()메소드]");
		System.out.println(Math.floor(3.4));
		System.out.println(Math.floor(3.9));
		System.out.println(Math.floor(-3.4));
		
		//반올림: 무조건 소수점 첫째자리에서 반올림(0.5포함)
		//static long round(double a)
		//static int round(float a)
		//ex. 3.5->4/ -3.8->-4/ -3.5->-3/ -3.4->-3(x축좌표 -3.5오른쪽이라 오른쪽정수값으로)
		System.out.println("[round()메소드]");
		System.out.println(Math.round(3.49)); //3
		System.out.println(Math.round(3.51)); //4
		System.out.println(Math.round(-1.4)); //-1
		System.out.println(Math.round(-0.8)); //-1
		System.out.println(Math.round(-0.4)); //0
		System.out.println(Math.round(-1.8)); //-2
		System.out.println(Math.round(-1.5)); //-1
		
		System.out.println("[내가 만든 round()메소드]");
		System.out.println(round(3.49)); //3
		System.out.println(round(3.51)); //4
		System.out.println(round(-1.4)); //-1
		System.out.println(round(-0.8)); //-1
		System.out.println(round(-0.4)); //0
		System.out.println(round(-1.8)); //-2
		System.out.println(round(-1.5)); //-1
		
		//지수: static double pow(double a, double b): a의 b승 의미, double형 반환이라 8.0 소수점 붙여 반환
		//ex. pow(2.3): 2의 3승 -> 2*2*2 (=2^3)
		System.out.println("[pow()메소드]");
		System.out.println(Math.pow(2, 3)); 
		System.out.println(Math.pow(3, 3)); 

		System.out.println("[내가만든 pow()메소드]");
		System.out.println(pow(2, 3)); 
		System.out.println(pow(3, 3)); 

		//Math.random()과 같은 기능을 제공해주는 Random클래스
		//5부터 10까지 랜덤하게 발생
		//(int)(Math.random()*(차이값+1))+시작값
		//Random클래스의 인스턴스변스.nextInt(차이값+1)+시작값 ----> 위와 같은 식
		Random rand=new Random();
		//System.out.println(rand.nextInt(6)+5); //5~10까지 랜덤값 나옴
		
		//seed: 난수를 발생시키는 알고리즘이 사용하는 씨앗값(랜덤하게 발생시킨 숫자 의미)
		//seed값 설정(set): 동일한 패턴(고정된)의 난수를 발생시킨다
		rand.setSeed(num);
		//1에서 10사이의 숫자를 무작위로 10개 발생(for씀)
		for(int i=0; i<10; i++) {
			int random=rand.nextInt(10)+1; //9에 1더해 10, 시작값은 1
			System.out.printf("%-3d",random); //10 8  3  5  1  6  2  2  1  7 이런식으로 최대10 랜덤숫자 10개 나옴
		}
		//랜덤하게 발생한 데이터는 고정시켜야 머신러닝에 사용가능 -> 이럴 때 위에 rand.setseed쓰면 랜덤발생된 숫자 10개가 그대로 유지됨
		
		
		
	}//////////////main

	

}//////////////////class
