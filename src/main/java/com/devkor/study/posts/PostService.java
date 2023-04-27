package com.devkor.study.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<PostEntity> findAllPosts(){
        return postRepository.findAll();
    }
    public PostEntity findPostById(Long id){
        return postRepository.findById(id).orElseThrow(()-> new NoSuchElementException());
    }

    public PostDto createPost(PostDto postDto){
        PostEntity postEntity = postDto.toEntity();
        PostEntity returnEntity =  postRepository.save(postEntity);
        return new PostDto(returnEntity.getEditor(), returnEntity.getLikeNum());

    }

}
