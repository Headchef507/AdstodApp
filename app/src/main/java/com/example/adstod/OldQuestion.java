package com.example.adstod;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import org.json.simple.JSONArray;
import java.util.Map;

//USELESS NÚNA, ADDI SKAL EYÐA ÞESSUM KLASA!!!

//MODEL LAYER
//MODEL LAYER
//Class sem stjórnar flæði spurninga
public class OldQuestion {


    private int mTextResId;
    private boolean mAnswerTrue;

    public OldQuestion(int TextResId, boolean AnswerTrue){
        mAnswerTrue = AnswerTrue;
        mTextResId = TextResId;
    }

//    class JsonEncodeDemo {

 /*   public static void main(String[] args) throws JSONException {
        //https://www.javatpoint.com/java-json-example
        JSONObject obj = new JSONObject();

        obj.put("name", "foo");
        obj.put("num", new Integer(100));
        obj.put("balance", new Double(1000.21));
        obj.put("is_vip", new Boolean(true));

        System.out.print(obj);
    }
*/
    public void setTextResId(int TextResId) {
        this.mTextResId = mTextResId;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setAnswerTrue(boolean AnswerTrue) {
        this.mAnswerTrue = mAnswerTrue;
    }

    public boolean ismAnswerTrue() {
        return mAnswerTrue;
    }
}
