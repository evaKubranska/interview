package com.epages.interview.model;

import com.epages.interview.model.Product;
import com.epages.interview.model.ProductDto;

import java.util.*;
import java.util.stream.Collectors;

public class InterviewApplicationMapper {

    public static Map<String, List<ProductDto>> sortProductList (List<Product> products) {
        Map<String, List<ProductDto>>  productMap = products.stream()
                        .collect(Collectors.groupingBy(Product::getBrand,
                        Collectors.mapping(
                                InterviewApplicationMapper::mapToProductDto, Collectors.toList())));

        return productMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> e.getValue().stream().sorted(
                                        Comparator.comparingDouble(ProductDto::getPrice))
                                .collect(Collectors.toList()),
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
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
