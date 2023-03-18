package com.devkor.study.products;

import com.devkor.study.products.dto.ProductDTO;
import com.devkor.study.products.dto.ProductPhotoDTO;
import com.devkor.study.products.entities.ProductEntity;
import com.devkor.study.products.entities.ProductPhotoEntity;
import com.devkor.study.products.repositories.ProductPhotoRepository;
import com.devkor.study.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductPhotoRepository productPhotoRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductPhotoRepository productPhotoRepository, ProductRepository productRepository) {
        this.productPhotoRepository = productPhotoRepository;
        this.productRepository = productRepository;
    }


    @Override
    public List<ProductDTO> findAllProducts(){
        // entity 받아오기
        List<ProductEntity> products = productRepository.findAll();

        //entity to dto 메서드로 추가?
        List<ProductDTO> productDTOs = new ArrayList<>();
        for(ProductEntity product : products){
            List<String> url = new ArrayList<>();
            for(ProductPhotoEntity photo : product.getPhotos()){
                url.add(photo.getUrl());
            }
            ProductPhotoDTO productPhotoDTO = new ProductPhotoDTO(url);
            ProductDTO productDTO = new ProductDTO(product.getName(),product.getPrice(), product.getStock(), product.getDescription(), productPhotoDTO);
            productDTOs.add(productDTO);
        }

        return productDTOs;
    }
    @Override
    public ProductDTO findProductById(Long id){
        ProductEntity product = productRepository.findById(id).orElseThrow(()->new NoSuchElementException("해당 id의 product가 없습니다."));
        //코드 중복 고치기 entity 에 toDto 넣어도 되는지
        List<String> url = new ArrayList<>();
        for(ProductPhotoEntity photo : product.getPhotos()){
            url.add(photo.getUrl());
        }
        ProductPhotoDTO productPhotoDTO = new ProductPhotoDTO(url);
        return new ProductDTO(product.getName(), product.getPrice(), product.getStock(), product.getDescription(), productPhotoDTO);

    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO){
        //dto 를 이용한 entity 초기화
        ProductEntity productEntity = ProductEntity.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .stock(productDTO.getStock())
                .description(productDTO.getDescription())
                .photos(new ArrayList<>()) // instance 초기화를 해야함
                .build();
        // photo url 들을 추가
        for(String u : productDTO.getPhotos().getUrl()){
            ProductPhotoEntity productPhotoEntity = ProductPhotoEntity.builder().url(u).build();
            productEntity.addPhoto(productPhotoEntity); // entity 계속 새로 선언돼도 save
        }
        //저장
        ProductEntity returnEntity = productRepository.save(productEntity); //양방향 관계

        //entity to dto 변환 과정
        List<String> url = new ArrayList<>();
        for(ProductPhotoEntity photo : returnEntity.getPhotos()){
            url.add(photo.getUrl());
        }
        ProductPhotoDTO returnPhotoDTO = new ProductPhotoDTO(url);
        return new ProductDTO(returnEntity.getName(), returnEntity.getPrice(), returnEntity.getStock(), returnEntity.getDescription(),returnPhotoDTO );


    }


    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(()-> new NoSuchElementException());
        //코드 중복 및 save 시 photo 가 업데이트 되는게 아니라 추가가 됨
//        ProductEntity productUpdateEntity = ProductEntity.builder()
//                .id(id)
//                .name(productDTO.getName())
//                .price(productDTO.getPrice())
//                .stock(productDTO.getStock())
//                .description(productDTO.getDescription())
//                .photos(new ArrayList<ProductPhotoEntity>()) // instance 초기화를 해야함
//                .build();
//        // photo url 들을 추가
//        for(String u : productDTO.getPhotos().getUrl()){
//            ProductPhotoEntity productPhotoEntity = ProductPhotoEntity.builder().url(u).build();
//            productEntity.addPhoto(productPhotoEntity); // entity 계속 새로 선언돼도 save
//        }
//
//        //저장
//        ProductEntity returnEntity = productRepository.save(productEntity); //양방향 관계
//
//        //entity to dto 변환 과정
//        List<String> url = new ArrayList<>();
//        for(ProductPhotoEntity photo : returnEntity.getPhotos()){
//            url.add(photo.getUrl());
//        }
//        ProductPhotoDTO returnPhotoDTO = new ProductPhotoDTO(url);
//        ProductDTO returnDTO = new ProductDTO(returnEntity.getName(), returnEntity.getPrice(), returnEntity.getStock(), returnEntity.getDescription(),returnPhotoDTO );
//
//        return returnDTO;

        // 사진 업데이트 시 다 삭제하고 다시 등록?
        // save하면 자동으로?

        return null;

    }

    @Override
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

}
