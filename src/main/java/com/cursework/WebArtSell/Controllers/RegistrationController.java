package com.cursework.WebArtSell.Controllers;

import com.cursework.WebArtSell.Models.User;
import com.cursework.WebArtSell.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "registration";
    }

    @PostMapping
    public String registerUser(@RequestParam String name, @RequestParam String email, @RequestParam String password,
                               @RequestParam String confirm_password, Model model) {
        if (!password.equals(confirm_password)) {
            model.addAttribute("error", "Пароли не совпадают!");
            return "registration";
        }

        User user = new User();
        user.setLogin(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole("USER");
        user.setCreationDate(LocalDateTime.now());
        user.setStatus("Активный");
        userRepository.save(user);

        return "redirect:/authorisation";
    }

}
