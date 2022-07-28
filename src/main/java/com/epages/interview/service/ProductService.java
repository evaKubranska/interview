package com.epages.interview.service;

import com.epages.interview.InterviewApplicationConfig;
import com.epages.interview.model.InterviewApplicationMapper;
import com.epages.interview.model.Product;
import com.epages.interview.model.ProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ProductService {
    private final InterviewApplicationConfig config;
    private final RestTemplate restTemplate;

    public ProductService(RestTemplateBuilder restTemplateBuilder, InterviewApplicationConfig config) {
        this.restTemplate = restTemplateBuilder.build();
        this.config = config;
    }

    private ResponseEntity<Product[]> fetchProduct() {
        return restTemplate.getForEntity(config.getAllProductsUrl(), Product[].class);
    }

    public List<Product> fetchProductList () {
        return Arrays.asList(fetchProduct().getBody());
    }

    public  Map<String, List<ProductDto>> getSortedProducts() {
        return InterviewApplicationMapper.sortProductList(fetchProductList());
//        Map<String, List<Object>> sortedProductList = products.stream()
//                .collect(Collectors.groupingBy(Product::getBrand,
//                        Collectors.mapping(p-> mapToDto(p), Collectors.toList())));
//        Stream<Map.Entry<String, List<ProductDto>>> pm = products.stream()
//                .collect(Collectors.collectingAndThen(Collectors.groupingBy(Product::getBrand,
//                                Collectors.mapping(p -> mapToDto(p), Collectors.toList())),
//                        p -> p.entrySet().stream().sorted(Map.Entry.comparingByKey())));

//        Map<String, List<ProductDto>>  productMap = products.stream()
//                .collect(Collectors.groupingBy(Product::getBrand,
//                        Collectors.mapping(p-> mapToDto(p), Collectors.toList())));
//        Map<String, List<ProductDto>> sortedMap = productMap.entrySet().stream()
//                .collect(Collectors.toMap(e-> e.getKey(),
//                        e -> e.getValue().stream().sorted(
//                                Comparator.comparingDouble(value -> value.getPrice())).collect(Collectors.toList()));s
//                Map<String, List<ProductDto>> sortedMap = new TreeMap<>();
//        sortedMap.putAll(productMap.entrySet().stream()
//                .collect(Collectors.toMap(Map.Entry::getKey,
//                        e -> e.getValue().stream().sorted(
//                                Comparator.comparingDouble(value -> value.getPrice())).collect(Collectors.toList()))));
//        return sortedMap;
    }

}
