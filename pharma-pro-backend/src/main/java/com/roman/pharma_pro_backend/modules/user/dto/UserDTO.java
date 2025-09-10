package com.roman.pharma_pro_backend.modules.user.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private UUID id;
    private String username;
    private String fullName;
    private String role;
    private LocalDateTime createdAt;
}
