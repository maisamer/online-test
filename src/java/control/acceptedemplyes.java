/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.modell;
import model.updateusers;
import need.Question;
/**
 *
 * @author egypt2
 */
@WebServlet(name = "acceptedemplyes", urlPatterns = {"/acceptedemplyes"})
public class acceptedemplyes extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String name=request.getParameter("name");
           String f="";
           int j=0;
           for(int i=0;i<name.length();i++){
               if(name.charAt(i)=='.'){
                   j=i+1;
                   break;
               }
           }
           for(int x=j;x<name.length();x++){
               f+=name.charAt(x);
           }
           updateusers v=new updateusers();
           int l;
           
           if(name.charAt(0)=='a'){
               l=1;
           }else{
               l=0;
           }
           String n=v.makevlaidtion(f,l);
           /// user name in n
           String L="";
           if(l==1){
               //sendmailtouser s=new sendmailtouser();
               //s.sendmail(n);
               System.out.println("entet here "+f);
                String aq=request.getParameter("choose");
                System.out.println(aq);
                
               modell m=new modell();
                if(aq.contains("1")){
                   ArrayList<Question> ques = m.allQuestion(f, 1);
                
                }
                if(aq.contains("2")){
                    L+="java , ";
                   ArrayList<Question> ques = m.allQuestion(f, 2);
                
                }
                if(aq.contains("3")){
                    L+="english, ";
                    ArrayList<Question> ques = m.allQuestion(f, 3);
                
                }
                if(aq.contains("4")){
                    L+="IQ , ";
                   ArrayList<Question> ques = m.allQuestion(f, 4);
                
                }
                m.updateUserSequence(f,aq);
               sendmailforacceptedusers Q=new sendmailforacceptedusers();
               Q.sendmail(f,"accepted start exam " , "Dear candidate , hr accepted your so log in and start this exam "+L+"enert in link ");
                
           }
          System.out.print(L);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(acceptedemplyes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(acceptedemplyes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
