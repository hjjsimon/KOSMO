package console.academy;

import java.util.Scanner;

public class AcademyLogic {
	//[멤버상수]
	public static final int MAX_PERSON=3; //입력하기 귀찮, 최대인원수 3명
	//[멤버변수]->Person형 배열 만듦, 부모님이 자식보다 크니까 담을 수 있음
	Person[] person;//Person형 배열, 참조형이니까 person에 null 들어있음, 아직 new 안해서 주소x
	//[생성자]
	public AcademyLogic() {
		person=new Person[MAX_PERSON];//Person타입 배열 3개짜리 생성, 가리키는 인스턴스 변수명 person[0],[1],[2]로 방 3개 생김, null로 초기화 
	}
	//[멤버 메소드]

	//1]메뉴 출력용 메소드, 매개변수 NO, 반환타입 void
	public void printMainMenu() {
		System.out.println("====================메인메뉴====================");
		System.out.println(" 1.입력 2.출력 3.수정 4.삭제 5.검색 6.파일저장 9.종료");
		System.out.println("==============================================");
		System.out.println("메인메뉴 번호를 입력하세요?");
	}////printMainMenu
	//tip. CRUD -> C=Create(생성,입력->1.입력), R=Read(조회,읽기->2.출력,5.검색), U=Update(3.수정), D=Delete(4.삭제)
	
	//2]메뉴번호 입력용 메소드, 매개변수 NO, 반환타입 int
	public int getMenuNumber() {
		Scanner sc=new Scanner(System.in); //키보드로 입력해서 시스템 in이래
		String menuStr=sc.nextLine().trim();//번호 입력하라는데 스페이스 누르는 애들 있음, "1"이랑 " 1"은 완전 다른 문자열, 문자열 다룰때는 trim 해주는게 좋음
		return Integer.parseInt(menuStr); //숫자형식의 문자열만 int로 배열해주는 parseInt
	}////getMenuNumber
	
	//3]메뉴번호에 따른 분기용 메소드, 매개변수 int(메인메뉴), 반환타입 void
	public void seperateMainMenu(int mainMenu) {//인자는 입력한 메인메뉴번호
		switch(mainMenu) {
			case 1: 
				while(true){//서브메뉴 출력 반복용 while
				//서브메뉴 출력]
				printSubMenu();
				//서브메뉴번호 입력]
				int subMenu=getMenuNumber(); //메뉴번호입력 만들어놓은거 또 씀
				if(subMenu==3) break; //3누르면 메인메뉴로 이동, 브레이크하니까 while빠져나옴, 그 밑에도 break라 switch빠져나옴, 호출한 메인으로 나옴, while처음으로 다시 메인메뉴출력
					switch(subMenu) {
				case 1: 
				case 2: setPerson(subMenu); break; //
				default: System.out.println("서브메뉴에 없는 번호입니다");
					
				}////switch
				}////while
				break;//입력, 브레이크하면 switch 빠져나옴
			case 2: 
				printPerson();
				break;//출력
			case 3: 
				updatePerson();
				break;//수정
			case 4: 
				deletePerson();
				break;//삭제
			case 5: 
				searchPerson();
				break;//검색
			case 6: 
				break;//파일저장
			case 9: 
				System.out.println("프로그램을 종료합니다");
				System.exit(0); //프로그램 종료코드, 0을 주면 정상적인 종료 의미
			default: System.out.println("메뉴에 없는 번호입니다");
		}////switch
	}////seperateMainMenu
	
	//4]서브메뉴 출력용 메소드, 매개변수 NO, 반환타입 void
	private void printSubMenu() {
		System.out.println("+++++++++++서브메뉴+++++++++++");
		System.out.println(" 1.학생 2.교사 3.메인메뉴로 이동");
		System.out.println("++++++++++++++++++++++++++++");
		System.out.println("서브메뉴 번호를 입력하세요?");
	}////printSubMenu

	//5]서브메뉴에 따른 학생 및 교사 데이터 입력용 메소드, 매개변수 int(서브메뉴), 반환타입 void
	//AcademyApp 클래스 그림설명
	//logic 인스턴스변수 주소 저장, 힙에 AcademyLogic@0x1234타입의 메모리 생성, logic이라는 이름으로 메인에서 메소드 호출중임 
	//public AcademyLogic() {
	//	person(작은방)=new Person[MAX_PERSON];//Person타입 배열 3개짜리 생성, 가리키는 인스턴스 변수명 person[0],[1],[2]로 방 3개 생김, null로 초기화 
	//	} -> 생성자니까 가장 먼저 호출됨, Person타입 메모리배열 생성, [Person@0x5678 -> 메모리는 AcademyLogic안의 person이라는 이름으로 가리킴,
	//방은 person[0],[1],[2] 이름, Person 타입 클래스니까 참조형, null로 초기화, person 작은방에 연결된 배열
	//AcademyLogic에 printMainMenu()등등 메소드도 있음
	private void setPerson(int subMenu) {
		//정원이 찼는지 여부 판단
		int index= -1; //채워지지 않은 Person형 배열(null인 배열요소)의 인덱스를 저장할 변수
		for(int i=0;i<MAX_PERSON;i++) {
			if(person[i]==null) {//null이면 index에 i값을 넣어줌
				index=i; //맨처음에 person[0]은 0저장
				break;
			}
		}//안에 주소가 들어있으면 null 아니니까 index가 -1이 됨
		if(index==-1) {//이미 정원이 찬 경우를 의미하는 것
			System.out.println("정원이 찼어요.. 더 이상 입력할 수 없어요");
			return;//바로 메소드 빠져나감
		}
		//정원이 덜 찬 경우, 즉 index가 -1이 아닌경우...
		Scanner sc=new Scanner(System.in);
		System.out.println("이름을 입력하세요?");
		String name=sc.nextLine().trim(); //이름입력
		System.out.println("나이를 입력하세요?");
		int age=Integer.parseInt(sc.nextLine().trim());
		//학생과 교사를 구분해서 입력받기] -> subMenu로 인자 받음
		switch(subMenu) {
			case 1://학생인경우, 학번 추가입력받아야함, -> 1번 입력시, 스튜던트 타입 메모리 만들어서 주소를 넣는다!*****
				System.out.println("학번을 입력하세요?");
				String stNumber=sc.nextLine().trim();
				person[index]=new Student(name,age,stNumber);//주소니까 new 하면 들어감, index로 위에서 받음
				break;
				//Student@0x1111새로 만들고, 내부에 Person(name,age,get(),print())은 상속받음, 
				//상속말고 Student안에 있는건 stNumber, get(), print() 오버라이딩 메소드 2개
				//Student@0x1111 주소는 person[0],[1],[2] 이런 식으로 넣을 예정
			default:
				System.out.println("과목을 입력하세요?");
				String subject=sc.nextLine().trim();
				person[index]=new Teacher(name,age,subject);
				//데이터 넣을 때는 name, age(얘네는 Person상속안의 더 작은방), stNumber 혹은 subject(얘네는 새로만든 작은방)에 들어감		
		}
	}////setPerson
	
	private void printPerson() {
		System.out.println("[학생/교사 구분없이 출력]");
		for(int i=0;i<MAX_PERSON;i++) {//MAX_PERSON 최대3
			if(person[i]!=null) person[i].print(); //0번방부터 2번방까지 i이동, 근데 다 입력한게 아니면 null 입력시 null.print? 에러남, 다 입력하면 주소.print() <-오버라이딩 된걸로 정상출력
		}
/*		//문제] 학생과 교사를 구분하여 출력하여라
 *  	//학생방식
		System.out.println("[학생목록]");
		for(int i=0;i<MAX_PERSON;i++) {		
			if(person[i]!=null&&person[i] instanceof Student) { // i번째 방이 null이면 instanceof false 나와서 아래 실행x, person[i]!=null 없어도 됨
				person[i].print();
			}
		}	
		System.out.println("[교사목록]");
		for(int i=0;i<MAX_PERSON;i++) {		
			if(person[i]!=null&&person[i] instanceof Teacher) {
				person[i].print();
			}
		}	
*/		//선생님방식
		StringBuffer student=new StringBuffer("[학생목록]\r\n"); //****
		StringBuffer teacher=new StringBuffer("[교사목록]\r\n");
		for(int i=0;i<MAX_PERSON;i++) 
			if(person[i]!=null) 
				if(person[i] instanceof Student)
					student.append(person[i].get()+"\r\n"); //get 오버라이딩 해놓음, 줄바꿈은 추가
				else
					teacher.append(person[i].get()+"\r\n");
		System.out.println(student.toString()+teacher); //위의 학생방식보다 1/2 연산, 지금 코드는 뜻 모르겠음***** 왜 티쳐는 toString안하지
	}////printPerson
	//학번은 유니크, 있거나 없거나, 확실한 구분 가능함
	//선생님은 구분할만한 key로 사용할게 없음, 그냥 그렇다~, 이름으로 검색한다고하자
	//수정, 삭제 전에 검색부터 할 생각, 검색할 애가 발견 시 수정 또는 삭제, 발견안되면 없습니다~
	//7]이름으로 검색하는 메소드, 매개변수:String(수정,삭제,검색용 타이틀) -> ex. 수정(삭제,검색)할 이름을 검색하세요, 반환타입:Person타입(선생님맘), Person형 배열에 저장된 주소를 반환할 것
	private Person findPersonByName(String title) {
		System.out.println(title+"할 사람의 이름을 입력하세요?");
		Scanner sc=new Scanner(System.in);
		String name=sc.nextLine(); //name변수에 사용자가 입력한 이름을 담음, 이걸 person[i]번 방이 가리키는 Person안의 name과 비교, 같으면 주소(Person타입)를 반환하면 됨
		for(int i=0;i<MAX_PERSON;i++) //중괄호 안썼대
			if(person[i]!=null) //null체크 필수, null이 아니어야함
				if(person[i].name.equals(name)) //equals로 문자열 비교
					return person[i]; //사람이 있으면 주소를 반환
		System.out.println(name+"로(으로) 검색된 정보가 없어요"); //찾는사람 없어야 여기로 나옴, null반환
		return null; 
	}////findPersonByName
	
	//8] 검색용 메소드
	private void searchPerson() {
		Person findPerson=findPersonByName("검색"); //findPersonByName으로 주소를 받아서 findPerson에 담음, 이렇게하면 검색할 사람의 이름을 입력하세요 나옴, Person타입으로 반환하므로 Person에 담아야함, 주소를 findPerson에 담음
		if(findPerson!=null){//null이 아니면, 사용자가 입력한 정보가 찾아진것, 출력해주면됨
			System.out.printf("[%s로 검색한 결과]%n",findPerson.name); //findPerson은 메소드 안에서 선언한 지역변수, 스택에 findPerson메모리 생김, 0x2222저장, 얘도 Teacher@0x2222가리킴, 여기의 name 가리킬 수 있음*****
			findPerson.print();
		}
	}////searchPerson
	
	//9] 수정용 메소드
	private void updatePerson() {
		Person findPerson=findPersonByName("수정");//findPersonByName으로 주소를 받아서 findPerson에 담음
		if(findPerson!=null) {//수정할 사람의 이름이 발견된 것
			Scanner sc=new Scanner(System.in); //나이, 학번 또는 과목 다시 입력받는 것, 그걸 아래에 넣음
			//나이수정
			System.out.printf("(현재 %s살) 몇 살로 수정할래?%n",findPerson.age);
			findPerson.age=Integer.parseInt(sc.nextLine().trim()); //입력받은 나이로 바꿈
			//학생인지 교사인지 판단
			if(findPerson instanceof Student) {//Student타입의 인스턴스인지 확인
				System.out.printf("(현재 %s) 몇 학번으로 수정할래?%n",((Student)findPerson).stNumber); //자식에서 새롭게 정의된 subject, stNumber는 볼 수 없음, person과 findPerson은 Person타입이라 상속받은 Person을 가리키는중, 형변환해야함
				((Student)findPerson).stNumber=sc.nextLine().trim();
			}//if면 학생
			else {
				System.out.printf("(현재 %s) 무슨 과목으로 수정할래?%n",((Teacher)findPerson).subject);
				((Teacher)findPerson).subject=sc.nextLine().trim();
			}//else면 교사
			System.out.printf("[%s(이)가 아래와 같이 수정되었습니다]%n",findPerson.name);
			findPerson.print();//수정내용을 확인하기 위한 출력
		}
	}////updatePerson
	
	//10] 삭제용 메소드
	//문제] 삭제처리하시오, 삭제할 사람의 입력 가길동 입력 시 Person 안의 이름과 비교, 주소를 null처리하면 됨, 그러면 person[0]이 스튜던트나 티쳐랑 연결 끊김, findPerson은 연결 남음,findPerson을 null처리하면 안됨
	private void deletePerson() {
		Person findPerson=findPersonByName("삭제"); //~~할 사람의 이름을 입력하세요?
		if(findPerson!=null) {//삭제할 사람의 이름이 발견된 것
			for(int i=0;i<MAX_PERSON;i++) {
				if(findPerson.equals(person[i])) {
					person[i]=null;  //findPerson의 주소랑 person의 주소를 비교, 그리고 null바꾼 후 break하면 됨
					System.out.printf("[%s가 삭제되었습니다]%n",findPerson.name);
					break; //메소드호출 끝나면 findPerson도 사라짐
				}////if
			}////for
		}////if
	}////deletePerson
	
	//과제] 금요일까지, new->JAVA프로젝트 한다음에 이름 만들고 콘솔 아카데미 패키지 카피, 만든 src에 콘솔 아카데미 패키지 복사하면 됨
		
}////class
