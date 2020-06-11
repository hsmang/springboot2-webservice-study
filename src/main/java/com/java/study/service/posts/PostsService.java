package com.java.study.service.posts;

import com.java.study.domain.posts.Posts;
import com.java.study.domain.posts.PostsRepository;
import com.java.study.domain.posts.PostsResponseDto;
import com.java.study.domain.posts.PostsUpdateRequestDto;
import com.java.study.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional // JPA의 엔티티 매니저가 활성화된 상태로 트랜잭션안에 있을 경우엔 영속성이 유지됨. 즉, Entity 객체의 값만 변경하면 별도로 update 쿼리를 날릴 필요가 없다. 이 개념을 Dirty Checking 이라고 함.
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }


    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
}
