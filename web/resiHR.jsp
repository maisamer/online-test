<%-- 
    Document   : resiHR
    Created on : Dec 19, 2018, 9:19:37 AM
    Author     : egypt2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resitertion HR</title>
          <style>
            h2{
                margin-top: 150px;
                color: green;
                margin-left: 250px;

            }
            label{
                 color: green;
                  font-size: 25px;
            }
            form{
                margin-top: 10px;
                margin-left: 250px;
                
            }
            input{
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
        
        <h2>Registration form</h2>

         <form name="RegForm" action="reghr" method="post" onsubmit="return validate2()">
            <table border="0" >
            <tbody>
            <tr>
            <tr>
            <td><label for="email">Email_Address:</label></td>
            <td><input id="email" maxlength="50" name="email" type="text" /></td>
            </tr>
            <tr>
            <td><label for="password">Password:</label></td>
            <td><input id="password" maxlength="50" name="password" type="password" /></td>
            </tr>
            <tr>
            <td ><input name="Submit" type="Submit" value="submit" /></td>
            </tr>

            </tbody>
            </table>
</form>
        <script>
            function  validate2(){             
                var email = document.forms["RegForm"]["email"];    
                var password = document.forms["RegForm"]["password"];  
                
              
          if (email.value === "") { 
                    window.alert("Please enter a valid e-mail address."); 
                    email.focus(); 
                    return false; 
                } 
   
                if (email.value.indexOf("@", 0) < 0)                 
                { 
                    window.alert("Please enter a valid e-mail address."); 
                    email.focus(); 
                    return false; 
                } 

                if (email.value.indexOf(".", 0) < 0)                 
                { 
                    window.alert("Please enter a valid e-mail address."); 
                    email.focus(); 
                    return false; 
                } 



                if (password.value === "")                        
                { 
                    window.alert("Please enter your password"); 
                    return false; 
                } 
                if(password.value.length<4){  
                       window.alert("Please enter vaild password"); 
                    return false; 
                     }
   
                return true; 
}




        </script>
    </body>
</html>
