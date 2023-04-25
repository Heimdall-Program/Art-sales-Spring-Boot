package com.cursework.WebArtSell.Controllers;


import com.cursework.WebArtSell.Models.Transaction;
import com.cursework.WebArtSell.Models.TransactionChartData;
import com.cursework.WebArtSell.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transaction_list")
    public String transactions(Model model) {
        List<Transaction> transactions = transactionService.findAll();
        System.out.println(transactions);
        model.addAttribute("transactions", transactions);
        return "transaction_list";
    }

}