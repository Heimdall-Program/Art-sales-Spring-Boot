package com.cursework.WebArtSell.Services;

import com.cursework.WebArtSell.Models.Product;
import com.cursework.WebArtSell.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findAllByCategory(String category) {
        return productRepository.findByCategory(category);
    }

}
