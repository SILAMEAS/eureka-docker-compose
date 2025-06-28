package com.test.product;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {
    public List<Product> getAllProducts() {
        return Arrays.asList(
                new Product(1L, "Apple", 0.99),
                new Product(2L, "Banana", 0.49),
                new Product(3L, "Cherry", 2.99)
        );
    }
}