package com.cursework.WebArtSell.Repositories;

import com.cursework.WebArtSell.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);

}
