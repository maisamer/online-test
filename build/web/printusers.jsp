<%-- 
    Document   : printusers
    Created on : Dec 31, 2018, 11:02:33 AM
    Author     : egypt2
--%>

<%@page import="need.Question"%>
<%@page import="need.emp"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>peaple has make exam</title>
        <style>
            #Q{
                margin-bottom: 10px;
            }
            h1{
                color: green;
                margin-left: 100px;
            }
            p{
                margin-left: 300px;
                margin-right: 100px;
            }
            h3{
                margin-left: 250px;
                color: blue;
            }
           span{
            font-size: 20px;
            color: red;
             font-style:italic;
             font-family: Arial;
        }
        </style>
         <%!
          int x=0;
      %>
    </head>
    <body>
        <%
             ArrayList<emp> em=(ArrayList<emp>)request.getAttribute("emp");
              ArrayList< ArrayList<Question> > exams = (  ArrayList< ArrayList<Question> >) request.getAttribute("exams");
               ArrayList< ArrayList<String> > Typesforall=(ArrayList< ArrayList<String> > ) request.getAttribute("Typesforall");
               
      %>
     
      <%
          for(int i=0;i<em.size();i++){
      %>
      <hr>
      <hr>
       <h1>user name is <%out.print(em.get(i).name);%></h1>
       <h1>email is <%out.print(em.get(i).eamil);%></h1>
       <h1>num exams is <%out.print(em.get(i).nuofexams);%></h1>
       <h1>score is <%out.print(em.get(i).score);%></h1>
       <% for(int k=0;k<em.get(i).nuofexams;k++){
           
               
                 %>
                 <h3>  new exam </h3>
          <div id="Q"> 
                 <%
                     
                 for(int j=0;j<exams.get(x).size();j++){ 
                       int c=exams.get(x).get(j).selected;
                  
           %>
             <div>
                  <p> Q- <%out.print(exams.get(x).get(j).que);%> <span> his answer </span> <%if(c>=5){out.print("skipped ");}else{out.print(exams.get(x).get(j).ans.get(c));}%> </p> 
              </div>
            
             
           
         <%  }%>
       </div> 
       <% x++;}%>
    <%}%>
    </body>
</html>
