package com.srsanjay.twenty.controller;

import com.srsanjay.twenty.model.User;
import com.srsanjay.twenty.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    public String getCurrentUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().toString();
    }

    @GetMapping("/success")
    public String success() {

        if (getCurrentUserRole().equals("[ROLE_USER]"))
            return "redirect:/home";

        if (getCurrentUserRole().equals("[ROLE_ADMIN]"))
            return "redirect:/admin";

        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Principal principal) {
        User user = userService.findByUsername(principal.getName()).get();
        session.setAttribute("user", user);
        return "home";
    }

}
