package com.example.websitebanhang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@SuperBuilder
public  class Product extends AbstractClass {

    private String name;
    private int price;
    private Long discount;
    private String description;
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalogType_id")
    private CatalogType catalogTypes;
    
    @OneToMany(mappedBy = "product")
    Set<ProductProperty> products;
}
