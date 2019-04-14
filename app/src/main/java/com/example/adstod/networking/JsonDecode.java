package com.example.adstod.networking;

import com.example.adstod.entities.Question;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.util.ArrayList;

// Class to decode JSON objects
public class JsonDecode {
    // Decode a JSONArray into a String array of answer options
    private String[] parseAnswerOptions(JSONArray array) {
        String[] theOptions = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            theOptions[i] = (String) array.get(i);
        }
        return theOptions;
    }

    // Decode a JSONArray into an ArrayList of Questions
    public ArrayList<Question> parseQuestions(JSONArray jArr) {
        ArrayList<Question> questions = new ArrayList<>();
        JSONObject temp;
        JSONArray tempArray;
        for (int i = 0; i < jArr.size(); i++) {
            Question que = new Question();

            // Question ID
            temp = (JSONObject) jArr.get(i);
            long id = (long) temp.get("id");
            que.setId(id);

            // Question text
            String qText = (String) temp.get("questiontext");
            que.setQuestionText(qText);

            // Answer options
            tempArray = (JSONArray) temp.get("options");
            assert tempArray != null;
            String[] qOptions = parseAnswerOptions(tempArray);
            que.setAnswerOptions(qOptions);

            // Add the question to the ArrayList
            questions.add(i, que);
        }
        return questions;
    }
}
