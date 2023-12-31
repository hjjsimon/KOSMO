/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.1.8
 * Generated at: 2023-05-25 08:10:52 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.builtinobject03;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import java.util.Enumeration;

public final class ApplicationForMajorMethod_005f1_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports,
                 org.apache.jasper.runtime.JspSourceDirectives {


	//선언부에서 물리적경로 얻는 방법들]
	//1]this키워드 사용
	private String getRealPath(){
		return this.getServletContext().getRealPath("/images");
	}
	//2]매개변수로 내장객체를 전달받으면 됨
	private String getRealPath(ServletContext app){//request받아도 됨
		return app.getRealPath("/images");
	}
	//3]멤버변수 사용
	private ServletContext app;
	private String getRealPath_(){
		return app.getRealPath("/images");//app는 두줄 위 코드, null임, 초기화 해줘야함
	}


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
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.util.Enumeration");
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("   \r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"ko\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("    <!--  \r\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css\">\r\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js\"></script>\r\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js\"></script>\r\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js\"></script>\r\n");
      out.write("    -->\r\n");
      out.write("     <link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/bootstrap/css/bootstrap.min.css\">\r\n");
      out.write("    <script src=\"");
      out.print(request.getContextPath() );
      out.write("/bootstrap/js/jquery.slim.min.js\"></script>\r\n");
      out.write("    <script src=\"");
      out.print(request.getContextPath() );
      out.write("/bootstrap/js/popper.min.js\"></script>\r\n");
      out.write("    <script src=\"");
      out.print(request.getContextPath() );
      out.write("/bootstrap/js/bootstrap.bundle.min.js\"></script>\r\n");
      out.write("    <script src=\"https://kit.fontawesome.com/0b4621b427.js\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("    <title>ApplicationForMajorMethod_1.jsp</title>\r\n");
      out.write("    <style>\r\n");
      out.write("        /*점보트론 세로폭 및 마진바툼 줄이기*/\r\n");
      out.write("        .jumbotron{\r\n");
      out.write("            padding-top:1rem;\r\n");
      out.write("            padding-bottom:1rem;            \r\n");
      out.write("            margin-bottom: .5rem;\r\n");
      out.write("            \r\n");
      out.write("            border-top-left-radius:0;\r\n");
      out.write("            border-top-right-radius:0;\r\n");
      out.write("        }\r\n");
      out.write("    </style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	<!-- 네비게이션 바  -->\r\n");
      out.write("	<!--상단 고정-->\r\n");
      out.write("	<nav class=\"navbar navbar-expand-sm bg-dark navbar-dark fixed-top\">   \r\n");
      out.write("      <!--Brand / Logo 표시-->      \r\n");
      out.write("      <a class=\"navbar-brand\" href=\"#\"><i class=\"fa-solid fa-house-chimney\"></i></a>\r\n");
      out.write("      <!-- Navbar text-->\r\n");
      out.write("      <span class=\"navbar-text\">\r\n");
      out.write("        모두를 위한 플랫폼\r\n");
      out.write("      </span>\r\n");
      out.write("      \r\n");
      out.write("      <!-- Toggler/collapsibe Button -->\r\n");
      out.write("      <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#collapsibleNavbar\">\r\n");
      out.write("        <span class=\"navbar-toggler-icon\"></span>\r\n");
      out.write("      </button>\r\n");
      out.write("\r\n");
      out.write("      <div class=\"collapse navbar-collapse justify-content-end\" id=\"collapsibleNavbar\">\r\n");
      out.write("       \r\n");
      out.write("        <!-- Links -->\r\n");
      out.write("        <ul class=\"navbar-nav mr-3\"><!--mr-3은 Navbar text와의 여백용-->\r\n");
      out.write("          <li class=\"nav-item\">\r\n");
      out.write("            <a class=\"nav-link active\" href=\"#\">Link 1</a>\r\n");
      out.write("          </li>\r\n");
      out.write("          <li class=\"nav-item\">\r\n");
      out.write("            <a class=\"nav-link \" href=\"#\">Link 2</a>\r\n");
      out.write("          </li>\r\n");
      out.write("          <!--Navbar With Dropdown-->\r\n");
      out.write("          <!--하단고정일때는 dropdown을 dropup으로-->\r\n");
      out.write("          <li class=\"nav-item dropdown\">\r\n");
      out.write("            <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"navbardrop\" data-toggle=\"dropdown\">Link 3</a>\r\n");
      out.write("            <div class=\"dropdown-menu\">\r\n");
      out.write("              <a class=\"dropdown-item\" href=\"#\">SubLink 1</a>\r\n");
      out.write("              <a class=\"dropdown-item\" href=\"#\">SubLink 2</a>\r\n");
      out.write("              <a class=\"dropdown-item\" href=\"#\">SubLink 3</a>\r\n");
      out.write("            </div>\r\n");
      out.write("          </li>\r\n");
      out.write("          <li class=\"nav-item\">\r\n");
      out.write("            <form class=\"form-inline\" action=\"#\">\r\n");
      out.write("              <input class=\"form-control mr-sm-2\" type=\"text\" placeholder=\"검색어 입력\">\r\n");
      out.write("              <button class=\"btn btn-success\" type=\"submit\">검색</button>\r\n");
      out.write("            </form>\r\n");
      out.write("          </li>\r\n");
      out.write("        </ul>\r\n");
      out.write("      </div>\r\n");
      out.write("    </nav>\r\n");
      out.write("    <div class=\"container\" style=\"margin-top:50px\">\r\n");
      out.write("        <div class=\"jumbotron bg-info\">\r\n");
      out.write("            <h1>application 내장객체</h1>            \r\n");
      out.write("        </div><!--jumbotron-->\r\n");
      out.write("        <fieldset class=\"form-group border p-3\">\r\n");
      out.write("        	<legend class=\"w-auto px-3\">주요 메소드</legend>\r\n");
      out.write("        	<h2>컨텍스트 초기화 파라미터 얻기: getInitParameter(\"파라미터명\")</h2>\r\n");
      out.write("        	<ul class=\"list-unstyled\">\r\n");
      out.write("        		<!-- web.xml에 파라미터명,값 추가하고옴 -->\r\n");
      out.write("        		<!-- \r\n");
      out.write("        			컨텍스트 초기화 파라미터\r\n");
      out.write("        			목적] 웹어플리케이션(컨텍스트)을 구성하는 모든 jsp(서블릿)에서 스트링형 상수를 공유하기 위함\r\n");
      out.write("        			step1] web.xml에 컨텍스트 초기화 파라미터 등록\r\n");
      out.write("        			step2] ServletContext의 getInitParameter(\"파라미터명\") 메소드로 얻기\r\n");
      out.write("        		 -->\r\n");
      out.write("        		<li>오라클 드라이버:");
      out.print(application.getInitParameter("ORACLE-DRIVER") );
      out.write("</li>\r\n");
      out.write("        		<li>오라클 주소:");
      out.print(application.getInitParameter("ORACLE-URL") );
      out.write("</li>\r\n");
      out.write("        		<!-- this.getInitParameter(\"파라미터명\")는 서블릿 초기화 파라미터 \r\n");
      out.write("        			이건 해당 서블릿(해당 페이지)에서만 접근가능, 다른 페이지에서는 접근불가(위의 컨텍스트 초기화 중 공유랑 다른 것)-->\r\n");
      out.write("        		<li>this.getInitParameter(\"파라미터명\"):");
      out.print(this.getInitParameter("ORACLE-DRIVER") );
      out.write("</li><!-- null -->\r\n");
      out.write("        		<!-- this는 현재 페이지(클래스)를 의미, 현재 페이지는 서블릿, 서블릿 초기화 파라미터 \r\n");
      out.write("        			근데 지금 jsp잖아 서블릿 아니고, jsp에서 초기화하는방법다 있음-->\r\n");
      out.write("        		<li>this.getInitParameter(\"파라미터명\"):");
      out.print(this.getInitParameter("SERVLET-INIT-PARAMETER") );
      out.write("</li>\r\n");
      out.write("        	</ul>\r\n");
      out.write("        	<h2>모든 컨텍스트 초기화 파라미터명 얻기: getInitParameterNames();</h2>\r\n");
      out.write("       		<ul class=\"list-unstyled\">\r\n");
      out.write("       			");
 
       				Enumeration<String> names = application.getInitParameterNames();
       				while(names.hasMoreElements()){
       					//컨텍스트 초기화 파라미터명 얻기
       					String paramName = names.nextElement();
       					//컨텍스트 초기화 파라미터값 얻기
       					String paramValue = application.getInitParameter(paramName);
       			
      out.write("\r\n");
      out.write("       			<li>");
      out.print(paramName );
      out.write(' ');
      out.write(':');
      out.write(' ');
      out.print(paramValue );
      out.write("</li>\r\n");
      out.write("       			");
}//while 
      out.write("\r\n");
      out.write("       		</ul>\r\n");
      out.write("       		<h2>서버의 물리적 경로 얻기(서버에 파일업로드,다운로드시 사용):getRealPath(\"/로 시작하는 웹상의 경로\")</h2>\r\n");
      out.write("       		<!-- webapp아래 /builtinobject03 이런 식으로 시작 -->\r\n");
      out.write("       		<!-- ServletContext의 메소드인 getRealPath(/로 시작하는 웹상의 경로)로 얻는다\r\n");
      out.write("       			웹상의 경로는 Context 루트를 제외한 /로 시작 -->\r\n");
      out.write("       		<ul class=\"list-unstyled\">\r\n");
      out.write("       			<li>application내장객체:");
      out.print(application.getRealPath("/images") );
      out.write("</li>\r\n");
      out.write("       			<!-- 아래가 물리적경로 \r\n");
      out.write("       			D:\\HJJ\\Workspace\\Java\\Basic\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\JSPProj\\images -->\r\n");
      out.write("       			<li>request내장객체(주로 내가 만든 서블릿(web.xml에 등록한거)에서 사용): ");
      out.print(request.getServletContext().getRealPath("/images") );
      out.write("</li>\r\n");
      out.write("       			<!-- 만든 서블릿은 클래스임 .jsp 아니라 application 사용 불가, getRealPath 불가 \r\n");
      out.write("       				서블릿에서 req 인자로 받음, req.으로 application 받아와서 getRealPath 해야함 \r\n");
      out.write("       				jsp는 application 지역변수로 선언돼있어서 바로 쓸 수 있음\r\n");
      out.write("       				.java는 req라는 이름이 request를 가져오는 방법임-->\r\n");
      out.write("       			<!-- 물리적경로 얻기위함, Servlet 요청은 실행중인 어플리케이션에 보냄 -->\r\n");
      out.write("       			<li>this키워드(주로 선언부나 내가 만든 서블릿에서 사용): ");
      out.print(this.getServletContext().getRealPath("/images") );
      out.write("</li><!-- 이것도 클래스(.java)에서 사용가능, req처럼 -->\r\n");
      out.write("      		</ul>\r\n");
      out.write("    		<h3>선언부(내장객체 사용불가, 지역변수)에서 물리적 경로 얻기</h3>   		\r\n");
      out.write("        	<ul class=\"list-unstyled\">\r\n");
      out.write("        		<li>this키워드 사용:");
      out.print(getRealPath() );
      out.write("</li>\r\n");
      out.write("        		<li>매개변수로 전달:");
      out.print(getRealPath(application) );
      out.write("</li>\r\n");
      out.write("        		");

        			this.app = application; //초기화함
        		
      out.write("\r\n");
      out.write("        		<li>멤버변수 사용:");
      out.print(getRealPath_() );
      out.write("</li>\r\n");
      out.write("        	\r\n");
      out.write("        	</ul>\r\n");
      out.write("        	\r\n");
      out.write("        	\r\n");
      out.write("        </fieldset>  \r\n");
      out.write("    </div><!--container-->\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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
