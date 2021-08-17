package com.example.websitebanhang.search;

import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;

import java.util.List;

@Data
public class ProductSearch {
    private String name;
    private int price;

}


