package com.example.websitebanhang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogType extends Base{
    private String code;
    private String name;
    private String image;
    private String gender;

    @OneToMany(mappedBy = "catalogTypes", fetch = FetchType.LAZY)
    private List<Product> product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catalog_id")
    private Catalog catalogList;
}
