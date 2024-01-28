package com.srsanjay.twenty.controller;

import com.srsanjay.twenty.dto.UserDto;
import com.srsanjay.twenty.model.User;
import com.srsanjay.twenty.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    UserService userService;

    @GetMapping
    public String register(Model model) {
        if (!model.containsAttribute("user"))
            model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping
    public String register(@Valid @ModelAttribute("user") UserDto userDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           HttpSession session) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", userDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            return "redirect:/register";
        }

        if (userExists(userDto.getUsername())) {
            redirectAttributes.addFlashAttribute("error", "Username " + userDto.getUsername() + " is not available");
            redirectAttributes.addFlashAttribute("user", userDto);
            return "redirect:/register";
        }

        userService.save(userDto);
        UUID token = generateToken();
        session.setAttribute("token", token);
        redirectAttributes.addFlashAttribute("token", token);
        return "redirect:/register/confirm";
    }

    @GetMapping("/confirm")
    public String confirmRegistration(HttpSession session, Model model) {

        if (isValidToken(session.getAttribute("token"), model.getAttribute("token"))) {
            session.removeAttribute("token");
            return "confirm-register";
        }

        return "redirect:/";

    }

    private boolean userExists(String username) {
        Optional<User> existing = userService.findByUsername(username);
        return existing.isPresent();
    }

    private UUID generateToken() {
        return UUID.randomUUID();
    }

    private boolean isValidToken(Object sessionToken, Object modelToken) {
        return  (sessionToken != null && sessionToken.equals(modelToken));
    }

}
