package com.tech_raj.controller;

import com.tech_raj.dto.LoginRequest;
import com.tech_raj.response.ApiResponse;
import com.tech_raj.response.LoginResponse;
import com.tech_raj.service.interfac.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;

    @PostMapping("login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest){
        final LoginResponse loginResponse = service.loginUser(loginRequest);
        ApiResponse response= new ApiResponse("Login successfully",1,loginResponse);
        return  ResponseEntity.ok(response);
    }
}
