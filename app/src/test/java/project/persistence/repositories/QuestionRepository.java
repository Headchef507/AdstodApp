package project.persistence.repositories;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import project.persistence.entities.AssistanceResource;
import project.persistence.entities.Question;
import java.sql.*;

/**
 * The repository, used to access the database, is created as a Singleton in order to have one instance
 * active across the session.
 * For simplicity on our part when working in our relational MySQL database,
 * we decided to create the repository in a more brute-force JDBC way
 * Therefore, we do not create this as an interface implementing JpaRepository
 */
@Repository
public class QuestionRepository {
    private static QuestionRepository repository = null;                    // Initilizing the repo as a singleton
    private boolean initList = true;                                        // Boolean used for initializing the list
    private List<Question> answers = null;
    private Question q;
    private List<AssistanceResource> results = null;
    private Connection conn = null;
    private String username = "kannski";
    private String password = "Kannski123";
    private String url = "jdbc:mysql://localhost:3306/adstodQuestions";
    private String language = "ICE";

    // Normally in a Singleton class the constructor is private but Spring throws an error if that is the case
    public QuestionRepository() {
        String [] a = new String[1];
        this.q = new Question(0, "", a, 0);
        this.answers = new ArrayList<Question>();
        this.results = new ArrayList<AssistanceResource>();
    }

    /**
     * Creates a new instance of the repository and makes it accessible to all parts of the project
     * @return the repository instance
     */
    public static QuestionRepository getInstance(){
        if(repository == null)
            repository = new QuestionRepository();
        return repository;
    }

    /**
     * Sets answer as the answer part of a Question entity at position theId in the List<Question> answers
     */
    public void saveAnswers(int theId, Question answer){
        this.answers.set(theId, answer);
    }

    /**
     * Returns an AssistanceResource from the database with the ID Long id
     */
    public AssistanceResource getResult(Long id) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        conn = DriverManager.getConnection(url, username, password);

        // Default statement in case the if statements below fail
        String stmt2 = "SELECT a.ID, a.Title, a.Link, p.Number, a.Description, a.PhoneNumberCount FROM AssistanceResourcesICE a JOIN PhoneNumbersForResourcesICE p2 ON a.ID = p2.AssistanceResourceID JOIN PhoneNumbers p ON p.ID = p2.PhoneNumberID WHERE a.ID = " + id.toString();
        if(language.equals("ICE")) // If the language is set to icelandic
            stmt2 = "SELECT a.ID, a.Title, a.Link, p.Number, a.Description, a.PhoneNumberCount FROM AssistanceResourcesICE a JOIN PhoneNumbersForResourcesICE p2 ON a.ID = p2.AssistanceResourceID JOIN PhoneNumbers p ON p.ID = p2.PhoneNumberID WHERE a.ID = " + id.toString();
        else if(language.equals("ENG")) // If the language is set to english
            stmt2 = "SELECT a.ID, a.Title, a.Link, p.Number, a.Description, a.PhoneNumberCount FROM AssistanceResourcesENG a JOIN PhoneNumbersForResourcesENG p2 ON a.ID = p2.AssistanceResourceID JOIN PhoneNumbers p ON p.ID = p2.PhoneNumberID WHERE a.ID = " + id.toString();
        else if(language.equals("POL")) // If the language is set to polish
            stmt2 = "SELECT a.ID, a.Title, a.Link, p.Number, a.Description, a.PhoneNumberCount FROM AssistanceResourcesPOL a JOIN PhoneNumbersForResourcesPOL p2 ON a.ID = p2.AssistanceResourceID JOIN PhoneNumbers p ON p.ID = p2.PhoneNumberID WHERE a.ID = " + id.toString();

        Statement prep = conn.createStatement();
        ResultSet r = prep.executeQuery(stmt2);

        // Getting the amount of phone numbers available for the AssistanceResource to initialize the array
        int numberOfPhoneNumbers = 0;
        AssistanceResource a = new AssistanceResource();
        if(r.next()) {
            numberOfPhoneNumbers = r.getInt(6);
        }
        String[] nums = new String[numberOfPhoneNumbers];

        // Setting the first few variables
        nums[0] = r.getString(4);
        a.setId(r.getLong(1));
        a.setTitle(r.getString(2));
        a.setLink(r.getString(3));
        a.setDescription(r.getString(5));

        // Run through the rest of the results (if there are any) and fill the phone number array
        int j = 1;
        while(r.next()){
            nums[j] = r.getString(4);
            j++;
        }
        a.setPhNumbers(nums);

        // Close the connection and return the assistanceResource object
        conn.close();
        return a;
    }

    //The function sends into FetchResults the list of questions with their answers
    //so it is possible to work with it
    public List<Question> getAnswers(){
        return this.answers;
    }

    /**
     * Gets a question with the ID Long id from the database
     * @param id
     * @return
     */
    public Question findQuestion(Long id) throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        //Initialize list to get up the empty list to work with. Only happens on first run through
        if(initList){
            for(int j = 0; j < getAnswersSize();j++){
                this.answers.add(this.q);
            }
            this.initList = false;
        }

        // Establish database connection and create statement based on language
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        conn = DriverManager.getConnection(url, username, password);
        String stmt = "SELECT q.id, q.QuestionText, q.OptionCount, o2.OptionText FROM QuestionsICE q JOIN OptionsForAnswersICE o on q.ID = o.QuestionID JOIN OptionsICE o2 on o.OptionID = o2.ID WHERE q.id = " + id.toString();
        if(language.equals("ICE"))
            stmt = "SELECT q.id, q.QuestionText, q.OptionCount, o2.OptionText FROM QuestionsICE q JOIN OptionsForAnswersICE o on q.ID = o.QuestionID JOIN OptionsICE o2 on o.OptionID = o2.ID WHERE q.id = " + id.toString();
        else if(language.equals("ENG"))
            stmt = "SELECT q.id, q.QuestionText, q.OptionCount, o2.OptionText FROM QuestionsENG q JOIN OptionsForAnswersENG o on q.ID = o.QuestionID JOIN OptionsENG o2 on o.OptionID = o2.ID WHERE q.id = " + id.toString();
        else if(language.equals("POL"))
            stmt = "SELECT q.id, q.QuestionText, q.OptionCount, o2.OptionText FROM QuestionsPOL q JOIN OptionsForAnswersPOL o on q.ID = o.QuestionID JOIN OptionsPOL o2 on o.OptionID = o2.ID WHERE q.id = " + id.toString();
        Statement prep = conn.createStatement();
        ResultSet r = prep.executeQuery(stmt);
        int numberOfOptions = 0;
        if(r.next()) {
            numberOfOptions = r.getInt(3);
        }
        String[] options = new String[numberOfOptions];
        options[0] = r.getString(4);
        Question q = new Question();
        int j = 1;
        while(r.next()){
            q.setId(r.getLong(1));
            q.setQuestionText(r.getString(2));
            options[j] = r.getString(4);
            j++;
        }
        q.setAnswerOptions(options);
        conn.close();
        return q;
    }

    /**
     * Fetches the number of questions in the database to limit sizes of lists
     */
    public int getAnswersSize() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        conn = DriverManager.getConnection(url, username, password);
        String stmt = "SELECT id FROM QuestionsICE ORDER BY ID DESC LIMIT 1;";
        if(language.equals("ICE"))
            stmt = "SELECT id FROM QuestionsICE ORDER BY ID DESC LIMIT 1;";
        else if(language.equals("ENG"))
            stmt = "SELECT id FROM QuestionsENG ORDER BY ID DESC LIMIT 1;";
        else if(language.equals("POL"))
            stmt = "SELECT id FROM QuestionsPOL ORDER BY ID DESC LIMIT 1;";
        Statement prep = conn.createStatement();
        ResultSet r = prep.executeQuery(stmt);
        int c = 0;
        if(r.next())
            c = r.getInt(1);
        return c;
    }

    // Returns the language that has been set
    public String getLanguage(){ return this.language; }

    // Sets the language for the website and questions
    public void setLanguage(String language){ this.language = language; }

}
