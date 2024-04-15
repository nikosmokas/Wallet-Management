package com.walletmanagement.service;

import com.walletmanagement.entities.Wallet;
import com.walletmanagement.web.dto.WalletCreationDTO;


public interface WalletService {

    Wallet save(WalletCreationDTO walletCreationDTO);
    
}
