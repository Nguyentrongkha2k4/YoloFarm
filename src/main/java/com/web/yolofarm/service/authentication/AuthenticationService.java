package com.web.yolofarm.service.authentication;

import org.springframework.stereotype.Service;

import com.web.yolofarm.component.JwtAuthentication;
import com.web.yolofarm.dto.request.userLoginRequest;
import com.web.yolofarm.dto.response.AuthenticationResponse;
import com.web.yolofarm.entity.User;
import com.web.yolofarm.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService{
    private final UserRepository userRepository;
    private final JwtAuthentication jwtAuthentication;

    public AuthenticationResponse login(userLoginRequest request){
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        AuthenticationResponse authenticationResponse = jwtAuthentication.authenticated(request.getPassword(), user);
        return authenticationResponse;
    }
}
