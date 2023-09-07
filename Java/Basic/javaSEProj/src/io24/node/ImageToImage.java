package io24.node;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageToImage {

	public static void main(String[] args) {
		//[이미지 카피]
		//데이터소스: 파일, 노드스트림 FileInputStream
		//데이터목적지: 파일, 노드스트림 FileOutputStream
		//이미지파일처럼 바이너리파일(txt로 못엶)인 (jpeg,gif,bmp,avi,mpeg,zip,dll,exe파일등) ->를 복사할 때는 1바이트 단위로 바이트기반의 노드스트림인 FileInputStream/FileOutputStream을 사용한다
		//단, Text파일등을 복사할때는 문자기반의 노드스트림인 FileReader/FileWriter사용하는게 유리
		
		//1]입출력 스트림 객체선언
		FileInputStream fis=null;
		FileOutputStream fos=null;
		try {
			//2]입력스트림 생성
			fis=new FileInputStream("src/io24/node/이질화.bmp");
			//3]출력스트림 생성
			fos=new FileOutputStream("src/io24/node/이질화Copy.bmp");
			//4]fis로 읽고 fos로 출력
			int data;
			long startTime=System.currentTimeMillis();//읽고쓰는데 시간이 얼마나 걸리나 구함, 1/1000초단위로 반환
/*			//필터효과 적용전
			while((data=fis.read())!=-1) {//파일끝 도달시 -1반환, 그전까지 계속 읽어서 data에 담음
				fos.write(data);
				fos.flush();//fos.close호출해서 flush안해도 되는데, 그래도 해야함 중간에 오류발생가능
			}*/
			//필터효과 적용-> 계란판에 담고 내보냄 -> 0.013초 걸림
			byte[] b=new byte[1024];
			while((data=fis.read(b))!=-1) {//이번엔 읽은 바이트수 -> 이전이랑 다름
				//write(배열명,0,읽은 바이트수): 읽은 바이트수만큼 출력
				fos.write(b, 0, data);//읽을때 시작점 0, 읽은 바이트수만큼 써야하므로 data
				fos.flush();
			}
			long endTime=System.currentTimeMillis();
			System.out.println("이미지 복사 소요시간:"+(endTime-startTime)/1000.0+"초");//1000으로 나눠서 초단위로 뺌 ->이미지 복사 소요시간:8.297초 나옴 RAM 32기가라 좋아서그럼, CPU도 i5라 좋음
		} 
		catch (FileNotFoundException e) {
			System.out.println("파일이 존재하지 않아요");
		}
		catch(IOException e){//부모라 밑으로 뺌, fis.read()가 던지는거 처리
			System.out.println("파일 읽기시 오류");
		}
		finally {
			try {
				if(fis!=null) fis.close();//null체크 이것도 try로 잡아줌
				if(fos!=null) fos.close();
			} 
			catch (IOException e) {e.printStackTrace();}
		}
	}

}
