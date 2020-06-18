package com.java.study.domain.posts;

import com.java.study.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {
    private Long id;
    private PostsType postsType;
    private String title;
    private User user;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity){
        this.id = entity.getId();
        this.postsType = entity.getPostsType();
        this.title = entity.getTitle();
        this.user =entity.getUser();
        this.modifiedDate = entity.getModifiedDate();
    }

}
