package com.rookies5.myspringbootlab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test") // active profile이 'test'일 때만 활성화
public class TestConfig {

    @Bean
    public MyEnvironment myEnvironment() {
        // MyEnvironment와 같은 패키지에 있으므로 별도의 import 없이 사용 가능합니다.
        return MyEnvironment.builder()
                .mode("개발환경")
                .build();
    }
}