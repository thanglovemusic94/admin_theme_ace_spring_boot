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
public class Size extends Base{
    private String size;

    @OneToMany(mappedBy = "size", fetch = FetchType.LAZY)
    private List<ProductSize> productSizeList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "size_color", joinColumns = @JoinColumn(name = "size_id"), inverseJoinColumns = @JoinColumn(name = "color_id"))
    private List<Color> colorList;
}
