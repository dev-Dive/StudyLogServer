package com.devdive.studylog.auth.api.rest.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

import static com.devdive.studylog.validation.ValidationGroups.*;

@Builder
public record RegisterRequest(
        @NotBlank(message = "이메일을 입력해주세요.", groups = First.class)
        @Email(message = "이메일 형식에 맞지 않습니다.")
        String email,
        @Size(min = 8, max = 20)
        String password,
        @NotBlank(message = "닉네임을 입력해주세요.", groups = First.class)
        @Size(min = 2, max = 14, message = "닉네임의 길이는 {min} ~ {max} 사이입니다.", groups = Second.class)
        @Pattern(regexp = "^[a-zA-Z가-힇0-9]+$", message = "닉네임은 한글, 영어, 숫자만 사용할 수 있습니다.")
        String nickname
) {
}
