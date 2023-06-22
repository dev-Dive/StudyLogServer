package com.devdive.studylog.auth.api.rest.dto;

import lombok.*;

@Builder
public record AuthenticationResponse(
        String token
) {

}
