package com.epages.interview;

import com.epages.interview.model.InterviewApplicationMapper;
import com.epages.interview.model.Product;
import com.epages.interview.model.ProductDto;
import com.epages.interview.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InterviewApplicationTests {

    @Autowired
    ProductService productService;

    @Test
    public void contextLoads() {
        List<Product> productList = productService.fetchProductList();
        assertNotNull(productList);
        assertTrue(productList.size() > 0);
    }

    @Test
    public void sortingTest() {
        List<Product> products = new ArrayList<>();
        products.add(Product.builder().id(1L).name("Product A").brand("Brand C").price(100.99).onSale(Boolean.TRUE).build());
        products.add(Product.builder().id(2L).name("Product B").brand("Brand A").price(19.99).onSale(Boolean.FALSE).build());
        products.add(Product.builder().id(3L).name("Product C").brand("Brand A").price(10.99).onSale(Boolean.FALSE).build());
        Map<String, List<ProductDto>> productsSorted = InterviewApplicationMapper.sortProductList(products);
        Map.Entry<String, List<ProductDto>> firstBrand = productsSorted.entrySet().stream().findFirst().orElse(null);
        assertEquals("Brand A", firstBrand.getKey());
        assertEquals(2, firstBrand.getValue().size());
        assertEquals("Product C", firstBrand.getValue().get(0).getName());
        assertEquals("Product B", firstBrand.getValue().get(1).getName());
    }
}
