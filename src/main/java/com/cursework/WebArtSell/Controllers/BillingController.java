package com.cursework.WebArtSell.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BillingController {

    @GetMapping("/billing")
    public String authorisationModel(Model model) {
        model.addAttribute("users", "");
        return "billing";
    }
}
