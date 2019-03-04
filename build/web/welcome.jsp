<%-- 
    Document   : welcome
    Created on : Dec 26, 2018, 2:22:53 AM
    Author     : go
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
        <style>
                  button{
                       border: 1px solid green;
                        background: #ffe;
                        border-radius: 5px;
                         width: 300px;
                         height: 30px;
                         font-size: 20px;
                 
                       
            }
           
        </style>
    </head>
    <body>
        <h3><%out.print("welcome "+session.getAttribute("username").toString());%></h3>
        <form action="start_exam" method="GET">
            <button type="submit">Open Exams</button>
        </form>
    </body>
</html>
