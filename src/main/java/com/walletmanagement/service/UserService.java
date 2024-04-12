package com.walletmanagement.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.walletmanagement.entities.User;
import com.walletmanagement.web.dto.UserRegistrationDTO;


public interface UserService extends UserDetailsService {

    User save(UserRegistrationDTO registrationDTO);

    
}
