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
import javax.servlet.http.HttpSession;
import model.modell;
import need.Question;

/**
 *
 * @author go
 */
@WebServlet(name = "start_exam", urlPatterns = {"/start_exam"})
public class start_exam extends HttpServlet {

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
            throws  ServletException,ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            int timeout = 60*60*48; // time of session
            HttpSession session=request.getSession();
            session.setMaxInactiveInterval(timeout);
            String username=session.getAttribute("username").toString();
          //  String username= "mai";
            //Model2 b=new Model2();
            modell m=new modell();
            System.out.println("iam hear wallahy");
            ArrayList<Integer> all = m.getAllExams(username); // change to username after adding login page
            ArrayList<Integer> allExams = m.sortExamsBySequence(username,all);
            session.setAttribute("exams", allExams);
            session.setAttribute("index", 0);
            ArrayList<Question> Questions = m.getAllQuestionInExam(allExams.get(0));
            int type = m.getExamType(allExams.get(0));
            int id = m.getExamId();
            session.setAttribute("examid", id);
            request.setAttribute("type", type);
            request.setAttribute("questions", Questions);
            request.getRequestDispatcher("showQuestion.jsp").forward(request, response);
            
            /***** Once The Time Out Is Reached. This Line Will Automatically Refresh The Page *****/
            response.setHeader("Refresh", timeout + "; URL=send_email");
        } catch (SQLException ex) {
            Logger.getLogger(start_exam.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(start_exam.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(start_exam.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(start_exam.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(start_exam.class.getName()).log(Level.SEVERE, null, ex);
        }
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
