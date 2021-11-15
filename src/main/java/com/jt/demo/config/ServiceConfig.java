package com.jt.demo.config;

import com.jt.demo.repository.AppUserRepository;
import com.jt.demo.repository.ProductRepository;
import com.jt.demo.service.AppUserService;
import com.jt.demo.service.MailService;
import com.jt.demo.service.ProductService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ServiceConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ProductService productService(ProductRepository repository,
                                         MailService mailService) {
        return new ProductService(repository, mailService);
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AppUserService appUserService(AppUserRepository repository) {
        return new AppUserService(repository);
    }

}
