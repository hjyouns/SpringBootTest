package com.rookies5.myspringbootlab.config;

import com.rookies5.myspringbootlab.domain.MyEnvironment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test") // 연습문제 요구사항대로 "test"로 설정
public class DevConfig {
    @Bean
    public MyEnvironment myEnvironment() {
        return MyEnvironment.builder().mode("개발환경").build();
    }
}