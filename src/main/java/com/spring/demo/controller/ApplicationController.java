package com.spring.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {
    @GetMapping("/")
    public String greetings(HttpServletRequest request) {
        return "Greeting from spring security! with sessionId: " + request.getSession().getId();
    }
}
