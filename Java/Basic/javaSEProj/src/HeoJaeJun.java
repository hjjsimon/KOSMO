import java.lang.String;
import java.util.Date;
import java.text.SimpleDateFormat;

public class HeoJaeJun
{//클래스 블락 시작

	public static void main(String[] args)
	{//main 시작
		//오늘 날짜 출력하기
		Date day = new Date();
		System.out.println(day);
		//2023-03-08 오후 17:00:36 형식으로 출력
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd a HH:mm:ss");
		String today = dateFormat.format(day);
		System.out.println(today);
		System.out.println("프로그램 종료!!!");
	}//main 끝

}//클래스 블락 끝