package com.spring.demo.service;

import com.spring.demo.converter.ProductConverter;
import com.spring.demo.entity.product.Product;
import com.spring.demo.entity.product.ProductRequest;
import com.spring.demo.entity.product.ProductResponse;
import com.spring.demo.exception.NotFoundException;
import com.spring.demo.parameter.ProductQueryParameter;
import com.spring.demo.repository.ProductRepository;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductService {

    private ProductRepository repository;

    public ProductService(ProductRepository repository ) {
        this.repository=repository;
    }

    public ProductResponse createProduct(ProductRequest  request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product= repository.insert(product);

        return  ProductConverter.toProductResponse(product);
    }

    public Product getProduct(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Can't find product."));
    }

    public ProductResponse getProductResponse(String id){
        Product product=getProduct(id);
        return ProductConverter.toProductResponse(product);

    }

    public ProductResponse replaceProduct(String id, ProductRequest request) {
        Product oldProduct = getProduct(id);
        Product product = new Product();
        product.setId(oldProduct.getId());
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product= repository.save(product);

        return ProductConverter.toProductResponse(product);
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


    public List<ProductResponse> getProducts (ProductQueryParameter param){
        String keyword = Optional.ofNullable(param.getKeyword()).orElse("");
        int priceFrom = Optional.ofNullable(param.getPriceFrom()).orElse(0);
        int priceTo = Optional.ofNullable(param.getPriceTo()).orElse(Integer.MAX_VALUE);

        Sort sort =genSortingStrategy(param.getOrderBy(), param.getSortRule());

        List<Product> productList= repository.findByPriceBetweenAndNameLikeIgnoreCase(priceFrom, priceTo, keyword, sort);

        return  productList.stream().map(ProductConverter::toProductResponse).collect(Collectors.toList());
    }
}