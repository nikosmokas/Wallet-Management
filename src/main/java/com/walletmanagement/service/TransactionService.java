package com.walletmanagement.service;


import com.walletmanagement.entities.Transaction;
import com.walletmanagement.web.dto.TransactionCreationDTO;


public interface TransactionService {

    Transaction save(TransactionCreationDTO transactionDTO);

    
}
