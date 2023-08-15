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


import com.example.jerry.user.domain.LoginDTO;
import com.example.jerry.user.domain.QuestionVo;
import com.example.jerry.user.domain.UserVo;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface UserService {

    /* 회원가입 호출 프로세스 */
    void insertUser(UserVo userVo);

    /* 회원가입 아이디 중복 체크 */
    int isExistId(String user_id);

    /* 회원가입 닉네임 중복 체크 */
    int isExistNickName(String user_nickname);

    /* 회원가입 휴대폰번호 중복 체크 */
    int isExistPhoneNumber(String user_phone);

    /* 회원가입 이메일 중복 체크 */
    int isExistEmail(String user_email);

    //  로그인
    public UserVo login(LoginDTO loginDTO);

    //  아이디 찾기
    public HashMap<String, Object> getUserIdByNickNameAndEmail(UserVo param);

    //  비밀번호 질문 조회
    public List<QuestionVo> getJoinQuestionList();

    // 비밀번호 찾기 질문 조회
    public HashMap<String, Object> getUserQuestionById(UserVo param);

    // 비밀번호 질문 답변
    public UserVo getUserPwByfindAnswer(UserVo param);

    // 임시비밀번호 발급
    public void getUserUpdatePw(UserVo param);

    // 로그인유지
    public void keepLogin(String uid, String sessionId, Date next);

    // Session Key 확인
    public UserVo checkLoginBefore(String value);

    //  유저 정보 업데이트
    public void updateUserInfoDate(UserVo param);

    //  유저정보 확인
    public UserVo getUser(String user_id);

    //  비밀번호 변경
    public void modifyPassword(String user_id, String user_pw);

    //  회원정보 탈퇴
    public void deleteUserInfoByUserNo(UserVo param);

    //  아이디 체크
    public boolean isCheckId(String user_id);

    //  닉네임 체크
    public boolean isCheckNickName(UserVo param);

    //  이메일 체크
    public boolean isCheckEmail(UserVo param);

    //  계정복구 정보 조회
    public int checkUser(UserVo param);

    //  계정 활성화
    public void recoveryUserByInfo(UserVo param);
}
