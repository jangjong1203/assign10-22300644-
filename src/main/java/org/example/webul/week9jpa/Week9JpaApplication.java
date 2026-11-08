package org.example.webul.week9jpa;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class Week9JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week9JpaApplication.class, args);
    }


}
