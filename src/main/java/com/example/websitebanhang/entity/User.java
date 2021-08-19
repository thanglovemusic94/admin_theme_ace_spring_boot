package com.example.websitebanhang.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(schema = "tbl")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


}
