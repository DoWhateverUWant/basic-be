package com.whatev.basicbe.config;

import com.whatev.basicbe.app.v1.controller.UserAccountControllerV1;
import com.whatev.basicbe.app.v1.repository.UserAccountChangedInfoRepositoryV1;
import com.whatev.basicbe.app.v1.repository.UserAccountRepositoryV1;
import com.whatev.basicbe.app.v1.service.UserAccountServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class AppV1Config {

    private final UserAccountRepositoryV1 userAccountRepositoryV1;
    private final UserAccountChangedInfoRepositoryV1 userAccountChangedInfoRepositoryV1;

    @Bean
    public UserAccountControllerV1 userAccountControllerV1() {
        return new UserAccountControllerV1(userAccountServiceV1());
    }

    @Bean
    public UserAccountServiceV1 userAccountServiceV1() {
        return new UserAccountServiceV1(userAccountRepositoryV1, userAccountChangedInfoRepositoryV1);
    }

}
