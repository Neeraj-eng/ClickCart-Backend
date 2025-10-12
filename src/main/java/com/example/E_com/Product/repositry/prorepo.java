package com.example.E_com.Product.repositry;

import com.example.E_com.Product.view.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface prorepo extends JpaRepository<Product,Integer> {
    @Query(value = "SELECT * FROM product " +
            "WHERE LOWER(name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(brand) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(category) LIKE LOWER(CONCAT('%', :keyword, '%'))" +
            "OR LOWER(description) LIKE LOWER(CONCAT('%', :keyword, '%'))",
            nativeQuery = true)
    List<Product> searchProducts(String keyword);
}

