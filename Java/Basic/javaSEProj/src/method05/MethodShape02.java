package method05;

import java.io.IOException;
import java.util.Scanner;

public class MethodShape02 {

	/*
	메소드 형식 2:매개변수는 없고 반환 값이 있는 경우
	※ 메소드 형식2 은 주로 메소드안에서 데이터를 생성(예: 메소드 안에서 사용자로부터 값을 입력받기)하여
	  그 값으로 처리한 후 그 결과값을 반환하고자 할때 주로 사용
	
	
	접근지정자 [modifier] 반환자료형(반환타입) 메소드명(){            반환하니까 void 아니고~
		  
		  처리할 일;
		 return  결과값;                -> 값을 반환하니까 return 결과값; 씀, 위에 반환자료형 써놓고 return 안하면, 반환한다고하고 안한거니까 에러남
		 
	}	
	
	결과값을 메소드를 호출한 쪽에 반환 할때는
	return이라는
	키워드 사용	
	*/
	
	//1] 메소드 정의 (메소드 만들 때 public 안써도 됨, 무조건 static은 해야함)
	static int noParamYesReturn() {         //This method must return a result of type int -> int결과를 리턴하라고 나옴
		
		int sum=0;
		for (int i=1 ; i<=10 ; i++) sum+=i;
		//return; -> return 0; -> return;; 하면 값을 반환해야하는데 안하니까 에러남, 반환타입이 void가 아닌 경우는 반드시 값을 반환해야한다(return예약어로)
		return sum;
		//return 0; //noPa~ 의 빨간줄 사라짐         
		//System.out.println("return문 이후"); // [x]unreachable code 나옴, return으로 메소드 밖을 나갔으니 메소드 내 이 코드 실행 불가
			
	}////////////noParamYesReturn
	
	// 문제] 사용자로부터 국영수 점수를 입력받아(메소드 안에서) 평균을 구해서 학점을 반환하는 메소드 정의 ex. "A학점","B학점...... ->String으로 반환해야함
	static String getGrade() { // 값은 안받으니까() 바로 닫음 -> 값 반환안하면 getGrade에 빨간줄 뜸
		Scanner sc = new Scanner(System.in);
		//과목 타이틀 출력용
		String [] subjects = {"국어","영어","수학"};
		//점수 저장용
		int [] jumsu = new int[subjects.length];
		//총합 저장용
		int total=0;
		//사용자 입력받기
		for(int i=0 ; i<jumsu.length ; i++) {
			//타이틀 출력
			System.out.println(subjects[i]+"점수 입력");          
			//점수저장
			jumsu[i]=sc.nextInt(); //nextInt() -> 입력받은 값을 저장하는 메소드 중 하나, 입력받은 점수는 jumsu 배열에 저장
			//total에 누적
			total+=jumsu[i];
			}///////// for
		
		//평균을 구해서 학점 반환
	/*	//방법1] 변수에 결과값 저장 후 변수 반환        -> 결과값 result에 담음
		String result;
		switch(total/3/10) {
			case 10:
			case 9: result="A학점"; break;
			case 8: result="B학점"; break;
			case 7: result="C학점"; break;
			case 6: result="D학점"; break;
			default: result="F학점";
		}
		return result;
	*/
		//방법2] 직접 return, break문 불필요. -> String result 안하니까, 메모리 적음, 그러나 변수 result 대신 case를 이용해 return할 때는 default 반드시 해야한다! 
		switch(total/3/10) {
			case 10:
			case 9: return "A학점"; 
			case 8: return "B학점"; 
			case 7: return "C학점"; 
			case 6: return "D학점"; 
			default: return "F학점"; //당연히 case 5: 4: 3~~ 0: return "F학점"도 말은 맞음. 근데 getGrade()에 빨간줄감
		}//////switch
		
		// 위 방법 주의할 점이 있음 
		// total/30 -> 10~6 넘어서 다 return해도,,,, default를 해줘야함, 다른 케이스가 생길 수 있다고 컴퓨터가 판단함
		// case를 이용해서 return 시 else에 해당하는 default를 필수로 넣어야함
		
	}///////////getGrade
	
	// 문제] 메소드 안에서 사용자로부터 숫자 2개(Scanner)와 산술연산자(System.in.read()사용)를 입력받는(+,-,*,/) 메소드로
	// 산술결과는 메소드안에서 바로 출력하고 산술연산자기호를 반환하는 메소드를 정의해라. 그리고 main 메소드에서 호출하여 사용자가 입력한 연산자를 출력하여라
	
	/*
		static int num() throws IOException {
			System.out.println("숫자를 입력하세요");
			Scanner sc = new Scanner(System.in);
			//점수 입력용
			int [] sansul = new int [2];
			
			for(int i=0 ; i<sansul.length ; i++) {
				sansul[i] = sc.nextInt();
				System.out.println("숫자를 하나 더 입력하세요");
			}
			char yeonsan = (char) System.in.read();
			
		}
	*/
	
	// 인자는 안받고 산술결과를 바로 출력, 아래만 열림, read는 int로 반환함 ->  + 의 int값으로도 가능, 
	// + 문자열이니까 string으로 해도 됨, 근데 선생님은 char로 함
	
	static char getOperator() throws IOException {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("첫번째 숫자 입력?");
		int fnum = sc.nextInt();                            // sc 빨간줄 해결 -> 위에 scanner 쓰면 해결됨
		System.out.println("연산자 기호(+,-,*,/) 입력?");
		char op = (char)System.in.read();                              
		System.out.println("두번째 숫자 입력?");
		int snum = sc.nextInt();
		// System.out.printf("첫번째 숫자:%d,연산자:%c,두번째숫자:%d%n",fnum,op,snum); -> 출력 뺌
		switch(op)	{	// switch 안에 char도 가능
			case '+': // char니까 싱글쿼트로 감쌈
				System.out.printf("%d + %d = %d%n", fnum,snum,fnum+snum);
			break;	
			case '-': // char니까 싱글쿼트로 감쌈
				System.out.printf("%d - %d = %d%n", fnum,snum,fnum-snum);
			break;	
			case '*': // char니까 싱글쿼트로 감쌈
				System.out.printf("%d * %d = %d%n", fnum,snum,fnum*snum);
			break;
			case '/': // char니까 싱글쿼트로 감쌈
				System.out.printf("%d / %d = %d%n", fnum,snum,fnum/snum);
			break;
			default:System.out.println("잘못된 연산자 기호입니다");
		}//////switch
		return op;           // 빨간줄 안가게 아무거나 쓰고 리턴
	}
	
	
	
	public static void main(String[] args) throws IOException {

		//메소드는 원래 중복코딩을 방지하기 위해 쓰는 것 
		
		//2]메소드 호출(call) -> invoke도 있다~ 나중에 배움, call은 직접 코드로 작성 ~~; 해야함, invoke는 코드로 작성을 안해도 특정 이벤트 발생 시 자동으로 호출됨
		// 콜백함수: 호출하는게 아님, invoke 같은 함수 말함, called라고 안하고 invoked라고 함
		// call by value: 값에 의한 호출 -> methodshape04 가보면 getMaxValue(3) -> 3값으로 호출 -> 지금까지한게 call by value
		// call by reference: 참조에 의한 호출, 값이 아니고 주소를 전달, 한번에 여러개 나옴 
		
		
		//방법1] 반환값에 변수 저장하고 출력 -> value라는 변수를 이 밑에서도 또 쓸 일이 있을 때 사용하는 방법
		noParamYesReturn(); //-> 메소드 원형이라 값 안넣어도 되니까 괄호안 공란 가능
		int value = noParamYesReturn();
		System.out.println("1부터 10까지 누적합:"+value); //10까지 합, sum에 55저장, return하면 noParamYesReturn() -> 이거 자체가 55가 됨, 그걸 다시 value에 넣은것
		
		// 방법2] 변수 미사용, 반환값 바로 출력
		// 메소드는 한번 정하면 계속 호출할 수 있음
		System.out.println("1부터 10까지 누적합:"+noParamYesReturn()); // 이 코드 실행 시 다시 메소드로 가서 sum에 55돼서, noPa~에 55가 return 
		
		System.out.println("당신의 학점은 "+getGrade());
		
		
		System.out.println("입력한 연산자는 "+getOperator());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}/////////////////////main
}////////////////////////////class
