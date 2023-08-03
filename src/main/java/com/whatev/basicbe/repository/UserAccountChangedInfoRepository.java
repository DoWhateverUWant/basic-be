package com.whatev.basicbe.repository;

import com.whatev.basicbe.domain.UserAccountChangedInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountChangedInfoRepository extends JpaRepository<UserAccountChangedInfo, Long> {
}
