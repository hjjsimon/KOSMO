/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.1.8
 * Generated at: 2023-05-31 06:45:57 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.error15;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;

public final class ErrorIndex_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports,
                 org.apache.jasper.runtime.JspSourceDirectives {

  private static final jakarta.servlet.jsp.JspFactory _jspxFactory =
          jakarta.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("jakarta.servlet");
    _jspx_imports_packages.add("jakarta.servlet.http");
    _jspx_imports_packages.add("jakarta.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile jakarta.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public boolean getErrorOnELNotFound() {
    return false;
  }

  public jakarta.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final jakarta.servlet.http.HttpServletRequest request, final jakarta.servlet.http.HttpServletResponse response)
      throws java.io.IOException, jakarta.servlet.ServletException {

    if (!jakarta.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final jakarta.servlet.jsp.PageContext pageContext;
    jakarta.servlet.http.HttpSession session = null;
    final jakarta.servlet.ServletContext application;
    final jakarta.servlet.ServletConfig config;
    jakarta.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    jakarta.servlet.jsp.JspWriter _jspx_out = null;
    jakarta.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/template/Top.jsp", out, false);
      out.write("\r\n");
      out.write("    <div class=\"container\" style=\"margin-top:50px\">\r\n");
      out.write("        <div class=\"jumbotron bg-info\">\r\n");
      out.write("            <h1>웹 어플리케이션 예외처리</h1><!-- 프로젝트 완료 후 사용자에게 보여주기 전에 빨간줄 가면 안되니까 처리 -->    \r\n");
      out.write("        </div><!--jumbotron-->\r\n");
      out.write("        <fieldset class=\"form-group border p-3\">\r\n");
      out.write("        	<legend class=\"w-auto px-3\">web.xml설정을 통한 예외처리</legend><!-- try~catch말고 -->\r\n");
      out.write("        	");

        		//방법1]try~catch사용
				/*
				try{
	        		out.println("HTTP메소드:"+request.getMethod()); //GET
	        		out.println("파라미터의 문자열 길이:"+request.getParameter("name").length());
	        		//최초 파라미터 없으니 name이 null임, NullPointException, 쿼리스트링 전달시 해결가능
				}
        		catch(NullPointerException e){
        			out.println("<h1 class='display-4'>관리자에게 문의하세요</h1>");
        			return;
        		}*/
        		//방법2]page지시어 속성 사용(위의 <%@ 안에 errorpage 설정)

	        	//방법3]web.xml의 설정으로 예외 처리- 전역설정으로 페이지마다 설정 불필요
				//     즉 web.xml에 설정만 하면 모든 .jsp혹은 서블릿에서 적용된다.
				/*
		       		-개발완료후 범용적인 에러 처리시
		        	 방법1:에러코드로(ex.서버측에러 500)
		         	 방법2:예외 클래스 직접 지정(ex.nullpoint 등 다양한 에러가 500에 해당, 이게 더 구체적이므로 2개 동시 설정시 이게 우선함)
		         	 예외 클래스 직접 지정이 우선한다.
		        
		       		location:컨텍스트 루트를 제외한 /로 시작하는 웹상의 경로
		       		try~catch불필요
		       		포워드로 에러 처리 페이지로 이동시킨다
		       		
		       		ex) web.xml에 아래 설정
		       		<error-page>
		       	  	<error-code>500</error-code>
		       	  	<location>/error15/ErrorCode.jsp</location><!-- 모든 JSPProj에서 500에러시 여기로 포워드됨 -->
		       	  	</error-page>
		       		
	   			*/
				request.setAttribute("reqVar", "리퀘스트 영역입니다");
        		out.println("HTTP메소드:"+request.getMethod()); //GET
        		out.println("파라미터의 문자열 길이:"+request.getParameter("name").length());
        	
        	
      out.write("\r\n");
      out.write("        </fieldset>        \r\n");
      out.write("    </div><!--container-->\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/template/Footer.jsp", out, false);
    } catch (java.lang.Throwable t) {
      if (!(t instanceof jakarta.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}