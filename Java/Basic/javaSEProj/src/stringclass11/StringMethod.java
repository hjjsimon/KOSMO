package stringclass11;

import java.io.CharArrayReader;
import java.util.Scanner;

public class StringMethod {

	//메소드의 인자로 정규표현식 넣어줌

	public static void main(String[] args) {

		//String클래스의 주요메소드 (이거 잘 다루면 프로그래밍 쉬움, 정규표현식 대체가능)
		String str1="Welcome To Java!";
		String str2="안녕 자바!";
		
		//1]int length(): 문자열의 길이반환. -> int로 반환해서 int 앞에 쓴 것
		System.out.println("[length() 메소드]");
		System.out.println("str1의 문자열 길이:"+str1.length()); //static 안붙었으니 인스턴스 변수로 접근, 공백 포함 16자
		System.out.println("str2의 문자열 길이:"+str2.length()); //6자
		
		//2]char charAt(int index): 문자열에서 index에 해당하는 하나의 문자를 반환한다(index는 0부터 시작)
		System.out.println("[charAt() 메소드]");
		System.out.println("str1의 5번째문자:"+str1.charAt(4)); //o나옴, 5번째문자->인덱스로 따지면 4번째
		System.out.println("str1의 5번째문자:"+str2.charAt(4)); //바 나옴
		//각 인덱스 위치에 해당하는 문자들 한번에 출력
		for(int i=0; i<str1.length(); i++) System.out.printf("%d인덱스에 해당하는 문자:%c%n",i,str1.charAt(i));
		
		String jumin="012345-1234567";
		System.out.println(jumin.charAt(7)=='1'?"남자":"여자"); //인덱스에서 인덱스7로 -뒤의 1을 가져와서 남자 확인가능
		//인구증가, 주민번호 앞6자리 7자리로 늘리면 - <-는 1아니니까 100%여자나옴, 위처럼 하드코딩하면 안됨
		//방법1)정적메소드씀, 숫자를 스트링으로 반환, 뒷주민번호의 0번 인덱스를 메소드 2개로 찾아감, 이건하드코딩아님
		int lastJumin=1234567; //숫자를 스트링으로 바꿔야함
		System.out.println(Integer.toString(lastJumin).charAt(0)=='1'?"남자":"여자");
		//방법2)인스턴스씀 lastJumin. 찍으면 인스턴스 안나옴, 오토박싱하거나 밸류오브쓰거나 해야함
		Integer intObj=lastJumin; 
		System.out.println(intObj.toString().charAt(0)=='1'?"남자":"여자");
		//방법3) 13번 인덱스로
		
		//3]int codePointAt(int index): 문자열에서 index에 해당하는 아스키(유니)코드값 반환
		System.out.println("[codePointAt() 메소드]");
		System.out.println("str1의 4번째 문자의 아스키(유니)코드값:"+str1.codePointAt(3)); //인덱스로 따지면3, c면 99
		System.out.println("str2의 4번째 문자의 아스키(유니)코드값:"+str2.codePointAt(3)); //자 51088
		
		//4]int compareTo(String anotherString): 정렬 시 주로사용
		//: 두 문자열을 비교하는데 첫번째 문자부터 순차적으로 비교해나간다
		//첫번째 문자열의 아스키코드값이 크면 양수, 작으면 음수, 같으면 0반환
		//두 문자열의 비교시에는 주로 equals()메소드 사용
		//compareTo()메소드는 정렬할 때 사용(오버라이딩한 메소드안에서)
		System.out.println("[compareTo() 메소드]");
		String str3="ABc";
		String str4="ABC";
		System.out.println(str3.compareTo(str4)); //c가 99 C가 67(A 65) -> 차이값 32 나옴, 앞에 2개는 같아서 0
		System.out.println(str4.compareTo(str3));// 빼기값이라 67-99= -32 나옴
		System.out.println("ABCD".compareTo("ABCD")==0? "문자열이 같다": "문자열이 다르다");
		System.out.println("ABCD".compareTo("A"));//문자열 개수가 틀린 경우, 앞에껀 같은데 뒤에는 비교할게 없음, -> 결과 3나옴
		System.out.println("ABCD".compareTo("a"));//다르니까 빼는데 65-97 첫자리, -> -32나옴
		System.out.println("A".compareTo("ABCD"));//결과-3나옴, 앞에꺼가 한자리라서 1-4= -3
		
		//5]String concat(String str): 두 문자열을 연결할 때 +와 같은 기능
		System.out.println("[concat() 메소드]");
		System.out.println("JAVA".concat(" WORLD").concat(" HI!")); //str 받고 str반환이라 계속 연결 가능, JAVA WORLD HI! -> 연결됨
		System.out.println("JAVA"+" WORLD"+" HI!"); //위 동일
		
		//6]boolean contains(CharSequence s): 문자열에 특정 문자열이 포함되었는지 판단하는 메소드, 인자로 전달된 문자열이 포함 시 true
		System.out.println("[contains() 메소드]");
		System.out.println(str1.contains("To")); //String str1="Welcome To Java!"; -> TO 포함되어있으므로 true나옴
		System.out.println(str1.contains("to"));
		String email="kim@nate.com";
		System.out.println(email.contains("@")?"이메일형식이다":"이메일형식이 아니다"); //@포함시 true, 정규표현식 안쓰고 가능 
		
		//7]static String copyValueOf(char[] data)
		// static String copyValueOf(char[] data, int offset, int count)
		// static String valueOf(char[] data)
		// static String valueOf(char[] data, int offset, int count) // 시작인덱스(offset)부터 몇개(count)를 바꿈
		//: char형 배열에 저장된 문자들을 String형으로 반환함.
		// copyValueOf -> valueOF 랑 같음, 이것만 하면 됨
		// string 클래스의 tochararray 메소드로 string -> char 했었음, 위에 내용이 그 반대 
		System.out.println("[valueOf() 메소드]");
		char[] chArr= {'A','C','A','D','E','M','Y'}; //0번방부터 하나하나저장
		System.out.println(String.copyValueOf(chArr)); // char->string
		System.out.println(String.copyValueOf(chArr,3,4)); //3번인덱스부터 4개만 바꿈
		System.out.println(String.valueOf(chArr));
		System.out.println(String.valueOf(chArr,3,4));
		//위 메소드 사용안하고 char형 배열에 저장된 문자들을 문자열로 변환] -> string에 누적
		String plus="";
		for(int i=0; i<chArr.length;i++) plus+=chArr[i];
		System.out.println(plus);
		
		//8]char[] toCharArray(): 문자열을 char형 배열로 반환
		System.out.println("[toCharArray() 메소드]");
		chArr="가나다라".toCharArray(); //캐릭터 배열로 반환
		for(int i=0; i<chArr.length;i++) System.out.printf("chArr[%d]:%-2c",i,chArr[i]); //배열에 한글자씩 캐릭터 저장
		System.out.println(); //줄바꿈
		
		//9]boolean endsWith(String suffix): 문자열이 특정 문자열로 끝나면 true (suffix가 접미사)
		System.out.println("[endsWith()메소드]");
		System.out.println("www.nate.com".endsWith("com")); //com으로 끝나면 true, net 등등도 확인가능
		System.out.println("www.nate.com".endsWith("co.kr"));
		
		//10]boolean startWith(String prefix): 문자열이 특정 문자열로 시작하면 true
		System.out.println("[startWith()메소드]");
		System.out.println("www.nate.com".startsWith("www"));
		System.out.println("www.nate.com".startsWith("http"));
		System.out.println("www.nate.com".startsWith("nate",4)); //nate 문자열의 시작인덱스 4부터 찾아갈 수도 있음 -> true나옴
		
		//11]static String format(String format, Object... args): 출력 형식을 지정하여 문자열로 반환
		System.out.println("[format()메소드]");
		System.out.printf("국어:%d,영어:%d,수학:%d,평균:%.2f%n",90,90,90,270/3.0); //줄바꿈 없으니까 %n
		System.out.println(String.format("국어:%d,영어:%d,수학:%d,평균:%.2f",90,90,90,270/3.0)); //여긴 %n줄바꿈 빼야함, println이라 두번줄바꿈됨, 반환값을 그대로 받아 println으로 출력
		
		//12]byte[] getBytes(): 문자열을 byte형 배열로 반환해줌, 배열로 반환될 때 아스키코드값으로 변환됨, 얘만 to아니고 get특이함, 한글은 적용X
		System.out.println("[getBytes()메소드]");
		byte[] by="ABCD".getBytes(); //string->byte, 0번방에는 A대신 65저장
		for(int i=0; i<by.length; i++) {
			//아스키코드로 출력
			System.out.println(String.format("by[%d]:%d",i,by[i]));
			//해당문자로 출력
			System.out.println(String.format("by[%d]:%c",i,by[i])); //%c로 출력하면됨, 굳이 형변환할 필요 없음
		}

		//13]int indexOf(String str):문자열에서 인자로 전달된 특정문자열의 시작인덱스를 반환, 특정문자열이 없으면 -1반환
		System.out.println("[indexOf()메소드]");
		System.out.println("JAVA".indexOf("AVA")); // AVA는 1부터 문자열 시작, 1을 반환
		System.out.println("JAVA".indexOf("A")); // A도 1부터 시작, 1반환
		System.out.println("JAVA".indexOf("V")); // V는 2부터 시작
		System.out.println("JAVA".indexOf("aVA")); //없음. -1반환
		//2번 charAt 유래
		jumin="123456-1234567";
		int index=jumin.indexOf("-")+1; // 하이픈 한칸 뒤 인덱스로 확인
		System.out.println(jumin.charAt(index)=='1'?"남자":"여자");
		
		//14]int lastIndexOf(String str): 문자열에서 인자로 전달된 특정문자열의 시작인덱스를 반환하는데 뒤에서부터 찾음, 특정문자열이 없으면 -1반환
		System.out.println("[lastindexOf()메소드]");
		System.out.println("JAVA".indexOf("AVA")); // 뒤부터 찾는데 가장 앞 인덱스가 1이라 1나옴
		System.out.println("JAVA".indexOf("A")); // 뒤에서부터 찾으면 인덱스 3나옴
		System.out.println("JAVA".indexOf("V")); // 2나옴
		System.out.println("JAVA".indexOf("aVA")); // -1나옴
		System.out.println("my.file.txt".lastIndexOf(".")); //7나옴, .뒤에는 파일확장자라 파일 이름에.붙이면 안됨, 추출하면 file.txt가 확장자가 돼버림
		
		//15] String replace(char oldChar, char newChar) //한문자 바꿈, 첫번째 기존문자를 새로운 문자로 바꾸는 것 
		//    String replace(CharSequence target, CharSequence replacement) //여러문자 바꿈
		System.out.println("[replace()/replaceAll()메소드]");
		String str5 = str1.replace('T', 'G');
		System.out.println(str5); //웰컴고자바 됨
		System.out.println(str1); //웰컴투자바 그대로, String에 저장된 데이터는 절대불변, 바뀌지않음(Immutable)*******
		System.out.println(str1.replace("Java", "Korea")); //이렇게 출력해도 됨
		System.out.println(str1.replace("Hello", "안녕"));//Hello 없으면 원본 그대로 반환
		System.out.println(str1.replace("o", "a")); //문자열 o를 a로 다 바꿈 -> replaceAll은 그러면 뭐임?
		//replaceAll은 replace와 다르게 인자로 일반문자열은 물론 정규표현식 형태의 문자열도 가능
		System.out.println(str1.replaceAll("o", "a"));
		//정규표현식 써보자
		String password="Ab12Cd34";
		//반드시 첫번째 인자가 비밀번호 문자열과 일치해야하고 별표도 비밀번호 개수와 동일하게 지정해야한다
		System.out.println(password.replaceAll("Ab12Cd34X", "******"));//이러면 하드코딩, 안맞으면 그대로 반환됨, 구림, 아래 정규표현식이 좋은 방법
		System.out.println("비밀번호:"+password.replaceAll("[0-9A-Za-z]", "*")); //숫자와 영문자무관, 맞는 부분을 *별표로 바꿈, ex. 비밀번호 칠 때 
		
		//16]String[] split(String regex):
		//문자열을 특정 구분자(혹은 정규표현식에 맞는 패턴)로 분리해서(쪼개서) String형 배열로 반환. -> String을 String으로 쪼갤뿐
		//해당 구분자가 없는 경우 배열크기가 1인(인덱스가 0) String형 배열(그대로 반환)이 생성되면서 모든 문자열이 0번방에 저장됨.
		//없는 구분자로 split시 배열의 크기가 1인 메모리가 생성되며 전체 문자열이 인덱스가 0인 방에 저장됨.
		System.out.println("[split()메소드]");
		String tel="010-1234-5678"; //구분자는 대시 - 하이픈, 쪼개면 010 1234 5678 3개의 문자열이 나옴, 각 0번방 1번방 2번방에 각각 저장
		String[] strArr=tel.split("-"); //구분자 String형 배열로 반환한다고 갖다대면 나옴, 그걸 보고 왼쪽에 맞춰줌
		for(int i=0; i<strArr.length; i++) System.out.println(String.format("strArr[%d]:%s",i,strArr[i]));
		//한번더
		String today="2023.03.21"; //today가 구분자니까 today. 으로 찾으면됨
		//strArr=today.split(".");
		strArr=today.split("\\."); //정규표현식 중 임의의 한 문자가 아님을 \\로 의미
		System.out.println("strArr.length:"+strArr.length); // 길이가 0으로 나옴?? -> .은 위에서 임의의 한 문자로 정의하기 때문!, 2023~ 다 임의의 한 문자, 얘네로 다 쪼개질 못함
		for(int i=0; i<strArr.length; i++) System.out.println(String.format("strArr[%d]:%s",i,strArr[i]));
		//정규표현식, 구분자가 없는 문자열에 쓰면 좋음, split가능하나 코딩량이 많음, 1로 한번쪼개고 뒤에 CDE2F3XYZ를 또 2개로 쪼개고.. 계속 반복
		String regex="AB1CDE2F3XYZ";
		String[] reArr=regex.split("[0-9]"); //인자로 넣어준 정규표현식에 일치하는 패턴으로 분리한다, 숫자 1개의미, 볼때마다 쪼갬
		for(int i=0; i<strArr.length; i++) System.out.println(String.format("reArr[%d]:%s",i,reArr[i]));
		regex="AB1CDE2F34XYZ";
		reArr=regex.split("[0-9]+"); //+또는{1.0}로 한문자 이상 지정-> 34 숫자도 통으로 구분자로 쓰기 가능
		for(int i=0; i<strArr.length; i++) System.out.println(String.format("reArr[%d]:%s",i,reArr[i]));
/*		String[] s=new String[4];
		s[0]=regex.split("1")[0];//구분자1로 쪼갠 배열의 0번방
		s[1]=regex.split("1")[1].split("2")[0];
		System.out.println(s[0]);
		System.out.println(s[1]);
*/                                      //위에 말한 하나하나 쪼개는 코드
		
		//17]String substring(int beginIndex): 문자열에서 시작인덱스부터 문자열 끝까지 추출하는 메소드
		System.out.println("[substring(시작인덱스)메소드]");
		System.out.println("Welcome".substring(3)); //Welcome의 3번인덱스부터 come추출
		String filename="my.file.homework.ppt"; //끝의 .을 찾고 +1해서 끝까지 추출하면 확장자를 알아낼 수 있음
		int beginIndex=filename.lastIndexOf(".")+1;
		System.out.println("확장자:"+filename.substring(beginIndex));
		
		//18]String substring(int beginIndex, int endIndex): 시작인덱스부터 끝인덱스 -1까지 문자열추출, 끝인덱스는 포함안됨
		System.out.println("[substring(시작인덱스,끝인덱스)메소드]");
		System.out.println("kim@nate.com".substring(4,8)); //4부터 (n) 부터, 8-1까지 (e)까지, nate추출
		
		//문제]@와 .사이의 문자열을 추출하여 출력하여라(for문 사용)
		String[] emails= {"a@b.c","kim@nate.com","choi@cyworld.com","park@naver.com"};
	
		
		for(int i=0; i<emails.length; i++) {
			//방법1
			//beginIndex=emails[i].indexOf("@")+1;//@위치 확인
			//int endIndex=emails[i].indexOf(".");//.위치 확인      
			//System.out.println(emails[i].substring(beginIndex,endIndex));		
			
			//방법2, 귀찮아서 변수에 안담음 -> 0번방 필요없음, @랑 .로 스플릿 후 1번방이 필요함
//			System.out.println(emails[i].split("@")[1].split("\\.")[0]);
		
			//방법3, 정규표현식(과제)
			
			String[] print=emails[i].split("[@\\.]"); System.out.println(print[1]);
		
		
		}//////for
		
		

		//19]String toLowerCase(): 대문자를 소문자로 변환
		//20]String toUpperCase(): 소문자를 대문자로 변환
		System.out.println("[toLowerCase()/toUpperCase()메소드]");
		System.out.println("대문자로 변경:"+str1.toUpperCase()); //Welcome To Java 모두 대문자로 변경
		System.out.println("소문자로 변경:"+str1.toLowerCase()); //모두 소문자로 입력
		//대소문자 구분없이 아이디 체크하기]
		//회원의 아이디가 소문자 "park"이라고 가정하자.
		Scanner sc=new Scanner(System.in);
		String nick=sc.nextLine();
		//1]equalsIgnoreCase(anotherString) 사용
		System.out.println("paRK".equalsIgnoreCase(nick)?"회원":"비회원 혹은 아이디 잘못"); //equals는 대소문자 정확필요
		//2]toLowerCase()나 toUpperCase()사용
		System.out.println("PARK".equals(nick.toUpperCase())?"회원":"비회원 혹은 아이디 잘못"); //UpperCase쓰면 대문자 되니까, 대문자와 equals로 정확히 비교
		System.out.println("park".equals(nick.toLowerCase())?"회원":"비회원 혹은 아이디 잘못");
		
		//21]String trim(): 양쪽 끝의 공백을 제거하는 메소드, 항상 문자열 비교시에는 트림 후 깔끔하게 비교해라(중간공백은 제거불가)
		System.out.println("트림전: X"+"   J A   V  A   "+"Y"); //트림전: X   J A   V  A   Y 출력
		System.out.println("트림후: X"+"   J A   V  A   ".trim()+"Y"); //트림후: XJ A   V  AY -> J A V A 양끝의 공백이 날아가고 X,Y랑 붙음
		
		//22]boolean matches(String regex):문자열이 정규표현식에 패턴이 일치하면 true, 값추출은 아님
		email="hwanyhee@naver.com";
		System.out.println(email.matches("[a-z]+@[a-z]+\\.[a-z]+")); //숫자, 대문자는 지금 안하고 걍 대충
		//WrapperClass03에 matches()메소드로 구현추가 -> pattern, matcher 객체생성 안해도 돼서 편함
		
		
		//자바의 문자열 다루는 클래스가 String, StringBuffer, StringBuilder 3개 존재
		//String -> 원본데이터가 안바뀜, immutable
		//StringBuffer, StringBuilder 2개는 원본데이터가 바뀜, mutable -> String타입의 새로운 메모리가 생성, 문자열 저장됨
		//ex. String s="a"; s+="b" -> String 참조형이니 s에 주소 저장, String@0x1234 클래스 안에 a데이터 저장
		//이제 새로운 클래스 String@0x5678클래스 안에 a에 b추가, ab저장, 그리고 s의 주소는 좌변시행으로 0x5678로 저장, 참조 바뀜
		//원본은 변하지 않으니까 a가 따로 저장되는 것, 메모리 낭비임, 가비지콜렉터가 가져가기전까지는 메모리 낭비
		//StringBuffer(thread-safe라고함, 동기화됨)와 StringBuilder(동기화x)는 동기화가 차이남
		//sequence of characters -> 문자열 의미
		//StringBuilder 클래스도 문자열 다루니까 메소드 비슷, 근데 append -> 원본데이터에 추가가됨, delete, insert 이런 것들이 추가 존재, mutable, 원본이 바뀌니까
		//ex. str에 0x1234 저장, String@0x1234에 welcome저장, str.substring(3)하면 반환하려고 string@0x5678타입의 메모리 새로 생성되고 메모리에는 (3)부터 come이 저장됨
		//ex. String s="가"; s에 주소 0x1234저장, String클래스 안에 수많은 메소드있고, 데이터 가 저장, s+="나" 하면 String@0x5678에 데이터 가나 저장, 좌변시행, s에 주소 0x5678저장
		
		
		
		
		
		
		
		
		
	}/////////main
}/////////////class
