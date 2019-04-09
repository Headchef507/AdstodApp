package com.example.adstod;


// The question entity
public class Question {

    // Declares that this attribute is the id (primary key
    private long id;
    private String questionText;
    private String[] answerOptions;
    private int answer;

    // Constructors for question
    public Question() {}
    public Question(long id, String questionText, String[] answerOptions, int answer){
        this.id = id;
        this.questionText = questionText;
        this.answerOptions = answerOptions;
        this.answer = answer;
    }

    // Id for a question
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    // The questionText for the User. For example: questionText == "What is your sex?"
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public String getQuestionText() { return questionText; }

    // These are the users answer options
    // Some range from: "I strongly agree" to "I strongly disagree"
    // Others are simple yes and no questions.
    public String[] getAnswerOptions() { return answerOptions; }

    public void setAnswerOptions(String[] answerOptions) { this.answerOptions = answerOptions; }

    // The answer options the user picks
    public int getAnswer(){ return answer; }

    public void setAnswer(int answer){ this.answer = answer; }

}
