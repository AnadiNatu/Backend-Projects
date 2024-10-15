package com.web.jwt_spring_security.controller;


import com.web.jwt_spring_security.dtos.HelloResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public HelloResponse hello(){

        return new HelloResponse("Hello from JWT Authorization");
    }
}
