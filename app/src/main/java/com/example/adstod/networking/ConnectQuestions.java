package com.example.adstod.networking;

import android.os.AsyncTask;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// A class to get questions from the server
public class ConnectQuestions extends AsyncTask<String, Void, JSONArray> {

    protected JSONArray doInBackground(String... params) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            // Create a query URL
            String concatUrl = params[0] + "?language=" + params[1];
            URL url = new URL(concatUrl);

            // Connect to the url
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Read input from connection i.e. get stuff
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));

            // Append input to a StringBuffer
            StringBuffer buffer = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");
            }

            // Various JSON stuff
            Object object;
            JSONArray arrayObj;
            JSONParser jsonParser = new JSONParser();

            // Parse buffered input to a JSONArray
            object = jsonParser.parse(buffer.toString());
            arrayObj = (JSONArray) object;

            return arrayObj;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
