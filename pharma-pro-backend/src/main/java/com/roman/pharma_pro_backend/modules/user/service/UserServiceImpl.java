package com.roman.pharma_pro_backend.modules.user.service;

import com.roman.pharma_pro_backend.common.ApiRequest;
import com.roman.pharma_pro_backend.common.FilterPaginationHandler;
import com.roman.pharma_pro_backend.modules.user.entity.User;
import com.roman.pharma_pro_backend.modules.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final FilterPaginationHandler<User> handler;

    public UserServiceImpl(UserRepository userRepository, FilterPaginationHandler<User> handler) {
        this.userRepository = userRepository;
        this.handler = handler;
    }

    @Override
    public User createUser(User user) {
        user.setCreatedBy(user.getUsername());
        if(user.getId() != null){
            user.setUpdatedBy(user.getUsername());
        }
        return userRepository.save(user);
    }

    @Override
    public Page<User> getUser(ApiRequest request) {
        return handler.handle(request, userRepository, User.class);
    }

}
