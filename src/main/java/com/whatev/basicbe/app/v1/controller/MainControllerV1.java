package com.whatev.basicbe.app.v1.controller;

import com.whatev.basicbe.app.v1.dto.response.UserAccountResponseV1;
import com.whatev.basicbe.app.v1.session.SessionConst;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;


@Controller
@RequestMapping("/api/v1")
@Tag(name = "Main", description = "메인 페이지")
public class MainControllerV1 {

    @Operation(summary = "homepage", description = "홈페이지(내정보 불러오기) (로그인 쿠키값 필요)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping(path = "/")
    public ResponseEntity<UserAccountResponseV1> homeLogin(
            @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) UserAccountResponseV1 userAccountResponseV1) throws HttpSessionRequiredException {
        if(userAccountResponseV1 == null) {
            throw new HttpSessionRequiredException("로그인 정보가 없습니다.");
        }

        return ResponseEntity.ok(userAccountResponseV1);
    }
}
