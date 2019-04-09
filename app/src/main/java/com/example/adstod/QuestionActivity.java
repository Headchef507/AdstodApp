package com.example.adstod;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

// Controller fyrir spurningar
public class QuestionActivity extends AppCompatActivity {

    private Button mNextButton;
    private Button mPreviousButton;
    private static final String KEY_INDEX = "index";
    private static final String KEY_LANGUAGE = "com.example.adstod.language";
    private int mCurrentIndex = 0;
    private String mLanguage = "english";

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    private void updateQuestion(){
        // Create fragment and give it an argument specifying the article it should show
        QuestionFragment newFragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putInt(QuestionFragment.KEY_NUMBUTTONS, 3);
        newFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.question_fragment, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
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
        if (mCurrentIndex == 4) {
            mNextButton.setText(R.string.finish_button);
        } else {
            mNextButton.setText(R.string.next_button);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Check if we recieved any state from a previous instance of the activity
        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
        }
        setContentView(R.layout.activity_question);

        // Fetch language from LanguageActivity
        mLanguage = getIntent().getStringExtra(KEY_LANGUAGE);

        // Create a new Fragment to be placed in the activity layout
        QuestionFragment firstFragment = new QuestionFragment();

        // In case this activity was started with special instructions from an
        // Intent, pass the Intent's extras to the fragment as arguments
        Bundle args = new Bundle();
        args.putInt(QuestionFragment.KEY_NUMBUTTONS, 5);
        firstFragment.setArguments(args);

        // Add the fragment to the 'question_fragment' FrameLayout
        getSupportFragmentManager().beginTransaction()
                .add(R.id.question_fragment, firstFragment).commit();

        // Navigation buttons
        mNextButton = findViewById(R.id.next_button);
        mPreviousButton = findViewById(R.id.previous_button);

        // Change buttons if on first or last question
        setPreviousVisibility();
        setNextVisibility();

        // Next button listener
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Increment question index (modulo total number of questions) and display new question
                mCurrentIndex++;
                setPreviousVisibility();
                setNextVisibility();
                updateQuestion();
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
