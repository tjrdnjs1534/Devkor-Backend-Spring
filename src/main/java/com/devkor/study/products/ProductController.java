package com.devkor.study.products;

import com.devkor.study.products.dto.ProductDTO;
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
    public List<ProductDTO> findAllProducts(){
        return productService.findAllProducts();
    }
    @GetMapping("/{id}")
    public ProductDTO findProductById(@PathVariable("id") Long id) {
        return productService.findProductById(id);
    }

    @PostMapping("")
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO){
        return productService.createProduct(productDTO);
    }


    @PatchMapping("{id}")
    public ProductDTO updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO){
        return productService.updateProduct(id,productDTO);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }
}

