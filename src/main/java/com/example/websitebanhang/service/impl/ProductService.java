package com.example.websitebanhang.service.impl;

import com.example.websitebanhang.entity.Product;
import com.example.websitebanhang.repository.ProductRepository;
import com.example.websitebanhang.search.ProductSearch;
import com.example.websitebanhang.service.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

@Service
public class ProductService implements IProductService, HandlerInterceptor {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> findAll(ProductSearch search, Pageable pageable) {

//        return productRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
//
//            Predicate preName = criteriaBuilder.like(root.get("name"), "%" + search.getName() + "%");
//            Predicate prePrice = criteriaBuilder.equal(root.get("price"), "%" + search.getPrice() + "%");
//            return null;
//        }, pageable);
        return  productRepository.findAll(pageable);
    }


}
