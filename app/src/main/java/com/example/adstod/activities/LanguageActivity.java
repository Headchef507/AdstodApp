package com.example.adstod.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.adstod.R;

// Controller for language screen
public class LanguageActivity extends AppCompatActivity {

    private Button mEnglishButton;
    private Button mIcelandicButton;
    private Button mPolishButton;

    private int mPermission;
    private String mLanguage;

    private static final String KEY_PERMISSION = "com.example.adstod.permission";
    private static final String KEY_LANGUAGE = "com.example.adstod.language";

    private void applyLanguage() {
        Intent intent = new Intent(this, QuestionActivity.class);

        // Pass the language on to the next activity
        intent.putExtra(KEY_LANGUAGE, mLanguage);
        intent.putExtra(KEY_PERMISSION, mPermission);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        // Fetch permission from LanguageActivity
        mPermission = getIntent().getIntExtra(KEY_PERMISSION, 0);

        // Button for English
        mEnglishButton = findViewById(R.id.english_button);
        mEnglishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLanguage = "ENG";
                applyLanguage();
            }
        });
        // Button for Icelandic
        mIcelandicButton = findViewById(R.id.icelandic_button);
        mIcelandicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLanguage = "ICE";
                applyLanguage();
            }
        });
        // Button for Polish
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
