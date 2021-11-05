package com.spring.demo.parameter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductQueryParameter {
    private String keyword;
    private String orderBy;
    private String sortRule;
    private Integer priceFrom;
    private Integer priceTo;
}
