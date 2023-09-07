package method05;

public class CommandLine { // .class로 저장되는 파일의 이름
	
	// 호출할 때 getMaxValue(3) <= 3이 인자(인수)라고 함 ex. javac aaa.java -> 에서는 (aaaa.java)가 인수
		/*
		명령줄(Command Line) 인수: main메서드의 매개변수인 param에 전달하는 값을 명령줄 인수라 한다
		param는 Command Line(cmd의 한줄)에서 인수를 의미 즉
		예] dos>java.exe CommandLine 100    가길동 에서  args는 100를 담은 매개변수임
		
		ex. CommandLine 100 프로그램 실행하려면 java를 쓰는것, CommandLine 100가 인수 /  CommandLine 프로그램에선 100이 인수
		
		
		[실행시] 인수 개수가 args 배열의 인덱스보다 많으면 상관없으나 부족하면 ArrayOutofBoundsException예외 발생
		
		dos>java.exe CommandLine 홍길동 20 강남 // 이번엔 인수 3개 전달, 각각 param[0]번방부터 [2]번방까지, 값 3개전달시 방이 3개 만들어짐
		*/

	public static void main(String[] param) {   // param은 힙영역에 저장된 배열을 저장하는 것, 메모리할당은 안된대(모르겠음)
		
		System.out.println("[명령줄 인수]");
		System.out.println("이름:"+param[0]);
		System.out.println("나이:"+param[1]);
		System.out.println("사는 곳:"+param[2]);       // 값 최소 3개 전달해야, 메모리3개, 방 [2]까지 만들어짐
		//param이라는 이름으로 가르키는 메모리가 안만들어짐, Index 0 out of bounds for length 0, 0번방 없다고 에러ㅏㄴㅁ
		
		// run configuration -> arguments 에서 값 3개 넣어서 프로그램에 전달되는 값이 param에 저장, 무조건 단어간 한칸씩 띄우기
		
	}

}
