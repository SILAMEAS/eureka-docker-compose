package com.test.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping
    public ResponseEntity<String> getAll() {
        return ResponseEntity.ok("Products work!");
    }
    @GetMapping("/jkl")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}