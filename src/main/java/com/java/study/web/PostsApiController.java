package com.java.study.web;

import com.java.study.config.auth.LoginUser;
import com.java.study.config.auth.dto.SessionUser;
import com.java.study.domain.posts.PostsResponseDto;
import com.java.study.domain.posts.PostsUpdateRequestDto;
import com.java.study.domain.user.User;
import com.java.study.service.posts.PostsService;
import com.java.study.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto, @LoginUser SessionUser sessionUser){
        User user = (User)httpSession.getAttribute("user");
        requestDto.saveUser(user);
        return postsService.save(requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable("id") Long id){
        return postsService.findById(id);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable("id") Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable("id") Long id){
        postsService.delete(id);
        return id;
    }

}
