package com.walletmanagement.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.walletmanagement.service.UserService;
import com.walletmanagement.web.dto.UserRegistrationDTO;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    
    private UserService userService;

    @Autowired
    public UserRegistrationController(UserService theUserService) {
        super();
        this.userService = theUserService;
    }

    @ModelAttribute("user")
    public UserRegistrationDTO userRegistrationDTO() {
        return new UserRegistrationDTO();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(Model model, RedirectAttributes redirectAttributes, @ModelAttribute("user") UserRegistrationDTO userRegistrationDTO) {
        
        String password = userRegistrationDTO.getPassword();
        String confirmPassword = userRegistrationDTO.getConfirmPassword();

        String errorMsg ="";
        if (!isValidPassword(password)) {
            errorMsg = "Password must contain at least one letter and one digit, and be between 8 and 20 characters long.";
            redirectAttributes.addFlashAttribute("error", errorMsg);
            return "redirect:/registration";
        }
    
        if (!verifyMatch(password, confirmPassword)) {
            errorMsg = "Passwords don't match.";
            redirectAttributes.addFlashAttribute("error", errorMsg);
            return "redirect:/registration";
        }
    
        try {
            userService.loadUserByUsername(userRegistrationDTO.getEmail());
            errorMsg = "Email already in use.";
            redirectAttributes.addFlashAttribute("error", errorMsg);
            return "redirect:/registration";
        } catch (Exception e) {
            userService.save(userRegistrationDTO);
            return "redirect:/login?success";
        }
        
    }


    private boolean verifyMatch(String password, String confirmPassword) {
        if (password.equals(confirmPassword)) {
            return true;
        }
        return false;
    }

    private boolean isValidPassword(String password) {
        // Password must contain at least one letter and one digit, and be between 8 and 20 characters long.
        String pattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$";
        return password.matches(pattern);
    }

}
