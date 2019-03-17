package project.controller;

// Imports needed
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.persistence.entities.Question;
import project.service.ProcessQuestionsService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Controller
public class SessionController {

    // Initializing variables
    private ProcessQuestionsService processQuestionsService;
    private Question currentQuestion = null;
    private int numberOfQuestions = 2;

    // Constructor
    @Autowired
    public SessionController(ProcessQuestionsService processQuestionsService) { this.processQuestionsService = processQuestionsService; }

    @RequestMapping (value = "/", method = RequestMethod.GET)
    public String home(){
        // The string "Index" that is returned here is the name of the view
        // (the Index.jsp file) that is in the path /main/webapp/WEB-INF/jsp/
          return "Index";
    }

    @RequestMapping (value = "/Question", method = RequestMethod.GET)
    public String getInitialQuestion(Model model, HttpServletRequest request) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        // Set the selected language
        String language = request.getParameter("lang");
        request.getSession().setAttribute("Language", language);
        this.processQuestionsService.setLanguage(language);

        // Add the total number of questions to the view
        this.numberOfQuestions = this.processQuestionsService.getAnswersSize();
        request.getSession().setAttribute("QuestionCount", this.numberOfQuestions);

        // Retrieve the initial question and add it to the view
        this.currentQuestion = this.processQuestionsService.findInitialQuestion();
        model.addAttribute("Question", this.currentQuestion);

        // Return the view
        return "/Question";
    }

    @RequestMapping (value = "/NextQuestion", method = RequestMethod.GET)
    public String getNextQuestionFromID(Model model, HttpServletRequest request) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        // Retrieve the selected answer and save it
        String selectedAnswer = request.getParameter("answer");
        this.processQuestionsService.saveAnswers(this.currentQuestion, Integer.parseInt(selectedAnswer));

        // Retrieve the next question and add it to the view
        this.currentQuestion = this.processQuestionsService.findNextQuestion(this.currentQuestion.getId());
        model.addAttribute("Question", this.currentQuestion);

        // Return the view
        return "/Question";
    }

    @RequestMapping (value = "/PrevQuestion", method = RequestMethod.GET)
    public String getPreviousQuestionFromID(Model model) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        // Retrieve the previous question and add it to the view
        this.currentQuestion = this.processQuestionsService.findPreviousQuestion(this.currentQuestion.getId());
        model.addAttribute("Question", this.currentQuestion);

        // Return the view
        return "/Question";
    }
}
