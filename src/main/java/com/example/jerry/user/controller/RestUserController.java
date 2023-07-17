/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 7월 3일 JerryDEV All rights reserved.         │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성자 : Min Woo Song
작성일 : 2023-07-03
작성시간 : PM 6:21
작성용도 : user web page client request processing rest controller file
*/

package com.example.jerry.user.controller;

import com.example.jerry.commons.annotation.LogException;
import com.example.jerry.user.domain.LoginDTO;
import com.example.jerry.user.domain.UserVo;
import com.example.jerry.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Random;

@RestController
@RequestMapping(value = "/user/*")
public class RestUserController {

    HashMap<String, Object> data = new HashMap<>();

    private final UserService userService;
    private final JavaMailSender javaMailSender;

    @Autowired
    public RestUserController(UserService userService, JavaMailSender javaMailSender) {
        this.userService = userService;
        this.javaMailSender = javaMailSender;
    }

    /* 회원가입 아이디 중복 체크 */
    @PostMapping(value = "isExistId")
    public HashMap<String, Object> isExistId(String user_id) {

        int check = userService.isExistId(user_id);
        if (check > 0) {
            data.put("result", "fail");
        } else {
            data.put("result", "success");
        }

        return data;
    }

    /* 회원가입 닉네임 중복 체크 */
    @PostMapping(value = "isExistNickName")
    public HashMap<String, Object> isExistNickName(String user_nickname) {

        int check = userService.isExistNickName(user_nickname);

        if (check > 0) {
            data.put("result", "fail");
        } else {
            data.put("result", "success");
        }

        return data;
    }

    /* 회원가입 휴대폰번호 중복 체크 */
    @PostMapping(value = "isExistPhoneNumber")
    public HashMap<String, Object> isExistPhoneNumber(String user_phone) {

        int check = userService.isExistPhoneNumber(user_phone);

        if (check > 0) {
            data.put("result", "fail");
        } else {
            data.put("result", "success");
        }

        return data;
    }

    /* 회원가입 이메일 중복 체크 */
    @PostMapping(value = "isExistEmail")
    public HashMap<String, Object> isExistEmail(String user_email) {

        int check = userService.isExistEmail(user_email);

        if (check > 0) {
            data.put("result", "fail");
        } else {
            data.put("result", "success");
        }

        return data;
    }

    /* 이메일 유효성 검증 */
    @PostMapping(value = "checkEmail")
    public HashMap<String, Object> checkEmail(String user_email) {

        Random random = new Random();
        int checkNum = random.nextInt(888888) + 111111;

        // 메일 제목, 내용
        String subject = "회원가입 인증 메일입니다.";
        String content = "홈페이지를 방문해주셔서 감사합니다." +
                "인증 번호는 " + checkNum + " 입니다." +
                "\r\n" +
                "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";

        // 보내는 사람
        String from = "hanbyeols333z@gmail.com";

        try {
            // 메일 내용 넣을 객체와, 이를 도와주는 Helper 객체 생성
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail, "UTF-8");

            // 메일 내용을 채워줌
            mailHelper.setFrom(from, "관리자");    // 보내는 사람 셋팅
            mailHelper.setTo(user_email);        // 받는 사람 셋팅
            mailHelper.setSubject(subject);    // 제목 셋팅
            mailHelper.setText(content);    // 내용 셋팅

            // 메일 전송
            javaMailSender.send(mail);

        } catch (Exception e) {
            e.printStackTrace();
        }

        data.put("code", checkNum);

        return data;
    }

    //  유저로그인
    @PostMapping(value = "userLoginProcess")
    @LogException
    public HashMap<String, Object> userLoginProcess(LoginDTO loginDTO, HttpSession session) {

        UserVo sessionUser = userService.login(loginDTO);
        if (sessionUser != null) {
            String state = sessionUser.getUser_status();
            if (state.equals("Inactive")) {
                data.put("result", "out");
            } else {
                data.put("result", "success");
                session.setAttribute("sessionUser", sessionUser);
            }
        }

        return data;
    }

    //  유저 로그아웃
    @PostMapping(value = "userLogoutProcess")
    @LogException
    public HashMap<String, Object> userLogoutProcess(HttpSession session) {

        UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");
        session.removeAttribute("sessionUser");
        session.invalidate();
        return data;
    }
}
