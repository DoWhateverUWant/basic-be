package com.whatev.basicbe.app.v1.controller;

import com.whatev.basicbe.app.v1.dto.response.LoginResponse;
import com.whatev.basicbe.app.v1.session.SessionConst;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;


@Controller
public class MainControllerV1 {

    @GetMapping("/")
    public ResponseEntity<LoginResponse> homeLogin(
            @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) LoginResponse loginResponse) throws HttpSessionRequiredException {
        if(loginResponse == null) {
            throw new HttpSessionRequiredException("세션의 값이 일치하지 않습니다.");
        }

        return ResponseEntity.ok(loginResponse);
    }
}
