package encapsulation13;

public class EncapsulationDTO {

/*	[은닉화(Encapsulation)]

			- 멤버변수를 캡슐화하되 멤버변수에 접근할 때는 
			  메소드로 접근하도록 한다.

			- 캡슐화 방법
				1. 멤버 변수 앞에 private 접근 지정자를 붙인다.
				   예] private int balance;

				2. 멤버 변수에 값을 할당하고 값을 꺼내올 수 있는 
				   메소드를 만든다.
				   2.1 setter메소드.
					      메소드의 접근 지정자는 public 반환타입은 없고, 매개변수를 받아들이도록 한다
					      setter메소드의 역할:
								 매개변수의 값을 멤버변수에 할당하는 일.
								public void setBalnace(int money){     //B 대문자로 써주는게 그냥 규칙, setBalance는 퍼블릭이라 이제 다른 곳에서 접근가능
									balance=money;
								}
					
				   2.2 getter메소드
				             메소드들은 반환타입이 있되 매개변수는 받아들이지 않는다.
					     getter메소드의 역할:
								멤버변수 설정된 값을 반환해주는 일

								public int getBalance(){
										return balnace;
								}
*/
			//캡슐화(=은닉화), 캡슐안에 넣어서 안보이게 하자는 뜻, 2가지 의미 있음
			//첫번째의미: private 접근지정자 쓰면 외부에서 안보임, 이것도 캡슐화의 하나 ex. private int num -> 이 num은 다른 클래스에서 못가져감
			//Getter(값 읽기용, 메소드명에 get을 붙이고, getNum(int받음), return Num;)
			//Setter(값 설정용(쓰기용), 메소드명에 set, setNum, return 안함, void) 
			//위에 놈들 -> 외부에서는 접근해야하므로 접근지정자 public한 메소드, 
			//두번째의미: 같은 성질의 그룹을 묶음
		
	//[멤버변수]	
	private String name;//예금주
	private String accountNo;//계좌번호
	private int balance;//잔액
		
	//Getter
	public String getName() {//인자는 필요없음 // 이거 왜 중괄호 못없애지?
		return name; 
	}
	public String getAccountNo() {
		return accountNo;
	}
	public int getBalance() {
		return balance;
	}
	
	//Setter
	public void setName(String username) {//반환은 필요없음
		name=username; //name에 값 설정
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	//우클릭 소스, getter 자동 만들기 있음/////////위에 잘 만들어졌는지 소스확인
	
	
	
	
	
	
	
	
	
	
		
	

}
