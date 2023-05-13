package com.cursework.WebArtSell.Controllers;

import com.cursework.WebArtSell.Models.User;
import com.cursework.WebArtSell.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/user_list")
public class UserListController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getAllUsers(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "user_list";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable("id") long id, @ModelAttribute("userForm") User userForm, Model model) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setLogin(userForm.getLogin());
            user.setEmail(userForm.getEmail());
            user.setStatus(userForm.getStatus());
            user.setRole(userForm.getRole());
            userRepository.save(user);
        }
        return "redirect:/user_list";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userRepository.deleteById(id);
        return "redirect:/user_list";
    }


}