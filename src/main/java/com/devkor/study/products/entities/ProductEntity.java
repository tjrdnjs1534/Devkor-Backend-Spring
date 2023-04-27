package com.devkor.study.products.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.sql.In;

import java.util.*;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder()
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private Integer stock;

    private String description;

    private String createdBy;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    //mappedby(연관관계의 주인), cascade(영속성 전이), orphan removal(연관관계 고아되면) 정책 확인
    private List<ProductPhotoEntity> photos = new ArrayList<>(); //set list차이

    public void addPhoto(ProductPhotoEntity photo){
        photos.add(photo);
        photo.setProduct(this);
    }
//    public void removePhoto(ProductPhotoEntity photo){
//        photos.remove(photo);
//        photo.setProduct(null);
//    }
}
