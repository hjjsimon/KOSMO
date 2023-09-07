package listener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class MyContextListener implements ServletContextListener{
	//web.xml에 등록x, 위에 @WebListener도 없어서 얘는 작동안함, 원래 서버껐다가 JSPProj 실행시 출력되야함(지금 등록해서 서버키면 출력됨)

	public MyContextListener() {
		System.out.println("MyContextListener의 생성자");//web.xml에 등록하거나 @WebListener하면, 원래 new하고 생성자 해야하는데 안해도 자동으로 초기화 됨
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {//서블릿 컨텍스트 관련 이벤트 정보
		System.out.println("웹 어플리케이션이 시작되었습니다");//웹 어플리케이션이 시작되었습니다
		System.out.println("서버 정보:"+sce.getServletContext().getServerInfo());//서버 정보:Apache Tomcat/10.1.8 출력됨, sce에 서버 정보 다 들어있음
		System.out.println("컨텍스트 루트:"+sce.getServletContext().getContextPath());//컨텍스트 루트:/JSPProj
		try {
			Context ctx = new InitialContext();//리스너 실행시 이 코드 시작
			DataSource source = (DataSource)ctx.lookup(sce.getServletContext().getInitParameter("JNDI-ROOT")+"/jsp");
			//데이터소스 얻고 모든 서블릿에서 사용가능하도록 할 것, 컨텍스트 초기화파라미터로 얻어와야함			
			//그리고 env 이후 /jsp 붙여야함
			//source 지역변수인데 어플리케이션 내의 서블릿이 어떻게 사용할 수 있을까, 서블릿컨텍스트 application 가져와서 set
			sce.getServletContext().setAttribute("DataSource", source);//데이터소스 속성명으로 얻어올 수 있음, 모든 서블릿이 공유 가능
			
		}
		catch (NamingException e) {e.printStackTrace();}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("웹 어플리케이션이 종료되었습니다");//DB연결은 웹어플리케이션 종료랑 같이하면 안됨, 연결 인스턴스가 계속 남으면 나중에 느려짐 
		//서버 꺼지면 웹어플리케이션도 꺼지니까 메모리에서 메소드 사라짐, 그래서 뭐 안된대, 근데 출력되는데?, 아 맞음, 종료되면 뜬다
		
	}

}
