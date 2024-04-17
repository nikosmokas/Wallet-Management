package com.walletmanagement.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.walletmanagement.entities.Transaction;
import com.walletmanagement.entities.User;
import com.walletmanagement.entities.Wallet;
import com.walletmanagement.repository.UserRepository;
import com.walletmanagement.repository.WalletRepository;
import com.walletmanagement.service.TransactionService;
import com.walletmanagement.service.TransactionServiceImp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MainController {

    @Autowired
    private TransactionService transactionService;
    
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

    
    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/")
    public String home(Model model) {
         // Retrieve authenticated user's details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String email = ((UserDetails) principal).getUsername();
                User user = userRepository.findByEmail(email);
                model.addAttribute("firstName", "Welcome, " + user.getFirstName() + "!");
                // Retrieve the authenticated user's wallets and add them to the model
                List<Wallet> userWallets = walletRepository.findByUserId(user.getId());
                model.addAttribute("authenticatedUserWallets", userWallets);
                // Fetch the last transaction for each wallet
                Map<Long, Transaction> lastTransactions = new HashMap<>();
                for (Wallet wallet : userWallets) {
                    Optional<Transaction> optionalLastTransaction = transactionService.getLastTransactionForWallet(wallet.getId());
                    Transaction lastTransaction = optionalLastTransaction.orElse(null);
                    lastTransactions.put(wallet.getId(), lastTransaction);
                }
                model.addAttribute("lastTransactions", lastTransactions);
                
            } else {
                model.addAttribute("firstName", "Welcome!");
            }
        } else {
            model.addAttribute("firstName", "Welcome!");
        }
    return "index";
    }

    @PostMapping("/")
    public String handlePostRequest(Model model) {
        return "redirect:/"; // Redirect to the root endpoint after processing the POST request
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Perform logout logic here
        // For example, invalidate session, clear authentication, etc.
        SecurityContextHolder.getContext().setAuthentication(null);
        request.getSession().invalidate();

        // Redirect the user to a desired page after logout
        return "logout";
    }

    
}
