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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.candidateinweb;
import model.findexams;
import model.modell;
import need.Question;
import need.examWithScore;

/**
 *
 * @author go
 */
@WebServlet(name = "findjavaexam", urlPatterns = {"/findjavaexam"})
public class findjavaexam extends HttpServlet {

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
            findexams x=new findexams();
           ArrayList<examWithScore>ids= x.getIdAndScore(2);
          
           ArrayList<String> l=new     ArrayList<String>();
            ArrayList<Integer> sec=new     ArrayList<Integer>();
           for(int i=0;i<ids.size();i++){
               candidateinweb f=new  candidateinweb();
                 String t= f.getnames(ids.get(i).exam);
                 l.add(t);
                 sec.add(ids.get(i).score);
           }
            
           request.setAttribute("ex","java");
             ArrayList< ArrayList<Question> > exams= new ArrayList<ArrayList<Question> >();
             modell v=new modell();
             for(int i=0;i<ids.size();i++){
                   ArrayList<Question> z=new ArrayList<Question>();
                   z=v.getAllQuestionInExam(ids.get(i).exam);
                   exams.add(z);
                 
               }
           request.setAttribute("ex", exams);
             request.setAttribute("list",l);
             request.setAttribute("sc",sec);
           RequestDispatcher rd=request.getRequestDispatcher("showexam.jsp");
           rd.forward(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(findjavaexam.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(findjavaexam.class.getName()).log(Level.SEVERE, null, ex);
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
