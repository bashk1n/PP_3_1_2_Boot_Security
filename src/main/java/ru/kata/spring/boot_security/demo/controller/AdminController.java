package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getUsers(Model model) {
        model.addAttribute("allUsers", userService.getAll());
        return "admin";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/add")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("allRoles", userService.getAllRoles());
        return "add";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add";
        }
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/update")
    public String edit(Model model, @RequestParam("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("allRoles", userService.getAllRoles());
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "update";
        }
        userService.save(user);
        return "redirect:/admin";
    }
}
