package array04;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ArrayType01 {

	public static void main(String[] args) {
		
		/*
		 * 배열: 하나의 이름(배열명)으로 같은 자료형의 데이터를 여러개 저장할 수 있는 메모리구조
		 * 메모리가 연속적으로 생김, 참조형이다. 나중에 컬렉션배우면 잘 안씀.
		 */
		
		// 배열(array)은 참조형(Reference type), 기본자료형이 아님, 메모리구조와 데이터저장 장소 다 틀림
		// ex. 학생 1000명의 나이를 저장하고싶음, 
		// 배열: 같은 타입의 메모리를 여러개 저장, 이름은 1갠데,
		// int []age = new int[1000]; (이 과정이 메모리할당) -> 변수1000개를 선언한만큼의 메모리가 만들어짐 []가 빠지면 그냥 변수, 자료형과 변수 사이에 []를 넣어주면 변수가 배열명이 됨, 그리고 배열명 age는 참조형이 됨
		// 참조형은 데이터를 저장하는 메모리를 만들때는 무조건 new 연산자를 사용 -> new int 하면 -> int형 배열 1000개라는 뜻, 앞 대괄호 앞에 int면 뒤에도 int여야함
		// age라는 변수에는 연속된 메모리의 주소가 저장됨
		// ex. age 방 안에 0x1234 이런 식으로 주소저장, 데이터 저장아님, 주소가 저장되니까 참조형이라고함, 이걸 보고 heap영역에 있는 6개 메모리를 찾아갈 수 있음
		// 자바에서 사용하는 heap 라고 부르는 메모리의 영역, 여기에 선생님이 설명한 6개의 칸이 생김 
		
		/*
		 * int[]a; -> a라는 이름의 메모리가 만들어짐, 근데 데이터 공간 아님, 그래서 참조형이라고함
		 * 
		 * a=1; -> 하면 에러남, 데이터를 넣을 수 있는 공간이 아니니까, 실제 데이터를 넣을 수 있는 주소를 저장하는 공간이니까
		 * 
		 * a = new int[3]; // 대입연산자 오른쪽부터 실행, 메모리 개수가 3개 -> a.length하면 개수 나옴
		 * 
		 * new : 말 그대로 새롭게 데이터를 저장할 메모리를 만들겠다는 뜻, int원하면 int써줌
		 * 대입연산자 오른쪽부터 실행, 3 넣었으니, 3개방의 연속된 메모리가 생김, 각 메모리는 int 타입
		 * 3개방 연속된 메모리의 주소가 0x1234라면 a안에 0x1234 주소가 들어감
		 * 3개방 첫번째는 a[0]이 이름, 순차적으로 a[1], a[2] 들어감          ----> a라는 이름을 참조해서 각 방 이름을 설정, 인덱스는 [0]부터 시작, 그래서 for문이랑 잘 맞음
		 * 변수처럼 쓰고싶으면 a[1]=10; 실행 시 a[1]에 데이터10이 들어감
		 * sysout(a[1]); 하면 10 출력
		 * 
		 * 
		 * int a; -> a=변수명, 이것도 Stack에 a라는 이름의 메모리 생성  //// int[] a; -> a= 배열명(주소가 저장되는 메모리->stack이라는 자바가 사용하는 메모리영역에 저장/ heap과 별개임)
		 * 위의 [3] 내의 3은 배열크기(메모리개수)라고 부름
		 * a[0] -> 배열요소명, 배열을 이루는 요소다!
		 * 위의 0은 배열의 인덱스라함, 인덱스는 항상 0부터 시작
		 * 
		 *  int a[] = new int[3];   -> 대괄호는 배열명 뒤에 넣어도 됨 
		 *  3은 heap이라는 메모리영역에 저장
		 *  
		 *  stack 안에 a 안에 0x1234
		 *  heap 안에 0x1234 이름 안에 첫번째 a[0], 두번째 a[1], 세번째 a[2]
		 *  
		 *  new int [1000] -> 1000개 방이 만들어짐, 인덱스는 0부터라 999까지 생김
		 * 
		 */
		
		/*
		 * int a, b, c; -> a, b, c라는 이름의 메모리생김, 위치는 선택불가
		 * a=10; -> a에 10이 들어감, 이게 기본자료형
		 * 
		 * 참조형은 메모리가 구분되어있음, 자바 만든 애들이 이렇게 만든 것,
		 * int []arr (또는 arr[], 보통 앞에꺼씀) -> Stack에 arr이라는 메모리가 생김, 기본자료형처럼 데이터 안들어감
		 * arr = new int[5]-> 5개 메모리가 연속적으로 Heap에 만들어짐,실제 데이터는 Heap 영역의 메모리에 들어감, 참조형은 데이터를 넣을 때 new 연산자를 써야함(걍 암기)
		 * 위에도 할당연산자, arr에 뭔가를 넣었음, Heap 영역의 수많은 메모리 중 0x1234라는 주소를 arr에 넣음
		 * arr에 있는 주소는 Heap 영역의 메모리 주소로 참조(reference)한다, arr이라는 이름으로 참조함
		 * 5개의 방은 처음부터 arr[0], arr[1], arr[2], arr[3], arr[4] 이렇게 5개 생김
		 * Stack의 num이라는 메모리에 0x1234로 같은 주소 참조 시 num이라는 이름으로 참조시 num[0], num[1], num[2], num[3], num[4]가 됨
		 * int형 """배열"""은 기본적으로 초기화되어 다 0이 들어있음 arr[0~4] 방에 따로 선언 안해도 0 들어있음
		 * arr[3]=100; 해주면 0이 100으로 바뀜
		 * sysout (arr[4]); 출력 시 0 나옴
		 * 
		 * 한줄로 쌓인 a[0]~a[4]는 1차원배열 x축 하나
		 * 2차원배열은 행렬로  _ _ _ _ _
		 * 				   _ _ _ _ _
		 *                 _ _ _ _ _  3행 5열
		 * 
		 * 
		 */
		
		
		// 1차원(선형) 배열]
		
		// 방법1] 배열 선언 후 메모리할당
		
		/*
		 * arrNum이라는 배열명으로 int형의 배열을 선언하는 것, arrNum이라는 이름으로 Stack영역에 주소를 저장할 수 있는 메모리가 생김
		 */
		
		int [] arrNum; // 1] 배열선언 -> 지금은 int형 배열이라고함
		//arrNum = 10; //주소 들어감, 그냥 숫자대입은 에러남(데이터 저장 불가)
		
		/*
		 *  2] 메모리 할당: 실제 값을 저장할 수 있는 메모리를 heap영역에 생성하기 위해 new 연산자 사용
		 *  int[5]에서 5는 배열크기 즉 메모리의 개수다
		 *  new연산자: 실제 데이터를 저장할 메모리를 heap영역에 할당하여라, 라는 의미
		 */
		
		arrNum= new int[5]; // 배열 선언하고, 데이터를 저장할 메모리 생성 -> 참조형은 new 연산자 사용 필수(암기)  // 이 코딩을 메모리 할당이라고함
		System.out.println(arrNum); 
		// arrNum에 주소 할당된게 보임
		// [: 1차형배열 의미, I: int형 의미 double형이면 D로 바뀜, @(영어로 앳, at)는 참조하다라는 뜻, 그리고 16진수형태로 5ca881b5로 저장됨
		System.out.println(arrNum[0]);
		System.out.println(arrNum[1]);
		System.out.println(arrNum[2]);
		System.out.println(arrNum[3]);
		System.out.println(arrNum[4]);
		// 데이터 안넣음, new로 메모리만 생성, 근데 "배열"은 초기화가 0으로 되어있음
		// " 배열명[숫자] " -> 를 배열요소명이라고함
		// 숫자는 배열인덱스라고 한다, 0부터 시작
		// 배열명[숫자] 즉 배열요소명은 변수처럼 사용 가능
		
		//3] 배열의 초기화
		/*
		 * 배열은 초기화를 하지 않아도, 해당 자료형의 기본값으로 설정된다.
		 * ex. int: 0, double: 0.0, boolean: false, char: ' '(빈값), 참조형:null 등
		 */
		
		System.out.println("[배열 초기화 전]");
		
		for(int i=0; i<5; i++) { // 인덱스-> i는 항상 0부터 시작, 5개방이면 i 4가 최대 
			System.out.printf("arrNum[%d] : %d%n",i,arrNum[i]); // arrNum[i] -> 변수처럼 쓰면 됨, 이게 통으로 데이터값으로 두번째 %d로 출력
		}
		
		/*
		 * -배열에 값 할당할 때
		 * 배열명[인덱스] = 값 혹은 변수;
		 * 
		 * -배열에 있는 값 읽어올때
		 * 배열명[인덱스]
		 * 배열명[인덱스]:배열요소명
		 * 배열의 인덱스는 0부터 시작
		 */
		
		// 배열초기화 방법1] 메모리 생성후 값 최초로 할당
		
		arrNum[0]=10;
		arrNum[1]=20;
		arrNum[2]=30;
		arrNum[4]=50;
		
		System.out.println("[배열 초기화 후]");
		
		for(int i=0; i<5; i++) {  
			System.out.printf("arrNum[%d] : %d%n",i,arrNum[i]); 
		}
		//arrNum[5]=60; // 배열인덱스의 범위를 벗어났다고 런타임(빨간줄은x, 실행시에만)에러남, 배열크기가 5면 4가 끝 

		// 4] 출력
		// 배열의 크기 얻기: 배열명.length -> sysout(arrNum.length)
		System.out.println("배열크기:"+arrNum.length);
		
		
		/*
		for(int i=0; i<arrNum.length; i++) {  // 위의 반복조건을 arrNum.length로 바꿈
			if(i==arrNum.length-1)
				System.out.printf("arrNum[%d]: %d ",i,arrNum[i]); 
			else 
				System.out.printf("arrNum[%d]: %d, ",i,arrNum[i]); 

		}
		// arrNum[0]: 10, arrNum[1]: 20, arrNum[2]: 30, arrNum[3]: 0, arrNum[4]: 50,  -> 마지막 콤마 꼴배기 싫음, 위에 5-1 최종 i=4이면 콤마 빼도록 바꿈
		
		*/
		
		// 삼항연산자로 만들기, 결과 위랑 동일
		
		for(int i=0; i<arrNum.length; i++) {
			System.out.printf(i==arrNum.length-1 ? "arrNum[%d] : %d" : "arrNum[%d] : %d,",i,arrNum[i]);
		}
		
		// 방법2] 배열선언과 동시에 메모리 할당
		
		String strArray[] = new String[3]; // String 참조형이라 null로 초기화, 하나하나 string형 메모리 생김
		System.out.println("\r\n"+strArray); // "\r\n" 이게 줄바꿈 엔터!
		
		System.out.println("[배열 초기화 전]");
		for (int i=0 ; i<strArray.length ; i++) {
			System.out.printf("strArray[%d] : %s%n", i, strArray[i]);            // 모두 null 나옴
		}
		
		// String형 배열 초기화
		strArray[1]="소프트웨어";
		strArray[0]="한국";
		strArray[2]="인재개발원";

		System.out.println("[배열 초기화 후]");
		for (int i=0 ; i<strArray.length ; i++) {
			System.out.printf("strArray[%d] : %s%n", i, strArray[i]);           
		}
		
		// 방법3] 배열선언과 (동시에 메모리할당) 동시에 초기화
		
		// 첫번째 방법] {} 배열초기화자(라고 함)만 사용
		
		/*
		double [] dbArr = {100, 3.14, 99.9}; // 메모리 3개가 만들어지고 동시에 3개 값이 각각 들어감
		for (int i=0 ; i<dbArr.length ; i++) {
			System.out.printf("dbArr[%d] : %s%n", i, dbArr[i]);        
		}
		*/
		
		// 두번째 방법] new 자료형[]{}                           // 위 아래 둘 다 많이 씀
		double [] dbArr = new double [] {100,3.14,99.9};
		for (int i=0 ; i<dbArr.length ; i++) {
			System.out.printf("dbArr[%d] : %s%n", i, dbArr[i]);  
		}
		
		/*
		boolean [] blArr; //boolean형 배열 선언
		blArr = {true, 3>2, 3>2&&5<3, false};              // 배열선언 후에는 초기화자 이렇게 못씀
		*/
		
		boolean [] blArr = {true, 3>2, 3>2&&5<3, false};
		for (int i=0 ; i<blArr.length ; i++) {
			System.out.printf("blArr[%d] : %s%n", i, blArr[i]);     // 방 4개 t, t, f, f 로 나옴
		}
		
		/*
		 * Scanner 클래스의 메소드 정리]
		 * ex. 사용자가 12(엔터)치면 버퍼에 1|2|'\r'|'\n' 저장됨
		 * nextInt(): 버퍼에 있는 엔터값(\r\n)은 읽지 않는다. nextInt()는 숫자만 읽는다. 즉, 숫자 12 반환.
		 * nextLine()는 엔터값도 읽는다. 하지만 엔터값을 제외한 부분만 반환한다. 즉, 문자열 "12" 반환. 버퍼에는 엔터값이 존재하지 않는다.
		 */
		
		// 숫자 입력 시 nextInt, 이름 입력 시 nextLine 쓰면 됨
		// 키보드 -> .java 랑 스트림으로 연결
		/*
		 * 키보드로 12랑 엔터 치면, 스트림 상으로 \n\r21 순서로 입력
		 * .java로 넘어와서 nextInt 쓰면 1 2 만 가져오고 \r \n 은 스트림에서 버림
		 * .java로 넘어와서 nextLine 쓰면 위에 \n\r이 남아있어서 아무것도 입력 불가, 엔터값도 읽어오니까 엔터 읽고, 스트림에 \n\r 가져오고 뒤에 스트림에 아무것도 없으니까 끝
		 * 이제 키보드로 쓰면 입력 가능
		 * /n/r동길홍 -> 키보드 쓰면 엔터값을 제외한 부분만 반환
		 */
		
		
		/*
		Scanner sc = new Scanner(System.in);
		System.out.println("나이를 입력하시오");
		int age = sc.nextInt();
		System.out.println("입력한 나이:"+age);
		
		sc.nextLine();  // 한번 더 써서 엔터를 제거해줘야 밑에서 이름 입력이 가능
		
		System.out.println("이름을 입력하시오");
		String name = sc.nextLine(); // nextLine은 string으로 받아줘야함
		System.out.println("입력한 이름:"+name);
		
		// name에 ""가 저장됨, nextInt가 엔터는 반환안함, 이름을 입력받아야하는데 입력못받음, 엔터값을 nextLine이 읽어버림 
		
		*/
		
		
		
		// 위아래 순서를 바꾸면 sc. nextLine(); 중간에 안써줘도 됨
		// 엔터값을 제외한 이전까지만 반환, name에 저장, 스트림에는 엔터값이 안남아서 밑에 nextInt는 입력가능
		
		/*
		Scanner sc = new Scanner(System.in);
		System.out.println("이름을 입력하시오");
		String name = sc.nextLine(); 
		System.out.println("입력한 이름:"+name);
		
		System.out.println("나이를 입력하시오");
		int age = sc.nextInt();
		System.out.println("입력한 나이:"+age);
		
		*/
		
		
		// [학생수만큼 이름 입력받기]
		
		/*
		Scanner sc = new Scanner(System.in);
		System.out.println("학생수를 입력하세요?");
		int numberOfStudents = sc.nextInt();
		System.out.println("입력받은 학생수:"+numberOfStudents);
		
		sc.nextLine(); // 스트림에 엔터 지워서 비워주기
		
		//학생수만큼 이름 입력받기
		//학생수만큼 메모리 생성
		String [] names = new String[numberOfStudents];            // 학생 수가 numberOfStudents에 저장, 그 개수대로 메모리 생성
		
		for(int i=0 ; i<numberOfStudents ; i++) {
			System.out.printf("%d번째 학생의 이름을 입력하세요?%n", i+1);             // 배열이 없으면 이런거 절대 못만듦, 학생수몇명일줄알고
			names[i] = sc.nextLine();
		}
		//출력]
		for(int i=0 ; i<numberOfStudents ; i++) {
			System.out.printf("%d번째 학생이름:%s%n", i+1, names[i]);
		}
		
		*/
		
		
		
		int[] jumsu = {98,99,180,34,9090,67,990,1004,19,5,189,55,66,78,890,990};
		int sum=0;
		
		// 문] int형 배열 jumsu에 저장된 점수의 총합과 평균을 구해서 출력
		// 물론 sum = jumsu[0] +jumsu[1] ~~~ 이렇게 해도 됨
		
		/*
		for (int i=0 ; i<jumsu.length ; i++) {
			System.out.printf("저장된 점수의 총합:%d%n", sum += jumsu[i]);
		}
		
		System.out.printf("평균:%d", sum/jumsu.length);
		*/
		
		for(int i=0 ; i<jumsu.length ; i++) {
			sum += jumsu[i];
		} // for문이 끝나면 sum에 점수가 누적되어있음
		
		System.out.printf("총점:%d, 평균:%.2f%n",sum,(double)sum/jumsu.length);  
		// 마지막쪽 sum/jumsu.length는 둘 다 인트라 소수점 안나옴 sum을 더블로 바꿔주면 소수점 나옴
		
		
		// 문] 상기 jumsu배열에 저장된 값 중 최대값을 구하라
		
		int max = jumsu[0];
		
		for(int i=1 ; i<jumsu.length ; i++) {
			if(max < jumsu[i]) max = jumsu[i];
		}
		System.out.println("최대값:"+max);
		
		// 문] 일차원 배열을 크기순서대로 재배치후 내림차순으로 출력하라
		
		//   0  1  2   3   4   5  6   7   8  9  10  11 12 13 14 15 
		// {98,99,180,34,9090,67,990,1004,19,5,189,55,66,78,890,990}
		
		/*
		for(int i=0 ; i<jumsu.length ; i++) {
			if(jumsu[i]<jumsu[i+1]) jumsu[i]=jumsu[i+1];
			else jumsu[i+1]=jumsu[i];
		}
		 System.out.println("jumsu[i]");
		*/
		
		/*
		int min = jumsu[0];
		 
		
		for(int i=1 ; i<jumsu.length ; i++) {
			
			if(max1 < jumsu[i]) max1 = jumsu[0];
			
			int max2
			if( max2=0  max1) 
		*/	
			
			
		
		
		/*
		 * {5 7 1 9 3} -> 이렇게 5개 있을 때
		 * {9 7 5 3 1} -> 이렇게 내림차순이 원하는 목표
		 *  
		 * 5랑 7, 1, 9, 3 모두 비교해봐야함
		 * 7이랑 1, 9, 3 모두 비교
		 * 1이랑 9, 3 모두 비교
		 * 3이랑 1 비교 
		 * 
		 * 마지막방은 자기자신이랑 비교할필요x, 맞으면 방을 바꾸면 됨
		 * 
		 * 0 -> 1, 2, 3, 4 번방이랑 비교 // 바깥 for문이 0, 안쪽 for문이 1증가해서 4번까지
		 * 1 -> 2, 3, 4 번방이랑 비교 // 바깥 for문 i가 1 증가, 안쪽 for문이 1더 증가한 2부터 4번(끝은 동일)
		 * 2 -> 3, 4 번방이랑 비교
		 * 3 -> 4 번방이랑 비교
		 * 4 -> 비교x
		 *  
		 */
	
		//////아래 아주 중요한 알고리즘 temp 맨위 맨아래 i, k 순서 암기
		
	for(int i=0 ; i<jumsu.length-1 ; i++) {
		
		for(int k=i+1 ; k<jumsu.length ; k++) { // 처음 i=0일때 k=1,2,3,4 번방이랑 비교 여기 jumsu.length 이해안됨
			
			if(jumsu[i] < jumsu[k]) { // 부등호 바꾸면 오름차순?
				int temp = jumsu[i]; // 임시변수 temp, i의 변수를 넣어줌, k 넣어도 되긴함
				jumsu[i] = jumsu[k];
				jumsu[k] = temp; // i방을 temp로 바꿈, k방은 temp로 복구, 여기 대각선 \// 이렇게 값이 바뀌는게 공식
				
			}///if
								
		}/// 안쪽 for
	
	}/// 바깥쪽 for
	
	for(int i=0 ; i<jumsu.length ; i++) System.out.printf("%d ",jumsu[i]);

		// [배열 정렬하기]-컬렉션 ---------------> 이런 코드도 있구나~~~ 잊어버려도 됨
		// Arrays.sort(jumsu);디폴트가 오름차순, 이때는 인자가 참조형(클래스)타입의 배열이 아니어도 된다 
		// 내림차순: 단, 아래 메소드를 사용하려면 기본 자료형 타입의 배열이 아닌 참조형(클래스)타입의 배열이어야 한다. int형은 안된다는 뜻
		//	Arrays.sort(T[],Collections.reverseOrder())
		//	Arrays.sort(T[],Comparator<? super T>)
		// int[]를 Integer[]로 변환
				
		Integer [] jumsu_ = new Integer[jumsu.length];
		for(int i=0 ; i<jumsu_.length ; i++) jumsu_[i]=jumsu[i];
		
		//내림차순 첫번째방법
		//Arrays.sort(jumsu_,Collections.reverseOrder());
		//내림차순 두번째방법
		Arrays.sort(jumsu_,new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				//return o2-o1; //내림차순
				return o1-o2; //오름차순
			}
		});
		
		for(int i=0 ; i<jumsu.length ; i++) System.out.printf("%d ",jumsu_[i]);
		
		// 디버깅 방법? 코드왼쪽 더블클릭하면 동그라미가 왼쪽 파란빗금에 생김 위쪽 Run에 Debug as 누르면 옆에 뭐가 보임 클릭, 
		// 여튼 브레이크 이후 f6 누르면 변수값을 추적하면서 for문 확인이 가능함, 컨트롤 f2로 디버깅 풀어주면됨, 그리고 진행
		
		
		
		
		
		
		
	}/////////////main

}////////////class
