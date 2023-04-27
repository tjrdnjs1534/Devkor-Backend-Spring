package com.devkor.study.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("")
    public List<PostEntity> findAllPosts(){
        return postService.findAllPosts();
    }

    @GetMapping("/{id}")
    public PostEntity findPostById(@PathVariable("id") Long id){
        return postService.findPostById(id);
    }

    @PostMapping("")
    public PostDto createPost(@RequestBody PostDto postDto){
        return postService.createPost(postDto);
    }


}
