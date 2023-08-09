package com.whatev.basicbe.app.v1.controller;

import com.whatev.basicbe.app.v1.dto.response.UserAccountResponseV1;
import com.whatev.basicbe.app.v1.session.SessionConst;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;


@Controller
@RequestMapping("/api/v1")
public class MainControllerV1 {

    @GetMapping(path = "/")
    public ResponseEntity<UserAccountResponseV1> homeLogin(
            @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) UserAccountResponseV1 userAccountResponseV1) throws HttpSessionRequiredException {
        if(userAccountResponseV1 == null) {
            throw new HttpSessionRequiredException("로그인 정보가 없습니다.");
        }

        return ResponseEntity.ok(userAccountResponseV1);
    }
}
