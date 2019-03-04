<%-- 
    Document   : test
    Created on : Dec 19, 2018, 10:30:31 PM
    Author     : go
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="need.Question"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <script type="text/javascript">

            function sendajax(i,j) {
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("GET", "save_answer?choice=" + j + "&question=" + i, true);
                xmlhttp.send();
                xmlhttp.onreadystatechange = function ()
                {
                    if (xmlhttp.readyState === 4 && xmlhttp.status === 200)
                    {
                        document.getElementById("show_response").innerHTML = xmlhttp.responseText;
                    }
                };

            }
            function mark(i) {
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("GET", "mark_question?questionId=" + i, true);
                xmlhttp.send();
                xmlhttp.onreadystatechange = function ()
                {
                    if (xmlhttp.readyState === 4 && xmlhttp.status === 200)
                    {
                        document.getElementById("show_response").innerHTML = xmlhttp.responseText;
                    }
                };
            }
            function skip(i) {
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("GET", "skip_question?questionId=" + i, true);
                xmlhttp.send();
                xmlhttp.onreadystatechange = function ()
                {
                    if (xmlhttp.readyState === 4 && xmlhttp.status === 200)
                    {
                        document.getElementById("show_response").innerHTML = xmlhttp.responseText;
                    }
                };

            }
        </script> 
        <style>
             h3{
                margin-left: 100px;
                margin-top: 30px;
               
            }
            
        </style>
    <head>
       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <% int type=Integer.parseInt(request.getAttribute("type").toString());
            if(type==1)
                out.print("Python test");
            else if(type==2)
                out.print("java test");
            else if(type==3)
                out.print("English test");
            else if(type==4)
                out.print("IQ test"); %>
        </title>
    </head>
    <body>
        <h3><%out.print("welcome "+session.getAttribute("username").toString());%></h3>
        <form type="GET" action="nextExam">
        <% 

            if(type==1)
                out.print("<h>Python test</h>");
            else if(type==2)
                out.print("<h>java test</h>");
            else if(type==3)
                out.print("<h>English test</h>");
            else if(type==4)
                out.print("<h>IQ test</h>");
            
            ArrayList<Question> a=(ArrayList<Question>) request.getAttribute("questions");
        %>
     <div id="Q">
        <% for(int i=0;i<a.size();i++){ %>
            <p><%=a.get(i).que%></p>
            <% for(int j=0;j<a.get(i).ans.size();j++){%>
            <% if(a.get(i).selected==j) { %>
                <input id='choice' type='radio' name=<%=a.get(i).getque(i)%> value=<%=a.get(i).getans(j)%>  onclick="sendajax('<%=a.get(i).getquesId()%>','<%=j%>')" checked> <%=a.get(i).ans.get(j)%>
            <% } %>
            <% if(a.get(i).selected!=j) { %>
                <input id='choice' type='radio' name=<%=a.get(i).getque(i)%> value=<%=a.get(i).getans(j)%>  onclick="sendajax('<%=a.get(i).getquesId()%>','<%=j%>')"> <%=a.get(i).ans.get(j)%>
            <% } %>   
            <% } %>
            <input type="radio" onclick="skip('<%=a.get(i).getquesId()%>')">Skip
            <input type="radio" onclick="mark('<%=a.get(i).getquesId()%>')">Mark
        <%  } %>
     </div>   
        <br>
        <div id="show_response"></div>
        <button id="ne" type="submit">Next</button>
        </form>
    </body>
</html>
