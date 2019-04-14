package com.example.adstod.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.adstod.networking.ConnectQuestions;
import com.example.adstod.networking.ConnectResults;
import com.example.adstod.networking.JsonDecode;
import com.example.adstod.entities.Question;
import com.example.adstod.fragments.QuestionFragment;
import com.example.adstod.R;
import com.example.adstod.networking.JsonEncode;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

// Controller for the question screen
public class QuestionActivity extends AppCompatActivity {

    private Button mNextButton;
    private Button mPreviousButton;

    private static final String KEY_INDEX = "com.example.adstod.index";
    private static final String KEY_PERMISSION = "com.example.adstod.permission";
    private static final String KEY_LANGUAGE = "com.example.adstod.language";
    private static final String KEY_QUESTIONS = "com.example.adstod.questions";

    private int mCurrentIndex = 0;
    private int mPermission;
    private String mLanguage = "ENG";
    private ArrayList<Question> mQuestions;
    private Question mCurrentQuestion;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        savedInstanceState.putSerializable(KEY_QUESTIONS, mQuestions);
    }

    // Change the displayed question
    private void changeQuestion(){
        TextView question_text_view = findViewById(R.id.question_text_view);
        question_text_view.setText(mCurrentQuestion.getQuestionText());

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

    // Public method to update answer from QuestionFragment
    public void updateAnswer(int answer) {
        mCurrentQuestion.setAnswer(answer);
        mQuestions.set(mCurrentIndex, mCurrentQuestion);
        setNextVisibility();
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
    // Also hide the button until an answer is selected
    private void setNextVisibility() {
        if (mCurrentQuestion.getAnswer() == 0) {
            mNextButton.setVisibility(View.INVISIBLE);
        } else {
            mNextButton.setVisibility(View.VISIBLE);
        }
        if (mCurrentIndex == mQuestions.size() - 1) {
            mNextButton.setText(R.string.finish_button);
        } else {
            mNextButton.setText(R.string.next_button);
        }
    }

    // Finish answering questions and send in the answers
    private void sendAnswers() {
        JSONArray jRes = null;
        // Encode answers into a JSONObject
        JsonEncode jEnc = new JsonEncode();
        JSONObject jAns = jEnc.composeAnswers(mQuestions, mPermission, mLanguage);

        // Initialise
        ConnectResults conRes = new ConnectResults(jAns);
        String[] params = {"http://adstodbackend.herokuapp.com/"};
        try {
            jRes = conRes.execute(params).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(jRes);
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

        // Fetch permission and language from LanguageActivity
        mPermission = getIntent().getIntExtra(KEY_PERMISSION, 0);
        mLanguage = getIntent().getStringExtra(KEY_LANGUAGE);

        // Fetch questions from database as a JSON array
        JSONArray jQuest = null;
        String[] params = {"http://adstodbackend.herokuapp.com/", mLanguage};
        try {
            jQuest = new ConnectQuestions().execute(params).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // Decode the JSON object received into an ArrayList of questions
        JsonDecode jDec = new JsonDecode();
        mQuestions = jDec.parseQuestions(jQuest);

        // Set current question
        mCurrentQuestion = mQuestions.get(mCurrentIndex);

        TextView question_text_view = findViewById(R.id.question_text_view);
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

        // Initialise button visibility
        setPreviousVisibility();
        setNextVisibility();

        // Next button listener
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex == 1) {
                    sendAnswers();
                }
                /*if (mCurrentIndex == mQuestions.size()-1) {
                    sendAnswers();
                } */else {
                    mCurrentIndex++;
                    // Set the current question
                    mCurrentQuestion = mQuestions.get(mCurrentIndex);
                    setPreviousVisibility();
                    setNextVisibility();
                    changeQuestion();
                }
            }
        });

        // Previous button listener
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex--;
                // Set the current question
                mCurrentQuestion = mQuestions.get(mCurrentIndex);
                setPreviousVisibility();
                setNextVisibility();
                changeQuestion();
            }
        });
    }
}
