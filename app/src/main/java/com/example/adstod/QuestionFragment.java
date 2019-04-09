package com.example.adstod;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

// Fragment for answer options
public class QuestionFragment extends Fragment {

    private String[] mOptions;
    private int mSelectedOption;

    public static final String KEY_OPTIONS= "com.example.adstod.options";
    public static final String KEY_SELECTED= "com.example.adstod.selected";

    public static QuestionFragment newInstance() {
        return new QuestionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.question_fragment, container, false);

        // Get the number of answer options
        Bundle args = getArguments();
        mOptions = args != null ? args.getStringArray(KEY_OPTIONS) : new String[0];
        mSelectedOption = args != null ? args.getInt(KEY_SELECTED) : 0;

        // Add as many radio buttons as there are answer options
        final RadioGroup rgp = myView.findViewById(R.id.options);
        for (int i = 1; i <= mOptions.length; i++) {
            RadioButton rbn = new RadioButton(myView.getContext());
            if (i == mSelectedOption) {
                rbn.setChecked(true);
            }
            rbn.setId(i);
            rbn.setText(mOptions[i-1]);

            // Add a listener to the buttons to determine which is selected
            rbn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedOption = rgp.getCheckedRadioButtonId();
                }
            });
            rgp.addView(rbn);
        }
        return myView;
    }

}
