package stringclass11;


class A{} //둘 다 클래스니까 참조형, 근데 A타입, B타입으로 클래스 다름
class B{}

public class StringBasic {

	public static void main(String[] args) {

		//[값(데이터) 비교]
		//기본자료형에서 ==는 실제 저장된 값 비교하는 연산자. ex. int a=10; int b=10; a==b -> 각 방의 저장된 값끼리 비교
		//*****참조형에서 ==는 주소비교(단, 같은 클래스 타입끼리만 비교가능)
		
		int num1=20, num2=20;
		String strResult= num1==num2? "데이터가 같다": "데이터가 다르다"; //삼항연산자 true니까 같다 나옴
		System.out.println(strResult);
		
		//인스턴스화
		A a=new A();
		B b=new B();
		
		//타입이 다르므로 주소비교 불가]
		//StrResult= a==b? "주소가 같다": "주소가 다르다"; //[x]빨간줄, A랑 B타입 클래스가 달라 주소비교 불가
			
		A a1=new A();//클래스, 설계도니까 찍어내면 됨, 당연히 멤버는 없고
		strResult= a==a1? "주소가 같다": "주소가 다르다"; //[x]둘다 new 했으니까 주소 무조건 다름
		System.out.println(strResult);
		System.out.printf("a=%s,a1=%s%n",a,a1); //각 주소 뽑기
		a=a1; //a주소 0x1234 -> a1주소 0x5678로 같게 바뀜, 같은 메모리를 참조(가리킴), a클래스는 참조 끊어지고 가비지 콜렉터가 가져감
		strResult= a==a1? "주소가 같다": "주소가 다르다"; //[o]
		System.out.println(strResult);
		System.out.printf("a=%s,a1=%s%n",a,a1);
		
		//타입이 다르므로 주소 대입연산도 불가] 단, 타입이 다르더라도, 두 클래스 간의 상속관계(부모자식관계)가 존재하면 대입연산 및 형변환 가능
		//a=b;
		//ex. 최상위인 Object(무조건 상속존재, 모든 클래스는 오브젝트 상속받음) 중 equals 메소드 -> boolean 결과로 나옴, 주소가 같으면 같은 객체
		//당연히 타입 다르면 주소비교 되든말든 다름, 그냥 에러 안나게 false비교가능한 메소드
		
		//타입이 다르더라도 주소비교가능-equals()메소드 사용시]
		strResult=a.equals(b)? "주소가 같다": "주소가 다르다"; //오버라이딩 판단방법?
		System.out.println(strResult);
		System.out.printf("a=%s,b=%s%n",a,b);
		System.out.printf("a=%s,b=%s%n",a.toString(),b.toString()); //객체의 주소를 문자열로 반환하는 메소드

		//이제 스트링 시작
		String str1=new String("JAVA"); //str1. 찍으면 메소드 겁나 많이 나올 것, 데이터 저장하는 메모리 어딘가 있겠지 거기에 문자열 JAVA저장
		String str2=new String("JAVA"); //String 같은 설계도로 또 찍음, 
		//str1 0x1234, str2 0x5678 주소 있음
		System.out.println("[new 연산자 사용]");
		System.out.println("==로 주소비교:"+(str1==str2)); //참조형, 같은 타입이니까 주소비교 가능, new 각각 했으니까 당연히 다름
		//얘는 타입이 달라도 주소비교 가능 -> 문자열 비교하도록 리모델링, 상속받은걸 리모델링
		System.out.println("문자열 비교:"+str1.equals(str2)); //반환타입 boolean 똑같은데 object -> String으로 됨, 상속받은것, 리모델링한것, 주소비교하는게 아니고 저장된 데이터를 비교하기로
		//strResult=a.equals(b) 위에서는 상속받은걸 그대로 비교함, String에서는 문자열 비교 시 ==쓰면 안됨
		//저장된 str1,2에 저장된 문자열 데이터값을 비교 시 true! 둘 다 JAVA!, 문자열 비교 시 equals 쓴다!, 문자열 쓰도록 오버라이딩 되어있다. 리모델링
		
		/*
		 ※String클래스에서 실제 저장된 문자열 비교시에는 equals()메소드 사용 (==는 안됨)
	     String클래스에서 Object클래스의 equals()메소드를 주소값 비교가 아니라 실제 저장된 문자열값을 비교하도록 오버라이딩 해놓음
		 ※Object클래스의 equals()메소드는 원래 두 객체간의 주소 비교를 하는 메소드임 		
		 */
		
		//문자열, 숫자 데이터는 프로그래밍에서 많이 씀, 문자열은 이전에 했듯 아래 기본자료형처럼 사용도 가능
		String str5="Hello"; //String은 new 안하고 그냥 이렇게 만듦
		String str6="Hello"; //new연산자 안쓰고 오히려 이렇게 저장하는편, String str1=new String("JAVA"); -> 이건 주소 다름
		
		System.out.println("==로 주소비교:"+(str5==str6)); //new로 각각 만듦, false 나와야함, 근데 str5,6에 같은 주소 있다고 나옴
		
		//대소문자 구분해서 비교-equals()메소드]
		System.out.println("equals()로 문자열비교:"+str5.equals(str6)); //오버라이딩 돼있음 -> 같은 문자열 저장, true 나와야함
		
		//대소문자 구분하지 않고 비교-equalsIgnoreCase()메소드]
		System.out.println("equalsIgnoreCase()로 문자열 비교:"+str5.equalsIgnoreCase(str6)); //대소문자
		
		
		//왜 str5,6 같은 주소???
		//64줄 실행 시 heap 어딘가 String클래스 객체 생김, 데이터 어딘가 Hello저장, String클래스의 수많은 메소드도 존재,
		//System.out.println(str5); //주소값 저장되어있는데, 찍어보면 Hello나옴
		//문자열 같은데 또 메모리 만들면 아깝잖아, str5만들고 str6 똑같이 문자열 Hello저장된 객체 만들려고하면 안만듦, 그냥 str6에 같은 주소만 넣고, 같은 클래스 가리키게함
		
		
	/*	str6="HellO";
		System.out.println("==로 주소비교:"+(str5==str6)); // O달라지면 달라짐
		System.out.println("equals()로 문자열비교:"+str5.equals(str6));
	*/	
		
		//역시 Wrapper클래스처럼 String클래스에서도 Object클래스의 toString()메소드를 //toString() 원래 객체 주소를 문자열로 봔환해줌
		//메모리 주소가 아닌 실제 저장된 문자열을 반환하도록 오버라이딩 해놓음
		
		String str7 = new String("자바");
		System.out.println(str7); //저장된 문자열 자바가 나옴
		System.out.println(str7.toString()); //object말고 스트링으로 되어있음. 주소말고 저장문자열 나옴
		//그럼 주소를 출력할 수 없냐? 네 못함, 헤시코드도 toString이랑 같이 오버라이딩 돼있음
		
		//ex. String a="H", String b="H" ->스트링 많이 쓰니까 기본자료형처럼 쓰도록 만들어놓음**********
		// 실제 a,b에는 주소가 저장됨, a==b하면 주소비교하는 것 ->스트링에서는 이러면 안됨
		// H라는 데이터를 비교하려면 a.equals()로 저장된 문자열 H를 비교해야한다~~~!!
		// 원래 equals는 다른 타입 주소비교, 근데 String에서는 주소말고 저장된 문자열을 비교하도록 오버라이딩(리모델링)해놓은것
		// equals는 Object에서 상속받음, 근데 건물 리모델링해서 바꿔서 쓴다고~~
		
	
		
		
		
		
	}///////////main

}///////////////class
