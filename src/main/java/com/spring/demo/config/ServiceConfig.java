package com.spring.demo.config;

import com.spring.demo.entity.Product;
import com.spring.demo.repository.ProductRepository;
import com.spring.demo.service.MailService;
import com.spring.demo.service.ProductService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ServiceConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ProductService productService(ProductRepository repository, MailService mailService){
        System.out.println("Product Service is created");
        return new ProductService(repository);
    }


}
