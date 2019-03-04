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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import need.Question;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;  
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import need.Question;
import need.examWithScore;
public class modell  {
    private int examid;
    String url = "jdbc:mysql://localhost:3306/examsystem";
    String user = "root";
    String password = "greedydp0283";
    String que;
    java.sql.Connection Con =null;
    java.sql.Statement Stmt = null;
    java.sql.ResultSet RS = null;
    
    public int getExamId(){
        return examid;
    }
    
    public ArrayList<Question> allQuestion(String username,int type) throws ClassNotFoundException, SQLException{
        Class.forName("org.gjt.mm.mysql.Driver");
        Con =null;
        Stmt = null;
        RS = null;
        Con = java.sql.DriverManager.getConnection(url, user, password);
        Stmt = Con.createStatement();  
        int numberofcat = 0;
        if(type==1){//python
            numberofcat=2;
        }
        else if(type==2){//java
            numberofcat=3;
        }
        else if(type==3){//English
            numberofcat=4;
        }
        else if(type==4){//IQ
            numberofcat=2;
        }
        this.examid =createExam(username, type);
        
        int check[]=new int[numberofcat+1];
        ArrayList<Question> Questions = new ArrayList<>();        
        que = "SELECT * FROM question "+"WHERE type = "+type+" ;";
        RS =Stmt.executeQuery(que);  
        // add question
        while(RS!=null&&RS.next()){
            int cat=RS.getInt("category");
            if(check[cat]<4){
                Question q=new Question();
                q.que=RS.getString("question");
                q.corect_ans=RS.getString("answer");
                q.cat=RS.getInt("category");
                q.setquesId(RS.getInt("id"));
                check[cat]++;
                Questions.add(q);
            }
        }
        // add wrong answer
        for(int i=0;i<Questions.size();i++){
            ArrayList<String> wrong=addWrongAnswer(Stmt,type,Questions.get(i).cat);
            ArrayList<String> a =sortAnswer(wrong,Questions.get(i).corect_ans);
            Questions.get(i).ans=a;
        }
        // insert into question_exam
        addQuestionToExam(Con,examid,Questions);
        Con.close();
        return Questions;
    }
    
    public int createExam(String username,int type) throws ClassNotFoundException, SQLException{
        java.sql.Connection Con =null;
        java.sql.Statement Stmt = null;
        java.sql.ResultSet RS = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
        Date date = new Date(); 
        java.sql.Date sDate = convertUtilToSql(date);
        Class.forName("org.gjt.mm.mysql.Driver");
        int id = 0;
        Con = java.sql.DriverManager.getConnection(url, user, password);
        Stmt = Con.createStatement(); 
        que = "SELECT examid FROM const ;";
        RS =Stmt.executeQuery(que);
        while(RS.next()){
            id = RS.getInt("examid");
        } 
        RS.close();
        que = "insert into  exam(id,type,score,date)"+ " values (?,?,?,?)";
        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = Con.prepareStatement(que);
        preparedStmt.setInt (1, id);
        preparedStmt.setInt (2, type);
        preparedStmt.setInt(3, 0);
        preparedStmt.setDate(4, sDate);
        // execute the preparedstatement
        preparedStmt.execute();
        
        que = "insert into candidate_exam(examid,username)"+ " values (?,?)";
        preparedStmt = Con.prepareStatement(que);
        preparedStmt.setInt (1, id);
        preparedStmt.setString (2, username);
        System.out.println("username is :"+username);
        preparedStmt.execute();
        preparedStmt.close();
        // update exam id
        updateExamId(Con,id+1);
        return id;
    }

    private ArrayList<String> addWrongAnswer(Statement stmt, int type,int cat) throws SQLException {
        ArrayList<String> all=new ArrayList<>();
        ArrayList<String> a=new ArrayList<>();
        java.sql.ResultSet r = null;
        String qu="";
        if(type==1){
            qu = "SELECT * FROM python "+"WHERE category = "+cat+" ;";
        }
        else if(type==2){
            qu = "SELECT * FROM java "+"WHERE category = "+cat+" ;";

        }
        else if(type==3){
            qu = "SELECT * FROM english "+"WHERE category = "+cat+" ;";

        }
        else if(type==4){
            qu = "SELECT * FROM iq "+"WHERE category = "+cat+" ;";
        }
        r = stmt.executeQuery(qu);
        while(r.next()){
            all.add(r.getString("wrong"));
        }
        a = chose(all);
        //System.out.println("size is "+a.size());
        return a;
    }
    private boolean take(){
        Random rand = new Random();
        double d =rand.nextFloat();
        if(d>0.5)
            return true;
        return false;
    }

    private ArrayList<String> chose(ArrayList<String> all) {
        ArrayList<String> a=new ArrayList<>();
        int count=0;
        for(int i=0;i<all.size();i++){
            if(count>=3)
                break;
            if((take()||(all.size()-i==(3-count)))){                
                a.add(all.get(i));
                count++;
            }
        }
        return a;
    }
    private java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;

    }

    private void updateExamId(Connection conn, int i) throws SQLException {
        String query = "update const set examid = ? where id = ?";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt   (1, i);
        preparedStmt.setInt(2, 1);
        preparedStmt.executeUpdate();
        preparedStmt.close();
    }

    private ArrayList<String> sortAnswer(ArrayList<String> wrong, String corect_ans) {
        ArrayList<String> a =new ArrayList<>();
        int index = random(0,3);
        int j=0;
        for(int i=0;i<4;i++){
            if(index==i)
                a.add(corect_ans);
            else{
                if(j<3)
                    a.add(wrong.get(j));
                j++;
            }
        }
        return a;
    }

    private int random(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

    private void addQuestionToExam(Connection Con1, int examid, ArrayList<Question> Questions) throws SQLException {
        PreparedStatement preparedStmt;
        for(int i=0;i<Questions.size();i++){
            que="insert into question_exam(examid,questionid,answer1,answer2,answer3,answer4,correctAnswer,answer,skipped,marked)" + "values (?,?,?,?,?,?,?,?,?,?)";  
            preparedStmt = Con1.prepareStatement(que);
            preparedStmt.setInt (1, examid);
            preparedStmt.setInt (2, Questions.get(i).getquesId());
            preparedStmt.setString(3,Questions.get(i).ans.get(0));
            preparedStmt.setString(4,Questions.get(i).ans.get(1));
            preparedStmt.setString(5,Questions.get(i).ans.get(2));
            preparedStmt.setString(6,Questions.get(i).ans.get(3));
            preparedStmt.setString(7,Questions.get(i).corect_ans);
             preparedStmt.setInt(8,5);
             preparedStmt.setInt(9,0);
              preparedStmt.setInt(10,0);
            preparedStmt.execute();
            preparedStmt.close();
        }
    }
    public void saveAnswer(int examid,int queid,int choice) throws ClassNotFoundException, SQLException{
        Class.forName("org.gjt.mm.mysql.Driver");
        Con = java.sql.DriverManager.getConnection(url, user, password);
        String query = "update question_exam set answer = ? where examid = ? and questionid = ?";
        PreparedStatement preparedStmt = Con.prepareStatement(query);
        preparedStmt.setInt   (1, choice);
        preparedStmt.setInt(2, examid);
        preparedStmt.setInt(3, queid);
        preparedStmt.executeUpdate();
        preparedStmt.close();
        Con.close();
    }
    public void calculateScore(String username) throws ClassNotFoundException, SQLException{
        int allScore=0;
        int score=0;
        Class.forName("org.gjt.mm.mysql.Driver");
        Con = java.sql.DriverManager.getConnection(url, user, password);
        que = "select examid from candidate_exam "+"WHERE username = '"+username+"'";
        System.out.println(que);
        Stmt = Con.createStatement(); 
        RS =Stmt.executeQuery(que);
        ArrayList<Integer> exams = new ArrayList<>();
        while(RS.next()){
            exams.add(RS.getInt("examid"));
        }
        for(int i=0;i<exams.size();i++){
            score = ScoreExam(Stmt,exams.get(i)); //calculate new seore
            updateExamScore(Con,exams.get(i),score);   // update exam score
            allScore+=score;
        }
        updateCandidateScore(Con,username,allScore);
        Con.close();
        Stmt.close();
        RS.close();
    }

    private int ScoreExam(Statement Stmt, Integer get) throws SQLException {
        int score=0;
        que = "select * from question_exam where examid = " + get;
        RS =Stmt.executeQuery(que);
        System.out.println("here in score exam");
        while(RS.next()){
            ArrayList<String> ans = new ArrayList<>();
            String correct = RS.getString("correctAnswer");
            ans.add(RS.getString("answer1"));
            ans.add(RS.getString("answer2"));
            ans.add(RS.getString("answer3"));
            ans.add(RS.getString("answer4"));
            int choice = RS.getInt("answer");
            if(choice >= 0&&choice <= 3){
                System.out.println(correct + " corr vs "+ans.get(choice));
                if(correct.equals(ans.get(choice))){
                    score++; 
                }
            }
        }      
        return score;
    }
    
    public ArrayList<Integer> getAllExams(String name) throws ClassNotFoundException, SQLException{
        ArrayList<Integer> all = new ArrayList<>();
        Class.forName("org.gjt.mm.mysql.Driver");
        Con = java.sql.DriverManager.getConnection(url, user, password);
        Stmt = Con.createStatement(); 
        String querey = "SELECT * FROM candidate_exam "+"WHERE username= '"+name+"'";
        System.out.println("here lolllllllll: "+name);
        RS =Stmt.executeQuery(querey);
        while(RS.next()){
            System.out.println(RS.getInt("examid"));
            all.add(RS.getInt("examid"));
        }
        ArrayList<Integer> sall = sortExamsBySequence(name,all);
        Con.close();
        RS.close();
        Stmt.close();
        return all;
    }
    

    private void updateExamScore(Connection Con, Integer id, int score) throws SQLException {
        que = "update exam set score = ? where id = ?";
        PreparedStatement preparedStmt = Con.prepareStatement(que);
        preparedStmt.setInt   (1,score);
        preparedStmt.setInt(2, id);
        preparedStmt.executeUpdate();
        preparedStmt.close();
    }

    private void updateCandidateScore(Connection Con, String username, int allScore) throws SQLException {
        que = "update candidate set score = ? where username = ?";
        PreparedStatement preparedStmt = Con.prepareStatement(que);
        preparedStmt.setInt(1,allScore);
        preparedStmt.setString(2,username);
        preparedStmt.executeUpdate();
        preparedStmt.close();
    }
    public ArrayList<Question> getAllQuestionInExam(int examId) throws ClassNotFoundException, SQLException{
        ArrayList<Question> Questions = new ArrayList<Question>();
        Class.forName("org.gjt.mm.mysql.Driver");
        Con = java.sql.DriverManager.getConnection(url, user, password);
        Stmt = Con.createStatement(); 
        que = "select * from question_exam where examid = " + examId ;
        RS =Stmt.executeQuery(que);
        while(RS.next()){
            Question q = new Question(); 
            
            q.selected=RS.getInt("answer");
            q.setquesId(RS.getInt("questionid"));
            ArrayList<String> a = new ArrayList<>();
            //System.out.println(RS.getString("answer1"));
            a.add(RS.getString("answer1"));
            a.add(RS.getString("answer2"));
            a.add(RS.getString("answer3"));
            a.add(RS.getString("answer4"));
            q.ans = a;
            q.corect_ans = RS.getString("correctAnswer");
            Questions.add(q);
        }
        for(int i=0;i<Questions.size();i++){
            que = "select question from question where id = " + Questions.get(i).getquesId();
            RS =Stmt.executeQuery(que);
            if(RS.next())
               Questions.get(i).que = RS.getString("question");
        }
        return Questions;
    }
    public int getExamType(int examId) throws ClassNotFoundException, SQLException{
        this.examid = examId;
        int type=-1;
        Class.forName("org.gjt.mm.mysql.Driver");
        Con = java.sql.DriverManager.getConnection(url, user, password);
        Stmt = Con.createStatement(); 
        que = "select type from exam where id = " + examId ;
        RS =Stmt.executeQuery(que);
        if(RS.next()){
            type = RS.getInt("type");
        }
        return type;
    }
     public String getscore(String name) throws ClassNotFoundException, SQLException {
        String sc="";
        Class.forName("org.gjt.mm.mysql.Driver");
        int x=1;
        Con=(Connection) DriverManager.getConnection(url, user, password);
        String q="SELECT * FROM candidate where username="+name+";";
        Statement st = (Statement) Con.createStatement();
        ResultSet rs = st.executeQuery(q);
       while(rs.next()){
          sc=rs.getString("score");
       }
         Con.close();
                    
    return sc;
    }
             
    public void updateUserSequence(String name, String seq) throws ClassNotFoundException, SQLException {
        Class.forName("org.gjt.mm.mysql.Driver");
        Con=(Connection) DriverManager.getConnection(url, user, password);
        que = "update candidate set sequence = ? where username = ?";
        PreparedStatement preparedStmt = Con.prepareStatement(que);
        preparedStmt.setString(1, seq);
        preparedStmt.setString(2, name);
        preparedStmt.executeUpdate();
        preparedStmt.close();
    }

    public ArrayList<Integer> sortExamsBySequence(String name, ArrayList<Integer> all) throws ClassNotFoundException, SQLException {
        ArrayList<Integer> res = new ArrayList<>();
        String seq = getSequence(name);
        ArrayList<Integer> types =new ArrayList<>();
        for(int i=0;i<all.size();i++){
            types.add(getExamType(all.get(i)));
        }
        for(int i=0;i<seq.length();i++){            
            int tp = Integer.parseInt(seq.charAt(i)+"");
            for(int j=0;j<types.size();j++){
                if(tp==types.get(j))
                    res.add(all.get(j));
            }
        }
        return res;
    }

    public String getSequence(String name) throws ClassNotFoundException, SQLException {
        String seq ="";
        que = "select sequence from candidate where username = '" + name + "'";
        Class.forName("org.gjt.mm.mysql.Driver");
        Con=(Connection) DriverManager.getConnection(url, user, password);
        Stmt = Con.createStatement();
        RS = Stmt.executeQuery(que);
        if(RS.next()){
            return RS.getString("sequence");
        }
        return seq;
    }
    public void skipQuestion(int examid,int questionid) throws ClassNotFoundException, SQLException{
        Class.forName("org.gjt.mm.mysql.Driver");
        Con=(Connection) DriverManager.getConnection(url, user, password);
        que = "update question_exam set skipped = ? where examid = ? and questionid = ?";
        PreparedStatement preparedStmt = Con.prepareStatement(que);
        preparedStmt.setInt(1,1);
        preparedStmt.setInt(2,examid);
        preparedStmt.setInt(3,questionid);
        preparedStmt.executeUpdate();
        preparedStmt.close();
    }
    public void markQuestion(int examid,int questionid) throws ClassNotFoundException, SQLException{
        Class.forName("org.gjt.mm.mysql.Driver");
        Con=(Connection) DriverManager.getConnection(url, user, password);
        que = "update question_exam set marked = ? where examid = ? and questionid = ?";
        PreparedStatement preparedStmt = Con.prepareStatement(que);
        preparedStmt.setInt(1,1);
        preparedStmt.setInt(2,examid);
        preparedStmt.setInt(3,questionid);
        preparedStmt.executeUpdate();
        preparedStmt.close();
    }
     public ArrayList<examWithScore> getExamsIdWithDate(String date) throws ClassNotFoundException, SQLException{
        Class.forName("org.gjt.mm.mysql.Driver");
        Con=(Connection) DriverManager.getConnection(url, user, password);
        ArrayList<examWithScore> res = new ArrayList<>();
        que = "select * from exam" ;
        Stmt = Con.createStatement();
        RS = Stmt.executeQuery(que);
        while(RS.next()){
            String c=RS.getString("date");
            if(date.equals(c)){
                 System.out.print(RS.getString("date")+" ");
                 int id = RS.getInt("id");
                  int sc = RS.getInt("score");
                     res.add(new examWithScore(id,sc));
            }
           
        }
        Con.close();
        Stmt.close();
        RS.close();
        return res;
    }
    public String getUserToExam(int examId) throws ClassNotFoundException, SQLException{
        String name="";
        Class.forName("org.gjt.mm.mysql.Driver");
        Con=(Connection) DriverManager.getConnection(url, user, password);
        que = "select username from candidate_exam where examid = " + examId;
        Stmt = Con.createStatement();
        RS = Stmt.executeQuery(que);
        if(RS.next()){
            name = RS.getString("username");
        }
        Con.close();
        Stmt.close();
        RS.close();

        return name;
    }



}

