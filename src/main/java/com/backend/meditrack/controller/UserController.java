package com.backend.meditrack.controller;

import com.backend.meditrack.dto.RegisterRequest;
import com.backend.meditrack.entity.User;
import com.backend.meditrack.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@Valid  @RequestBody RegisterRequest request){
        return  userService.registerUser(request);
    }
}
