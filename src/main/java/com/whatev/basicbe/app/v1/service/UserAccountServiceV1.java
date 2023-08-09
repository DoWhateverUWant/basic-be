package com.whatev.basicbe.app.v1.service;

import com.whatev.basicbe.app.v1.domain.UserAccount;
import com.whatev.basicbe.app.v1.dto.request.UpdateUserAccountRequest;
import com.whatev.basicbe.app.v1.dto.request.UserAccountRequestV1;
import com.whatev.basicbe.app.v1.dto.request.LoginRequestV1;
import com.whatev.basicbe.app.v1.dto.response.UserAccountResponseV1;
import com.whatev.basicbe.app.v1.repository.UserAccountChangedInfoRepositoryV1;
import com.whatev.basicbe.app.v1.repository.UserAccountRepositoryV1;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAccountServiceV1 {

    private final UserAccountRepositoryV1 userAccountRepositoryV1;
    private final UserAccountChangedInfoRepositoryV1 userAccountChangedInfoRepositoryV1;

    // 회원 가입
    @Transactional
    public UserAccountResponseV1 saveUser(UserAccountRequestV1 userAccountRequestV1) {
        // 비밀 번호 체크
        if(!userAccountRequestV1.getUserPassword().equals(userAccountRequestV1.getUserConfirmPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 회원 정보 저장 및 로그용 DB에 저장
        UserAccount user = userAccountRepositoryV1.save(userAccountRequestV1.toEntity());
        userAccountChangedInfoRepositoryV1.save(userAccountRequestV1.toInfoEntity());

        return UserAccountResponseV1.from(user);
    }

    // 로그인
    @Transactional(readOnly = true)
    public UserAccountResponseV1 login(LoginRequestV1 loginRequestV1) {
        return userAccountRepositoryV1.findById(loginRequestV1.getUserId())
                .filter(user -> user.getUserPassword().equals(loginRequestV1.getUserPassword()))
                .map(UserAccountResponseV1::from)
                .orElseThrow(() -> new EntityNotFoundException("아이디 또는 비밀번호가 일치하지 않습니다."));
    }

    // 유저 조회 (userId)
    @Transactional(readOnly = true)
    public Optional<UserAccountResponseV1> searchUser(String userId) {
        return userAccountRepositoryV1.findById(userId)
                .map(UserAccountResponseV1::from);
    }

    // 유저 정보 수정
    @Transactional
    public UserAccountResponseV1 updateUser(String userId, UpdateUserAccountRequest userAccountRequest) {
        UserAccount userAccount = userAccountRepositoryV1.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("해당 유저의 정보가 없습니다."));

        String resultPassword = passwordCheck(userAccount.getUserPassword(), userAccountRequest);

        UserAccount updateUserAccount = UserAccount.of(
                userId,
                resultPassword,
                userAccountRequest.getEmail(),
                userAccountRequest.getNickname(),
                userAccountRequest.getName(),
                userAccount.getRrn11(),
                userAccount.getRrn12(),
                userAccountRequest.getGender()
        );

        UserAccount savedUser = userAccountRepositoryV1.save(updateUserAccount);
        userAccountChangedInfoRepositoryV1.save(userAccountRequest.toInfoEntity(savedUser));

        return UserAccountResponseV1.from(savedUser);
    }


    private static String passwordCheck(String currPassword, UpdateUserAccountRequest userAccountRequest) {
        String resultPassword = currPassword;

        // 입력받은 현재 비밀번호가 빈 문자열이 아니고
        if(userAccountRequest.getCurrUserPassword() != null) {

            // 입력받은 바꿀 비밀번호와 확인 비밀번호의 값이 같고, 입력받은 현재 비밀번호와 DB에서 추출한 현재 비밀번호가 같으면
            if(userAccountRequest.getChangeUserPassword().equals(userAccountRequest.getConfirmChangeUserPassword()) &&
                    userAccountRequest.getCurrUserPassword().equals(currPassword)) {
                resultPassword = userAccountRequest.getChangeUserPassword();
            }
        }

        return resultPassword;
    }
}
