package eltaglib;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContext;
import model.PagingUtil;
import model.bbs.BBSDao;
import model.bbs.BBSDto;

public class MyTagLib {//public static 필수
	
	//[문자열이 숫자형식이면 true, 아니면 false반환]
	public static boolean isNumber(String value) { //클래스명으로 접근, 숫자면 false, 아니면 true 반환
		for (int i=0; i<value.length(); i++) { //WrapperClass03에서 가져옴
			int codeValue=Character.codePointAt(value, i);
			if(!(codeValue>='0'&&codeValue<='9')) return false; 
		}	
		return true ;
	}//////////////////isNumber
	/*
	 * 주민번호 예]123456-1234567
	    9 : 1800 ~ 1899년에 태어난 남성
		0 : 1800 ~ 1899년에 태어난 여성
		1 : 1900 ~ 1999년에 태어난 남성
		2 : 1900 ~ 1999년에 태어난 여성
		3 : 2000 ~ 2099년에 태어난 남성
		4 : 2000 ~ 2099년에 태어난 여성
		5 : 1900 ~ 1999년에 태어난 외국인 남성
		6 : 1900 ~ 1999년에 태어난 외국인 여성
		7 : 2000 ~ 2099년에 태어난 외국인 남성
		8 : 2000 ~ 2099년에 태어난 외국인 여성	
	 */
	public static String getGender(String ssn) {
		
		int beginIndex = ssn.indexOf("-")+1;// -가 처음 나오는 위치 찾고 +1 위치, 123456-9876543에서 (beginIndex에 7저장)
		int endIndex = beginIndex+1;// 위 숫자에서 9위치(endIndex에 8저장)
		try {
			int gender = Integer.parseInt(ssn.substring(beginIndex, endIndex)) ;//대쉬 이하 첫번째인 숫자9 추출해옴
			//substring() 메소드는 string 객체의 시작 인덱스로 부터 종료 인덱스 전 까지 문자열의 부분 문자열을 반환합니다. 7~8자리 중 8전에 즉 7~7숫자 찾음
			switch(gender) {
				case 1:
				case 3: 
				case 9: return "남성";
				case 0:
				case 2: 
				case 4: return "여성";
				case 5:
				case 7: return "외국인 남성";
				default: return "외국인 여성";
			}
		}
		catch (Exception e) {
			return "유효하지 않은 주민번호입니다";
		}
	}
	public static boolean isMember(ServletContext context, String username, String password) {
		
		BBSDao dao = new BBSDao(context);
		boolean flag = dao.isMember(username, password);
		dao.close();
		return flag;
	}
	
	public static List<BBSDto> selectList(ServletContext context, int beginIndex, int endIndex) {
		
		BBSDao dao = new BBSDao(context);
		Map map = new HashMap();
		map.put(PagingUtil.START, beginIndex);//BBSDao에서 selectList보면 시작,끝 번호 담고있음, 이거 put해줘야함
		map.put(PagingUtil.END, endIndex);
		List<BBSDto> response = dao.selectList(map);
		dao.close();
		return response;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
