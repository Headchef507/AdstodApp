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

// Fragment fyrir spurningu og valmöguleika
public class QuestionFragment extends Fragment {

    private int mSelectedOption = 1;
    private int mNumButtons = 5;

    public static final String KEY_NUMBUTTONS= "com.example.adstod.numbuttons";

    public static QuestionFragment newInstance() {
        return new QuestionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.question_fragment, container, false);

        Bundle args = getArguments();
        mNumButtons = args.getInt(KEY_NUMBUTTONS);

        // Búa til jafn marga radio takka og það eru valmöguleikar
        RadioGroup rgp = myView.findViewById(R.id.options);
        for (int i = 1; i <= mNumButtons; i++) {
            RadioButton rbn = new RadioButton(myView.getContext());
            if (i == mSelectedOption) {
                rbn.setChecked(true);
            }
            rbn.setId(i);
            rbn.setText("RadioButton" + i);
            rgp.addView(rbn);
        }
        return myView;
    }

}
