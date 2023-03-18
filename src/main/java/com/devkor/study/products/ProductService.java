package com.devkor.study.products;

import com.devkor.study.products.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAllProducts();
    ProductDTO findProductById(Long id);

    ProductDTO createProduct(ProductDTO productDTO);

    ProductDTO updateProduct(Long id, ProductDTO productDTO);
//
    void deleteProduct(Long id);

}
