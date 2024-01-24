package com.srsanjay.twenty.controller;

import com.srsanjay.twenty.model.User;
import com.srsanjay.twenty.model.UserRole;
import com.srsanjay.twenty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String adminPanel(Model model) {
        List<User> users = userService.findByRole(UserRole.ROLE_USER);
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
        userService.deleteById(id);
        redirectAttributes.addFlashAttribute("action", "User deleted successfully");
        return "redirect:/admin";
    }

    @GetMapping("/disable")
    public String disableUser(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
        userService.disableById(id);
        redirectAttributes.addFlashAttribute("action", "User disabled successfully");
        return "redirect:/admin";
    }

    @GetMapping("/enable")
    public String enableUser(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
        userService.enableById(id);
        redirectAttributes.addFlashAttribute("action", "User enabled successfully");
        return "redirect:/admin";
    }

}
