package com.example.journalApp.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HealthCheckController {

    @GetMapping("/health-check")
    public String healthCheck() {
        return "Ok";
    }
}
