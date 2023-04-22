package com.cursework.WebArtSell.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping
    public String showHomePage(Model model) {
        model.addAttribute("title", "Главная страница");
        return "main";
    }
}