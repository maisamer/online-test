

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>registration form</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
           button, input{
                        border: 1px solid green;
                        background: #ffe;
                        border-radius: 5px;
                         width: 300px;
                         height: 30px;
                         font-size: 20px;
                 
                       
            }
            span{
                color:red;
                font-size: 30px;
            }
           
         
        </style>

    </head>
    <body>
     
        <h2>Registration form</h2>
         <form name="RegForm" action="addnewcandiate" method="post" onsubmit="return validate();"  enctype="multipart/form-data">
            <table border="0" >
            <tbody>
            <tr>
            <td><label for="name">User-Name: </label></td>
            <td><input onblur="toajax()" id="name" maxlength="10" name="name" type="text" /></td> 
          
            </tr>
           
             <tr>
            <td><label for="email">Email_Address:</label></td>
            <td><input id="email" maxlength="35" name="email" type="text" /></td>
            </tr>
            <tr>
            <td><label>Phone number:</label></td>
            <td><input id="phonenumber" maxlength="13" name="phonenumber" type="text" /></td>
            </tr>

            <tr>
            <td><label for="password">Password:</label></td>
            <td><input id="password" maxlength="10" name="password" type="password" /></td>
            </tr>
             <tr>
            <td><label for="cv">CV :</label></td>
            <td><input id="cv" name="cv" type="file" /></td>
            </tr>
            <tr>
            <td ><input name="Submit" type="Submit" value="submit" /></td>
            </tr>
            </tbody>
            </table>
</form>
        <script>
         function toajax(){   
                             var name = document.forms["RegForm"]["name"]; 
                            
                             var xmlhttp = new XMLHttpRequest();
                             xmlhttp.open("GET", "valiateregistrtion?name=" + name.value, true);
                             xmlhttp.send();
                             xmlhttp.onreadystatechange = function () {
                                 if (xmlhttp.readyState === 4 && xmlhttp.status === 200){
                                                var f=String(xmlhttp.responseText);
                                              
                                                 if(f==="fo"){
                                                     alert("invalid name is taken , change it now ");
                                                    document.getElementById("name").value = "";
                                                 }else if(f==="not"){
                                                     alert("valid user name , lolol ");
                                                 }
                                                     
                                              }  
                                        
                              };
                             
         }    
         function validate(){ 
                                  var name = document.forms["RegForm"]["name"];               
                                  var email = document.forms["RegForm"]["email"];    
                                  var phone = document.forms["RegForm"]["phonenumber"];  
                                  var password = document.forms["RegForm"]["password"];  
                                  var cv=document.forms["RegForm"]["cv"]; 
                                  if (name.value === ""){alert("Please enter your name.");return false;  }
                                  if (email.value === "") {alert("Please enter a valid e-mail address.");return false; } 
                                  if (email.value.indexOf("@", 0) < 0){ alert("Please enter a valid e-mail address.");return false; } 
                                  if (email.value.indexOf(".", 0) < 0){ alert("Please enter a valid e-mail address.");return false;} 
                                   if (phone.value.length>0){ 
                                            if(phone.value.length<9){  
                                                alert("Please enter a valid phone."); 
                                                return false; 
                                                }
                                               
                                         }
                                  if (password.value === "") {  alert("Please enter your password"); return false;  } 
                                  if(password.value.length<5){  alert("Please enter vaild password");   return false; }
                                  if( cv.value === "" ){alert("Please enter your cv");  return false;}
                                  return true;
                              
                  } 
                              
        function ajax(name){ 
              
                                       
                              
        
    }
        </script>
    </body>
</html>
