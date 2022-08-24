package com.mycalories.caloriesrest.controller;

import com.mycalories.caloriesrest.UserMapper;
import com.mycalories.caloriesrest.dto.UserDTO;
import com.mycalories.caloriesrest.service.UserService;
import com.mycalories.model2.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        try {
            User user = this.userService.createUser(userMapper.dtoToEntity(userDTO));
            if (user != null) {
                return ResponseEntity.status(HttpStatus.OK).body("User Registered Succesfully");
            }
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Username already exists");

    }
}
