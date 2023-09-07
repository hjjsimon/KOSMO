package thread23;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.player.Player;

/*
 선행작업 :1.http://www.java2s.com/Code/Jar/j/Downloadjlayer101jar.htm에서 
         	Download jlayer-1.0.1-1.jar클릭하여 다운로드
         2.lib폴더 만든후 다운로드한 jar붙여넣기
           프로젝트 선택후 마우스 우클릭->Build Path->Configure Build Path->
           Libraries탭에서 Classpath선택후->Add External Jars후 lib폴더에 있는
           라이브러리 선택하여 클래스패스에 추가
         3.music폴더 만들어 mp3파일 저장
         
 */

public class MediaPlayer extends Thread {
	private Player player;
    private FileInputStream fis;
    String filePath;
    public MediaPlayer(String filePath) {    	
    	this.filePath=filePath;    	
    }
    public void play() {
        try {     
        	fis = new FileInputStream(filePath);
            player = new Player(fis);           
            player.play();
        } 
        catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
    public void playStop(){
        if (player != null) {
            player.close();
            player = null;
        }
    }
    @Override
    public void run() {    	
    	play();    		
    }
}
