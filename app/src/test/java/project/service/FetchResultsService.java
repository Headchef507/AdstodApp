package project.service;

import project.persistence.entities.AssistanceResource;

import java.sql.SQLException;
import java.util.List;

public interface FetchResultsService {

    //Information in corresponding implementation (FetchResultsServiceImplementation)

    List<AssistanceResource> getResults() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException;

}
