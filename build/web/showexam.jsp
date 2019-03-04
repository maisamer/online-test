<%-- 
    Document   : showexam
    Created on : Dec 30, 2018, 7:31:31 AM
    Author     : egypt2
--%>

<%@page import="need.Question"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
        h2{
            margin-left: 300px;
            color: green;
        }
        #info{
            margin-left: 100px;
            color: blue;
        }
        h2{
            margin-left: 200px;
            color: greenyellow;
            
        }
        p{
            margin-left: 300px;
            margin-right: 100px;
        }
        #Q{
            margin-bottom: 10px;
        }
        span{
            font-size: 20px;
            color: red;
             font-style:italic;
             font-family: Arial;
        }
    </style>
    <body>
        <hr>
        <%
                
            ArrayList<String> l=(ArrayList<String> )request.getAttribute("list");
            ArrayList<Integer> scores= (  ArrayList<Integer>) request.getAttribute("sc");
            ArrayList< ArrayList<Question> > exams = (  ArrayList< ArrayList<Question> >) request.getAttribute("ex");
            
        %>
        <%if(l.size()==0){%>
                <h2>no exams</h2>
        <%}%>
        <%
          for(int i=0;i<exams.size();i++){
              %>
                <div id="info">
                  <h1>  <%out.print(l.get(i) +" his score is "+scores.get(i));%> </h1> 
                 
              </div>
              <h2>exam </h2>
              <br>
              <%
                  for(int j=0;j<exams.get(i).size();j++){
                      int c=exams.get(i).get(j).selected;
              %> 
              <div id="Q">
                  <p> Q <%out.print(j+1);%> <%out.print(exams.get(i).get(j).que);%> <span> his answer </span><%if(c>=5){out.print("skipped or not answer ");}else{out.print(exams.get(i).get(j).ans.get(c));}%>  </p> 
              </div>
            
                <%  }%>
                <hr>
            
         <% }
     %>
    </body>
</html>
