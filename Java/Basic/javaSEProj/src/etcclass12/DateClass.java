package etcclass12;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import common.utility.CommonUtil;

public class DateClass {

	//util.Date , sql.Date <-얘는 util에서 상속
		//왕중요 Date(long date), getTime() <-get은 반환.
		//toString도 있음, Object에서 상속, 오버라이딩 된 것
	
	
	/*
	  Date클래스:날짜 정보를 다루는 클래스로   대부분 Deprecated되었음
	  Date()기본 생성자(인자가 없는거)나 Date(long date)인자 생성자를 제외한 모든  생성자와 대부분의 메소드가  Deprecated됨.
	 
	  java.util패키지에 있음 -> 자주 쓰는건 어느 패키지에 있는지 암기, 하드코딩하면 임포트로 가져와야함
	 
	  Date클래스의 toString() 메소드도 메모리의 주소를 문자열로 반환하는 것(원래 기능)이 아니라 저장된 날짜 데이타를 문자열로 반환되도록 오버라이딩 되었음.
 */
	private static String dayOfWeek(int day) {
		switch(day) {
		case 0: return "일요일";
		case 1: return "월요일";
		case 2: return "화요일";
		case 3: return "수요일";
		case 4: return "목요일";
		case 5: return "긐요일";
		default: return "토요일";}
	}////dayOfWeek
	
	private static String getDate(Date date) {
		return String.format("%d년 %d월 %d일 %d시 %d분 %d초", 
				date.getYear()+1900,//설정할 때 1뺐으니까, 반환은 1더해야함);
				date.getMonth()+1,
				date.getDate(),
				date.getHours(),
				date.getMinutes(),
				date.getSeconds()
				);
	
	}////getDate	
	
	private static String getDate(Calendar cal) {
		return String.format("%d년 %d월 %d일 %s %d시 %d분 %d초", 
				cal.get(Calendar.YEAR),//설정할 때 1뺐으니까, 반환은 1더해야함);
				cal.get(Calendar.MONTH)+1,
				cal.get(Calendar.DATE),
				cal.get(Calendar.AM_PM)==1? "오후": "오전", //1이 오후
				cal.get(Calendar.HOUR),
				cal.get(Calendar.MINUTE),
				cal.get(Calendar.SECOND)
				);
		
	}////////////////소스 확인, getDate 이름 동일?????



	
	public static void main(String[] args) throws ParseException {

		Date today=new Date(); //Date. 찍고 util 유래 import 해야함, 인자 안넣으면 현재 시스템 날짜 정보 가져옴
		System.out.println("today:"+today); //오버라이딩 되어 주소출력x, 날짜출력o ->today:Wed Mar 22 11:40:23 KST 2023
		System.out.println("today.toString():"+today.toString()); //오버라이딩 되었으므로 결과동일
		System.out.println("년도:"+(today.getYear()+1900));//세팅은 set계열, 컨트롤. 찍으면 초록색동그라미에 빗금(디프리케이트), 1900이 빼진 값으로 출력해줌, 1900더해줘야 년도나옴 
		//위에 문자열이라 1900 괄호 안묶으면 1231900연결되어 나옴
		System.out.println("요일:"+today.getDay());
		//Returns the day of the week represented by this date. Thereturned value (0 = Sunday, 1 = Monday, 2 = Tuesday, 3 = Wednesday,~~~
		//수요일은 3이라고 반환함, 못알아봄 -> 아래 메소드 새로 만듦, 시스템 날짜를 가져옴, 날짜 바꾸면 그거 나옴
		System.out.println("요일:"+dayOfWeek(today.getDay()));
		
		Date pasteDay = new Date(1970-1900, 1-1, 1, 0, 0, 1);
		//Date 컨트롤로 찾음, 년~초까지, 년도 1900빼는건 또 더해줌, 월에서 1은 0으로 세팅됨 0~11값만 가능 ex. 1-1=0으로 1월(보기쉽게) //1970년 1월 1일 0시 0분 1초
		System.out.println(getDate(pasteDay));
		
		// Date클래스의 int getTime()메소드 -> int반환하는게 아래내용
		// 1970년 1월 1일 0시 0분 0초 이후로 흘러온 시간을 1/1000 초 단위(밀리세컨)로 반환 
		// 위에 1초 지나게 세팅했으니까 1000m
		// GMT 영국 그리니치 천문대 시간 기준, 우리나라는 9시간 빠름
		
		//※long getTime():1970년 1월 1일 0시 0분 0초부터 특정 시점까지 흘러온 시간을 1000분 1초단위로 반환하는 메소드
		//영국에 있는 그리리치천문대 기준 우리나라는 9시간 빠름 
		//pasteDay객체를 1970년 1월 1일 0시 0분 1초
		
		long diff=pasteDay.getTime(); //밀리세컨단위라 숫자 큼, long으로 반환함, diff(차이)에 담음
		System.out.println(diff+(9*60*60*1000)); //1초니까 1000밀리초-> 1000 차이값이 나와야함
		//근데 그리니치 기준이라 우리나라 시간으로 9시간 더해줘야함-> 9시간을 밀리세컨 단위로 바꿔서 더해줌
		
		/*
	    Calendar클래스: 생성자로 Calendar객체를 인스턴스화 할 수없다 (왜냐하면 접근지정자가 protected이기때문에)
	    Calendar클래스는 Single tone Design으로 정의 되었음. 그래서 getInstance()메소드로 인스턴스화 함.
	    java.util패키지에 존재
	    클래스를 설계하는 디자인 패턴, 그 중 single tone Design으로 설계됐대
	    */
		
		//Calendar cal=new Calendar(); //[x] Cannot instantiate the type Calendar -> 생성자 인스턴스화 못한다고 나옴,
		// Calendar rightNow = Calendar.getInstance(); -> 클래스명으로 접근, static이네, Calendar 타입으로 반환
			
		Calendar cal= Calendar.getInstance();
		System.out.println("cal:"+cal); //cal:java.util.GregorianCalendar[time=1679454307500,areFieldsSet=true,areAllFieldsSet=true~
		//주소 안나옴, 오버라이딩 된 것, 현 시간에 대해 각종 정보가 나옴
		System.out.println("cal.toString():"+cal.toString()); //똑같음, toString 오버라이딩 됐으니까 똑같이 나오는 것
		System.out.println("년도:"+cal.get(Calendar.YEAR)); //int field -> get() 괄호 내 갖다대면 필드가 어떻게 숫자야, 이러면 상수로 정의된 것, final이라고 찾아보면 나옴
		//상수로 써야함 그래서 calendar.YEAR , 1 이런건 모름
		//1:일요일, 2:월요일......7:토요일
		System.out.println("요일:"+cal.get(Calendar.DAY_OF_WEEK)); //4나오면 모름
		System.out.println("요일:"+dayOfWeek(cal.get(Calendar.DAY_OF_WEEK)-1)); //위에 만든 메소드 호출해서 한번 더 감싸 4나오는거 해결
		//데이트 클래스에서는 일요일이 0, 
		//지금은 캘린더(?) -> 일요일이 1 시작 그래서 1 뺴줌
		//System.out.println(getDate(cal)); //cal은 캘린더 타입, 위 메소드 괄호안에는 데이트 타입 넣어야함, 빨간줄 찍어서 메소드 생성
		System.out.println(getDate(cal)); // 2023년 3월 22일 오후 0시 16분 41초 -> 알아보게 잘 나옴
		// 데이트 타입 <-> 캘린더 타입 상호전환 중요 
		// 캘린더 클래스 내 getTime() -> 이건 Date객체 반환해줌 (인터넷 찾아보면 나옴)
		// 데이트 클래스 내 getTime() -> 은 위에 설명 그리니치 어쩌구
		System.out.println(getDate(cal)); //Date타입 반환, long타입이 아님
		
		//Calendar클래스의 Date getTime() -> 위처럼 int가 아니라 Date를 반환
		//Calendar타입을 Date타입으로 변환(두 클래스가 상속관계가 아니라 형변환 불가): Date Calendar객체.getTime()로 변환 
		//캘린더 객체의 getTime -> 캘린더 타입을 데이트 타입으로 바꿔주는 것, 형변환은 불가
		
		System.out.println(getDate(cal.getTime()));//cal 캘린더가 데이트 타입으로 바뀜 -> 오전오후없는 getDate 메소드, 위에껀 오전오후 있음
		
		//특정날짜 설정방법1: void setTime(Date타입) <-인자 Date타입 넣음 / Date타입 인자로 전달된 날짜정보를 캘린더타입의 날짜정보로 세팅/ cal.set이랑 다름
		//가져올 때 get으로 위에 Calendar.DAY_ 어쩌구 받음, 날짜 설정은 set!
		//calendar.setTime() <- 으로 쓰면됨 void 무시
		//Date타입의 날짜를 Calendar타입의 날짜로 설정 <- Date<->Calendar 자유자재 필수, Calendar.getTime()의 반대
		cal.setTime(pasteDay); //Date pasteDay = new Date(1970-1900, 1-1, 1, 0, 0, 1); 위에 세팅, 
		//Calendar cal= Calendar.getInstance();-> cal은 현재날짜로 세팅해놨는데 위 때문에 바뀜
		
		System.out.println(getDate(cal));//캘린더타입 받는거
		System.out.println(getDate(cal.getTime()));//데이트 타입 받는거 , 위랑 동일 오전/오후 안뜰뿐
		
		//특정날짜 설정방법2: set()메소드
		Calendar cal1=Calendar.getInstance(); //둘다 현재 시스템 날짜 들어가있음
		Calendar cal2=Calendar.getInstance();
		//월은 0베이스, 1월이 0
		cal1.set(2023, 8-1, 25); //년,월,일 세팅 ,, 1월=0 귀찮으니까, 그냥 8월은 8-1로 쓰자 //현재날짜 시스템에서 시분초는 바꾸지 않고, 년월일만 변경 
		cal2.set(2023, 1-1, 31, 10, 10, 10); //년,월,일,시,분,초 모두 현재날짜에서 바꿔버림
		System.out.println(getDate(cal1)); //그냥 cal1치면 이상한 긴 정보 나옴, getDate해야 깔끔하게 나옴, 내가 만든 메소드
		System.out.println(getDate(cal2));
		
		//SimpleDateFormat클래스: java.text패키지에 있는 클래스, 날짜관련 정보 얻는데 유용함, 캘린더 불편하대
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd a HH:mm:ss EEE"); //오전 오후가 a
		System.out.println("dateFormat"+dateFormat); //주소 나옴. toString이 오버라이딩 안된것, Object껄 씀
		System.out.println("dateFormat.toString()"+dateFormat.toString());
		
		/*
		 *  java.lang.Object
			java.text.Format
			java.text.DateFormat
			java.text.SimpleDateFormat -> 얘는 DateFormat에서 상속받음, 상속받은건 그대로 쓸 수 있음,
			
			날짜를 문자로 바꿀 때 DateFormat의 메소드인 format 메소드를 쓸 수 있음
			날짜정보는 인자로 받은 Date에 있고, yyyy-MM-dd a HH:mm:ss EEE -> 타입으로 줌
		 */
		
		//1]String format(Date d); -> Date타입을 String형으로 반환해주는 메소드, 날짜 패턴에 맞게 반환해줌, Date -> String 으로 바꿔줌
		System.out.println("오늘 날짜:"+dateFormat.format(today)); //오늘 날짜:2023-03-22 오후 12:57:07 수/ 위에 EEE -> EEEE 4개하면 수 말고 수요일 나옴
		System.out.println("과거 날짜:"+dateFormat.format(pasteDay)); //과거 날짜:1970-01-01 오전 00:00:01 목
		
		/*
		2]중간에 날짜 패턴 변경 -applyPattern(String 날짜패턴)
		 a는 오전/오후를 나타내는 패턴
		 예로 날짜 패턴에 at를 출력하고 싶다면
		 a는 패턴을 의미하는 문자 이기때문에
		 "yyyy년 MM월 dd일 at HH시"라고 하면 에러
		 그래서 'at'으로 표현하면 a는 패턴을 의미하는게아니라
		 그냥 a문장에 해당
		 "yyyy년 MM월 dd일 'at' HH시"는 정상*/
		
		//중간에 날짜 바꾸고 싶으면 쓰는게 applyPattern 인자로 전달된 새로운 pattern을 새로 적용
		
		dateFormat.applyPattern("yyyy/MM/dd"); //시분초 출력안하게 날짜 바꾼 것
		System.out.println("오늘 날짜:"+dateFormat.format(today));
		System.out.println("과거 날짜:"+dateFormat.format(pasteDay));
		
		/*3] Date parse("날짜형식의 문자열"); -> 날짜형식의 문자열을 Date타입으로 반환 해주는 메소드, String ->Date 바꿈
	    ※단,날짜 형식의 문자열이 날짜패턴과 일치해야한다*/
		//DateFormat에서 상속받은 parse매소드, 날짜형식 String 받아 Date로 반환, 단, 패턴이 맞아야함
		
		String stringDate="2023/1/1"; //MM은 한자리로 해도 됨, 날짜를 문자열로 저장, 문자열끼리 저장, ex 2023/9/7 이랑 2023/1/1 날짜 차이? -> 윤달도 끼고 겁나 어려움
		//getTime으로 구할 수 있음, getTime으로 1970년 기준 흘러온 시간만큼을 두 날짜별로 각각 알아냄, 그리고 그 차이를 구하면 밀리세컨단위로 시간차가 나옴
		//그래서 stringDate를 Date타입으로 바꿔줘야함
		System.out.println(dateFormat.parse(stringDate));//Date로 parsing하겠다!, Date로 반환
		//stringDate="2023-1-1"; //이런식으로 패턴 바꾸면 에러남, dateFormat.applyPattern("yyyy/MM/dd"); -> 이거랑 패턴 달라서 parsing 불가
		
		//4] toPattern(): 패턴을 반환하는 메소드
		System.out.println(dateFormat.toPattern()); //설정한 패턴대로 나옴
		//두 날짜간 차이 구하기
		SimpleDateFormat simple=new SimpleDateFormat("yyyy.MM.dd");
		String stFDate="2023.3.22";//FirstDate 의미, 위 패턴에 맞게 써야함
		String stSDate="2023.3.23";//24시간 차이남

		//1)String->Date: parse() 써서 바꾸기
		Date fDate=simple.parse(stFDate); //퍼스트데이트
		Date sDate=simple.parse(stSDate); //세컨데이트
		
		//2)Date클래스의 getTime()호출: 1970년부터 흘러온 시간을 밀리세컨단위로 확인 
		// 1970년 1월 1일 0시 0분 0초부터 각 날짜까지 흘러온 시간을 1000분의 1초 단위로 얻기
		long fTime=fDate.getTime();
		long sTime=sDate.getTime();
		//sTime이 큼, 숫자 모르겠으면 그냥 절대값으로 하면 되고~
		diff=Math.abs(fTime-sTime);
		System.out.println(diff); //밀리세컨 단위로 86400000초 차이남
		System.out.println("초단위:"+diff/1000);
		System.out.println("분단위:"+diff/1000/60);
		System.out.println("시간단위:"+diff/1000/60/60);
		System.out.println("일단위:"+diff/1000/60/60/24);

		//CommonUtil 클래스에 메소드 만들고 옴 //일단위로 계산해서 반환해줌, 일단위라 'D'한거 'd'도 됨, Upper씀
		System.out.println("수료일까지 남은 일수:"+CommonUtil.getDiffBetweenDates("2023-3-22", "2023-8-25", "yyyy-MM-dd", 'D')); 
		System.out.println("수료일까지 남은 시간:"+CommonUtil.getDiffBetweenDates("2023-3-22", "2023-8-25", "yyyy-MM-dd", 'H'));
		System.out.println("수료일까지 남은 분:"+CommonUtil.getDiffBetweenDates("2023-3-22", "2023-8-25", "yyyy-MM-dd", 'M'));
		
		//문제]그 사람과 사귀기 시작한 날이 2023년 2월 15일이다 100일째 되는 날은 언제인가?
		System.out.println(CommonUtil.getDiffBetweenDates("2023-2-15", "2023-5-26", "yyyy-MM-dd", 'D'));
		
		//방법1]Date의 getTime()으로 1970년 1월 1일 기준 2023년 2월 15일까지의 시간을 1/1000초 단위로 얻어서 
		//100일을 1/1000초 단위로 바꾼 다음 더해주면 됨, 100일 전이면 빼기
		//100x24x60x60x1000 -> 21억이라 int로는 불가, 이 중 하나를 long으로 바꾸면 결과도 큰 long으로 계산됨
		//2023년 2월 15일을 날짜로 바꿈 -> simple 위에 씀
		Date firstDate=simple.parse("2023.2.15"); //simple 데이터포맷 . . 으로 맞춰줘야함
		//날짜를 1000분의 1초 단위로 변환
		long firstTime=firstDate.getTime(); //21억이라 int말고 long 변환, 1000분의 1초 단위로 변환, 에폭 어쩌구
		long hundredTime=(long)100*24*60*60*1000;
		System.out.println("100일째 되는 날:"+simple.format(new Date(firstTime+hundredTime))); //date타입을 반환해야하는데 갖고있는게 long, 반환해줘야함
		
		//방법2]Calendar클래스의 add메소드: 날짜를 더하거나 빼줌
		//날짜 더할 때는 add(Calendar.DAY_OF_MONTH, -5) -> 5일 뺌, Calendar객체부터 생성필요
		//방법2]Calendar객체.add(int field, int amount):amount는 음수 또는 양수
		Calendar firstCal=Calendar.getInstance(); //객체생성
		firstCal.set(2023,2-1,15); //2월이라 2-1
//		firstCal.add(Calendar.DAY_OF_MONTH, 100);
//		System.out.println("100일째 되는 날:"+simple.format(firstCal.getTime())); //firstCal 캘린더타입 -> 데이트타입으로 바꿔야함. getTime으로 바꿔줘야함
		
		//위에 더해버린건 주석처리
		//추가예제, 달을 더해보자
//		firstCal.add(Calendar.MONTH, 2); // 2달 더함
//		System.out.println("2023.2.15에 2달 더한 날:"+simple.format(firstCal.getTime()));
		
		firstCal.add(Calendar.MONTH, -2); // 2달 빼기
		System.out.println("2023.2.15에 2달 뺀 날:"+simple.format(firstCal.getTime())); //2022.12.15로 바뀜
		
		//boolean equals(Object obj) Compares two dates for equality.
		//원래 equals 주소비교인데, 오버라이딩 되어 Date 클래스 안에 있음, 두 날짜 같으면 t
		//앞에날짜, 인자가 뒤의 날짜면 after 메소드 T나옴
		
		//Date클래스의 날짜 차이관련 메소드]
		//1]두 날짜 사이의 선후관계 판단하는 메소드//////////////////////소스-------------------------
		//1)boolean after()/before()

		System.out.println(sDate.after(fDate)? //괄호안이 늦은 날짜돼야함 [o] true
				String.format("%s가 %s보다 최근 날짜다", simple.format(fDate), simple.format(sDate)): //출력
				String.format("%s가 %s보다 최근 날짜가 아니다", simple.format(fDate), simple.format(sDate)));
		System.out.println(sDate.before(fDate)? //괄호안이 빠른 날짜돼야함 [x] false
				String.format("%s가 %s보다 최근 날짜가 아니다", simple.format(fDate), simple.format(sDate)):
				String.format("%s가 %s보다 최근 날짜다", simple.format(fDate), simple.format(sDate))); //출력
		//fDate가 3.22, sDate가 3.23, fDate가 앞이라 false,
		
		//2]두 날짜가 같은지 비교, 같으면 true 다르면 false, Date클래스의 equals()메소드는 메모리주소비교가 아닌 날짜 비교하도록 오버라이딩 됨
		System.out.println(fDate.equals(sDate)?"두 날짜가 같다":"두 날짜가 다르다");
		
		//3]int compareTo(Date anotherDate): 두 날짜가 같으면 0반환, 앞의 날짜가 최근이면 양수(1), 앞의 날짜가 과거면 음수(-1) 반환, 날짜 차이는 계산안됨
		System.out.println(fDate.compareTo(sDate)); //sDate가 최근이니까 -1나옴, fDate가 최근이면 1나옴
		
			
	}///////////////main
}//////////////////class
