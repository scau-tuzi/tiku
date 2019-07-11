package io.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("io.swagger.pojo")
public class TikuApplication {

    public static void main(String[] args) {
        SpringApplication.run(TikuApplication.class, args);
    }

}
