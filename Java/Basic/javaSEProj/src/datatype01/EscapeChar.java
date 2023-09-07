package datatype01;

public class EscapeChar {           
	
	//Escape 문자, 탈출하다 문자, 모든 문자가 이스케이프는 아님
	//특정 형식에 맞게 출력하기 위해 사용하는 문자로, 특정 문자 앞에 \을 붙이면 그 특정 문자는 특수한 기능을 함.
	//이스케이프 문자는 모든 "" 더블쿼트 문자열 내에서 사용할 수 있다.
	//이스케이프 문자는 모두 소문자여야 한다.
	//ex. "HitHomeRun" -> 여기서 t는 일반 문자
	//ex. "Hi/tHomeRun" -> 여기서 t는 \와 결합해서 탭만큼 들여쓰는 기능을 하는 이스케이프 문자가 됨. 출력시 Hi - HomeRun 됨
	//t가 hi 만나면 hit, t 앞에 hi\t -> 이렇게되면 t는 본래 문자의 역할에서 탈출, 기능을 못함, 역슬래시랑 결합해서 탭만큼 들여쓰는 기능을 함
	//출력시 hi    나오고 - 탭 위치에 슬래시 나옴
	//특정 문자만 (모두x) 그 문자 본래의 의미를 잃어버림, 그리고 새로운 기능이 부가됨
	
	public static void main(String[] args) {
		
		System.out.println("Hit Home-Run");
		System.out.println("Hi\t Home-Run"); // 출력 시 t 안보임

		//1] \n: 줄바꿈(line feed)기능, next 인 것 같음
		
		System.out.print("Welcome To Java!");            //println 에서 ln이 줄바꿈기능, 출력후 바로 엔터쳐서 다음 줄로 빼줌, 빼면 줄바꿈 안됨
		System.out.print("Hi Java!");
		System.out.print("Let's Do it.Go Fighting!!!");
		
		System.out.print("Welcome To Java!\n");            //n써서 줄바꾼 버전, println 대신 print로 \n을 써서 줄 바꾸는 방법
		System.out.print("Hi Java!");
		System.out.print("\nLet's Do it.\nGo Fighting!!!\n");
		
		//2] \r: 커서를 해당 줄에서 맨 처음으로 땡김(carriage return)
		
		System.out.println("My Nickname is Superman\rXX");  // XX는 그냥 문자임, \r도 줄바꿈 해줌, 근데 원래 안이럼, 커서가 한칸씩 앞으로 오다가
		// cmd로 보면 MY 사라지고 XX가 들어감 \r 이후로는 맨 앞으로 데려간 것
		// \r = 13, \n = 10 각 아스키 코드값 암기 필수
		// ex. 엔터키는 다음라인으로 갔다가(\n), 맨 앞으로 가는 것(\r), 엔터키를 누르면 컴퓨터에는 \r\n으로 저장되는 것, 13 10은 그냥 문자가 아님 기능을 하는 문자
		// \n은 그냥 같은 줄의 한칸 밑으로만 내림, 거기서 앞으로 땡기는게 \r -> 문자로 찍히지 않으므로 우리 눈에는 안보임
		
		
		//3] \t: 탭키만큼 띄어쓰기 기능, tab 인 것 같음
		
		System.out.println("국어t영어t수학");
		System.out.println("국어\t영어\t수학");
		
		//4] \': 싱글쿼테이션표시, 파이썬에서는 문자열을 ''로 감싸도 돼서 의미 있음, 자바는 무의미
		
		System.out.println("나의 닉네임은 '스마트 보이' 입니다");  //자바는 문자열 더블만 지금처럼 가능
		System.out.println("나의 닉네임은 \'스마트 보이\' 입니다");  // 자바스크립트에서는 ''를 문자열에 쓰므로 의미가 있음
		
		//5] \":더블쿼테이션표시, 원래 더블쿼테이션은 문자열의 시작/끝을 의미, 근데 탈출 시 그냥 나타나게 됨, 중요함
		
		//System.out.println("나의 닉네임은 "스마트 보이" 입니다"); // 스마트보이 변수면 선언이 되던가 +로 연결이 되던가 해야함. 지금 걍 중간에 끊김
		System.out.println("나의 닉네임은 \"스마트 보이\" 입니다"); 
		
		//6]\\: 둘 중 뒤에 \는 이스케이프 문자역할을 하는 특수문자가 아니라는 것을 컴파일러에게 알려주는 기능, 중요함 
		
		System.out.println("D:\nDrive\table");
		System.out.println("D:\\nDrive\\table");
		
		//윈도우는 디렉토리를 (원)으로 표시함
		
		/* 
		 * -------------------------------------------------------------------------
		 * Format String(형식 문자열): 출력형식을 지정하기 위한 형식 문자열 
		 * ex) %s나 %d 등의 변환지시어(Conversion Specifier)를 사용해서 원하는 출력형식을 만들 수 있다. 
		 * ex) System.out.println("나의 %d 닉네임은 \"스마트 보이\" 입니다"); -> 이럴 때 %d는 괜찮음. println는 그대로 %d 나오는 메소드임
		 * ex) 위를 printf -> Format String의 F임. 이건 %d만 넣으면 에러남.
		 * 변환지시어를 쓸 수 있는 메소드가 정해져있다. ex) printf
		 * 
		 * 주요 변환 지시어] 아래 값을 쓰면 대체됨 ex) %d는 decimal 임 -> 모두 소문자 씀
		 * %d: 정수값 출력시(byte/short/int/long)
		 * %f: 실수값 출력시(float/double)
		 * %c: 한 문자 출력시(char)
		 * %b: boolean형 출력시
		 * %s: 문자열 출력시 (다 됨, 모르겠으면 이거)
		 * -----------------------------------------------5개 변환지시어 중요 밑에 2개는 기능
		 * %n: 줄바꿈 (\r\n도 가능)
		 * %%: %리터럴 의미, \\처럼 아니라는 뜻
		 * 
		 * 이스케이프 문자는 더블쿼트 안에서 썼음. 이건 동일.
		 * 형식문자열을 쓸 수 있는 문자열이 정해져있음 
		 * println, print 이런걸 메소드라고함
		 */
		
		int kor=99, eng=80, math=96;
		double avg=(kor+eng+math)/3.0; 
		System.out.println("avg="+avg); 
		// 3으로 나누면 91.0 나옴, 결과값이 k+e+m=int끼리 계산한거니까, /3도 int이므로 int끼리 연산이라 소수점 날리고 int로 결과값 나옴
		// 근데 int 결과 91도 double에 담김, 근데 double니까 소수점 나오도록 91.0 됨
		
		//double avg1=(kor+eng+math)/3; // 3.0으로 나누면 double로 계산한거니까, double로 결과값이 나옴 91.0 // 91.66666 나옴
		
		System.out.println("[형식 문자열 미사용]"); // 문자열을 4개나 써야해서 귀찮다
		System.out.println("국어:"+kor+", 영어:"+eng+", 수학:"+math+", 평균:"+avg); // 91.6666666 나오도록 바꾸기 3.0으로 나눠서
		
		//더블쿼테이션 안에 있는걸 형식문자열이라고함 출력되는 그 값
		
		System.out.println("[형식 문자열 사용]");  // 문자열 1개에 모두 해결가능, 위에는 +랑" 귀찮음 
		System.out.printf("국어:%d, 영어:%d, 수학:%d, 평균:%.14f%n", kor, eng, math, avg); // d, f랑 개수도 4,4 맞추기
		
		// 플로트타입으로 출력됨 printf 라서? 
		// .14f 는 소수점 14자리까지 출력, 없으면 6자리 출력됨
		// 변환지시어는 5개지만 %n은 기능이니까 뒤에 kor 같은 값은 4개면 됨, %n은 줄바꿈기능
		// printf("형식문자열(""내의 글), 값들을 콤마로 구분해서 나열). 단 printf는 줄바꿈 기능이 없다. 뒤에 sysout 아무거나 쓰면 그대로 연결돼서 나옴
		
		System.out.printf("국어:%d\t 영어:%d\t 수학:%d\t 평균:%.14f\t\n", kor, eng, math, avg);
		
		// ,콤마 대신 \t로 바꾸고 %n은 \r\n으로 바꾸기 가능
		
		/*
		 * 형식문자열에서 데이터 출력시 자릿수 지정 
		 * ex) %숫자d
		 * %4c: 한문자를 출력하는데 전체 자리수는 4 -> 띄어쓰기 효과, 앞에부터 자리칸을 비움 ex. System.out.printf("%4c", '가'); -> [_ _ _ 가] 나옴
		 * %5d: 정수 숫자를 출력하는데 전체 자리수는 5 -> 119면 다섯자리(다섯칸) 중에 _ _ 1 1 9, 2칸 띄어쓴 효과 
		 * %6.2f%: 실수를 출력하는데 소수점 둘째자리(2)까지만 출력하고 전체자리수는 6(소수점포함) -> 3.14면 소수점포함 총 4자리임
		 * ----- 전체자리수는 잘 안씀. 띄어쓰기 할 때만 사용. 
		 * 양수는 오른쪽부터 채운다, -(음수)를 붙이면 왼쪽부터 채운다 
		 * ex) printf("%4d",12); ----> _ _ 12
		 * ex) printf("%-4d",12); ----> 12 _ _
		 */
		
		System.out.printf("%1.2f%n",3.14); // 전체자리수가 1자리? 말이 안됨. 근데 왜 되지. 여백 줄 때 1 씀. 4도 동일 붙음, 6쓰면 2칸 띄어짐
		
		System.out.println("[출력 자리수 미지정]");
		System.out.printf("국어:%d, 영어:%d, 수학:%d, 평균:%.14f%n", kor, eng, math, avg);
	
		System.out.println("[출력 자리수 미지정(양수)]"); 
		System.out.printf("국어:%4d, 영어:%4d, 수학:%4d, 평균:%6.2f%n", kor, eng, math, avg); 
		// 1칸 여백두게 4자리 해줌. 평균은 소수점 2자리까지, 올백이면 평균 100.00 6자리
		// 양수니까 총4자리인데 뒤쪽 2개를 채워서 국어:_ _ 99로 나온 것
		
		System.out.println("[출력 자리수 미지정(음수)]"); 
		System.out.printf("국어:%-4d, 영어:%-4d, 수학:%-4d, 평균:%.2f%n", kor, eng, math, avg); 
		// 평균은 왼쪽 붙이려면 6필요없음, 음수라 왼쪽부터 채움
		
		//기타]
		System.out.printf("%c %b %.2f %s%n",'가', false, 3.145678, "문자열"); //4개 4개 값을 짝 맞춘 것
		//'가' 를 "가"로 하려면 %c는 스트링이 아니라 %s로 바꿔야함
		
		System.out.printf("%s %s %s %s%n",'가', false, 3.145678, "문자열");
		//모든 값을 %s로 출력해도 된다. 단, 실수는 소수점 몇째자리는 안됨

		System.out.println("====================================");
		
		System.out.printf("%20s%n", "자바반 성적표");            //20 했더니 대충 중간 맞네~ 감으로 하는것 시행착오필요 //10은 왼쪽으로 감
		
		System.out.println("====================================");

		System.out.format("%-10s%-12s%s%n","KOREA","ENGLISH","MATH");  //english는 7자리 %-12s 하면 오른쪽에 다섯칸 남음, korea 5자니까 오른쪽5칸 동일, 마지막은 대충
		                                                               //format이라는 메소드 씀
		System.out.println("====================================");

		System.out.printf("%-10s%-12s%s%n", 100, 99, 78);
		
		System.out.printf("%-10s%-12s%s%n", kor, eng, math);  //이건 위에 있던거 가져온거

		System.out.println("====================================");

		// 이스케이프, 형식문자열 끝
		
	}// main
	
}//class
