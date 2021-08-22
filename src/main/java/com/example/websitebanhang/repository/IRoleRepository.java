package com.example.websitebanhang.repository;

import com.example.websitebanhang.entity.ERole;
import com.example.websitebanhang.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
