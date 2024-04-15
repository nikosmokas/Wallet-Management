package com.walletmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.walletmanagement.entities.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    
    List<Wallet> findByUserId(Long userId);
}
