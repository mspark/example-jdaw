package de.mspark.example.jdaw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ComponentScan({ "de.mspark" })
public class App {
    
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
