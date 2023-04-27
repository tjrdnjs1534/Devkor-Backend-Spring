package com.devkor.study.posts;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String editor;
    private int likeNum;

    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL)
    private List<CommentEntity> comments  = new ArrayList<>();;
}
