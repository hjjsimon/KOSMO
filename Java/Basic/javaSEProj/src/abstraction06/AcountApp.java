package abstraction06;
//은행계좌를 추상화하자]

class Account{
	//[멤버 변수]
	String name;//예금주
	String accountNo;//계좌번호
	int balance;//잔액
	
	//[멤버 메소드]
	
	//입금 출력용]
	void deposit(int money) {
		balance+=money; //입금시 잔액누적
		System.out.println(money+"원이 입금되었습니다");
	}///////
	
	//출금 출력용]
	void withDraw(int money) {
		if(balance<money){//잔액보다 큰 돈은 인출불가
			System.out.println("잔액이 부족해요");
			return; //메소드 빠져나감, 아래줄 진행x
		}
		balance-=money;//balance<money 거짓이면 실행
		System.out.println(money+"원이 출금되었습니다");
	}////////
	
	//통장정보 출력용]
	void printAccount() {
		System.out.printf("[%s님의 계좌정보]%n계좌번호:%s%n잔액:%d원%n",name,accountNo,balance);
	}
	
	
}//////////////



public class AcountApp {

	
	
	public static void main(String[] args) {

		//통장개설] Account 타입의 메모리를 만듦
		
		Account ac1=new Account();
		System.out.println("[멤버변수 초기화전]");
		ac1.printAccount(); //ac1으로 가리키니까, ac1으로 접근
		
		/*
		 stack에 ac1, heap에 Account타입의 메모리(큰방)가 생성(Account@x1234)
		 설계도 보면 변수3개 name(스트링), accountNo(스트링), balance(인트) 메모리(작은방) 만들어짐
		 메소드는 3개 deposit(), withDraw(), printAccount() 만들어짐
		 주소는 stack의 ac1에 저장, 0x1234
		 */
		
		//[멤버변수 초기화] 값들 세팅
		ac1.accountNo="123-456";
		ac1.balance=1000;
		ac1.name="가길동";
		System.out.println("[멤버변수 초기화후]");
		ac1.printAccount();
		
		//인출]
		ac1.withDraw(15000); //if참에 걸려서 인출불가
		//출금]
		ac1.withDraw(500); //if거짓이라 500원 출금
		//통장정리]
		ac1.printAccount(); //통장정보 출력
		//입급]
		ac1.deposit(10000); //10000원 넣음
		//통장정리]
		ac1.printAccount(); //10500원됨
		
		Account ac2=new Account();
		ac2.name="나길동";
		ac2.accountNo="567-123";
		ac2.balance=10000;	
		ac2.printAccount();
		
		/*
		 스택에 ac2, 힙에 Account@5678 큰방메모리 똑같은 구조로 생김, 변수랑 메소드 3개씩 초기화까지 완료
		  */
		
		
		
		
	}

	
}
