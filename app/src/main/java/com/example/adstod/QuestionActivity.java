package com.example.adstod;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

// Controller for the question screen
public class QuestionActivity extends AppCompatActivity {

    private Button mNextButton;
    private Button mPreviousButton;

    private static final String KEY_INDEX = "com.example.adstod.index";
    private static final String KEY_LANGUAGE = "com.example.adstod.language";
    private static final String KEY_QUESTIONS = "com.example.adstod.questions";

    private int mCurrentIndex = 0;
    private String mLanguage = "ENG";
    private ArrayList<Question> mQuestions;
    private Question mCurrentQuestion;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        savedInstanceState.putSerializable(KEY_QUESTIONS, mQuestions);
    }

    private void updateQuestion(){
        // Set the current question
        mCurrentQuestion = mQuestions.get(mCurrentIndex);

        // Create fragment and give it an argument
        QuestionFragment newFragment = new QuestionFragment();
        Bundle args = new Bundle();

        // Put answer options and answer into the fragment
        args.putStringArray(QuestionFragment.KEY_OPTIONS, mCurrentQuestion.getAnswerOptions());
        args.putInt(QuestionFragment.KEY_SELECTED, mCurrentQuestion.getAnswer());
        newFragment.setArguments(args);

        // Begin the fragment transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the question_fragment view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.question_fragment, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    // Public method to update answer from fragment
    public void updateAnswer(int answer) {
        mCurrentQuestion.setAnswer(answer);
        mQuestions.set(mCurrentIndex, mCurrentQuestion);
    }

    // If on the first question make the previous button invisible
    private void setPreviousVisibility() {
        if (mCurrentIndex == 0) {
            mPreviousButton.setVisibility(View.INVISIBLE);
        } else {
            mPreviousButton.setVisibility(View.VISIBLE);
        }
    }

    // If on the last question make the next button a finish button
    private void setNextVisibility() {
        /*
        if (mCurrentIndex == mQuestions.size() - 1 && mCurrentIndex != 0) {
            mNextButton.setText(R.string.finish_button);
        } else {
            mNextButton.setText(R.string.next_button);
        }*/
        mNextButton.setText(R.string.next_button);
    }

    private void sendResults() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if we received any state from a previous instance of the activity
        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
            mQuestions = (ArrayList<Question>) savedInstanceState.getSerializable(KEY_QUESTIONS);
        }
        setContentView(R.layout.activity_question);

        // Fetch language from LanguageActivity
        mLanguage = getIntent().getStringExtra(KEY_LANGUAGE);

        // Fetch questions from database
        JSONArray j = null;
        String[] params = {"http://adstodbackend.herokuapp.com/", mLanguage};
        try {
            j = new Connect().execute(params).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        JsonDecode guy = new JsonDecode();
        try {
            mQuestions = guy.TheJsonParser(j);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Set current question
        mCurrentQuestion = mQuestions.get(mCurrentIndex);

        TextView question_text_view = findViewById(R.id.question_text_view);
        System.out.println(mCurrentQuestion.getQuestionText());
        question_text_view.setText(mCurrentQuestion.getQuestionText());

        // Create a new fragment to be placed in the activity layout
        QuestionFragment firstFragment = new QuestionFragment();

        // Add number of options to the fragment
        Bundle args = new Bundle();
        args.putStringArray(QuestionFragment.KEY_OPTIONS, mCurrentQuestion.getAnswerOptions());
        args.putInt(QuestionFragment.KEY_SELECTED, mCurrentQuestion.getAnswer());
        firstFragment.setArguments(args);

        // Add the fragment to the 'question_fragment' FrameLayout
        getSupportFragmentManager().beginTransaction()
                .add(R.id.question_fragment, firstFragment).commit();

        // Navigation buttons
        mPreviousButton = findViewById(R.id.previous_button);
        mNextButton = findViewById(R.id.next_button);

        // Change buttons if on first or last question
        setPreviousVisibility();
        setNextVisibility();

        // Next button listener
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex == mQuestions.size()-1 && mCurrentIndex != 0) {
                    sendResults();
                } else {
                    mCurrentIndex++;
                    setPreviousVisibility();
                    setNextVisibility();
                    updateQuestion();
                }
            }
        });

        // Previous button listener
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex--;
                setPreviousVisibility();
                setNextVisibility();
                updateQuestion();
            }
        });
    }
}
