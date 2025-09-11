package com.roman.pharma_pro_backend.modules.user.service;

import com.roman.pharma_pro_backend.common.ApiRequest;
import com.roman.pharma_pro_backend.modules.auth.dto.RegisterRequest;
import com.roman.pharma_pro_backend.modules.user.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> registerUser(RegisterRequest request);
    Page<UserEntity> getUser(ApiRequest request);
}
