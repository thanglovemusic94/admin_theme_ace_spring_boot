package com.example.websitebanhang.service;

import com.example.websitebanhang.entity.Product;
import com.example.websitebanhang.search.ProductSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {
    Page<Product> findAll(ProductSearch search, Pageable pageable);
}
