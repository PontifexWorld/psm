package com.example;

import com.thebeastshop.forest.springboot.annotation.ForestScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@ForestScan(basePackages = "com.example.forest.controller")
public class PsmApplication {

    public static void main(String[] args) {
        SpringApplication.run(PsmApplication.class, args);
    }

}
