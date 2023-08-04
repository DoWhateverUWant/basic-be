package com.whatev.basicbe.app.v1.dto;

import com.whatev.basicbe.app.v1.domain.UserAccount;
import com.whatev.basicbe.app.v1.domain.UserAccountChangedInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserAccountDtoV1 {

    @NotNull(message = "ID를 작성해 주세요.")
    private String userId;

    @NotNull(message = "비밀번호를 작성해 주세요.")
    @Size(min = 4, max = 25, message = "비밀번호는 4자 이상, 25자 미만으로 작성하셔야 합니다.")
    private String userPassword;

    @NotNull(message = "확인 비밀번호를 작성해 주세요.")
    @Size(min = 4, max = 25, message = "비밀번호는 4자 이상, 25자 미만으로 작성하셔야 합니다.")
    private String userConfirmPassword;

    @NotNull(message = "이메일을 작성해 주세요.")
    @Email
    private String email;

    @NotNull(message = "닉네임을 작성해 주세요.")
    @Size(min = 1, max = 25, message = "닉네임은 1자 이상, 25자 미만으로 작성하셔야 합니다.")
    private String nickname;

    @NotNull(message = "이름을 작성해 주세요.")
    private String name;

    @NotNull(message = "주민번호 앞자리를 작성해 주세요.")
    private String rrn11;

    @NotNull(message = "주민번호 뒷자리를 작성해 주세요.")
    private String rrn12;

    @NotNull(message = "성별을 선택해 주세요.")
    private String gender;

    public static UserAccountDtoV1 from(UserAccount entity) {
        return new UserAccountDtoV1(
                entity.getUserId(),
                entity.getUserPassword(),
                entity.getUserPassword(),
                entity.getEmail(),
                entity.getNickname(),
                entity.getName(),
                entity.getRrn11(),
                entity.getRrn12(),
                entity.getGender()
        );
    }

    public UserAccount toEntity() {
        return UserAccount.of(
                userId,
                userPassword,
                email,
                nickname,
                name,
                rrn11,
                rrn12,
                gender
        );
    }

    public UserAccountChangedInfo toInfoEntity() {
        return UserAccountChangedInfo.of(
                toEntity(),
                email,
                nickname,
                name,
                rrn11,
                rrn12,
                gender
        );
    }
}
