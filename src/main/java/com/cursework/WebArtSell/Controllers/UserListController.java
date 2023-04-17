package com.cursework.WebArtSell.Controllers;

import com.cursework.WebArtSell.Models.Announcement;
import com.cursework.WebArtSell.Repo.TransactionRepository;
import com.cursework.WebArtSell.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserListController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user_list")
    public String authorisationModel(Model model) {
        Iterable<Announcement> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "user_list";
    }
}
