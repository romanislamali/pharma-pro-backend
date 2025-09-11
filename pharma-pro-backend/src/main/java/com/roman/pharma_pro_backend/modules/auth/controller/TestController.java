package com.roman.pharma_pro_backend.modules.auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {


    @GetMapping("/hello")
    public String hello() {
        return "Hello authenticated user";
    }


    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping("/admin")
    public String admin() {
        return "Hello admin";
    }
}
