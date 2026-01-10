package com.example.springtdd.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.springtdd.domain.post.dto.PostRequestDto;
import com.example.springtdd.domain.post.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = PostController.class)
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    PostService postService;

    @Test
    void post_method_테스트1() throws Exception {

        // given
        PostRequestDto requestDTO = new PostRequestDto();
        requestDTO.setTitle("제목");
        requestDTO.setContent("내용");

        // when & then
        mockMvc.perform(post("/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void post_method_테스트2() throws Exception {

        // given
        PostRequestDto requestDTO = new PostRequestDto();
        requestDTO.setTitle("제목");
        requestDTO.setContent("내용");

        given(postService.create(any(PostRequestDto.class))).willReturn(1L);

        // when & then
        mockMvc.perform(post("/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));

        verify(postService).create(any(PostRequestDto.class));
    }

    @Test
    void post_method_테스트3() throws Exception {

        // given
        PostRequestDto requestDTO = new PostRequestDto();
        requestDTO.setTitle("");
        requestDTO.setContent("내용");

        given(postService.create(any(PostRequestDto.class))).willThrow(new IllegalArgumentException());

        // when & then
        mockMvc.perform(post("/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }
}