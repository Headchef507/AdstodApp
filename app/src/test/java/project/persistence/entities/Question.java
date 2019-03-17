package project.persistence.entities;

import javax.persistence.*;
import java.util.Iterator;
/*
The class that each questionText and there multiple answers.
 */

@Entity
@Table(name = "questionsice")
public class Question implements Iterator<Question> {

    // Declares that this attribute is the id (primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String questionText;
    private String[] answerOptions;
    private int answer;

    //PostitNote used an empty constructor, because we need to be able to create an empty PostitNote to add
    //to our model so we can use it with our form
    public Question() {
        //We need and empty Question in the beginning
    }
    public Question(long id, String questionText, String[] answerOptions, int answer){
        this.id = id;
        this.questionText = questionText;
        this.answerOptions = answerOptions;
        this.answer = answer;

    }

    //the id of each questionText
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }


    //this is where the answer the User picks best fits him or her.
    public int getAnswer(){ return answer; }

    public void setAnswer(int answer){ this.answer = answer; }

    //the questionText for the User. For example: questionText == "What is your sex?"
    public String getQuestionText() { return questionText; }

    public void setQuestionText(String questionText) { this.questionText = questionText; }


   //These are the users answer options
    //Some range from: "I strongly agree" to "I strongly disagree"
    //others are simple yes and no questions.
    public String[] getAnswerOptions() { return answerOptions; }

    public void setAnswerOptions(String[] answerOptions) { this.answerOptions = answerOptions; }

    //The following hasNext og Next eru til að ítra yfir allar spurningarnar
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Question next() {
        return null;
    }

    //This is for easier debug in PostitNote
    /*
    @Override
    public String toString(){
        return String.format(
               "Postit Note[name=%s, note=%s]",
                name,note);
    }
    */
}
