package com.example.adstod.networking;

import android.os.AsyncTask;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

// A class to send results to the server
public class ConnectResults extends AsyncTask<String, Void, JSONArray> {
    private JSONObject jAnswers;

    public ConnectResults(JSONObject jAns) {
        this.jAnswers = jAns;
    }

    protected JSONArray doInBackground(String... params) {

        String urlString = params[0]; // URL to call
        OutputStream out;

        try {
            // Create a url
            URL url = new URL(urlString);

            // Open a connection to the url
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            // Adjust request settings
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");

            // Write to the output, i.e. post stuff
            out = new BufferedOutputStream(urlConnection.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(jAnswers.toJSONString());
            writer.flush();
            writer.close();
            out.close();

            // Connect to the url
            urlConnection.connect();

            // Read input from connection, i.e. get stuff
            InputStream stream = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
