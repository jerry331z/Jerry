/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 7월 2일 JerryDEV All rights reserved.         │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성자 : Min Woo Song
작성일 : 2023-07-02
작성시간 : 오후 7:51
작성용도 : User Repository layer (Data Access Object) Interface Class File
*/

package com.example.jerry.user.persistance;

import com.example.jerry.user.domain.LoginDTO;
import com.example.jerry.user.domain.UserVo;

public interface UserDAO {

    /* 회원가입 프로세스 */
    void insertUser(UserVo param);

    /* 회원가입 아이디 중복 체크 */
    int isExistId(String user_id);

    /* 회원가입 닉네임 중복 체크 */
    int isExistNickName(String user_nickname);

    /* 회원가입 휴대폰번호 중복 체크 */
    int isExistPhoneNumber(String user_phone);

    /* 회원가입 이메일 중복 체크 */
    public int isExistEmail(String user_email);

    //  로그인
    public UserVo selectByIdAndPw(LoginDTO loginDTO);

    //  최종 로그인 시간 업데이트
    public void updateLastConnectionDate(LoginDTO loginDTO);
}
