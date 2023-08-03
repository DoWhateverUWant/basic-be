package com.whatev.basicbe.domain;

import com.whatev.basicbe.domain.constant.converter.GenderTypeConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Table(indexes = {
        @Index(columnList = "email", unique = true),
        @Index(columnList = "created_at"),
        @Index(columnList = "created_by")
})
@Entity
public class UserAccount extends AuditingFields{

    @Id
    @Column(nullable = false, length = 50, name = "user_id")
    private String userId;

    @Column(nullable = false, length = 255, name = "user_password")
    private String userPassword;

    @Column(nullable = false, length = 100, name = "email")
    private String email;

    @Column(nullable = false, length = 50, name = "nickname")
    private String nickname;

    @Column(nullable = false, length = 50, name = "name")
    private String name;

    @Column(nullable = false, length = 50, name = "rrn11")
    private String rrn11;

    @Column(nullable = false, length = 50, name = "rrn12")
    private String rrn12;

    @Convert(converter = GenderTypeConverter.class)
    @Column(nullable = false, length = 10, name = "gender")
    private String gender;

    public static UserAccount of(String userId, String userPassword, String email, String nickname, String name, String rrn11, String rrn12, String gender) {
        return new UserAccount(userId, userPassword, email, nickname, name, rrn11, rrn12, gender);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount userAccount)) return false;
        return userId != null && Objects.equals(userId, userAccount.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
