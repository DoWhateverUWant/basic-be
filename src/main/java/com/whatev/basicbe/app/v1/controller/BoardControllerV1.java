package com.whatev.basicbe.app.v1.controller;

import com.whatev.basicbe.app.v1.domain.constant.SearchType;
import com.whatev.basicbe.app.v1.service.BoardServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BoardControllerV1 {

    private final BoardServiceV1 boardServiceV1;

    // 전체 게시글 조회
    @GetMapping(path = "/boards")
    public ResponseEntity<?> boards(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue
            ) {


    }

    // 단일 게시글 조회
    @GetMapping(path = "/board/{boardId}")
    public ResponseEntity<?> board(@PathVariable Long boardId) {


    }

    
    // 게시글 작성


    // 게시글 삭제


    // 게시글 수정
}
