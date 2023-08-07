package com.whatev.basicbe.app.v1.controller;

import com.whatev.basicbe.app.v1.dto.request.LoginRequestV1;
import com.whatev.basicbe.app.v1.dto.request.UserAccountRequestV1;
import com.whatev.basicbe.app.v1.dto.response.LoginResponseV1;
import com.whatev.basicbe.app.v1.dto.response.UserAccountResponseV1;
import com.whatev.basicbe.app.v1.service.UserAccountServiceV1;
import com.whatev.basicbe.app.v1.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserAccountControllerV1 {

    private final UserAccountServiceV1 userAccountServiceV1;

    // 회원 가입
    @PostMapping(path= "/signup")
    public ResponseEntity<UserAccountResponseV1> signup(@RequestBody UserAccountRequestV1 userAccountRequestV1) {
        UserAccountResponseV1 user = userAccountServiceV1.saveUser(userAccountRequestV1);

        return ResponseEntity.ok().body(user);
    }

    // 로그인
    @PostMapping(path = "/login")
    public String login(@RequestBody LoginRequestV1 loginRequestV1, HttpServletRequest request, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        LoginResponseV1 loginResponseV1 = userAccountServiceV1.login(loginRequestV1);

        if(loginResponseV1 == null) {
            bindingResult.reject("Login Fail", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "redirect:/api/v1";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_USER, loginResponseV1);
        session.setMaxInactiveInterval(7200);

        redirectAttributes.addAttribute("STATUS", "200");
        redirectAttributes.addAttribute("JSESSIONID", session.getId());

        return "redirect:/api/v1";
    }

    // 로그아웃
    @PostMapping(path = "/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if(session != null) {
            session.invalidate();
        }

        return "redirect:/api/v1";
    }
}
