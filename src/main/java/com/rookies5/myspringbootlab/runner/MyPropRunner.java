package com.rookies5.myspringbootlab.runner;

import com.rookies5.myspringbootlab.config.MyPropProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j; // 로그를 위한 라이브러리
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j // 이 어노테이션 하나로 'log' 객체를 바로 사용할 수 있습니다
@Component
@RequiredArgsConstructor
public class MyPropRunner implements CommandLineRunner {

    @Value("${myprop.username}")
    private String username;

    @Value("${myprop.port}")
    private int port;

    private final MyPropProperties myPropProperties;

    @Override
    public void run(String... args) throws Exception {
        log.info("==================================");
        log.debug("[방법 1] @Value 방식 - Username: {}, Port: {}", username, port);

        log.info("----------------------------------");

        log.debug("[방법 2] Properties 객체 방식 - Username: {}, Port: {}",
                myPropProperties.getUsername(),
                myPropProperties.getPort());
        log.info("==================================");
    }
}