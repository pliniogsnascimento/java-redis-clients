package com.test.java_redis_demo.controllers;

import com.test.java_redis_demo.entities.Product;
import com.test.java_redis_demo.services.ProductsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/v1/products")
@Log4j2
public class ProductsController {

    @Autowired
    private ProductsService service;

    @PostMapping
    public ResponseEntity<Product> insertProduct(@RequestBody Product product) {
        var insertedProduct = this.service.insertProduct(product);
        log.info("New product inserted");
        return ResponseEntity.status(201).body(insertedProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam("pagenumber")Optional<Integer> pageNumber,
            @RequestParam("trending")Optional<Boolean> trending) {
        log.info("Getting products");
        if (trending.isPresent())
            return ResponseEntity.ok(this.service.getTrendingProducts());

        return ResponseEntity.ok(this.service.getProducts(pageNumber.orElse(0)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        log.info("Getting product by id");
        var product = service.getProductById(id);

        return product.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
