package com.justudy.backend.common.enum_util;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class EnumController {

    private final EnumMapper enumMapper;

    @GetMapping("/enum")
    public Map<String, List<EnumValue>> getEnumValue() {
        return enumMapper.getAll();
    }
}
