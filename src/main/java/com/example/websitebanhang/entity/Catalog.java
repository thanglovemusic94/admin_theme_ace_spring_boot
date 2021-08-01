package com.example.websitebanhang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Catalog extends Base{
    private String catalogName;
    private String catalogCode;
    private String catalogImage;

    @OneToMany(mappedBy = "catalogList", fetch = FetchType.LAZY)
    private List<CatalogType> catalogTypeList;
}