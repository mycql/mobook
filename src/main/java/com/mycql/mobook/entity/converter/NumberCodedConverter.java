package com.mycql.mobook.entity.converter;

import com.mycql.mobook.entity.NumberCoded;
import jakarta.persistence.AttributeConverter;

import java.util.Objects;
import java.util.stream.Stream;

public class NumberCodedConverter<T extends NumberCoded> implements AttributeConverter<T, Integer> {

    private T target;

    public NumberCodedConverter(T target) {
        this.target = target;
    }
    @Override
    public Integer convertToDatabaseColumn(T value) {
        return Objects.isNull(value) ? null : value.getCode();
    }

    @Override
    public T convertToEntityAttribute(Integer code) {
        if (Objects.isNull(code)) {
            return null;
        }
        T[] values = target.getValues();
        return Stream.of(values)
            .filter(c -> c.getCode() == code)
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }
}
