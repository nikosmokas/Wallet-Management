package com.walletmanagement.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.walletmanagement.entities.User;
import com.walletmanagement.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    
    @GetMapping("/login")
    public String login() {
        return "login";
    }



    @PostMapping("/login")
    public String processLogin(Model model, User loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        // Retrieve user by email from the database
        User user = userRepository.findByEmail(email);

        // Check if the user exists and if the password matches
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            // Authentication successful, redirect to the dashboard
            return "redirect:/?firstName=" + user.getFirstName();
        } else {
            // Authentication failed, return error message to the login page
            model.addAttribute("error", "Invalid email or password");
            return "redirect:/";
        }
    }

    @GetMapping("/")
    public String home(Model model, @RequestParam(name = "firstName", required = false) String firstName) {
        if (firstName != null) {
            model.addAttribute("firstName", "Welcome, " + firstName + "!");
        } else {
            model.addAttribute("firstName", "Welcome!");
        }
        return "index";
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
