package com.spring.demo.service;

import com.spring.demo.entity.Product;
import com.spring.demo.exception.NotFoundException;
import com.spring.demo.exception.UnprocessableEntityException;
import com.spring.demo.parameter.ProductQueryParameter;
import com.spring.demo.repository.MockProductDAO;
import com.spring.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Product createProduct (Product request){
        boolean isIdDuplicated = repository.findById(request.getId()).isPresent();

        if(isIdDuplicated){
            throw new UnprocessableEntityException("The id Of the Product is duplicated");
        }

        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        return repository.insert(product);
    }

    public Product getProduct(String id){
        return repository.findById(id).orElseThrow(()->new NotFoundException("Can't find product."));
    }

    public Product replaceProduct(String id , Product request){
        Product product = getProduct(id);
        return productDAO.replace(product.getId(),request);
    }

    public void deleteProduct(String id){
        Product product =getProduct(id);
        productDAO.delete(product.getId());
    }

    public List<Product> getPorduct(ProductQueryParameter param){
        return productDAO.find(param);
    }
}
