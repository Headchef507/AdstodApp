package project.service.Implementation;

//import org.springframework.stereotype.Service;
import project.persistence.entities.AssistanceResource;
import project.persistence.entities.Question;
import project.persistence.repositories.QuestionRepository;
import project.service.FetchResultsService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//@Service
public class FetchResultsServiceImplementation implements FetchResultsService {

    // Instance Variables
    private QuestionRepository repository = QuestionRepository.getInstance();              //to contact the repository
    private List<Question> answeredQuestion;            //placeholder for the Questions List to work with them in getResults()
    private List<AssistanceResource> assistanceResources = new ArrayList<>(); //List of all of the AssistanceResource

    /*
    * This is where we receive the AssistanceResources and add theme to the list
    * the first one is the one that fits the most
    * If no answers fit the criteria for each Assistance Resource
    * the default will be chosen.
    */
    @Override
    public List<AssistanceResource> getResults() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        this.answeredQuestion = repository.getAnswers();

        //In all following comments above if statements:
        //AR = Assistance Resource

        //If true, this will add the AR "Kvennathvarf"
        if (answeredQuestion.get(0).getAnswer() == 1 &&
                answeredQuestion.get(5).getAnswer() == 1)
            assistanceResources.add(repository.getResult((long)1));

        //If true, this will add the AR "Stigamót"
        if(answeredQuestion.get(5).getAnswer() == 1)
            assistanceResources.add(repository.getResult((long)2));

        //If true, this will add the AR "Frú Ragnheiður"
        if(answeredQuestion.get(5).getAnswer() > 3 && answeredQuestion.get(10).getAnswer() > 3)
            assistanceResources.add(repository.getResult((long)3));

        //If true, this will add the AR "Heimsóknarvinur"
        if(answeredQuestion.get(2).getAnswer() == 5 && answeredQuestion.get(15).getAnswer() > 3) //you can add all of the "Hversu vel á eftirfarandi"
            assistanceResources.add(repository.getResult((long)4));

        //If true, this will add the AR "Heilahristingur"
        if(answeredQuestion.get(2).getAnswer() == 1 && answeredQuestion.get(11).getAnswer() == 1)
            assistanceResources.add(repository.getResult((long)5));

        //If nothing is chosen, then the default AR "Þjónustumiðstöð Laugardals og Háaleitis" will be sent to the user
        else
            assistanceResources.add(repository.getResult((long)6)); //default Assistance Resource



        return assistanceResources; //here comes the default AssistanceResource
    }


}
