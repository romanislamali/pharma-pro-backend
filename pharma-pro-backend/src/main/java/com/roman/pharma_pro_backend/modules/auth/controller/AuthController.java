package com.roman.pharma_pro_backend.modules.auth.controller;

import com.roman.pharma_pro_backend.common.ApiPaths;
import com.roman.pharma_pro_backend.common.Roles;
import com.roman.pharma_pro_backend.common.security.CustomUserDetailsService;
import com.roman.pharma_pro_backend.common.security.JwtUtil;
import com.roman.pharma_pro_backend.modules.auth.dto.RegisterRequest;
import com.roman.pharma_pro_backend.modules.auth.dto.AuthRequest;
import com.roman.pharma_pro_backend.modules.auth.dto.AuthResponse;
import com.roman.pharma_pro_backend.modules.user.entity.UserEntity;
import com.roman.pharma_pro_backend.modules.user.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(ApiPaths.AUTH)
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    private final CustomUserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(ApiPaths.LOGIN)
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
