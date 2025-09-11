package com.roman.pharma_pro_backend.modules.user.controller;

import com.roman.pharma_pro_backend.common.ApiPaths;
import com.roman.pharma_pro_backend.common.ApiRequest;
import com.roman.pharma_pro_backend.common.Services;
import com.roman.pharma_pro_backend.modules.user.entity.UserEntity;
import com.roman.pharma_pro_backend.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiPaths.API+Services.USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'MANAGER')")
    @PostMapping(ApiPaths.SAVE)
    public ResponseEntity<?> registerUser(@Validated @RequestBody UserEntity request) {
        return userService.registerOrUpdateUser(request);
    }

//    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'MANAGER')")
    @PostMapping(ApiPaths.UPDATE)
    public ResponseEntity<?> updateUser(@Validated @RequestBody UserEntity request) {
        return userService.registerOrUpdateUser(request);
    }

//    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'MANAGER')")
    @PostMapping(ApiPaths.CHANGE_STATUS)
    public ResponseEntity<?> changeUserStatus(@Validated @RequestBody UserEntity request) {
        return userService.registerOrUpdateUser(request);
    }

//    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN', 'MANAGER')")
    @PostMapping(ApiPaths.LIST)
    public ResponseEntity<?> getUsers(@RequestBody ApiRequest request) {
        if(request.getService().equalsIgnoreCase(Services.USER)){
            return ResponseEntity.ok(userService.getUser(request));
        }
        return ResponseEntity.badRequest().body("Unsupported service");
    }

}
