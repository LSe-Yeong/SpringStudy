package com.example.springtdd.domain.post.service;

import com.example.springtdd.domain.post.dto.PostRequestDto;
import com.example.springtdd.domain.post.entity.PostEntity;
import com.example.springtdd.domain.post.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Long create(PostRequestDto postRequestDto) {

        if (postRequestDto.getTitle().isBlank()) {
            throw new IllegalArgumentException("빈값 금지");
        }

        PostEntity post = new PostEntity();
        post.setTitle(postRequestDto.getTitle());
        post.setContent(postRequestDto.getContent());

        return postRepository.save(post).getId();
    }

}
