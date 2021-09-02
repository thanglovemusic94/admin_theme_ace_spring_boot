package com.example.websitebanhang.controller.admin;

import com.example.websitebanhang.dto.ProductDto;
import com.example.websitebanhang.entity.Product;
import com.example.websitebanhang.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class ProductController {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductController(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @DeleteMapping("/product")
    public ResponseEntity<?> delete(Long id) {
        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getProductDetail")
    public ResponseEntity<Product> getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("No product by id = + " + id));
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PatchMapping("/product/{id}")
    public ResponseEntity<?> getProduct(ProductDto productDto, @PathVariable Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("No product by id = + " + id));
        modelMapper.map(productDto, product);
        productRepository.save(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


    @GetMapping("/get-product")
    public ResponseEntity<Page<Product>> index(Pageable pageable) {
        Page<Product> pageableProducts = productRepository.findAll(pageable);
        return new ResponseEntity<>(pageableProducts, HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createFaq(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        productRepository.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
