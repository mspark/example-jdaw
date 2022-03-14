package de.mspark.example.jdaw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = "de.mspark.example")
@ComponentScan({ "de.mspark.jdaw", "de.mspark.example" })
@EntityScan({"de.mspark.jdaw"})
public class App {
    
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
