package com.cursework.WebArtSell.Controllers;

import com.cursework.WebArtSell.Models.Transaction;
import com.cursework.WebArtSell.Repo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transaction_list")
public class TransactionListController {
    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping
    public String getAllTransactions(Model model) {
        Iterable<Transaction> transactions = transactionRepository.findAll();
        model.addAttribute("transactions", transactions);
        return "transaction_list";
    }
}