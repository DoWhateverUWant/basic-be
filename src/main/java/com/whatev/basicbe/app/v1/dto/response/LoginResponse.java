package com.whatev.basicbe.app.v1.dto.response;

import com.whatev.basicbe.app.v1.domain.UserAccount;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LoginResponse {

    private String userId;

    private String email;

    private String nickname;

    private String name;

    private String gender;

    public static LoginResponse of(String userId, String email, String nickname, String name, String gender) {
        return new LoginResponse(userId, email, nickname, name, gender);
    }

    public static LoginResponse from(UserAccount entity) {
        return new LoginResponse(
                entity.getUserId(),
                entity.getEmail(),
                entity.getNickname(),
                entity.getName(),
                entity.getGender()
        );
    }
}
