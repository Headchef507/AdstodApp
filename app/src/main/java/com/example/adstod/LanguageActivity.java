package com.example.adstod;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

//CONTROLLER
//TextView and Button are VIEW
public class LanguageActivity extends AppCompatActivity {

    private Button mEnglishButton;
    private Button mIcelandicButton;
    private Button mPolishButton;
    private String mLanguage = "";
    private static final String KEY_LANGUAGE = "language";

    private void applyLanguage() {
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra(KEY_LANGUAGE, mLanguage);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        //Til að tengja við xml skrána þá notum við R.id.truebutton (R er auto generated class sem er tengingin okkar(don´t fuck with it))
        mEnglishButton = findViewById(R.id.english_button);
        mEnglishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLanguage = "english";
                applyLanguage();
            }
        });
        //Til að tengja við xml skrána þá notum við R.id.truebutton (R er auto generated class sem er tengingin okkar(don´t fuck with it))
        mIcelandicButton = findViewById(R.id.icelandic_button);
        mIcelandicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLanguage = "icelandic";
                applyLanguage();
            }
        });
        //Til að tengja við xml skrána þá notum við R.id.truebutton (R er auto generated class sem er tengingin okkar(don´t fuck with it))
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
