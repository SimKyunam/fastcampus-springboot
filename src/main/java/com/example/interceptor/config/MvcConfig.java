package com.example.interceptor.config;

import com.example.interceptor.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;
    //private final SomethingInterceptor somethingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //interceptor를 여러개 지정할 수 있다. 메소드 순서대로 실행
        
        registry.addInterceptor(authInterceptor).addPathPatterns("/api/private/*");
        //registry.addInterceptor(somethingInterceptor).addPathPatterns("/api/private/*");
    }
}
