package com.fb.onedigit.controller;

import com.fb.onedigit.models.builders.UserDTOBuilder;
import com.fb.onedigit.models.dtos.UserDTO;
import com.fb.onedigit.service.UserService;
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

    @GetMapping(value = "{uid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> findByUid(@PathVariable("uid") UUID uid, @RequestHeader("public-key") String publicKey) {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.cryptUser(uid, publicKey));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> insert(@Valid @RequestBody UserDTO userDTO) {
        var user = this.userService.insert(UserDTOBuilder.toEntity(userDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserDTOBuilder.fromEntity(user));
    }

    @PutMapping(value = "{uid}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> update(@PathVariable("uid") UUID uid, @Valid @RequestBody UserDTO userDTO) {
        var user = this.userService.update(UserDTOBuilder.toEntity(userDTO, uid));
        return ResponseEntity.status(HttpStatus.OK).body(UserDTOBuilder.fromEntity(user));
    }


    @DeleteMapping(value = "{uid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity remove(@PathVariable("uid") UUID uid) {
        this.userService.remove(uid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
