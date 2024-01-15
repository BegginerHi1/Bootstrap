package com.example.security.controllers;

import com.example.security.entities.User;
import com.example.security.services.RoleService;
import com.example.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping
    public String list(Model model, Principal principal) {
        model.addAttribute(userService.findByEmail(principal.getName()));
        model.addAttribute("newUser", new User());
        model.addAttribute("list", userService.list());
        model.addAttribute("roles", roleService.findAll());
        return "admin/list";
    }

    @PatchMapping
    public String saveUpdateUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
