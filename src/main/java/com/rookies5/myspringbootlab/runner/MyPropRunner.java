package com.rookies5.myspringbootlab.runner;

import com.rookies5.myspringbootlab.config.MyEnvironment;
import com.rookies5.myspringbootlab.property.MyPropProperties; // 수정된 패키지 경로 반영
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyPropRunner implements CommandLineRunner {

    @Value("${myprop.username}")
    private String username;

    // 1-5) property 패키지의 MyPropProperties 주입
    private final MyPropProperties myPropProperties;

    // 1-6) config 패키지의 MyEnvironment 주입
    private final MyEnvironment myEnvironment;

    @Override
    public void run(String... args) throws Exception {
        log.info("********** [실습 1 결과 리포트] **********");

        log.info("1. @Value로 읽은 username: {}", username);

        // Debug 로그 출력 (application-test.properties 설정 시 확인 가능)
        log.debug("2. 객체로 읽은 port (Debug 로그): {}", myPropProperties.getPort());

        log.info("3. 현재 설정된 환경 모드: {}", myEnvironment.getMode());

        log.info("****************************************");
    }
}