package com.devkor.study.products;

import com.devkor.study.products.dto.ProductDto;
import com.devkor.study.products.dto.ProductPhotoDto;
import com.devkor.study.products.entities.ProductEntity;
import com.devkor.study.products.entities.ProductPhotoEntity;
import com.devkor.study.products.repositories.ProductPhotoRepository;
import com.devkor.study.products.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductPhotoRepository productPhotoRepository;
    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> findAllProducts(){
        // entity 받아오기
        List<ProductEntity> products = productRepository.findAll();
        //entity to dto 메서드로 추가?
        List<ProductDto> productDtos = new ArrayList<>();
        for(ProductEntity product : products){
            ProductDto productDto = new ProductDto();
            productDtos.add(productDto.entityToDto(product));
        }
        return productDtos;
    }
    @Override
    public ProductDto findProductById(Long id){
        ProductEntity product = productRepository.findById(id).orElseThrow(()->new NoSuchElementException("해당 id의 product가 없습니다."));
        ProductDto productDto = new ProductDto();
        return productDto.entityToDto(product);
    }

    @Override
    public ProductDto createProduct(ProductDto productDto){
        //dto 를 이용한 entity 초기화
        ProductEntity productEntity = ProductEntity.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .stock(productDto.getStock())
                .description(productDto.getDescription())
                .photos(new ArrayList<>()) // instance 초기화를 해야함
                .build();
        // photo url 들을 추가
        for(String u : productDto.getPhotos().getUrl()){
            ProductPhotoEntity productPhotoEntity = ProductPhotoEntity.builder().url(u).build();
            productEntity.addPhoto(productPhotoEntity); // entity 계속 새로 선언돼도 save
        }
        //저장
        ProductEntity returnEntity = productRepository.save(productEntity); //양방향 관계
        //entity to dto 변환 과정
        ProductDto returnProductDto = new ProductDto();
        return returnProductDto.entityToDto(returnEntity);
    }


    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(()-> new NoSuchElementException());
        //코드 중복 및 save 시 photo 가 업데이트 되는게 아니라 추가가 됨 -> 그래서 관계 끊어주고 orphan처리로 delete
        productEntity.getPhotos().stream().forEach(productPhotoEntity -> {
            productPhotoEntity.setProduct(null);
        });
        ProductEntity productUpdateEntity = ProductEntity.builder()
                .id(id)
                .name(productDto.getName())
                .price(productDto.getPrice())
                .stock(productDto.getStock())
                .description(productDto.getDescription())
                .photos(new ArrayList<ProductPhotoEntity>()) // instance 초기화를 해야함
                .build();
        // photo url 들을 추가
        for(String u : productDto.getPhotos().getUrl()){
            ProductPhotoEntity productPhotoEntity = ProductPhotoEntity.builder().url(u).build();
            productUpdateEntity.addPhoto(productPhotoEntity); // entity 계속 새로 선언돼도 save
        }

        //저장
        ProductEntity returnEntity = productRepository.save(productUpdateEntity); //양방향 관계
        //entity to dto 변환 과정
        ProductDto returnProductDto = new ProductDto();
        return returnProductDto.entityToDto(returnEntity);

        // 사진 업데이트 시 다 삭제하고 다시 등록?
        // save하면 자동으로?
    }

    @Override
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

}
