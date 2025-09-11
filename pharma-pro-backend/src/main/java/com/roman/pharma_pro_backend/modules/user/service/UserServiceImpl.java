package com.roman.pharma_pro_backend.modules.user.service;

import com.roman.pharma_pro_backend.common.ApiRequest;
import com.roman.pharma_pro_backend.common.Roles;
import com.roman.pharma_pro_backend.common.util.FilterPaginationHandler;
import com.roman.pharma_pro_backend.modules.auth.dto.RegisterRequest;
import com.roman.pharma_pro_backend.modules.user.entity.UserEntity;
import com.roman.pharma_pro_backend.modules.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FilterPaginationHandler<UserEntity> handler;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, FilterPaginationHandler<UserEntity> handler, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.handler = handler;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<?> registerUser(RegisterRequest request) {
        Optional<UserEntity> existing = userRepository.findByUsername(request.getUsername());
        if (existing.isPresent()) {
            return ResponseEntity.badRequest().body("Username already taken");
        }

        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole() == null ? Roles.SALESMAN.name() : request.getRole());
        userRepository.save(user);
        return ResponseEntity.ok("User successfully registered");
    }

    @Override
    public Page<UserEntity> getUser(ApiRequest request) {
        return handler.handle(request, userRepository, UserEntity.class);
    }

}
