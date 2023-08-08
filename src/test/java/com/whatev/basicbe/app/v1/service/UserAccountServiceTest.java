package com.whatev.basicbe.app.v1.service;

import com.whatev.basicbe.app.v1.domain.UserAccount;
import com.whatev.basicbe.app.v1.dto.request.UserAccountRequestV1;
import com.whatev.basicbe.app.v1.dto.response.UserAccountResponseV1;
import com.whatev.basicbe.app.v1.repository.UserAccountRepositoryV1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 유저(V1)")
@ExtendWith(MockitoExtension.class)
public class UserAccountServiceTest {

    @InjectMocks private UserAccountServiceV1 userAccountServiceV1;

    @Mock private UserAccountRepositoryV1 userAccountRepositoryV1;

    @MockitoSettings(strictness = Strictness.WARN)
    @DisplayName("유저 정보를 입력 하면, 새로운 유저의 정보를 저장 한다.")
    @Test
    void givenUserParams_whenSaving_thenSaveUserAccount() {

        UserAccount userAccount = createUserAccount("test");

        // given
        given(userAccountRepositoryV1.save(userAccount)).willReturn(userAccount);

        // when
        UserAccountResponseV1 result = userAccountServiceV1.saveUser(
                UserAccountRequestV1.from(userAccount));

        // then
        assertThat(result)
                .hasFieldOrPropertyWithValue("userId", userAccount.getUserId())
                .hasFieldOrPropertyWithValue("email", userAccount.getEmail())
                .hasFieldOrPropertyWithValue("nickname", userAccount.getNickname())
                .hasFieldOrPropertyWithValue("name", userAccount.getName())
                .hasFieldOrPropertyWithValue("gender", userAccount.getGender());
        then(userAccountRepositoryV1).should().save(userAccount);
    }

    @DisplayName("존재하는 유저 ID를 검색하면, 회원 데이터를 Optional로 반환한다.")
    @Test
    void givenExistentUserId_whenSearching_thenReturnOptionalUserData() {

        // given
        String userId = "test";
        given(userAccountRepositoryV1.findById(userId)).willReturn(Optional.of(createUserAccount(userId)));

        // when
        Optional<UserAccountResponseV1> result = userAccountServiceV1.searchUser(userId);

        // then
        assertThat(result).isPresent();
        then(userAccountRepositoryV1).should().findById(userId);
    }

    @DisplayName("존재하지 않는 유저 ID를 검색하면, 비어있는 Optional을 반환한다.")
    @Test
    void givenNonExistentUserId_whenSearching_thenReturnOptionalUserData() {

        // given
        String userId = "non-user";
        given(userAccountRepositoryV1.findById(userId)).willReturn(Optional.empty());

        // when
        Optional<UserAccountResponseV1> result = userAccountServiceV1.searchUser(userId);

        // then
        assertThat(result).isEmpty();
        then(userAccountRepositoryV1).should().findById(userId);
    }

    private UserAccount createUserAccount(String userId) {
        return UserAccount.of(
                userId,
                "test1234",
                "test@gmail.com",
                "테스트",
                "김테스트",
                "000101",
                "3011111",
                "FEMALE"
        );
    }
}
