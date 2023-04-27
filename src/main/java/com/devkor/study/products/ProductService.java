package com.devkor.study.products;

import com.devkor.study.products.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAllProducts();
    ProductDto findProductById(Long id);

    ProductDto createProduct(ProductDto productDto);

    ProductDto updateProduct(Long id, ProductDto productDto);
//
    void deleteProduct(Long id);

}
