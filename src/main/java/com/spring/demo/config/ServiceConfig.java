package com.spring.demo.config;

import com.spring.demo.entity.Product;
import com.spring.demo.repository.ProductRepository;
import com.spring.demo.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public ProductService productService(ProductRepository repository){
        return new ProductService(repository);
    }
}
