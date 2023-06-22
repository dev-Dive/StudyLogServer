package com.devdive.studylog.auth.api.rest.dto;

import lombok.Builder;

@Builder
public record AuthenticationRequest(
        String email,
        String password
) {

}
