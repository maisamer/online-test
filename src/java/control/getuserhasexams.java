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
import model.getexamdetels;
import model.modell;
import model.viwenewusers;
import need.Question;
import need.emp;

/**
 *
 * @author egypt2
 */
@WebServlet(name = "getuserhasexams", urlPatterns = {"/getuserhasexams"})
public class getuserhasexams extends HttpServlet {

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
           viwenewusers V=new viwenewusers();
           ArrayList<emp> em=new  ArrayList<emp>();
           em= V.displaynewuserswithvail2();
           out.print(em.size());
            modell v=new modell();
           ArrayList< ArrayList<Question> > exams= new ArrayList<ArrayList<Question> >();
            ArrayList< ArrayList<String> > Typesforall=new ArrayList< ArrayList<String> > ();
            ArrayList<ArrayList<Integer> > idsforall=new ArrayList<ArrayList<Integer> > ();
           for(int i=0;i<em.size();i++){
               ArrayList<Integer> ids=new ArrayList<Integer> ();
                 ids=v.getAllExams(em.get(i).name);
                 idsforall.add(ids);
                 
           }
              System.out.print("jik");
               for(int i=0;i<idsforall.size();i++){
                 //get exam type
                   ArrayList<String> Types=new ArrayList<String> ();
                  for(int j=0;j<idsforall.get(i).size();j++){
                        getexamdetels TT=new getexamdetels();
                        String vv=TT.getexamtypeusingid(idsforall.get(i).get(j));
                         Types.add(vv);
                  }
                  Typesforall.add(Types);
                 
               }
          
           for(int i=0;i<idsforall.size();i++){
                for(int j=0;j<idsforall.get(i).size();j++){
                        ArrayList<Question> z=new ArrayList<Question>();
                        z=v.getAllQuestionInExam(idsforall.get(i).get(j));
                          exams.add(z);
                  }
           }
             request.setAttribute("emp",em);
              request.setAttribute("exams",exams);
               request.setAttribute("Typesforall",Typesforall);
             RequestDispatcher rd=request.getRequestDispatcher("printusers.jsp");
             rd.forward(request, response);
                        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(getuserhasexams.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(getuserhasexams.class.getName()).log(Level.SEVERE, null, ex);
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
