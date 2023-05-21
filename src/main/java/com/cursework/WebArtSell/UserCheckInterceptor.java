package com.cursework.WebArtSell;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

import com.cursework.WebArtSell.Models.User;

public class UserCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || !user.getRole().equals("USER")) {
            response.sendRedirect("/authorisation");
            return false;
        }
        return true;
    }
}
