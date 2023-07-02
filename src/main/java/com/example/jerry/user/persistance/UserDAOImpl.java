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
import com.example.jerry.user.domain.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO{

    private static final String NAMESPACE = "mappers.user.UserSQLMapper";

    @Autowired
    private SqlSession sqlSession;

    @Override
    @LogException
    public void insertUser(UserVo userVo) {
        sqlSession.insert(NAMESPACE + ".insertUser", userVo);
    }
}
