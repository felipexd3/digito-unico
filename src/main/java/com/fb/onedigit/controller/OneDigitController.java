package com.fb.onedigit.controller;

import com.fb.onedigit.models.dtos.OneDigitDTO;
import com.fb.onedigit.service.OneDigitService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("one-digit")
public class OneDigitController {
    private final OneDigitService oneDigitService;

    @ApiOperation(value = "Busca por um usuário específico")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna o digito único de um determinado numero"),
        @ApiResponse(code = 400, message = "Um ou mais parâmetros não foram preenchidos"),
        @ApiResponse(code = 422, message = "Usuario não encontrado"),
    })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> findOneDigit(@Valid @RequestBody OneDigitDTO oneDigitDTO) {
        return new ResponseEntity<>(this.oneDigitService.processNumber(oneDigitDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Busca por um usuário específico")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna uma lista de digitos únicos de um determinado usuário"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "user/{userUid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OneDigitDTO>> findAllByUser(@PathVariable("userUid")UUID userUid) {
        return new ResponseEntity(this.oneDigitService.findAllByUser(userUid), HttpStatus.OK);
    }
}
