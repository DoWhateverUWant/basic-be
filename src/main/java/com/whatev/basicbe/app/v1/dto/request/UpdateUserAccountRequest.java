package com.whatev.basicbe.app.v1.dto.request;

import com.whatev.basicbe.app.v1.domain.UserAccount;
import com.whatev.basicbe.app.v1.domain.UserAccountChangedInfo;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UpdateUserAccountRequest {

    private String currUserPassword;

    private String changeUserPassword;

    private String confirmChangeUserPassword;

    @Email
    private String email;

    private String nickname;

    private String name;

    private String gender;

    public UserAccount toEntity(UserAccount entity) {
        return UserAccount.of(
                entity.getUserId(),
                entity.getUserPassword(),
                email,
                nickname,
                name,
                entity.getRrn11(),
                entity.getRrn12(),
                gender
        );
    }

    public UserAccountChangedInfo toInfoEntity(UserAccount entity) {
        UserAccount user = toEntity(entity);
        return UserAccountChangedInfo.of(
                user,
                email,
                nickname,
                name,
                user.getRrn11(),
                user.getRrn12(),
                gender
        );
    }
}
