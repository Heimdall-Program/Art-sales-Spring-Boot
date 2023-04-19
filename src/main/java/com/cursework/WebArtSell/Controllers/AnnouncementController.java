package com.cursework.WebArtSell.Controllers;

import com.cursework.WebArtSell.Models.Announcement;
import com.cursework.WebArtSell.Repo.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/announcement")
public class AnnouncementController {
    @Autowired
    private AnnouncementRepository announcementRepository;

    @GetMapping
    public String getAllAnnouncements(Model model) {
        List<Announcement> posts = (List<Announcement>) announcementRepository.findAll();
        model.addAttribute("posts", posts);
        return "announcement";
    }

    @GetMapping("/add")
    public String addAnnouncement() {
        return "announcement-add";
    }

    @PostMapping("/add")
    public String createAnnouncement(@ModelAttribute Announcement announcement) {
        announcementRepository.save(announcement);
        return "redirect:/announcement";
    }

    @GetMapping("/{id}")
    public String getAnnouncementById(@PathVariable long id, Model model) {
        Optional<Announcement> announcement = announcementRepository.findById(id);
        if (announcement.isPresent()) {
            model.addAttribute("announcement", announcement.get());
            return "announcement-details";
        }
        return "redirect:/announcement";
    }

    @GetMapping("/{id}/edit")
    public String editAnnouncement(@PathVariable long id, Model model) {
        Optional<Announcement> announcement = announcementRepository.findById(id);
        if (announcement.isPresent()) {
            model.addAttribute("announcement", announcement.get());
            return "announcement-edit";
        }
        return "redirect:/announcement";
    }

    @PostMapping("/{id}/edit")
    public String updateAnnouncement(@PathVariable long id, @ModelAttribute Announcement updatedAnnouncement) {
        Announcement announcement = announcementRepository.findById(id).orElseThrow();
        announcement.setTitle(updatedAnnouncement.getTitle());
        announcement.setAnons(updatedAnnouncement.getAnons());
        announcement.setFull_text(updatedAnnouncement.getFull_text());
        announcementRepository.save(announcement);
        return "redirect:/announcement";
    }

    @PostMapping("/{id}/remove")
    public String deleteAnnouncement(@PathVariable long id) {
        announcementRepository.deleteById(id);
        return "redirect:/announcement";
    }
}
