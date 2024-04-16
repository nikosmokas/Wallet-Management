package com.walletmanagement.web;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.walletmanagement.entities.User;
import com.walletmanagement.service.WalletService;
import com.walletmanagement.web.dto.WalletCreationDTO;
import com.walletmanagement.repository.UserRepository;



@Controller
@RequestMapping("/wallet")
public class WalletController {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletService walletService;



    @ModelAttribute("wallet")
    public WalletCreationDTO walletCreationDTO() {
        return new WalletCreationDTO();
    }

    @GetMapping("/walletCreationForm")
    public String showWalletCreationForm() {
        return "wallet/walletCreationForm";
    }

    @GetMapping("/walletSuccess")
    public String walletSuccess() {
        return "wallet/walletSuccess";
    }

    @GetMapping("/walletFail")
    public String walletFail() {
        return "wallet/walletFail";
    }

    @PostMapping("/walletCreationForm")
    public String createNewWallet(Model model, @ModelAttribute("wallet") WalletCreationDTO walletCreationDTO) {
        
        
        // Retrieve authenticated user's details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String email = ((UserDetails) principal).getUsername();
                User user = userRepository.findByEmail(email);
                walletCreationDTO.setUserId(user.getId());
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                walletCreationDTO.setAmount(Float.parseFloat(decimalFormat.format(walletCreationDTO.getAmount())));
                walletService.save(walletCreationDTO);
                return "redirect:/wallet/walletSuccess";
            }
            else {
                return "redirect:/wallet/walletFail";
            }
        }
        return "redirect:/wallet/walletFail";
        
    }


    
}
