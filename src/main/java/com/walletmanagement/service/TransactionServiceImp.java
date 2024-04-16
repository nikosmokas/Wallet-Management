package com.walletmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walletmanagement.entities.Transaction;
import com.walletmanagement.repository.TransactionRepository;
import com.walletmanagement.web.dto.TransactionCreationDTO;

@Service
public class TransactionServiceImp implements TransactionService{
    
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction save(TransactionCreationDTO transactionCreationDTO) {
        Transaction trans = new Transaction(transactionCreationDTO.getAmount(), transactionCreationDTO.getType(), transactionCreationDTO.getWalletId(), transactionCreationDTO.getDate());

        return transactionRepository.save(trans);
    }
    
}
