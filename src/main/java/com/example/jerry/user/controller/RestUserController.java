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

import com.example.jerry.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/user/*")
public class RestUserController {

    HashMap<String, Object> data = new HashMap<>();

    private final UserService userService;

    @Autowired
    public RestUserController(UserService userService) {
        this.userService = userService;
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
}
