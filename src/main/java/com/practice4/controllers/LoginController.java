package com.practice4.controllers;


import com.practice4.models.LoginAttempt;
import com.practice4.models.User;
import com.practice4.repository.LoginAttemptRepository;
import com.practice4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private LoginAttemptRepository loginAttemptRepository;
    @Autowired
    private UserRepository userRepository;
   // private List<String> loginAttempts = new ArrayList<>();

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String handlelogin(@RequestParam("uname") String username,
                              @RequestParam("psw") String password,
                              Model model) {
        //loginAttempts.add("Username: " + username + ", Password: " + password); this was pre-DB
        loginAttemptRepository.save(new LoginAttempt(username, password));

        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)){
            return "redirect:/upload";
        } else {
            return "redirect:/login?error";
        }
    }

    @GetMapping("/LoginAttempts")
    public String showLoginAttempts(Model model) {
        List<LoginAttempt> loginAttempts = loginAttemptRepository.findAll();
        model.addAttribute("loginAttempts", loginAttempts);
        return "loginAttempts";
    }
}
