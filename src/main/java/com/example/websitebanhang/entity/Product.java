package com.example.websitebanhang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Product extends Base{
    private String name;
    private String price;
    private String description;
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalogType_id")
    private CatalogType catalogTypes;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductSize> productSizes;


//    private Long size;
//    private String color;
//    private Long discount;
}
