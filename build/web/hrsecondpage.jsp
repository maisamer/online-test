<%-- 
    Document   : hrsecondpage
    Created on : Dec 24, 2018, 8:33:45 AM
    Author     : egypt2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
    *       input[type=text] {
                width: 250px;
                height: 30px;
                -webkit-transition: width 0.4s ease-in-out;
                transition: width 0.4s ease-in-out;
                margin-top: 50px;
                margin-left: 100px;
                border: 1px solid green;
                border-radius: 5px;
          }
          #da{
              width: 320px;
          }
          input[type=text]:focus {
                width: 30%;
              }
              #fr{
                  margin-top: 100px;
              }
              button{
                        border: 1px solid green;
                        border-radius: 5px;
                         width: 110px;
                         height: 30px;
                          background-color: #4CAF50;
                            color: white;
                            border: none;
                            font-size: 15px;
                    margin-left: 100px;margin-top: 20px;
                }
                #whatshow{
                    margin-top: 20px;
                    border: 1px solid green;
                    border-radius: 5px;
                    margin-left: 100px;
                    width: 250px;
                    height: 100px;
                    
                }
                #full,#report{
                    margin: 10px;
                }
                body{
                    margin-left: 400px;
                }
                

          
        </style>
    </head>
    <body>
        
         <div>
             <form  name="tocand" action="sarchaboutusers"  method="post" onsubmit="return validate();" >
                  <input onblur="toajax()" type="text" name="search" id="search" placeholder="Search candidate">  
                  <button type="submit" >search</button>
             </form>     
        </div>
         
        <div>
            <form  action="findjavaexam"  method="post">
                  <button type="submit">java exams</button>
            </form> 
           
        </div>  
        <div>
            <form action="findpythonexam"  method="post">
                 <button type="submit">python exam</button>

            </form>
        </div> 
        <div>
            <form action="findIQexam"  method="post">
                 <button type="submit">IQ exam</button>
            </form>
        </div>
          <div>
            <form action="findenglsiexam"  method="post">
                 <button type="submit">English exam</button>
            </form>
        </div>
        <div>
            <form name="date" action="getexamsbydate"  method="post" onsubmit="return validate2();">
               
                <input type="text"  name="da" id="da" placeholder="Search exam date in format year-month-day"> 
                  <br>
                  <button type="submit">search</button>
            </form>
        </div>  
              
        <script>
                      function toajax(){   
                             var name = document.forms["tocand"]["search"];
                             alert(name.value);
                             var xmlhttp = new XMLHttpRequest();
                             xmlhttp.open("GET", "validatesearch?name=" + name.value, true);
                             xmlhttp.send();
                             xmlhttp.onreadystatechange = function () {
                                 if (xmlhttp.readyState === 4 && xmlhttp.status === 200){
                                                var f=String(xmlhttp.responseText);
                                              
                                                 if(f==="0"){
                                                     alert("invalid name is taken , ");
                                                      document.getElementById("search").value = "";
                                                 }else if(f==="1"){
                                                     alert("valid user name , lolol ");
                                                 }
                                                     
                                              }  
                                        
                              };
                             
         } 
            function  validate2(){ 
                      var name = document.forms["date"]["search"];  
                      if(name.value===""){
                          alert("enter valid date as format showed before  ");
                          return false;
                      }
                     
                      return true;             
                  } 
             function validate(){ 
                      var name = document.forms["tocand"]["search"];  
                      if(name.value===""){
                          alert("search by name or emaail ");
                          return false;
                      }
                      
                      return true;             
                  }  
                  
        </script>   
    </body>
</html>
