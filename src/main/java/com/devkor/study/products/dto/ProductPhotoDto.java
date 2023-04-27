package com.devkor.study.products.dto;

import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductPhotoDto {
    private List<String> url;


//    public Set<ProductPhotoEntity> toEntitySet(){
//        Set<ProductPhotoEntity> PhotoEntitySet =  new LinkedHashSet<ProductPhotoEntity>();
//        for(String u : url){
//            PhotoEntitySet.add(ProductPhotoEntity.builder().url(u).build());
//        }
//        return PhotoEntitySet;
//    }
}
