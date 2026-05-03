package com.example.SpringJwt.service;

import com.example.SpringJwt.dto.JoinDto;
import com.example.SpringJwt.entity.UserEntity;
import com.example.SpringJwt.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void joinProcess(JoinDto joinDto) {

        String username = joinDto.getUsername();
        String password = joinDto.getPassword();

        Boolean isExist = userRepository.existsByUsername(username);

        if (isExist) {
            return;
        }

        UserEntity entity = new UserEntity();
        entity.setUsername(username);
        entity.setPassword(bCryptPasswordEncoder.encode(password));
        entity.setRole("ROLE_ADMIN");

        userRepository.save(entity);
    }
}
