/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author egypt2
 */
public class updateusers {
    String url = "jdbc:mysql://localhost:3306/examsystem";
    String user = "root";
    String password = "greedydp0283";
                Connection Con =null;
      public String makevlaidtion(String name , int l) throws ClassNotFoundException{
               if(l==0){
                   //delerte
                 return deleteuser(name);
               }
               //update by 2 to send mails 
               Class.forName("org.gjt.mm.mysql.Driver");
              
                  try {
                   Con=(Connection) DriverManager.getConnection(url, user, password);
                  String query = "update candidate set vail = ? where username = ?";
                    PreparedStatement preparedStmt = (PreparedStatement) Con.prepareStatement(query);
                    preparedStmt.setString(1, "1");
                    preparedStmt.setString(2, name);
                    // execute the java preparedstatement
                    preparedStmt.executeUpdate();
                    Con.close();;
    }   catch (SQLException ex) {
            Logger.getLogger(updateusers.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
}
    public String  deleteuser(String name) throws ClassNotFoundException{
        Class.forName("org.gjt.mm.mysql.Driver");
              
                  try {
                   Con=(Connection) DriverManager.getConnection(url, user, password);
                  String query = "delete from candidate where username = ?";
                   PreparedStatement preparedStmt = (PreparedStatement) Con.prepareStatement(query);
                   preparedStmt.setString(1, name);

      preparedStmt.execute();
      
                    Con.close();;
    }   catch (SQLException ex) {
            Logger.getLogger(updateusers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
       
    }
}
