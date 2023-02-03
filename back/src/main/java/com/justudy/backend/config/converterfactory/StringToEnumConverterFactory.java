package com.justudy.backend.config.converterfactory;

import com.justudy.backend.common.enum_util.EnumModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Arrays;

public class StringToEnumConverterFactory implements ConverterFactory<String, Enum<? extends EnumModel>> {

    @Override
    public <T extends Enum<? extends EnumModel>> Converter<String, T> getConverter(Class<T> targetType) {
        return null;
    }

    private static final class StringToEnumConverter<T extends Enum<? extends EnumModel>>
            implements Converter<String, T> {

        private final Class<T> enumType;
        private final boolean isEnumModel;

        public StringToEnumConverter(Class<T> enumType) {
            this.enumType = enumType;
            this.isEnumModel = Arrays.stream(enumType.getInterfaces())
                    .anyMatch(i -> i == EnumModel.class);
        }

        @Override
        public T convert(String source) {
            if (source.isEmpty()) {
                return null;
            }

            T[] constants = enumType.getEnumConstants();
            for (T constant : constants) {
                if (isEnumModel && ((EnumModel) constant).getValue().equals(source.trim())) {
                    return constant;
                }
            }
            return null;
        }
    }
}
