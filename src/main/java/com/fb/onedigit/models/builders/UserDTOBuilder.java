package com.fb.onedigit.models.builders;


import com.fb.onedigit.models.User;
import com.fb.onedigit.models.dtos.UserDTO;

import java.util.UUID;

public class UserDTOBuilder {
    public static UserDTO fromEntity(User entity) {
        return UserDTO.builder()
            .uid(entity.getUid())
            .name(entity.getName())
            .email(entity.getEmail())
            .build();
    }

    public static User toEntity(UserDTO dto) {
        return User.builder()
            .name(dto.getName())
            .email(dto.getEmail())
            .build();
    }

    public static User toEntity(UserDTO dto, UUID uid) {
        return User.builder()
            .uid(uid)
            .name(dto.getName())
            .email(dto.getEmail())
            .build();
    }
}
