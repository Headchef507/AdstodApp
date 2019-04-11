package com.example.adstod;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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

        // Takki fyrir ensku
        mEnglishButton = findViewById(R.id.english_button);
        mEnglishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLanguage = "ENG";
                applyLanguage();
            }
        });
        // Takki fyrir íslensku
        mIcelandicButton = findViewById(R.id.icelandic_button);
        mIcelandicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLanguage = "ICE";
                applyLanguage();
            }
        });
        // Takki fyrir pólsku
        mPolishButton = findViewById(R.id.polish_button);
        mPolishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLanguage = "POL";
                applyLanguage();
            }
        });
    }
}
