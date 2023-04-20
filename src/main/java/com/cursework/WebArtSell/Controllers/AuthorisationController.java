package com.cursework.WebArtSell.Controllers;

import com.cursework.WebArtSell.Models.User;
import com.cursework.WebArtSell.Repo.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/authorisation")
public class AuthorisationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "authorisation";
    }

    @PostMapping
    public String authenticateUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        Optional<User> optionalUser = userRepository.findByEmailAndPassword(email, password);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            session.setAttribute("user", user);
            if (user.getRole().equals("ADMIN")) {
                return "redirect:/admin";
            } else {
                return "redirect:/user-panel";
            }
        } else {
            model.addAttribute("errorMessage", "Неверный адрес электронной почты или пароль");
            return "authorisation";
        }
    }


    public boolean checkUserRole(HttpServletRequest request, String role) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return false;
        }
        User user = (User) session.getAttribute("user");
        return user.getRole().equals(role);
    }

    @GetMapping("/admin")
    public String showAdminPage(HttpServletRequest request) {
        if (checkUserRole(request, "ADMIN")) {
            return "user_list";
        } else {
            return "redirect:/authorisation";
        }
    }

    @GetMapping("/user-panel")
    public String showUserPanel(HttpServletRequest request) {
        if (checkUserRole(request, "USER")) {
            return "/main";
        } else {
            return "redirect:/authorisation";
        }
    }

}
