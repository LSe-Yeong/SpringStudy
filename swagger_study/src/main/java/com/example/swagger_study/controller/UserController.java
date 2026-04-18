package com.example.swagger_study.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User API" , description = "유저 관련 API")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @GetMapping("/{username}")
    public ResponseEntity<?> userGet() {
        return new ResponseEntity<>("hiUser",new HttpHeaders(), HttpStatus.OK);
    }

}
