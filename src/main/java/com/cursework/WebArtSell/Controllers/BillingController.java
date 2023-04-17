package com.cursework.WebArtSell.Controllers;

import com.cursework.WebArtSell.Models.Announcement;
import com.cursework.WebArtSell.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
