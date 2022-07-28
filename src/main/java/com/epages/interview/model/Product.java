package com.epages.interview.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class Product {

    private Long id;
    private String name;
    private double price;
    private String brand;
    private boolean onSale;
}
