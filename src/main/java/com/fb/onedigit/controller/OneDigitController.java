package com.fb.onedigit.controller;

import com.fb.onedigit.models.OneDigit;
import com.fb.onedigit.service.OneDigitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("v1/one-digit")
public class OneDigitController {
    private final OneDigitService oneDigitService;

    @PostMapping
    public ResponseEntity<Integer> findOneDigit(@Valid @RequestBody OneDigit oneDigit) {
        return new ResponseEntity<>(this.oneDigitService.repeatNumbers(oneDigit.getNumber(), oneDigit.getExp()), HttpStatus.OK);
    }
}
