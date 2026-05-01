package com.example.seven.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {

        http
                .csrf(csrf -> csrf.disable());

        // 로그인 필터 활성화
        http
                .formLogin(login -> login
                        .loginProcessingUrl("/login")
                        .loginPage("/login"));

        // 인가 필터 설정
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/join").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/user").hasAnyRole("USER","ADMIN")
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().denyAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
