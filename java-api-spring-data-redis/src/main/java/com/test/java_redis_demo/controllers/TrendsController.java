package com.test.java_redis_demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrendsController {

    @GetMapping("/health")
    String health() {
        return "Healthy";
    }
}
