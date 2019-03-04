package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class log_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Login</title>\n");
      out.write("          <style>\n");
      out.write("            h1{\n");
      out.write("                margin-top: 150px;\n");
      out.write("                color: green;\n");
      out.write("                                margin-left: 250px;\n");
      out.write("\n");
      out.write("            }\n");
      out.write("            label{\n");
      out.write("                 color: green;\n");
      out.write("                  font-size: 25px;\n");
      out.write("            }\n");
      out.write("            form{\n");
      out.write("                margin-top: 10px;\n");
      out.write("                margin-left: 250px;\n");
      out.write("                \n");
      out.write("            }\n");
      out.write("            input{\n");
      out.write("                       border: 1px solid green;\n");
      out.write("                        background: #ffe;\n");
      out.write("                        border-radius: 5px;\n");
      out.write("                         width: 300px;\n");
      out.write("                         height: 30px;\n");
      out.write("                         font-size: 20px;\n");
      out.write("                 \n");
      out.write("                       \n");
      out.write("            }\n");
      out.write("           \n");
      out.write("         \n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("         <h1>Login</h1>\n");
      out.write("\n");
      out.write("         <form name=\"RegForm\"  method=\"post\" onsubmit=\"valid();\"action=\"foundname\">\n");
      out.write("            <table border=\"0\" >\n");
      out.write("            <tbody>\n");
      out.write("            <tr>\n");
      out.write("            <td><label for=\"name\">User-Name: </label></td>\n");
      out.write("            <td><input id=\"name\" maxlength=\"10\" name=\"name\" type=\"text\" onblur=\"sendajax()\"/></td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("            <tr>\n");
      out.write("            <td><label for=\"password\">Password:</label></td>\n");
      out.write("            <td><input onblur=\"sendajax2()\" id=\"password\" maxlength=\"10\" name=\"password\"\n");
      out.write("            type=\"password\" /></td>\n");
      out.write("            </tr>\n");
      out.write("            <tr>\n");
      out.write("             <tr>\n");
      out.write("            <td ><input name=\"Submit\" type=\"Submit\" value=\"submit\" /></td>\n");
      out.write("            </tr>\n");
      out.write("            </tbody>\n");
      out.write("            </table>\n");
      out.write("</form>\n");
      out.write("  <script>\n");
      out.write("    function valid(){\n");
      out.write("          var name = document.forms[\"RegForm\"][\"name\"];               \n");
      out.write("          var password = document.forms[\"RegForm\"][\"password\"];\n");
      out.write("           if (name.value === \"\"){alert(\"Please enter your name.\");return false;  }\n");
      out.write("            if (password.value === \"\") {  alert(\"Please enter your password\"); return false;  } \n");
      out.write("            return  true;\n");
      out.write("    }  \n");
      out.write("     function sendajax(){\n");
      out.write("         alert(\"jejejje \");\n");
      out.write("                           var name = document.forms[\"RegForm\"][\"name\"];         \n");
      out.write("                             var xmlhttp = new XMLHttpRequest();\n");
      out.write("                             xmlhttp.open(\"GET\", \"valiateregistrtion?name=\" + name.value, true);\n");
      out.write("                             xmlhttp.send();\n");
      out.write("                             alert(\"kkkkkkkkkkkkkkk\");\n");
      out.write("                             xmlhttp.onreadystatechange = function () {\n");
      out.write("                                 if (xmlhttp.readyState === 4 && xmlhttp.status === 200){\n");
      out.write("                                                var f=String(xmlhttp.responseText);\n");
      out.write("                                                  alert(f);\n");
      out.write("                                                 if(f===\"not\"){\n");
      out.write("                                                     alert(\"invalid name is taken , change it now \");\n");
      out.write("                                                    document.getElementById(\"name\").value = \"\";\n");
      out.write("                                                 }else if(f===\"fo\"){\n");
      out.write("                                                     alert(\"valid user name , lolol \");\n");
      out.write("                                                 }\n");
      out.write("                                                     \n");
      out.write("                                              }  \n");
      out.write("                                        \n");
      out.write("                              };\n");
      out.write("      }\n");
      out.write("              \n");
      out.write("               function sendajax2(){\n");
      out.write("                           var pass = document.forms[\"RegForm\"][\"password\"];         \n");
      out.write("                             var xmlhttp = new XMLHttpRequest();\n");
      out.write("                             alert(pass.value);\n");
      out.write("                             xmlhttp.open(\"GET\", \"validtaepassword?pass=\" + pass.value, true);\n");
      out.write("                             xmlhttp.send();\n");
      out.write("                             alert(\"kkkkkkkkkkkkkkk\");\n");
      out.write("                             xmlhttp.onreadystatechange = function () {\n");
      out.write("                                 if (xmlhttp.readyState === 4 && xmlhttp.status === 200){\n");
      out.write("                                                var f=String(xmlhttp.responseText);\n");
      out.write("                                                  alert(f);\n");
      out.write("                                                 if(f===\"not\"){\n");
      out.write("                                                     alert(\"invalid pass is taken , remeber \");\n");
      out.write("                                                    document.getElementById(\"password\").value = \"\";\n");
      out.write("                                                 }else if(f===\"fo\"){\n");
      out.write("                                                     alert(\"valid user pass , lolol \");\n");
      out.write("                                                 }\n");
      out.write("                                                     \n");
      out.write("                                              }  \n");
      out.write("                                        \n");
      out.write("                              };\n");
      out.write("      }\n");
      out.write("              \n");
      out.write("                      \n");
      out.write("        </script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
