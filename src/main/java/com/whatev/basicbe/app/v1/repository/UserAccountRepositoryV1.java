package com.whatev.basicbe.app.v1.repository;

import com.whatev.basicbe.app.v1.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepositoryV1 extends JpaRepository<UserAccount, String> {
}
