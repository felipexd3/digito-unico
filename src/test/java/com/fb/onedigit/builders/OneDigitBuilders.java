package com.fb.onedigit.builders;

import com.fb.onedigit.models.dtos.OneDigitDTO;

public class OneDigitBuilders {

    public static OneDigitDTO builder(String number, Integer exp) {
        return OneDigitDTO.builder()
            .number(number)
            .exp(exp)
            .build();
    }
}
