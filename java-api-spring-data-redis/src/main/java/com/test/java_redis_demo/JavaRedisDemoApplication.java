package com.test.java_redis_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories
public class JavaRedisDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaRedisDemoApplication.class, args);
	}

}
