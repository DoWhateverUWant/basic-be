package com.whatev.basicbe.app.v1.controller;

import com.whatev.basicbe.app.v1.dto.UserAccountDtoV1;
import com.whatev.basicbe.app.v1.dto.request.LoginRequest;
import com.whatev.basicbe.app.v1.dto.response.LoginResponse;
import com.whatev.basicbe.app.v1.service.UserAccountServiceV1;
import com.whatev.basicbe.app.v1.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
public class UserAccountControllerV1 {

    private final UserAccountServiceV1 userAccountServiceV1;

    // 회원 가입
    @PostMapping(path= "/signup")
    public ResponseEntity<UserAccountDtoV1> signup(@RequestBody UserAccountDtoV1 userAccountDtoV1) {
        userAccountServiceV1.saveUser(userAccountDtoV1);

        return ResponseEntity.ok().build();
    }

    // 로그인
    @PostMapping(path = "/login")
    public String login(@RequestBody LoginRequest loginRequest, HttpServletRequest request, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        LoginResponse loginResponse = userAccountServiceV1.login(loginRequest);

        if(loginResponse == null) {
            bindingResult.reject("Login Fail", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "redirect:/";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_USER, loginResponse);
        session.setMaxInactiveInterval(7200);

        redirectAttributes.addAttribute("STATUS", "200");
        redirectAttributes.addAttribute("JSESSIONID", session.getId());

        return "redirect:/";
    }
}
