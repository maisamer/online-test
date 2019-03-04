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
import model.getexamdetels;
import model.modell;
import need.Question;

/**
 *
 * @author go
 */
@WebServlet(name = "sarchaboutusers", urlPatterns = {"/sarchaboutusers"})
public class sarchaboutusers extends HttpServlet {

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
           String R="";  
           String typesearch=request.getParameter("search");
           if(typesearch.contains("@")){
                candidateinweb d=new candidateinweb();
                out.print("here");
                R= d.serchbyemailto(typesearch);
           }else{
               R=typesearch;
           }  
           
           out.print("user name"+R);
            ArrayList< ArrayList<Question> > exams= new ArrayList<ArrayList<Question> >();
               modell v=new modell();
               ArrayList<Integer> ids=new ArrayList<Integer> ();
               ArrayList<String> Types=new ArrayList<String> ();
               ids=v.getAllExams(R);
               
               for(int i=0;i<ids.size();i++){
                   ArrayList<Question> z=new ArrayList<Question>();
                   z=v.getAllQuestionInExam(ids.get(i));
                   exams.add(z);
                 
               }
               System.out.print("jik");
               for(int i=0;i<ids.size();i++){
                 //get exam type
                 getexamdetels TT=new getexamdetels();
                 String vv=TT.getexamtypeusingid(ids.get(i));
                 Types.add(vv);
                 
               }
               // go to show full test for specific user 
                request.setAttribute("name",R);
                 request.setAttribute("exams",exams);
               
              //report 
               ArrayList<Integer>   scores=new ArrayList<Integer> ();
                for(int i=0;i<ids.size();i++){
                 //get exam type
                 getexamdetels TT=new getexamdetels();
                 int vv=TT.getexamscors(ids.get(i));
                 scores.add(vv);
               }
              candidateinweb B=new  candidateinweb();
               int vvv=B.getscore(R);
               request.setAttribute("types",Types);
                 request.setAttribute("scores",scores);  
                 request.setAttribute("fullsecore",vvv);
               //  out.print(Types.size()+" "+scores.size()+" "+vvv);
                RequestDispatcher rd=request.getRequestDispatcher("allinfo.jsp");
                 rd.forward(request, response);
              
       
         
         } catch (ClassNotFoundException ex) {
            Logger.getLogger(sarchaboutusers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(sarchaboutusers.class.getName()).log(Level.SEVERE, null, ex);
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
