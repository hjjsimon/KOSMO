package stringclass11;
//문자열 만들 때 new String 해야됨, String() 생성자~
// String(byte[] bytes) -> 바이트형 배열을 String으로 반환할 때 생성자를 썼네!
// String(byte[] bytes, int offset, int length) -> offset인덱스부터 끝까지 문자열로 만듦

import java.util.Scanner;

import common.utility.CommonUtil;

//byte[] -> string 변환시 "String(byte[] bytes) 생성자"씀(스트링의 메소드가 없음), 반대는 "getByte() 메소드" 씀
//String->char[] 변환시 생성자 String(char[] value)나 valueOf 쓰면 됨
//생성자는 이름 그대로 쓰고, 메소드는 클래스명.메소드로 쓰는게 차이인듯
		
public class StringConstructor {
	
	public static void main(String[] args) {
		//String과 StringBuffer는 상속관계 없어 형변환x, 생성자로 변환!
		//[String클래스의 생성자]
		
		//1] byte형 배열을 문자열로 변환
		
		//String(byte[] bytes) 생성자 이용
		byte[] bArr= {65,66,67,68,69,48};
		String byteToString=new String(bArr);
		System.out.println("byte[]을 String으로 변환:"+byteToString); //byte[]을 String으로 변환:ABCDE0 위의 배열이 바뀜
		
		//String(byte[] bytes, int 시작인덱스, int 길이)
		byteToString=new String(bArr,2,2);
		System.out.println("byte[]을 String으로 변환:"+byteToString); //2번방 67부터 2개, 67 68을 스트링으로~
		
		//1-1]문자열을 byte형 배열로: String 클래스의 getBytes()
		bArr=byteToString.getBytes(); //현재 CD저장됨, 변수는 재활용
		for(int i=0; i<bArr.length; i++) System.out.println(String.format("bArr[%d]:%d",i,bArr[i])); //0번방, 1번방 -> 바이트로 67, 68 나옴
		for(int i=0; i<bArr.length; i++) System.out.println(String.format("bArr[%d]:%c",i,bArr[i])); //%d->%c로 바꾸면 문자열로 나옴 C,D
		
		//2]char형 배열을 문자열로 변환
		// 생성자 혹은 static String valueOf(char[])이용 -> 이건 이미해서 안함
		
		//String(char[] value)
		char[] chArr= {'H','I','!',' ','안','녕'};
		String charToString=new String(chArr); //생성자 씀, 캐릭터가 문자열로 변환
		System.out.println("char[]를 String으로 변환:"+charToString);
		
		//String(char[] value, int offset, int count)
		charToString=new String(chArr,4,2);
		System.out.println("char[]를 String으로 변환:"+charToString);
		
		//2-1]문자열을 char형 배열로: String클래스의 toCharArray()
		chArr=charToString.toCharArray();
		for(int i=0; i<chArr.length; i++) System.out.println(String.format("chArr[%d]:%c",i,chArr[i]));
		
		//3]아스키나 유니코드값이 저장된 int형 배열을 문자열로 변환 
		int[] codePoints= {50,51,65,66,44032,94};
		String intToString=new String(codePoints, 0, codePoints.length);
		System.out.println("int[]를 String으로 변환:"+intToString);
		
		//문제] String을 int로 변환하기 ->메소드없어서 만들어야함
		String str="ABC123가각";  //각 코드값으로 방에 저장
		//유니코드로 바꾸고 이를 int[]배열에 저장해야함
		//parseInt는 문자열이 숫자일때만 가능함(ex."3"), NumberFormatException발생-> "가" 이런건 안됨, 
		
/*		int[] intArray=new int[str.length()];
		for(int i=0; i<str.length(); i++) {
			intArray[i]=(int)str.charAt(i);         //유니코드값을 int로 변환해 저장함
			System.out.printf("[%d]",intArray[i]);
		}
		System.out.println();
*/	
		//선생님은 커먼유틸에 메소드 만들어서 가져옴
		int[] intArray=CommonUtil.convertStringToIntArray(str);
		for(int i=0; i<intArray.length; i++) System.out.println(String.format("intArray[%d]:%c",i,intArray[i]));
		
		//문제] Scanner클래스로 문자열을 입력받아(nextLine()사용) char형 배열로 변환해서 출력하고
		//또한 입력받은 문자열을 숫자(Integer.parseInt()사용)로 변환해서 2를 곱한 값을 출력해라. (문자 넣으면 에러)
		//단 입력받은 문자열이 숫자형식이 아니면 숫자형식일때까지 계속 입력받아 출력해라
		
		Scanner sc=new Scanner(System.in);
		while(true) {	
		System.out.println("숫자형태의 문자열을 입력하세요?");
		String value=sc.nextLine();
		chArr=value.toCharArray();//캐릭터형배열로 변환
		for(int i=0; i<chArr.length; i++) System.out.println(String.format("chArr[%d]:%c",i,chArr[i]));
//		if(!CommonUtil.isNumber(value)) { //[문자열이 숫자 형식이면 true, 아니면 false반환]
		if(!value.matches("\\d+")) { //정규표현식 쓰는 방식
			System.out.println("숫자형식이 아닙니다"); 
			continue;
		}////if
		System.out.println("2를 곱한 값:"+Integer.parseInt(value)*2);//여기로 오면 숫자형식임
		break; //반복 멈추게
		}////while
	
	}///////////main
}///////////////class
