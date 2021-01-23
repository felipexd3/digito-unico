package com.fb.onedigit.builders;

import com.fb.onedigit.models.dtos.UserDTO;

public class UserBuilders {

    public static UserDTO builder() {
        return UserDTO.builder()
            .name("Felipe Batista")
            .email("fbfelipe91@gmail.com")
            .build();
    }

    public static UserDTO builderWithError() {
        return UserDTO.builder()
            .email("fbfelipe91@gmail.com")
            .build();
    }
}
