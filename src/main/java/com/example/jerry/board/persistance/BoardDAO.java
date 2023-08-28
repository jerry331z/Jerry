/*
 ┌───────────────────────────────────────────────────────────────────┐
 │ Copyright (c) 2023년 8월 28일 JerryDEV All rights reserved.        │
 └───────────────────────────────────────────────────────────────────┘
 */

/*
작성자 : Min Woo Song
작성일 : 2023-08-28
작성시간 : 오전 7:11
작성용도 : Bulletin board Repository layer (Data Access Object) Interface Class File
*/

package com.example.jerry.board.persistance;

import com.example.jerry.board.domain.BoardVo;

import java.util.List;

public interface BoardDAO {

    //  게시글 목록
    public List<BoardVo> getBoardList();
}
