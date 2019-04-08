package com.example.adstod;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

// Controller fyrir spurningar
public class QuestionActivity extends AppCompatActivity {

    private Button mNextButton;
    private Button mPreviousButton;
    private TextView mQuestionTextView;
    //bý til tilviksbreytu af Question með því að setja hana í lista
    private Question[] mQuestionBank = new Question[] {
    //harðkóðum rétta svarið inn. R.string.question_.. nær í eftirfarandi textan úr strings.xml
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_Akureyri, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_asia, true)
    };
    private static final String KEY_INDEX = "index";
    private static final String KEY_LANGUAGE = "com.example.adstod.language";
    private int mCurrentIndex = 0;
    private String mLanguage;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    private void updateQuestion(){
        //Retrieve text for current question and update the QuestionTextView to display it
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){ //check if we recieved any state from a previous instance of the activity
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);//retrieve question index or use default value of 0 if none was stored in Bundle
        }
        setContentView(R.layout.activity_question);

        // Ná í tungumál úr LanguageActivity
        mLanguage = getIntent().getStringExtra(KEY_LANGUAGE);

        //To retrieve the QuestionTextView object from the view hierarchy
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        //Til að tengja við xml skrána þá notum við R.id.truebutton (R er auto generated class sem er tengingin okkar(don´t fuck with it))
        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Increment question index (modulo total number of questions) and display new question
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        mPreviousButton = findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex - 1 ) % mQuestionBank.length;
                updateQuestion();
            }
        }));
        //After all the setup is complete, display first question
        updateQuestion();
    }
    private void checkAnswer(boolean userPressedTrue) {
        //Retrieve correct answer for current question
        boolean andswerIsTrue = mQuestionBank[mCurrentIndex].ismAnswerTrue();

        int messageResId = 0;

        //Create and show toast with response.
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
}
