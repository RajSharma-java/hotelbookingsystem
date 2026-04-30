package com.tech_raj.controller;

import com.tech_raj.dto.SignUpResponse;
import com.tech_raj.dto.UserDto;
import com.tech_raj.service.interfac.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // register user
    @PostMapping("register")
    public ResponseEntity<SignUpResponse> registerUser(@RequestBody UserDto userDto){
        final SignUpResponse userDto1 = userService.userRegistration(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

}
