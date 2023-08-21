package com.whatev.basicbe.app.v1.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@ToString(callSuper = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class UserAccountChangedInfo {

    @Id
    @Column(nullable = false, name = "no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @ManyToOne(targetEntity = UserAccount.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    @ToString.Exclude
    private UserAccount userAccount;

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

    @Column(nullable = false, length = 10, name = "gender")
    private String gender;

    public static UserAccountChangedInfo of(UserAccount userAccount, String email, String nickname, String name, String rrn11, String rrn12, String gender) {
        return new UserAccountChangedInfo(null, userAccount, email, nickname, name, rrn11, rrn12, gender);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccountChangedInfo userAccountChangedInfo)) return false;
        return no != null && Objects.equals(no, userAccountChangedInfo.no);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no);
    }
}
