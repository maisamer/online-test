<%-- 
    Document   : allinfo
    Created on : Dec 30, 2018, 6:36:24 AM
    Author     : egypt2
--%>

<%@page import="need.Question"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            h2{
                text-align:center;
                color: green;
                border: 2px solid green;
                 border-radius: 5px;
            }
            h4{
                margin-left: 150px;
            }
            p{
                margin-left: 200px;
                margin-right: 50px;
                margin-top: 10px;
            }
            h3{
                color: blue;
                text-align: center;
                 
            }
           span{
            font-size: 20px;
            color: red;
             font-style:italic;
             font-family: Arial;
        }
        </style>
    </head>
    <body>
         <%
              ArrayList< ArrayList<Question> > exams = (  ArrayList< ArrayList<Question> >) request.getAttribute("exams");
              ArrayList<String> Types=(ArrayList<String> ) request.getAttribute("types");
              String name=request.getAttribute("name").toString();
              ArrayList<Integer> scores= (  ArrayList<Integer>) request.getAttribute("scores");
              int fullscore=Integer.parseInt(request.getAttribute("fullsecore").toString());
      %>
                  <h2>user name is <%out.print(name);%></h2>
                  <br>
                 <h2>full score is <%out.print(fullscore);%></h2>
         <%
          for(int i=0;i<Types.size();i++){
         %>
          <h3>exam type is <%out.print(Types.get(i));%> his score is <%out.print(scores.get(i));%></h3>
         <%}%>            
      <%          
          for(int i=0;i<exams.size();i++){
              %>
              <hr><hr>
              <h4>exam type is <%out.print(Types.get(i));%></h4>
              <%
                  for(int j=0;j<exams.get(i).size();j++){
                      int c=exams.get(i).get(j).selected;
                      
              %> 
              <div>
                  <p> Q <%out.print(j+1);%> <%out.print(exams.get(i).get(j).que);%> <span> his answer </span><%if(c>=5){out.print("skipped ");}else{out.print(exams.get(i).get(j).ans.get(c));}%> </p> 
              </div>
            
                <%  }%>
            
          <%}%>
   
    </body>
</html>
