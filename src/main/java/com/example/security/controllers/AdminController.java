package com.example.security.controllers;

import com.example.security.entities.User;
import com.example.security.repositories.RoleRepository;
import com.example.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("list", userService.list());
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/list";
    }

    @GetMapping("/{id}")
    public String userById(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "admin/user_page";
    }

    @GetMapping("/create")
    public String createUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/new";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/edit";
    }

    @PatchMapping
    public String updateUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
