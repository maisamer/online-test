/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import need.emp;

/**
 *
 * @author egypt2
 */
public class viwenewusers {
     String url = "jdbc:mysql://localhost:3306/examsystem";
    String user = "root";
    String password = "greedydp0283";
                Connection Con =null;
      public  ArrayList<emp> displaynewusers() throws ClassNotFoundException{
           Class.forName("org.gjt.mm.mysql.Driver");
              
               ArrayList<emp> arrtnotvalid=new ArrayList<>();
               try {
                   Con=(Connection) DriverManager.getConnection(url, user, password);
                   String q="SELECT * FROM candidate WHERE vail="+"0";
                   // 0 for not show , 1 show and will send , 2 end user 
                   Statement st = (Statement) Con.createStatement();
                   ResultSet rs = st.executeQuery(q);
                        while (rs.next()){
                         emp p=new emp();
                         p.s = rs.getBlob("cv");
                         p.name=rs.getString("username");
                         p.eamil=rs.getString("email");
                         arrtnotvalid.add(p);
                       
                        }
                         
                         Con.close(); 
    }   catch (SQLException ex) {
            Logger.getLogger(viwenewusers.class.getName()).log(Level.SEVERE, null, ex);
        } 
    return arrtnotvalid;
    }
  
      
     public  ArrayList<emp> displaynewuserswithvail2() throws ClassNotFoundException{
           Class.forName("org.gjt.mm.mysql.Driver");
              
               ArrayList<emp> arrtnotvalid=new ArrayList<>();
               try {
                   Con=(Connection) DriverManager.getConnection(url, user, password);
                   String q="SELECT * FROM candidate WHERE vail="+"2";
                   // 0 for not show , 1 show and will send , 2 end user 
                   Statement st = (Statement) Con.createStatement();
                   ResultSet rs = st.executeQuery(q);
                        while (rs.next()){
                         emp p=new emp();
                         p.name=rs.getString("username");
                         p.eamil=rs.getString("email");
                         p.score=rs.getInt("score");
                         String c=rs.getString("sequence");
                         p.nuofexams=c.length();
                         arrtnotvalid.add(p);
                         
                        }
                         
                         Con.close(); 
    }   catch (SQLException ex) {
            Logger.getLogger(viwenewusers.class.getName()).log(Level.SEVERE, null, ex);
        } 
    return arrtnotvalid;
    }  
      
      
      
      
      
      
      
      
      
    
}
