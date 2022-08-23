package com.mycalories.caloriesrest.controller;

import com.mycalories.caloriesrest.UserMapper;
import com.mycalories.caloriesrest.dto.UserDTO;
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
    public User register(@RequestBody UserDTO userDTO) {
        try {
            return this.userService.createUser(userMapper.dtoToEntity(userDTO));
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return null;

    }
}
