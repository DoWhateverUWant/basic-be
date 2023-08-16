package com.whatev.basicbe.app.v1.repository;

import com.whatev.basicbe.app.v1.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepositoryV1 extends JpaRepository<Board, Long> {
}
