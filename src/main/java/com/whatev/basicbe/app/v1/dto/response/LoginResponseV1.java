package com.whatev.basicbe.app.v1.dto.response;

import com.whatev.basicbe.app.v1.domain.UserAccount;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LoginResponseV1 {

    private String userId;

    private String email;

    private String nickname;

    private String name;

    private String gender;

    public static LoginResponseV1 of(String userId, String email, String nickname, String name, String gender) {
        return new LoginResponseV1(userId, email, nickname, name, gender);
    }

    public static LoginResponseV1 from(UserAccount entity) {
        return new LoginResponseV1(
                entity.getUserId(),
                entity.getEmail(),
                entity.getNickname(),
                entity.getName(),
                entity.getGender()
        );
    }
}
