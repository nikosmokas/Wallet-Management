package com.walletmanagement.web;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String createNewWallet(Model model, @ModelAttribute("wallet") WalletCreationDTO walletCreationDTO, RedirectAttributes redirectAttributes) {
        
        
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
                // Add success message to be displayed in the success modal
                redirectAttributes.addFlashAttribute("successMessage", "Wallet added to your profile!");
                return "redirect:/";
            }
            else {
                return "redirect:/wallet/walletFail";
            }
        }
        return "redirect:/wallet/walletFail";
        
    }

    @DeleteMapping("/deleteWallet/{walletId}")
    public ResponseEntity<String> deleteWallet(@PathVariable Long walletId) {
        try {
            // Call the service method to delete the wallet
            walletService.deleteWallet(walletId);
            return ResponseEntity.ok().body("Wallet deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting wallet: " + e.getMessage());
        }
    }


    
}
