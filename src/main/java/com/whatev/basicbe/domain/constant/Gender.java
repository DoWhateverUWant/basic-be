package com.whatev.basicbe.domain.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.procedure.NoSuchParameterException;

import java.util.Arrays;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Gender {

    MALE("MALE", "남성"),
    FEMALE("FEMALE", "여성");

    private final String type;
    private final String value;

    public static Gender find(String value) {
        return Arrays.stream(Gender.values())
                .filter(v -> v.getValue().equals(value))
                .findAny()
                .orElseThrow(() -> new NoSuchParameterException(String.format("해당하는 %s이 존재하지 않습니다.", value)));
    }

}