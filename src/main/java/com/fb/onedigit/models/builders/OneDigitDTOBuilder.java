package com.fb.onedigit.models.builders;

import com.fb.onedigit.models.OneDigit;
import com.fb.onedigit.models.User;
import com.fb.onedigit.models.dtos.OneDigitDTO;

public class OneDigitDTOBuilder {
    public static OneDigitDTO fromEntity(OneDigit entity) {
        return OneDigitDTO.builder()
            .digit(entity.getDigit())
            .exp(entity.getExp())
            .number(entity.getNumber())
            .userUid(entity.getUser().getUid())
            .build();
    }

    public static OneDigit toEntity(OneDigitDTO dto, Integer digit, User user) {
        return OneDigit.builder()
            .digit(digit)
            .exp(dto.getExp())
            .number(dto.getNumber())
            .user(user)
            .build();
    }
}
