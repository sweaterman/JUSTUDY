//package com.justudy.backend.config;
//
//import com.justudy.backend.config.interceptor.LoginCheckInterceptor;
//import com.justudy.backend.member.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@RequiredArgsConstructor
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    private final MemberRepository memberRepository;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor())
//                .order(1)
//                .addPathPatterns()
//                .excludePathPatterns("/", "/login");
//    }
//
//    @Bean
//    public LoginCheckInterceptor loginInterceptor() {
//        return new LoginCheckInterceptor(memberRepository);
//    }
//}
