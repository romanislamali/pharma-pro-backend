package com.roman.pharma_pro_backend.modules.user.service;

import com.roman.pharma_pro_backend.common.ApiRequest;
import com.roman.pharma_pro_backend.modules.user.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> registerOrUpdateUser(UserEntity request);
    Page<UserEntity> getUser(ApiRequest request);
}
