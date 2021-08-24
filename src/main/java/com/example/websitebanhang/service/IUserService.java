package com.example.websitebanhang.service;

import com.example.websitebanhang.dto.user.JwtResponse;
import com.example.websitebanhang.dto.user.LoginDTO;
import com.example.websitebanhang.dto.user.SignupRequest;
import com.example.websitebanhang.entity.User;
import com.example.websitebanhang.entity.VerificationToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface IUserService extends UserDetailsService{

    JwtResponse login(LoginDTO loginDTO) throws JsonProcessingException;

    User registerNewUserAccount(SignupRequest request) throws Exception;
            //throws UserAlreadyExistException;

    void saveRegisteredUser(User user);

    User getUser(String verificationToken);

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);
}
