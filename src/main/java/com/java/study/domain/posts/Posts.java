package com.java.study.domain.posts;

import com.java.study.domain.BaseTimeEntity;
import com.java.study.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(value = EnumType.STRING)
    private PostsType postsType;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public Posts(PostsType postsType, String title, String content, User user){
        this.postsType = postsType;
        this.title = title;
        this.content = content;
        this.user = user;
    }


    public void update(PostsType postsType, String title, String content) {
        this.postsType = postsType;
        this.title = title;
        this.content = content;
    }
}
