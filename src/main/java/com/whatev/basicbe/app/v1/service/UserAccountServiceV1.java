package com.whatev.basicbe.app.v1.service;

import com.whatev.basicbe.app.v1.dto.UserAccountDtoV1;
import com.whatev.basicbe.app.v1.dto.request.LoginRequest;
import com.whatev.basicbe.app.v1.dto.response.LoginResponse;
import com.whatev.basicbe.app.v1.repository.UserAccountChangedInfoRepositoryV1;
import com.whatev.basicbe.app.v1.repository.UserAccountRepositoryV1;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class UserAccountServiceV1 {

    private final UserAccountRepositoryV1 userAccountRepositoryV1;
    private final UserAccountChangedInfoRepositoryV1 userAccountChangedInfoRepositoryV1;

    // 회원 가입
    @Transactional
    public void saveUser(UserAccountDtoV1 userAccountDtoV1) {
        // 비밀 번호 체크
        if(!userAccountDtoV1.getUserPassword().equals(userAccountDtoV1.getUserConfirmPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 회원 정보 저장 및 로그용 DB에 저장
        userAccountRepositoryV1.save(userAccountDtoV1.toEntity());
        userAccountChangedInfoRepositoryV1.save(userAccountDtoV1.toInfoEntity());
    }

    // 로그인
    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest loginRequest) {
        return userAccountRepositoryV1.findById(loginRequest.getUserId())
                .filter(user -> user.getUserPassword().equals(loginRequest.getUserPassword()))
                .map(LoginResponse::from)
                .orElseThrow(() -> new EntityNotFoundException("아이디 또는 비밀번호가 일치하지 않습니다."));
    }
}
