package com.whatev.basicbe.app.v1.service;

import com.whatev.basicbe.app.v1.dto.request.BoardRequestV1;
import com.whatev.basicbe.app.v1.repository.BoardRepositoryV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardServiceV1 {

    private BoardRepositoryV1 boardRepositoryV1;

    // 게시글 작성
    @Transactional
    public void saveBoard(BoardRequestV1 boardRequestV1, String userId) {

    }

}
