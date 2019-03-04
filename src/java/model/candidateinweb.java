/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import need.emp;

/**
 *
 * @author egypt2
 */
public class candidateinweb {
    String url = "jdbc:mysql://localhost:3306/examsystem";
    String user = "root";
    String password = "greedydp0283";
                public Connection Con =null;
     public String getnames(int id) throws ClassNotFoundException {
        Class.forName("org.gjt.mm.mysql.Driver");
         String nn="";
               try {
                   Con=(Connection) DriverManager.getConnection(url, user, password);
                   String query = "SELECT username FROM candidate_exam where examid="+id+";";
                   Statement st = (Statement) Con.createStatement();
                   ResultSet rs = st.executeQuery(query);
                  while(rs.next()){
                    String  k=rs.getString("username");
                      return  k;
                  }
                    Con.close();;
               } catch (SQLException ex) { 
            Logger.getLogger(candidateinweb.class.getName()).log(Level.SEVERE, null, ex);
        }
    
              return  nn;
    }
     public String getuservalid(String name) throws ClassNotFoundException{
          Class.forName("org.gjt.mm.mysql.Driver");
               int x=1;
               try {
                   Con=(Connection) DriverManager.getConnection(url, user, password);
                   String q="SELECT * FROM candidate where vail = '2'";
                   Statement st = (Statement) Con.createStatement();
                   ResultSet rs = st.executeQuery(q);
                  while(rs.next()){
                      String t=rs.getString("username");
                      if(t.equals(name)){
                         
                               return "fo";
                          
                         
                      }
                  }
                    Con.close();;
                     return "not";
         
     }  catch (SQLException ex) {
            Logger.getLogger(candidateinweb.class.getName()).log(Level.SEVERE, null, ex);
        }
               return "not";
     }
   public String serchbyemailto(String name) throws ClassNotFoundException{
         Class.forName("org.gjt.mm.mysql.Driver");
         String k="00";
         boolean p = false;
         String nn="";
               try {
                   Con=(Connection) DriverManager.getConnection(url, user, password);
                   String query ="SELECT * FROM candidate where email= '"+name+"'";
                   Statement st = (Statement) Con.createStatement();
                   ResultSet rs = st.executeQuery(query);
                  while(rs.next()){
                    String v=rs.getString("vail");
                    if(v.equals("2")){
                        String x=rs.getString("username");
                        return x;
                    }
                      
                  }
                    Con.close();;
                    return k;
               } catch (SQLException ex) { 
            Logger.getLogger(candidateinweb.class.getName()).log(Level.SEVERE, null, ex);
        }
    return k;
            
    }
  
    public int getscore(String name) throws ClassNotFoundException {
        String sc="";
        Class.forName("org.gjt.mm.mysql.Driver");
               int x=1;
               try {
                  
                   Con=(Connection) DriverManager.getConnection(url, user, password);
                   String q="SELECT * FROM candidate where username= '"+name+"'";
                   Statement st = (Statement) Con.createStatement();
                   ResultSet rs = st.executeQuery(q);
                  while(rs.next()){
                     return  rs.getInt("score");
                  }
                    Con.close();;
                    
    }   catch (SQLException ex) { 
            Logger.getLogger(candidateinweb.class.getName()).log(Level.SEVERE, null, ex);
        }
    return 0;
    }
      public void updateendexam(String t) throws ClassNotFoundException{
            Class.forName("org.gjt.mm.mysql.Driver");
         String nn="";
               try {
                   Con=(Connection) DriverManager.getConnection(url, user, password);
                    String query = "update candidate set vail = ? where username = ?";
                    PreparedStatement preparedStmt = Con.prepareStatement(query);
                    preparedStmt.setString(1, "2");
                    preparedStmt.setString(2, t);
                    preparedStmt.executeUpdate();
                    preparedStmt.close();
               } catch (SQLException ex) {
            Logger.getLogger(candidateinweb.class.getName()).log(Level.SEVERE, null, ex);
        }
      }          
      public String foundemailusingname(String name) throws ClassNotFoundException{
         Class.forName("org.gjt.mm.mysql.Driver");
         String nn="";
               try {
                   Con=(Connection) DriverManager.getConnection(url, user, password);
                   String query = "SELECT * FROM candidate";
                   Statement st = (Statement) Con.createStatement();
                   ResultSet rs = st.executeQuery(query);
                  while(rs.next()){
                    String  k=rs.getString("username");
                      if(k.equals(name)){
                           nn=rs.getString("email");
                           return nn;
                      }
                      
                  }
                    Con.close();;
               } catch (SQLException ex) {
            Logger.getLogger(candidateinweb.class.getName()).log(Level.SEVERE, null, ex);
        }
    
              return  nn;
    }
       public void addd(emp y) throws ClassNotFoundException{
                Class.forName("org.gjt.mm.mysql.Driver");
               
               try {
                   Con=(Connection) DriverManager.getConnection(url, user, password);
                   String q="insert into candidate (username,password,email,phone,score,sequence,cv,vail)" + " values (?, ?, ?, ?, ?, ?,?,?)";
                   PreparedStatement statement = (PreparedStatement) Con.prepareStatement(q);
                   statement.setString(1, y.name);
                    statement.setString(2, y.pass); 
                    statement.setString(3, y.eamil);
                     statement.setString(4, y.phone);
                     statement.setInt(5, 0);
                         statement.setString(6, "");
                           statement.setBlob(7, y.inputStream);
                     statement.setString(8, "0");
                     
                  
                   
                     
                    statement.execute();
                    Con.close();;             
            } catch (SQLException ex) { 
            Logger.getLogger(candidateinweb.class.getName()).log(Level.SEVERE, null, ex);
        } 
        }
               
     public  String checck_userReg(String name)  throws SQLException, ClassNotFoundException{
               Class.forName("org.gjt.mm.mysql.Driver");
               int x=1;
               
               try {
                  
                   Con=(Connection) DriverManager.getConnection(url, user, password);
                   String q="SELECT * FROM candidate ";
                   Statement st = (Statement) Con.createStatement();
                   ResultSet rs = st.executeQuery(q);
                  while(rs.next()){
                      String t=rs.getString("username");
                      if(t.equals(name)){
                          return "fo";
                      }
                  }
                    Con.close();;
                     return "not";
    } catch (SQLException ex) {  
            Logger.getLogger(candidateinweb.class.getName()).log(Level.SEVERE, null, ex);
        }
               return "not";
}
  public String check_userloginpass(String name) throws ClassNotFoundException{
       Class.forName("org.gjt.mm.mysql.Driver");
               int x=1;
              
               
               try {
                  
                   Con=(Connection) DriverManager.getConnection(url, user, password);
                   String q="SELECT * FROM candidate";
                   Statement st = (Statement) Con.createStatement();
                   ResultSet rs = st.executeQuery(q);
                  while(rs.next()){
                      String t=rs.getString("password");
                      if(t.equals(name)){
                          return "fo";
                      }
                  }
                    Con.close();;
                     return "not";
    }   catch (SQLException ex) {  
            Logger.getLogger(candidateinweb.class.getName()).log(Level.SEVERE, null, ex);
        }
               return "not";
      
  }

    public String getvaule(String nn) throws ClassNotFoundException {
         Class.forName("org.gjt.mm.mysql.Driver");
               int x=1;
               try {
                   Con=(Connection) DriverManager.getConnection(url, user, password);
                   String q="SELECT * FROM candidate";
                   Statement st = (Statement) Con.createStatement();
                   ResultSet rs = st.executeQuery(q);
                  while(rs.next()){
                      String t=rs.getString("username");
                      if(t.equals(nn)){
                          String pp=rs.getString("vail");
                          System.out.println(t+" "+pp+"  45 l  ");
                          return  pp;
                      }
                  }
                    Con.close();;
                     return "not";
    }   catch (SQLException ex) {  
            Logger.getLogger(candidateinweb.class.getName()).log(Level.SEVERE, null, ex);
        }
               return "not";
      
    }
}
