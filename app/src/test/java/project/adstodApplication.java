package project;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The main class of the project.
 * By running the main class of {@link adstodApplication} then you start the Spring Boot system
 */

@SpringBootApplication
@EnableJpaRepositories
public class adstodApplication {
    public static void main(String[] args){
        SpringApplication.run(adstodApplication.class);
    }
}