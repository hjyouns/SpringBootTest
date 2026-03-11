package com.rookies5.myspringbootlab.runner;

import com.rookies5.myspringbootlab.config.MyPropProperties;
import com.rookies5.myspringbootlab.domain.MyEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyPropRunner implements CommandLineRunner {

    // 1-4) @Value 방식
    @Value("${myprop.username}")
    private String username;

    // 1-5) Properties 객체 방식
    private final MyPropProperties myPropProperties;

    // 1-6) Profile별 Bean 주입
    private final MyEnvironment myEnvironment;

    @Override
    public void run(String... args) throws Exception {
        log.info("********** [실습 1 결과 리포트] **********");

        // 1-7 요구사항: System.out 대신 log 메서드 사용
        log.info("1. @Value로 읽은 username: {}", username);
        log.debug("2. 객체로 읽은 port (Debug 로그): {}", myPropProperties.getPort());
        log.info("3. 현재 설정된 환경 모드: {}", myEnvironment.getMode());

        log.info("****************************************");
    }
}