package com.example.adstod.networking;

import com.example.adstod.entities.Question;
import com.example.adstod.entities.Result;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.net.MalformedURLException;
import java.net.URL;
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

    // Decode a JSONArray into an int array of phone numbers
    private int[] parsePhoneNumbers(JSONArray array) {
        int[] theNumbers = new int[array.size()];
        for (int i = 0; i < array.size(); i++) {
            theNumbers[i] = Integer.parseInt((String) array.get(i));
        }
        return theNumbers;
    }

    // Decode a JSONArray into an ArrayList of Results
    public ArrayList<Result> parseResults(JSONArray jArr) throws MalformedURLException {
        ArrayList<Result> results = new ArrayList<>();
        JSONObject temp;
        JSONArray tempArray;
        for (int i = 0; i < jArr.size(); i++) {
            Result res = new Result();
            temp = (JSONObject) jArr.get(i);

            // Result ID
            long id = (long) temp.get("id");
            res.setId(id);

            // Result title
            String rTitle = (String) temp.get("title");
            res.setTitle(rTitle);

            // Result link
            URL rLink = new URL((String) temp.get("link"));
            res.setLink(rLink);

            // Result description
            String rDescription = (String) temp.get("description");
            res.setDescription(rDescription);

            // Phone numbers
            tempArray = (JSONArray) temp.get("phonenumbers");
            assert tempArray != null;
            int[] rNumbers = parsePhoneNumbers(tempArray);
            res.setPhoneNumbers(rNumbers);

            // Add the question to the ArrayList
            results.add(i, res);
        }
        return results;
    }
}
