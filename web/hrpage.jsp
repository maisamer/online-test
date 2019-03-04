<%-- 

    Document   : hrpage
    Created on : Dec 20, 2018, 1:45:37 AM
    Author     : egypt2
--%>

<%@page import="java.io.InputStream"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="need.emp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <style>
            h2,h4{
                color: green;
            }
            #info{
                 margin-left: 200px;
                 margin-top: 100px;
                 
            }
            h2{
                margin-left: 25px;
            }
            h4,p{
                margin-left: 50px;
            }
            button , input{
                       border: 1px solid green;
                        background: #ffe;
                        border-radius: 5px;
                        margin-right: 5px;
            }
            input{
                margin-left: 300px;
                font-size: 20px;
              
            }
            #gotoprofile{
                  padding: 20px; 
            }
            h1{
                text-align: center;
            }
            #goto{
                margin-left: 1000px;
                margin-top: 100px;
            }
            #forloop{
                margin-bottom: 50px;
            }
            h1{
                color: blue;
            }
        </style>
       
    </head>
    <body>
    <%
           ArrayList<emp> j = (ArrayList<emp>) request.getAttribute("ar");
           ArrayList acep = new ArrayList();
           ArrayList rej = new ArrayList();
      %>
      
       <div id="goto">  
                  <form name="RegForm" action="hrsecondpage.jsp">
                          <input id="gotoprofile" name="Submit" type="Submit" value="go to your profile " />
                  </form>         
              </div>  
     
    <% if(j.size()==0){
        %>
        <h2>no new employess</h2>
  <%  }%>  
      <div id="forloop">
          <form>
          <%
          for (int i=0; i < j.size(); i++) {
              
           %>
           <hr> <h1> python =1 , java =2 , IQ =3 , english =4 </h1><hr><hr>
           <div id="info">
               <h2> employee number <% out.print(i+1); %> </h2>
               <h4 class="nr" id="ll"> name :-  <% out.print(j.get(i).name); %> </h4>
            
                <h4>  Email :-   <%  out.print(j.get(i).eamil);  %> </h4>
                <% 
                       long l=(long)j.get(i).s.length();  
                        InputStream  k=j.get(i).s.getBinaryStream(1 ,l);
                        int ch;
                        StringBuilder sb = new StringBuilder();
                        while((ch = k.read()) != -1)
                                  sb.append((char)ch);
                        String KK=sb.toString();
                %>
                <p> <blod>His CV :-</blod> <% out.print(KK); %></p>
            </div>
                 <input type="text" name="choose" id="choose">
                <button id="apply" TYPE="BUTTON" value="acce">accepted <%out.print("."+j.get(i).name); %></button>
                <button id="reject" TYPE="BUTTON" VALUE="rejec" >rejected <% out.print("."+j.get(i).name); %> </button>
                
                
                </form>
               
               <br>
              <%   }  %>
              
     </div>
             
               <script src="jquery-3.2.0.min.js"></script>
        <script>        
         var ac=[];
         var rej=[];
         $(document).ready(function (){
              $("button").click(function() {
                var f = $(this).text();
                var s=String(f);
                 var i=0;
                 for (i = 0; i < f.length; i++ ){
                     if(f[i]==='.'){
                         break;
                     }
                 }
                 var b=$(this).prev("input").val();
                alert("here");
                if(f[0]==='a'){
                    if(b.length===0){
                        alert("enter values of exams ");
                        return false;
                    }
                }
                 if(f[0]==='a'){
                      $(this).hide( "slow", function() {});
                      $(this).next().hide( "slow", function() {});
                        $(this).prev().prev("div").hide( "slow", function() {});
                         $(this).prev("input").hide( "slow", function() {});
                      
                 }else if(f[0]==='r'){
                     //rejected
                      $(this).hide( "slow", function(){});
                         $(this).prev().prev().prev("div").hide( "slow", function() {});
                           $(this).prev().prev("input").hide( "slow", function() {});
                      $(this).prev().hide( "slow", function() {});
                     
                     
                     
                 }
             
                  var xmlhttp = new XMLHttpRequest();
                             xmlhttp.open("GET", "acceptedemplyes?name=" + s+"&choose=" + b, true);
                             xmlhttp.send();
                             xmlhttp.onreadystatechange = function () {
                                 if (xmlhttp.readyState === 4 && xmlhttp.status === 200){
                                                var x=String(xmlhttp.responseText);
                                                alert("."+x+".");
                                                alert("endprocess");     
                                              }  
                                        
                              };
                 
                 
               });
         });
     </script>
                           

       
    </body>
</html>
