package io.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("io.swagger.pojo")
@ComponentScan(basePackages = {"io.swagger.pojo.dao.repos", "io.swagger.pojo.dao", "io.swagger.pojo.dto", "io.swagger.pojo", "io.swagger", "io.swagger.api", "io.swagger.configuration"})
public class TikuApplication {

    public static void main(String[] args) {
        SpringApplication.run(TikuApplication.class, args);
    }

}
