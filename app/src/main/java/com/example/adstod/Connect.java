package com.example.adstod;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.json.simple.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

class Connect extends AsyncTask<String, Void, JSONArray> {

    /*
    protected void onPreExecute() {
        super.onPreExecute();

        ProgressDialog pd = new ProgressDialog();
        pd.setMessage("Please wait");
        pd.setCancelable(false);
        pd.show();
    }*/

    protected JSONArray doInBackground(String... params) {


        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            String concatUrl = params[0] + "?language=" + params[1];
            URL url = new URL(concatUrl);
            System.out.println(concatUrl);

            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            connection.setRequestMethod("GET");

            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line;

            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");
            }

            Object object;
            JSONArray arrayObj;
            JSONParser jsonParser=new JSONParser();
            object=jsonParser.parse(buffer.toString());
            arrayObj=(JSONArray) object;

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
