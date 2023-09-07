package encapsulation13;

public class EncapsulationDAO {

	//EncapsulationDTO에 저장된 데이터를 가공처리하는 로직을 갖는 클래스
	//초기화로직, 입금하는로직, 인출하는로직, 통장정보를 출력하는 로직
	
	//[멤버변수]
	private EncapsulationDTO capDto=new EncapsulationDTO(); //private으로 숨김
	
	
	//[멤버메소드] 
	//1)초기화
	public void initialize(String name, String accountNo, int balance) {
		//capDto. 해도 다른 곳에서는 아무것도 안 뜸, 접근 안됨 get, set 필요, 이 메소드를 다른 곳에서 호출해야함
		capDto.setName(name);
		capDto.setAccountNo(accountNo);
		capDto.setBalance(balance);
	}///////initialize
	
	//2)입금로직
	public void deposit(int money) {
		//balance+=money; balance에 접근 불가라 이걸로 적립 불가
		//getBalance에 머니를 더함
		capDto.setBalance(money+capDto.getBalance()); //기존 1억 + 10원
		System.out.println(money+"가 입금되었습니다");
		print();
	}///////deposit
	
	//3)인출로직
	public void withDraw(int money) {
		if(money>capDto.getBalance()){//잔액 충분해야 인출가능하게 비교, 읽어오는거니까 getBalance!
			System.out.println("잔액이 부족합니다");
			return;			
		}
		capDto.setBalance(capDto.getBalance()-money); //기존 1억 + 10원
		System.out.println(money+"가 출금되었습니다");
		print();
	}////////withDraw
	
	//4)통장정보출력
	public void print() {
		System.out.println(String.format("[%s님의 계좌정보]%n계좌번호:%s%n잔액:%s", 
				capDto.getName(),
				capDto.getAccountNo(),
				capDto.getBalance()
				));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
