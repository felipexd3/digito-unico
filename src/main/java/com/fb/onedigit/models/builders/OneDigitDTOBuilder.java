package com.fb.onedigit.models.builders;

import com.fb.onedigit.models.OneDigit;
import com.fb.onedigit.models.User;
import com.fb.onedigit.models.dtos.OneDigitDTO;

import java.util.List;
import java.util.stream.Collectors;

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

    public static List<OneDigitDTO> fromEntity(List<OneDigit> entity) {
        return entity.stream()
            .map(OneDigitDTOBuilder::fromEntity)
            .collect(Collectors.toList());
    }
}
