/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 7월 2일 JerryDEV All rights reserved.         │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성자 : Min Woo Song
작성일 : 2023-07-02
작성시간 : 오후 7:51
작성용도 : User Repository layer (Data Access Object) Implements Class File
*/

package com.example.jerry.user.persistance;

import com.example.jerry.commons.annotation.LogException;
import com.example.jerry.user.domain.LoginDTO;
import com.example.jerry.user.domain.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

    private static final String NAMESPACE = "mappers.user.UserSQLMapper";
    private final SqlSession sqlSession;

    @Autowired
    public UserDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }


    @Override
    @LogException
    public void insertUser(UserVo userVo) {
        sqlSession.insert(NAMESPACE + ".insertUser", userVo);
    }

    /* 회원가입 아이디 중복 체크 */
    @Override
    public int isExistId(String user_id) {
        return sqlSession.selectOne(NAMESPACE + ".isExistId", user_id);
    }

    /* 회원가입 닉네임 중복 체크 */
    @Override
    public int isExistNickName(String user_nickname) {
        return sqlSession.selectOne(NAMESPACE + ".isExistNickName", user_nickname);
    }

    /* 회원가입 휴대폰번호 중복 체크 */
    @Override
    public int isExistPhoneNumber(String user_phone) {
        return sqlSession.selectOne(NAMESPACE + ".isExistPhoneNumber", user_phone);
    }

    /* 회원가입 이메일 중복 체크 */
    @Override
    public int isExistEmail(String user_email) {
        return sqlSession.selectOne(NAMESPACE + ".isExistEmail", user_email);
    }

    //  로그인
    @Override
    @LogException
    public UserVo selectByIdAndPw(LoginDTO loginDTO) {
        return sqlSession.selectOne(NAMESPACE + ".selectByIdAndPw", loginDTO);
    }
}
