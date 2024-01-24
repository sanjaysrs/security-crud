package com.srsanjay.twenty.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.List;

@Controller
public class HomeController {

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
    public String home() {
        return "home";
    }

}
