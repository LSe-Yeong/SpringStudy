package com.example.springtdd.api;

import com.example.springtdd.domain.post.dto.PostRequestDto;
import com.example.springtdd.domain.post.service.PostService;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post")
    public ResponseEntity<?> postMethod(
            @RequestBody PostRequestDto dto
            ) {

        Long resultId = postService.create(dto);

        Map<String, Object> responseBody = Map.of("id",resultId);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application","json"));

        return new ResponseEntity<>(responseBody, httpHeaders, HttpStatus.OK);
    }
}
