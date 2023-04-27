package com.devkor.study.products.repositories;

import com.devkor.study.products.entities.ProductPhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPhotoRepository extends JpaRepository<ProductPhotoEntity, Long> {
}
