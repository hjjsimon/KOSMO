package com.kosmo.springapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class RuntimeInterceptor implements HandlerInterceptor{//인터셉터! 얘도 필터임
	
	//특정 패턴의 URL요청에 대해서 컨트롤러 메소등의 실행시간 구하기
	private long startTime,endTime;
	 
	public RuntimeInterceptor() {
		System.out.println("RuntimeInterceptor 생성");//스프링컨텍스트에 빈으로 등록안돼서 출력안됨
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		//핸들러가 컨트롤러, 컨트롤러 다양 메모컨트롤러, 커멘트컨트롤러.. 다양하므로 Object로 받음
		throws Exception {
			System.out.println("[RuntimeInterceptor-preHandle메소드]");
			//URI는 루트 요청 HelloSpringController 참고
			System.out.println("request:" + request + ",HTTP Method:" + request.getMethod() + ",URI:" + request.getRequestURI());
			System.out.println("handler:" + handler.toString() );
			startTime=System.currentTimeMillis();
			return true;//true는 무조건 요청전달
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
				ModelAndView modelAndView) throws Exception { //컨트롤러가 디스패처서블릿에 보내는 모델(데이터), 뷰정보가 modelandview
			System.out.println("[RuntimeInterceptor-postHandle]");
			if(modelAndView != null)//IndexController에서 ModelAndView 반환하는게 없어서 아래 값이 null나와 에러
				System.out.println("ModelAndView - 뷰 명 : " + modelAndView.getViewName() + ",데이타 :" + modelAndView.getModel() + "]" );
		    endTime=System.currentTimeMillis();
		    System.out.println("핸들러(컨트롤러 메소드 실행시간):"+(endTime-startTime)/1000.0+"초");
		}
	
	@Override
		public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
				throws Exception {
			System.out.println("[RuntimeInterceptor-afterCompletion메소드]");
		}
	
	
	
	
	
	
	
	
	
}












