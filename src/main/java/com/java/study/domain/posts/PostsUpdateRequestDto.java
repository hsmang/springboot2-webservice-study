package com.java.study.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private Long id;
    private PostsType postsType;
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsUpdateRequestDto(PostsType postsType, String title, String content){
        this.postsType = postsType;
        this.title = title;
        this.content = content;
    }


}
