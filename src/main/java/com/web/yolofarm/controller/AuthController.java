package com.web.yolofarm.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.yolofarm.dto.ResponseObject;
import com.web.yolofarm.dto.request.userLoginRequest;
import com.web.yolofarm.dto.response.AuthenticationResponse;
import com.web.yolofarm.service.authentication.IAuthenticationService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final IAuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseObject<AuthenticationResponse> postMethodName(@RequestBody userLoginRequest request) {
        ResponseObject<AuthenticationResponse> responseObject = ResponseObject.<AuthenticationResponse>builder().code(200).status(true).data(authenticationService.login(request)).build();
        return responseObject;
    }
    
}
