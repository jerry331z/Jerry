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
import com.example.jerry.user.domain.QuestionVo;
import com.example.jerry.user.domain.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDAOImpl implements UserDAO {

    private static final String NAMESPACE = "mappers.user.UserSQLMapper";
    private final SqlSession sqlSession;

    @Autowired
    public UserDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }


    /* 회원가입 */
    @Override
    @LogException
    public void insertUser(UserVo userVo) {
        sqlSession.insert(NAMESPACE + ".insertUser", userVo);
    }

    /* 회원가입 아이디 중복 체크 */
    @Override
    @LogException
    public int isExistId(String user_id) {
        return sqlSession.selectOne(NAMESPACE + ".isExistId", user_id);
    }

    /* 회원가입 닉네임 중복 체크 */
    @Override
    @LogException
    public int isExistNickName(String user_nickname) {
        return sqlSession.selectOne(NAMESPACE + ".isExistNickName", user_nickname);
    }

    /* 회원가입 휴대폰번호 중복 체크 */
    @Override
    @LogException
    public int isExistPhoneNumber(String user_phone) {
        return sqlSession.selectOne(NAMESPACE + ".isExistPhoneNumber", user_phone);
    }

    /* 회원가입 이메일 중복 체크 */
    @Override
    @LogException
    public int isExistEmail(String user_email) {
        return sqlSession.selectOne(NAMESPACE + ".isExistEmail", user_email);
    }

    //  로그인
    @Override
    @LogException
    public UserVo selectByIdAndPw(LoginDTO loginDTO) {
        return sqlSession.selectOne(NAMESPACE + ".selectByIdAndPw", loginDTO);
    }

    //  최종로그인 시간 업데이트
    @Override
    @LogException
    public void updateLastConnectionDate(LoginDTO loginDTO) {
        sqlSession.update(NAMESPACE + ".updateLastConnectionDate", loginDTO);
    }

    //  아이디 찾기
    @Override
    @LogException
    public HashMap<String, Object> getUserIdByNickNameAndEmail(UserVo param) {
        return sqlSession.selectOne(NAMESPACE + ".getUserIdByNickNameAndEmail", param);
    }

    //  비밀번호 찾기 질문
    @Override
    @LogException
    public List<QuestionVo> getJoinQuestionList() {
        return sqlSession.selectList(NAMESPACE + ".getJoinQuestionList");
    }

    // 비밀번호 찾기 질문 조회
    @Override
    @LogException
    public HashMap<String, Object> getUserQuestionById(UserVo param) {
        return sqlSession.selectOne(NAMESPACE + ".getUserQuestionById", param);
    }

    //  비밀번호 찾기 질문 답변
    public UserVo getUserPwByfindAnswer(UserVo param) {
        return sqlSession.selectOne(NAMESPACE + ".getUserPwByfindAnswer", param);
    }

    //  임시 비밀번호 발급
    @Override
    @LogException
    public void getUserUpdatePw(UserVo param) {
        sqlSession.update(NAMESPACE + ".getUserUpdatePw", param);
    }

    // 로그인 유지
    @Override
    @LogException
    public void keepLogin(String user_id, String sessionId, Date next) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("user_id", user_id);
        paramMap.put("sessionId", sessionId);
        paramMap.put("next", next);

        sqlSession.update(NAMESPACE + ".keepLogin", paramMap);
    }

    // Session Key 확인
    @Override
    @LogException
    public UserVo checkUserWithSessionKey(String value) {
        return sqlSession.selectOne(NAMESPACE + ".checkUserWithSessionKey", value);
    }

    //  유저정보 업데이트
    @Override
    @LogException
    public void updateUserInfoDate(UserVo param) {
        sqlSession.update(NAMESPACE + ".updateUserInfoDate", param);
    }

    //  유저정보 조회
    @Override
    @LogException
    public UserVo getUser(String user_id) {
        return sqlSession.selectOne(NAMESPACE + ".getUser", user_id);
    }

    //  비밀번호 변경
    @Override
    @LogException
    public void modifyPassword(String user_id, String user_pw) {
        Map<String, Object> keyValue = new HashMap<>();
        keyValue.put("user_id", user_id);
        keyValue.put("user_pw", user_pw);

        sqlSession.update(NAMESPACE + ".modifyPassword", keyValue);
    }

    //  회원정보 탈퇴
    @Override
    @LogException
    public void deleteUserInfoByUserNo(UserVo param) {
        sqlSession.update(NAMESPACE + ".deleteUserInfoByUserNo", param);
    }

    //  아이디 체크
    public int isCheckId(String user_id) {
        return sqlSession.selectOne(NAMESPACE + ".isCheckId", user_id);
    }

    //  닉네임 체크
    public int isCheckNickName(UserVo param) {
        return sqlSession.selectOne(NAMESPACE + ".isCheckNickName", param);
    }

    //  이메일 체크
    public int isCheckEmail(UserVo param) {
        return sqlSession.selectOne(NAMESPACE + ".isCheckEmail", param);
    }

    //  계정 복구 정보 조회
    public int checkUser(UserVo param) {
        return sqlSession.selectOne(NAMESPACE + ".checkUser", param);
    }

    //  계정 활성화
    public void recoveryUserByInfo(UserVo param) {
        sqlSession.update(NAMESPACE + ".recoveryUserByInfo", param);
    }

    //  게시글 작성자 정보
    public UserVo getUserByNo(int user_no) {
        return sqlSession.selectOne(NAMESPACE + ".getUserByNo", user_no);
    }
}
