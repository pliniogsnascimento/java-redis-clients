package com.test.java_redis_demo.repositories;

import com.test.java_redis_demo.entities.Trend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrendRepository extends JpaRepository<Trend, Long> {
    Trend findTopByOrderByIdDesc();
}
