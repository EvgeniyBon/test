package com.example.testWeb.controllers;

import com.example.testWeb.domain.Role;
import com.example.testWeb.domain.User;
import com.example.testWeb.repos.UserRepo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/user")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {
    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public String userList(Model model) {

        model.addAttribute("users", userRepo.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping()
    public String userSave(@RequestParam(name = "userId") User user,
                           @RequestParam Map<String, String> form,
                           @RequestParam String username
    ) {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        user.getRoles().clear();
        form.forEach((key, val) -> {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        });
        userRepo.save(user);
        return "redirect:/user";
    }
    @GetMapping("{user}/delete")
    public String deleteUser(@PathVariable User user){
        Optional<User> user1 = userRepo.findById(user.getId());
        user1.ifPresent(userRepo::delete);
        return "redirect:/user";
    }
}
