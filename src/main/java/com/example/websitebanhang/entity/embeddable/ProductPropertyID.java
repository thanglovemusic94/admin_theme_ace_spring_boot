package com.example.websitebanhang.entity.embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPropertyID implements Serializable {

    @Column(name = "product_id")
    private int productId;

    @Column(name = "property_id")
    private int propertyId;

}
