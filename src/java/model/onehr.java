/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import need.hr;

/**
 *
 * @author egypt2
 */
public class onehr {
    String url = "jdbc:mysql://localhost:3306/examsystem";
    String user = "root";
    String password = "greedydp0283";
    String que;
    java.sql.Connection Con =null;
    java.sql.Statement Stmt = null;
    java.sql.ResultSet RS = null;
      public  String getemail()  throws SQLException, ClassNotFoundException{
               Class.forName("org.gjt.mm.mysql.Driver");
               int x=1;
              
               try {
                  
                   Con=(Connection) DriverManager.getConnection(url, user, password);
                   String q="SELECT * FROM hr ";
                   Statement st = (Statement) Con.createStatement();
                   ResultSet rs = st.executeQuery(q);
                  while(rs.next()){
                      String t=rs.getString("email");
                     return t;
                  }
                    Con.close();;
                     return "not";
    }catch (SQLException ex) {
            Logger.getLogger(onehr.class.getName()).log(Level.SEVERE, null, ex);
        }  
               return "not";
}
     public  String checck_userReg(String name)  throws SQLException, ClassNotFoundException{
               Class.forName("org.gjt.mm.mysql.Driver");
               int x=1;
              
               try {
                  
                   Con=(Connection) DriverManager.getConnection(url, user, password);
                   String q="SELECT * FROM hr ";
                   Statement st = (Statement) Con.createStatement();
                   ResultSet rs = st.executeQuery(q);
                  while(rs.next()){
                      String t=rs.getString("email");
                      if(t.equals(name)){
                          return "fo";
                      }
                  }
                    Con.close();;
                     return "not";
    }catch (SQLException ex) {
            Logger.getLogger(onehr.class.getName()).log(Level.SEVERE, null, ex);
        }  
               return "not";
}
  public String check_userloginpass(String name) throws ClassNotFoundException{
       Class.forName("org.gjt.mm.mysql.Driver");
               int x=1;
               try {
                  
                   Con=(Connection) DriverManager.getConnection(url, user, password);
                   String q="SELECT * FROM hr ";
                 Stmt = (Statement) Con.createStatement();
                  RS = Stmt.executeQuery(q);
                  while(RS.next()){
                      String t=RS.getString("passe");
                      if(t.equals(name)){
                          return "fo";
                      }
                  }
                    Con.close();;
                     return "not";
    }   catch (SQLException ex) { 
            Logger.getLogger(onehr.class.getName()).log(Level.SEVERE, null, ex);
        }
               return "not";
      
  }
     public String addhr(hr t) throws ClassNotFoundException{
          Class.forName("org.gjt.mm.mysql.Driver");
               try {
                        Con=(Connection) DriverManager.getConnection(url, user, password);
                        String q="insert into hr (email,passe)" + " values (?, ?)";
                        PreparedStatement statement = Con.prepareStatement(q);
                        statement.setString(1, t.GetE());
                        statement.setString(2, t.GetP());
                        statement.execute();
                        return  "registration succefuly go to login ";
               } catch (SQLException ex) {
            Logger.getLogger(onehr.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    public int checkfoundhr() throws ClassNotFoundException{
           Class.forName("org.gjt.mm.mysql.Driver");
               try {
                  
                   Con=(Connection) DriverManager.getConnection(url, user, password);
                   String q="SELECT * FROM hr ";
                   Stmt = (Statement) Con.createStatement();
                   RS = Stmt.executeQuery(q);
                  while(RS.next()){
                     return  1;
                  }
                  Con.close();;
                    Con.close();;
        
        return 0;
    }   catch (SQLException ex) {
            Logger.getLogger(onehr.class.getName()).log(Level.SEVERE, null, ex);
        }
               return  0;
    }
    
}
