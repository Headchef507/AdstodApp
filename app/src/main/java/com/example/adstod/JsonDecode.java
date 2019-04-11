package com.example.adstod;

import org.json.JSONException;
import org.json.simple.JSONObject;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.util.ArrayList;

public class JsonDecode {
    JSONParser parser = new JSONParser();
    ArrayList<Question> questions = new ArrayList<>();

    //adding my Answer options to Object Question
    public String[] AnswerOptionParser(JSONArray array) throws JSONException {
        String[] theOptions = new String[array.size()];
        int i = 0;
        while (i < array.size()) {
            theOptions[i] = (String) array.get(i);
            i++;
        }
        return theOptions;
    }

    //Method to create the List<Question>
    public ArrayList<Question> TheJsonParser(JSONArray object) throws JSONException {
        int i = 0;
        Question q;
        JSONObject temp;
        JSONArray arraytemp;
        while (i < object.size()) {
            Question que = new Question();

            temp = (JSONObject) object.get(0);
            String qtext = (String) temp.get("questiontext");
            que.setQuestionText(qtext);

            arraytemp = (JSONArray) temp.get("options");
            String[] qoptions = AnswerOptionParser(arraytemp);
            que.setAnswerOptions(qoptions);

            questions.add(i, que);
            i++;
        }
        return questions;
    }
}
