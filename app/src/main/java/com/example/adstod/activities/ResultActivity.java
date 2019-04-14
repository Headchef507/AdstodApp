package com.example.adstod.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.adstod.R;
import com.example.adstod.entities.Result;
import com.example.adstod.networking.JsonDecode;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.MalformedURLException;
import java.util.ArrayList;

// Controller for the result page
public class ResultActivity extends AppCompatActivity {
    private static final String KEY_RESULTS = "com.example.adstod.results";

    private ArrayList<Result> mResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Fetch results
        String res = getIntent().getStringExtra(KEY_RESULTS);

        // Various JSON stuff
        Object object = null;
        JSONArray arrayObj;
        JSONParser jsonParser = new JSONParser();

        // Parse JSON result string to a JSONArray
        try {
            object = jsonParser.parse(res);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        arrayObj = (JSONArray) object;

        // Decode the JSONArray into an ArrayList of results
        JsonDecode jDec = new JsonDecode();
        try {
            mResults = jDec.parseResults(arrayObj);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Create a new LinearLayout
        LinearLayout lay = findViewById(R.id.results);
        for (int i = 0; i < mResults.size(); i++) {
            // Fetch a result
            Result result = mResults.get(i);

            // Create a new TextView to display the result title
            TextView title = new TextView(this);
            title.setWidth(200);
            title.setText(result.getTitle());
            lay.addView(title);

            // Create a new TextView to display the result link
            TextView link = new TextView(this);
            link.setAutoLinkMask(Linkify.ALL);
            link.setText(result.getLink().toString());
            lay.addView(link);

            // Create a new TextView to display the result description
            TextView description = new TextView(this);
            description.setText(result.getDescription());
            lay.addView(description);

            // Create LinearLayout for the phone numbers
            LinearLayout numLay = new LinearLayout(this);
            numLay.setOrientation(LinearLayout.VERTICAL);
            int[] numbers = result.getPhoneNumbers();
            for (int j = 0; j < numbers.length; j++) {
                // Create a TextView for each phone number
                TextView number = new TextView(this);
                String pho = "Símanúmer: " + numbers[j];
                number.setText(pho);
                numLay.addView(number);
            }
            lay.addView(numLay);
        }
    }
}
