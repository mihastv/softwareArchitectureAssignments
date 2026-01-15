package assignment2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "assignment2");
        SpringApplication.run(Application.class, args);
    }
}