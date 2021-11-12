package com.spring.demo.controller;

import com.spring.demo.entity.product.Product;
import com.spring.demo.entity.product.ProductRequest;
import com.spring.demo.entity.product.ProductResponse;
import com.spring.demo.parameter.ProductQueryParameter;
import com.spring.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.*;


@RestController
@RequestMapping(value="/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

@Autowired
private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") String id) {
        Product product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }
/*
    @GetMapping
    public ResponseEntity<List<Product>>getProducts(
            @RequestParam(value="keyword", defaultValue ="")String name){
        List<Product> productList = productDB.stream()
                .filter(p-> p.getName().toUpperCase().contains(name.toUpperCase()))
                .collect(Collectors.toList());
        return  ResponseEntity.ok().body(productList);
    }
*/


    @GetMapping
    public ResponseEntity<List<ProductResponse>>getProducts(@ModelAttribute ProductQueryParameter param){
            List<ProductResponse> productList = productService.getProducts(param);
            return ResponseEntity.ok().body(productList);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request) {

        ProductResponse product = productService.createProduct(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(location).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> replaceProduct(
            @PathVariable("id") String id, @Valid @RequestBody ProductRequest  request){
            ProductResponse product = productService.replaceProduct(id,request);
            return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id")String id){
         productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }
}