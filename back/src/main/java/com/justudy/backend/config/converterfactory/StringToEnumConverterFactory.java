package com.justudy.backend.config.converterfactory;

import com.justudy.backend.common.enum_util.EnumModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class StringToEnumConverterFactory implements ConverterFactory<String, Enum<? extends EnumModel>> {

    @Override
    public <T extends Enum<? extends EnumModel>> Converter<String, T> getConverter(Class<T> targetType) {
        if (EnumModel.class.isAssignableFrom(targetType)) {
            return new StringToEnumConverter<>(targetType);
        }
        return null;
    }

    private static final class StringToEnumConverter<T extends Enum<? extends EnumModel>>
            implements Converter<String, T> {

        private final Map<String, T> map;

        public StringToEnumConverter(Class<T> targetEnum) {
            T[] enumConstants = targetEnum.getEnumConstants();
            map = Arrays.stream(enumConstants)
                    .collect(Collectors.toMap(enumConstant ->
                            ((EnumModel) enumConstant).getValue(), Function.identity()));
        }

        @Override
        public T convert(String source) {
            if (!StringUtils.hasText(source)) {
                return null;
            }
            source = source.toLowerCase();

            T enumModel = map.get(source);
            if (enumModel == null) {
                throw new IllegalArgumentException("잘못된 타입입니다");
            }
            return enumModel;
        }
    }
}
