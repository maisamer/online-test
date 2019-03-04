<%-- 
    Document   : finish
    Created on : Dec 26, 2018, 2:36:23 AM
    Author     : go
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
             h1{
                text-align:center;
                color: green;
                border: 2px solid green;
                 border-radius: 5px;
                 margin-top: 150px;
                 margin-left: 350px;
                 width: 600px;
                 padding: 20px;
            }
            button{
                        border: 2px solid green;
                        background: #ffe;
                        border-radius: 5px;
                        margin-left: 600px;
                        font-size: 20px;
                        padding: 10px;
            }
        </style>
    </head>
    <body>
        <form action="send_email">
            <h1>you want submit your solution</h1>
            <button type="submit">submit</button>
        </form>
    </body>
</html>
