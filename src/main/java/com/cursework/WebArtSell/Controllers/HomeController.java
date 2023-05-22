package com.cursework.WebArtSell.Controllers;

import com.cursework.WebArtSell.Models.Product;
import com.cursework.WebArtSell.Models.User;
import com.cursework.WebArtSell.Services.ProductService;
import com.cursework.WebArtSell.Services.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final ProductService productService;
    private final TransactionService transactionService;

    public HomeController(ProductService productService, TransactionService transactionService) {
        this.productService = productService;
        this.transactionService = transactionService;
    }

    @GetMapping("/main-user")
    public String getMainUserPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        List<Product> products = productService.findAll();
        Long currentUserId = user.getId();
        for (Product product : products) {
            if (transactionService.isProductInTransactions(product)) {
                product.setDescription("Этот товар уже продан!");
            }
            if (product.getCreatedBy().getId().equals(currentUserId)) {
                product.setDescription("Это ваш товар!)");
            }
        }
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
