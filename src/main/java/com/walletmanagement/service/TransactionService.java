package com.walletmanagement.service;


import java.util.Optional;

import com.walletmanagement.entities.Transaction;
import com.walletmanagement.web.dto.TransactionCreationDTO;


public interface TransactionService {

    Transaction save(TransactionCreationDTO transactionDTO);
    public Optional<Transaction> getLastTransactionForWallet(Long walletId);

    
}
