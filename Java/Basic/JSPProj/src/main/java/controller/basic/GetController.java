package controller.basic;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 	서블릿 3.0부터 아래 어노테이션 지원.
 	web.xml에 서블릿 등록 불 필요

	@WebServlet(urlPatterns =  "URL패턴",initParams = {@WebInitParam(name = "파라미터명",value ="파라미터값" ),@WebInitParam(name = "파라미터명",value ="파라미터값" )})
	서블릿 초기화 파라미터는 InitParam사용, {}는 배열 의미
	
	@WebServlet("/Basic/get.kosmo")
*/

//1.HttpServelt상속(상속받아야 서블릿됨)
public class GetController extends HttpServlet  {
	//호출 순서: init()(최초 요청시) -> service()(요청시마다:doGet() 혹은 doPost()등 호출) -> destroy()(서버 중지시)
	//현재 a태그라 get방식이니 doget으로 오버라이딩함	
	
	public GetController() {
		System.out.println("서블릿의 생성자 GetController():GetController");
	}
	
	@Override
	public void destroy() {
		System.out.println("서블릿의 destroy():GetController");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("서블릿의 init():GetController");
	}
	//2.GET방식으로 요청이 들어옴:doGet오버라이딩
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("서블릿의 doGet():GetController");
		//3.리퀘스트 영역에 데이타 저장
		req.setAttribute("GET", "GET방식 요청입니다");//서블릿이 요청받음, request에 저장하고 포워드, EL로 출력할 예정, GET이란 이름으로 저장, ${GET}시에 가져옴
		//4.뷰로(JSP페이지)로 포워딩
		//4-1.뷰 선택
		RequestDispatcher dispatcher= req.getRequestDispatcher("/servlet13/ServletBasic.jsp");//JSP페이지니까 디렉토리 경로
		//4-2.뷰로 포워딩
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("서블릿의 doPost():GetController");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("서블릿 진입");
		super.service(req, resp);//주석 처리시 doGet/doPost 호출 안됨 즉 이 코드에 의해서 doGet()호출-다시 service()로
		//위 코드 실행시 doGet()으로 가서 다 2~4-2까지 실행 후 아래 출력한대
		System.out.println("서블릿의 service():GetController");//service는 get,post 둘다 출력됨
		/*
		 super.service(req, res)는 부모 클래스인 GenericServlet의 service 메서드를 호출하는 코드입니다. 
		 super 키워드는 부모 클래스를 참조할 때 사용되며, 여기서는 GenericServlet 클래스의 service 메서드를 호출하고 있습니다. 
		 이를 통해 상위 클래스의 service 메서드가 먼저 실행되고, 이후 서브 클래스에서 추가적인 로직을 구현할 수 있습니다.
		 */
	}
	
	
}
