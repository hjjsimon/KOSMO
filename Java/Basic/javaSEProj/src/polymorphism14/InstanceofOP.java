package polymorphism14;

/*
[instanceof 연산자]
- 두 클래스간의 형변환이 가능한지 판단하는 연산자
- 해당 인스턴스 변수가 어떤 타입의 변수인지 판단하는 연산자 ---> 위랑 동의어
- 두 클래스간의 상속관계가 있어야 한다. ---> 상속관계 없으면 빨간줄 에러
    즉 is a 관계가 성립해야 한다. (주어가 자식, 보어가 부모)
- 해당 인스턴스변수가  해당 타입이면  true ,아니면 false 결과값 나옴

형식]
   인스턴스변수 instanceof 클래스타입 ---> 변수에는 주소 어떤 타입의 메모리를 가리키는 저장, 가리키는게 클래스타입의 메모리냐고 묻는것 
	--> 쉽게 변수가 클래스타입이냐?! **********
*/

class Super{}
class Normal extends Super{}


public class InstanceofOP {// +, - 같은 기호뿐 아니라 new 같은 것도 연산자! 그냥 컴퓨터에 연산을 시키면 연산자임

	public static void main(String[] args) {

		//1]두 클래스간 상속관계가 존재하지 않을때 -> ex. String과 StringBuffer
		String str=new String("JAVA");
		StringBuffer buf=new StringBuffer("JAVA"); //둘은 상속관계없음
		//상속관계 미존재시 대입연산 및 형변환 불가
		//str=buf; //클래스 달라 Type mismatch 
		//str=(String)buf; //형변환 안됨, 두 클래스간 상속관계 없어서
		//buf=str;//[x]
		//buf=(StringBuffer)str;[x]
		
		//상속관계가 없을 때 instanceof 연산자 사용시 컴파일 에러
		//System.out.println(str instanceof StringBuffer); //[x]str이 가리키는 메모리는 String타입, StringBuffer랑 상속관계 없어서 에러
		//System.out.println(buf instanceof String); //[x]
		System.out.println(str instanceof String?"str은 String타입이다":"str은 String타입이 아니다"); // String 맞음, true 나옴
		System.out.println(str instanceof Object?"str은 Object타입이다":"str은 Object타입이 아니다"); //Object 넣어도 됨, 모든 부모니까
		//str -> String@0x1234인 String메모리 가리킴****
		//String@0x1234안에 Object 작은방 상속이 있음, str은 String@0x1234가리킴, String타입 맞음
		//근데 Object타입도 그대로 포함하므로, 그걸 가리켜도 true -> Object타입 형태 있으니까, Object냐고 물어도 맞음
		//부모님 유전자도 있으니까~ 그냥 허씨냐고 물어보는것
		System.out.println(buf instanceof StringBuffer?"buf는 StringBuffer타입이다":"buf는 StringBuffer타입이 아니다");
		
		//2]두 클래스간 상속관계가 존재 할때
			//instanceof연산자로 먼저 판단하지 않고 형변환시] -> 오류발생가능
			/*
			 * 상속 관계는 존재하나 형변환이 불가한 경우********
			 * 컴파일 에러는 발생하지 않으나 실행시 에러 발생
			 * -ClassCastException
			 *
			 * 실행시 에러를 피하기위해 반드시 instanceof로 판단후 형변환! -> 코드로는 빨간줄이 안간대, 확신용
			 */
		Object obj=new Object();//Object는 모든 클래스와 상속관계 있음
		//String strObj=obj; //[x]부모가 더 넓음, 작은 그릇에 담을 수 없음 ->Type mismatch 기본자료형부터 나던 에러
		//String strObj=(String)obj; //[x]스트링클래스는 오브젝트로 캐스트(형변환)안된다고 런타임에러뜸, class java.lang.Object cannot be cast to class java.lang.String
		//Student s=(Student)p2; <-HeteroGenous 클래스에서는 됐는데 지금은 왜 안되는지!
		//그림설명
		//obj에 Object@0x1234주소 넣음
		//String타입 strObj으로 Object@0x1234를 가리키게하는게 목표
		//근데 Object@0x1234안에 String타입의 메모리구조(작은방)이 있으면 되는데 없음 
		//Object가 String을 상속받은게 아니니까!
		
//		obj=new String("aaaa"); //이걸 넣고 
//		String strObj=(String)obj; //obj=new String("aaaa"); 이걸 넣고 시행시 에러안남
		//이제 String@0x5678 메모리 힙에 만들고, 안에 Object는 당연히 상속
		//obj에는 0x5678주소 들어가면서 위에 Object@0x1234는 연결 끊김
		//obj는 변수 Object타입 -> 이제 String@0x5678 안의 상속받은 Object상속(작은방)을 가리킴
		//그리고 String strObj=(String)obj; 실행시 obj가 Object상속(작은방)을 가리키다가 String(큰방)을 가리킴(형변환했으니까)
		//그러면 String strObj가 String(큰방)을 가리킬 수 있음
		
		//Person p2=new Student(); -->부모로 자식을 가리킴
		//Student s=(Student)p2;
		//이전에는 p2로 스튜던트 타입 메모리를 가리킴, 안에 상속받은 Person있음
		//Object obj=new Object(); -->부모로 부모를 가리킴*************이게 차이점
		//String strObj=(String)obj;
		//지금은 Object 안에 상속받은 String이 없음
		
		//그림설명
		//obj안에 0x1234, Object@0x1234생기고 obj가 가리킴
		//strObj안에 주소 넣어야하는데~ obj 
		//obj로 String을 참조하게 형변환 하는건데 Object안에 String상속받은게 없어서 찾을 수 없어 에러!**************
		
		System.out.println(obj instanceof String); //[false]안에 스트링 없어서 false나옴, 빨간줄 안가고 런타임에러라 이건 해주는게 좋음
		if(obj instanceof String) {//false라 안에 코드로 안감
			String strObj=(String)obj;
			System.out.println("형변환이 일어났어요:1");
		}
		//이렇게 체크해주는게 코드 안정성에 좋다~
		
		obj=new String("JAVA"); //이게 obj가 Object에서 String을 가리키도록 한것!!! (obj를 스트링으로 형변환)
		//위 코드 실행시 String타입0x5678 메모리 생성, 안에 어딘가 데이터 JAVA들어감, Object(작은방)는 당연히 상속받음 
		//그리고 obj에는 0x5678저장됨(저장된 주소는 스트링타입), String0x5678 가리켜야하는데 Object타입 obj니까 String0x5678안의 Object(작은방)을 가리킴*********
		System.out.println(obj instanceof String);//[true] 스트링으로 형변환 가능한지, 스트링이냐? 하고 물어보는게 좋음
		if(obj instanceof String) {
			String strObj=(String)obj;//[o] 이제 된대!
			System.out.println("형변환이 일어났어요:2");
		}
		
		Super s=new Super(); //Normal이 자식, Super가 부모
		System.out.println(s instanceof Super); //위에 new옆 Super타입메모리만들었으니까 당연히 true
		System.out.println(s instanceof Object); //Object 상속받은 구조 당연히 Super 메모리 안에 있으니까 true
		System.out.println(s instanceof Normal); //부모안에 자식구조 없으니까 false
		//Normal n=s; //부모가 크니까 에러남
		//Normal n=(Normal)s; //[x]타입은 맞으니까 빨간줄은x 근데 에러
		s=new Normal(); //Normal을 넣어서 자식타입 메모리를 만들어줌***********기존 연결 Super끊기고 Normal타입 메모리의 주소를 넣음, Normal 가리키면 안에 Super는 당연히 있음****
		System.out.println(s instanceof Super);
		System.out.println(s instanceof Object);
		System.out.println(s instanceof Normal);
		Normal n=(Normal)s;//[o]이제 s를 Normal로 바꿀 수 있음
		
		
		
	}////main

}////class
