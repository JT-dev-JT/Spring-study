package com.spring.demo.converter;

import com.spring.demo.entity.product.Product;
import com.spring.demo.entity.product.ProductRequest;
import com.spring.demo.entity.product.ProductResponse;

public class ProductConverter {
    private ProductConverter(){}

    public static Product toProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());

        return product;
    }

    public static ProductResponse toProductResponse (Product product){
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());

        return  response;
    }
}
