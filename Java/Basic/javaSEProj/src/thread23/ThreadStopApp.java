package thread23;

class ThreadStop extends Thread{
	//플래그변수 이용-비추천] 안멈출수도 있음
//	boolean isFlag; //false기본초기화
	@Override //우클릭 없이 run()컨트롤엔터치면 바로 오버라이딩됨
	public void run() { 
/*		//플래그 변수 사용시(비추천)-스레드가 멈추지않을 수 있다
		//isFlag를 true로 설정한다하더라도 while문 안의 모든 명령문의 실행이 끝나야 다시 반복조건을 검사하니까
		int i=1;
		while(!isFlag) {//true로 무한루프
			System.out.println("i="+i++);//true인동안 계속 i++
			try {
				sleep(1000);
			} 
			catch (InterruptedException e) {e.printStackTrace();}
		}
		
	}*/
		//[interrupt()메소드 호출(추천)-InterruptedException예외 발생시키기 위해서]
		//스레드 작업중간에 멈출 수 있다(while 내부명령은 얼마나 오래걸릴지 모름), InterruptedException 발생하면 무조건 catch절로 들어가니까!
		int i=1;
		try {
			while(true) {//true로 무한루프
				System.out.println("i="+i++);//true인동안 계속 i++
				sleep(1000);
			}
		}
		catch (InterruptedException e) {System.out.println("interrupt()호출");}//
		finally {//여기로 내려오면 더 실행할 코드 없음
			System.out.println("스레드 Dead상태");
		}
	}////run()	
}////ThreadStop

public class ThreadStopApp {

	public static void main(String[] args) {
		
		ThreadStop thread=new ThreadStop();
		thread.start();
		//main스레드를 5초동안 Wait상태로 전이
		try {
			Thread.sleep(5000);//메인스레드를 5초 wait시킴, 메인스레드 무관하게 Thread는 i++진행
		} 
		catch (InterruptedException e) {e.printStackTrace();}
		//플래그 이용] boolean형 플래그 변수사용-비추천 -> while안의 작업이 다 끝나야 while(true)인지 다시 확인, while내부 작업이 끝나야하는게 문제, 내부작업 중간에 못멈춤
		//thread.isFlag=true; //메인스레드가 5초 후 Running상태에서 이 코드 실행, true되면 위에 while(false)됨, run()스레드는 종료하게됨, i=1,2,3,4,5 하고 꺼짐
		if(thread.isAlive()) thread.interrupt();//스레드가 Dead가 아니면 true반환, interrupt를 호출시킴, 예외 발생시 catch절로 들어가서 바로 끝남
		
	}////main
}////class

