package com.cursework.WebArtSell.Controllers;

import com.cursework.WebArtSell.Models.Comment;
import com.cursework.WebArtSell.Models.Product;
import com.cursework.WebArtSell.Models.User;
import com.cursework.WebArtSell.Repositories.CommentRepository;
import com.cursework.WebArtSell.Repositories.ProductRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/table-products/{productId}/comments")
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/add")
    public String addComment(@PathVariable long productId, @ModelAttribute Comment comment, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Product product = productRepository.findById(productId).orElseThrow();
        comment.setUser(user);
        comment.setProduct(product);
        comment.setCreationDate(LocalDateTime.now());
        commentRepository.save(comment);
        return "redirect:/table-products/" + productId;
    }
}
