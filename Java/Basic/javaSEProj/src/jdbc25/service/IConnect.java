package jdbc25.service;

public interface IConnect {//반복코딩 줄이자!->작업쉽게 하려고 인터페이스 만들어놓은것
	//멤버 상수(앞에 public static final 생략)
	String OACLE_DRIVER="oracle.jdbc.OracleDriver";
	String ORACLE_URL="jdbc:oracle:thin:@127.0.0.1:1521:xe";
	//추상 메소드
	void connect(String url,String user,String password);
	void execute() throws Exception;//exception 던지는 메소드
	void close();
	String getValue(String message);
	String getQueryString();
}
