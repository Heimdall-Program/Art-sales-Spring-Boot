package com.cursework.WebArtSell.Repo;

import com.cursework.WebArtSell.Models.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
}