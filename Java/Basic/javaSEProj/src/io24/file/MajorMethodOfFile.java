package io24.file;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MajorMethodOfFile {
	
	//문] File클래스의 getName()과 같은 역할을 하는 나만의 메소드를 정의하자
	
	private static String getName(String pathName) {
		
		
/*		if(pathName.contains("\\")) {
			String[] rSlash=pathName.split("\\");
			return rSlash[rSlash.length-1];
		}
		else {
			String[] slash=pathName.split("/");
			return slash[slash.length-1];
		}*/
		//방법1]lastIndexOf 및 substring
		//int beginIndex=pathName.lastIndexOf("\\")!=-1? pathName.lastIndexOf("\\")+1:pathName.lastIndexOf("/")+1;//-1이 아니면 역슬래시가 있다는것
		//return pathName.substring(beginIndex);
		//방법2]split 및 정규표현식
		String[] re=pathName.split("[/\\\\]"); // /나 \\있으면 -> \\\\가 \\를 하나로 본것 
		return re[re.length-1];
	}

	public static void main(String[] args) {

		// \: 디렉토리의 윈도우식 표기법
		// /: 디렉토리의 UNIX(LINUX)식 표기법
		String wExistFile="D:\\HJJ\\Workspace\\Java\\Basic\\javaSEProj\\src\\HelloWorld.java";//w는 윈도우뜻
		String wNoExistFile="D:\\HJJ\\NoExist.txt";//그냥 없는 파일 막 씀
		String uRelativeDir="src/io24/node";//u는 유닉스, relativeDir 상대경로
		String uAbsoluteDir="D:/HJJ/NODIR";//이것도 없는 디렉토리임, 드라이브(ex. D)부터 시작하면 절대경로
		//1]File 객체 생성
		//1-1]파일에 대한 정보를 가진 File객체
		File fwExistFile=new File(wExistFile);//괄호안에 경로 넣음, 파일객체라고 f붙여줌 
		File fwNoExistFile=new File(wNoExistFile);//파일 없어도 에러는 안남
		//1-2]디렉토리에 대한 정보를 가진 File객체
		File fuRelativeDir=new File(uRelativeDir);
		File fuAbsoluteDir=new File(uAbsoluteDir);
		//2]파일명 혹은 디렉토리명 얻기:String getName()
		System.out.println("fwExistFile의 파일명:"+fwExistFile.getName()); //HelloWorld.java 출력
		System.out.println("fwNoExistFile의 파일명:"+fwNoExistFile.getName()); //없는 파일인데 에러안남
		System.out.println("fuRelativeDir의 디렉토리명:"+fuRelativeDir.getName());
		System.out.println("fuAbsoluteDir의 디렉토리명:"+fuAbsoluteDir.getName());
		System.out.println("[내가 만든 getName()메소드]");
		System.out.println("wExistFile의 파일명:"+getName(wExistFile)); 
		System.out.println("wNoExistFile의 파일명:"+getName(wNoExistFile)); 
		System.out.println("uRelativeDir의 디렉토리명:"+getName(uRelativeDir));
		System.out.println("uAbsoluteDir의 디렉토리명:"+getName(uAbsoluteDir));
		//3]파일 혹은 디렉토리 여부 판단:
		//boolean isFile() 혹은 boolean isDirectory()
		//isFile():파일이 아니거나, 존재하지 않으면 false반환
		//isDirectory():디렉토리가 아니거나, 존재하지 않으면 false반환
		System.out.println(fwExistFile.isFile());//t
		System.out.println(fwNoExistFile.isFile());//f
		System.out.println(fuRelativeDir.isFile());//f, 디렉토리 존재는 맞는데 파일이 아님
		System.out.println(fuRelativeDir.isDirectory());//t
		System.out.println(fuAbsoluteDir.isDirectory());//f, 디렉토리는 맞는데 없음
		System.out.println(fwExistFile.isDirectory());//f, 디렉토리가 아님
		//4]파일 혹은 디렉토리 존재여부판단: boolean exists()-> 항상 존재여부판단을 먼저 하고 위 메소드로 파일인지 디렉토리인지 판단
		System.out.println(fwExistFile.exists());//t 존재함
		System.out.println(fwNoExistFile.exists());//f
		System.out.println(fuRelativeDir.exists());//t
		System.out.println(fuAbsoluteDir.exists());//f
		System.out.println(fwExistFile.exists()? fwExistFile.isFile()? "파일이다":"디렉토리다":"존재하지않는다" );//이중삼항연산자
		//5]파일 혹은 디렉토리의 상대경로: getPath(), 절대경로: getAbsolutePath() 얻기
		//자바 프로그램에서 상대경로로 표기하고 절대경로를 얻고자할때 주로 getAbsolutePath() 사용
		System.out.println("fuAbsoluteDir의 상대경로:"+fuAbsoluteDir.getPath());
		System.out.println("fuAbsoluteDir의 절대경로:"+fuAbsoluteDir.getAbsolutePath());//이건 절대경로나 상대경로 동일
		System.out.println("fuRelativeDir의 상대경로:"+fuRelativeDir.getPath());//src\io24\node
		System.out.println("fuRelativeDir의 절대경로:"+fuRelativeDir.getAbsolutePath());//D:\HJJ\Workspace\Java\Basic\javaSEProj\src\io24\node
		//6]파일 혹은 디렉토리의 크기얻기(단위:바이트): long length() -> 바이트단위라 크기가 커서 long형 반환
		//1.디렉토리는 OS에 따라서 0을 반환하거나 일부 크기를 반환할 수 있다. 
		//2.파일은 존재하면 해당크기를 반환, 없으면 0반환
		//length()메소드 사용시 크기가 0인경우
		//-디렉토리(OS에 따라 다름)
		//-파일형식이지만 파일이 존재하지 않는경우
		//-파일이 존재하지만 크기가 0인경우
		//디렉토리의 경우 윈도우10은 크기반환하지만 실제크기가 아님
		System.out.println(fwExistFile.length()+"바이트");//헬로우월드 파일 619바이트임
		System.out.println(fwNoExistFile.length()+"바이트");//없으니까 0바이트
		System.out.println(fuRelativeDir.length()+"바이트");//5.85MB 인데 4096바이트라고 나옴, 틀림, 디렉토리는 그냥 크기 찾지마
		System.out.println(fuAbsoluteDir.length()+"바이트");//없으니까 0바이트
		//7]상위 부모경로 얻기: getParent()
		//주로 디렉토리에 적용하는데, 파일객체를 생성시 상대경로든 절대경로든 여러 경로를 준 경우에 사용
		//맨 마지막이 자식, src/io24/node면 node가 자식->src/io24 반환
		System.out.println(fwExistFile.getParent());
		System.out.println(fwNoExistFile.getParent());
		System.out.println(fuRelativeDir.getParent());
		System.out.println(fuAbsoluteDir.getParent());

		File fone=new File("file"); //경로가 한단계
		System.out.println(fone.getParent());//부모 없으면 null
		File ftwo=new File("parent","child");
		System.out.println(ftwo.getParent());//위 코드는 부모 parent, 자식 child를 정한것, 부모 parent출력
		//8]디렉토리 혹은 파일삭제: boolean delete(), 삭제 성공시 true, 실패시 false
		System.out.println(fwNoExistFile.delete());//없는 파일이니까 false
		File fexist=new File("D:\\HJJ\\새 텍스트 문서.txt");
		System.out.println(fexist.delete());//삭제하면 true 나옴, 또 실행시 없어졌으니 false나옴
		System.out.println(fuRelativeDir.delete());//false, 디렉토리는 내부가 비어야 삭제가능함 
		System.out.println(new File("D:\\HJJ\\논병아리").delete());//true, 논병아리 폴더 새로 만든거 지움->귀찮아서 변수안담음
		//9]디렉토리 생성:boolean mkdir()/mkdirs() //mkdir 도스명령어랑 동일 -> make directory 약자임
		//mkdir():부모 디렉토리 존재하지 않으면 생성실패
		//mkdirs():부모 디렉토리까지 생성해줌
		File fmkdir=new File("src/io24/node/temp");
		System.out.println(fmkdir.mkdir());//true나옴, temp 폴더가 만들어짐
		System.out.println(new File("src/io24/mynode/temp").mkdir());//f, mynode 부모디렉토리 없어서 안만들어짐
		System.out.println(new File("src/io24/node/nodir/temp").mkdirs());//t, temp위에 nodir없음 근데 s붙으면 만들어짐
	
		//읽기전용 체크하면 원본수정 불가, 다른이름으로만 저장해야함
		//리눅스에서는 디렉토리도 파일임, 디렉토리면 d를 붙임 -> drwx, r=read읽기가능, w=write쓰기가능, x=executable실행가능
		//10]읽기/쓰기/실행 가능판단(윈도우계열 OS는 모든 파일이 디폴트로 실행가능)
		File fcan=new File("D:/HJJ/can.txt");
		System.out.println(fcan.canRead()?"읽기가능":"읽기불가");
		System.out.println(fcan.canWrite()?"쓰기가능":"쓰기불가");//읽기전용 체크시 쓰기불가 나옴
		System.out.println(fcan.canExecute()?"실행가능":"실행불가");
		//11]파일 혹은 디렉토리의 최근 수정된 시간 얻기
		//long lastModified():1970년 1월1일 0시0분0초부터 현재까지 흘러온 시간을 1000분의 1초 단위로 환산해 long형으로 반환
		long time=fcan.lastModified();//long형반환
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd a h:mm");
		String lastString=dateFormat.format(new Date(time)); //long(밀리초)을 Date로 바꿔줌 Date() 메소드
		System.out.println("최근 수정된 시간:"+lastString);
		//12]파일 이름변경: boolean renameTo(File dest)
		//src.renameTo(dest) src와 dest는 둘 다 파일객체
		//같은 폴더 안: 파일명 변경효과(rename)
		//다른 폴더 안으로: 파일 이동효과(cut and paste)
		File fchange=new File("d:/hjj/change.txt");//소문자로 해도됨
		System.out.println(fcan.renameTo(fchange));//can.txt이름을 change.txt로 바꿀예정, true반환
		File fmove=new File("d:/hjj/교안/move.txt");//change.txt파일을 잘라서 move로 바꿔 옮김
		System.out.println(fchange.renameTo(fmove));
		//13]파일목록 및 디렉토리목록 얻어오기
		//String[] list(): 해당 디렉토리안에 있는 파일 및 디렉토리명만 얻어옴(그래서 String배열반환)
		//File[] listFiles(): 해당 디렉토리안에 있는 파일 및 디렉토리에 대한 파일객체 배열 반환(File배열반환하므로 위의 많은 메소드들을 엮어쓸 수 있음)
		System.out.println("[파일 및 디렉토리명만 반환]");
		String[] names=fuRelativeDir.list();
		for(String name:names) System.out.println(name);//"src/io24/node" 안의 모든 파일, 디렉토리명 반환
		System.out.println("[File[] 반환]");
		File[] files=fuRelativeDir.listFiles();
		for(File f:files) {
			//파일명 혹은 디렉토리명
			String name=f.getName();
			//최근 수정된 시간
			String lastModified=dateFormat.format(new Date(f.lastModified()));
			if(f.isFile()) System.out.print("파일명:"+name);//print로 한줄출력
			else System.out.print("디렉토리명:"+name);
			System.out.println(",최근 수정된 시간:"+lastModified); 
		}
		
	}///////////main
	
}//////////////class
