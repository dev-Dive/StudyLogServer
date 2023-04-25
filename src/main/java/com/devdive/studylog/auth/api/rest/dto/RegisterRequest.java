package com.devdive.studylog.auth.api.rest.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record RegisterRequest(
        String email,
        @Size(min = 8, max = 20)
        String password,
        String nickname
) {
}
