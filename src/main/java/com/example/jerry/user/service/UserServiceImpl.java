/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 7월 2일 JerryDEV All rights reserved.         │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성자 : Min Woo Song
작성일 : 2023-07-02
작성시간 : 오후 7:49
작성용도 : User business layer (service layer) Implements Class File
*/

package com.example.jerry.user.service;

import com.example.jerry.commons.annotation.LogException;
import com.example.jerry.user.domain.LoginDTO;
import com.example.jerry.user.domain.UserVo;
import com.example.jerry.user.persistance.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @LogException
    public void insertUser(UserVo param) {
        userDAO.insertUser(param);
    }

    /* 회원가입 아이디 중복 체크 */
    @Override
    public int isExistId(String user_id) {
        return userDAO.isExistId(user_id);
    }

    /* 회원가입 닉네임 중복체크 */
    @Override
    public int isExistNickName(String user_nickname) {
        return userDAO.isExistNickName(user_nickname);
    }

    /* 회원가입 휴대폰번호 중복체크 */
    @Override
    public int isExistPhoneNumber(String user_phone) {
        return userDAO.isExistPhoneNumber(user_phone);
    }

    /* 회원가입 이메일 중복 체크 */
    @Override
    public int isExistEmail(String user_email) {
        return userDAO.isExistEmail(user_email);
    }

    //  로그인
    @Override
    @LogException
    public UserVo login(LoginDTO loginDTO) {
        return userDAO.selectByIdAndPw(loginDTO);
    }
}
