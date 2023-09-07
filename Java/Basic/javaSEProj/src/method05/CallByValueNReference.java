package method05;

// call by value & call by reference는 메소드 호출과 관련된 개념 -> 호출 시 매개변수에 값을 전달
// 매개변수에 값을 전달(복사)하거나(call by value)
// 매개변수에 메모리의 주소값을 전달(복사)하는 경우(call by reference) -> 반환타입을 void로 해도 반환받은거랑 똑같음, 메모리를 공유하기 때문

public class CallByValueNReference {           
	
	// 매개변수의 타입이 기본자료형인 경우: call by value(값에 의한 호출): 값이 매개변수에 전달됨, 메소드04보면 int numberCount에 int니까 값이 저장
	// getMaxValue메모리에 numberCount, sc, numbers 메모리가 생김 -> 호출 시 numberCount에 3이 저장되는 것 = 값에 의한 호출, 값이 매개변수(int형)에 저장
	
	//Call By Value 테스트용 메소드 정의] void 값 반환x, 위랑 다른내용 새로 시작
	static void callByValue(int first, int second) {
		
		int temp = first;    //임시변수 하나 만듦, 1을 temp에 임시 저장
		first = second; 	// first는 10 됨
		second = temp;        // second는 1됨, 3개 코드 실행시 값이 바껴있음
		System.out.printf("callByValue메소드 안]first:%d,second:%d%n",first,second);
		
	}/////////////////// callByValue
	
	/*
	매개변수의 타입이 참조형(배열,String,클래스,인터페이스등) 인 경우:
	call by reference(참조에 의한 호출)
	값이 아니라 주소값이 매개변수에 전달됨.
	같은 메모리를 참조한다
	-call by reference를 적용해서 매개변수에 여러개의 값을 동시에 전달
	  할 수도 있고 또한 여러개의 값을 반환할 수  있다.
	 (반환타입이 void여도 반환되는 효과가 일어남)                   
	 
	  // 메소드에서 값이 바뀌면 메인도 값이 바뀜 call by value랑 다름, 동시 여러개 전달, void도 반환 이게 특이
	*/
	
	//Call By Reference테스트를 위한 메소드 정의]
	static void callByReference(int [] arr) {
		int temp=arr[0]; // 0번방에 있는걸 temp에 넣음
		arr[0]=arr[1]; //1번방->0번방에
		arr[1]=temp; //1번방->temp에
		System.out.printf("callByReference메소드 안]arr[0]:%d,arr[1]:%d%n",arr[0],arr[1]);
	}
	
	

	public static void main(String[] args) { 
		//만든 프로그램에 전달할 인자들이 args에 들어감, String형이고 [] 배열임, 3을 전달해도 "3"으로 문자열로 저장됨, 커맨드라인에 전달하는 인자를 받는 매개변수
		
		
		//Call By Value 테스트] -> 교안 확인 val1,2 대신 first second 쓴것
		int first=1, second=10;
		System.out.printf("main메소드 안-callByValue호출 전]first:%d,second:%d%n",first,second); // 같은 이름의 first, second지만 메소드 지역이 다름

		callByValue(first,second); // 위에 first, second 했으니까 1, 10 각각 안써도됨 이게 진행되면 위에 메소드로 가서 int temp = first; 부터 진행하는 것
		System.out.printf("main메소드 안-callByValue호출 후]first:%d,second:%d%n",first,second); // 같은 이름의 first, second지만 메소드 지역이 다름

		/*
		 * main 메소드의 스택 영역에 first, second 메모리가 생김 (메소드마다 스택, 힙 메모리 가짐)
		 * call by value메소드의 스택 영역에는 first, second, temp 3개 메모리가 생김
		 * int first=1, second=10; 실행 -> 1, 10 메모리 생성
		 * callByValue(first,second); 실행 -> callbyvalue메모리의 first, second에 각각 1, 10이 저장됨
		 * 그리고 위의 callbyvalue 메소드로 가서 int temp = first부터 실행 temp가 1로 바뀌고~~ 실행 끝나면
		 * System.out.printf("main메소드 안-callByValue호출 후]first:%d,second:%d%n",first,second); 실행, -> main 메소드는 first 1, 10 그대로
		 * callbyvalue 메소드의 스택 영역내 first, second, temp는 1,10이었다가 메소드 진행되며 값이 다 바뀜
		 */
		
		//Call By Reference 테스트]
		int[] ref= new int[2];
		ref[0]=1;
		ref[1]=10;
		System.out.printf("main메소드 안-callByReference호출 전]ref[0]:%d,ref[1]:%d%n",ref[0],ref[1]); 

		callByReference(ref); // 변수명, 배열명만 전달하면 됨 ref 통으로, 한번에 1,10 값 2개 전달, 매개변수는 arr 1개인데, 그리고 void인데 반환됨
		System.out.printf("main메소드 안-callByReference호출 후]ref[0]:%d,ref[1]:%d%n",ref[0],ref[1]); 
		// 이번에는 콜밸류랑 다르게 호출해도 값이 바껴있음, ref바꾼 코드가 메인에 없는데? -> 같은 메모리를 공유하기 때문, 그림교안 참고
		/*
		 * main 메소드의 스택영역 -> ref 메모리(주소존재) 만들어짐, main메소드의 힙영역-> ref[2]니까 2개 메모리가 생김, 1과 10 각방에 넣음, 호출전값으로 프린트
		 * callbyref 메소드의 스택영역 -> arr메모리 만들어짐, temp도 생김, ref에 저장된 주소값 0x1234가 arr에 들어감, 주소대로 arr이라는 이름으로 0x1234주소 찾아가면 arr[0], arr[1] 이름이 됨
		 * arr[0]에 1, arr[1]에 10 존재, 그리고 temp랑 바꾸면, 최종 arr[0]에 10, arr[1]에 1, 
		 * (main메소드의 힙영역, callref의 힙영역은 하나로 통일됨)
		 * 그리고 callByReference(ref); ref라는 이름으로 참조
		 * System.out.printf("main메소드 안-callByReference호출 후]ref[0]:%d,ref[1]:%d%n",ref[0],ref[1]); 출력시 출력값나옴 바뀐걸로
		 * 
		 * static int[] <= void 대신 int형 반환하고
		 * return arr; 해도 됨
		 * 
		 * 근데 callByReference(ref); 말고 ref = callByReference(ref);로 반환한걸 받아야함, 근데 이렇게 안하지
		 * 
		 * 
		 */
		
		
		
		
		
		
		
		
		

	}/////////////main

}/////////////////class
