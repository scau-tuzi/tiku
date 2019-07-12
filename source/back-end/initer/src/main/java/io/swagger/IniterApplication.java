package io.swagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"io.swagger.pojo.dao.repos", "io.swagger.pojo.dao", "io.swagger.pojo.dto", "io.swagger.pojo", "io.swagger", "io.swagger.api", "io.swagger.configuration"})
public class IniterApplication {

    public static void main(String[] args) {
        SpringApplication.run(IniterApplication.class, args);
    }

}
