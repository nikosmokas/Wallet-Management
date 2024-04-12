package com.walletmanagement.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String registerUserAccount(Model model, @ModelAttribute("user") UserRegistrationDTO userRegistrationDTO) {
        try {
            userService.loadUserByUsername(userRegistrationDTO.getEmail());
            model.addAttribute("error", "Email already in use.");
            return "redirect:/registration?error";
        } catch (Exception e) {
            userService.save(userRegistrationDTO);

            return "redirect:/registration?success";
        }
        
    }

}
