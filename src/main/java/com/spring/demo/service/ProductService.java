package com.spring.demo.service;

import com.spring.demo.entity.Product;
import com.spring.demo.exception.NotFoundException;
import com.spring.demo.exception.UnprocessableEntityException;
import com.spring.demo.parameter.ProductQueryParameter;
import com.spring.demo.repository.MockProductDAO;
import com.spring.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Product createProduct(Product request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        return repository.insert(product);
    }

    public Product getProduct(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Can't find product."));
    }

    public Product replaceProduct(String id, Product request) {
        Product oldproduct = getProduct(id);
        Product product = new Product();
        product.setId(oldproduct.getId());
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        return repository.save(product);
    }

    public void deleteProduct(String id) {
        repository.deleteById(id);
    }

    public Sort genSortingStrategy(String orderBy, String sortRule){
        Sort sort = Sort.unsorted();
        if (Objects.nonNull(orderBy)&& Objects.nonNull(sortRule)){
            Sort.Direction direction=Sort.Direction.fromString(sortRule);
            sort=Sort.by(direction,orderBy);
        }
        return sort;
    }


    public List<Product> getProducts (ProductQueryParameter param){
        String keyword = Optional.ofNullable(param.getKeyword()).orElse("");
        int priceFrom = Optional.ofNullable(param.getPriceFrom()).orElse(0);
        int priceTo = Optional.ofNullable(param.getPriceTo()).orElse(Integer.MAX_VALUE);

        Sort sort =genSortingStrategy(param.getOrderBy(), param.getSortRule());

        return repository.findByPriceBetweenAndNameLikeIgnoreCase(priceFrom, priceTo, keyword, sort);
    }
}