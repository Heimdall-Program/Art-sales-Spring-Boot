package com.cursework.WebArtSell.Controllers;


import com.cursework.WebArtSell.Models.Transaction;
import com.cursework.WebArtSell.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @GetMapping("/table-transactions")
    public String transactions(Model model) {
        List<Transaction> transactions = transactionService.findAll();
        Map<String, Double> chartData = transactionService.getMonthlySalesData();
        model.addAttribute("transactions", transactions);
        model.addAttribute("chartData", chartData);
        return "table-transactions";
    }
}


