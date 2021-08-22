package com.example.websitebanhang.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(schema = "tbl")
public class Role extends Base {
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;

}