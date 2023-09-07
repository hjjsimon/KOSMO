package console.academy;

import java.io.IOException;

public class AcademyApp {

	public static void main(String[] args) throws IOException {
		//배열 사용]
//		AcademyLogic logic=new AcademyLogic(); //지금 오른쪽이 AcademyLogic가면 있는 생성자, 찍으면 글로감, AcademyLogic타입 클래스 생성
		//System.out.println(logic.person); -> 주소확인
		//System.out.println(logic.person[0]); -> Person형 배열 0번방 null 초기화 확인
		//컬렉션 사용]
//파일저장4/4		AcademyCollectionLogic logic=new AcademyCollectionLogic(); //클래스명만 바꾼거라 실행하면 잘 됨
		//컬렉션 사용-파일저장추가(BufferedReader/PrintWriter)
//		AcademyCollectionBufferedRWLogic logic=new AcademyCollectionBufferedRWLogic();//컬렉션메모리는 프로그램끄면 RAM에 올라온 메모리 사라짐, 파일로 저장필요
		
		//컬렉션 사용-파일저장추가(ObjectInputStream/ObjectOutputStream)]
		AcademyCollectionObjectInOutLogic logic=new AcademyCollectionObjectInOutLogic();
		
		while(true) {
			//1.메인 메뉴 출력
			logic.printMainMenu();
			//2.메인메뉴 번호 입력받기
			int mainMenu=logic.getMenuNumber(); //반환타입 int니까 int에 담음
			//System.out.println(mainMenu); parseInt라 숫자형 아닌 문자 입력시 에러남
			//3.메인메뉴에 따른 분기->AcademyLogic에 메소드 생성
			logic.seperateMainMenu(mainMenu); //switch끝나면 다시 while내 맨 위로 이동, 반복
		}
	}
}