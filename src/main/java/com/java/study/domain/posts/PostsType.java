package com.java.study.domain.posts;

public enum PostsType {
    notice("공지사항"),
    free("자유게시판");

    private String value;

    PostsType(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
