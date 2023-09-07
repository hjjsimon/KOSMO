package common.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//모든 언어에는 정규표현식이 있음, 쓰면 간결하게 노가다 해결가능

public class CommonUtil {
	//[문자열이 숫자 형식이면 true, 아니면 false반환]
	public static boolean isNumber(String value) { //클래스명으로 접근, 숫자면 false, 아니면 true 반환
		for (int i=0; i<value.length(); i++) { //WrapperClass03에서 가져옴
			int codeValue=Character.codePointAt(value, i);
			if(!(codeValue>='0'&&codeValue<='9')) return false; 
		}	
		return true ;
	}//////////////////isNumber
	
	//RockPaperScissorsMethodVer -> 사용자 입력 중 숫자는 다 처리했는데 영어 처리는 안함, 이제 처리하러감
	
	//두 날짜 차이를 반환하는 메소드] 반환타입:long, 매개변수: String타입의 두 날짜, 날짜패턴, 구분자(단위 ex. 년 그런거)------------------------------------------
	public static long getDiffBetweenDates(String stFDate, 
			String stSDate, 
			String pattern, 
			char delim) throws ParseException{ //날짜사이의 차이를 가져온다고 씀, delimiter 약자로 d 구분자도 받을거임
		
		//1]매개변수에 전달된 pattern으로 SimpleDateFormat객체생성
		SimpleDateFormat dateFormat=new SimpleDateFormat(pattern);
		
		//2]String->Date: parse() 써서 바꾸기
		Date fDate=dateFormat.parse(stFDate);
		Date sDate=dateFormat.parse(stSDate);
		
		//3]시간차 구하기: getTime()
		long fTime=fDate.getTime();
		long sTime=sDate.getTime();
		long diff=Math.abs(fTime-sTime);
		
		//4]매개변수 delim에 따른 날짜차이 반환
		switch(Character.toUpperCase(delim)) {
		case 'D': return diff/1000/60/60/24;//일단위 
		case 'H': return diff/1000/60/60; //시간단위
		case 'M': return diff/1000/60; //분단위
		default: return diff/1000; //초단위
		}
	}////getDiffBetweenDates

	//문자열을 int[]배열로 변환
	public static int[] convertStringToIntArray(String str) {
		int[] intArray = new int[str.length()];
	    for (int i = 0; i < str.length(); i++) {
	        intArray[i] = (int) str.charAt(i); // 각 문자의 유니코드 값을 정수형 배열에 저장
	    }
	    return intArray; // 변환된 정수형 배열 반환
	}
	
	//이름에서 자음을 구해 반환하는 메소드]
	public static char getJaeum(String name) {
		//김길동->ㄱ,박길동->ㅂ,홍길동->ㅎ
		char[] jaeum=name.toCharArray(); //이름을 캐릭터배열로 변형
		//방법1]->char 숫자로 비교할 수 있음, 유니코드로 대소비교하는것
/*		if(jaeum[0] >='가' && jaeum[0] < '나') return 'ㄱ';
		else if(jaeum[0] >='나' && jaeum[0] <'다') return 'ㄴ';
		else if(jaeum[0] >='다' && jaeum[0] <'라') return 'ㄷ';
		else if(jaeum[0] >='라' && jaeum[0] <'마') return 'ㄹ';
		else if(jaeum[0] >='마' && jaeum[0] <'바') return 'ㅁ';
		else if(jaeum[0] >='바' && jaeum[0] <'사') return 'ㅂ';
		else if(jaeum[0] >='사' && jaeum[0] <'아') return 'ㅅ';
		else if(jaeum[0] >='아' && jaeum[0] <'자') return 'ㅇ';
		else if(jaeum[0] >='자' && jaeum[0] <'차') return 'ㅈ';
		else if(jaeum[0] >='차' && jaeum[0] <'카') return 'ㅊ';
		else if(jaeum[0] >='카' && jaeum[0] <'타') return 'ㅋ';
		else if(jaeum[0] >='타' && jaeum[0] <'파') return 'ㅌ';
		else if(jaeum[0] >='파' && jaeum[0] <'하') return 'ㅍ';
		else if(jaeum[0] >='하' && jaeum[0] <='힣') return 'ㅎ';
		return '0';//한글 아니면 반환 자음이 아닌경우 숫자, 문자 0 -> 영어이름 입력시 숫자 문자 0반환함
*/			
		//방법2]
		char[] startChar= {'가','나','다','라','마','바','사','아','자','차','카','타','파','하'};
		char[] endChar= {'낗','닣','띻','맇','밓','삫','앃','잏','찧','칳','킿','팋','핗','힣'}; 
		char[] returnChar= {'ㄱ','ㄴ','ㄷ','ㄹ','ㅁ','ㅂ','ㅅ','ㅇ','ㅈ','ㅊ','ㅋ','ㅌ','ㅍ','ㅎ'}; 
		
		for(int i=0;i<startChar.length;i++) {
			if(jaeum[0]>=startChar[i]&&jaeum[0]<=endChar[i]) //가~낗 이면 ㄱ반환
				return returnChar[i];
		}
		
		return '0';//한글이 아니면 반환, 자음이 아닌경우 숫자, 문자 0 -> 영어이름 입력시 숫자 문자 0반환함
	}
	
	
	
	
}////class
