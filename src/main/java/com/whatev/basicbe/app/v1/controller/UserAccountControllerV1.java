package com.whatev.basicbe.app.v1.controller;

import com.whatev.basicbe.app.v1.dto.request.LoginRequestV1;
import com.whatev.basicbe.app.v1.dto.request.UpdateUserAccountRequest;
import com.whatev.basicbe.app.v1.dto.request.UserAccountRequestV1;
import com.whatev.basicbe.app.v1.dto.response.UserAccountResponseV1;
import com.whatev.basicbe.app.v1.service.UserAccountServiceV1;
import com.whatev.basicbe.app.v1.session.SessionConst;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "UserAccount", description = "유저 관련 API Document")
public class UserAccountControllerV1 {

    private final UserAccountServiceV1 userAccountServiceV1;

    // 회원 가입
    @Operation(summary = "Sign Up", description = "회원가입 하기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserAccountResponseV1.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @Parameters({
            @Parameter(name = "userId", description = "아이디", example = "hong1234"),
            @Parameter(name = "userPassword", description = "비밀번호", example = "hgd1234!"),
            @Parameter(name = "userConfirmPassword", description = "확인 비밀번호", example = "hgd1234!"),
            @Parameter(name = "email", description = "이메일", example = "hong@gmail.com"),
            @Parameter(name = "nickname", description = "닉네임", example = "홍당무"),
            @Parameter(name = "name", description = "이름", example = "홍길동"),
            @Parameter(name = "rrn11", description = "주민번호 앞자리", example = "990909"),
            @Parameter(name = "rrn12", description = "주민번호 뒷자리", example = "1030509"),
            @Parameter(name = "gender", description = "성별", example = "MALE")
    })
    @PostMapping(path = "/signup")
    public ResponseEntity<UserAccountResponseV1> signup(@RequestBody UserAccountRequestV1 userAccountRequestV1) {
        UserAccountResponseV1 user = userAccountServiceV1.saveUser(userAccountRequestV1);

        return ResponseEntity.ok().body(user);
    }

    @Operation(summary = "Login", description = "로그인")
    @ApiResponses({
            @ApiResponse(responseCode = "302", description = "REDIRECTION"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @Parameters({
            @Parameter(name = "userId", description = "아이디", example = "hong1234"),
            @Parameter(name = "userPassword", description = "비밀번호", example = "hgd1234!")
    })
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
    @Operation(summary = "Logout", description = "로그아웃")
    @ApiResponses({
            @ApiResponse(responseCode = "302", description = "REDIRECTION"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping(path = "/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return "redirect:/api/v1";
    }

    @Operation(summary = "get my info", description = "내 정보 불러오기 (로그인 쿠키값 필요)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
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
    @Operation(summary = "Put my info", description = "내 정보 수정하기 (로그인 쿠키값 필요)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = UserAccountResponseV1.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @Parameters({
            @Parameter(name = "currUserPassword", description = "현재 비밀번호", example = "hgd1234!"),
            @Parameter(name = "changeUserPassword", description = "바꿀 비밀번호", example = "hhh1111!"),
            @Parameter(name = "confirmChangeUserPassword", description = "확인 비밀번호", example = "hhh1111!"),
            @Parameter(name = "email", description = "이메일", example = "hong@gmail.com"),
            @Parameter(name = "nickname", description = "닉네임", example = "홍당무"),
            @Parameter(name = "name", description = "이름", example = "홍길동"),
            @Parameter(name = "gender", description = "성별", example = "MALE")
    })
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