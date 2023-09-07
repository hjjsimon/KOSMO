//1]패키지 선언문, 아래 클래스는 패키지19 아래에 있다는 뜻
//디렉토리(=패키지,폴더) 만드는 이유는 유사한 클래스, 인터페이스끼리 묶기 위해서
package package19;
//2]import선언문, 다른 패키지에 있는건 import해서 씀, java.lang 패키지에 있는거 제외
//import:다른 패키지에 있는 클래스들을 가져다쓸때 사용하는 선언문, ex. 패키지명.하위패키지명...클래스명 이런식
//단, java.lang패키지 안에 있는 클래스들은 기본패키지로 import할 필요 없음
import java.util.Date; //java.util.*; <-util 패키지 안의 모든 클래스나 인터페이스를 의미, 모든 클래스를 로드하면 쓸데없음, 안좋음(util패키지 안의 하위 패키지는 포함안됨)

import common.utility.CommonUtil;
//import java.sql.Date; //빨간줄감, Date가 util에 있는 Date인지 sql에 있는 Date인지 컴퓨터가 헷갈림(sql은 util을 상속받음)

/*
[컴파일된 .class파일들을 .jar로 묶기] ->묶어서 친구 주면 됨(jar는 java archive(압축하다)의 약자)

1. 실행용으로 묶을 수도 있고-> Runnabl jar로 묶는것
	※먼저 main이 있는 클래스를 한번은 실행해야한다(그래야 launch configuration에 뜸) ->우선 콘솔에 한번 컨트롤 f11해야함
	(하나의 자바 프로젝트를 하나의 실행 프로그램으로 만들자 즉 해당 프로젝트에 main메소드가 있는 클래스가 하나가 되도록) -> 원래 프로젝트 하나가 하나의 프로그램, 실행 메인은 딱 하나가 정상
	-> 실행가능한 클래스를 jar로 묶어서 친구한테 주면 쓸 수 있다
	-> cmd에서 쓰면 콘솔 이클립스보다 나음, 
	1) File - Export-Java-Runnable Jar file선택-Next
	2) Launch Configuration:main메소드가 있는 클래스 선택, Export Destination:.jar로 저장할 위치 및 파일명 지정
	3) 콘솔창을 실행하여 2)번에서 생성한 .jar가 있는 디렉토리로 이동후 java -jar 파일명.jar  로 프로그램 실행
	위와 같이 묶는 경우 해당 프로젝트의 모든 패키지가 jar로 묶인다(용량커짐, 구림)
	따라서 새 프로젝트를 만들어 실행하고자하는 파일이 들어 있는 패키지나 클래스만 별도로 복사해서 실행가능한 .jar로 배포하는 것이 좋다
	
	jar cmd로 접근시 
	C:\Users\kosmo>d:
 	D:\>cd hjj
	D:\HJJ>java -jar academy.jar -> 콘솔나옴
	
2. 라이브러리용으로 묶을 수도 있다

	2-1)클래스를 라이브리러(jar)로 배포하기
	   
		프로젝트 선택후 마우스 우클릭-Export-Java-Jar file선택-Next-
	 	배포하고자하는 패키지 선택(common.utility)
	    Export Destination:.jar로 저장할 위치 및 파일명 지정 그리고 Next~ Finish
	
	2-2)jar로 묶는 라이브러리 프로젝트에서 가져다 쓰기
	
		Build Path : 프로젝트마다 빌드 패스 해야 한다
		새 프로젝트 생성후 해당 프로젝트 마우스 우클릭-Build Path-Configure Bulid Path-Libraries탭-Classpath 선택
		-Add External JARs후 배포한 jar파일 선택
		
		패키지 누르고 컨트롤 z 하면 삭제한 common.util 다시 생김, 넣어준 jar 지울때는 빌드패스에서 리무브 어플라이
*/

public class PackageApp {

	public static void main(String[] args) {
		//다른 패키지에 있는 클래스 접근방법]
		//방법1]import해서 사용: 패키지지정 불필요	
		Date utilDate=new Date();
		System.out.println(utilDate); //날짜 출력, sysout 이런 것도 java.lang에 있는 것, 그래서 import할 필요 없음
		
		//방법2]import안해서 사용: 직접 패키지명까지 씀
		//java.sql.Date sqlDate=new java.sql.Date(); //기본생성자가 없다고 나옴, new java.sql.Date(); 빨간줄 눌러보면 얘는 인자생성자밖에 없는 클래스임
		java.sql.Date sqlDate=new java.sql.Date(new Date().getTime()); //getDate() 는 1970년 1월1일부터 1000분의 1초단위로 반환
		System.out.println(sqlDate); //toString이 오버라이딩 되어서 이쁘게 2023-03-28 나옴, 주소안나온다는 뜻(sqlDate나 sqlDate.toString이나 동일) 
	
	}

}
