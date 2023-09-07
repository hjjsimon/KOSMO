package array04;

public class ArrayType02 {

	public static void main(String[] args) {
		
		// 2차원 배열: 1차원배열(x축)dl y축으로도 쌓인 것(평면), 대괄호2개만 쓰면 됨(중간이든 뒤든 무관)
		
		//1]배열선언: 스택에 주소를 저장할 수 있는 arrNum이라는 메모리가 생성됨
		int [][] arrNum;  // 선언이니까 메모리가 만들어짐// 3차원은 안씀, 메소드 쓰거나, 쪼개버림, 머신러닝에서 이미지 데이터 같은건 3차원
		
		//2]메모리할당: 실제 데이터를 저장하는 메모리 할당, heap영역에 메모리 할당, []앞:행(바깥for문) []뒤:열(안쪽for문) , 배열선언 후 메모리할당
		arrNum = new int[2][3];  // 앞에 [2]가 행 [3]가 열 -> 6개 메모리 -> new로 새로 만들어짐 stack의 arrNum을 참조해서 heap영역에!, arrNum에는 주소 저장됨
		// arrNum에 주소 0x5678저장, 인덱스 이름은 arrNum[0][0] 부터 시작(똑같이 [행][열]) -> 0부터 시작
		// arrNum[0][0] arrNum[0][1] arrNum[0][2]
		// arrNum[1][0] arrNum[1][1] arrNum[1][2]
		System.out.println(arrNum); // 2행3열짜리 전체의 시작주소, [[I@5ca881b5 -> [ [ 대괄호 2개 나옴, @=at은 참조하다라는 뜻 I는 int // 이건 전체 메모리 주소
		System.out.println(arrNum[0]); // 0행 전체의 시작주소, [I@24d46ca6 -> 이것도 출력?? 여기는 0행 전체의 시작주소가 저장됨 // 이건 0행의 메모리 주소 -> arrNum[0][0] arrNum[0][1] arrNum[0][2]
		System.out.println(arrNum[1]); // 1행 전체의 시작주소

		System.out.println(arrNum[0][0]); // 0행 0렬의 데이터 -> 0나옴
		System.out.println(arrNum[1][2]); // 1행 2열의 데이터 -> 0나옴
		
		System.out.println("[초기화 전]");
		for(int i=0 ; i<2 ; i++) { // i -> 행 인덱스는 0, 1  (총 2행 3열)
			for(int k=0 ; k<3 ; k++) { //k-> 열 인덱스는 0, 1, 2 존재
				System.out.printf("(%d행,%d열):%-3d",i,k,arrNum[i][k]);
			}
			System.out.println(); // 한 행 끝나면 줄바꿈, arrNum[0][0] arrNum[0][1] arrNum[0][2] 이후 엔터치는 것
		}
		
		//3]초기화
		
		arrNum[0][0]=1;
		arrNum[0][1]=2;
		arrNum[0][2]=3;
		arrNum[1][0]=4;
		arrNum[1][1]=5;
		arrNum[1][2]=6;
		
		System.out.println("[초기화 후]");
		for(int i=0 ; i<2 ; i++) { // i -> 행 인덱스는 0, 1  (총 2행 3열)
			for(int k=0 ; k<3 ; k++) { //k-> 열 인덱스는 0, 1, 2 존재
				System.out.printf("(%d행,%d열):%-3d",i,k,arrNum[i][k]);
			}
			System.out.println(); // 한 행 끝나면 줄바꿈, arrNum[0][0] arrNum[0][1] arrNum[0][2] 이후 엔터치는 것
		}
		
		// i<2(행의수), k<3(열의 수) -> 하드코딩 구림 똘똘하게하기
		// 자바는 행, 렬 개수를 다르게 할 수도 있음 각 행마다 열의 수가 다르면 0행 3열, 1행 4열, 2행 3열 -> ㅗ 모양 
		// 2차원 배열에서 행의 수는 배열명.length
		// 열의 수는 배열명[행인덱스].length
		
		System.out.println("행의 수:"+arrNum.length); // "행의 개수" 이게 전체주소라고 말하셨지만 총 행의 주소(행의 개수)라고 생각하는게 쉬운듯
		System.out.println("0행의 열의 수:"+arrNum[0].length); // "0행의 열의 개수" 지금0은 행인덱스, 주소가 저장된 것들은 . 찍으면 뭐가 나옴, .length도 되고, 여튼 뭘 참조하는 것
		System.out.println("1행의 열의 수:"+arrNum[1].length); // "1행의 열의 개수" 지금1은 행인덱스
		
		System.out.println("[행 및 열의 수를 length속성으로 변경]");
		for(int i=0 ; i<arrNum.length ; i++) { // i -> 행 인덱스는 0, 1  (총 2행 3열) -> 세로로 몇 줄인지 나옴(행의 개수)
			for(int k=0 ; k<arrNum[i].length ; k++) { //k-> 열 인덱스는 0, 1, 2 존재
				System.out.printf("(%d행,%d열):%-3d",i,k,arrNum[i][k]);
			}
			System.out.println(); // 한 행 끝나면 줄바꿈, arrNum[0][0] arrNum[0][1] arrNum[0][2] 이후 엔터치는 것
		}
		
		//6]열의 수를 행마다 다르게 할당하기
		int dynamic[][] = new int[2][]; //배열 선언과 동시에 메모리할당, 열을 동적으로 할당하기 위해 열의 수 미지정, 행은 2개 0행, 1행, heap에 메모리가 2행으로 틀만 정해져있음~
		System.out.println(dynamic); // 전체 주소(=전체 행주소) 나옴 [[I@214c265e
		System.out.println(dynamic[0]); // 0행 전체의 시작주소, 아직 메모리가 만들어지지 않아서 null 나옴 = 주소가 없다, 참조하는 메모리가 없다 라는 뜻
		dynamic[0]= new int[3]; // 0행을 3열(3칸짜리)로 확정하는 것 -> 주소할당됨
	    System.out.println(dynamic[0]); // 이것만 보면 1차원이라 대괄호1개 주소나옴 [I@448139f0
		dynamic[1]= new int[1]; 
		System.out.printf("0행의 시작주소:%s,0행의 열의 수:%d%n",dynamic[0],dynamic[0].length);
		System.out.printf("1행의 시작주소:%s,1행의 열의 수:%d%n",dynamic[1],dynamic[1].length);

	
		for(int i=0 ; i<dynamic.length ; i++) { // i -> 행 인덱스
			for(int k=0 ; k<dynamic[i].length ; k++) { //k-> 열 인덱스
				System.out.printf("(%d행,%d열):%-3d",i,k,dynamic[i][k]);
			}
			System.out.println(); // 줄바꿈
		}
		
		//초기화] 지금 값 0 -> 다른걸로 바꿀 예정
		for(int i=0 ; i<dynamic.length ; i++) { // i -> 행 인덱스
			for(int k=0 ; k<dynamic[i].length ; k++) { //k-> 열 인덱스
				dynamic[i][k]=i+k; // 첫칸 0.0 -> 0, 두번째칸 0.1 -> 합1 세번째칸 0.2 ->합2 / 1행 0열 1.0 -> 합1
			}	
		}
		
		System.out.println("[초기화 후]");
		for(int i=0 ; i<dynamic.length ; i++) { // i -> 행 인덱스
			for(int k=0 ; k<dynamic[i].length ; k++) { //k-> 열 인덱스
				System.out.printf("(%d행,%d열):%-3d",i,k,dynamic[i][k]);
			}
			System.out.println(); // 줄바꿈
		}
		
		// 초기화자로 배열선언
		// 7] 2차원 배열 선언과 동시에 초기화
		// 바깥 {}는 초기화를 위한 중괄호(초기화자)
		// 바깥 {}안의 {}안의 수가 행의 수를 의미
		// 안쪽 {}안의 값의 수가 열의 수를 의미
		
		int[][] arrInit = {{1,2},{3,4,5},{6}}; // 바깥 중괄호는 초기화자, 안의 중괄호의 수가 행의 수 -> 지금 3행, 그 안의 값이 열의 수, 1행의 열의 수 3개, 그리고 1행 0열의 값은 3 
		System.out.println("[초기화자 사용]");
		
		for(int i=0 ; i<arrInit.length ; i++) { // i -> 행 인덱스
			for(int k=0 ; k<arrInit[i].length ; k++) { //k-> 열 인덱스
				System.out.printf("(%d행,%d열):%-3d",i,k,arrInit[i][k]);
			}
			System.out.println(); // 줄바꿈
		}///////////
		
		/*4행 5열
		 1 9 9 9 9
		 9 1 9 9 9
		 9 9 1 9 9
		 9 9 9 1 9 */
		
		//문1] 2차원 배열을 선언하는데, 선언과 동시에 메모리 할당하라   -> 동시에 초기화 아님, 동시 초기화면 초기화자
		int [][]nine = new int [4][5]; // 할당은 new! 
		//문2] 위의 값을 문1]에서 선언한 배열에 저장하라 (위의 199~값) -> 행인덱스, 열인덱스 동일 시 1 아니면 9
		for(int i=0 ; i<nine.length ; i++) { // i -> 행 인덱스
			for(int k=0 ; k<nine[i].length ; k++) { //k-> 열 인덱스
				if(i==k) nine[i][k]=1;
				else nine[i][k]=9;			
			}
		}
		//문3] 2차원 배열에 저장된 값을 위 모양대로 출력하라
		for(int i=0 ; i<nine.length ; i++) { // i -> 행 인덱스
			for(int k=0 ; k<nine[i].length ; k++) { //k-> 열 인덱스
				System.out.printf("%-3d",nine[i][k]);			
			}
			System.out.println();
		}
		
		/* 잘함~
		//문1]
		int [][] question= new int[4][5];
		
		//문2]
		question[0][0]=1;
		question[0][1]=9;
		question[0][2]=9;
		question[0][3]=9;
		question[0][4]=9;
		
		question[1][0]=9;
		question[1][1]=1;
		question[1][2]=9;
		question[1][3]=9;
		question[1][4]=9;

		question[2][0]=9;
		question[2][1]=9;
		question[2][2]=1;
		question[2][3]=9;
		question[2][4]=9;
		
		question[3][0]=9;
		question[3][1]=9;
		question[3][2]=9;
		question[3][3]=1;
		question[3][4]=9;

		//문3]
		System.out.println("[문제]");
		for(int i=0 ; i<question.length ; i++) { // i -> 행 인덱스
			for(int k=0 ; k<question[i].length ; k++) { //k-> 열 인덱스
				System.out.printf("(%d행,%d열):%-3d",i,k,question[i][k]);
			}
			System.out.println(); // 줄바꿈
		}///////////
		*/
		
	}//////////////main

}/////////////class
