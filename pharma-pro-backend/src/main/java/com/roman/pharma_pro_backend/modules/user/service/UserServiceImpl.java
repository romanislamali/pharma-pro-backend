package com.roman.pharma_pro_backend.modules.user.service;

import com.roman.pharma_pro_backend.common.ApiRequest;
import com.roman.pharma_pro_backend.common.util.FilterPaginationHandler;
import com.roman.pharma_pro_backend.modules.user.entity.UserEntity;
import com.roman.pharma_pro_backend.modules.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FilterPaginationHandler<UserEntity> handler;

    public UserServiceImpl(UserRepository userRepository, FilterPaginationHandler<UserEntity> handler) {
        this.userRepository = userRepository;
        this.handler = handler;
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        user.setCreatedBy(user.getUsername());
        if(user.getId() != null){
            user.setUpdatedBy(user.getUsername());
        }
        return userRepository.save(user);
    }

    @Override
    public Page<UserEntity> getUser(ApiRequest request) {
        return handler.handle(request, userRepository, UserEntity.class);
    }

}
