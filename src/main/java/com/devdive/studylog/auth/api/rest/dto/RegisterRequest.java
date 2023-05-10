package com.devdive.studylog.auth.api.rest.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record RegisterRequest(
        String email,
        @Size(min = 8, max = 20, message = "비밀번호의 길이는 {min}~{max} 사이입니다.")
        String password,
        String nickname
) {
}
