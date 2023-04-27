package com.devkor.study.products.dto;

import com.devkor.study.products.entities.ProductEntity;
import com.devkor.study.products.entities.ProductPhotoEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private String name;

    private Integer price;

    private Integer stock;

    private String description;

    private ProductPhotoDto photos;


    public ProductDto entityToDto(ProductEntity productEntity) {
        List<String> url = new ArrayList<>();
        for(ProductPhotoEntity photo : productEntity.getPhotos()){
            url.add(photo.getUrl());
        }
        ProductPhotoDto productPhotoDto = new ProductPhotoDto(url);
        return ProductDto.builder()
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .stock(productEntity.getStock())
                .description(productEntity.getDescription())
                .photos(productPhotoDto)
                .build();
    }

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
