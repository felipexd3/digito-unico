package com.fb.onedigit.controller;

import com.fb.onedigit.models.builders.UserDTOBuilder;
import com.fb.onedigit.models.dtos.UserDTO;
import com.fb.onedigit.service.UserService;
import io.swagger.annotations.Api;
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
import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "Busca por um usuário específico")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna um usuário"),
        @ApiResponse(code = 204, message = "Usuário não encontrado"),
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "{uid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> findByUid(@PathVariable("uid") UUID uid, @RequestHeader("public-key") String publicKey) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.cryptUser(uid, publicKey));
    }

    @ApiOperation(value = "Cadastro de usuário")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Usuário cadastrado"),
        @ApiResponse(code = 409, message = "Usuário já cadastrado"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> insert(@Valid @RequestBody UserDTO userDTO) {
        var user = this.userService.insert(UserDTOBuilder.toEntity(userDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserDTOBuilder.fromEntity(user));
    }

    @ApiOperation(value = "Atualizar usuário")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Usuário atualizado"),
        @ApiResponse(code = 204, message = "Usuário não encontrado"),
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "{uid}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> update(@PathVariable("uid") UUID uid, @Valid @RequestBody UserDTO userDTO) {
        var user = this.userService.update(UserDTOBuilder.toEntity(userDTO, uid));
        return ResponseEntity.status(HttpStatus.OK).body(UserDTOBuilder.fromEntity(user));
    }


    @ApiOperation(value = "Remover usuário")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Usuário excluido com sucesso"),
        @ApiResponse(code = 422, message = "Usuário já excluído ou não existente")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{uid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity remove(@PathVariable("uid") UUID uid) {
        this.userService.remove(uid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
