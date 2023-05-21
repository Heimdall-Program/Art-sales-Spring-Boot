package com.cursework.WebArtSell.Controllers;


import com.cursework.WebArtSell.Models.Product;
import com.cursework.WebArtSell.Models.Transaction;
import com.cursework.WebArtSell.Models.TransactionChartData;
import com.cursework.WebArtSell.Models.User;
import com.cursework.WebArtSell.Repositories.ProductRepository;
import com.cursework.WebArtSell.Repositories.TransactionRepository;
import com.cursework.WebArtSell.Services.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/billing/{id}")
    public String authorisationModel(@PathVariable Long id, Model model, HttpServletRequest request) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            model.addAttribute("product", product.get());

            Transaction transaction = new Transaction();

            HttpSession session = request.getSession();
            User buyer = (User) session.getAttribute("user");
            transaction.setBuyerId(buyer.getId().toString());
            transaction.setSellerId(product.get().getCreatedBy().getId().toString());
            transaction.setPurchaseDate(LocalDateTime.now());
            transaction.setSum(product.get().getPrice().doubleValue());

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


    @GetMapping("/api/transactions")
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    @GetMapping("/api/transactionChartData")
    public List<TransactionChartData> getTransactionChartData() {
        List<TransactionChartData> chartData = transactionRepository.findTransactionChartData();
        System.out.println(chartData);
        return chartData;
    }

    @GetMapping("/table-transactions")
    public String transactions(Model model) {
        List<Transaction> transactions = transactionService.findAll();
        Map<String, Double> chartData = transactionService.getMonthlySalesData();
        model.addAttribute("transactions", transactions);
        model.addAttribute("chartData", chartData);
        return "table-transactions";
    }
}
