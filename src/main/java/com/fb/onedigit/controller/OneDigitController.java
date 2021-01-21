package com.fb.onedigit.controller;

import com.fb.onedigit.models.dtos.OneDigitDTO;
import com.fb.onedigit.service.OneDigitService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("one-digit")
public class OneDigitController {
    private final OneDigitService oneDigitService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> findOneDigit(@Valid @RequestBody OneDigitDTO oneDigitDTO) {
        return new ResponseEntity<>(this.oneDigitService.repeatNumbers(oneDigitDTO), HttpStatus.OK);
    }
}
