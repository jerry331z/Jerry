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
import org.springframework.web.util.WebUtils;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
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
    @LogException
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
    @LogException
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
    @LogException
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
    @LogException
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
    @LogException
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
    public HashMap<String, Object> userLoginProcess(LoginDTO loginDTO, HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        UserVo sessionUser = userService.login(loginDTO);
        if (sessionUser != null) {
            String state = sessionUser.getUser_status();
            if (state.equals("Inactive")) {
                data.put("result", "out");
            } else {
                data.put("result", "success");
                session.setAttribute("sessionUser", sessionUser);
                /* 아이디 저장 */
                if (loginDTO.isSaveCookie()) {
                    // 아이디 저장 유효기간 : 30일
                    int amount = 60 * 60 * 24 * 30;
                    // 아이디저장 쿠키 객체 생성
                    Cookie saveIdCooke = new Cookie("setCookieYN", "Y");
                    Cookie userInputId = new Cookie("userInputId", loginDTO.getUser_id());
                    // 모든 경로에서 접근 가능하게 처리
                    saveIdCooke.setPath("/");
                    userInputId.setPath("/");
                    // 쿠키 유효 기간
                    saveIdCooke.setMaxAge(amount);
                    userInputId.setMaxAge(amount);
                    // 쿠키 저장
                    response.addCookie(saveIdCooke);
                    response.addCookie(userInputId);
                } else {
                    Cookie saveCookie = WebUtils.getCookie(request, "setCookieYN");
                    Cookie userInputId = WebUtils.getCookie(request, "userInputId");
                    if (saveCookie != null && userInputId != null) {
                        saveCookie.setPath("/");
                        userInputId.setPath("/");
                        // 쿠키 유효기간 0
                        saveCookie.setMaxAge(0);
                        userInputId.setMaxAge(0);
                        // 쿠키 저장
                        response.addCookie(saveCookie);
                        response.addCookie(userInputId);
                    }
                }
            }

            if (loginDTO.isUseCookie()) { // 로그인 유지 체크시
                // 로그인 유지 유효기간 : 일주일
                int amount = 60 * 60 * 24 * 7;
                // 로그인 유지 유효 일자
                Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount));
                // 로그인 유지 기간 갱신(아이디, 세션아이디, 유효일자)
                userService.keepLogin(sessionUser.getUser_id(), session.getId(), sessionLimit);

                // 로그인 쿠키 객체 생성
                Cookie loginCookie = new Cookie("loginCookie", session.getId());
                // 모든 경로에서 접근 가능하게 처리
                loginCookie.setPath("/");
                // 쿠키 유효 기간
                loginCookie.setMaxAge(60 * 60 * 24 * 7);
                // 쿠키 저장
                response.addCookie(loginCookie);
            }

            String url = (String) session.getAttribute("destination");
            String redirect = url != null ? url : "/";
            data.put("redirect", redirect);
        }
        return data;
    }

    //  유저 로그아웃
    @PostMapping(value = "userLogoutProcess")
    @LogException
    public HashMap<String, Object> userLogoutProcess(HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");

        Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
        if (loginCookie != null) {
            loginCookie.setPath("/");
            // 쿠키 유효기간 0
            loginCookie.setMaxAge(0);
            // 쿠키 저장
            response.addCookie(loginCookie);
            // 로그인 유지 갱신
            userService.keepLogin(sessionUser.getUser_id(), "none", new Date());
        }

        session.removeAttribute("sessionUser");
        session.invalidate();
        return data;
    }

    //  유저 아이디 찾기
    @PostMapping("getUserIdByNickNameAndEmail")
    @LogException
    public HashMap<String, Object> getUserIdByNickNameAndEmail(UserVo param) {

        HashMap<String, Object> userInfo = userService.getUserIdByNickNameAndEmail(param);

        if (userInfo == null) {
            data.put("result", "fail");
        } else {
            data.put("result", "success");
            data.put("userInfo", userInfo);
        }

        System.out.println(data.get("userInfo"));

        return data;
    }

    //  비밀번호 찾기 질문 조회
    @PostMapping(value = "getUserQuestionById")
    @LogException
    public HashMap<String, Object> getUserQuestionById(UserVo param) {

        HashMap<String, Object> userInfo = userService.getUserQuestionById(param);

        if (userInfo == null) {
            data.put("result", "fail");
        } else {
            data.put("userInfo", userInfo);
        }

        return data;
    }

    //  비밀번호 질문 답변 조회
    @PostMapping(value = "getUserPwByfindAnswer")
    @LogException
    public HashMap<String, Object> getUserPwByfindAnswer(UserVo param) {
        UserVo userInfo = userService.getUserPwByfindAnswer(param);

        if (userInfo == null) {
            data.put("result", "fail");
        } else {
            data.put("result", "success");
        }
        return data;
    }

    //  비밀번호 찾기 - 수정
    @PostMapping("getUserUpdatePw")
    @LogException
    public HashMap<String, Object> getUserUpdatePw(UserVo param) {
        boolean exist = userService.isExistId(param.getUser_id()) > 0;
        if (exist) {
            data.put("result", "success");
            userService.getUserUpdatePw(param);
        } else {
            data.put("result", "fail");
        }
        return data;
    }

    //  회원정보 변경 - 이메일 인증
    @PostMapping(value = "checkUserInfo")
    @LogException
    public HashMap<String, Object> checkUserInfo(String user_email) {
        Random random = new Random();
        int checkNum = random.nextInt(888888) + 111111;

        // 메일 제목, 내용
        String subject = "회원정보 변경 인증 메일입니다.";
        String content = "저희 사이트를 방문해주셔서 감사합니다." +
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

    //  회원정보 변경 - 변경 완료
    @PostMapping(value = "updateInfoUser")
    @LogException
    public HashMap<String, Object> updateInfoUser(UserVo param, HttpSession session) {

        UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");
        if (sessionUser != null) {
            data.put("result", "success");
            userService.updateUserInfoDate(param);
            session.removeAttribute("sessionUser");
        } else {
            data.put("result", "fail");
        }

        return data;
    }

    // 현재 비밀번호 체크
    @PostMapping("checkPw")
    @LogException
    public HashMap<String, Object> checkPw(String current_password, String user_id) {

        HashMap<String, Object> data = new HashMap<>();

        UserVo userVo = userService.getUser(user_id);

        if (userVo != null) {
            data.put("result", "success");
        } else {
            data.put("result", "fail");
        }

        return data;
    }

    //  비밀번호 수정
    @PostMapping(value = "modifyPassword")
    @LogException
    public HashMap<String, Object> modifyPassword(UserVo userVo, HttpSession session) {

        HashMap<String, Object> data = new HashMap<>();

        UserVo sessionUser = userService.getUser(userVo.getUser_id());

        if (sessionUser != null) {
            data.put("result", "success");
            /* 비밀번호 변경 */
            userService.modifyPassword(userVo.getUser_id(), userVo.getUser_pw());

            /* 비밀번호 변경후 로그아웃 */
            session.invalidate();
        } else {
            data.put("result", "fail");
        }

        return data;
    }

    //  회원탈퇴
    @PostMapping(value = "deleteUserInfoByUserNo")
    public HashMap<String, Object> deleteUserInfoByUserNo(UserVo vo, HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        UserVo sessionUser = (UserVo) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            data.put("result", "fail");
        } else {
            data.put("result", "success");
            userService.deleteUserInfoByUserNo(sessionUser);
            session.invalidate();
        }
        return data;
    }

}
