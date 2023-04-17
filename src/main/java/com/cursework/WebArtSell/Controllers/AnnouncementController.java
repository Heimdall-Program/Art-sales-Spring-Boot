package com.cursework.WebArtSell.Controllers;

import com.cursework.WebArtSell.Models.Announcement;
import com.cursework.WebArtSell.Repo.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnnouncementController {
    @Autowired
    private AnnouncementRepository announcementRepository;

    @GetMapping("/announcement")
    public String announcementModel(Model model) {
        Iterable<Announcement> posts = announcementRepository.findAll();
        model.addAttribute("posts", posts);
        return "announcement";
    }
}
