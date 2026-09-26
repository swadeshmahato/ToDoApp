package com.todoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

        System.out.println();
        System.out.println("==============================================");
        System.out.println("        TASKFLOW APPLICATION STARTED");
        System.out.println("==============================================");
        System.out.println("Website : http://localhost:8080/");
        System.out.println("API     : http://localhost:8080/api/tasks");
        System.out.println("==============================================");
        System.out.println();
    }
}