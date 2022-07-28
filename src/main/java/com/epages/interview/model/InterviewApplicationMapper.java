package com.epages.interview.model;

import com.epages.interview.model.Product;
import com.epages.interview.model.ProductDto;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class InterviewApplicationMapper {

    public static Map<String, List<ProductDto>> sortProductList (List<Product> products) {
        Map<String, List<ProductDto>>  productMap = products.stream()
                        .collect(Collectors.groupingBy(Product::getBrand,
                        Collectors.mapping(p-> mapToProductDto(p), Collectors.toList())));

        Map<String, List<ProductDto>> sortedMap = new TreeMap<>();

        sortedMap.putAll(productMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> e.getValue().stream().sorted(
                                Comparator.comparingDouble(value -> value.getPrice()))
                                .collect(Collectors.toList()))));
        return sortedMap;
    }

    public static ProductDto mapToProductDto(Product product) {
        if(product == null) {
            return null;
        }
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .event(product.isOnSale() ? "ON SALE" : null)
                .build();
    }
}
