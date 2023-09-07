package controlstatement03;

import java.io.IOException;

public class WHILEStatement {

	public static void main(String[] args) throws IOException {
		
		// 반복횟수 정해짐: for문, 반복횟수 모름: while문
		//while문:반복문으로 반복횟수가 정해져 있지 않을때 주로 사용
		//형식:
		/*
			[초기식;]                        -> for처럼 한번만 실행, 위에서부터 순차시작, 내려오면 위로 못올라감, 선생님이 대괄호침 필요없을 때도 있다는 뜻
			while(반복조건){ 				    
					
			  반복조건이 참일때 실행할 명령문들;    -> 반복조건 참일동안 while 블락 내 명령문이 실행
			   [증감식;]                      -> 초기식, 반복조건식, """명령문 이후 증감식(이 단계)""" 이후 반복조건식
					 
			}
							
			 무한루프 처리]
			 while(true){                   -> for(;;)랑 동일, 반복조건이 true면 항상 반복조건 true니까 무한루프. 여기도 if(조건식)처럼 t나f로만 나와야함, int num1 = 10 같은 초기식은 for문에서만 사용
			 	실행할 명령문들;
			 }
		*/
		
		// for(초기식;반복조건식;증감식{
		           //반복조건이참일때 실행할 명령문
	    //  }
		
		// 문] for에서 1~10까지 합 for로 작성한거랑 동일한 내용, while로 작성
		
		int sum=0; //누적합을 저장할 변수 선언
		int i=1; // [초기식]
		while (i<=10) { // [반복조건]
			sum+=i; // [명령문]
			i++; // [증감식]
		}
		System.out.println("1부터 10까지 누적합:"+sum); // 55나옴
		System.out.println("while문이 끝난 후:"+i); // i=10까지는 합되고, 11일 때 반복식 안맞아서 빠져나옴
		
		// 문] 1부터 1000까지 숫자중 3의 배수이거나 5의 배수인 숫자의 합을 구해라, 단 3과 5의 공배수는 제외시켜라
		
		sum=0;
		i=1;
		while (i<1001) {
			if((i%3==0||i%5==0)&&i%15!=0) sum+=i;
			i++;
		}
		System.out.println("1부터 100까지 3의 배수이거나 5의 배수의 누적합(공배수 제외):"+sum);	
		
		// 선생님
		
		sum=0;
		i=1;
		while (i<1001) {
			if(i % 3 ==0 ^ i % 5 ==0) sum+=i;  
			i++;
		}
		System.out.println("1부터 100까지 3의 배수이거나 5의 배수의 누적합(공배수 제외):"+sum);	
		
		/* 방법1] ||하고 &&사용
				sum=0; // sum에 들어간거 재설정필요
				for(i=1; i<101; i++) {
					if((i % 3 ==0 || i % 5 ==0 )&& i % 15 !=0) sum+=i;
				}      // 15의 공배수는 이 위에 포함, 이 위에 15의 공배수는 포함하면 안됨, 3과 5의 배수는 누적, 15의 배수는 누적x
				System.out.println("1부터 100까지 3의 배수이거나 5의 배수의 누적합(공배수 제외):"+sum);
				
				// 방법2] ^(XOR)연산 사용
				sum=0;
				for(i=1; i<101; i++) {
					if(i % 3 ==0 ^ i % 5 ==0) sum+=i;           
				}  // 둘 다 같으면 거짓, 15는 둘 다 t t 라서 f 실행안됨, 9는 3의 배수t 5의배수f 하나 다르면 t라 실행됨, 2는 둘 다 f f로 f 실행안됨
				System.out.println("1부터 100까지 3의 배수이거나 5의 배수의 누적합(공배수 제외):"+sum);
		*/
		
		System.out.println("문자열을 입력하세요?"); // 바로 밑 두줄 없어도 동일하게?? 한번해보기 이해x
		/*
		char word= (char)System.in.read(); // System.in.read(); -> 이것도 char로 형변환
		System.out.print(word); // read라는 메소드는 사용자 입력한거에서 1바이트밖에 못읽음, 한글은 2바이트이므로 입력시 깨짐
		System.out.print((char)System.in.read());
		System.out.print((char)System.in.read());
		System.out.print((char)System.in.read());
		System.out.print((char)System.in.read()); 
		//줄바꿈 안하고 ~ read에 갖다대면 int라 아스키코드로 나옴, char로 형변환 출력 필요
		//4번 더 반복시 H 이후 E L L O도 출력 read를 5번 입력했으니까
		//근데 사용자가 몇글자를 입력할지 모름, 이럴때 반복문 while씀, 지금은 HELLO WORLD 해도 HELLO만 출력됨
		*/
		
		// 키보드와 .java가 스트림으로 연결 HELLO 엔터 치면 스트림에 \n \r O L L E H 순서로 입력
		// 5회 read시 1바이트씩 읽으면 H, E, L, L, O 까지만 읽고 엔터는 못읽음
		// \r 은 13임, 13을 만나면 빠져나오는게 아이디어
		
		char word;
		while((word=(char)System.in.read())!=13) {  //입력받는게 word로 들어감, 엔터친 13이면 빠져나오겠다는 뜻
			System.out.print(word); // 입력받는 값이 13이 아닐때까지 반복입력
		}
		System.out.printf("%n"); //줄바꿈
		
		/* for문으로도 가능
		
		for( ; (word=(char)System.in.read())!=13 ; ) { // for문 중간이 반복조건, 초기식과 증감식은 없음
			System.out.print(word);
		}
		System.out.printf("%n"); //줄바꿈	
		*/
		
		// 2중 while문, for문 안에 for문 쓰듯 동일, 0001 0010 0100 1000 while문으로 해보기
		
		int k=1; //바깥 while문의 초기식
		// int j=4; // 안쪽 while문의 초기식을 밖에 쓰면 에러는 안남, 근데 0 0 0 1만 출력됨
		while(k<=4) {
			
			int j=4; //안쪽 while문의 초기식
			while(j>=1) {
				
				if(k==j) System.out.printf("%-2d",1);
				else System.out.printf("%-2d",0);
				j--;
			
			}
			
			// j=4; --> 바깥 while 위에 int j=4하면 여기서 j=0 된 후 다시 j=4로 초기화하면 됨, 안쪽 while의 초기식을 바깥쪽 while에 위치시킬 때 추가
			System.out.println(); //줄바꿈
			k++;
	
		} /////////////// 바깥 while
		
		// int j=4 앞에 출력시, k=1, j=4 시작, j>=1로 4가 크니까 k==j로 이동, 근데 여기서 거짓, 0됨, j가 3으로 감소, 명령문끝났으니 반복조건으로감
		// j=3, 거짓이니까 0, j=2로 감소, 거짓이니까 0, 반복조건, j=2니까 참, k=1, j=2로 거짓이니까 0, j=1로 감소, 반복조건 j>=1 아직 성립
		// if(k==j) 니까 1됨, 0 0 0 1 실행, 그리고 j--; 하면 0이 됨, 반복조건 이제 안맞음, 내려와서 줄바꿈, k=2로 증가 후 반복조건 k<=4으로 이동, 내려옴
		// 현재 j=0인데 j>=1해당안됨, 줄바꿈으로 넘어감, k=3됨, j=0 여전히, while에 안들어감, 줄바꿈, k=4됨, 반복조건 아직 맞음, 줄바꿈, k=5되면 빠져나옴
		
		// 별 찍기 while문으로
		
		k=1;
		while(k<=5) {
			
			int j=1;
			while(k>=j) { // k가 j보다 크거나 같을때 반복
			
				System.out.printf("%-2c",'*');
				j++; //언젠가 빠져나가도록
				
			}
			
			System.out.println();
			k++;
			
		}
		
		// 문제] 구구단 출력 while문으로
		
		/*
		for(int k=1; k<=9; k++) {
			for(int j=2; j<=9; j++) {
				System.out.printf("%d * %d =%-3d",j,k,j*k);
			}
			System.out.println();
		}
		*/
		
		k=1;
		while(k<=9) {
			
			int j=2;
			while(j<=9) {
				
				System.out.printf("%d * %d =%-3d",j,k,j*k);
				j++;
				
			}
			
			System.out.println();
			k++;
			
		}
		
		
		while(true) { 
			System.out.println("무한반복");
			break; // 가장 가까운 반복문(현재 자기를 감싼 while)을 빠져나감 
		}
		System.out.println("프로그램 종료"); // true는 무한반복이라 실행될 수 없는 코드
		
		
		
		
		
		
		
		
		
		
		

	}////////////main

}////////////class
