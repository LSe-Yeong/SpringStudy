package com.example.seven.domain.user.repository;

import com.example.seven.domain.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
