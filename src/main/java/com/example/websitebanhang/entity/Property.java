package com.example.websitebanhang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Property extends AbstractClass{


    private String name; // color or size

    @Column(unique = true)
    private String value; // value color or size

    @OneToMany(mappedBy = "property")
    Set<ProductProperty> property;


    /**
     *  -> snecker Jelly1 -> size : 35 color: xanh
     *  -> snecker Jelly1 -> color : vàng
     *  -> snecker Jelly2
     */
}
