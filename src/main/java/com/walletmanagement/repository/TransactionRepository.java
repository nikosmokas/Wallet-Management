package com.walletmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.walletmanagement.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    List<Transaction> findByWalletId(Long walletId);
    Optional<Transaction> findTopByWalletIdOrderByTransactionDateDesc(Long walletId);
}
