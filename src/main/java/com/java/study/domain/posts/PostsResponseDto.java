package com.java.study.domain.posts;

import com.java.study.domain.user.User;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private PostsType postsType;
    private String title;
    private String content;
    private User user;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.postsType = entity.getPostsType();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.user = entity.getUser();
    }
}
