package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = "/bbs08/*")//처리시 web.xml 필터등록 생략가능
public class RuntimeFilter implements Filter{//자카르타꺼 filter 임포트
	
	public RuntimeFilter() {
		System.out.println("RuntimeFilter의 생성자");
	}
	
	@Override
	public void destroy() {
		System.out.println("RuntimeFilter의 destroy() invoked");//호출하는게 아님, invoked 된다고함 
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("RuntimeFilter의 init() invoked");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		
		System.out.println("RuntimeFilter의 doFilter() invoked");
		///bbs08/*이 들어간 패턴의 요청을 하면 이게 출력됨 ex. HelloWorld 실행하면 안뜸
		//요청 전달이 안됨, 요청 가로채고 chain에 doFilter()해야 요청이 전달됨, 필터 구현시 반드시 doFilter()호출해야함
		//JSPProj 실행 후 게시판 누르면 /bbs08/List.jsp인데 요청해도 흰 화면, 응답 안해줌(List.jsp로 이동하는걸 필터가 가로채는것)
		//아래코드 미호출시 요청이 .JSP나 요청을 처리하는 서블릿으로 전달안된다(브라우저에 아무것도 출력안됨)
		//chain.doFilter(request, response);//이거 미호출시 요청 전달이 안됨, 이거 호출해야 요청 전달이 됨(이제 게시판이 응답해줌)
		//즉, 위 코드를 반드시 작성해야 요청이 전달된다
		
		//[JSP나 서블릿의 요청처리시간을 측정하는 작업을 필터로 구현]
		//사전작업코드(JSP나 요청처리용 서블릿에 요청전달 전)-JSP나 서블릿의 요청처리시간 측정
		System.out.println("[doFilter() 호출전에 사전작업]");
		HttpServletRequest req = (HttpServletRequest)request;//위의 request는 ServletRequest타입이라 형변환
		String uri = req.getRequestURI();//사용자 요청 URL 얻기, 이건 HttpServletRequest에만 있어서 req 만들어야함
		long startTime = System.currentTimeMillis();//시작시간 얻기
		chain.doFilter(request, response);//사전,사후 기준(요청 전달: 서블릿 요청에 필수)
		
		//사후작업코드(JSP나 요청처리용 서블릿에 응답받은 후)
		System.out.println("[doFilter() 호출전에 사후작업]");
		long endTime = System.currentTimeMillis();//종료시간 얻기
		System.out.println(String.format("요청URI:%s,요청처리시간:%s",uri,(endTime-startTime)/1000.0+"초"));		
		
		
		
		
		
		
	}
}
