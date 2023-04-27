package com.devkor.study.posts;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post_photos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder()
public class PostPhotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "ref_post")
    private PostEntity post;
}

