package com.roman.pharma_pro_backend.modules.user.service;

import com.roman.pharma_pro_backend.common.ApiRequest;
import com.roman.pharma_pro_backend.modules.user.dto.UserDTO;
import com.roman.pharma_pro_backend.modules.user.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User createUser(User user);
    Page<User> getUser(ApiRequest request);
}
