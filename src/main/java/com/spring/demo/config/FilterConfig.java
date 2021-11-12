package com.spring.demo.config;

import com.spring.demo.filter.LogApiFilter;
import com.spring.demo.filter.LogProcessTimeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean logProcessTimeFilter(){
        FilterRegistrationBean<LogProcessTimeFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new LogProcessTimeFilter());
        bean.addUrlPatterns("/*");
        bean.setName("logProcessTimeFilter");
        bean.setOrder(0);
        return bean;
    }

    @Bean
    public  FilterRegistrationBean logApiFilter(){
        FilterRegistrationBean<LogApiFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new LogApiFilter());
        bean.addUrlPatterns("/*");
        bean.setOrder(0);
        return bean;
    }
}
