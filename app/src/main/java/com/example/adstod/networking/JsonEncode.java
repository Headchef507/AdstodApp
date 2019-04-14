package com.example.adstod.networking;

import com.example.adstod.entities.Question;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;

// Class to encode JSON objects
public class JsonEncode {
    // Encode answers to a JSONObject
    public JSONObject composeAnswers(ArrayList<Question> questions, int permission, String language) {
        JSONObject answerObj = new JSONObject();
        JSONArray answers = new JSONArray();

        // Retrieve the answers from the questions
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            answers.add(q.getAnswer());
        }

        // Insert the necessary values into the JSONObject
        answerObj.put("answers", answers);
        answerObj.put("permission", permission);
        answerObj.put("language", language);
        return answerObj;
    }
}
