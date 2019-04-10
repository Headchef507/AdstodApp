package com.example.adstod;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

class Connect extends AsyncTask<String, Void, JSONObject> {
    @Override
    protected JSONObject doInBackground(String... strings) {
        try{
            HttpsURLConnection connection;
            try {
                connection = (HttpsURLConnection) new URL("https://adstod.herokuapp.com/allquestions").openConnection();
                JSONObject o = (JSONObject) connection.getContent();
                if(o == null) return new JSONObject("test");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
