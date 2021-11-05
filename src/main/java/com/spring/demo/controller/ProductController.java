package com.spring.demo.controller;

import com.spring.demo.entity.Product;
import com.spring.demo.parameter.ProductQueryParameter;
import com.spring.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<Product>>getProducts(@ModelAttribute ProductQueryParameter param){
            List<Product> productList = productService.getProducts(param);
            return ResponseEntity.ok().body(productList);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product request) {

        Product product = productService.createProduct(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(location).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(
            @PathVariable("id") String id, @RequestBody Product request
    ){
            Product product = productService.replaceProduct(id,request);
            return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id")String id){
         productService.deleteProduct(id);

        return ResponseEntity.notFound().build();
    }
}