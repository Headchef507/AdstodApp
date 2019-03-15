package com.example.adstod;

//MODEL LAYER
//MODEL LAYER
//Class sem stjórnar flæði spurninga
public class Question {


    private int mTextResId;
    private boolean mAnswerTrue;

    public Question(int TextResId, boolean AnswerTrue){
        mAnswerTrue = AnswerTrue;
        mTextResId = TextResId;
    }

    public void setTextResId(int TextResId) {
        this.mTextResId = mTextResId;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setAnswerTrue(boolean AnswerTrue) {
        this.mAnswerTrue = mAnswerTrue;
    }

    public boolean ismAnswerTrue() {
        return mAnswerTrue;
    }
}
