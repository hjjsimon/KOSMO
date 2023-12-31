/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.1.8
 * Generated at: 2023-05-28 01:11:44 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.el09;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import model.MemberDTO;

public final class BuiltinObjectOfParamResult_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("model.MemberDTO");
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
      out.write("<!-- BuiltinObjectOfParamResult.jsp -->\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/template/Top.jsp", out, false);
      out.write("\r\n");
      out.write("    <div class=\"container\" style=\"margin-top:50px\">\r\n");
      out.write("        <div class=\"jumbotron bg-info\">\r\n");
      out.write("            <h1>EL의 param내장객체</h1>            \r\n");
      out.write("        </div><!--jumbotron-->\r\n");
      out.write("        <fieldset class=\"form-group border p-3\">\r\n");
      out.write("        	<legend class=\"w-auto px-3\">자바코드로 영역에 저장된 속성 및 파라미터로 전달된 값 읽기</legend>\r\n");
      out.write("        	<h1 class=\"display-4\">영역에 저장된 속성</h1>\r\n");
      out.write("        	");

        		MemberDTO member = (MemberDTO)request.getAttribute("dtoObject");//형변환
        	
      out.write("\r\n");
      out.write("        	<ul class=\"list-unstyled\">\r\n");
      out.write("        		<li>MemberDTO객체 : ");
      out.print(member );
      out.write("</li><!-- 이름:김길동,아이디:KIM,비번:1234,나이:20 -->\r\n");
      out.write("        		<li>String객체 : ");
      out.print(request.getAttribute("stringObject") );
      out.write("</li><!-- String객체 : 문자열 객체-->\r\n");
      out.write("        		<li>Integer객체 : ");
      out.print(request.getAttribute("integerObject") );
      out.write("</li><!-- Integer객체 : 1000 -->\r\n");
      out.write("        	</ul>\r\n");
      out.write("        	<h1 class=\"display-4\">파라미터로 전달된 값</h1>\r\n");
      out.write("        	");

        		int first = Integer.parseInt(request.getParameter("first"));
        		int second = Integer.parseInt(request.getParameter("second"));
        	
      out.write("\r\n");
      out.write("        	두 파라미터의 합: ");
      out.print(first+second );
      out.write("\r\n");
      out.write("        </fieldset>        \r\n");
      out.write("        <!--\r\n");
      out.write("			영역에 저장된 값은 xxxScope내장 객체로]\r\n");
      out.write("			-xxxScope.속성명 혹은 xxxScope[\"속성명\"]\r\n");
      out.write("			파라미터로 전달 된 값은\r\n");
      out.write("			param내장객체 혹은 paramValues내장객체(체크박스종류)로\r\n");
      out.write("			\r\n");
      out.write("			]\r\n");
      out.write("			-param.파라미터명 혹은 param[\"파라미터명\"]\r\n");
      out.write("			 paramValues.파리미터명 혹은 paramValues[\"파라미터명\"]\r\n");
      out.write("			\r\n");
      out.write("			 param은 request.getParameter()와 같고\r\n");
      out.write("			 paramValues는 request.getParameterValues()와 같다\r\n");
      out.write("			\r\n");
      out.write("			※EL에서는 문자열등을 표시할때 \"\"이나 '' 둘다 사용가능\r\n");
      out.write("		 -->\r\n");
      out.write("        \r\n");
      out.write("        \r\n");
      out.write("        <fieldset class=\"form-group border p-3\">\r\n");
      out.write("        	<legend class=\"w-auto px-3\">EL코드로 영역에 저장된 속성 및 파라미터로 전달된 값 읽기</legend>\r\n");
      out.write("        	<h1 class=\"display-4\">영역에 저장된 속성</h1>\r\n");
      out.write("        	<ul class=\"list-unstyled\">\r\n");
      out.write("        		<li>MemberDTO객체 : ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dtoObject }", java.lang.String.class, (jakarta.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</li><!-- requestScope 생략된 것, 형변환도 안해서 편리함, 코드도 깔끔 -->\r\n");
      out.write("        		<li>String객체 : ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.stringObject }", java.lang.String.class, (jakarta.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</li><!-- 한번 써봄, 보통 안씀 -->\r\n");
      out.write("        		<li>Integer객체 : ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${integerObject }", java.lang.String.class, (jakarta.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</li>\r\n");
      out.write("        	</ul>\r\n");
      out.write("        	<h1 class=\"display-4\">파라미터로 전달된 값</h1>\r\n");
      out.write("        	두 파라미터의 합: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${param.first+param['second'] }", java.lang.String.class, (jakarta.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("<!-- parseInt안해도 돼서 편함 -->\r\n");
      out.write("        	\r\n");
      out.write("        	\r\n");
      out.write("        </fieldset> \r\n");
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
