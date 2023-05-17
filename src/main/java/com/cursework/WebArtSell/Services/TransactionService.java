package com.cursework.WebArtSell.Services;

import com.cursework.WebArtSell.Models.Transaction;
import com.cursework.WebArtSell.Repo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public Map<String, Double> getMonthlySalesData() {
        List<Transaction> transactions = findAll();

        Map<String, Double> salesData = new TreeMap<>();

        for (Transaction transaction : transactions) {
            String monthYear = transaction.getPurchaseDate().format(DateTimeFormatter.ofPattern("MM-yyyy"));

            salesData.put(monthYear, salesData.getOrDefault(monthYear, 0.0) + transaction.getSum());
        }

        return salesData;
    }

}