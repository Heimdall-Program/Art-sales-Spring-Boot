package com.cursework.WebArtSell.Controllers;

import com.cursework.WebArtSell.Models.Product;
import com.cursework.WebArtSell.Models.Transaction;
import com.cursework.WebArtSell.Repo.ProductRepository;
import com.cursework.WebArtSell.Repo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class BillingController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/billing/{id}")
    public String authorisationModel(@PathVariable Long id, Model model) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            model.addAttribute("product", product.get());

            Transaction transaction = new Transaction();
            transaction.setBuyerId("yourBuyerId");
            transaction.setSellerId("yourSellerId");
            transaction.setPurchaseDate(LocalDateTime.now());
            transaction.setSum(Double.valueOf(product.get().getPrice().toString()));
            model.addAttribute("transaction", transaction);
        } else {
            return "redirect:/billing";
        }

        return "billing";
    }

    @PostMapping("/billing-buy")
    public String processTransaction(@ModelAttribute Transaction transaction, Model model) {
        try {
            transactionRepository.save(transaction);
        } catch (Exception e) {
            model.addAttribute("error", "Ошибка");
            model.addAttribute("transaction", new Transaction());
            return "billing";
        }

        return "redirect:/main-user";
    }

}

