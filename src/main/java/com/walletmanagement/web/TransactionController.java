package com.walletmanagement.web;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.walletmanagement.entities.User;
import com.walletmanagement.entities.Wallet;
import com.walletmanagement.service.TransactionService;
import com.walletmanagement.service.WalletService;
import com.walletmanagement.web.dto.TransactionCreationDTO;
import com.walletmanagement.repository.UserRepository;
import com.walletmanagement.repository.WalletRepository;



@Controller
public class TransactionController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private WalletService walletService;
    
    @Autowired
    private TransactionService transactionService;



    @ModelAttribute("transaction")
    public TransactionCreationDTO transactionCreationDTO() {
        return new TransactionCreationDTO();
    }

    

    @PostMapping("/saveIncomeTransaction")
    public String createIncomeTransaction(Model model, @RequestBody TransactionCreationDTO transactionCreationDTO) {
        // Retrieve authenticated user's details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String email = ((UserDetails) principal).getUsername();
                User user = userRepository.findByEmail(email);

                Optional<Wallet> optionalWallet = walletRepository.findById(transactionCreationDTO.getWalletId());
                if (optionalWallet.isPresent()) {
                    Wallet wallet = optionalWallet.get();
                    // Update wallet amount
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    Float formatedAmount = Float.parseFloat(decimalFormat.format(transactionCreationDTO.getAmount()));


                    wallet.setAmount(wallet.getAmount() + formatedAmount);
                    walletRepository.save(wallet);

                    // Set current date and time
                    Date currentDateAndTime = new Date();
                    transactionCreationDTO.setDate(currentDateAndTime);

                    // Save the transaction
                    transactionService.save(transactionCreationDTO);

                    return "redirect:/";
                } else {
                    // Handle case when wallet is not found
                    return "redirect:/error";
                }
            } else {
                // Handle case when principal is not an instance of UserDetails
                return "redirect:/error";
            }
        } else { 
            return "redirect:/error";
        }
    }


    @PostMapping("/saveExpenseTransaction")
    public String createExpenseTransaction(Model model, @RequestBody TransactionCreationDTO transactionCreationDTO) {
        // Retrieve authenticated user's details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String email = ((UserDetails) principal).getUsername();
                User user = userRepository.findByEmail(email);

                Optional<Wallet> optionalWallet = walletRepository.findById(transactionCreationDTO.getWalletId());
                if (optionalWallet.isPresent()) {
                    Wallet wallet = optionalWallet.get();
                    // Update wallet amount
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");

                    BigDecimal walletAmount = BigDecimal.valueOf(wallet.getAmount());
                    BigDecimal formatedAmount = BigDecimal.valueOf(Float.parseFloat(decimalFormat.format(transactionCreationDTO.getAmount())));
                    BigDecimal result = walletAmount.subtract(formatedAmount);

                    wallet.setAmount(result.floatValue());
                    walletRepository.save(wallet);

                    // Set current date and time
                    Date currentDateAndTime = new Date();
                    transactionCreationDTO.setDate(currentDateAndTime);

                    // Save the transaction
                    transactionService.save(transactionCreationDTO);

                    return "redirect:/";
                } else {
                    // Handle case when wallet is not found
                    return "redirect:/error";
                }
            } else {
                // Handle case when principal is not an instance of UserDetails
                return "redirect:/error";
            }
        } else { 
            return "redirect:/error";
        }
    }
}
