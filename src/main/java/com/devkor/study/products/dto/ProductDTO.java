package com.devkor.study.products.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private String name;

    private int price;

    private int stock;

    private String description;

    private ProductPhotoDTO photos;

//    public ProductEntity toEntity(){
//        return ProductEntity.builder()
//                .name(name)
//                .price(price)
//                .stock(stock)
//                .description(description)
//                .photos(photos.toEntitySet())
//                .build();
//    }
}
