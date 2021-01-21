package com.fb.onedigit.service;

import com.fb.onedigit.models.dtos.OneDigitDTO;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class OneDigitService {

    public Integer repeatNumbers(OneDigitDTO oneDigitDTO) {
        return this.findOneDigit(oneDigitDTO.getNumber().repeat(oneDigitDTO.getExp()));
    }

    public Integer findOneDigit(String numbers) {
        var digit = Stream.of(numbers.split("")).
            mapToInt(Integer::parseInt)
            .sum();
        if (digit > 9) {
            return this.findOneDigit(String.valueOf(digit));
        }
        return digit;
    }
//    public Integer findOneDigit(String number, Integer exp) {
//        int digit = 0;
//        var numbers = number.repeat(exp);
//        while (numbers.length() > 1) {
//            digit = Stream.of(numbers
//                    .split(""))
//                    .mapToInt(Integer::parseInt)
//                    .reduce(0, Integer::sum);
//            numbers = String.valueOf(digit);
//        }
//        return digit;
//    }
}
