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
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("sizeId")
    @JoinColumn(name = "size_id")
    private Size size;
}
