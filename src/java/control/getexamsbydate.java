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
import need.Question;
import need.examWithScore;

/**
 *
 * @author egypt2
 */
@WebServlet(name = "getexamsbydate", urlPatterns = {"/getexamsbydate"})
public class getexamsbydate extends HttpServlet {

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
           String d=request.getParameter("da");
            modell m = new modell();
            ArrayList<examWithScore> all = m.getExamsIdWithDate(d);
            ArrayList<Integer> exam = new ArrayList<>();
            ArrayList<String> users = new ArrayList<>();
            for(int i=0;i<all.size();i++){
                exam.add(all.get(i).score);
                users.add(m.getUserToExam(all.get(i).exam));
            }
             ArrayList< ArrayList<Question> > exams= new ArrayList<ArrayList<Question> >();
             modell v=new modell();
             for(int i=0;i<all.size();i++){
                   ArrayList<Question> z=new ArrayList<Question>();
                   z=v.getAllQuestionInExam(all.get(i).exam);
                   exams.add(z);
               }
            //HttpSession session=request.getSession();
              request.setAttribute("ex", exams);
            request.setAttribute("extype", d);
            request.setAttribute("list",users);
            request.setAttribute("sc",exam);
          request.getRequestDispatcher("showexam.jsp").forward(request, response);
          out.print(all.size());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(getexamsbydate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(getexamsbydate.class.getName()).log(Level.SEVERE, null, ex);
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
