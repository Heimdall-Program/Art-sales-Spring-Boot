package com.cursework.WebArtSell.Controllers;

import com.cursework.WebArtSell.Models.Product;
import com.cursework.WebArtSell.Services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/main-user")
    public String getMainUserPage(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "main-user";
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "main";
    }
}
