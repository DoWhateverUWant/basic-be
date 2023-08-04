package com.whatev.basicbe.app.v1.repository;

import com.whatev.basicbe.app.v1.domain.UserAccountChangedInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountChangedInfoRepositoryV1 extends JpaRepository<UserAccountChangedInfo, Long> {
}
