package thread23;
/*
메소드 동기화: 여러 스레드에 의해 호출되는 공유 메소드를 동기화 함으로써 여러 스레드가 동시에 호출 못하도록 lock을 거는 것
[형식]
	  접근지정자 synchronized  반환타입 메소드명([매개변수]){ ->지정자 synchronized 빠지면 그냥 메소드임, 접근지정자랑 순서바꿔도됨, 여튼 싱크로나이즈드 쓰면 메소드가 동기화됨
	}

->Thread상속받은 클래스 MyThread, 내부에 스레드 만듦, 스레드 3개 만들어서 start()함, 인간눈에 동시에 3개 작업으로 보이지만 1개씩 빠르게할뿐, start로 3개 모두 runnable상태빠짐  
->NotThread클래스도 있음, run이 NotThread의 메소드를 호출한다고함, 이 메소드가 근데 오래걸림, 수행하다말고 runnable로 넘어갈 수 있음, 다른게 running으로 들어옴,
->스레드3개가 메소드를 공유함, 그러면 또 처음부터 메소드 수행함, 이럴때 lock걸어야함, 그러면 다른 스레드가 호출할 수 없음
*/
//여러 스레드가 공유하는 메소드를 가진 클래스-인스턴스 하나만 만듦(각 스레드에 인스턴스 전달)
class MethodSyncClass{
	int seed;//얘도1개생김 -> // seed를 공유한게 아니라 메소드를 동시에 같이 호출한것
	public MethodSyncClass(int seed) {
		this.seed = seed;
	}
	//동기화시 synchronized 지정자 메소드에 추가]
	synchronized void increase(int inc) {//seed에 인자로 전달된 inc만큼을 증가시킬것
		for(int i=1;i<=10;i++) {
			seed+=inc;
			System.out.println(String.format("[스레드명:%s,데이터:%s,i=%s]",Thread.currentThread().getName(),seed,i));
			try {Thread.sleep(1000);}
			catch (InterruptedException e) {e.printStackTrace();}
		}
	}
}
//[공유메소드를 호출하는 스레드]
class MethodSyncThread extends Thread{
	//멤버변수
	MethodSyncClass msc;
	//스레드마다 다르게, 일정하게 증가시킬 숫자를 저장할 멤버
	int inc;
	//인자생성자
	public MethodSyncThread(MethodSyncClass msc, int inc,String threadName) {//초기화
		super(threadName);//스레드명으로 쓸 것
		this.msc = msc;
		this.inc = inc;
	}
	@Override
	public void run() {
		msc.increase(inc);
	}

}

public class MethodSynchronized {

	public static void main(String[] args) {

		//공유메소드 갖고있는 클래스, 하나만 인스턴스화]
		MethodSyncClass msc=new MethodSyncClass(10);//seed값 10 넣어줌 -> 지금 메모리1개!!!!!! 메소드 1개 공유할때는 문제 생기니 동기화 필수!
		//두개의 스레드 생성->같은 메소드를 번갈아가며 호출
		MethodSyncThread mst1=new MethodSyncThread(msc, 2, "1st 스레드");//msc에 주소들어감
		mst1.start();		
		MethodSyncThread mst2=new MethodSyncThread(msc, 5, "2nd 스레드");//msc에 주소 같이 들어감
		mst2.start();	
		/*
		 그림설명
		 MethodSyncClass@0x1234메모리가 하나만 만들어짐, 안에 seed,10으로 초기화, increase(inc값받음) 메소드도 있음
		 스레드는 2개 mst1,2만듦, msc에는 주소가 저장, 스레드에 같은 주소 넘김, 각 스레드의 방 중 msc에는 0x1234 들어있어서 MSC@0x1234가리킴
		 스레드의 방 중 inc에는 각각 2랑 5가 들어감, 하나는 2씩증가 하나는 5씩증가,
		 아니 근데 seed가 10이 아니고 17? 처음에 mst1이 아니라 mst2가 일한거임, 둘 다 러너블상태인데, mst2가 increase호출, seed에 5를 증가시킴, MSC@0x1234안에 seed15됨, 
		 그리고 mst1이 일을해서 seed가 17되고 출력한것, 근데 두번쩨 mst2는 왜 17출력? -> 아 seed+=5하고 그 다음에 sysout출력해서 또 17인것
		 아~ seed+=inc;랑 System.out.println(String.format("[스레드명:%s,데이터:%s,i=%s]",Thread.currentThread().getName(),seed,i));가 연속진행x
		 덧셈만하고 끝나기도 하고 출력까지하고 끝나기도하고 그런것. 중간에 멈추면 다른 스레드가 running 뭐 갑자기 멈추고 다른놈 진행하고~ 그런것
		 위에 메소드에 싱크로나이즈드 해주면 중간에 멈추고 다른 스레드가 끼어들 수 없음
		 1스레드가 2씩 증가시키고, 그게 끝나야 2스레드가 5씩 증가, 1번이랑 2번 스레드중 누가 먼저할지는 모름
		 
		 String은 데이터 불변, StringBuffer랑 StringBuilder는 데이터 변경-> 스레드가 데이터 변경시키면 문제가능, 멀티스레드에서는 동기화해놓은 buffer가 안전, 단일스레드에서는 동기화 안해놓은것도 갠찬 builder 
		 */
		
		
	}//////main

}//////////class
