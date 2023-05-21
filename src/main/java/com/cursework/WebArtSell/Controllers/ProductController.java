package com.cursework.WebArtSell.Controllers;

import com.cursework.WebArtSell.Models.Product;
import com.cursework.WebArtSell.Models.User;
import com.cursework.WebArtSell.Repositories.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/table-products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "table-products";
    }

    @GetMapping("/add")
    public String addAnnouncement() {
        return "product-add";
    }

    @PostMapping("/add")
    public String createProduct(@ModelAttribute Product product, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        product.setCreatedBy(user);
        product.setCreationDate(LocalDateTime.now());
        productRepository.save(product);
        return "redirect:/main-user";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable long id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "product-details";
        }
        return "redirect:/table-products";
    }

    @GetMapping("/{id}/edit")
    public String editProduct(@PathVariable long id, Model model) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "product-edit";
        }
        return "redirect:/table-products";
    }

    @PostMapping("/{id}/edit")
    public String updateProduct(@PathVariable long id, @ModelAttribute Product updatedProduct) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setImageUrl(updatedProduct.getImageUrl());
        product.setPrice(updatedProduct.getPrice());
        product.setArtist(updatedProduct.getArtist());
        product.setDimensions(updatedProduct.getDimensions());
        productRepository.save(product);
        return "redirect:/table-products";
    }

    @PostMapping("/{id}/remove")
    public String deleteProduct(@PathVariable long id) {
        productRepository.deleteById(id);
        return "redirect:/table-products";
    }

}
