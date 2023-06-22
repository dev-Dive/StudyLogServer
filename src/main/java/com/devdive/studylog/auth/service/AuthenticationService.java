package com.devdive.studylog.auth.service;

import com.devdive.studylog.auth.api.rest.dto.AuthenticationRequest;
import com.devdive.studylog.auth.api.rest.dto.AuthenticationResponse;
import com.devdive.studylog.auth.api.rest.dto.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
