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

import com.example.jerry.user.domain.UserVo;

public interface UserDAO {

    /* 회원가입 프로세스 */
    public void insertUser(UserVo param);
}
