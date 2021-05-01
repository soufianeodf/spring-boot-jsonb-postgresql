package io.github.soufianeodf.jsonbpostgresql.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Configuration
@Profile(value = {"test", "dev"})
public class AppConfig {

    @PostConstruct
    void print() {
        System.err.println("hello from config");
    }
}
