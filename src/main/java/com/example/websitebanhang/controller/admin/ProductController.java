package com.example.websitebanhang.controller.admin;

import com.example.websitebanhang.dto.ProductDto;
import com.example.websitebanhang.entity.Product;
import com.example.websitebanhang.search.ProductSearch;
import com.example.websitebanhang.service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final IProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(IProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Get list product")
    @GetMapping("/product")
    public Page<Product> index(ProductSearch search,
                               @PageableDefault Pageable pageable) {
        return productService.findAll(search, pageable);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
