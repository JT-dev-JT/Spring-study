package com.spring.demo.service;

import com.spring.demo.entity.Product;
import com.spring.demo.exception.NotFoundException;
import com.spring.demo.exception.UnprocessableEntityException;
import com.spring.demo.repository.MockProductDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductService {
    @Autowired
    MockProductDAO productDAO;

    public Product createProduct (Product request){
        boolean isIdDuplicated = productDAO.find(request.getId());
        if(isIdDuplicated){
            throw new UnprocessableEntityException("The id Of the Product is duplicated");
        }

        Product product = new Product();
        product.setId(request.getId());
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        return productDAO.insert(product);
    }

    public Product getProduct(String id){
        return productDAO.find(id).orElseThrow(()->new NotFoundException("Cannot find product"));
    }
}
