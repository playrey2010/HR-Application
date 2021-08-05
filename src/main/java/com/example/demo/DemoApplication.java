package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Create a new Spring Boot application with Spring Security using database-based authentication (4.04).
//
//        Each Employee can work in only one Department and each Department can have many Employees.
//
//        Any one can view a list of all departments but must be logged in to view a list of employees in each department and view detail of each employee.
//
//        Only admin can add/update departments and employees. Employee can not be deleted but can be marked as "no longer employed".
//
//        Create a user with id = "admin" and password = "admin" as an admin.
//
//        Use thymeleaf's fragments, navbar, bootstrap, etc...


@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
