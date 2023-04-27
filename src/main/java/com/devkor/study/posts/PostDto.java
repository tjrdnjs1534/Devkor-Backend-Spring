package com.devkor.study.posts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private String editor;
    private int likeNum;

    public PostEntity toEntity(){
        return PostEntity.builder().editor(editor).likeNum(likeNum).build();
    }
}
