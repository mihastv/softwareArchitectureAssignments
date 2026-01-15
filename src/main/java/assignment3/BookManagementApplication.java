package assignment3;

import assignment2.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookManagementApplication {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "assignment3");
        SpringApplication.run(BookManagementApplication.class, args);
    }
}