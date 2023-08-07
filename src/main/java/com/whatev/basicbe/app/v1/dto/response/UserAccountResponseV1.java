package com.whatev.basicbe.app.v1.dto.response;

import com.whatev.basicbe.app.v1.domain.UserAccount;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserAccountResponseV1 {

    private String userId;

    private String email;

    private String nickname;

    private String name;

    private String gender;

    public static UserAccountResponseV1 from(UserAccount entity) {
        return new UserAccountResponseV1(
                entity.getUserId(),
                entity.getEmail(),
                entity.getNickname(),
                entity.getName(),
                entity.getGender()
        );
    }
}
