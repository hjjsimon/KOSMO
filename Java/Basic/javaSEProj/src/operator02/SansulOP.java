package operator02;

public class SansulOP {

	public static void main(String[] args) {
		
		// +, - 는 안함
		
		/*
		 * -산술연산자(이항연산자- 계산시 항이 2개 필요, 의 대표, ex. 더하기는 항2개 필요 _ + _/ 느낌표는 단항연산자, +, - 자체로도 부호로 쓰여 단항연산자 )의 결과는 다양하다
		 * (3항연산자도 있음, 항이 적을수록 우선순위가 높다, 먼저 계산한다)
		 * -산술연산자 내에서의 연산 우선순위 존재
		 * (*,%,/) > (+,-) , %는 나머지 연산자, 프로그래밍에만 있음, 합차가 우선순위 낮음, 그냥 수학 동일
		 * ex. 5를 2로 나누면 -> 5/2
		 * ex. 5를 2로 나누면 2하고 4되고 1 남고 답 2.5 나며지 0, 나머지 0 될때까지 계산하는게 나누기/ 2.5는 몫, 나머지는 0/ 나누기는 값을 몫으로 취한다
		 *     오 위에서 2/5는 인트형끼리 계산이라 2.5가 실제 답이지만 자바로는 2라고 인트로 계산됨
		 * ex. 5%2는 나머지 연산, 나머지 나오면 그냥 끝냄, 몫을 갖지않음, 그냥 2까지 계산하고 나머지 1남으면 그게 답
		 *     나머지가 1이면 홀수, 나머지가 0이면 짝수 (중요)
		 * 
		 * -우선순위가 같은 경우 왼쪽에서 오른쪽으로 연산
		 * -산술 연산자를 써서 식을 만들면 산술식
		 * 
		 */
		
		int result=3 * 2 + 5 % 2 - 6/3*5;  // -3이 답으로 나와야함
		
		System.out.printf("3 * 2 + 5 %% 2 -6/3*5의 결과:%d", result);    // 중간에 % 뒤에 빈칸이 에러남, %% 써줘야 진짜 %로 계산
		
		//위 계산 결과는 result란 이름에 저장
		
		// 우선순위 동일, 곱, 나머지, 나누기, 곱 그냥 식 순서대로 계산/ 그리고 합,차 계산
		/*
		 * 1] 3*2=6
		 * 2] 5%2=1
		 * 3] 6/3=2
		 * 중간정리] 6+1-2*5
		 * 4] 2*5=10
		 * 5] 6+1=7
		 * 6] 7-10=-3
		 * 
		 */
		
		
		
		
		
		
		
		
		
		
		
		
		

	}// TODO Auto-generated method stub

}
