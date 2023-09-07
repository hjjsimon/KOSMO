package io24.file;

import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WindowExplorer {
	
	private static void printLine(int size,char symbol) {
		for(int i=0;i<size;i++) {
			System.out.print(symbol);
		}
		System.out.println();
	}
	
	public static void main(String[] args) {

		File f = new File("src/io24/node");
		
		//문]윈도우 탐색기처럼 상기 디렉토리 안에 있는 하위디렉토리나 파일을 출력하여라(단, 디렉토리부터...)
		printLine(106, '=');
		System.out.println(String.format("%-50s%-32s%-14s%s","이름","수정한 날짜","유형","크기"));
		printLine(106, '=');
		
/*		String uRelativeDir="src/io24/node";
		File fuRelativeDir=new File(uRelativeDir);
		String[] names=fuRelativeDir.list();
		File[] files=fuRelativeDir.listFiles();
		String name=null;
		
		for(File file:files) {
			
			if(file.isFile()) name=file.getName();
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd a h:mm");
			String lastModified=dateFormat.format(new Date(file.lastModified()));
			String type="";
			if(file.getName().contains("txt")) type="TXT 파일";
			if(file.getName().contains("java")) type="JAVA 파일";
			if(file.getName().contains("bmp")) type="BMP 파일";
			double doubleNum = (double)(file.length()/1024); //1000이 아니라 1024로 나눠야함
			double ceilNum = Math.ceil(doubleNum);
			System.out.println(String.format("%-50s%-32s%-14s%s",name,lastModified,type,(int)ceilNum+"KB"));
		}*/
		StringBuilder sbDir=new StringBuilder();
		StringBuilder sbFile=new StringBuilder();
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd a h:mm");
		File[] files=f.listFiles();
		//천단위 콤마찍기
		NumberFormat nf=NumberFormat.getInstance();//Locale 안넣어도 됨, 이후 size-> nf.format(size)하면 됨
			
		for(File file:files) {
			//파일명 혹은 디렉토리명
			String name=file.getName();
			//최근 수정된 시간]
			String lastModified=dateFormat.format(new Date(file.lastModified()));
			if(file.isFile()) {
				//파일유형]
				String ext=name.substring(name.lastIndexOf(".")+1).toUpperCase();//.으로 자르고 +1위치
				//파일크기]
				int size=(int)Math.ceil(file.length()/1024.0);//더블에 담아야해서 1024.0으로 나눔
				sbFile.append(String.format("%-50s%-32s%-14s%sKB%n",name,lastModified,ext+" 파일",nf.format(size)));//천단위 콤마찍기
			}
			else {//디렉토리인경우
				sbDir.append(String.format("%-50s%-32s%-14s%n",name,lastModified,"파일 폴더"));
			}
		}
		System.out.println(sbDir+sbFile.toString());
	}
}
