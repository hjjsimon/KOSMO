package console;

public class HeoJaeJun0316 {

	public static void main(String[] args) {
		
		
		int[] lotto = new int[6];
		BaseBall.setRandomNumber(lotto,1,45); //메소드 잘 만들면 다 가져다씀, 객체지향언어의 장점
		for(int i=0 ; i<lotto.length ; i++) System.out.printf("%-3d",lotto[i]);
		
		//지문 1] 6개의 숫자를 저장할 배열 선언 및 메모리 할당
		
		//지문 2]랜덤 하게 숫자를 발생시켜 배열에 저장하는 메소드 호출
		
		//지문 3]로또 번호를 출력하는 메소드 호출

		//지문 4] 로또 발생 계속 여부를 판단하는 메소드 호출(x나 X입력시 종료 그외는 계속 로또 번호 출력)​
		
	
	}

}
