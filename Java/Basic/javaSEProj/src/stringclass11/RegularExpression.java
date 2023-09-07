package stringclass11;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {

/*
[정규표현식] 

ex.이메일 입력, a@b.c 이런 패턴이 맞는지 확인하는게 정규표현식, 패턴에서 특정 데이터를 추출할 때도 씀

어떠한 문자열값이 특정한 패턴을 갖는지를 검사하기위한 표현식으로******
데이터를 검사하기 위해 이용된다.또한 특정 데이타들을 추출할때도 사용한다******
예를들어 숫자로 된 값만을 취하여야 할때라든지,
이메일 주소가 형식에 맞는지등을
검사하는데 정규표현식이 이용됨
JDK1.4부터 지원

[패턴을 표현하는 문자] ->이런 식을 써서 정규표현식 만듦, 잘 까먹음

. : 임의의 한글자를 의미 -> a()b -> 한 글자가 와야함, aAb 이러면 맞음, a4b 맞음
예) a.b(aab,acb,afb........) 

* : * 바로 앞의 문자가 없거나 한 개 이상이 있을 경우 -> a>=0 의미, b됨, a없으니까 맞음, a12b는 안됨
예) a*b (b,ab,aab,aaab....)

+ : +바로 앞의 문자가 최소 한개 이상일때 -> a>=1의미, 한개는 돼야함 그냥 b안됨
예) a+b (ab,aab,aaab...)

? : ?바로 앞의 문자가 없거나 한 개 존재하는 경우-> 0또는 1, aab 안됨
예) a?b (b,ab)

^ : ^ 뒤에 문자열과 같은 문자열로 시작하는 경우 즉 문자열의 시작을 의미 -> 문자열의 시작, 
    [ ]안에서 ^ 는 [ ]안의 문자를 제외한 문자를 의미
예) ^ab(ab,abc,abdr...) -> ^ab면 ab로 시작해야함, ab, abc abfdfd맞음, acb 안됨

$ : $앞의 문자열과 같은 문자열로 끝나는 경우 즉 문자열의 끝을 의미
예) .*ab$ (ab,avab,aab,abab...) -> *앞에 임의의 한글자 있으니까 됨, ab문자열로 끝나야함, 1ab됨, 1은 임의의한글자, 숫자라는 문자1개

[] : []안에 문자열중에 하나만의 문자만을 의미 -> 범위 중 한 문자, [a-z]면 A안됨, 3안됨
예) [a-z](a부터 z까지중 한 문자)
    [0-9](0부터 9까지 숫자중 한 문자)
 [abc](a혹은 b혹은 c)

{} :{}앞의 한 문자의 개수를 의미 문자{최소개수,최대개수} -> 문자열아니고 한 문자!
    최소개수는 반드시 있어야하고 최대개수가 없는 경우는 1개또는 1개이상을 의미
 하고 숫자 하나만을 적어 주엇을때는 그 숫자만큼의 개수를 의미한다.
예) abc{1,2} (abc,abcc) -> 1은 최소개수, 2는 최대개수, c가 최소1개, 최대2개, abc, abcc
    a{3} (aaa) -> aaa문자 정확히 3개 의미
 a{1,} (a,aa,aaa....) -> a최소개수1개, 최대개수 무한, 최소 a 1개만 넘으면됨

참고) *,+,?를 {} 로 표현해보면
* => {0,}
+ => {1,}
? => {0,1}

() : ()안의 문자는 그룹으로 인식한다.데이타 추출시 주로 사용한다.
예) a(bc){2} (abcbc) -> 위에 엮임, 앞에 묶인 문자를 한 문자로 취급

| : or 연산자
예) a(b|c)d  (abd,acd) -> 파이프라인이라함, 왼쪽 정규표현식, 중간에 b나 c가 되도록함

[^ ] : []안의 문자는 사용 못한다는 의미 ->not! 부정의미(대괄호 안의 ^일 때, not연산자)
예) [^abc] (a나 b나 혹은 c를 포함하지 않은 한 문자) -> d나 A나 가능, a b c 세개만 없으면 됨
    [^0-9] (0에서 9까지의 숫자를 포함하지 않은 한 문자)

\p{Alpha}:대/소문자 알파벳 한 문자를 의미
\p{Digit}:숫자 한 문자를 의미 -> [0-9]도 숫자 한문자, 이게 나음
\p{Alnum}:대/소문자 알파벳 혹은 숫자 한 문자를 의미 ->[A-Za-z0-9]와 동일

\r:캐리지리턴
\n:new line -> 줄바꿈
\t:일반 탭문자
\0:null문자
[\b]백스페이스
\s:공백문자 -> 많이 씀
\S:공백이 아닌 문자 -> 대문자는 not의미, 공백이 not
\w:알파벳문자,숫자,_[a-zA-Z0-9_] -> 대소문자 무관, 언더바_도 포함함, \p{Alnum}에 언더바 추가된 것 중 한 문자 뽑아오기
\W:알파벳문자,숫자,_가 아닌문자[^a-zA-Z0-9_] -> 대문자라 not!, 꺽쇄!

\d:정수[0-9]
\D:정수가 아닌 문자[^0-9]

예시)
abc{2,3}:ab뒤에 c가 최소 2개에서 최대 3개인 문자열인지
^a.{2}d$ :a와 d사이에 2개의 문자가 있는 문자열인지
^a|c$ : a로 시작하거나 혹은 c로 끝나는 문자열
a(bc){2,3}:a뒤에 bc가 최소 2개에서 최대 3개인 문자열인지
^[a-z][0-9]$ :a에서 z사이의 한문자로 시작하고 0에서 9사이의 한 숫자로 끝나는 문자열인지
^[a-z][0-9]:a에서 z사이의 한문자로 시작하고 0에서 9사이의 한 숫자로 끝나는 문자열인지
^[^a-z][^0-9]:a에서 z사이의 한문자로 시작하지 않고 0에서 9사이의 한 숫자로 끝나지 않는 문자열인지


[주요 정규식]

-핸드폰 번호
 "^[01][1][016789]\\d{3,4}\\d{4}$"

-> 0이나 1로 시작/ 1이 와야함/016789 중 한 문자/ 3자리~최대4자리, 자바정규표현식이라 이스케이프 안되도록 \\d에 \\ 2번 써줌/뒤에 숫자 4자리

-이메일

"\\p{Alnum}+@\\p{Alnum}+\\.\\p{Alnum}+"

-> +대신 {1,} 써도 됨, .은 .com의 .인데 \1개붙이면 이스케이프, 방지 \\ 2개 써줌


예제
-> 정규표현식(regular expression) -> regex.안에 Patter, Matcher 클래스 많이 씀

	import java.util.regex.Pattern;
	import java.util.regex.Matcher;

	public class MyPattern{
		
		public static void main(String[] args){
			//13자리 숫자만 
			Pattern p = Pattern.compile("^(\\d{13})"); // \d는 숫자, 이스케이프방지\\ -> 문자열로 넣은 정규표현식을 컴파일, Pattern타입의 객체를 만듦
			String str="1111112222222";
			Matcher m = p.matcher(str); //p.matcher 메소드, matcher객체 만듦,

			if(m.matches()){ //matcher객체의 matches 메소드, true면 패턴일치
				System.out.println("패턴 일치");
			}
			else{
				System.out.println("패턴 불일치");
			}
		}
	}
*/	
	
	public static void main(String[] args) {
		//어떤 문자열이 특정패턴을 갖는지 판단하거나, 데이터를 추출할 때 정규표현식을 사용한다.
		Scanner sc=new Scanner(System.in);
		String regex="[a-zA-Z]+@[a-zA-Z0-9]+\\.[a-zA-Z]+"; 
		//a~z문자 또는 A~Z문자 중 한개이상(+), @는 이메일의@, 그냥 .쓰면 임의의한글자, 문자. 쓰려면 \\. 필수, 뒤에는 보통 .com, 그냥 영어 한문자이상
		
		//1.Pattern객체 생성: Pattern Pattern.compile("패턴문자열") -> 정적메소드인 compile을 쓰면 Pattern을 반환, Patt 컨트롤 찍으면 소스에 PPattern나옴 
		Pattern pattern=Pattern.compile(regex); //pattern에 담음
		//String pattern():패턴문자열 봔환
		System.out.println(pattern.pattern()); //pattern.찍으면 pattern객체의 메소드 쭉 뜸 -> pattern.pattern()은 정규표현식 string으로 반환(컨트롤상태에서 콜론:뒤가 반환타입)
		System.out.println("이메일을 입력하세요?");
		String email= sc.nextLine();
		
		//2.Matcher객체 생성:Matcher Pattern객체.matcher("패턴과 일치하는지 검증할 대상 문자열") ->Pattern객체 생성
		Matcher matcher=pattern.matcher(email); //Matcher객체 임포트 해줘야함
		
		//Matcher객체의 주요메소드
		//1)matches():대상문자열 전체가 패턴과 일치시 true반환, Matcher의 matches메소드(반환값 boolean)
//		System.out.println(matcher.matches()?"이메일 형식이다":"이메일 형식이 아니다"); //matches메소드 -> 내가 정의한 패턴문자열에 일치 시 true 아니면 false를 불린값으로 반환
		
		//아래 start(), end()메소드는 반드시 매칭이 된 경우에만 호출해야한다 ->아니면 노매치 에러//-> 패턴 안맞으면 start메소드 에러남(즉, 트루일때)-> 매칭이 된 상태에서 호출해야함
		//2)start(): 매칭되는 문자열 시작위치 반환.
		//3)end(): 매칭되는 문자열 끝 다음의 문자위치 반환.
//		if(matcher.matches()) {
//		System.out.println("패턴이 일치하는 문자열의 시작위치:"+matcher.start()); //패턴일치하는 문자열을 반환, 처음부터 일치시 문자열 맞는 위치 0으로 나옴
//		System.out.println("패턴이 일치하는 문자열의 끝위치:"+matcher.end()); // a@b.c 다 맞으면 끝 위치 5나옴, 0,1,2,3,4, 다음 위치를 알려줌
//		}
		// 나의 이메일 주소는 a@b.c이다 -> 쓰면 공백 있으니까 이메일 형식이 아니다, matches 안맞음
		
		//4)find([인덱스]): 입력(대상)문자열에서 패턴과 일치하는 다음 문자열을 찾는 메소드, [인덱스]는 생략가능, 인덱스 미지정시 인덱스0부터 찾기 시작함(반환값 boolean)
		System.out.println(matcher.find()? "이메일형식의 문자열부분이 있다": "이메일형식의 문자열부분이 없다");
		// a@b.c -> 0부터 일치함, -> 위에 matches 주석처리해야함, 이미 읽어서 문자열부분이 없다고 나옴
		// 얘는 전체를 따지는게 아님, 인덱스 지정 위치부터 찾음 -> 
		if(matcher.find()) {
			System.out.println("패턴이 일치하는 문자열의 시작위치:"+matcher.start());
			System.out.println("패턴이 일치하는 문자열의 끝위치:"+matcher.end()); 
		}
//		System.out.println(matcher.find()); //다음 이메일 주소 없으면 false나옴, 위에 find(0)해야 true, 0안해도 true
		
		//그룹관련 함수들 
		//아래 정보를 3개 방법으로 가공
		String value="[17.07.11 23:29:11] [INFO ]  [eclipse.galileo-bean-thread-50618297 galileo.site.SiteBean:317 ] - ##galileo_bean end. MessageExchange_ID:id:localhost-15a6308ba1c-6:86071562";
		
		//패턴 만들자, matches 쓸거, 그룹함수,
		//방법1:\d사용
//		pattern = Pattern.compile("\\[(\\d{2}\\.\\d{2}\\.\\d{2}\\s\\d{2}:\\d{2}:\\d{2})\\]\\s\\[(.+)\\]\\s{2}\\[(.+)\\]\\s-\\s(.+)");  //패턴 달라졌으니 새로!, pattern과 일치하는걸 인자로 전달된 걸로 대체하는 메소드 replace
		// [대괄호 정규표현식에서 쓰니까 \\써줌, 숫자는 \d 마찬가지 \\, 그리고 2개, 공백은 띄워놔도됨 11~23 사이, 공백지우려면 \\s, 콜론은 표기 없으니까 그냥 씀, INFO -> 임의의 문자 .+, 띄어쓰기2개는 \\s{2}2개, 괄호 안 이클립스 어쩌구는 임의의문자 1개이상 .+로 퉁침(띄어쓰기포함)
		// 위에꺼 복붙해서 수정해나가면됨
		
		//방법2:[0-9]사용 -> \d동일
//		pattern = Pattern.compile("\\[([0-9]{2}\\.[0-9]{2}\\.[0-9]{2}\\s[0-9]{2}:[0-9]{2}:\\d{2})\\]\\s\\[(.+)\\]\\s{2}\\[(.+)\\]\\s-\\s(.+)");
		
		//방법3:러프하게, 추출만할 목적
		pattern = Pattern.compile("\\[(.+)\\] \\[(.+) \\]  \\[(.+) \\] - (.+)");
		//빈공백 필요 없으면 공백은 괄호로 안싸도됨, 공백 냅둬도 됨. 괄호한건 .+로 바꿈, 대괄호 앞에만\\ 해줘야함, INFO는 귀찮
		
		matcher=pattern.matcher(value);
		System.out.println("패턴일치 여부:"+matcher.matches());
		//4)groupCount(): 패턴 내 그룹핑한(괄호지정) 전체 갯수를 반환. 패턴이 일치안해도 됨
		System.out.println("그룹수:"+matcher.groupCount()); //위에 대괄호 4개 안의 4개를 그룹으로 묶음
		//5)group()
		//항상 패턴이 일치하는지 판단후 matcher.group(그룹번호)사용
		//일치하지 않으면 java.lang.Illegal~~~~ No match foud에러
		//group(0)번은 전체 대상문자열
		if(matcher.matches()) {
//			System.out.println("그룹 0번:"+matcher.group()); //그룹함수, 인자 안넣으면 전체 대상문자열 반환
//			System.out.println("그룹 0번:"+matcher.group(0)); //전체 대상문자열 반환
//			System.out.println("그룹 0번:"+matcher.group(1)); //첫번째 괄호
//			System.out.println("그룹 0번:"+matcher.group(2)); //두번째
//			System.out.println("그룹 0번:"+matcher.group(3));
//			System.out.println("그룹 0번:"+matcher.group(4));
			for(int i=1; i<=matcher.groupCount(); i++) System.out.printf("그룹 %d번:%s%n",i,matcher.group(i));
		}
		
		// 한번더 해보기
		value="00000  000%  1. Before Marshalling";
		pattern=Pattern.compile("([0-9]{5})\\s{2}(\\d{3})%  \\d{1,}\\. (.+)"); 
		//먼저 괄호부터 쳐야 추출하기 편함 (00000) -> 이후 정규표현식으로 만들기, 변하는 1도 바꾸기, 그룹괄호는 안침
		matcher=pattern.matcher(value); //패턴 객체로 매처 만듬
		if(matcher.matches()) {
			for(int i=1; i<=matcher.groupCount(); i++) System.out.printf("그룹 %d번:%s%n",i,matcher.group(i));
		}
		
		value="2005-04-30 17:16:14 95 45.114.2.130 200 TCP_MISS 2222 404 GET http images.netmechanic.com /images/webtools/webmaster_tools.gif - - DIRECT images.netmechanic.com image/gif \"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; CAWORLD2k4; .NET CLR 1.1.4322; .NET CLR 1.0.3705)\" PROXIED Computers/Internet - 192.16.170.44 SG-HTTP-Service - none -";
		//문]위 로그에서 아이피(45.114.2.130),응답코드(200),캐시결과(TCP_MISS),
		//   HTTP메소드(GET->GET외에도 가능, 3자 이상임)를 정규표현식을 이용하여 추출하여라. -> 4개 추출, 그룹 4개
		// 아이피도 0~255사이, 최대 3자리/ 응답코드는 3자리/ 캐시결과는 그대로
		pattern=Pattern.compile("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}\\s\\d{2}\\s([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})\\s(\\d{3})\\s([A-Z]{3}_[A-Z]{4})\\s\\d{4}\\s\\d{3}\\s([A-Z]{3,})\\s.+");
		//언더바_는 냅두고, [A-Z]{3,} -> A~Z까지 3자 이상, [0-255]하면 안됨 [0-9]해야함
		matcher=pattern.matcher(value);
		System.out.println(matcher.matches());
		if(matcher.matches()) {
			for(int i=1; i<=matcher.groupCount(); i++) System.out.printf("그룹 %d번:%s%n",i,matcher.group(i));
		}
		
		
		
		
		
		
		
	}////////////main

}////////////////class
