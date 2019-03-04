<%-- 
    Document   : log
    Created on : Dec 19, 2018, 5:47:00 AM
    Author     : egypt2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
          <style>
            h1{
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
         <h1>Login</h1>

         <form name="RegForm"  method="post" onsubmit="valid();"action="foundname">
            <table border="0" >
            <tbody>
            <tr>
            <td><label for="name">User-Name: </label></td>
            <td><input id="name" maxlength="10" name="name" type="text" onblur="sendajax()"/></td>
            </tr>
            <tr>
            <tr>
            <td><label for="password">Password:</label></td>
            <td><input onblur="sendajax2()" id="password" maxlength="10" name="password"
            type="password" /></td>
            </tr>
            <tr>
             <tr>
            <td ><input name="Submit" type="Submit" value="submit" /></td>
            </tr>
            </tbody>
            </table>
</form>
  <script>
    function valid(){
          var name = document.forms["RegForm"]["name"];               
          var password = document.forms["RegForm"]["password"];
           if (name.value === ""){alert("Please enter your name.");return false;  }
            if (password.value === "") {  alert("Please enter your password"); return false;  } 
            return  true;
    }  
     function sendajax(){
         alert("jejejje ");
                           var name = document.forms["RegForm"]["name"];         
                             var xmlhttp = new XMLHttpRequest();
                             xmlhttp.open("GET", "valiateregistrtion?name=" + name.value, true);
                             xmlhttp.send();
                             alert("kkkkkkkkkkkkkkk");
                             xmlhttp.onreadystatechange = function () {
                                 if (xmlhttp.readyState === 4 && xmlhttp.status === 200){
                                                var f=String(xmlhttp.responseText);
                                                  alert(f);
                                                 if(f==="not"){
                                                     alert("invalid name is taken , change it now ");
                                                    document.getElementById("name").value = "";
                                                 }else if(f==="fo"){
                                                     alert("valid user name , lolol ");
                                                 }
                                                     
                                              }  
                                        
                              };
      }
              
               function sendajax2(){
                           var pass = document.forms["RegForm"]["password"];         
                             var xmlhttp = new XMLHttpRequest();
                             alert(pass.value);
                             xmlhttp.open("GET", "validtaepassword?pass=" + pass.value, true);
                             xmlhttp.send();
                             alert("kkkkkkkkkkkkkkk");
                             xmlhttp.onreadystatechange = function () {
                                 if (xmlhttp.readyState === 4 && xmlhttp.status === 200){
                                                var f=String(xmlhttp.responseText);
                                                  alert(f);
                                                 if(f==="not"){
                                                     alert("invalid pass is taken , remeber ");
                                                    document.getElementById("password").value = "";
                                                 }else if(f==="fo"){
                                                     alert("valid user pass , lolol ");
                                                 }
                                                     
                                              }  
                                        
                              };
      }
              
                      
        </script>
    </body>
</html>
