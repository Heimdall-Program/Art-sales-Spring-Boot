package com.cursework.WebArtSell.Controllers;

import com.cursework.WebArtSell.Models.Transaction;
import com.cursework.WebArtSell.Repo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }
}