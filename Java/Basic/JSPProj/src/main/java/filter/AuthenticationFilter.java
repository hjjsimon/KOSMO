package filter;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.JWTOkens;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet Filter implementation class AuthenticationFilter
 * New->Filter로 생성(request 선택, 루트는 /bbs08/*)
 * web.xml에 자동으로 <filter>태그 등록된다
 * 1. @WebFilter추가(web.xml에 등록된 <filter>태그 삭제)
 * 2. extends HttpFilter 삭제(클래스 생성시 슈퍼클래스를 지정안하면 됨)
 * 3. doFilter()만 남긴다
 *  
 * 회원제 게시판(bbs08)의 각 JSP페이지의 로그인 인증여부 판단을 위한
 * IsMember.jsp 인클루드 처리한 것을 필터로 교체하자(이제 각 페이지마다 include처리할 필요가 없음)
 * 
 * 로그인 인증여부 판단 코드 추가후에는 각 JSP페이지의 IsMember.jsp 인클루드 처리한 것을 주석처리
 * <jsp:include  page="/common/IsMember.jsp"/> -> 주석처리
 * 
 */
@WebFilter(urlPatterns = "/bbs08/*") //이패턴의 URL 요청은 필터를 거치도록 설정
//public class AuthenticationFilter extends HttpFilter implements Filter {
public class AuthenticationFilter implements Filter {
   
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("사전작업!!!!");
		//사전작업으로 로그인여부 판단
		HttpServletRequest req = (HttpServletRequest)request;
		/*
		//세션기반 인증일때
		Object checkLogin = req.getSession().getAttribute("USER-ID");//로그인 됐는지 영역에서 확인
		if(checkLogin == null) {//로그인 안됨(요청전달x), 사용자에게 보내기 전에 컨테이너에 보낼 응답 만듦
			//방법1-브라우저로 HTML태그를 응답
			HttpServletResponse resp = (HttpServletResponse)response;
			resp.setContentType("text/html; charset=UTF-8");//한글 안깨지게함, 아무 jsp 가서 맨위에꺼 긁어옴
			PrintWriter out = resp.getWriter();
			out.println("<script>");
			out.println("alert('Please Use AfterLogin...')");
			out.println("location.replace('"+req.getContextPath()+"/session06/Login.jsp')");//중간에 변수 처리시 "++" 기억
			out.println("</script>");
			return;
		}
		*/
		//토큰기반 인증일때
		String token = JWTOkens.getToken(req, req.getServletContext().getInitParameter("COOKIE-NAME"));
		boolean isAuthentication = JWTOkens.verifyToken(token, "/resources/tokens", "secret-key");
		if(! isAuthentication) {
			//방법1
			/*
			HttpServletResponse resp = (HttpServletResponse)response;
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>");
			out.println("alert('Please Use AfterLogin(based Tokens)')");
			out.println("location.replace('"+req.getContextPath()+"/session06/LoginBasedToken.jsp')");
			out.println("</script>");
			return;
			*/
			//방법2-로그인페이지로 포워드
			req.setAttribute("NOT-LOGIN", "로그인 후 이용하세요123");
			req.getRequestDispatcher("/session06/LoginBasedToken.jsp").forward(request, response);
			
		}
		else {//인증 안되면 아예 안보냄, 인증시 컨트롤러 갔다옴
			// pass the request along the filter chain
			chain.doFilter(request, response);//로그인 성공! 필터로 요청처리해줌, List.jsp 가서 실행하고 다시 여기로 옴
			//doFilter()는 요청은 무조건 전달이 됨, 주석처리시 요청전달 안됨
			System.out.println("사후작업");
		}
		
		
	}
}
