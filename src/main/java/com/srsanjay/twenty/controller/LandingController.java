package com.srsanjay.twenty.controller;

import com.srsanjay.twenty.model.UserRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LandingController {

    public String getCurrentUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
        return authorities.get(0).getAuthority();
    }

    @GetMapping("/success")
    public String success() {

        if (getCurrentUserRole().equals(UserRole.ROLE_USER.name()))
            return "redirect:/home";

        if (getCurrentUserRole().equals(UserRole.ROLE_ADMIN.name()))
            return "redirect:/admin";

        return "redirect:/login";
    }

    @GetMapping("/")
    public String landing() {
        return "landing";
    }

}
