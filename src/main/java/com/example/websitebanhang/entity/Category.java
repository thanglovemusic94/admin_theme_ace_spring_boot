package com.example.websitebanhang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category extends AbstractClass{

    private String code;

    private String name;

    private String image;

    private String gender;

    @OneToMany(mappedBy = "category")
    private List<Product> product;

    @ManyToOne
    @JoinColumn(name = "parent_id", columnDefinition = "integer default 0")
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Category> childrenCategory;

}
