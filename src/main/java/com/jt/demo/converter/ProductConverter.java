package com.jt.demo.converter;

import com.jt.demo.entity.product.Product;
import com.jt.demo.entity.product.ProductRequest;
import com.jt.demo.entity.product.ProductResponse;

public class ProductConverter {

    private ProductConverter() {

    }

    public static ProductResponse toProductResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());

        return response;
    }

    public static Product toProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());

        return product;
    }
}
