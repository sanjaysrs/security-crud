package com.srsanjay.twenty.controller;

import com.srsanjay.twenty.dto.UpdateUserDto;
import com.srsanjay.twenty.dto.UserDto;
import com.srsanjay.twenty.model.User;
import com.srsanjay.twenty.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    private String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/home")
    public String home(HttpSession session) {
        if (session.getAttribute("user")==null) {
            User user = userService.findByUsername(getCurrentUsername()).get();
            session.setAttribute("user", user);
        }
        return "home";
    }

    @GetMapping("/update")
    public String update(Model model) {
        User user = userService.findByUsername(getCurrentUsername()).orElse(null);
        model.addAttribute("user", new UpdateUserDto(user));
        return "update-profile";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("user") UpdateUserDto updateUserDto,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes,
                         HttpSession session) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("user", updateUserDto);
            return "update-profile";
        }

        userService.update(updateUserDto);
        User user = userService.findByUsername(getCurrentUsername()).get();
        session.setAttribute("user", user);
        redirectAttributes.addFlashAttribute("update", "Profile updated successfully");
        return "redirect:/home";
    }

}
