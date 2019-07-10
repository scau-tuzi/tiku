package cn.czfshine.tiku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableJpaRepositories("cn.czfshine.tiku.pojo")
public class TikuApplication {

    public static void main(String[] args) {
        SpringApplication.run(TikuApplication.class, args);
    }

}
