package operator02;

public class BeeKyoOP {

	public static void main(String[] args) {
		// 클래스 우클릭-> 리팩터로 이름 변경. 넥스트 넥스트 피니시
		
		/*
		 * -비교연산자(이항연산자)의 결과는 true 아니면 false (boolean값) / 산술연산자는 양쪽항의 값에 따라 결과 다양, 이건 불린값 2개뿐/ 산술연산자 같은 이항연산자
		 * 기준은 무조건 왼쪽, ex. >이면 왼쪽이 크다는 뜻
		 * 
		 * > : ~보다 크다
		 * >= : ~보다 크거나 같다      ex. 3>=1 은 같지는 않지만 크니까 true
		 * < : ~보다 작다
		 * <= : ~보다 작거나 같다
		 * != : 같지 않다
		 * == : 같다
		 * 
		 * 비교연산자는 우선순위 동일
		 * 산술연산자가 비교연산자보다 우선
		 * 비교연산자를 사용하면 비교식 이라고 함
		 * 
		 */

		int num1=10, num2=20;
		System.out.println(num1>num2); // 10>20은 false
		
		boolean b=num1==num2; // num1이 num2와 같은가? b값은 false로 저장
		System.out.printf("%d == %d의 결과: %b%n", num1, num2, b); // 값3개 필요, %b -> b값으로 내줌, 에 저장된 결과인 false를 가져올 예정
		
		b=num1!=num2;
		System.out.printf("%d == %d의 결과: %b%n", num1, num2, b); // b값을 바꿨으니 true 됨
		 
		b=15 % 3 * 2 + 4 > (10-2) * 4 !=true; // 괄호안이 1번, 나머지가 2번, 곱이 3번, 괄호랑 4곱이 4번, 합이 5번, 비교연산자 >이 6번, != 가 7번
		// 4>32!=true? 가 나옴, false!=true, false랑 true는 같지않다 -> yes, true가 나옴
		System.out.printf("15 %% 3 * 2 + 4 > (10-2) * 4 !=true의 결과:%b", b); // % 나머지는 항상 조심, "" 사이에 쓸 때는 복붙 후 %를 %%로 바꿔야함
		
		
		
		
		
		
		
	}

}
