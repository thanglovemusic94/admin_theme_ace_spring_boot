package com.example.websitebanhang.service.impl;


import com.example.websitebanhang.dto.user.JwtResponse;
import com.example.websitebanhang.dto.user.LoginDTO;
import com.example.websitebanhang.dto.user.SignupRequest;
import com.example.websitebanhang.entity.*;
import com.example.websitebanhang.repository.IRoleRepository;
import com.example.websitebanhang.repository.IUserRepository;
import com.example.websitebanhang.repository.IVerificationTokenRepository;
import com.example.websitebanhang.security.JwtUtils;
import com.example.websitebanhang.service.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserService implements IUserService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final IUserRepository userRepository;

    private final PasswordEncoder encoder;

    private final IRoleRepository roleRepository;

    private final IVerificationTokenRepository tokenRepository;

    public UserService(AuthenticationManager authenticationManager,
                       JwtUtils jwtUtils, IUserRepository userRepository,
                       PasswordEncoder encoder, IRoleRepository roleRepository,
                       IVerificationTokenRepository tokenRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }


    @Override
    public JwtResponse login(LoginDTO loginDTO) throws JsonProcessingException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

        //SecurityContextHolder.getContext().getAuthentication().getDetails();
        String jwt = jwtUtils.generateJwtToken(authentication);
        return new JwtResponse(jwt);
    }


    @Override
    public User registerNewUserAccount(SignupRequest signUpRequest) throws Exception {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new Exception("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new Exception("Error: Email is already in use! : " + signUpRequest.getEmail());
        }

        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName(ERole.ROLE_USER);
                    return role;
                });

        roles.add(userRole);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Override
    public void saveRegisteredUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUser(String verificationToken) {
        User user = tokenRepository.findByToken(verificationToken).getUser();
        return user;
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }
}
