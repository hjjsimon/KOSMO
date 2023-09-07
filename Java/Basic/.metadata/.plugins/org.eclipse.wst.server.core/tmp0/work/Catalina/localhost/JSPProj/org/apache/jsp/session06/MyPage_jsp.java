/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.1.8
 * Generated at: 2023-05-26 02:11:50 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.session06;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import model.JWTOkens;

public final class MyPage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports,
                 org.apache.jasper.runtime.JspSourceDirectives {

  private static final jakarta.servlet.jsp.JspFactory _jspxFactory =
          jakarta.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/template/Top.jsp", Long.valueOf(1684497899021L));
    _jspx_dependants.put("/template/Footer.jsp", Long.valueOf(1684406783975L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("jakarta.servlet");
    _jspx_imports_packages.add("jakarta.servlet.http");
    _jspx_imports_packages.add("jakarta.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("model.JWTOkens");
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("    \r\n");

	//토큰기반 인증인 경우 유효한 토큰 여부 판단
	boolean isValidToken=JWTOkens.verifyToken(
			JWTOkens.getToken(request, application.getInitParameter("COOKIE-NAME")), "/resources/tokens", "secret-key");


      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"ko\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("    \r\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css\">\r\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js\"></script>\r\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js\"></script>\r\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js\"></script>\r\n");
      out.write("    <script src=\"https://kit.fontawesome.com/0b4621b427.js\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("    <title></title>\r\n");
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
      out.write("        솔로를 위한 플랫폼\r\n");
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
      out.write("        ");
 if(session.getAttribute("USER-ID")==null){ 
      out.write("\r\n");
      out.write("          <li class=\"nav-item\">\r\n");
      out.write("            <a class=\"nav-link active\" href=\"");
      out.print(request.getContextPath() );
      out.write("/session06/Login.jsp\">로그인(세션) <i class=\"fa fa-user-o\"></i></a>\r\n");
      out.write("          </li>\r\n");
      out.write("        ");
}else{ 
      out.write("\r\n");
      out.write("          <li class=\"nav-item\">\r\n");
      out.write("            <a class=\"nav-link active\" href=\"");
      out.print(request.getContextPath() );
      out.write("/session06/Logout.jsp\">로그아웃(세션) <i class=\"fa fa-user-plus\"></i></a>\r\n");
      out.write("          </li>\r\n");
      out.write("         ");
}
      out.write("\r\n");
      out.write("          ");
 if(!isValidToken){ 
      out.write("\r\n");
      out.write("          <li class=\"nav-item\">\r\n");
      out.write("            <a class=\"nav-link active\" href=\"");
      out.print(request.getContextPath() );
      out.write("/session06/LoginBasedToken.jsp\">로그인(토큰) <i class='fas fa-sign-in-alt'></i></a>\r\n");
      out.write("          </li>\r\n");
      out.write("         ");
}else{ 
      out.write("\r\n");
      out.write("          <li class=\"nav-item\">\r\n");
      out.write("            <a class=\"nav-link active\" href=\"");
      out.print(request.getContextPath() );
      out.write("/session06/LogoutBasedToken.jsp\">로그아웃(토큰) <i class='fas fa-sign-out-alt'></i></a>\r\n");
      out.write("          </li>\r\n");
      out.write("          ");
} 
      out.write("\r\n");
      out.write("          <li class=\"nav-item\">\r\n");
      out.write("            <a class=\"nav-link \" href=\"#\">마이페이지</a>\r\n");
      out.write("          </li>\r\n");
      out.write("          <li class=\"nav-item\">\r\n");
      out.write("            <a class=\"nav-link \" href=\"");
      out.print(request.getContextPath() );
      out.write("/bbs08/List.jsp\">게시판</a>\r\n");
      out.write("          </li>       \r\n");
      out.write("          \r\n");
      out.write("        </ul>\r\n");
      out.write("      </div>\r\n");
      out.write("    </nav>");
      out.write("\r\n");
      out.write("    <div class=\"container\" style=\"margin-top:50px\">\r\n");
      out.write("        <div class=\"jumbotron bg-info\">\r\n");
      out.write("            <h1>마이페이지 ");
 if(session.getAttribute("USER-ID")!=null){ 
      out.write("<small>");
      out.print(session.getAttribute("USER-ID") );
      out.write("님 반갑습니다.");
} 
      out.write("</small></h1>            \r\n");
      out.write("        </div><!--jumbotron-->\r\n");
      out.write("        ");
 if(session.getAttribute("USER-ID")!=null){ 
      out.write("<!-- null체크, 현재 페이지 바로 실행시 null님 반갑습니다, 이러면 로그아웃 안뜨게함 -->\r\n");
      out.write("        <a class=\"btn btn-info\" href=\"Logout.jsp\">로그아웃</a>   \r\n");
      out.write("        ");
} 
      out.write(" \r\n");
      out.write("    </div><!--container-->\r\n");
      out.write("\r\n");
      out.write("<!-- Footer.jsp -->    \r\n");
      out.write("	<footer class=\"page-footer font-small blue\">\r\n");
      out.write("		<!-- Copyright -->\r\n");
      out.write("		<div class=\"footer-copyright text-center py-3\">\r\n");
      out.write("			© 2023 (주)한국소프트웨어아이엔씨 (153-759) 서울시 금천구 가산동 426-5 월드메르디앙 2차 413호 \r\n");
      out.write("			<a href=\"");
      out.print(request.getContextPath());
      out.write("\"> ikosmo.co.kr</a>\r\n");
      out.write("			<!-- <a href=\"/JSPProj\"> ikosmo.co.kr</a> 으로 들어감 -->\r\n");
      out.write("			<!-- web.xml 맨 위에 있는 <welcome-file>HelloWorld.jsp</welcome-file>로 이동함 -->\r\n");
      out.write("		</div>\r\n");
      out.write("		<!-- Copyright -->\r\n");
      out.write("  	</footer>\r\n");
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