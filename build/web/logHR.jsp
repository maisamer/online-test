<%-- 
    Document   : logHR
    Created on : Dec 19, 2018, 9:22:52 AM
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
         <form action="HRpage" method="post" onsubmit="vali()" name="RegForm">
            <table border="0" >
            <tbody>
            <tr>
            <td><label for="name">Email: </label></td>
            <td><input onblur="sendajax()" id="email" maxlength="50" name="email" type="text" /></td>
            </tr>
            <tr>
            <tr>
            <td><label for="password">Password:</label></td>
            <td><input onblur="sendajax2()" id="password" maxlength="50" name="passwor" type="password" /></td>
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
          var name = document.forms["RegForm"]["email"];               
          var password = document.forms["RegForm"]["password"];
           if (name.value === ""){alert("Please enter your name.");return false;  }
            if (password.value === "") {  alert("Please enter your password"); return false;  } 
            return  true;
    }  
     function sendajax(){
         alert("jejejje ");
                           var name = document.forms["RegForm"]["email"];         
                             var xmlhttp = new XMLHttpRequest();
                             xmlhttp.open("GET", "validateloginHR?name=" + name.value, true);
                             xmlhttp.send();
                             alert("kkkkkkkkkkkkkkk");
                             xmlhttp.onreadystatechange = function () {
                                 if (xmlhttp.readyState === 4 && xmlhttp.status === 200){
                                                var f=String(xmlhttp.responseText);
                                                  alert(f);
                                                 if(f==="not"){
                                                     alert("invalid email is taken , remeber ");
                                                    document.getElementById("email").value = "";
                                                 }else if(f==="fo"){
                                                     alert("valid email, lolol ");
                                                 }
                                                     
                                              }  
                                        
                              };
      }
              
               function sendajax2(){
                           var pass = document.forms["RegForm"]["password"];         
                             var xmlhttp = new XMLHttpRequest();
                             alert(pass.value);
                             xmlhttp.open("GET", "validtaepasswordtologinhr?pass=" + pass.value, true);
                             xmlhttp.send();
                             alert("kkkkkkkkkkkkkkk");
                             xmlhttp.onreadystatechange = function () {
                                 if (xmlhttp.readyState === 4 && xmlhttp.status === 200){
                                                var f=String(xmlhttp.responseText);
                                                  alert(f);
                                                 if(f==="not"){
                                                     alert("invalid pass , remeber ");
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
