package thread23;

class Soldier{
	void longedMethod(){//시간이 오래걸리는 메소드(메인에서 뉴 한다음에 클래스명으로 실행해야함)->스레드로 구현할것
		for(int i=1;i<=10;i++) {
			System.out.println(String.format("스레드명:%s,i=%d",Thread.currentThread().getName(),i));//실행중인 스레드명
			try {
				Thread.sleep(1000);//1초간격
			}
			catch(InterruptedException e){e.printStackTrace();}//sleep메소드가 이런 에러를 발생시킴
		}
	}
}/////////Soldier
//상속받은 longedMethod()스레드로 구현하자
//1]Runnable 인터페이스 상속
class Commander extends Soldier implements Runnable{//사령관=솔져, 솔져!=사령관, 더 큰 부모 솔져 상속, 이미 상속받았으니 러너블 인터페이스!
	//2]run()오버라이딩
	@Override
	public void run() {
		longedMethod();//상속받았으니 바로 호출
	}
}
public class RunnableApp {

	public static void main(String[] args) {

		Commander commander=new Commander();//객체생성, commander는 상속받았으니 Soldier타입, Runnable타입 이라고도 할 수 있음, 근데 상속받지 않은 Thread타입은 아님
		System.out.println(commander instanceof Commander);//start()은 Thread 메소드임, Runnable꺼는 run()메소드밖에 없음
		System.out.println(commander instanceof Soldier);
		System.out.println(commander instanceof Runnable); //상속받은거니까 다 true나옴, 오른쪽이 커야함
		//System.out.println(commander instanceof Thread);//상속x
		//commander.start(); 불가! Thread꺼임!
		//Runnable타입을 Thread타입으로 변환해야함!] ->근데 commander랑 Thread 상속관계 없어서 형변환불가
		//Thread클래스의 인자생성자를 이용해서 Thread타입으로 변환, Runnable을 받아서 Thread로 바꾸는 메소드가 있음
		Thread th1=new Thread(commander); //괄호안에 Runnable타입 넣으면 됨, commander도 Runnable타입이니 가능 -> 이름 안준건 Thread-0으로 이름 지어짐
		th1.start();
		Thread th2=new Thread(commander,"두번째 스레드");//이번엔 스레드 이름도 넣어줌
		th2.start();
	}
	
}
