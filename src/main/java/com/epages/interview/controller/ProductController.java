package com.epages.interview.controller;

import com.epages.interview.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/sort")
    public ResponseEntity getSortedProducts() {
       return ResponseEntity.ok().body(productService.getSortedProducts());
    }
}
