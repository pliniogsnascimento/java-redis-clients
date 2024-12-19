package com.test.java_redis_demo.services;

import com.test.java_redis_demo.entities.Product;
import com.test.java_redis_demo.entities.Trend;
import com.test.java_redis_demo.repositories.ProductRepository;
import com.test.java_redis_demo.repositories.TrendRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
public class ProductsService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TrendRepository trendRepository;

    public Product insertProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getProducts(int pageNumber) {
        PageRequest page = PageRequest.of(pageNumber, 10);
        return productRepository.findAll(page).getContent();
    }

    public List<Product> getTrendingProducts() {
        try {
            return trendRepository.findTopByOrderByIdDesc().getProducts();
        } catch (Exception e) {
            log.error(e.getMessage());
            return this.getProducts(0);
        }
    }

    public Trend saveTrend(List<Product> products) {
        return trendRepository.save(new Trend(products));
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findById(UUID.fromString(id));
    }
}
