package com.example.adstod;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

// Controller fyrir tungumálaskjá
public class LanguageActivity extends AppCompatActivity {

    private Button mEnglishButton;
    private Button mIcelandicButton;
    private Button mPolishButton;
    private String mLanguage;
    private static final String KEY_LANGUAGE = "com.example.adstod.language";

    private void applyLanguage() {
        Intent intent = new Intent(this, QuestionActivity.class);



        // Sendum tungumál áfram til að nota í næsta skjá
        intent.putExtra(KEY_LANGUAGE, mLanguage);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        if(new Connect().execute("https://adstod.herokuapp.com") == null) Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_LONG).show();
        else Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();

        // Takki fyrir ensku
        mEnglishButton = findViewById(R.id.english_button);
        mEnglishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLanguage = "english";
                applyLanguage();
            }
        });
        // Takki fyrir íslensku
        mIcelandicButton = findViewById(R.id.icelandic_button);
        mIcelandicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLanguage = "icelandic";
                applyLanguage();
            }
        });
        // Takki fyrir pólsku
        mPolishButton = findViewById(R.id.polish_button);
        mPolishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLanguage = "polish";
                applyLanguage();
            }
        });
    }
}
