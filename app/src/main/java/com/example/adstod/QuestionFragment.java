package com.example.adstod;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class QuestionFragment extends Fragment {

    private int selectedOption = 1;

    public static QuestionFragment newInstance() {
        return new QuestionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.question_fragment, container, false);

        // Búa til jafn marga radio takka og það eru valmöguleikar
        RadioGroup rgp = myView.findViewById(R.id.options);
        int buttons = 5;
        for (int i = 1; i <= buttons ; i++) {
            RadioButton rbn = new RadioButton(myView.getContext());
            if (i == selectedOption) {
                rbn.setChecked(true);
            }
            rbn.setId(i);
            rbn.setText("RadioButton" + i);
            rgp.addView(rbn);
        }
        return myView;
    }

}
