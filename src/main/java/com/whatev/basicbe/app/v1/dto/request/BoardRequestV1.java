package com.whatev.basicbe.app.v1.dto.request;

import com.whatev.basicbe.app.v1.domain.Board;
import com.whatev.basicbe.app.v1.domain.UserAccount;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BoardRequestV1 {

    @NotNull
    private String title;

    @NotNull
    private String content;

    public Board toEntity(UserAccount entity) {
        return Board.of(entity,
                title,
                content);
    }
}
