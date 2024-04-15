package com.walletmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walletmanagement.entities.Wallet;
import com.walletmanagement.repository.WalletRepository;
import com.walletmanagement.web.dto.WalletCreationDTO;

@Service
public class WalletServiceImp implements WalletService{
    
    @Autowired
    private WalletRepository walletRepository;

    @Override
    public Wallet save(WalletCreationDTO walletCreationDTO) {
        Wallet wallet = new Wallet(walletCreationDTO.getWalletName(), walletCreationDTO.getCurrency(), walletCreationDTO.getAmount(), walletCreationDTO.getNotes(), walletCreationDTO.getUserId());

        return walletRepository.save(wallet);
    }
    
}
