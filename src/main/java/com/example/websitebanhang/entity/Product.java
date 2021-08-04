package com.example.websitebanhang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Product extends AbstractClass {

    private String name;
    private int price;
    private Long discount;
    private String description;
    private String code;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    @OneToMany(mappedBy = "product")
    Set<ProductProperty> products;
}
