package com.whatev.basicbe.app.v1.controller;

import com.whatev.basicbe.app.v1.dto.request.LoginRequestV1;
import com.whatev.basicbe.app.v1.dto.request.UpdateUserAccountRequest;
import com.whatev.basicbe.app.v1.dto.request.UserAccountRequestV1;
import com.whatev.basicbe.app.v1.dto.response.UserAccountResponseV1;
import com.whatev.basicbe.app.v1.service.UserAccountServiceV1;
import com.whatev.basicbe.app.v1.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserAccountControllerV1 {

    private final UserAccountServiceV1 userAccountServiceV1;

    // 회원 가입
    @PostMapping(path = "/signup")
    public ResponseEntity<UserAccountResponseV1> signup(@RequestBody UserAccountRequestV1 userAccountRequestV1) {
        UserAccountResponseV1 user = userAccountServiceV1.saveUser(userAccountRequestV1);

        return ResponseEntity.ok().body(user);
    }

    // 로그인
    @PostMapping(path = "/login")
    public String login(@RequestBody LoginRequestV1 loginRequestV1, HttpServletRequest request, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        UserAccountResponseV1 userAccountResponseV1 = userAccountServiceV1.login(loginRequestV1);

        if (userAccountResponseV1 == null) {
            bindingResult.reject("Login Fail", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "redirect:/api/v1";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_USER, userAccountResponseV1);
        session.setMaxInactiveInterval(7200);

        redirectAttributes.addAttribute("STATUS", "200");
        redirectAttributes.addAttribute("JSESSIONID", session.getId());

        return "redirect:/api/v1";
    }

    // 로그아웃
    @PostMapping(path = "/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return "redirect:/api/v1";
    }

    // 내 정보 조회
    @GetMapping(path = "/my-info")
    public ResponseEntity<UserAccountResponseV1> myInfo(
            @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) UserAccountResponseV1 userAccountResponseV1) throws HttpSessionRequiredException {

        if (userAccountResponseV1 == null) {
            throw new HttpSessionRequiredException("로그인 정보가 없습니다.");
        }

        return ResponseEntity.ok().body(userAccountResponseV1);
    }

    // 내 정보 수정
    @PutMapping(path = "/my-info")
    public ResponseEntity<?> updateMyInfo(@RequestBody UpdateUserAccountRequest userAccountRequest, HttpServletRequest request,
                                          @SessionAttribute(name = SessionConst.LOGIN_USER, required = false) UserAccountResponseV1 responseV1) throws HttpSessionRequiredException {

        if (responseV1 == null) {
            throw new HttpSessionRequiredException("로그인 정보가 없습니다.");
        }

        UserAccountResponseV1 userAccountResponseV1 = userAccountServiceV1.updateUser(responseV1.getUserId(), userAccountRequest);

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_USER, userAccountResponseV1);
        session.setMaxInactiveInterval(7200);

        return ResponseEntity.status(HttpStatus.OK).body(userAccountResponseV1);
    }
}