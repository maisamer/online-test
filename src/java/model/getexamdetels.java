/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author go
 */
public class getexamdetels {
   String url = "jdbc:mysql://localhost:3306/examsystem";
    String user = "root";
    String password = "greedydp0283";
    String que;
    java.sql.Connection Con =null;
    java.sql.Statement Stmt = null;
    java.sql.ResultSet RS = null;
    public String getexamtypeusingid(int y) throws ClassNotFoundException{
        String ty="";
          Class.forName("org.gjt.mm.mysql.Driver");
          try{
                    Con=(com.mysql.jdbc.Connection) DriverManager.getConnection(url, user,password);
                    que = "SELECT * FROM exam "+"WHERE  id= "+y+" ;";
                     Stmt = Con.createStatement();  
                    RS =Stmt.executeQuery(que); 
                    while(RS.next()){
                       int p=RS.getInt(2);
                       System.out.println("rrrr "+p);
                       if(p==1){
                           ty="python";
                       }else if(p==2){
                           ty="java";
                       }else if(p==4){
                           ty="IQ";
                       }else{
                           ty="english";
                       }
                    }
                    Con.close();;   
          } catch (SQLException ex) { 
              Logger.getLogger(getexamdetels.class.getName()).log(Level.SEVERE, null, ex);
          } 
          return ty;
    }
    public int getexamscors(int y) throws ClassNotFoundException{
         String ty="";
          Class.forName("org.gjt.mm.mysql.Driver");
          try{
                    Con=(com.mysql.jdbc.Connection) DriverManager.getConnection(url, user,password);
                    que = "SELECT * FROM exam "+"WHERE  id= "+y+" ;";
                     Stmt = Con.createStatement(); 
                    RS =Stmt.executeQuery(que); 
                    while(RS.next()){
                       return  RS.getInt("score");
                    }
                    Con.close();;
                   
          } catch (SQLException ex) { 
              Logger.getLogger(getexamdetels.class.getName()).log(Level.SEVERE, null, ex);
          }
          return 0;
    }
}
