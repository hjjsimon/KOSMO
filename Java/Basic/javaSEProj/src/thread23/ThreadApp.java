package thread23;
/*
//스레드명이 main, 1개면 단일스레드, 지금껏 하던것
//자바는 스레드를 여러개 실행시킬 수 있음, 멀티스레드 환경을 제공함(자바스크립트는 단일스레드)
//ex. 경주마 4마리, 4개 객체 만듦, 피니시라인이 있음, 1번말 런, 2번말 런...4번까지 런
//Horse라는 클래스를 만들고 run메소드로 시행, 피니시라인 도착시 stop하게 만들어놓음
//단일(메인)스레드에서는 1번run해서 달린 후 피니시라인도착, 그래야 2번말 run함
//런이라는 메소드를 스레드로 만들면 Horse객체를 4개 생성, 메인까지 이 프로그램 실행하려면 5개 스레드 필요
//그러면 1번,2번,3번,4번말 동시에 출발하는 것처럼 스레드로 만들 수 있음
//jvm에 스케쥴러가 있음, CPU(수행할 시간)를 확보해야 일을함, 스케쥴러가 1번말한테 런실행시키다가 멈추게함, 그리고 2번말 달리게하다가 멈추게함, 근데 이 시간이 너무 짧아 사람눈에는 동시수행처럼 보임
//시간이 오래걸리는 작업은 보통 스레드로 구현함 -> while(true){} 스레드로 무한루프 구현해도 다운안됨, 스레드 스케쥴러가 일하라고할때만 무한반복하니까 갠찬!

 [스레드]
	1]스레드란
	  -하나의 프로세스 안에서 실행되는 명령 흐름 즉 프로세스 안의 작은 프로그램이다
	    메서드(=스레드, 근데 정확히 메소드가 스레드는 아님, 단 메인메소드는 처음부터 스레드로 동작하게끔 자바만든애들이 만들어놓음)로 보면 됨(명령흐름=메소드 비슷). ->프로그램과 프로세스 다름, 계산기 같은게 프로그램, 눌러서 모니터상에 보이면 그게 메모리상에 올라와서 실행하는것, 실행된 프로그램이 프로세스(메모리에 올라온것!!)임
		쉽게 이해, 실행된 하나의 프로그램(=프로세스)
	  -자바는 멀티 스레딩을 지원하는데 이를 지원하기 위해 Thread클래스와
	    Runnable인터페이스를 제공한다->스레드클래스, 러너블인터페이스 
->하나 클릭했을때 컴터 먹통되면 cpu를 다 잡아먹은것, 프로그램을 스레드로 안만들어서 그런것
메소드가 스레드가 되려면 스레드 클래스를 상속받아야함, 러너블인터페이스는 굳이 왜? 아 단일상속이라 이미 메소드 포함한 클래스가 다른 클래스 상속시 인터페이스로 임플리먼트시키는것
벡터하고 어레이리스트 두 차이 동기화, 스트링빌더랑 스트링버퍼 차이도 동기화
->스레드의 상태전이그림교안, start()하면 실행가능해짐 아직 실행은x, 스케줄러가 cpu시간을 쓰게해주면 실행
->실행시 run(){코드} 런메소드 안의 코드를 실행함, 후에 스레드 종료,
->스레드 안의 메소드 sleep(3000) 쓰면 3000*1/1000=3초 쉼, 런 내부의 코드 실행하다가 멈춰서대기(wait)
->3초후에 바로실행은x, 실행가능상태로 감
->wait()쓰면 wait 후 notify()써야 러너블로 간다고는 하는데 보장x, 내 스레드 여러개 돌리면 2번을 러너블(실행가능)로 하고싶은데 1~4번 중 스케줄러 맘대로 하나 실행가능상태로 만듦
->notifyAll()쓰면 4개 스레드 모두 실행가능상태로감, 실행가능상태에 들어간 스레드 많으면, 스케쥴러 바쁘고, cpu잘게 나눠써야해서 안좋음, 그래서 슬립씀
->이런 상태(실행가능->실행->종료 또는 대기)를 스레드의 상태전이라고함

	  멀티스레딩 이란?
		- 하나의 프로세스 안에서 여러 개의 스레드가 동시 작업하는 것.
		  즉 한 프로그램 내에서 두가지
		  이상의 일을 수행하는 것
		- main메서드(프로그램 진입점)를 가진 자바 프로그램은 
		  하나의 스레드를 가진(main스레드) 단일 스레드라고 
		  볼 수 있다.
		  Thread클래스나 runnable인터페이스를 상속받아 다른 스레드를 
		  동작 시킬 수 있다.
 
 */

//스레드로 구현하지 않은 클래스]
class NotThread{
	String title;
	public NotThread(String title) {
		this.title = title;
	}
	//스레드로 구현하지 않은 메소드
	void notThreadMethod() {
		//아래로직이 오랜 시간이 걸리는 작업이라고 가정
		for(int i=1;i<=10;i++) {
			System.out.println(String.format("%s]i=%d",title,i));
			try {
				Thread.sleep(1000);
			}//1초를 쉬는것, 한번에 콘솔에 찍힘 너무 빠름, 찍고 1초 쉼
			catch (InterruptedException e) {e.printStackTrace();}
			//에러안나면 여기로 내려옴
		}
	}
}///////class NotThread
//스레드로 구현한 클래스]
//1]Thread클래스를 상속받아 스레드 구현
class YesThread extends Thread{
	public YesThread(String threadName){//필요에 의해 만든 인자생성자, 받은걸 스레드명으로 쓸 것, 기본생성자로 만들면 알아서 스레드명 만들어짐
		super(threadName);//부모한테 만들어달라고함
	}
	void threadMethod() {//복붙
		//아래로직이 오랜 시간이 걸리는 작업이라고 가정
		for(int i=1;i<=10;i++) {
			System.out.println(String.format("%s]i=%d",getName(),i)); //getName()으로 받음, 상속받았으니까 바로 가능
			try {
				sleep(1000);//sleep(천분의1초); -> 스레드를 1/1000초동안 wait상태로 빠지게하는 메소드, 1/1000초 후에는 다시 runnable상태로 돌아감
			}//상속받은거라 sleep갖고있음, Thread.sleep()에서 Thread 붙일 필요 없음
			catch (InterruptedException e) {e.printStackTrace();}
			//에러안나면 여기로 내려옴
		}
	}
	//2]run()메소드 오버라이딩
	//run()메소드 안에 시간이 오래걸리는 작업기술
	//개발자가 직접 호출하는게 아님, 스레드가 Running상태로 들어갔을때 자동으로 호출되는 메소드(자동호출=콜백메소드)
	@Override//우클릭->스레드의 런메소드 오버라이딩
	public void run() {//시간이 오래 걸리는 작업을 run()안에서 구현하면됨
		threadMethod();
	}
}////////class YesThread
/*
독립 스레드(Non Daemon 스레드): -> 지금까지 만든 main,yt1,yt2 얘네는 독립스레드(non daemon스레드)ex.yt1,2는 main스레드 끝나도 작동가능, 독립이라 무관
    메인스레드와 working스레드(개발자가 만든 스레드) ->yt1,2 의미
메인스레드가 끝나도 종료되지 않고 스레드가
Dead상태 될때까지 계속 실행되는 스레드

종속 스레드(Daemon 스레드): ->종속스레드로 만들면, 독립스레드에 종속적으로 만듦, 
모든 독립스레드가 끝나면 자동으로 종료(Dead)가 되는 스레드
-주 스레드(독립스레드)의 보조역할을 하는 스레드
-종속 스레드는 주로 무한루프로 구성한다.
-예]배경음악 깐다든지,10분마다 자동 저장한다든지 등등..

어떤 스레드를 종속 스레드로 만들려면 setDaemon(true)로 설정 -> 디폴트가 false임, setDaemon메소드 안에 true 들어가면 종속
*/
//종속스레드로 사용할 스레드]
//1]Thread 클래스 상속
class DaemonThread extends Thread{
	@Override
	public void run() {//보통 무한루프 종속시킴, 스레드로 만들면 무한루프도 다운안됨
		while(true) {
			System.out.println(String.format("%s]배경 음악이 흘러요",getName()));//getName()에는 스레드명
			System.out.println(String.format("%s]3초마다 저장",getName()));
			try {
				sleep(3000); //오버라이드되어서 던질 수 없음, 던지면 오버라이드 규칙에 어긋남
			} 
			catch (InterruptedException e) {e.printStackTrace();}
		}
	}
}////////////class DaemonThread


public class ThreadApp {

	public static void main(String[] args) throws InterruptedException {

/*		System.out.println("main 스레드 시작");//디폴트로 메인은 스레드로 만들어짐, 스레드명은 메인
		//스레드로 구현하지 않은 클래스 테스트]
		NotThread nt1= new NotThread("1st클래스");
		NotThread nt2= new NotThread("2nd클래스");
		nt1.notThreadMethod();//얘네는 스레드 아님, 메인스레드가 이 코드 실행시 1~10출력
		nt2.notThreadMethod();//메인스레드가 다음에 이 코드 실행시 1~10출력, 그냥 지금껏 하던대로 끝남
		System.out.println("main 스레드 끝");
*/		
		
		System.out.println("main 스레드 시작");
		YesThread yt1=new YesThread("1st 스레드");//스래드명 넣어줌
		YesThread yt2=new YesThread("2nd 스레드");
		yt1.setName("첫번째 스레드");//스레드명 바꾸는 메소드
		yt1.start();//스레드를 Runnable상태로 전이시킴, 스레드한테 상속받은거 씀, start하면 Runnable상태로 돌입, 스케쥴러가 명령해야 run()메소드 안의 코드가 실행됨, 아직 실행되는건 아님
		//join()메소드: 1]start()호출후에 join()을 호출해야한다, 2]join()메소드를 호출한 스레드가 다 실행이 끝나서 Dead상태가 돼야 다른 스레드가 작동한다
		//yt1.join();//두번째 스레드가 일할수도 있는데, 메인스레드 시작->첫번째스레드->첫번째스레드....->메인스레드끝->
		//main은 runnable방으로(방으로 생각) 빠지고, yt1이 running으로 들어온것, 
		//조인걸면 스레드가 종료될때까지 다른 스레드는 작업을 못하게함, 이게 끝나면 첫번째 스레드 yt1은 dead상태로 돌입, 이후 runnable에 있던 main이 다시 running됨
		//이후 running으로 yt2나와서 2nd스레드 출력, 갑자기 또 yt2는 runnable, main은 running으로~ 뭐 이런 느낌
		//스레드의 우선권 설정] 우선순위가 높다고 그 스레드가 먼저 실행된다는 보장은 없다(확률이 높음)
		yt2.setPriority(Thread.MAX_PRIORITY); //보통 스타트 하기전에 우선권 설정함, 2번에 맥스우선권 줘도 1스레드 먼저 시행가능
		yt2.start();
		
		DaemonThread daemonThread=new DaemonThread();
		daemonThread.setName("Daemon Thread");//기본생성자로 만들었으니 setName으로 이름 설정
		daemonThread.setDaemon(true);//종속스레드설정 ->yt1,2 독립스레드 끝나면 종료됨 ->코드 주석처리하면 독립스레드니까 3초마다 계속 출력됨, 여튼 종속이라 3초마다 실행, yt1,2각 3번 출력후 
		daemonThread.start();//러너블
		
		System.out.println("현재 활성화 상태(Runnable 또는 Running상태 의미)에 있는 스레드 수:"+Thread.activeCount()); //activeCount, 메인스레드(러닝상태)랑 yt1,2(러너블2개) 총 3개가 활성화상태로 찍힘
		System.out.println("첫번째 스레드 우선권:"+yt1.getPriority()); //스레드가 가질 수 있는 최대 우선권이 있음, 확률상 1,2,3번 스레드중 1번이 젤 크다고치면, 1번이 러닝상태에 전이될 확률 높음, 1번이 우선권이 높다고함, 근데 100%러닝상태로 돌입될 보장은x, 다른 프로그램에도 스레드가 많음, 결정은 스케쥴러 맘대로 걍 시킴 
		System.out.println("두번째 스레드 우선권:"+yt2.getPriority());//값 설정 안하면 디폴트, 최대 10, 최소 1, 디폴트(norm,normal) 5로 우선권 줌
		System.out.println("현재 실행중(Running)중인 스레드:"+Thread.currentThread().getName()); //main 실행나옴
		System.out.println("main스레드의 우선권:"+Thread.currentThread().getPriority());//설정 안했으니 5, 실행중인 스레드의 우선권 반환
		System.out.println("main 스레드 끝");
		/*	main 스레드 시작 -> 얘네는 메인스레드가 일을 시킨것, 그리고 yt1메인스레드가 실행시킴, 스레드가 만들어짐, yt2도 동일, 이후 yt1,2 start는 러너블상태로전이, 그리고 메인스레드 끝
		  	main 스레드 끝 -> 메인스레드 일이 끝난건 메인스레드끝 출력이 끝! -> 메인스레드가 끝났는데(dead상태 된것!) 종료가 안되고 2nd, 1st 출력이 계속 됨, 
			2nd 스레드]i=1 -> 메인스레드 dead되고 2번째 스레드가 일을 한것, 그리고 1초 쉬면 wait갔다가 runnable로 이동, 그사이 runnable상태에 있던 1이 일한것, 그리고 1초 쉼
			1st 스레드]i=1
			1st 스레드]i=2
			2nd 스레드]i=2
			1st 스레드]i=3
			2nd 스레드]i=3      이런식으로 실행, 스레드 1st,2nd 뭐가 먼저 실행될지는 스케쥴러맘, 할때마다 무작위
*/
	}

}
