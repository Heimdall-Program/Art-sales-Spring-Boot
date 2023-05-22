package com.cursework.WebArtSell.Repositories;

import com.cursework.WebArtSell.Models.Transaction;
import com.cursework.WebArtSell.Models.TransactionChartData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT new com.cursework.WebArtSell.Models.TransactionChartData(FUNCTION('DATE_FORMAT', t.purchaseDate, '%Y-%m-%d'), SUM(t.sum)) " +
            "FROM Transaction t GROUP BY FUNCTION('DATE_FORMAT', t.purchaseDate, '%Y-%m-%d') ORDER BY FUNCTION('DATE_FORMAT', t.purchaseDate, '%Y-%m-%d') ASC")
    List<TransactionChartData> findTransactionChartData();

    List<Transaction> findAllByProductId(Long productId);
}
