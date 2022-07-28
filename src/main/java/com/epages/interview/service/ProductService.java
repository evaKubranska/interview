package com.epages.interview.service;

import com.epages.interview.InterviewApplicationConfig;
import com.epages.interview.model.InterviewApplicationMapper;
import com.epages.interview.model.Product;
import com.epages.interview.model.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final InterviewApplicationConfig config;
    private final RestTemplate restTemplate;

    private ResponseEntity<Product[]> fetchProduct() {
        return restTemplate.getForEntity(config.getAllProductsUrl(), Product[].class);
    }

    public List<Product> fetchProductList () {
        return Arrays.asList(fetchProduct().getBody());
    }

    public  Map<String, List<ProductDto>> getSortedProducts() {
        return InterviewApplicationMapper.sortProductList(fetchProductList());
    }

}
