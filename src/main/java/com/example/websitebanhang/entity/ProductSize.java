package com.example.websitebanhang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class ProductSize implements Serializable {
    private long quantity;
    @EmbeddedId
    private ProductSizeKey id;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("size_id")
    @JoinColumn(name = "size_id")
    private Size size;
}
