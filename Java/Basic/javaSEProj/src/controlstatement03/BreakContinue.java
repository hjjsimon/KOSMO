package controlstatement03;

public class BreakContinue {

	public static void main(String[] args) {
	
		/*
		 기타 제어문: continue문 이나 break문은 반복문(for/while/do~while)이나 switch문에서 사용
		 		   continue문이나 break문을 만나면 그 아래에 있는 명령문들은 실행이 안된다.
		 
		 1) continue(반복문 안에서씀): continue문을 만나면 반복문 처음으로 이동. 
		 	ex. for문에서는 반복조건 참일때 실행할 명령문 이후 if(조건) continue면 증감식으로감
		 	ex. while문에서는 while(반복조건) <- 여기로 감
		 
		 2) break(switch, 반복문 안에서씀):	break문을 만나면 switch문이나 반복문을 빠져 나간다. 
		 	
		 */
		
		int i=0;
		while(i<1000000000) {
			// 증감식 없으면 i 계속 0 이라 무한식
			i++; 
			// 위에 넣어줘야함, 밑에 넣으면 i%2에서 무한루프, 처음 i=1됨, if(i%2) 거짓이니까 컨티뉴 실행x, 
			// 컨티뉴 이후 출력, 브레이크문 이전 출력, i==3 거짓, 브레이크 실행x, 브레이크문 이후 출력, 반복 끝나면 다시 반복조건으로 옴 십억이하니까 i=2로 됨 
			// i%2==0 맞으니까 컨티뉴 c이전출력, i=3되면 c이전이후, 브레이크 이전 출력
			System.out.printf("[i가 %d일 때]%n",i);
			System.out.println("continue명령문 이전 출력");
			if(i%2==0) continue; // 짝수면 컨티뉴, 반복문 처음인 i<10억으로 가서 다시 연산, i=0시작시 i=0에서 십억보다 작으니까 다시 i%2로 내려옴
			System.out.println("continue명령문 이후 출력");
			System.out.println("break명령문 이전 출력");
			if(i==3) break; // 가장 가까운 반복문을 빠져나감
			System.out.println("break명령문 이후 출력");
		}
		//break; 브레이크는 반복문이나 스위치 밖에서 못쓴다고 빨간줄
		//continue; 컨티뉴는 반복문 밖에서 못쓴다고 빨간줄
		
		// 자바에도 이런기능이 있구나하고 끝, 권장x, 보통안씀
		// 아래처럼 레이블을 이용해 안쪽 for문이 아닌 바로 바깥 for문을 빠져나갈 수 있으나, 프로그램 흐름을 망침
		
		kosmo:for(i=0;i<10;i++) { // 레이블을 준다고함, ~~아무거나~~: ex. kosmo란 이름을 가진 반복문 바깥 for문을 빠져나가겠다는 뜻으로 for 옆 콜론씀, 아래에도 kosmo; 해줘야함
			for(int k=0;k<10;k++) {
				System.out.printf("HELLO: i=%d,k=%d%n",i,k);
				break kosmo; // k=0일때 출력하고 가장 가까운 반복문인 안쪽 for문을 빠져나옴, 그러면 i=0 -> 1로 증가, k=0을 벗어날 수 없음
			}
		}
		
		
		
		
		
		
		
		
		
		

	}////////////////main
	
}/////////////class
