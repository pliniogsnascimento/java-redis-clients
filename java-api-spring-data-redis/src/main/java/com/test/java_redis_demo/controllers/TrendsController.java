package com.test.java_redis_demo.controllers;

import com.test.java_redis_demo.entities.Product;
import com.test.java_redis_demo.entities.Trend;
import com.test.java_redis_demo.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/trends")
public class TrendsController {

    @Autowired
    private ProductsService service;

    @PostMapping
    public ResponseEntity<Trend> postTrend(@RequestBody List<Product> products) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveTrend(products));
    }
}
