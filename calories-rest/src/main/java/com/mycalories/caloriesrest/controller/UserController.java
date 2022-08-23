package com.mycalories.caloriesrest.controller;

import com.mycalories.caloriesrest.dto.UserDTO;
import com.mycalories.caloriesrest.mapper.UserMapper;
import com.mycalories.caloriesrest.service.UserService;
import com.mycalories.model2.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/api/register")
    public ResponseEntity<HttpStatus> register(@RequestBody UserDTO userDTO) {
        try {

            User newUser = this.userService.createUser(userMapper.dtoToEntity(userDTO));
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }
}
