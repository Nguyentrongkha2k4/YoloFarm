package com.web.yolofarm.service.authentication;

import com.web.yolofarm.dto.request.userLoginRequest;
import com.web.yolofarm.dto.response.AuthenticationResponse;

public interface IAuthenticationService {
    public AuthenticationResponse login(userLoginRequest request);
}
