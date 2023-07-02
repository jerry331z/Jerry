/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 7월 2일 JerryDEV All rights reserved.         │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성자 : Min Woo Song
작성일 : 2023-07-02
작성시간 : 오후 7:48
작성용도 : User business layer (service layer) Interface Class File
*/

package com.example.jerry.user.service;


import com.example.jerry.user.domain.UserVo;

public interface UserService {

    /* 회원가입 호출 프로세스 */
    public void insertUser(UserVo userVo);
}
