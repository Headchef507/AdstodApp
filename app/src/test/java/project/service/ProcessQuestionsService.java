package project.service;

import project.persistence.entities.Question;

import java.sql.SQLException;

public interface ProcessQuestionsService {

    //See the corresponding implementation for all information on this service
    //ProcessQuestionServiceImplementation

    //int processAnswers(Question answers);

    Question findInitialQuestion() throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException;

    Question findNextQuestion(long id) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException;

    Question findPreviousQuestion(long id) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException;

    void saveAnswers(Question currentQuestion, int answer);

    int getAnswersSize() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;

    String getLanguage();

    void setLanguage(String language);
}
