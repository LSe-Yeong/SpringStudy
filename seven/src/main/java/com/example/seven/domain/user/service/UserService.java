package com.example.seven.domain.user.service;

import com.example.seven.domain.user.dto.UserRequestDto;
import com.example.seven.domain.user.entity.UserEntity;
import com.example.seven.domain.user.entity.UserRole;
import com.example.seven.domain.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void join(UserRequestDto dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();

        UserEntity entity = new UserEntity();
        entity.setUsername(username);
        entity.setPassword(passwordEncoder.encode(password));
        entity.setRole(UserRole.USER);

        userRepository.save(entity);
    }
}
