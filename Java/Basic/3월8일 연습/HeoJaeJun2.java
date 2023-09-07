import java.util.Date;
import java.text.SimpleDateFormat;

public class HeoJaeJun2
{
	public static void main(String[] args)
	{
		Date day = new Date();
		System.out.println(day);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd a HH:mm:ss");
		String today = dateFormat.format(day);
		System.out.println(today);
		System.out.println("프로그램 종료~");
	}
}