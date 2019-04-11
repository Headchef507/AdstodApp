package com.example.adstod;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

// Controller fyrir upphafssíðu
public class MainActivity extends AppCompatActivity {
    private Button mAcceptButton;
    private Button mRejectButton;
    private static final String KEY_PERMISSION = "com.example.adstod.language";

    private void acceptTerms() {
        Intent intent = new Intent(this, LanguageActivity.class);
        intent.putExtra(KEY_PERMISSION, 1);
        startActivity(intent);
    }

    private void rejectTerms() {
        Intent intent = new Intent(this, LanguageActivity.class);
        intent.putExtra(KEY_PERMISSION, 0);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Takki til að samþykkja skilmála
        mAcceptButton = findViewById(R.id.accept_button);
        mAcceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceptTerms();
            }
        });
        mRejectButton = findViewById(R.id.reject_button);
        mRejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rejectTerms();
            }
        });
    }
}
