package com.fb.onedigit.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OneDigitDTO {
    @NotEmpty
    private String number;
    @Max(100000)
    private Integer exp;
    private Integer digit;
    private UUID userUid;
}
