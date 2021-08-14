package com.example.websitebanhang.repository;

import com.example.websitebanhang.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
