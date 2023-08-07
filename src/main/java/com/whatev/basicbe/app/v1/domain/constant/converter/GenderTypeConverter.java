package com.whatev.basicbe.app.v1.domain.constant.converter;

import com.whatev.basicbe.app.v1.domain.constant.Gender;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.AttributeConverter;

import java.util.Objects;

public class GenderTypeConverter implements AttributeConverter<Gender, String> {
    @Override
    public String convertToDatabaseColumn(Gender attribute) {
        if(Objects.isNull(attribute)) return null;

        return attribute.getType();
    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        if(StringUtils.isBlank(dbData)) return null;

        return Gender.find(dbData);
    }
}
