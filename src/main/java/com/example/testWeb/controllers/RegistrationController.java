package com.example.testWeb.controllers;

import com.example.testWeb.domain.Role;
import com.example.testWeb.domain.User;
import com.example.testWeb.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping("registration")
    public String registerMain (){
        return "registration";
    }

    @PostMapping("registration")
    public String registerNewUser(User user, Map<String,Object> model){
        System.out.println(user);
      User usr = userRepo.findByUsername(user.getUsername());
      if (usr!=null){
          model.put("message","User already exists!");
          return "registration";
      }
      user.setActive(true);
      user.setRoles(Collections.singleton(Role.USER));
      userRepo.save(user);
        return "redirect:/login";
    }
}
