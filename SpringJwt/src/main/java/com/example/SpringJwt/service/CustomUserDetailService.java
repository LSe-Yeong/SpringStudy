package com.example.SpringJwt.service;

import com.example.SpringJwt.dto.CustomUserDetails;
import com.example.SpringJwt.entity.UserEntity;
import com.example.SpringJwt.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUsername(username);

        if (user != null) {
            return new CustomUserDetails(user);
        }

        return null;
    }
}
