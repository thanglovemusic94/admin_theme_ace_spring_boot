package com.example.websitebanhang.repository;

import com.example.websitebanhang.entity.User;
import com.example.websitebanhang.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}
