/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 7월 2일 JerryDEV All rights reserved.         │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성자 : Min Woo Song
작성일 : 2023-07-02
작성시간 : 오후 7:00
작성용도 : user web page client request processing controller file
*/

package com.example.jerry.user.controller;

import com.example.jerry.user.domain.UserVo;
import com.example.jerry.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user/*")
public class UserController {

    @Autowired
    UserService userService;

    /* 회원가입 페이지 */
    @GetMapping(value = "register")
    public String register() {
        return "user/register";
    }

    /* 회원가입 프로세스 호출 */
    @PostMapping(value = "insertUserProcess")
    public String insertUserProcess(UserVo param) {
        userService.insertUser(param);

        return "redirect:../";
    }
}
