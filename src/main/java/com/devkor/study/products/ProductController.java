package com.devkor.study.products;

import com.devkor.study.products.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductServiceImpl productService;
    @Autowired
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<ProductDto> findAllProducts(){
        return productService.findAllProducts();
    }
    @GetMapping("/{id}")
    public ProductDto findProductById(@PathVariable("id") Long id) {
        return productService.findProductById(id);
    }

    @PostMapping("")
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        return productService.createProduct(productDto);
    }


    @PatchMapping("{id}")
    public ProductDto updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto){
        return productService.updateProduct(id,productDto);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }
}

