package stringclass11;


public class StringBufferClass { //StringBuffer라는 클래스가 안보이지만 있어서 Class 붙여준것
	
	/*StringBuffer 클래스:
	 String클래스(immutable)는 String클래스의 메서드를 이용해서 새로운 문자열을 생성하면 원래 문자열은 변하지 않고
	 새롭게 생성된 문자열을 저장하기 위해서 메모리가 새롭게 생성된다.
	 이런 메모리 낭비를 막기위해서 StringBuffer클래스가 사용된다. ->문자열 많이 다루면 버퍼, 빌더 써야한다
	 즉 StringBuffer클래스는 원래값이 변한다(mutable)(메모리가 계속 생기지 않고)
	 */	
	
	private static String reverse(String str) {//반환타입 String "HELLO WORLD"
		
		//자바 배열은 new int[3]-> 메모리개수 3 한번 저장 시 끝, 자바 스크립트는 개수설정 변경 가능
		
/*		//방법1] char[] 2개 사용
		char[] src=str.toCharArray(); //str을 캐릭터형 배열로 방 하나하나에 저장, H->src[0]에 저장, 근데 최종은 스트링으로 반환
		//거꾸로 바꿔서 저장할 배열
		char[] dest=new char[src.length];//dest, 목적지 뜻, str.length해도 됨, dest는 src.length 길이만큼 똑같이 생성됨, HELLO WORLD를 0번방 D부터 역순저장
		//마지막에는 char형 배열-> 스트링 바꿔주면 됨
		for(int i=src.length-1; i>=0; i--) dest[src.length-1-i]=src[i]; //역순으로 넣어주면됨, HELLO면 0번->4번, 1번->3번, 합이 4=src.length-1 이 공통점이구나
		return String.valueOf(dest);	//스트링형 밸류로 바꿔주면 됨
*/		
/*		//방법2] 반복을 2분의1로 줄이고 char[]하나 사용 //메모리 배열 1개쓰고, 반복 절반이라 우수
		char[] src=str.toCharArray(); //51236 -> 65321 재배치 스킬을 씀, HELLO 배열중 그냥 H를 O끝에 넣으면 O 지워지니까 임시방 필요, 0번->4번, 1번->3번, 2번은 냅둠
		// 이런식으로 배열 내에서 바꿔버리면 됨, 메모리개수가 5개면 2번 재배열, 메모리개수가 4개도 2번 재배열하면 끝
		for(int i=0; i<src.length/2; i++) { //메모리개수가 5면 2가 답, 4도 2가 답, 배열길이 2로 나누면 int값, 내림해버리면 됨, i=2되면 그냥 빠져나오면 되니까 i<로 세팅
			char temp=src[i]; //0번방을 temp에 넣음, 사선공식
			src[i]=src[src.length-1-i];
			src[src.length-1-i]=temp;
		}
		return String.valueOf(src);
*/		
		//방법3] char[] 요소를 String에 누적
		char[] src=str.toCharArray();
		String reverse="";
		for(int i=src.length-1; i>=0; i--) reverse+=src[i];
		return reverse;

/*내방법	String str1="";
		for(int i=str.length()-1; i>=0; i--) {
			str1+=str.charAt(i);			
		}
		return str1;
	}
*/			
	}///////////reverse
	
	
	public static void main(String[] args) {

		//[StringBuffer클래스의 주요 메소드] -> String클래스의 메소드는 이미 했고, 공통빼고 다른걸 주로함
		//StringBuffer buf="HELLO"; //[x]문자열은 String형 상수(얘는 너무 많이 써서 기본형처럼 봐줬던것)라서, StringBuffer타입에 직접 대입 불가
		//[StringBuffer를 생성(인스턴스화, 객체화)하면서 문자열 초기화하지않기]
		StringBuffer buf=new StringBuffer();
		System.out.println("buf="+buf); //기본적으로 ""(빈문자열)이 저장돼있다, null하고 틀림!
		//주소가 저장되어있지만 찍히지는 않음, toString을 Object에서 상속 후 오버라이딩 됨, 메모리 주소열을 문자열로 반환하는애
		System.out.println("buf.toString():"+buf.toString()); //String클래스처럼 오버라이딩 됐다!, 이건 위랑 똑같은거!
		//buf만들어지고 주소 0x1234 저장, StringBuffer@0x1234 클래스 생성, 초기화 안해서 빈 문자열이 저장,
		//클래스 안에 toString메소드도 있는데, 데이터를 반환하도록 오버라이딩 됨, 근데 데이터가 빈 문자열이라 빈문자열이 출력되는 것
		System.out.println(" ".length()); //공란 한개, 길이 1 나옴, ""면 0 나옴
		System.out.println("저장된 문자열 길이:"+buf.length());
		System.out.println("기본 버퍼 크기:"+buf.capacity()); //버퍼크기(용량): 16자 담을 수 있음->한글도 16자, 영어도 16자 나옴
		
		String str=""; //빈문자열
		System.out.println("str="+str); //메소드가 String으로 됨, 오버라이딩 된 것, 주소 안나옴, 버퍼와 동일
		System.out.println("str.toString():"+str.toString()); //이건 위랑 똑같은거
		System.out.println("저장된 문자열 길이:"+str.length());
		
		//1]append():문자열을 추가로 저장
		System.out.println("[append()메소드]"); //빈 문자열이 저장되어있음
		buf.append("JAVA"); //컨트롤 누르면 반환은 StringBuffer타입, 오버라이딩도 StringBuffer, 넣을 수 있는 값은 괄호안에 보면 다양
		//빈 문자열에 append메소드로 JAVA 문자열 저장
		System.out.println("[JAVA문자열 저장 후]");
		System.out.println("buf="+buf); //toString이 저장된 문자열 JAVA 그대로 반환
		System.out.println("buf.toString():"+buf.toString()); //JAVA동일
		System.out.println("저장된 문자열 길이:"+buf.length()); //길이 4
		System.out.println("기본 버퍼 크기:"+buf.capacity()); //버퍼 16
		
		buf.append("0123456789").append("가나다라"); //4글자에 10자 추가함, append가 stringbuffer로 반환, 또 .찍을 수 있음 또 4자 추가, 18자? 용량넘음
		System.out.println("[추가문자열 저장 후]");
		System.out.println("buf="+buf);
		System.out.println("buf.toString():"+buf.toString()); //이건 위랑 동일, 문자열로 반환
		System.out.println("저장된 문자열 길이:"+buf.length()); //18
		System.out.println("기본 버퍼 크기:"+buf.capacity()); //18글자수에 16이 추가됨, 총34, 16자 공간이 남음, 이게 공간낭비, 쓰면서 신경쓸 필요는 없음
		
		//2]trimToSize(): 저장된 문자열만큼 버퍼크기를 줄인다
		System.out.println("[trimToSize()메소드]"); 
		buf.trimToSize();
		System.out.println("버퍼 크기:"+buf.capacity()); //34에서 18로 줄어듦
		
		//3]StringBuffer delete(int start, int end): start인덱스부터 end-1 인덱스까지의 문자열을 삭제, 이것도 반환타입이 StringBuffer(맨앞에씀), .찍으면 됨
		System.out.println("[delete()메소드]");
		buf.delete(4, 14); //JAVA0123456789가나다라 -> 여기서 JAVA이후 4부터, 14-1=13까지 날림, 0123456789가 날아감
		System.out.println("삭제 후 buf:"+buf); //JAVA가나다라
		System.out.println("삭제 후 버퍼 크기:"+buf.capacity()); //삭제 후 버퍼크기 18, 0123456789 날렸지만 버퍼크기는 그대로임, trimtosize하면 줄어들겠지
		
		//4]StringBuffer deleteCharAt(int index): index위치의 하나의 문자 삭제
		System.out.println("[deleteCharAt()메소드]");
		System.out.println("한 문자 삭제 후:"+buf.deleteCharAt(3)); //JAVA가나다라 -> JAV가나다라, A날림
		
		//5]StringBuffer insert(int index, String str): index위치에 특정 문자열 삽입
		System.out.println("[insert()메소드]");
		System.out.println("문자열 삽입후:"+buf.insert(4, "JAV")); //toString이 오버라이딩 돼있음, 저장된 문자열 데이터 반환하도록, 그래서 지금 주소 말고 데이터 나오는게 가능
		//4면 나 자리 -> 그 앞에 JAV 저장, 나다라 밀림, JAV가JAV나다라
		System.out.println("문자열 맨 앞에 삽입후:"+buf.insert(0,"123456789")); //123456789JAV가JAV나다라, 맨 앞에 삽입
		
		//6]StringBuffer replace(int start, int end, String str) ->String과 달리 replaceAll은 없음
		// start인덱스로부터 end-1인덱스까지의 문자열을 str로 바꾼다
		System.out.println("[replace()메소드]");
		System.out.println(buf.replace(9, 12, "자바")); // 123456789JAV가JAV나다라 -> 9가 J, 12-1이 V -> JAV가 자바로 됨
		
		//[특정 문자열로 초기화하면서 StringBuffer 객체 생성]
		buf =new StringBuffer("123456789"); //new함, StringBuffer타입 클래스 0x5678새로 생성, 데이터 123465789들어감, buf에는 이전 0x1234에서 0x5678로 주소 바뀜,
		System.out.println("buf="+buf); //123456789
		System.out.println("buf.toString():"+buf.toString()); 
		System.out.println("저장된 문자열 길이:"+buf.length()); //9 
		System.out.println("기본 버퍼 크기:"+buf.capacity()); //기본16에 인자인 문자열의 길이가 plus
		//스트링아규먼트(인자) 클래스 생성하면서 9자 저장, 기본버퍼크기16에 추가됨, 몰라도 됨
		
		//7]StringBuffer reverse(): 문자열을 반대로 배치
		System.out.println(buf.reverse()); //데이터를 거꾸로 배치함 987654321 됨
		
		//String 클래스는 인서트, 딜리트 등 없어서 기존 불변문자를 바꿀 수가 없음, String클래스 -> StringBuffer클래스로 변형 불가, 상속관계가 전혀 없음, Object안에 각각있음, 형변환&대입연산불가
		//[String타입을 StringBuffer타입으로 변환하기]
		str="HELLO WORLD"; //스트링에 이 문자 저장
		//buf= (StringBuffer)str; //cannot cast, 형변환 불가, 상속관계 없음
		//str -> SB 바꾸려면, 스트링버퍼의 생성자를 써야함 StringBuffer(String str):
		//Constructs a string buffer initialized to the contents of the specified string.
		buf=new StringBuffer(str); //StringBuffer의 생성자를 사용해서 String타입을 StringBuffer타입으로 변경하기 ->생성자 써야하니까 new필요
		System.out.println("HELLO WORLD를 거꾸로:"+buf.reverse());//헬로우 월드 거꾸로 스트링에는 없음, 그래서 위에 스트링버퍼로 바꾼 것
		System.out.println("내가 만든 reverse()사용:"+reverse(str)); //스트링버퍼 말고, 스트링 클래스 메소드 배운걸로 메소드 새로 만들어서 하기
		
		
		
		
		
		
		
		
		
		
		
		
		
	}////////main



}
