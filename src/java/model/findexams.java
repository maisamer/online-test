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
import need.examWithScore;

/**
 *
 * @author go
 */
public class findexams {
     String url = "jdbc:mysql://localhost:3306/examsystem";
    String user = "root";
    String password = "greedydp0283";
    public Connection Con =null;
     public  ArrayList<Integer> getidsforexams(int y) throws ClassNotFoundException{
                           Class.forName("org.gjt.mm.mysql.Driver");
                           ArrayList<Integer>ids=new ArrayList<Integer>();
                            try {
                                Con=(com.mysql.jdbc.Connection) DriverManager.getConnection(url, user, password);
                                 Statement st = (Statement) Con.createStatement();
                                
                                String qu="SELECT id FROM exam where type="+y+";";
                                ResultSet rs = st.executeQuery(qu);
                            
                                 while(rs.next()){
                                     int id=rs.getInt("id");
                                     ids.add(id);
                                 }
                                 Con.close();
                            } catch (SQLException ex) {   
             Logger.getLogger(findexams.class.getName()).log(Level.SEVERE, null, ex);
         }
             
             return ids;
             
             }
      public  ArrayList<examWithScore> getIdAndScore(int y) throws ClassNotFoundException{
                           Class.forName("org.gjt.mm.mysql.Driver");
                           ArrayList<examWithScore> re = new ArrayList<>();
                           ArrayList<examWithScore>ids=new ArrayList<examWithScore>();
                            try {
                                Con=(com.mysql.jdbc.Connection) DriverManager.getConnection(url, user, password);
                                 Statement st = (Statement) Con.createStatement();
                                
                                String qu="SELECT id,score FROM exam where type="+y+";";
                                ResultSet rs = st.executeQuery(qu);
                            
                                 while(rs.next()){
                                     int id=rs.getInt("id");
                                     int sc=rs.getInt("score");
                                     ids.add(new examWithScore(id,sc));
                                 }
                                 
                                 re=filter(ids);
                                 Con.close();
                            } catch (SQLException ex) {   
             Logger.getLogger(findexams.class.getName()).log(Level.SEVERE, null, ex);
         }
             
             return re;
             
    }
    public ArrayList<examWithScore> filter(ArrayList<examWithScore> all) throws ClassNotFoundException, SQLException{
        ArrayList<examWithScore> res=new ArrayList<>();
        for(int i=0;i<all.size();i++){
            candidateinweb f=new  candidateinweb();
            String t= f.getnames(all.get(i).exam);
            String q = "select vail from candidate where username = '"+t+"'";
            Class.forName("org.gjt.mm.mysql.Driver");
            Con=(com.mysql.jdbc.Connection) DriverManager.getConnection(url, user, password);
            Statement st = (Statement) Con.createStatement();
            ResultSet rs = st.executeQuery(q);
            if(rs.next()){
                String v = rs.getString("vail");
                if(v.equals("2"))
                    res.add(all.get(i));
            }
        }
        return res;
    }
    
}
