package com.spring.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.PrimitiveIterator;
@Getter
@Setter
@ToString
@Document(collection  = "products")
public class Product {
    private String id;
    private String name;
    private int price;

    public Product() {
    }

    public Product(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

}
