package com.cursework.WebArtSell.Controllers;

import com.cursework.WebArtSell.Models.User;
import com.cursework.WebArtSell.Repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/table-users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getAllUsers(Model model, HttpSession session) {
        Iterable<User> users = userRepository.findAll();
        User currentUser = (User) session.getAttribute("user");
        model.addAttribute("users", users);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("statuses", new String[]{"Проверенный", "Непроверенный"});
        model.addAttribute("roles", new String[]{"ADMIN", "USER"});
        return "table-users";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, @ModelAttribute User user, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (!currentUser.getId().equals(id)) {
            Optional<User> optUser = userRepository.findById(id);
            if (optUser.isPresent()) {
                User existUser = optUser.get();
                existUser.setLogin(user.getLogin());
                existUser.setEmail(user.getEmail());
                existUser.setStatus(user.getStatus());
                existUser.setRole(user.getRole());
                userRepository.save(existUser);
            }
        }
        return "redirect:/table-users";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (!currentUser.getId().equals(id)) {
            userRepository.deleteById(id);
        }
        return "redirect:/table-users";
    }
}
