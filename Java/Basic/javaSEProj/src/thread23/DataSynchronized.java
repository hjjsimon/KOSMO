package thread23;
//String은 데이터 불변, StringBuffer랑 StringBuilder는 데이터 변경-> 스레드가 데이터 변경시키면 문제가능, 멀티스레드에서는 동기화해놓은 buffer가 안전(lock걸리니까), 
//단일스레드에서는 동기화 안해놓은것도 갠찬 builder->성능상 유리함 
//동기화 블락을 이용한 데이타 동기화
//동기화 블락: -> 블락을 만들어줘야함, 블락이니까 {} 중괄호 필요
/*
	synchronized(공유 메모리를 갖고 있는 객체){ -> 힙영역에 생성된 객체를 넣어줌
		동기화할 로직
	}
*/
//여러 스레드가 공유하는 데이터(메모리)를 갖는 클래스-인스턴스는 똑같이 하나만 만들고, 각 스레드에 인스턴스를 전달한다
class DataSyncClass{
	int shareData;//여러 스레드가 공유하는 메모리, 아까 seed를 공유한게 아니라 메소드를 동시에 같이 호출한것 -> 메모리1개 가짐, 공유/ 아까는 메소드를 1개 공유
	public DataSyncClass(int shareData) {
		this.shareData = shareData;
	}
}
//공유데이터를 사용하는 스레드
class DataSyncThread extends Thread{
	//멤버변수]
	//공유할 데이터를 갖고있는 DataSyncClass타입의 멤버
	DataSyncClass dsc;
	//일정하게 증가시킬 숫자를 저장할 멤버
	int inc;
	//인자생성자]
	public DataSyncThread(DataSyncClass dsc, int inc,String threadName) {
		super(threadName);
		this.dsc = dsc;
		this.inc = inc;
	}
	//오래 걸리는 메소드] 이번에는 스레드의 메소드에서 증가시킴
	//DataSyncClass의 shareData에 저장된 값을 반복하면서 inc만큼 누적해서 계속 저장
	void increase() {
		for(int i=1;i<=10;i++) {
			dsc.shareData+=inc; //dsc의 쉐어데이터를 누적합
			System.out.println(String.format("[스레드명:%s,공유데이터:%s]",getName(),dsc.shareData));//이미 Thread니까 겟네임만하면됨 
		try {sleep(1000);} 
		catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	@Override
	public void run() {
		//데이터 동기화전]
		//increase();
		//데이터 동기화후]
		synchronized (dsc) {//공유데이터 갖는 dsc넣음
			increase();
		}
	}
}
public class DataSynchronized {

	public static void main(String[] args) {
		//공유메모리 갖고있는 클래스, 하나만 인스턴스화]
		DataSyncClass dsc=new DataSyncClass(10);
		//2개의 스레드 생성]
		DataSyncThread dst1=new DataSyncThread(dsc, 2, "1st 스레드");
		dst1.start();
		DataSyncThread dst2=new DataSyncThread(dsc, 5, "2nd 스레드");
		dst2.start();
		
	}////////////main

}///////////////class
