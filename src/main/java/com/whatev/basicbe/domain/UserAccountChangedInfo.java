package com.whatev.basicbe.domain;

import com.whatev.basicbe.domain.constant.converter.GenderTypeConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

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

    @Convert(converter = GenderTypeConverter.class)
    @Column(nullable = false, length = 10, name = "gender")
    private String gender;

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
