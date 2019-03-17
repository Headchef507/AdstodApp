package project.service.Implementation;

//import org.springframework.stereotype.Service;
import project.persistence.entities.Question;
import project.persistence.repositories.QuestionRepository;
import project.service.ProcessQuestionsService;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Iterator;
import java.util.ListIterator;


//@Service
public class ProcessQuestionsServiceImplementation implements ProcessQuestionsService {


    //Creates an instance of the repository
    private QuestionRepository repository = QuestionRepository.getInstance();

    // Finds the initial question to start it all off
    @Override
    public Question findInitialQuestion() throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        return this.repository.findQuestion((long) 1);
    }

    /*
    * Finds the next question after by using it´s id.
    * If it's a jump question it checks whether you answered yes on that question.
    * If so it will continue as normal, if not, it will send you next question after that
    */
    @Override
    public Question findNextQuestion(long id) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        if (id == 4 || id == 9) {
            if(this.repository.getAnswers().get((int)id).getAnswer()==2)
                id += 2;
            else
                id += 1;
        }
        else
            id += 1;
        return this.repository.findQuestion(id);
    }

    /*
    * Finds the previous question to question number id
    * If the previousQuestion was skipped, it go to the question before that
    */
    @Override
    public Question findPreviousQuestion(long id) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        if(id==6 || id==11){
            if(this.repository.getAnswers().get((int)id-2).getAnswer()==2)
                id -= 2;
            else
                id -= 1;
        }
        else
            id -= 1;
        return this.repository.findQuestion(id);
    }

    /*
    * Receives the selected answer from the Question and stores it in the repository
    */
    @Override
    public void saveAnswers(Question currentQuestion, int answer) {
        currentQuestion.setAnswer(answer);
        this.repository.saveAnswers((int)currentQuestion.getId(),currentQuestion);
    }


    //This returns how many Questions there are in the database
    public int getAnswersSize() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        return this.repository.getAnswersSize();
    }

    //Getters and setter for which language the user requested
    public String getLanguage(){ return this.repository.getLanguage(); }

    public void setLanguage(String language){ this.repository.setLanguage(language); }
}
