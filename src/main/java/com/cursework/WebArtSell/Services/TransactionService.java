package com.cursework.WebArtSell.Services;

import com.cursework.WebArtSell.Models.Product;
import com.cursework.WebArtSell.Models.Transaction;
import com.cursework.WebArtSell.Models.User;
import com.cursework.WebArtSell.Repositories.TransactionRepository;
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
    public boolean checkProductTransaction(Long productId){
        List<Transaction> transactions = transactionRepository.findAllByProductId(productId);

        return transactions.isEmpty();
    }

    public boolean checkProductOwnership(User user, Product product){
        return user.getId().equals(product.getCreatedBy().getId());
    }

    public boolean isProductInTransactions(Product product) {
        return transactionRepository.findAll().stream()
                .anyMatch(transaction -> transaction.getProductId().equals(product.getId()));
    }
}