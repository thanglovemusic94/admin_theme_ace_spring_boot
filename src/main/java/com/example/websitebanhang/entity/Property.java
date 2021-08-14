package com.example.websitebanhang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String value; // value color or size

    @OneToMany(mappedBy = "property")
    Set<ProductProperty> property;
}
