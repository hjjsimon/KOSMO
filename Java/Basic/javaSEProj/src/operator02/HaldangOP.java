package operator02;

public class HaldangOP {

	public static void main(String[] args) {
		
		/*
		 * [할당(대입)연산자]
		 * = : 변수(데이터가 저장된 메모리) = 값(변수)
		 * 오른쪽에 있는 값을 왼쪽의 변수에 할당(대입)한다.
		 * 
		 * [축약표현]
		 * +=,-=,*=,%=.. 등등
		 * 변수1 += 값(변수2) => 변수1 = 변수1+값 혹은 변수1 = 변수1+변수2
		 */
		
		//1]할당(대입) 연산자
		int num1; //변수 선언, num1이라는 메모리가 만들어짐, 지역변수는 반드시 초기화 후 사용 
		//System.out.println(num1); //[x] 블락안에 num1 넣으면 이제 지역변수이므로 자동으로 데이터 설정x는 안됨. 
		//대입연산자(=)로 초기화
		//대입연산자의 오른쪽 식이 항상 먼저 실행된 후 그 결과값을 왼쪽의 변수에 대입
		num1 = 100; // 100은 상수가 메모리 어딘가에 저장, 그리고 =이 실행되면, num1이라는 메모리에 복사하는 것 -> = 오른쪽부터 실행된다는 뜻
		System.out.println(num1);
		
		int num2; // 이 코드 실행 시 num2라는 방이 생
		num2 = num1; // 변수 num1에 저장된 값 대입 -> num1부터 실행, =으로 num2에 대입하는 것
		//10 = num1; // [x] 할당연산자의 왼쪽에는 변수만 와야함 
		
		
		//2]축약표현
		//산술연산자에만 사용 ->>>>>>>num1 += 1; -> 이렇게 안씀 1 증가면 그냥 num1++ 라고 씀 -> num1 = num1 +1 동일 (당연히 + 대신 - 는 자신을 줄이는 것)
		num1 += 100; // num1 = num1+100; 이걸 줄인게 왼쪽코드, 둘은 동일함, 간결한 코드가 좋으니 왼쪽 사용. -> 자기자신을 100 증가시키는것
		             // 뜻은 num1에 100이란 값을 더하겠다는(+) 뜻, 그리고 대입하겠다(=)
					 // num1 자신에 100을 더해서 다시 본인 num1에 넣음. num1은 200이 됨
		System.out.printf("num1:%d, num2:%d%n", num1, num2); // num1에는 100에서 200이 됨, num2는 위에 100으로 설정한 값 그대로 -> 다시 num2 = num1; 해야 200이 됨
	
		num1 %= num2; //대입연산자 왼쪽은 무조건 변수, 풀어쓰면 num1 = num1 % num2 (= 대입연산자만 빼고 그대로 쓰면 됨)
		// num1 나머지하고 num2 니까 200 % 100 -> num1 = 0 됨
		System.out.printf("num1:%d, num2:%d%n", num1, num2);
		
		num2 *= 2+10; // num2=num2 * 2+10   -> 210이 나와야한다고 생각되는데 1200나옴
		System.out.printf("num1:%d, num2:%d%n", num1, num2);
		// 대입연산자 오른쪽 먼저 시행, 근데 num2 = num2 * (2+10) 으로 묶은 값으로 나온 것

		//num1 &&= num2; // [x] syntax error, 이런 문법은 없다는 뜻 &&=
		
		/*
		 * 증감연산자(단항 연산자): ++(증가연산자), --(감소연산자) -> 둘 합쳐 증감연산자라고 부름
		 * !랑 ~는 단항연산자로 숫자가 오른쪽에 붙음, 근데 이건 숫자가 왼쪽, 오른쪽 둘 다 붙을 수 있음. 그리고 차이가 크다 
		 * 
		 * ++: 자기자신을 1 증가시킴
		 * --: 자기자신을 1 감소시킴
		 * 단독으로 쓰일 때는 항이 증감연산자 앞에 붙거나 결과는 동일
		 * 하지만 다른 연산자와 결합 시 결과 다름
		 * ++(또는--)변수: 연산자가 앞에 붙었을 때는 먼저 자신을 증감시킴.
		 * 변수++(또는--): 다른 연산 수행한 후 자신을 증감시킴. -> ++ 이나 --를 최종 계산에 추가한다는 뜻, 뒤에 붙었으니 뒤에 계싼
		 */
		
		++num1; // 이렇게 다른 연산 없이 단독으로 쓰이면 ++이 앞에 붙던, 뒤에 붙던 무관
		
		//1.단독으로 사용시]
		int num3 = 10;
		//변수명++(후위연산자, 뒤에 붙었다는 뜻)
		//num3++; //num3 = num3 +1 과 같다. 즉 num3+=1과 같다 -> 위에 10이니까 1추가 11됨
		//System.out.println("num3="+num3);
		
		//++변수명(전위연산자, 앞에 붙었다는 뜻)
		//++num3;
		//System.out.println("num3="+num3); //-> 똑같은 결과로 11나옴
		
		//2.다른 연산과 함께 사용시
		//후위연산: 다른 연산 수행 후 마지막에 자기자신을 1증가 또는 1감소
		int num4 = 10; 
		//int result = num4 + num3++; // 연산이 +연산1개 =대입연산1개 총2개, =의 오른쪽부터 실시 10+10 1번 -> result에 20 들어가도록 =가 2번 시행, 그 후에 num3방은 10이 11로 바뀜
		//num3방에 10 들어가있고, num4방에 10 들어가있음
		//result방에 20, 그리고 num3는 11로 바뀜 
		// +가 1번 시행, =이 2번 시행, num3에 1추가가 3번 시행
		//System.out.printf("result:%d, num3: %d%n", result, num3);		
		// 1] num4 + num3 -> 10 + 10
		// 2] result = 20 -> result에 20 들어감
		// 3] 연산이 끝난 후 num3 = num3 +1 -> num3는 10에서 11로 바뀜
		
		int result = num4 + ++num3;
		System.out.printf("result:%d, num3: %d%n", result, num3); // 이번에는 result가 21이 됨, num3는 11
		// 1] num3 = num3 +1 -> num3가 10에서 11이 됨
		// 2] num4 + num3 = 10 + 11 -> 21
		// 3] result = 21 -> result 방에 21이 들어감
		
		int num5=10;
		//System.out.println(num5++); //단독으로 붙어서 앞 뒤 상관없이 11? [x] 10 나옴
		// println(int x) -> 아래가 메소드에 x라는 메모리를 만듦
		// 메소드도 메모리에 그려짐, println 메소드(큰 방)안에 x라는 메모리(작은 방)를 갖고있음, 여기에 대입연산이 먼저 이루어짐, num5 =10이 x에 대입됨, 대입연산이 끝남, 그리고 num5는 11로 바뀜
		//System.out.println(num5); 
	
		System.out.println(++num5); // 이건 num5 방에 10을 1 증가 먼저 시키고 x에 넣음, 출력 값도 11이고, num5만 뽑아봐도 11
		System.out.println(num5);
		
		// 변수, 자료형, 연산자 3개 끝남, 제어문(Control) 시작 -> 프로그램의 흐름을 제어함 
		// 프로그램 짜다보면 갈래길이 나옴, 그 명령길을 결정하는게 제어문
		// 프로그램의 흐름을 제어하는 명령문 = 제어문
		// ex. 조건문, 반복문, 기타 크게 나누면 이정도
		// ex. 아이디, 비밀번호 입력 후 로그인 누르면 사용자가 입력한 값으로 프로그램의 흐름을 제어해야함
		// 사용자의 아이디, 비밀번호가 홈페이지의 회원이라면 회원 페이지로 이동
		// 아니라면 다시 로그인 페이지로 보냄 -> 이 때 쓰는게 if 문-> if (회원)
		// ex. helloworld 만번 출력하려면 sysout 만번.. 복붙하다가 까먹음. 반복문 쓰면 됨
		// ex. 게시판에 15번 반복문해서 15칸 생기는 것
		
		// 조건문(=분기문, 분기시키니까) -> 대표적으로 if문, switch문
		// 반
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}////////////main

}/////////////////class