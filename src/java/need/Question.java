
package need;

import java.util.ArrayList;


public class Question {
    private int quesId;
    public int cat;
    public String que;
    public String corect_ans;
    public ArrayList<String> ans;
    public int selected;
    public Question(){
        selected =-1;
    }
    public String getque(int i){
        String x = "q"+i;
        return x;
    }
    public String getans(int i){
        return i+"";
    }
    public void setquesId(int id){
        this.quesId = id;
    }
    public int getquesId(){
        return this.quesId ;
    }
    
}
