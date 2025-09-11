package com.roman.pharma_pro_backend.modules.user.controller;

import com.roman.pharma_pro_backend.common.ApiRequest;
import com.roman.pharma_pro_backend.modules.user.entity.UserEntity;
import com.roman.pharma_pro_backend.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        UserEntity savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/list")
    public ResponseEntity<?> getUser (@RequestBody ApiRequest request) {
        if(request.getService().equalsIgnoreCase("USER")){
            return ResponseEntity.ok(userService.getUser(request));
        }
        return ResponseEntity.badRequest().body("Unsupported service");
    }

}
