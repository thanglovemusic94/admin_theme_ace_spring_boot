package com.example.websitebanhang.service.impl;

import com.example.websitebanhang.entity.User;
import com.example.websitebanhang.entity.UserDetailsImpl;
import com.example.websitebanhang.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class JwtUserservice implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s).orElseThrow(() -> {
            throw new UsernameNotFoundException("k tim thay");
        });


        return UserDetailsImpl.build(user);
    }
}
