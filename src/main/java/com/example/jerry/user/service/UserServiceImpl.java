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
import com.example.jerry.user.domain.QuestionVo;
import com.example.jerry.user.domain.UserVo;
import com.example.jerry.user.persistance.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
    @LogException
    public int isExistId(String user_id) {
        return userDAO.isExistId(user_id);
    }

    /* 회원가입 닉네임 중복체크 */
    @Override
    @LogException
    public int isExistNickName(String user_nickname) {
        return userDAO.isExistNickName(user_nickname);
    }

    /* 회원가입 휴대폰번호 중복체크 */
    @Override
    @LogException
    public int isExistPhoneNumber(String user_phone) {
        return userDAO.isExistPhoneNumber(user_phone);
    }

    /* 회원가입 이메일 중복 체크 */
    @Override
    @LogException
    public int isExistEmail(String user_email) {
        return userDAO.isExistEmail(user_email);
    }

    //  로그인
    @Override
    @LogException
    public UserVo login(LoginDTO loginDTO) {
        userDAO.updateLastConnectionDate(loginDTO);
        return userDAO.selectByIdAndPw(loginDTO);
    }

    //  아이디 찾기
    @Override
    @LogException
    public HashMap<String, Object> getUserIdByNickNameAndEmail(UserVo param) {
        return userDAO.getUserIdByNickNameAndEmail(param);
    }

    // 비밀번호 찾기 질문
    @Override
    @LogException
    public List<QuestionVo> getJoinQuestionList() {
        return userDAO.getJoinQuestionList();
    }

    //  비밀번호 찾기 질문 조회
    @Override
    @LogException
    public HashMap<String, Object> getUserQuestionById(UserVo param) {
        return userDAO.getUserQuestionById(param);
    }

    //  비밀번호 질문 답변
    public UserVo getUserPwByfindAnswer(UserVo param) {
        return userDAO.getUserPwByfindAnswer(param);
    }

    //  임시 비밀번호 발급
    public void getUserUpdatePw(UserVo param) {
        userDAO.getUserUpdatePw(param);
    }

    //  자동로그인
    @Override
    @LogException
    public void keepLogin(String user_id, String sessionId, Date next) {
        userDAO.keepLogin(user_id, sessionId, next);
    }

    // Session Key 확인
    @Override
    @LogException
    public UserVo checkLoginBefore(String value) {
        return userDAO.checkUserWithSessionKey(value);
    }

    //  유저 정보 프로필 업데이트
    @Override
    @LogException
    public void updateUserInfoDate(UserVo param) {
        userDAO.updateUserInfoDate(param);
    }

    //  유저정보 조회
    @Override
    @LogException
    public UserVo getUser(String user_id) {
        return userDAO.getUser(user_id);
    }
}
