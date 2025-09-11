package com.roman.pharma_pro_backend.modules.user.service;

import com.roman.pharma_pro_backend.common.ApiRequest;
import com.roman.pharma_pro_backend.modules.user.entity.UserEntity;
import org.springframework.data.domain.Page;

public interface UserService {
    UserEntity createUser(UserEntity user);
    Page<UserEntity> getUser(ApiRequest request);
}
