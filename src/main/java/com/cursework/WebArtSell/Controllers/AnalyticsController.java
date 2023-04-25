package com.cursework.WebArtSell.Controllers;

import com.cursework.WebArtSell.Models.Transaction;
import com.cursework.WebArtSell.Models.TransactionChartData;
import com.cursework.WebArtSell.Repo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnalyticsController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/api/transactions")
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    @GetMapping("/api/transactionChartData")
    public List<TransactionChartData> getTransactionChartData() {
        System.out.println("Fetching chart data...");
        List<TransactionChartData> chartData = transactionRepository.findTransactionChartData();
        System.out.println(chartData);
        return chartData;
    }


}
