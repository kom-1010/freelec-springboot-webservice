package com.sbweb.freelecspringbootwebservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbweb.freelecspringbootwebservice.domain.posts.Posts;
import com.sbweb.freelecspringbootwebservice.domain.posts.PostsRepository;
import com.sbweb.freelecspringbootwebservice.web.dto.PostResponseDto;
import com.sbweb.freelecspringbootwebservice.web.dto.PostsSaveRequestDto;
import com.sbweb.freelecspringbootwebservice.web.dto.PostsUpdateRequestDto;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @AfterEach
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void posts_save() throws Exception{
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title).content(content).author("author").build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

//        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
//
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    @WithMockUser(roles = "USER")
    public void posts_update() throws Exception{
        String title = "title1";
        String content = "content1";
        String author = "author1";

        Posts savePosts = postsRepository.save(Posts.builder().title(title).content(content).author(author).build());

        Long updated_postsId = savePosts.getId();
        String updated_title = "title2";
        String updated_content = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto
                .builder().title(updated_title).content(updated_content).build();

        String url = "http://localhost:" + port + "/api/v1/posts/"+updated_postsId;

        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

//        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);
//
//        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);
//
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(updated_title);
        assertThat(all.get(0).getContent()).isEqualTo(updated_content);
    }

//    @Test
//    public void posts_findById(){
//        String title = "title";
//        String content = "content";
//        String author = "author";
//        Posts savePosts = postsRepository.save(Posts.builder().title(title).content(content).author(author).build());
//
//        Long id = savePosts.getId();
//
//        PostResponseDto responseDto = new PostResponseDto(savePosts);
//        String url = "http://localhost:" + port + "/api/v1/posts/"+id;
//
//        ResponseEntity<Long> responseEntity = restTemplate.getForEntity(url, Long.class, responseDto);
//
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//
//        List<Posts> all = postsRepository.findAll();
//        assertThat(all.get(0).getId()).isEqualTo(id);
//        assertThat(all.get(0).getTitle()).isEqualTo(title);
//        assertThat(all.get(0).getContent()).isEqualTo(content);
//        assertThat(all.get(0).getAuthor()).isEqualTo(author);
//    }
//
}
