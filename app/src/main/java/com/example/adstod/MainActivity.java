package com.example.adstod;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

//CONTROLLER
//TextView and Button are VIEW
public class MainActivity extends AppCompatActivity {

    private Button mAcceptButton;

    private void acceptTerms() {
        Intent intent = new Intent(this, LanguageActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Til að tengja við xml skrána þá notum við R.id.truebutton (R er auto generated class sem er tengingin okkar(don´t fuck with it))
        mAcceptButton = findViewById(R.id.accept_button);
        mAcceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceptTerms();
            }
        });
    }
}
