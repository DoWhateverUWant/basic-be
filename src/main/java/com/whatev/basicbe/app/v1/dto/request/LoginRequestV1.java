package com.whatev.basicbe.app.v1.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LoginRequestV1 {

    @NotNull(message = "아이디를 입력하세요")
    private String userId;

    @NotNull(message = "비밀번호를 입력하세요")
    private String userPassword;
}
