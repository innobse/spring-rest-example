package ru.bse71.learnup.spring.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;

@SpringBootApplication
public class SpringRestExampleApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(SpringRestExampleApplication.class, args);
        System.out.println(ctx.getBean(DataSource.class));
    }

}
