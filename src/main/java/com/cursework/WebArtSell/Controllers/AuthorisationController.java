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
    public String userLogin(@RequestParam String email,
                            @RequestParam String password,
                            Model model,
                            HttpServletRequest request) {
        Optional<User> optionalUser = userRepository.findByEmailAndPassword(email, password);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            if (user.getRole().equals("ADMIN")) {
                return "redirect:/user_list";
            } else {
                return "redirect:/main-user";
            }
        } else {
            model.addAttribute("error", "Неверный email или пароль");
            return "authorisation";
        }
    }

}
