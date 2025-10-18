package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    @Query("SELECT b FROM Brand b WHERE b.category.id = :categoryId")
    List<Brand> findBrandsByCategory(@Param("categoryId") Long categoryId);
}
