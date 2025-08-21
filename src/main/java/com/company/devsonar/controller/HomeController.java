package com.company.devsonar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    // Root endpoint
    @GetMapping("/")
    public String home() {
        return "DevSonarProject is running!";
    }

    // Coverage placeholder
    @GetMapping("/coverage")
    public String coverage() {
        return "Coverage report placeholder - real HTML will be served later";
    }

    // API endpoint for /api/home
    @GetMapping("/api/home")
    public String apiHome() {
        return "API Home endpoint is working!";
    }
}
