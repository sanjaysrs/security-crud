package com.srsanjay.twenty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/failure")
    public String login(RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("error", "Invalid username or password");
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("logout", "You have been successfully logged out.");
        return "redirect:/login";
    }

}
