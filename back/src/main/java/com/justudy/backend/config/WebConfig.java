package com.justudy.backend.config;

import com.justudy.backend.config.converterfactory.StringToEnumConverterFactory;
import com.justudy.backend.config.interceptor.AdminCheckInterceptor;
import com.justudy.backend.config.interceptor.LoginCheckInterceptor;
import com.justudy.backend.member.repository.MemberRepository;
import io.swagger.models.HttpMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final MemberRepository memberRepository;

    @Bean
    public LoginCheckInterceptor loginInterceptor() {
        return new LoginCheckInterceptor(memberRepository);
    }

    @Bean
    public AdminCheckInterceptor adminCheckInterceptor() {
        return new AdminCheckInterceptor(memberRepository);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.HEAD.name(),
                        HttpMethod.OPTIONS.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.PATCH.name(),
                        HttpMethod.DELETE.name()
                )
                .allowCredentials(true);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        ConverterFactory converterFactory = new StringToEnumConverterFactory();
        registry.addConverterFactory(converterFactory);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(loginInterceptor())
        //         .addPathPatterns("/**")
        //         .excludePathPatterns("/api/login",
        //                 "/api/member/register",
        //                 "/api/member/check/**",
        //                 "/api/member/matter-most");

        // registry.addInterceptor(adminCheckInterceptor())
        //         .addPathPatterns("/api/admin/**");
    }
}
