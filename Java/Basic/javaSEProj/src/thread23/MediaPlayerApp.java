package thread23;

import java.io.FileInputStream;
import java.util.Scanner;

import javazoom.jl.player.Player;

public class MediaPlayerApp {
	
	public static void main(String[] args) {
		
		Scanner  sc = new Scanner(System.in);
		MediaPlayer player=null;
		while(true) {
			System.out.println("1.음악 재생 2. 음악 중지 3. 프로그램 종료");
			System.out.println("메뉴번호를 입력하세요?");
			int menu=Integer.parseInt(sc.nextLine().trim());
			if(menu==1) {
				player = new MediaPlayer("music/music.mp3");//C:\Users\kosmo\Downloads\JavaSEProj\JavaSEProj\music
			
				//player.setDaemon(true);//break하면 데몬으로 메인끝나면 같이 끝남
				
				player.start();
			}			
			else if(menu==2) {
				if(player !=null) player.playStop();
			}
			else if(menu==3) System.exit(0);//break;로 while을 나가도 JL플레이어가 실행시킴, 그냥 그런놈
			else System.out.println("잘못 입력하셨습니다");
		}
	}///////////////
}
