package com.example.adstod;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//CONTROLLER
//TextView and Button are VIEW
public class MainActivity extends AppCompatActivity {

    private Button mTrueButton; //m-ið er name nameing convention í Android
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviousButton;
    private TextView mQuestionTextView;
    private String lanuage; //tilviksbreytan sem heldur utan um tungumálið
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
    private static final String KEY_IINDEX = "index";
    private int mCurrentIndex = 0;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_IINDEX, mCurrentIndex);
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
            mCurrentIndex = savedInstanceState.getInt(KEY_IINDEX,0);//retrieve question index or use default value of 0 if none was stored in Bundle
        }
        setContentView(R.layout.activity_main);
//To retrieve the QuestionTextView object from the view hierarchy
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        //Til að tengja við xml skrána þá notum við R.id.truebutton (R er auto generated class sem er tengingin okkar(don´t fuck with it))
        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if true is correct answer
                checkAnswer(true);
                //before the checkAnswer() method was created
                // Toast.makeText(MainActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
            }
        });
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if false is correct answer
                checkAnswer(false);
                //before the checkAnswer() method was created
                //Toast.makeText(MainActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
            }
        });
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Increment question index (modulo total number of questions) and display new question
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        mPreviousButton = (Button) findViewById(R.id.previous_button);
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

        //Determine appropriate response
        if (userPressedTrue == andswerIsTrue) {
            messageResId = R.string.correct_toast;
        }
        else {
            messageResId = R.string.incorrect_toast;
        }
//Create and show toast with response.
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
}
