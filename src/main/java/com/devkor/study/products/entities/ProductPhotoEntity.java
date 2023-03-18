package com.devkor.study.products.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_photos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder()
public class ProductPhotoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne
    @JoinColumn(name="ref_productID")
    private ProductEntity product;


    public void setProduct(ProductEntity product) {
        this.product = product;
    } //setter 없이 연결하는 법? builder로?


}
