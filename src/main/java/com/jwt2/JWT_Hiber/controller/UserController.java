package com.jwt2.JWT_Hiber.controller;


import com.jwt2.JWT_Hiber.entry.User;
import com.jwt2.JWT_Hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController
{


    @Autowired
    private UserService userService;


    @GetMapping
    public List<User> getAllData() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return userService.getAllData(authentication.getName());
    }
}
