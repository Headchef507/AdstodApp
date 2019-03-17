package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.persistence.entities.AssistanceResource;
import project.service.FetchResultsService;

import java.sql.SQLException;
import java.util.List;

/**
 * ResultsController uses FetchResultsService to get results from the database based on answers in the repository
 */
@Controller
public class ResultsController {

    // Instance Variables
    private FetchResultsService fetchResultsService;
    private List<AssistanceResource> assistanceResources;

    // Dependency Injection
    @Autowired
    public ResultsController(FetchResultsService fetchResultsService) {
        this.fetchResultsService = fetchResultsService;
    }

    // Function mapped to the /Results page at the end of the questionnaire
    @RequestMapping (value = "/Results", method = RequestMethod.GET)
    public String getResults(Model model) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        this.assistanceResources = fetchResultsService.getResults();

        model.addAttribute("Resources", assistanceResources);

        return "/Result";
    }
}
