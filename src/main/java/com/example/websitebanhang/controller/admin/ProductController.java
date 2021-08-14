package com.example.websitebanhang.controller.admin;

import com.example.websitebanhang.dto.ProductDto;
import com.example.websitebanhang.entity.Product;
import com.example.websitebanhang.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductController(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        productRepository.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
