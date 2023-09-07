package datatype01;

public class JungSuType {

	public static void main(String[] args) {
		/*
		 원칙] 1. 큰 자료형(그릇)과 작은 자료형(그릇)의 연산결과는 큰 자료형을 따른다. ex. int(4) short(2) 더하면 인트나옴 
		 	  2. 같은 자료형끼리의 연산 결과는 같은 자료형이 된다. ex. 인트 + 인트 = 인트
		 	  예외] int(4byte)형보다 작은 자료형(byte(1),short(2),char(2))들끼리 연산 결과는 int(4)형이다. ex. 바이트 더하기 쇼트는 인트
		 */
		//규칙1] 기본형에서 수치형(정수형,실수형) 중 정수형에서 대표 자료형은 int임. int로 들어가야하는게 정수값의 기본. 그래도 127까지는 byte도 됨
		byte b1; //b1이라는 이름의 메모리를 만든 변수 선언, 크기는 1바이트, byte를 int로 바꾸면 밑밑 128도 됨
		b1 = 127; //[o] 최대 127까지 저장 가능
		System.out.println("b1="+b1);
		//b1 = 128; //[x] 127 넘어서 안됨. 마우스 갖다대면 타입 미스매치, 타입이 안맞는대 바이트인데 128은 인트형상수임, 127도 int에 들어가는 int형 상수는 맞음
		b1 = (byte) 128; //형변환이 일어남, 128빨간줄의 첫번째 클릭한 결과, 바이트로 넣음
		System.out.println("b1="+b1);
		//규칙2]int형보다 작은 자료형(byte,short,char)들의 연산결과는 int형이다. (예외)
		byte b2=20,b3=30;
		//byte b4=b2+b3;//20+30은 50인데 왜 int에 담기지? 연산결과가 왜 4바이트야?, 아 b2,b3는 변수라 뭐가 담길지 몰라서 아예 int로 담아버리는 것 20말고 127됨
		//[x]20,30은 사용자가 입력하는 값, b2+b3는 넉넉하게 int로 넣어줌
		//방법1]연산결과인 int형에 담는다
		int num1=b2+b3; //b2 b3 둘다 바이트라 합쳐도 int 못넘으니까 ㄱㅊ
		System.out.println("num1="+num1);
		//방법2]위에처럼 b2 b3를 바이트로 찌그러트려서 담아버림
		byte b4=(byte)(b2+b3);
		System.out.println("b4="+b4);
		
		short s1=1000,s2=2000;
		//short s3 = s1+s2; //똑같이 타입미스매치나옴
		//방법1] num1 인트니까 괜찮음, num1 변수를 바꾼것(불확실)
		num1 = s1+s2;//[o] 
		System.out.println("num1="+num1);
		//방법2] short에 담기 6만5천얼마까지 가능, 위 결과 동일
		short s3 = (short)(s1+s2);
		System.out.println("s3="+s3);
		/*
		 규칙3]정수형에서 int형보다 작은 자료형들을 제외한 자료형들끼리(int,long)의 연산결과는 모두 원칙
		 ex. int+int=int/ int+long=long/ long+long=long 
		 */
		int num2=100,num3=200;
		long ln1=1000,ln2=2000;
		num1 = num2+num3;//연산결과 int형 값 바뀜 num1 300으로
		System.out.println("num1="+num1);
		long ln3 = ln1+ln2;//연산결과 long형
		System.out.println("ln3="+ln3);
		//int num4 = ln1+num2; //[x]빨간줄, int+long이므로 long 가야함
		ln3 = ln1+num2;//해결1]long형에 담는다
		System.out.println("ln3="+ln3);//1100으로 바뀜
		//해결2]연산결과 전체를 int형으로 찌글 변화
		int num4 =(int)(ln1+num2);//[o]
		//해결3]굳이8바이트 아까움 4바이트 인트에 넣는 다른 방법, long만 인트로 찌글
		num4 =(int)ln1+num2;//[o]
		
		//long ln4 = 2;//2는 4바이트에 담음, 정수형은 무조건 4바이트 인트 21억까지가능
		//long ln4 = 2200000000; //[x]22억 충분한데 왜 안되지 8바이트 2^63인데? 22억은 인트형 상수, 그냥 정수니까 롱형급 큰데 인트라고 생각하는 것, 
		long ln4 = 2200000000L; // L; 가 롱형 상수라는 뜻 (l, L 소,대문자 둘 다 가능)
		System.out.println("ln4="+ln4);
		
		//규칙4] 숫자앞에 0(영)이 붙으면 8진수 의미
		num4 = 0412; //8진수 412는 266 십진수라는 뜻 -> 8^2*4 + 8^1*1 +  
		System.out.println("num4="+num4);
		//규칙5] 숫자 앞에 0x(0X)가 붙으면 16진수
		//0 1,2...9,A(10의미),B(11)~~F(15)
		num4 = 0x10A;//1*16^2 + 0*16^1 + A(10)*16^0 = 256+0+10
		System.out.println("num4="+num4);
		//1]국어,영어,수학 점수 및 점수 총합을 저장할 수 있는 변수 4개를 선언하여라(int사용)
		
		int a;
		int b;
		int c;
		int all;
		
		int kor,eng,math,total;
		
		//2]국어에는 89, 영어에는 99, 수학은 78을 저장하여라
		
		a = 89;
		b = 99;
		c = 78;
		
		kor=89;
		eng=99;
		math=78;
		
		//3] 국영수의 총합을 구해서 1]번에서 선언한 총합 저장용 변수에 저장하여라
		
		all = a+b+c;
		
		total=kor+eng+math;
		
		//4] 각 국영수 점수 및 총합을 출력하여라, 더블쿼테이션안에 있는건 그대로 출력됨, 콤마 넣어주면 구분되는 센스
		
		System.out.println("a="+a);
		System.out.println("b="+b);
		System.out.println("c="+c);
		System.out.println("all="+all);
		
		System.out.println("국어:"+kor);
		System.out.println("영어:"+eng);
		System.out.println("수학:"+math);
		System.out.println("총점:"+total);
		
		System.out.println("국어:"+kor+"영어:"+eng+"수학:"+math+"총점:"+total);
		
		System.out.println("국어:"+kor+",영어:"+eng+",수학:"+math+",총점:"+total);
		
		//국영수총점 한번에 쓰려면 사이에 + 넣기
		
	}///////////main

}////////////////////class
