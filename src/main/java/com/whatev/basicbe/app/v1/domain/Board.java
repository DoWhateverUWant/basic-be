package com.whatev.basicbe.app.v1.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Entity
public class Board {

    @Id
    @Column(nullable = false, name = "no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @ManyToOne(targetEntity = UserAccount.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private UserAccount userAccount;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = true, name = "content")
    private String content;

    public static Board of (UserAccount userAccount, String title, String content) {
        return new Board(null, userAccount, title, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board board)) return false;
        return no != null && Objects.equals(no, board.no);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no);
    }
}
