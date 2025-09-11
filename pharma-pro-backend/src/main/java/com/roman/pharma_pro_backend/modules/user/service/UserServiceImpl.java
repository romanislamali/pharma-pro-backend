package com.roman.pharma_pro_backend.modules.user.service;

import com.roman.pharma_pro_backend.common.ApiRequest;
import com.roman.pharma_pro_backend.common.Roles;
import com.roman.pharma_pro_backend.common.util.FilterPaginationHandler;
import com.roman.pharma_pro_backend.modules.user.entity.UserEntity;
import com.roman.pharma_pro_backend.modules.user.repository.UserRepository;
import jakarta.transaction.Transactional;
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
    @Transactional
    public ResponseEntity<?> registerOrUpdateUser(UserEntity requestUser) {
        Optional<UserEntity> existingOpt = userRepository.findByUsername(requestUser.getUsername());

        if (requestUser.getId() != null) {
            Optional<UserEntity> userOpt = userRepository.findById(requestUser.getId());
            if (userOpt.isEmpty()) {
                return ResponseEntity
                        .badRequest()
                        .body("User not found with id " + requestUser.getId());
            }

            UserEntity user = userOpt.get();

            if (existingOpt.isPresent() && !existingOpt.get().getId().equals(user.getId())) {
                return ResponseEntity
                        .badRequest()
                        .body("Username already taken");
            }

            user.setUsername(requestUser.getUsername());

            if (requestUser.getPassword() != null && !requestUser.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(requestUser.getPassword()));
            }

            if (requestUser.getRole() != null) {
                user.setRole(requestUser.getRole());
            }

            userRepository.save(user);
            return ResponseEntity.ok("User successfully updated");

        } else {
            if (existingOpt.isPresent()) {
                return ResponseEntity
                        .badRequest()
                        .body("Username already taken");
            }

            if (requestUser.getPassword() == null || requestUser.getPassword().isEmpty()) {
                return ResponseEntity
                        .badRequest()
                        .body("Password cannot be empty");
            }

            requestUser.setPassword(passwordEncoder.encode(requestUser.getPassword()));

            if (requestUser.getRole() == null) {
                requestUser.setRole(Roles.SALESMAN.name());
            }

            userRepository.save(requestUser);
            return ResponseEntity.ok("User successfully registered");
        }
    }

    @Override
    public Page<UserEntity> getUser(ApiRequest request) {
        return handler.handle(request, userRepository, UserEntity.class);
    }

}
