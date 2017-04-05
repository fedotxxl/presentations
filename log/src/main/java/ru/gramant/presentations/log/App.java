package ru.gramant.presentations.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Created on 05.04.17.
 */
@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
@Import({App.Config.class})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Configuration
    public static class Config {

    }
}
