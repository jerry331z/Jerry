/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 7월 30일 JerryDEV All rights reserved.        │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성자 : Min Woo Song
작성일 : 2023-07-30
작성시간 : 오후 10:40
작성용도 : 
*/

package com.example.jerry.commons.interceptor;
import com.example.jerry.user.domain.UserVo;
import com.example.jerry.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AutoLoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
        // 로그인 유지를 위한 쿠키가 존재하면
        if (loginCookie != null) {
            UserVo userVO = userService.checkLoginBefore(loginCookie.getValue());
            // session에 로그인 정보 저장
            if (userVO != null) {
                session.setAttribute("sessionUser", userVO);
            }
        }
        return true;
    }
}