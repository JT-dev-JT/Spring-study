package com.spring.demo.config;

import com.spring.demo.repository.AppUserRepository;
import com.spring.demo.repository.ProductRepository;
import com.spring.demo.service.AppUserService;
import com.spring.demo.service.MailService;
import com.spring.demo.service.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public ProductService productService(ProductRepository repository, MailService mailService){
        System.out.println("Product Service is created");
        return new ProductService(repository);
    }

    @Bean
    public AppUserService appUserService(AppUserRepository repository){
        return new AppUserService(repository);
    }


}
