package com.devkor.study.products.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private int price;

    private int stock;

    private String description;

    private String createdBy;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL) //mappedby, cascade 정책 확인
    private List<ProductPhotoEntity> photos = new ArrayList<>(); //set list차이

    public void addPhoto(ProductPhotoEntity photo){
        photos.add(photo);
        photo.setProduct(this);
    }
}
