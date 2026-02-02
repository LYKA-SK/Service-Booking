package com.mindvault.online_service.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindvault.online_service.entities.User;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/me")
    public User me(@AuthenticationPrincipal User user) {
        return user; // returns the currently logged-in user
    }
}