/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 8월 28일 JerryDEV All rights reserved.        │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성자 : Min Woo Song
작성일 : 2023-08-28
작성시간 : 오전 7:33
작성용도 : Bulletin board business layer (service layer) Interface Class File
*/

package com.example.jerry.board.service;

import java.util.ArrayList;
import java.util.HashMap;

public interface BoardService {

    //  게시글 목록
    public ArrayList<HashMap<String, Object>> getBoardList();
}
