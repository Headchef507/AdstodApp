package com.example.adstod;

import org.json.JSONObject;
import org.json.JSONException;

public class JsonEncode {
    public JSONObject getJSONQuestions(String language) throws JSONException {
        JSONObject obj = new JSONObject();

        obj.put("name", language);/*
        obj.put("num", new Integer(100));
        obj.put("balance", new Double(1000.21));
        obj.put("is_vip", new Boolean(true));
        */

        System.out.print(obj);
        return obj;
    }

    public JSONObject getJSONResult(String[] answers) throws JSONException{
        JSONObject jsonAnswers = new JSONObject();
        for(int i = 0; i<answers.length;i++) {
            jsonAnswers.put("name" + i, answers[i]);
        }
        return jsonAnswers;
    }


}
