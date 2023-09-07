package wrapperclass10;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WrapperClass03 {

	//문 인자로 전달받은 value가 숫자형식이면 true반환, 아니면 false반환하는 메소드를 정의 -> 문자열이 숫자인지 아닌지 판단하는 메소드는 자바에 없어서 직접 만들어서 써야함
	//ex. "20"->true, "20A1"->false
	
	private static boolean isNumber(String value) {  //char[]였는데 bollean으로 바꿈, string->value로
		
		
		for (int i=0; i<value.length(); i++) {
			
			//방법1] isDigit와 codePointAt 사용
			//if(!Character.isDigit(Character.codePointAt(value, i))) return false;
			
			//방법2] codePointAt()만 사용
			int codeValue=Character.codePointAt(value, i);
			if(!(codeValue>='0'&&codeValue<='9')) return false; //codeValue 대신 길게 써도 되는데, 지저분하니까 codeValue 변수에 넣은 것
		}	
		return true ;//에러안나게 트루
	}
	
	
	
	public static void main(String[] args) {

		//JDK 1.4 이전 버전의 코딩형태
		char ch='A';
		Character chObj=new Character(ch); //이제 안씀 바로 쓰면 됨, deprecate, A라는 문자가 저장된 래퍼클래스
		char chResult=(char)(chObj.charValue()+1); //charValue니까 char로 반환, char+int=int -> int에 담으면 숫자가 나오니까 char로 형변환해줌
		System.out.println("chResult:"+chResult);
		
		//오토박싱으로 편하게 하기
		chObj='C';   //chObj -> 캐릭터형// 오토박싱 char->Character형으로
		chResult=chObj; //chObj가 풀림// 오토언박싱 Character->char형으로
		//래퍼클래스는 오버라이딩 돼어있음
		System.out.println("chResult:"+chResult);
		//Character클래스의 주요 메소드] 
		//1]static int codePointAt(CharSequence seq,int index)-> CharSequence seq 는 스트링으로 이해
		//:문자열에서 index에 해당하는 위치의 한 문자의 아스키코드(유니코드)값을 반환한다. (index는 0부터 시작) ex. ABCD면 index[0]은 A, 아스키코드 65
		System.out.println("[codePointAt메소드]");
		System.out.println(Character.codePointAt("ABCD", 2));//스태틱 붙었으니 클래스명으로 접근
		
		//2]static boolean isDigit(char ch)
		//static boolean isDigit(int codePoint) ----------> to 계열은 바꿈, is계열은 불린타입(t/f) 반환, isDigit? -> 숫자냐?
		//어느 한 문자가 숫자인지 아닌지 판단하는 메소드
		
		System.out.println("[isDigit메소드]");
		System.out.println(Character.isDigit('A')); //이거 디지털(숫자)? x -> false
		System.out.println(Character.isDigit(65)); //위와 동일
		System.out.println(Character.isDigit('2')?"숫자다":"숫자가 아니다"); // 0=48
		System.out.println(Character.isDigit(50)?"숫자다":"숫자가 아니다");
		System.out.println(Character.isDigit('가')?"숫자다":"숫자가 아니다");
		System.out.println(Character.isDigit(44032)?"숫자다":"숫자가 아니다");
		
		
		System.out.println("[내가 만든 isNumber()메소드]"); //문제풀기용 메소드 하나 알려줌
		System.out.println("HELLO".length()); //문자열 스트링에 .찍으면 스트링클래스의 모든 인스턴스메소드 나옴 -> 문자열의 길이를 반환하는 메소드
		System.out.println(isNumber("1004")); //숫자니까 true나와야함
		System.out.println(isNumber("100사"));//숫자아니니까 false 나와야함
		System.out.println(isNumber("10A4321"));//숫자아니니까 false
		
	/*	Scanner sc = new Scanner(System.in);
		System.out.println("나이를 입력하세요?"); 
		String value=sc.nextLine();
		if(isNumber(value)) { //isNumber 호출하면 t나 f로 나옴
			System.out.println("당신의 10년후 나이:"+(Integer.parseInt(value)+10)); //숫자일때만 parseInt로 int 바꿔줄 생각
		}
		else System.out.println("나이는 숫자만 입력해주세요");
	*/
		
		//3]static boolean isLetter(char ch)
		//:문자인지 아닌지(언어 ex. 한글,한문,영어,일본어등에 해당하는 것만) 판단하는 메소드 -> letter가 아니면 false로 본
		
		System.out.println("[isLetter메소드]");
		System.out.println(Character.isLetter('가')); //char 넣어야함, 가 한글이니까 true나옴
		System.out.println(Character.isLetter('A'));
		System.out.println(Character.isLetter('家'));
		System.out.println(Character.isLetter('@')); //언어x, false
		System.out.println(Character.isLetter('5')); //언어x, false
		
		//4]static boolean isWhitespace(char ch)
		//static boolean isWhitespace(int codePoint)
		//:공백(white space)문자인지 판단하는 메소드		
		System.out.println("[isWhitespace메소드]");
		System.out.println(Character.isWhitespace('가'));
		System.out.println(Character.isWhitespace(' '));
		System.out.println((int)' '); //공백의 아스키코드값 32
		System.out.println(Character.isWhitespace(32)); //32도 true나옴, 아스키코드 숫자로~
		String whiteString=" H E L LO  ";
		int whiteCount=0; //사용자가 이름 입력시 공백 넣으면 쓰는 코드
		for(int i=0; i<whiteString.length(); i++) {
			if(Character.isWhitespace(Character.codePointAt(whiteString, i))) 
				whiteCount++;
		}
		System.out.println("총 공백 수:"+whiteCount);
		
		//5]대소문자를 판단하는 메소드(영문자에만 적용)
		//static boolean isLowerCase(char ch) -> 소문자냐? 맞으면 true
		//static boolean isUpperCase(char ch) 
		//알파벳이 아닌 문자에 적용시 모두 false반환.
		System.out.println("[isLowerCase/isUpperCase메소드]"); 
		System.out.println(Character.isUpperCase('A')); //대문자? t
		System.out.println(Character.isUpperCase('a')); //대문자? f
		System.out.println(Character.isLowerCase('A')); //소문자? f
		System.out.println(Character.isLowerCase('a')); //소문자? t
		System.out.println(Character.isLowerCase('가')); //한글, 한자는 대소문자 없어서 무조건 false, 대문자도, 소문자도 아님
		System.out.println(Character.isLowerCase('3')); //숫자도 false
		
		//문자열을 배열로 메모리를 만들어서 각 방에 넣어주는 메소드-> 각 방은 당연히 char형, 배열도 char형
		System.out.println("[String클래스의 toCharArray()]");
		String value="KOSMO";
		char[] chArray=value.toCharArray(); //캐릭터형 배열에 담아줌
		System.out.println(chArray[0]); //K저장, 0번방
		
		//문제] 아이디를 입력받는데 영문자 소문자와 숫자 혹은 영문자 소문자로만 입력받아야한다
		// 대문자가 포함 시 "잘못입력했어요"라고 출력하고, 제대로된 아이디를 입력받을때까지 계속 입력받도록하여라
		// 단, 숫자로 시작해서도 안된다, 즉 숫자로 시작한 아이디 입력시에도 "잘못입력했어요"출력
		// hint] 아이디 입력시 Scanner의 nextLine 판단
		
		
	/*	내꺼
		while(true) {
			
			Scanner sc = new Scanner(System.in);
			System.out.println("아이디를 입력하세요?"); 
			String nickName=sc.nextLine();
			char[] chNickName=nickName.toCharArray();
			
			for(int i=0; i<nickName.length(); i++) {
				
				if (!(Character.isLowerCase(chNickName[i]))) break;
				if (chNickName[0]>='0'&&chNickName[0]<='9') break;
			}
			System.out.println("잘못입력했어요");
			continue;
			
		}////////while
	*/	
		
		Scanner sc = new Scanner(System.in);
		System.out.println("아이디를 입력하세요?");
		
		////방법2]패턴객체 사용(정규표현식), 정규표현식 패턴체크시-패턴객체 생성 사용, matcher객체도 생성
//		Pattern pattern = Pattern.compile("[a-z]+[0-9]*[a-z]"); //+[0-9a-z]* -> 로 하면 영문 숫자 영문 숫자도 맞게됨
		//a~z 1개이상, 중간에 숫자는 포함가능, 소문자도
		
		
		while(true) {
			
	/*	 //여기서부터 반복
	 * 	 //방법1]패턴객체(정규표현식) 및 String클래스의 matches(정규표현식) 미사용
		String nickName=sc.nextLine(); //여기가 다시 입력받는 곳, 위의 Scanner는 그냥 배열
		char[] chNickName=nickName.toCharArray();
		
		//옳은 아이디인지 아닌지 판단용 변수
		boolean isCorrect=true;
		
		for(int i=0; i<nickName.length(); i++) { //chNick.length 해도 됨
			
			//숫자로 시작하거나 소문자가 아니고 숫자가 아닌경우(대문자,한글,특수문자인 경우)
			if(Character.isDigit(chNickName[0]) || //숫자로 시작하면 앞에서 걸림 
					!Character.isLowerCase(chNickName[i])&& //or 대문자,한글,특수문자,숫자(모두 false)
					!Character.isDigit(chNickName[i]))	//중간 숫자는 상관없음을 걸러줌
				{System.out.println("잘못입력했어요");
				isCorrect=false;
				break;}///if문
		}///for문
		if(isCorrect) {
			System.out.println("당신의 아이디는 "+nickName);
		break;
		}*/
			String nickName=sc.nextLine();
			//방법2]패턴객체 사용
//패턴객체사용			Matcher matcher=pattern.matcher(nickName);
//			if(matcher.matches()) {
//				System.out.println("당신의 아이디는 "+nickName);
//				break;
//			}
			
			//방법3]String클래스의 matches(정규표현식)메소드 사용             //matches()메소드 하나 써서, Pattern 객체, Matcher객체 생성을 안해도 됨
			if(nickName.matches("[a-z]+[0-9a-z]*")) {
				System.out.println("당신의 아이디는 "+nickName);
				break;
			}
			System.out.println("잘못 입력했어요");
		}//////while
		
		
		//6]대문자를 소문자로, 소문자를 대문자로
		//static char toUpperCase(char ch) ->대문자는 그대로 반환
		//static int toUpperCase(int code)
		//static char toLowerCase(char ch)
		//static int toLowerCase(int code)
		System.out.println("[toLowerCase/toUpperCase메소드]");
		System.out.println(Character.toUpperCase('A'));
		System.out.println(Character.toUpperCase('a'));
		System.out.println(Character.toLowerCase('A'));
		System.out.println(Character.toLowerCase('a')); 
		// '가',숫자 등 넣으면 소문자될게 없으니까 그대로 반환, 위에서 false되던거랑 동일
		
		//문] 사용자로부터 문자열(영문자)를 입력받아서 대문자는 소문자로, 소문자는 대문자로 변경하여 출력 ex.HelLo ->hELlO
		
//		Scanner sc=new Scanner(System.in);
		System.out.println("영문자를 입력하세요?");
		String alphabet=sc.nextLine();
		char[] chAlphabet=alphabet.toCharArray();
		
	/*8	for(int i=0; i<alphabet.length(); i++) { //chAlphabet.length도 됨, alphabet.length는 스트링 메소드
			if(Character.isLowerCase(chAlphabet[i])) Character.toUpperCase(chAlphabet[i]);
			if(Character.isUpperCase(chAlphabet[i])) Character.toLowerCase(chAlphabet[i]);
			
		
		System.out.println(alphabet);
	*/
		
		//3]방법
		//String alphaString=""; //문자 하나하나를 스트링에 누적하는 것-> 문자열이 됨
		
		for(int i=0; i<alphabet.length(); i++) { //chAlphabet.length도 됨, alphabet.length는 스트링 메소드
			if(Character.isUpperCase(chAlphabet[i])) {
				//1]방법-직접 출력
				//System.out.println(Character.toLowerCase(chAlphabet[i]));
				
				//2]방법-소문자로 변경해서 i방에 다시 저장(원본이 변함, 비추천)
				chAlphabet[i]=Character.toLowerCase(chAlphabet[i]);
				
				//3]방법-문자들을 하나씩 String형 변수에 누적
				//alphaString+=Character.toLowerCase(chAlphabet[i]);
			}
			else {
				//1]방법-직접 출력
				//System.out.println(Character.toUpperCase(chAlphabet[i]));
				
				//2]방법
				chAlphabet[i]=Character.toUpperCase(chAlphabet[i]);
				
				//3]방법
				//alphaString+=Character.toUpperCase(chAlphabet[i]);
			}
			//2]방법->따로 출력해야함 
			System.out.print(chAlphabet[i]);  //print로 줄바꿈 안함
		
		}////////for
		
		//3]방법
		//System.out.println(alphaString);
		
		//문제] 위의 최종 변환된 문자열을 거꾸로 출력하여라 ex.Hello->hELLO->OLLEh
		
		System.out.println("\r\n[거꾸로 출력]"); //엔터쳐주고
		
		for(int i=chAlphabet.length-1; i>=0; i--) {//끝번방부터 출력하면 됨, 0이면 종결, 방번호 하나씩 줄이면 됨 
			System.out.print(chAlphabet[i]); //줄바꿈 없이 print로
		}
		System.out.println();
		
		//모든 Wrapper클래스는 기본 자료형을 String형(문자열)으로 변환해줄 수 있는 아래와 같은 메소드를 공통으로 갖고있음
		//String toString()
		//static String toString(기본자료형타입)
		short s=200; //200을 "200" 문자열로 바꾸고싶음
		//방법1]static String toString(기본자료형타입) 사용
		System.out.println(Short.toString(s)+300);//정적메소드 쓰기,Short.toString(s), 말그대로 쇼트가 스트링됨, "200"으로 바꿔줌, +300하면 200300됨
		//방법2]String toString() 사용
		Short shObj=s;//오토박싱으로 래퍼클래스 생성
		System.out.println(shObj.toString()+300);
		//방법3]static 래퍼클래스 valueOf(기본자료형) 사용 -> ex. int 넣으면 Integer가 반환, 참조형이 됨
		shObj=Short.valueOf(s);//valueOf사용해서 래퍼클래스 생성
		System.out.println(shObj.toString()+300);
		
		ch='가'; //'가'문자를 "가"문자열로 만들고싶음
		//방법1]static String toString(기본자료형타입) 사용
		System.out.println(Character.toString(ch)); //char를 string으로 반환, "가"
		//방법2]String toString() 사용
		chObj=ch; //오토박싱
		System.out.println(chObj.toString()); //"가"
	
		boolean b=true;//true->"true"하고싶음
		System.out.println(Boolean.toString(b));
		System.out.println(Boolean.valueOf(b).toString()); //스트링으로 한번더!
		
		/* 모든 Wrapper클래스에는 valueOf()계열 메소드를 가지고 있다(기본자료형을 참조형으로 변환해주는 메소드)
		 
		 1)static Wrapper클래스 valueOf(기본형): 기본형을 참조형으로만들 때 씀, ex.boolean->Boolean(오토박싱지원 의미없음)  
		 
		 boolean b=true;
		 Boolean.valueOf(b); ->Boolean 타입을 반환해줌
		 Boolean boolObj=Boolean.valueOf(b); ->밸류오브로 기본형->참조형
		 Boolean boolObj=b; ->오토박싱으로 기본형->참조형
		 
		 2)static Wrapper클래스 valueOf(String): 숫자형식의 문자열을 Wrapper클래스로(단, 래퍼클래스.parse계열 많이 사용 ex.Integer.parseint)
		
		 Float fObj=Float.valueOf("3.14") ->문자열 "3.14"를 숫자 3.14로 바꿈(래퍼클래스)
		 float f=Float.parseFloat("3.14"); ->기본자료형 f에 숫자 float 3.14로 바꿔줌
		
		-> 위의 2개 기본형, String을 참조형으로 바꾸는걸 많이 씀
		
		*/
		
		
		
		
	}//////////main

	

}//////////////class
